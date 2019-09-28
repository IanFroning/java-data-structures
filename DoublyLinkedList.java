package edu.coloradocollege.ifroning.datastructures;

class DoubleListNode<T> {
    T element;
    DoubleListNode<T> next;
    DoubleListNode<T> prev;

    public DoubleListNode(T newElement) {
        element = newElement;
        next = null;
        prev = null;
    }

    public void addNext(DoubleListNode<T> newNext) {
        next = newNext;
    }

    public void addPrevious(DoubleListNode<T> newPrevious) {
        prev = newPrevious;
    }

    public DoubleListNode<T> getNext() {
        return next;
    }

    public DoubleListNode<T> getPrevious() {
        return prev;
    }

    public T getElement() {
        return element;
    }
}

public class DoublyLinkedList<T> {

    DoubleListNode<T> first;
    DoubleListNode<T> last;
    int listLength;

    public DoublyLinkedList() {
        first = null;
        last = null;
        listLength = 0;
    }

    public void add(T newElement) {
        DoubleListNode<T> newNode =
                                new DoubleListNode<T>(newElement);
        if (first == null) {
            first = newNode;
        }
        else {
            last.addNext(newNode);
            newNode.addPrevious(last);
        }
        last = newNode;
        listLength++;
    }

    public void add(T newElement, int index) {
        DoubleListNode<T> newNode = new DoubleListNode<T>(newElement);
        if (first == null) {
            first = newNode;
            last = newNode;
        }
        else if (index == 0) {
            newNode.addNext(first);
            first.addPrevious(newNode);
            first = newNode;
        }
        else {
            if (index < listLength) {
                DoubleListNode<T> previous = first;
                for (int i = 0; i < index - 1; i++) {
                    previous = previous.getNext();
                }
                newNode.addNext(previous.getNext());
                previous.getNext().addPrevious(newNode);
                previous.addNext(newNode);
                newNode.addPrevious(previous);
            }
            else {
                System.out.println("Index doesn't exist");
            }
        }
        listLength++;
    }

    public void add(T newElement, T previousElement) {
        DoubleListNode<T> newNode =
                                new DoubleListNode<T>(newElement);
        DoubleListNode<T> current = first;
        int copies = 0;
        for (int i = 0; i < listLength; i++) {
            if (current.getElement() == previousElement) {
                newNode.addNext(current.getNext());
                current.getNext().addPrevious(newNode);
                current.addNext(newNode);
                newNode.addPrevious(current);
                listLength++;
                copies++;
            }
            current = current.getNext();
        }
        System.out.println("Added " + copies + " copies of " +
                                                newElement);
    }

    public void removeFirst() {
        if (first != null) {
            first = first.getNext();
            listLength--;
        }
    }

    public void removeLast() {
        if (last != null) {
            last = last.getPrevious();
            listLength--;
        }
    }

    public void remove(T element) {
        DoubleListNode<T> current = first;
        int copies = 0;
        for (int i = 0; i < listLength; i++) {
            if (current.getNext().getElement() == element) {
                current.addNext(current.getNext().getNext());
                if (current.getNext().getNext() != null) {
                    current.getNext().getNext().addPrevious(current);
                }
                listLength--;
                copies++;
            }
            current = current.getNext();
        }
        System.out.println("Removed " + copies + " copies of " +
                                                element);
    }
    public T getFirst() {
        return first.getElement();
    }

    public T getLast() {
        return last.getElement();
    }

    public T get(int index) {
        if (index < listLength) {
            DoubleListNode<T> current = first;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current.getElement();
        }
        else {
            System.out.println("Index doesn't exist:");
            return null;
        }
    }

    public boolean contains(T element) {
        DoubleListNode<T> current = first;
        for (int i = 0; i < listLength; i++) {
            if (current.getElement() == element) {
                return true;
            }
            current = current.getNext();
        }
        return false;

    }
    
    public boolean isEmpty() {
        if (listLength == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public int size() {
        return listLength;
    }

    public static void main(String[] args)  {
        DoublyLinkedList<String> myList =
                                    new DoublyLinkedList<String>();
        myList.add("First");
        myList.add("New First", 0);
        myList.add("Last");
        myList.add("Middle", 2);
        myList.add("Median", "Middle");
        myList.removeFirst();
        myList.removeLast();
        myList.remove("Median");
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }
        System.out.println(myList.contains("First"));
    }
}
