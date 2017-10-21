package ru.dionisius;

import java.sql.Timestamp;

/**
 * Created by Dionisius on 26.07.2017.
 * Job vacancy class.
 * Contains ru.dionisius.data of job's vacancy title,
 * description and creation date.
 */
public class Job {
    /**
     * Job vacancy title.
     */
    private final String title;
    /**
     * Job vacancy description.
     */
    private final String description;
    /**
     * Job vacancy creation date.
     */
    private final Timestamp create_date;

    /**
     * Constructor.
     * @param title job vacancy title.
     * @param description job vacancy description.
     * @param create_date job vacancy creation date.
     */
    public Job(final String title, final String description, final Timestamp create_date) {
        this.title = title;
        this.description = description;
        this.create_date = create_date;
    }

    /**
     * Job vacancy title.
     * @return job vacancy title.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Job vacancy description getter.
     * @return job vacancy description.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Job vacancy creation date getter.
     * @return job vacancy creation date.
     */
    public Timestamp getCreate_date() {
        return this.create_date;
    }
}
