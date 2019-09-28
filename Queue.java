package edu.coloradocollege.ifroning.datastructures;

public class Queue<T> {
    DoublyLinkedList<T> myList;

    public Queue() {
        myList = new DoublyLinkedList<T>();
    }

    public boolean isEmpty() {
        return myList.isEmpty();
    }

    public int size() {
        return myList.size();
    }

    public T first() {
        return myList.getFirst();
    }

    public void enqueue(T newElement) {
        myList.add(newElement);
    }

    public T dequeue() {
        T element = myList.getFirst();
        myList.removeFirst();
        return element;
    }
}
