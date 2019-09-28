package edu.coloradocollege.ifroning.datastructures;

class StackNode<T> {
    T element;
    StackNode<T> next;
    
    public StackNode(T newElement) {
        element = newElement;
        next = null;
    }

    public T getElement() {
        return element;
    }

    public StackNode<T> getNext() {
        return next;
    }

    public void addNext(StackNode<T> newNext) {
        next = newNext;
    }
}

public class Stack<T> {
    StackNode<T> top;
    int length;

    public Stack() {
        top = null;
        length = 0;
    }

    public void push(T newElement) {
        StackNode<T> newNode = new StackNode<T>(newElement);
        newNode.addNext(top);
        top = newNode;
        length++;
    }

    public T pop() {
        T popped = top.getElement();
        top = top.getNext();
        length--;
        return popped;
    }

    public T peek() {
        return top.getElement();
    }

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        if (length == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String[] args) {
        Stack<String> s = new Stack<String>();
        System.out.println(s.isEmpty());
        s.push("Pop");
        s.push("the");
        s.push("Top");
        System.out.println(s.pop());
        System.out.println(s.peek());
        System.out.println(s.size());
        System.out.println(s.isEmpty());
    }
}
