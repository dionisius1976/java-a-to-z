package ru.dionisius;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Created by Dionisius on 21.07.2017.
 */
public class JobsCollector implements IDataCollector {
    /**
     *
     */
    private final String jobSiteUrl = "http://www.sql.ru/forum/job-offers";

    /**
     * Logger for database errors.
     */
    private static final Logger LOG = LoggerFactory.getLogger(JobsCollector.class);
    /**
     * Specified DbJobManager instance.
     */
    private final DbJobManager dbJobManager;
    /**
     * SimpleDateFormat for converting dates from forum.
     */
    private final SimpleDateFormat format = new SimpleDateFormat("d MMM yy, HH:mm", new Locale("ru", "RU"));

    /**
     * Constructor.
     * @param dbJobManager
     */
    public JobsCollector(final DbJobManager dbJobManager) {
        super();
        this.dbJobManager = dbJobManager;
    }

    @Override
    public void collectData() {
        List<Job> jobsList = new ArrayList<>();
        Timestamp lastVacancyDate = this.dbJobManager.getLastVacancyDate();
        if (lastVacancyDate == null) {
            lastVacancyDate = this.setDateToCurrentNewYear();
        }
        try {
            String title = null;
            String description = null;
            Timestamp create_date = null;
            Document doc = Jsoup.connect(this.jobSiteUrl).get();
            Elements topics = doc.select("tr:has(.postslisttopic)");
            for (Element topic : topics) {
                if (topic.text().toLowerCase().contains("java") && !topic.text().toLowerCase().contains("script") &&
                        !topic.text().toLowerCase().contains("закрыт")) {
                    Elements link = topic.select("td.postslisttopic > a[href]");
                    Elements data = topic.select("td");
                    title = link.attr("href");
                    description = link.get(0).text();
                    create_date = parseDate(data.get(5).text());
                    if(create_date.before(lastVacancyDate)) {
                        break;
                    }
                    jobsList.add(new Job(title, description, create_date));
                }
            }
            this.dbJobManager.add(jobsList);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            this.dbJobManager.disconnectDb();
        }
    }

    private Timestamp setDateToCurrentNewYear() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(LocalDate.now().getYear(), 0, 1, 0, 0, 0);
        return new Timestamp(calendar.getTimeInMillis());
    }

    public Timestamp parseDate(String date) {
        Calendar calendar = Calendar.getInstance();
        if (date.contains("сегодня")) {
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(date.substring(9, 11)));
            calendar.set(Calendar.MINUTE, Integer.parseInt(date.substring(12, 14)));
        } else if (date.contains("вчера")) {
            calendar.add(Calendar.DATE, -1);
            calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(date.substring(7, 9)));
            calendar.set(Calendar.MINUTE, Integer.parseInt(date.substring(10, 12)));
        } else {
            try {
                calendar.setTime(format.parse(date));
            } catch (ParseException e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return new Timestamp(calendar.getTimeInMillis());
    }

}
