package ru.dionisius;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dionisius on 22.02.2017.
 */
public class SimpleTreeIsBalanced<E extends Comparable> implements ISimpleTree<E>,
                                                                    ISimpleTreeSearch<E>,
                                                                    ISimpleBalanced<E> {
    /**
     * Root element of this tree.
     */
    private Leaf<E> root = null;
    /**
     * List of all this tree's children.
     */
    private List<E> allChilds = new LinkedList<E>();

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
        return this.isElementConsists(this.root, element);
    }

    @Override
    public boolean isBalanced() {
        return this.isTreeBalanced(this.root);
    }

    private boolean isTreeBalanced(Leaf<E> rootLeaf) {
        if (rootLeaf == null || rootLeaf.getChildrenNumber() > 2) {
            return false;
        }
        List<Leaf<E>> allChilds = rootLeaf.getChildren();
        for (Leaf<E> leaf : allChilds) {
            this.isTreeBalanced(leaf);
        }
        return true;
    }

    /**
     * Checks if specified element consists in the tree with specified root.
     * @param rootLeaf specified root.
     * @param element specified element that is checking.
     * @return true if specified element consists in in the tree with specified root.
     */
    private boolean isElementConsists(Leaf<E> rootLeaf, E element) {
        List<Leaf<E>> allChilds = rootLeaf.getChildren();
        for (Leaf<E> leaf : allChilds) {
            if (element.equals(leaf.getValue())) {
                return true;
            }
            if (!leaf.getChildren().isEmpty()) {
                for (Leaf<E> current : leaf.getChildren()) {
                    this.isElementConsists(current, element);
                }
            }
        }
        return false;
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
        public int getChildrenNumber() {
            return this.index;
        }
    }
}
