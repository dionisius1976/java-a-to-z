package ru.dionisius;

import java.util.Iterator;

/**
 * Created by Dionisius on 02.02.2017.
 */
public class CommonIterator implements IIterator{

    @Override
    public Iterator<Integer> convert(final Iterator<Iterator<Integer>> it) {
        return new ConvertingIterator(it);
    }

    private class ConvertingIterator implements Iterator {
        /**
         *
         */
        private final Iterator<Iterator<Integer>> it;
        /**
         *
         */
        private Iterator<Integer> currentIterator = null;

        /**
         * @param it
         */
        private ConvertingIterator(final Iterator<Iterator<Integer>> it) {
            this.it = it;
            if (it != null && it.hasNext()) {
                this.currentIterator = it.next();
            }
        }

        @Override
        public boolean hasNext() {
            boolean result = false;
            if (this.currentIterator != null && (this.currentIterator.hasNext() || it.hasNext())) {
                result = true;
            }
            return result;
        }

        @Override
        public Object next() {
            if (!this.currentIterator.hasNext()) {
                this.currentIterator = it.next();
            }
            return this.currentIterator.next();
        }
    }
}
