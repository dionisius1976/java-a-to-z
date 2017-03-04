package ru.dionisius;

/**
 * Created by Dionisius on 28.02.2017.
 * The class to run the program.
 */
public class Runner {
    /**
     * Method runs the program.
     * @param args console arguments.
     */
    public static void main(String[] args) {
        String fileName  = "c:/orders.xml";
        Processor processor = new Processor();

        long simpleTime = -System.currentTimeMillis();
        processor.init(fileName);
        simpleTime += System.currentTimeMillis();
        System.out.printf("%s%d%s%s", "Operating time: ", simpleTime, " ms.", System.lineSeparator());
        System.out.println();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        processor.showResults();
    }
}
