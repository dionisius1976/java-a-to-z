package ru.dionisius.hashList;

import java.util.Iterator;

/**
 * Created by Dionisius on 16.02.2017.
 */
public class HashList<T, V> implements IHashList<T, V> {
    /**
     *
     */
    private final int INITIAL_CAPACITY = 10;
    /**
     *
     */
    private Node<T,V>[] nodes = (Node<T,V>[])new Node[this.INITIAL_CAPACITY];
    /**
     *
     */
    private int size = 0;
    /**
     *
     */
    boolean isNullKeyExist = false;

    @Override
    public boolean insert(final T key, final V value) {
        if (this.size == this.nodes.length) {
            this.grow();
        }
        if (key == null) {
            this.addNullKey(value);
        } else {
            int hash = this.hash(key.hashCode());
            int position = this.getPosition(hash);
            for (Node n = this.nodes[position]; n != null; n.next()) {
                Object k;
                if (n.hash == hash && ((k = n.key) == key || key.equals(k))) {
                    n.value = value;
                    return true;
                }
            }
            this.addEntry(hash, key, value, position);
            this.size++;
        }
        return true;
    }

    private void addNullKey(V value) {
        if (!this.isNullKeyExist) {
            this.size++;
            this.isNullKeyExist = true;
        }
        this.nodes[0] = new Node<>(0, null, value, null);

    }

    private int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^= (h >>> 7) ^ (h >>> 4);
    }


    private int getPosition(int hash) {
        return Math.abs(hash % (this.nodes.length - 1));
    }

    @Override
    public V get(final T key) {
        int hash;
        int position;
        V returnValue = null;
        if (key != null) {
            hash = this.hash(key.hashCode());
            position = this.getPosition(hash);
        } else {
            hash = 0;
            position = 0;
        }
        for (Node n = this.nodes[position]; n != null; n.next()) {
            Object k;
            if (n.hash == hash && ((k = n.key) == key || key.equals(k))) {
                returnValue = (V) n.value;
                break;
            }
        }
        return returnValue;
    }

    @Override
    public boolean delete(final T key) {
        boolean returnValue = false;
        int hash = this.hash(key.hashCode());
        int position = this.getPosition(hash);
        for (Node n = this.nodes[position]; n != null; n.next()) {
            Object k;
            if (n.hash == hash && ((k = n.key) == key || key.equals(k))) {
                this.nodes[position] = n.next();
                returnValue = true;
                break;
            }
        }
        return returnValue;
    }

    @Override
    public Iterator<Node> iterator() {
        return new ThisIterator<>();
    }

    private class ThisIterator<T, V> implements Iterator{
        int index = 0;
        int nodesNumber = 0;
        HashList.Node current;
        boolean inLoop = false;

        @Override
        public boolean hasNext() {
            boolean hasNext = false;
            if (!inLoop) {
                if (nodes[this.index] == null || nodes[this.index].next() == null) {
                    hasNext = this.nodesNumber < size;
                } else {
                    this.inLoop = true;
                    this.current = this.current.next();
                    hasNext = true;
                }
            } else {
                if (nodes[this.index].next == null) {
                    hasNext = this.nodesNumber < size;
                    this.inLoop = false;
                } else {
                    this.inLoop = true;
                    hasNext = true;
                }
            }
            return hasNext;
        }

        @Override
        public V next() {
            V value = null;
            HashList.Node o = null;
                if((!inLoop)) {
                    o = nodes[this.index++];
                } else {
                    o = this.current;
                    this.current = this.current.next();
                    this.nodesNumber++;
                }
                if (o != null) {
                    value = (V) o.getValue();
                    this.nodesNumber++;
                }
            return value;
        }
    }

    private void addEntry(int hash, T key, V value, int index) {
        Node n = this.nodes[index];
        if (n == null) {
            this.nodes[index] = new Node(hash, key, value, n);
        } else {
            n.next = new Node(hash, key, value, n);
        }
    }


    private void grow() {
        Node[] newNodes = (Node[]) new Object[this.nodes.length >> 1];
        System.arraycopy(this.nodes, 0, newNodes, 0, this.nodes.length);
        this.nodes = newNodes;
    }

    private class Node<T, V> {
        private int hash;
        private T key;
        private V value;
        private Node<T, V> next;
        public Node(final int hash, final T key, final V value, final Node next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
        public Node next() {
            return this.next;
        }
        public V getValue() {return this.value;}
    }
}
