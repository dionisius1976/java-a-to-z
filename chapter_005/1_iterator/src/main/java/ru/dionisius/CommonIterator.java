package ru.dionisius;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Dionisius on 02.02.2017.
 */
public class CommonIterator implements IIterator{

    @Override
    public Iterator<Integer> convert(final Iterator<Iterator<Integer>> it) {
        return new ConvertingIterator(it);
    }

    private class ConvertingIterator implements Iterator {
//        private final List<Iterator<Integer>> it;
        private int iteratorsIndex = 0;
//        private final Iterator<Iterator<Integer>> iter;
        private Iterator<Integer> currentIterator;

//        private ConvertingIterator(final Iterator<Iterator<Integer>> it) {
//            this.it = this.initItArray(it);
//        }
        private ConvertingIterator(final Iterator<Iterator<Integer>> it) {
            this.iter = it;
        }

        @Override
        public boolean hasNext() {
            boolean result = false;
            while (this.iter.hasNext()) {
                if (this.iter.next().hasNext()) {
                    result = true;
                }
            }
            return result;
        }

        @Override
        public Object next() {
            while (this.iter.hasNext()) {
                while (this.iter.next().hasNext()) {

                }
            }
            return null;
        }

//        private List<Iterator<Integer>> initItArray (Iterator<Iterator<Integer>> it) {
//            List<Iterator<Integer>> returnIterator = new ArrayList<>();
//            if (it != null) {
//                while (it.hasNext()) {
//                    returnIterator.add(it.next());
//                }
//            }
//            return returnIterator;
//        }

//        @Override
//        public boolean hasNext() {
//            boolean returnValue = false;
//                if (this.iteratorsIndex < this.it.size()) {
//                    if (this.it.get(this.iteratorsIndex) != null) {
//                        if (this.it.get(this.iteratorsIndex).hasNext()) {
//                            returnValue = true;
//                        } else {
//                            for (int i = this.iteratorsIndex + 1; i < this.it.size(); i++) {
//                                if (this.it.get(i) == null) {
//                                    continue;
//                                }
//                                if (this.it.get(i).hasNext()) {
//                                    returnValue = true;
//                                    this.iteratorsIndex = i;
//                                    break;
//                                }
//                            }
//                        }
//                    } else {
//                        for (int i = this.iteratorsIndex + 1; i < this.it.size(); i++) {
//                            if (this.it.get(i) == null) {
//                                continue;
//                            }
//                            if (this.it.get(i).hasNext()) {
//                                returnValue = true;
//                                this.iteratorsIndex = i;
//                                break;
//                            }
//                        }
//                    }
//                }
//            return returnValue;
//        }
//
//        @Override
//        public Object next() {
//            Object returnValue = new Object();
//            if (this.it.get(this.iteratorsIndex) != null) {
//                returnValue = this.it.get(this.iteratorsIndex).next();
//            } else {
//                this.iteratorsIndex++;
//            }
//            return returnValue;
//        }
//    }
}
