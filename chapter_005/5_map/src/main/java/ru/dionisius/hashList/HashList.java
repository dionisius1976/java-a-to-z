package ru.dionisius.hashList;

import java.util.Iterator;

/**
 * Created by Dionisius on 16.02.2017.
 */
public class HashList<T, V> implements IHashList<T, V> {
    private final int INITIAL_SIZE = 50;
    private Node[] nodes = new Node[50];
    int[] x = new int[30];
    private int size = 0;

    @Override
    public boolean insert(final T key, final V value) {
        if (this.size == this.nodes.length) {
            this.grow();
        }
        for (int i = 0; i < this.size; i++) {
            for (Node n =this.nodes[i]; n != null; n.next()) {

            }
        }
        this.size++;
        return false;
    }

    @Override
    public V get(final T key) {
        return null;
    }

    @Override
    public boolean delete(final T key) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return new Iterator() {
            int index = 0;
            @Override
            public boolean hasNext() {
                return this.index > size;
            }

            @Override
            public Object next() {
                return nodes[this.index++];
            }
        }
    }

    private void grow() {

    }

    private class Node {
        private int hash;
        private T key;
        private V value;
        private Node next;
        public Node(int hash, T key, V value, Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
        public Node next() {
            return this.next;
        }
    }

}
