package ru.dionisius;

/**
 * Created by Dionisius on 13.02.2017.
 * Compares the time of adding elements to specified sets.
 */
public class SpeedComparer {
    /**
     * Compares the time of adding elements to specified sets.
     * Prints the difference in percents.
     * @param args console arguments.
     */
    public static void main(String[] args) {
        ISet<String> simple = new SimpleArraySet<>();
        ISet<String> fast = new FastSimpleArraySet<>();
        long simpleTime = -System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            simple.add(String.valueOf(i));
        }
        simpleTime += System.currentTimeMillis();
        long fastTime = -System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            fast.add(String.valueOf(i));
        }
        fastTime += System.currentTimeMillis();
        long difference = (simpleTime - fastTime) * 100 / simpleTime;
        System.out.printf("%s%d%s%s", "Adding time of SimpleArraySet is ", simpleTime, " ms", System.lineSeparator());
        System.out.printf("%s%d%s%s", "Adding time of FastSimpleArraySet is ", fastTime, " ms", System.lineSeparator());
        System.out.printf("%s%d%s%s", "FastSimpleArray is faster the SimpleArray at ", difference, "%", System.lineSeparator());
    }
}
