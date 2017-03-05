package ru.dionisius;

/**
 * Created by Dionisius on 04.03.2017.
 * Class for testing memory usage.
 */
public class User {
    /**
     * The name of this user.
     */
    private final String name;
    /**
     * Constructor.
     * @param name the name of this user.
     */
    public User(String name) {
        this.name = name;
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
        System.out.println(String.format("Finalize %s", this));
    }

    @Override
    public String toString() {
        return String.format("User %s", name);
    }

    /**
     * Invokes finalize() method.
     * @param args console arguments.
     * @throws InterruptedException if exception occurs.
     */
    public static void main(String[] args) throws InterruptedException {
        long simpleTime = -System.currentTimeMillis();
        for (int index = 0; index < 3000; index++) {
            new User(String.format("%s", index));
        }
        simpleTime += System.currentTimeMillis();
        System.out.println("Working time: " + simpleTime);
        info();
    }

    /**
     * Prints info about memory usage.
     */
    public static void info() {
        int mb = 1024 * 1024;
        Runtime runtime = Runtime.getRuntime();
        System.out.println("##### Heap utilization statistics [MB] #####");
        long temp = runtime.maxMemory();
        System.out.println("Max Memory:" + temp + " bytes");
        temp = runtime.totalMemory();
        System.out.println("Total Memory:" + temp + " bytes");
        temp = (runtime.totalMemory() - runtime.freeMemory());
        System.out.println("Used Memory:" + temp + " bytes");
        temp = runtime.freeMemory();
        System.out.println("Free Memory:" + temp + " bytes");
    }
}
