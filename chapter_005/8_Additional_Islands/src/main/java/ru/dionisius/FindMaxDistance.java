package ru.dionisius;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Dionisius on 07.03.2017.
 * This class is designed for calculating and
 * printing the biggest distance between the first
 * and the last same numbers in specified array.
 */
public class FindMaxDistance {
    /**
     * Specified array with number that ought to be handled.
     */
    private final int[] array;
    /**
     * Stores distance for each number.
     * Key is distance, map value is number.
     */
    private final Map<Integer, NumberValue> numberValues = new HashMap<>();
    //private final TreeSet<NumberValue> orderedValues = new TreeSet<>();
    private int maxDistance;
    private int numberWithMaxDistance;

    /**
     * Constructor.
     * @param array specified array with number that ought to be handled.
     */
    public FindMaxDistance(final int[] array) {
        this.array = array;
        this.numberWithMaxDistance = array[0];
    }
    /**
     * Starts the program.
     */
    public void init() {
        this.find();
        this.showResult();
    }
    /**
     * Calculates the biggest distance between the first
     * and the last same numbers in specified array
     * for every number of the array and stores this
     * calculated data in the map.
     */
    private void find() {
        NumberValue newNumberValue = null;
        int number = 0;
        int distance = 0;
        for (int i = 0; i < this.array.length; i++) {
            number = this.array[i];
            if (!this.numberValues.containsKey(number)) {
                newNumberValue = new NumberValue(i, i, number);
                this.numberValues.put(number, newNumberValue);
                //this.orderedValues.add(newNumberValue);
            } else {
                distance = i - this.numberValues.get(number).getFrom();
                newNumberValue = new NumberValue(this.numberValues.get(number).getFrom(), i, number);
                //this.orderedValues.remove(this.numberValues.remove(number));
                //this.orderedValues.add(newNumberValue);
                this.numberValues.remove(number);
                this.numberValues.put(number, newNumberValue);
                if (distance > this.maxDistance) {
                    this.numberWithMaxDistance = number;
                    this.maxDistance = distance;
                }
            }
        }
    }

    /**
     * Prints the the biggest distance between the first
     * and the last same numbers.
     */
    private void showResult() {
        System.out.format("Max distance is %d for number %d%s", this.maxDistance, this.numberWithMaxDistance, System.lineSeparator());
//        System.out.println(this.orderedValues.first());
    }

    /**
     * Class stores the number and its maximum distance
     * in the array.
     */
    private class NumberValue implements Comparable{
        /**
         * The first position of this number in the array.
         */
        private final int from;
        /**
         * The last position of this number in the array.
         */
        private final int to;
        /**
         * The number.
         */
        private final int numberValue;

        /**
         * Constructor.
         * @param from the first position of this number in the array.
         * @param to the last position of this number in the array.
         * @param numberValue the number.
         */
        private NumberValue(final int from, final int to, final int numberValue) {
            this.from = from;
            this.to = to;
            this.numberValue = numberValue;
        }

        /**
         * Getter of the first position of this number in the array.
         * @return
         */
        public int getFrom() {
            return from;
        }

        /**
         * Getter for number.
         * @return number.
         */
        public int getNumberValue() {
            return numberValue;
        }

        /**
         * Returns the distance between the first and the last position of this number in the array.
         * @return
         */
        public int getDistance() {
            return this.to - this.from;
        }


        @Override
        public String toString() {
            return String.format("Maximum distance is %d for number %d%s", this.getDistance(), this.getNumberValue());
        }

        @Override
        public int compareTo(Object o) {
            NumberValue v = (NumberValue) o;
            return v.getDistance() - this.getDistance();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            NumberValue that = (NumberValue) o;

            if (from != that.from) return false;
            return to != that.to;
        }

        @Override
        public int hashCode() {
            int result = from;
            result = 31 * result + to;
            return result;
        }
    }

    public static void main(String[] args) {
        int[] array = {1,2,3,4,25,26,26,2};
        FindMaxDistance fmd = new FindMaxDistance(array);
        fmd.init();
    }
}
