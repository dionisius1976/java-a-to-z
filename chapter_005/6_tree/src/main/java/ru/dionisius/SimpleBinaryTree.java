package ru.dionisius;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Dionisius on 23.02.2017.
 */
public class SimpleBinaryTree<E extends Comparable> implements ISimpleTree<E>, ISimpleTreeSearch<E> {

    private Node<E> root;
    private List<E> childrens = new LinkedList<E>();

    public SimpleBinaryTree() {}

    @Override
    public void addChild(E value) {
        this. root = this.addValue(this.root, value, null);
    }

    @Override
    public List<E> getChildren() {
        this.childrens.clear();
        this.getChilds(this.root);
        return this.childrens;
    }

    private void getChilds(Node<E> root) {
        if (root != null) {
            if (root.getValue() != null) {
                this.childrens.add(root.getValue());
            } else {
                if (root.getLeft() != null) {
                    this.getChilds(root.getLeft());
                }
                if (root.getRight() != null) {
                    this.getChilds(root.getRight());
                }
            }
        }
    }

    private Node<E> addValue(Node current, E value, Node parent) {
        if (current == null) {
            current = new Node(value, null, null, parent);
        } else {
            int compareResult = current.getValue().compareTo(value);
            if (compareResult < 0) {
                this.addValue(current.getLeft(), value, current);
            } else {
                this.addValue(current.getRight(), value, current);
            }
        }
        return current;
    }

    @Override
    public boolean consists(E value) {
        return this.consists(this.root, value);
    }

    private boolean consists(Node<E> root, E value) {
        if(root == null) {
            return false;
        }
        if (root.getValue().compareTo(value) == 0) {
            return true;
        }
        this.consists(root.getLeft(), value);
        this.consists(root.getRight(), value);
        return false;
    }

    private class Node<E extends Comparable> {
        private E value;
        private Node parent;
        private Node left;
        private Node right;

        public Node(E value, Node<E> left, Node<E> right, Node<E> parent){
            this.value = value;
            this.left = left;
            this.right = right;
            this.parent = parent;
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parant) {
            this.parent = parant;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}
