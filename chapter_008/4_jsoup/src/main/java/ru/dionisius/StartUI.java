package ru.dionisius;

/**
 * Class starts the program that finds all vacancies for
 * java programmers on www.sql.ru site and stores them
 * at specified database.
 */
public class StartUI {
	/**
	 * IDataCollector instance.
	 */
	private final IDataCollector jobsCollector;

	/**
	 * Constructor.
	 * @param jobsCollector
	 */
	public StartUI(final IDataCollector jobsCollector) {
		this.jobsCollector = jobsCollector;
	}

	/**
	 * Initiate controllers of the program.
	 */
	public void init() {
		this.jobsCollector.collectData();
	}

	/**
	 * Starts the program in operating system.
	 * @param args console inputted arguments.
	 */
	public static void main(String[] args) {
		String propertiesFile = "config.properties";
		DbJobManager jobManager = new DbJobManager(propertiesFile);
		IDataCollector jobsCollector = new JobsCollector(jobManager);
		new StartUI(jobsCollector).init();
	}
}