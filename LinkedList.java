package edu.coloradocollege.ifroning.datastructures;

class ListNode<T> {
    T element;
    ListNode<T> next;

    public ListNode(T newElement) {
        element = newElement;
        next = null;
    }

    public void addNext(ListNode<T> newNext) {
        next = newNext;
    }

     public ListNode<T> getNext() {
        return next;
    }

    public T getElement() {
        return element;
    }
}

public class LinkedList<T> {

    ListNode<T> first;
    ListNode<T> last;
    int listLength;

    public LinkedList() {
        first = null;
        last = null;
        listLength = 0;
    }

    public void add(T newElement) {
        ListNode<T> newNode = new ListNode<T>(newElement);
        if (first == null) {
            first = newNode;
        }
        else {
            last.addNext(newNode);
        }
        last = newNode;
        listLength++;
    }

    public void add(T newElement, int index) {
        ListNode<T> newNode = new ListNode<T>(newElement);
        if (first == null) {
            first = newNode;
            last = newNode;
        }
        else if (index == 0) {
            newNode.addNext(first);
            first = newNode;
        }
        else {
            if (index < listLength) {
                ListNode<T> previous = first;
                for (int i = 0; i < index - 1; i++) {
                    previous = previous.getNext();
                }
                newNode.addNext(previous.getNext());
                previous.addNext(newNode);
            }
            else {
                System.out.println("Index doesn't exist");
            }
        }
        listLength++;
    }

    public void add(T newElement, T previousElement) {
        ListNode<T> newNode = new ListNode<T>(newElement);
        ListNode<T> previousNode = new ListNode<T>(previousElement);
        ListNode<T> current = first;
        int copies = 0;
        for (int i = 0; i < listLength; i++) {
            if (current.getElement() == previousNode.getElement()) {
                newNode.addNext(current.getNext());
                current.addNext(newNode);
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
            ListNode<T> current = first;
            for (int i = 0; i < listLength - 1; i++) {
                current = current.getNext();
            }
            last = current;
            last.addNext(null);
            listLength--;
        }
    }

    public void remove(T element) {
        ListNode<T> current = first;
        int copies = 0;
        for (int i = 0; i < listLength; i++) {
            if (current.getNext().getElement() == element) {
                current.addNext(current.getNext().getNext());
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
            ListNode<T> current = first;
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
        ListNode<T> current = first;
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
        LinkedList<String> myList = new LinkedList<String>();
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
