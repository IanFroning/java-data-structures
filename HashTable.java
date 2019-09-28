package edu.coloradocollege.ifroning.datastructures;

class HashNode<K,E> {
    
    K key;
    E element;
    HashNode<K,E> next;

    public HashNode(K nodeKey, E nodeElement) {
        key = nodeKey;
        element = nodeElement;
        next = null;
    }

    public K getKey() {
        return key;
    }

    public E getElement() {
        return element;
    }

    public HashNode<K,E> getNext() {
        return next;
    }

    public boolean hasNext() {
        if (next == null) {return false;}
        else              {return true;}
    }

    public void addNext(HashNode<K,E> newNext) {
        next = newNext;
    }
}

public class HashTable<K,E> {
    
    private HashNode<K,E>[] table;
    private int filled;

    public HashTable(int initialSize) {
        table = (HashNode<K,E>[]) new HashNode[initialSize];
        filled = 0;
    }

    private int getHashCode(K key) {
        int code = key.hashCode();
        if (code < 0) {
            code = - code;
        }
        return code % table.length;
    }

    public void add(K key, E element) {
        /*if (filled + 1 >= table.length) {
            HashNode<K,E>[] doubleTable =
                (HashNode<K,E>[]) new HashNode[2 * table.length];
            for (int i = 0; i < table.length; i++) {
                doubleTable[2 * i] = table[i];
            }
            table = doubleTable;
        }*/
        int index = getHashCode(key);
        HashNode<K,E> toAdd = new HashNode<K,E>(key, element);
        if (table[index] == null) {
            table[index] = toAdd;
            filled++;
        }
        else {
            toAdd.addNext(table[index]);
            table[index] = toAdd;
            filled++;
        }
    }

    public E remove(K key) {
        int index = getHashCode(key);
        HashNode<K,E> node = table[index];
        if (node.getKey().equals(key)) {
            E result = node.getElement();
            table[index] = node.getNext();
            filled--;
            return result;
        }
        while (node.getNext() != null &&
                                    !node.getNext().getKey().equals(key)) {
            node = node.getNext();
        }
        if (node.getNext() != null) {
            E result = node.getNext().getElement();
            filled--;
            node.addNext(node.getNext().getNext());
            return result;
        }
        else {
            return null;
        }
    }

    public E get(K key) {
        int index = getHashCode(key);
        HashNode<K,E> node = table[index];
        while (node != null && !node.getKey().equals(key)) {
            node = node.getNext();
        }
        if (node != null) {
            return node.getElement();
        }
        else {
            return null;
        }
    }


    public int size() {
        return filled;
    }
}
