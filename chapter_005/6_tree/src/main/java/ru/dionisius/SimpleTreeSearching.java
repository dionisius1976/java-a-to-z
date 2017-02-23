package ru.dionisius;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dionisius on 22.02.2017.
 */
public class SimpleTreeSearching <E extends Comparable> implements ISimpleTree<E>, ISimpleTreeSearch<E> {
    /**
     * Root element of this tree.
     */
    private Leaf<E> root = null;
    /**
     * List of all this tree's children.
     */
    private List<E> allChilds = new LinkedList<E>();

    /**
     * The flag for consists method.
     */
    private boolean consist = false;

    @Override
    public void addChild(E value) {
        if (value != null){
            Leaf newLeaf = new Leaf(value);
            if (this.root == null) {
                this.root = newLeaf;
            } else {
                this.addLeaf(this.root, newLeaf);
            }
        }
    }

    @Override
    public List getChildren() {
        this.allChilds.clear();
        this.getChilds(this.root.getChildren());
        return this.allChilds;
    }

    @Override
    public boolean consists(E element) {
        this.consist = false;
        this.isElementConsists(this.root, element);
        return consist;
    }

    /**
     * Checks if specified element consists in tree with specified root.
     * @param rootLeaf specified root.
     * @param element specified element that is searching.
     */
    private void isElementConsists(Leaf<E> rootLeaf, E element) {
        if (element.compareTo(rootLeaf.getValue()) == 0) {
            this.consist = true;
        }
        for (Leaf<E> leaf : rootLeaf.getChildren()) {
            this.isElementConsists(leaf, element);
        }
    }

    /**
     * Adds the new leaf to tree with specified root.
     * @param nodeLeaf specified root.
     * @param leaf the new leaf that adding to tree.
     * @return true if leaf is added.
     */
    private boolean addLeaf(Leaf nodeLeaf, Leaf leaf) {
        if (nodeLeaf.canAdd()) {
            nodeLeaf.addChild(leaf);
            return true;
        } else {
            List<Leaf<E>> nodeChildren = nodeLeaf.getChildren();
            for (Leaf<E> current : nodeChildren) {
                if (current.canAdd()) {
                    this.addLeaf(current, leaf);
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Returns all children of specified leaves list.
     * @param leaves specified leaves list.
     */
    private void getChilds(List<Leaf<E>> leaves) {
        if (!leaves.isEmpty()) {
            for (Leaf leaf : leaves) {
                if (leaf != null && leaf.getValue() != null) {
                    this.allChilds.add((E)leaf.getValue());
                    this.getChilds(leaf.getChildren());
                }
            }
        }
    }

    private class Leaf<E> {
        private E value;
        private List<Leaf<E>> children = new LinkedList<Leaf<E>>();
        private int index = 0;

        public Leaf(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public void addChild(Leaf<E> child) {
            this.children.add(child);
            this.index++;
        }

        public List<Leaf<E>> getChildren() {
            return this.children;
        }

        public boolean canAdd() {
            return this.index < 5;
        }
    }
}