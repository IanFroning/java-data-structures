package edu.coloradocollege.ifroning.datastructures;


import java.util.LinkedList;

class TreeNode<T extends Comparable> {

    TreeNode<T> parent;
    TreeNode<T> leftChild;
    TreeNode<T> rightChild;
    T element;

    public TreeNode(T _element) {
        parent = null;
        element = _element;
        leftChild = null;
        rightChild = null;
    }

    public TreeNode(T _element, TreeNode<T> _parent) {
        parent = _parent;
        element = _element;
        leftChild = null;
        rightChild = null;
    }

    public void addLeftChild(TreeNode<T> newChild) {
        leftChild = newChild;
    }

    public void addRightChild(TreeNode<T> newChild) {
        rightChild = newChild;
    }

    public TreeNode<T> getRightChild() {
        return rightChild;
    }

    public TreeNode<T> getLeftChild() {
        return leftChild;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public T getElement() {
        return element;
    }

}


public class Tree<T extends Comparable> {

    TreeNode<T> root;

    public Tree() {
        root = null;
    }

    public TreeNode<T> getRoot() {
        return root;
    }

    public boolean isEmpty() {
        if (root == null) {
            return true;
        }
        else {
            return false;
        }
    }
    
    public void add(T newElement) {
        if (root == null) {
            root = new TreeNode(newElement);
        }
        else {
            addHelper(newElement, root);
        }
    }

    public void addHelper(T newElement, TreeNode<T> current) {
        if (newElement.compareTo(current.getElement()) < 0) {
            TreeNode<T> leftChild = current.getLeftChild();
            if (leftChild == null) {
                current.addLeftChild(new TreeNode(newElement, current));
            }
            else {
                addHelper(newElement, leftChild);
            }
        }
        else {
            TreeNode<T> rightChild = current.getRightChild();
            if (rightChild == null) {
                current.addRightChild(new TreeNode<T>(newElement, current));
            }
            else {
                addHelper(newElement, rightChild);
            }
        }
    }

    public boolean find(T element) {
        TreeNode<T> removedNode = findHelper(root, element);
        if (removedNode == null) {
            return false;
        }
        else {
            return true;
        }
    }

    public TreeNode<T> findHelper(TreeNode<T> current, T element) {
        if (current.getElement().compareTo(element) == 0) {
            return current;
        }
        else if (current.getElement().compareTo(element) > 0) {
            return findHelper(current.getLeftChild(), element);
        }
        else if (current.getElement().compareTo(element) < 0) {
            return findHelper(current.getRightChild(), element);
        }
        return null;
    }

    public T remove(T element) {
        TreeNode<T> removed = findHelper(root, element);
        if (removed == null) {
            System.out.println("Element not found. Returned null");
            return null;
        }
        else if (removed.getRightChild() == null &&
                                    removed.getLeftChild() == null) {
            if (removed.getParent().getRightChild() == removed) {
                removed.getParent().addRightChild(null);
            }
            else {
                removed.getParent().addLeftChild(null);
            }
        }
        else if (removed.getLeftChild() == null) {
            if (removed.getParent().getRightChild() == removed) {
                removed.getParent().addRightChild(removed.getRightChild());
            }
            else {
                removed.getParent().addLeftChild(removed.getRightChild());
            }
        }
        else if (removed.getRightChild() == null) {
            if (removed.getParent().getRightChild() == removed) {
                removed.getParent().addRightChild(removed.getLeftChild());
            }
            else {
                removed.getParent().addLeftChild(removed.getLeftChild());
            }
        }
        else {
            TreeNode<T> child = removed.getRightChild();
            while (child.getLeftChild() != null) {
                child = child.getLeftChild();
            }
            if (removed == root) {
                root = child;
            }
            else {
                if (removed.getParent().getRightChild() == removed) {
                    removed.getParent().addRightChild(child);
                }
                else {
                    removed.getParent().addLeftChild(child);
                }
            }
            if (child.getParent().getLeftChild() == child) {
                child.getParent().addLeftChild(child.getRightChild());
                child.addRightChild(removed.getRightChild());
            }
            child.addLeftChild(removed.getLeftChild());
        }
            return removed.getElement();
    }

    public int size() {
        int size = sizeHelper(root);
        return size;
    }

    public int sizeHelper(TreeNode<T> current) {
        int count = 0;
        if (current != null) {
            count++;
            count += sizeHelper(current.getLeftChild());
            count += sizeHelper(current.getRightChild());
        }
        return count;
    }

    public int height() {
        int height = heightHelper(root);
        return height;
    }

    public int heightHelper(TreeNode<T> current) {
        if (current == null) {
            return 0;
        }
        else if (current.getLeftChild() == null &&
                                    current.getRightChild() == null) {
            return 1;
        }
        else {
            int leftHeight = 1 + heightHelper(current.getLeftChild());
            int rightHeight = 1 + heightHelper(current.getRightChild());
            if (leftHeight >= rightHeight) {
                return leftHeight;
            }
            else {
                return rightHeight;
            }
        }
    }

    public boolean isBalanced() {
        return isBalancedHelper(root);
    }

    // Adapted from pseudocode found at
    // http://stackoverflow.com/questions/742844/how-to-determine-if-binary-tree-is-balanced
    public boolean isBalancedHelper(TreeNode<T> current) {
        if (current == null) {
            return true;
        }
        else if (current.getLeftChild() == null &&
                                    current.getRightChild() == null) {
            return true;
        }
        else if (isBalancedHelper(current.getLeftChild()) &&
                 isBalancedHelper(current.getRightChild()) &&
                 Math.abs(heightHelper(current.getLeftChild()) -
                 heightHelper(current.getRightChild())) <= 1) {
            return true;
            
        }
        else {
            return false;
        }
    }

    public void printTreeInOrder() {
        printTreeInOrderHelper(root);
    }

    public void printTreeInOrderHelper(TreeNode<T> current) {
        if (current != null) {
            printTreeInOrderHelper(current.getLeftChild());
            System.out.println(current.getElement());
            printTreeInOrderHelper(current.getRightChild());
        }
    }

    public void printTreePreOrder() {
        printTreePreOrderHelper(root);
    }

    public void printTreePreOrderHelper(TreeNode<T> current) {
        if (current != null) {
            System.out.println(current.getElement());
            printTreePreOrderHelper(current.getLeftChild());
            printTreePreOrderHelper(current.getRightChild());
        }
    }

     public void printTreePostOrder() {
        printTreePostOrderHelper(root);
    }

    public void printTreePostOrderHelper(TreeNode<T> current) {
        if (current != null) {
            printTreePostOrderHelper(current.getLeftChild());
            printTreePostOrderHelper(current.getRightChild());
            System.out.println(current.getElement());
        }
    }
    public void printTreeLevelOrder() {
        LinkedList<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode current = q.poll();
            System.out.println(current.getElement());
            if (current.getLeftChild() != null) {
                q.offer(current.getLeftChild());
            }
            if (current.getRightChild() != null) {
                q.offer(current.getRightChild());
            }
        }
    }

    public static void main(String[] args) {
        Tree<Integer> t = new Tree<Integer>();
        t.add(5);
        t.add(1);
        t.add(2);
        t.add(4);
        t.add(3);
        t.add(7);
        t.add(6);
        t.add(8);
        t.add(0);
        t.add(9);
        System.out.println("Height = "+ t.height() +", size = "+ t.size() +
            ", Balanced = "+ t.isBalanced());
        System.out.println("Removed " + t.remove(3));
        System.out.println("Height = "+ t.height() +", size = "+ t.size() +            ", Balanced = "+ t.isBalanced());
    }
}

