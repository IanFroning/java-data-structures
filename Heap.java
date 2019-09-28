package edu.coloradocollege.ifroning.datastructures;

import java.util.ArrayList;

public class Heap<T extends Comparable> {
    
    private ArrayList<T> heap;

    public Heap() {
        heap = new ArrayList<T>();
    }

    public void add(T element) {
        heap.add(element);
        int iAdded = heap.size() - 1;
        int iParent = (iAdded - 1) / 2;
        while (element.compareTo(heap.get(iParent)) < 0 && iAdded > 0) {
            T temp = heap.get(iAdded);
            heap.set(iAdded, heap.get(iParent));
            heap.set(iParent, temp);
            iAdded = iParent;
            iParent = (iAdded - 1) / 2;
        }
    }

    public T remove() {
        if (heap.size() == 0) {
            return null;
        }
        else if (heap.size() == 1) {
            return heap.remove(0);
        }
        else {
            T root = heap.get(0);
            T last = heap.remove(heap.size() - 1);
            heap.set(0, last);
            int iParent = 0;
            int iLChild = 1;
            int iRChild = 2;
            while (iRChild < heap.size() && (
                   heap.get(iParent).compareTo(heap.get(iLChild)) > 0 ||
                   heap.get(iParent).compareTo(heap.get(iRChild)) > 0)) {
					 if (heap.get(iLChild).compareTo(heap.get(iRChild)) < 0) {
                    T parent = heap.get(iParent);
                    heap.set(iParent, heap.get(iLChild));
                    heap.set(iLChild, parent);
                    iParent = iLChild;
                    iLChild = iParent * 2 + 1;
                    iRChild = iParent * 2 + 2;
                }
                else {
                    T parent = heap.get(iParent);
                    heap.set(iParent, heap.get(iRChild));
                    heap.set(iRChild, parent);
                    iParent = iRChild;
                    iLChild = iParent * 2 + 1;
                    iRChild = iParent * 2 + 2;
                }
            }
            if (iLChild == heap.size() - 1 && 
                heap.get(iParent).compareTo(heap.get(iLChild)) > 0) {
                T parent = heap.get(iParent);
                heap.set(iParent, heap.get(iLChild));
                heap.set(iLChild, parent);
            }
            return root;
        }
    }

    public T get() {
        return heap.get(0);
    }

    public boolean find(T element) {
        for (int i = 0; i < heap.size(); i++) {
            if (heap.get(i) == element) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        if (heap.size() == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int size() {
        return heap.size();
    }

    public int height() {
        if (heap.size() == 0) {
            return 0;
        }
        else {
            return 1 + (int) (Math.log10(heap.size()) / Math.log10(2));
        }
    }

    public void printInOrder() {
        printInOrderHelper(0);
    }

    public void printInOrderHelper(int i) {
        if (heap.size() > 2*i + 1) {printInOrderHelper(2*i + 1);}
        System.out.println(heap.get(i));
        if (heap.size() > 2*i + 2) {printInOrderHelper(2*i + 2);}
    }

    public void printPreOrder() {
        printPreOrderHelper(0);
    }

    public void printPreOrderHelper(int i) {
        System.out.println(heap.get(i));
        if (heap.size() > 2*i + 1) {printPreOrderHelper(2*i + 1);}
        if (heap.size() > 2*i + 2) {printPreOrderHelper(2*i + 2);}
    }

    public void printPostOrder() {
        printInOrderHelper(0);
    }

    public void printPostOrderHelper(int i) {
        if (heap.size() > 2*i + 1) {printInOrderHelper(2*i + 1);}
        if (heap.size() > 2*i + 2) {printInOrderHelper(2*i + 2);}
        System.out.println(heap.get(i));
    }

    public void printLevelOrder() {
        for (int i = 0; i < heap.size(); i++) {
            System.out.println(heap.get(i));
        }
    }
}
