package edu.coloradocollege.ifroning.datastructures;

public class Graph<E> {
    private int[][] adjacencies;
    private E[] elements;
    private int filled;

    public Graph(int initalSize) {
        adjacencies = new int[initalSize][initalSize];
        elements = (E[]) new Object[initalSize];
        filled = 0;
    }
    
    private void enlarge() {
        int[][] doubleAdjacencies = new int[filled * 2][filled * 2];
        E[] doubleElements = (E[]) new Object[filled * 2];
        for (int i = 0; i < filled; i++) {
            doubleElements[i] = elements[i];
            for ( int j = 0; j < filled; j++) {
                doubleAdjacencies[i][j] = adjacencies[i][j];
            }
        }
        adjacencies = doubleAdjacencies;
        elements = doubleElements;
    }

    public void addNode(E node) {
        if (filled  >= elements.length) {
            enlarge();
        }
        int i = 0;
        while (elements[i] != null) {
            i++;
        }
        elements[i] = node;
        filled++;
    }

    private int getIndex(E element) {
        for (int i = 0; i < elements.length; i++) {
            if (elements[i] != null && elements[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public void addEdge(E firstNode, E secondNode, int weight) {
        if (weight > 0) {
            adjacencies[getIndex(firstNode)][getIndex(secondNode)] = weight;
        }
        else {
            System.out.println("Nonpositive weight");
        }
    }

    public boolean removeNode(E node) {
        int index = getIndex(node);
        if (index == -1) {
            return false;
        }
        for (int i = index; i < elements.length - 1; i++) {
            elements[i] = elements[i + 1];
        }
        for (int i = 0; i < elements.length - 1; i++) {
            for (int j = index; j < elements.length - 1; j++) {
                adjacencies[i][j] = adjacencies[i][j + 1];
            }
        }
        for (int i = 0; i < elements.length - 1; i++) {
            for (int j = index; j < elements.length - 1; j++) {
                adjacencies[j][i] = adjacencies[j + 1][i];
            }
        }
        elements[elements.length - 1] = null;
        adjacencies[index][elements.length - 1] = 0;
        adjacencies[elements.length - 1][index] = 0;
        filled--;
        return true;
    }

    public void removeEdge(E firstNode, E secondNode) {
        adjacencies[getIndex(firstNode)][getIndex(secondNode)] = 0;
    }
    
    public int size() {
        return filled;
    }

    public String[] shortestPath(E start, E end) {
        String[] path = new String[filled];
        path[0] = start.toString();
        int[] costs = new int[filled];
        for (int i = 0; i < costs.length; i++) {
            costs[i] = 1000000000;
        }
        int index = getIndex(start);
        costs[index] = 0;
        int endIndex = getIndex(end);
        int oldIndex = index;
        int k = 1;
        while (index != endIndex) {
            for (int i = 0; i < filled; i++) {
                if (adjacencies[index][i] != 0 && i != getIndex(start)
                        && i != oldIndex) {
                    costs[i] = adjacencies[index][i] + costs[index];
                }
            }
            int minCost = 1000000000;
            int newIndex = index;
            for (int i = 0; i < costs.length; i++) {
                if (costs[i] < minCost && adjacencies[index][i] != 0 &&
                        i != getIndex(start) && i != oldIndex) {
                    minCost = costs[i];
                    newIndex = i;
                }
            }
            oldIndex = index;
            index = newIndex;
            path[k] = elements[index].toString();
            k++;
        }
        return path;
    }

    public void print(E start) {
        String string = "";
        for (int[] row : adjacencies) {
            string += "\n";
            for (int e : row) {
                string += e + " ";
            }
        }
        System.out.println(string);

        Queue<E> q = new Queue<E>();
        boolean[] visited = new boolean[elements.length];
        int index = getIndex(start);
        visited[index] = true;
        q.enqueue(start);
        while(q.size() != 0) {
            E current = q.dequeue();
            index = getIndex(current);
            System.out.println(current.toString());
            for (int i = 0; i < adjacencies.length; i++) {
                if (visited[i] == false && adjacencies[index][i] > 0) {
                    q.enqueue(elements[i]);
                    visited[i] = true;
                }
            }
        }
    }

    public boolean isConnected() {
        if (filled == 0) {
            return true;
        }
        int verticies = 0;
        Queue<E> q = new Queue<E>();
        boolean[] visited = new boolean[elements.length];
        E start = elements[0];
        int index = getIndex(start);
        visited[index] = true;
        q.enqueue(start);
        while(q.size() != 0) {
            E current = q.dequeue();
            index = getIndex(current);
            verticies++;
            for (int i = 0; i < adjacencies.length; i++) {
                if (visited[i] == false && adjacencies[index][i] > 0) {
                    q.enqueue(elements[i]);
                    visited[i] = true;
                }
            }
        }
        if (verticies < filled) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean hasCycles() {
        boolean[] visited = new boolean[elements.length];
        return hasCyclesHelper(0, 0, visited);
    }

    public boolean hasCyclesHelper(int index, int lastIndex,
                                                    boolean[] visited) {
        visited[index] = true;
        for (int i = 0; i < filled; i++) {
            if (visited[i] && i != index && i != lastIndex) {
                return true;
            }
            else if (adjacencies[index][i] > 0 && i != lastIndex) {
                return hasCyclesHelper(i, index, visited);
            }
        }
        return false;
    }

    public Graph<E> minSpanningTree() {
        Graph<E> mst = new Graph<E>(filled);
        boolean[][] edgeAdded = new boolean[filled][filled];
        if (!isConnected()) {
            System.out.println("Tree is not connected");
        }
        else {
            E current = elements[1];
            mst.addNode(current);
            int index = getIndex(current);
            Heap<Integer> minHeap = new Heap<Integer>();
            boolean firstTimeThrough = true;
            while ((mst.size() < filled && minHeap.size() > 0)
                                                    || firstTimeThrough) {
                firstTimeThrough = false;
                for (int i = 0; i < filled; i++) {
                    if (adjacencies[index][i] > 0 && !edgeAdded[index][i]
                                                && !edgeAdded[i][index]) {
                        minHeap.add(adjacencies[index][i]);
                        edgeAdded[index][i] = true;
                        edgeAdded[i][index] = true;
                    }
                }
                int min = minHeap.remove();
                int j = 0;
                boolean done = false;
                while (!done) {
                    if (adjacencies[index][j] == min) {
                        index = j;
                        done = true;
                    }
                    j++;
                }
                E destination = elements[index];
                mst.addNode(destination);
                mst.addEdge(current, destination, min);
                current = destination;
            }
        }
        return mst;
    }
}
