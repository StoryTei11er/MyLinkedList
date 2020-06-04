package com.company;

import java.util.*;

public class LinkedList<T> implements List<T> {

    private Node<T> firstNode;
    private Node<T> lastNode;
    private int size = 0;

    public LinkedList() {
        lastNode = new Node<T>(null, firstNode, null);
        firstNode = new Node<T>(null, null, lastNode);
    }

    //Work
    @Override
    public int size() {
        return this.size;
    }

    //Work
    @Override
    public boolean isEmpty() {
        boolean isEmpty;

        if (size == 0) {
            isEmpty = true;
        } else {
            isEmpty = false;
        }
        return isEmpty;
    }

    //Work
    @Override
    public boolean contains(Object o) {
        boolean isContain = false;

        for (int i = 0; i <= size; i++) {
            try {
                T finderElement = get(i);
                if (finderElement.equals(o)) {
                    isContain = true;
                }
            } catch (NullPointerException ignored) {
            }
        }
        return isContain;
    }

    //Work
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int counter = 0;

            @Override
            public boolean hasNext() {
                return counter < size;
            }

            @Override
            public T next() {
                return get(counter++);
            }
        };
    }

    //Work
    @Override
    public Object[] toArray() {
        T[] array = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            try {
                T nodeElement = get(i);
                array[i] = nodeElement;
            } catch (NullPointerException ignored) {
            }
        }
        return array;
    }

    //Work
    @Override
    public <T1> T1[] toArray(T1[] a) {
        T[] copiedArray = (T[]) Arrays.copyOf(a, a.length, a.getClass());
        return (T1[]) copiedArray;
    }

    //Work
    @Override
    public boolean add(T t) {
        Node<T> next = lastNode;
        next.setElement(t);
        lastNode = new Node<>(null, next, null);
        next.setNextElement(lastNode);
        size++;

        return true;
    }

    //Work
    @Override
    public boolean remove(Object o) {
        boolean isRemoved = false;
        Node<T> node = firstNode;
        for (int i = 0; i <= size; i++) {
            try {
                node = getNextNode(node);
                if (node.getElement().equals(o) && i == 0) {
                    removeFirstNodeByValue(node);
                    isRemoved = true;
                    break;
                } else if (node.getElement().equals(o) && i != 0) {
                    removeNode(node);
                    isRemoved = true;
                }
            } catch (NullPointerException ignored) {
            }
        }
        return isRemoved;
    }

    //Work
    @Override
    public boolean containsAll(Collection<?> c) {
        boolean isContain = false;
        int counter = 0;
        T[] temp = (T[]) toArray();
        for (T t : temp) {
            for (Object o : c) {
                if (t.equals(o)) {
                    counter++;
                }
            }
        }
        if (counter == c.size()) {
            isContain = true;
        }
        return isContain;
    }

    //Work
    @Override
    public boolean addAll(Collection<? extends T> c) {
        boolean isAddingAll = false;
        for (Object o : c) {
            add((T) o);
            isAddingAll = true;
        }
        return isAddingAll;
    }

    //Work
    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean isAddingAll = false;
        int i = 0;
        for (Object o : c) {
            if (i >= index) {
                add((T) o);
                isAddingAll = true;
            }
            i++;
        }
        return isAddingAll;
    }

    //Work
    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoveAll = false;
        for (Object o : c) {
            if (contains(o)) {
                remove(o);
                isRemoveAll = true;
            }
        }
        return isRemoveAll;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        //ignored
        return false;
    }

    //Work
    @Override
    public void clear() {
        while (size != 0) {
            remove(0);
        }
    }

    //Work
    @Override
    public T get(int index) {
        Node<T> target = firstNode.getNextElement();
        for (int i = 0; i < index; i++) {
            target = getNextNode(target);
        }
        return target.getElement();
    }

    //Work
    @Override
    public T set(int index, T element) {
        Node<T> target = firstNode;
        T pastElement = null;
        for (int i = 0; i <= size; i++) {
            try {
                target = getNextNode(target);
                if (i == index) {
                    pastElement = target.element;
                    target.setElement(element);
                }
            } catch (NullPointerException ignored) {
            }
        }
        return pastElement;
    }

    //Work
    @Override
    public void add(int index, T element) {
        if (index == 0) {
            addFirstNode(element);
        } else if (index <= size - 1) {
            addMiddleNode(index, element);
        } else {
            add(element);
        }
    }


    //Work
    @Override
    public T remove(int index) {
        T target = get(index);
        Node<T> node = firstNode;
        if (index == 0) {
            removeFirstNodeByIndex(node);
        } else {
            for (int i = 0; i <= size; i++) {
                try {
                    node = getNextNode(node);
                    if (node.getElement().equals(target)) {
                        removeNode(node);
                    }
                } catch (NullPointerException ignored) {
                }
            }
        }
        return null;
    }

    //Work
    @Override
    public int indexOf(Object o) {
        int finderIndex = 0;
        for (int i = 0; i <= size; i++) {
            try {
                T finderElement = get(i);
                if (finderElement.equals(o)) {
                    finderIndex = i;
                    break;
                }
            } catch (NullPointerException ignored) {
            }
        }
        return finderIndex;
    }

    //Work
    @Override
    public int lastIndexOf(Object o) {
        int finderIndex = 0;
        for (int i = 0; i <= size; i++) {
            try {
                T finderElement = get(i);
                if (finderElement.equals(o)) {
                    finderIndex = i;
                }
            } catch (NullPointerException ignored) {
            }
        }
        return finderIndex;
    }

    //Work
    @Override
    public ListIterator<T> listIterator() {
        return new ListIterator<T>() {
            int currentIndex = 0;
            int pastReturnedElement = -1;
            int usedPrevious = 0;
            int usedNext = 0;

            //Work
            @Override
            public boolean hasNext() {
                boolean isHasNext = false;
                if (currentIndex < size) {
                    isHasNext = true;
                } else {
                    isHasNext = false;
                }
                return isHasNext;
            }

            //Work
            @Override
            public T next() {
                usedNext++;
                T returnedElement = null;

                if (currentIndex >= size) {
                    System.out.println("Array is end!");
                } else {
                    returnedElement = get(currentIndex);
                    currentIndex++;
                    pastReturnedElement++;
                }
                return returnedElement;
            }

            //Work
            @Override
            public boolean hasPrevious() {
                boolean isHasPrevious = false;
                if (currentIndex == 0) {
                    isHasPrevious = false;
                } else {
                    isHasPrevious = true;
                }
                return isHasPrevious;
            }

            //Work
            @Override
            public T previous() {
                usedPrevious++;
                T returnedElement = null;
                if (currentIndex == 0) {
                    System.out.println("You haven`t previous element");
                } else {
                    returnedElement = get(currentIndex);
                    pastReturnedElement = currentIndex;
                    currentIndex--;
                }
                return returnedElement;
            }

            //Work
            @Override
            public int nextIndex() {
                int nextIndex = currentIndex + 1;
                if (nextIndex >= size) {
                    return size;
                } else {
                    return currentIndex + 1;
                }
            }

            //Work
            @Override
            public int previousIndex() {
                int previousIndex = currentIndex - 1;
                if (previousIndex <= 0) {
                    return -1;
                } else {
                    return previousIndex;
                }
            }

            //Work
            @Override
            public void remove() {
                if (usedPrevious != 0 || usedNext != 0) {
                    LinkedList.this.remove(pastReturnedElement);
                } else {
                    System.out.println("You never used 'next' or 'previous' before!");
                }
            }

            //Work
            @Override
            public void set(T t) {
                LinkedList.this.set(pastReturnedElement, t);
            }

            //Work
            @Override
            public void add(T t) {
                LinkedList.this.add(pastReturnedElement + 1, t);
            }
        };
    }

    //Work
    @Override
    public ListIterator<T> listIterator(int index) {
        return new ListIterator<T>() {
            int currentIndex = index;
            int pastReturnedElement = index - 1;
            int usedPrevious = 0;
            int usedNext = 0;

            //Work
            @Override
            public boolean hasNext() {
                boolean isHasNext = false;
                if (currentIndex < size) {
                    isHasNext = true;
                } else {
                    isHasNext = false;
                }
                return isHasNext;
            }

            //Work
            @Override
            public T next() {
                usedNext++;
                T returnedElement = null;

                if (currentIndex >= size) {
                    System.out.println("Array is end!");
                } else {
                    returnedElement = get(currentIndex);
                    currentIndex++;
                    pastReturnedElement++;
                }
                return returnedElement;
            }

            //Work
            @Override
            public boolean hasPrevious() {
                boolean isHasPrevious = false;
                if (currentIndex == 0) {
                    isHasPrevious = false;
                } else {
                    isHasPrevious = true;
                }
                return isHasPrevious;
            }

            //Work
            @Override
            public T previous() {
                usedPrevious++;
                T returnedElement = null;
                if (currentIndex == 0) {
                    System.out.println("You haven`t previous element");
                } else {
                    returnedElement = get(currentIndex);
                    pastReturnedElement = currentIndex;
                    currentIndex--;
                }
                return returnedElement;
            }

            //Work
            @Override
            public int nextIndex() {
                int nextIndex = currentIndex + 1;
                if (nextIndex >= size) {
                    return size;
                } else {
                    return currentIndex + 1;
                }
            }

            //Work
            @Override
            public int previousIndex() {
                int previousIndex = currentIndex - 1;
                if (previousIndex <= 0) {
                    return -1;
                } else {
                    return previousIndex;
                }
            }

            //Work
            @Override
            public void remove() {
                if (usedPrevious != 0 || usedNext != 0) {
                    LinkedList.this.remove(pastReturnedElement);
                } else {
                    System.out.println("You never used 'next' or 'previous' before!");
                }
            }

            //Work
            @Override
            public void set(T t) {
                LinkedList.this.set(pastReturnedElement, t);
            }

            //Work
            @Override
            public void add(T t) {
                LinkedList.this.add(pastReturnedElement + 1, t);
            }
        };
    }

    //Work
    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        T[] temp = (T[]) toArray();
        T[] sublist = Arrays.copyOfRange(temp, fromIndex, toIndex);
        return Arrays.asList(sublist);
    }

    //Work
    //Берёт элемент переданной ноды и переходит на следующую ноду.
    private Node<T> getNextNode(Node<T> currentTarget) {
        return currentTarget.getNextElement();
    }

    //Work
    //Удаляет переданную ноду за исключением первой
    private void removeNode(Node<T> currentNode) {
        Node<T> previous = currentNode.getPreviousElement();
        Node<T> next = currentNode.getNextElement();

        next.setPreviousElement(previous);
        previous.setNextElement(next);

        currentNode.setNextElement(null);
        currentNode.setPreviousElement(null);
        currentNode.setElement(null);

        size--;
    }

    //Work
    private void removeFirstNodeByValue(Node<T> currentNode) {
        Node<T> next = currentNode.getNextElement();

        firstNode = new Node<>(null, null, next);
        currentNode.setPreviousElement(firstNode);

        next.setPreviousElement(firstNode);
        firstNode.setNextElement(next);

        currentNode.setNextElement(null);
        currentNode.setPreviousElement(null);
        currentNode.setElement(null);

        size--;
    }

    //Work
    private void removeFirstNodeByIndex(Node<T> currentNode) {
        Node<T> next = currentNode.getNextElement().getNextElement();

        firstNode = new Node<>(null, null, next);
        currentNode.setPreviousElement(firstNode);

        next.setPreviousElement(firstNode);
        firstNode.setNextElement(next);

        currentNode.setNextElement(null);
        currentNode.setPreviousElement(null);
        currentNode.setElement(null);

        size--;
    }

    //Work
    private void addFirstNode(T t) {
        Node<T> first = firstNode;
        first.setElement(t);
        firstNode = new Node<>(null, null, first);
        first.setPreviousElement(firstNode);
        size++;
    }

    //Work
    private void addMiddleNode(int index, T t) {
        T target = get(index);
        Node<T> pastNode = firstNode;
        for (int i = 0; i <= size; i++) {
            try {
                pastNode = getNextNode(pastNode);
                if (pastNode.getElement().equals(target)) {

                    Node<T> previous = pastNode.getPreviousElement(); // 67
                    Node<T> middle = new Node<>(t, previous, pastNode);

                    previous.setNextElement(middle);
                    pastNode.setPreviousElement(middle);
                    size++;
                }
            } catch (NullPointerException ignored) {
            }
        }
    }


    //Work
    private class Node<T> {

        private T element;
        private Node<T> nextElement;
        private Node<T> previousElement;

        Node(T element, Node<T> previousElement, Node<T> nextElement) {
            this.element = element;
            this.nextElement = nextElement;
            this.previousElement = previousElement;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public Node<T> getNextElement() {
            return nextElement;
        }

        public void setNextElement(Node<T> nextElement) {
            this.nextElement = nextElement;
        }

        public Node<T> getPreviousElement() {
            return previousElement;
        }

        public void setPreviousElement(Node<T> previousElement) {
            this.previousElement = previousElement;
        }
    }
}


