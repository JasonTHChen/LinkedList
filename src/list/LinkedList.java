package list;

import java.util.Arrays;
import java.util.Collection;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Doubly-linked list implementation of Collection interface.
 * This list can be created with any type, 
 * and same type of value can be added to the list as a collection
 * The list can also be iterated forward and backward.
 * 
 * @author Tzu Hsiang Chen (Jason)
 * @version 1.1
 * @since March 23, 2017
 *
 */
public class LinkedList<E> implements Collection<E> {
    private Node head;
    private Node tail;
    
    /**
     * constructs default LinkedList which does not have any node.
     */
    public LinkedList() {
        head = null;
        tail = null;
    }

    /**
     * constructs LinkedList by adding one element into the node.
     * 
     * @param element type of element
     */
    public LinkedList(E element) {
        add(element);
    }

    /**
     * get head of the list.
     * 
     * @return the head
     */
    public Node getHead() {
        return head;
    }

    /**
     * gets tail of the list.
     * 
     * @return the tail
     */
    public Node getTail() {
        return tail;
    }
    
    public E getHeadElement() {
        return head.getElement();
    }
    
    public E getTailElement() {
        return tail.getElement();
    }

    /**
     * appends the specified element to the end of the list.
     * 
     * @param element - type of element
     * @return true if the element adds to the list
     */
    @Override
    public boolean add(E element) {
        Node newNode = new Node(element);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
        }
        tail = newNode;
        return true;
    }

    /**
     * appends all of the elements in the specified collection to the end of the list.
     * 
     * @param collection type of collection
     * @return true if everything add successfully; otherwise, false.
     */
    @Override
    public boolean addAll(Collection<? extends E> collection) {
        for (E element : collection) {
            if (!this.add(element)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Removes all of the elements from the list.
     * The list will be empty after this call returns.
     */
    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    /**
     * returns true if the list contains at least one of the specified element.
     * 
     * @param object - search object
     * @return true or false
     */
    @Override
    public boolean contains(Object object) {
        Node temp = head;
        while (temp != null) {
            if (temp.element.equals(object)) {
                return true;
            }
            
            temp = temp.getNext();
        }
        return false;
    }

    /**
     * returns true if the list contains all of the specified elements in the collection.
     * 
     * @param collection - collection of objects
     * @return true or false
     */
    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object object : collection) {
            if (!this.contains(object)) {
                return false;
            }
        }
        return true;
    }

    /**
     * returns true if this collection contains no element.
     * 
     * @return true or false
     */
    @Override
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    /**
     * returns an iterator over the elements contained in the list.
     * 
     * @return ListIterator point of the list
     */
    @Override
    public ListIterator<E> iterator() {
        return new ListIterator<E>() {     
            private Node current = null;
            private int previousIndex = -1;
            private int nextIndex = 0;
            
            /*
             * returns true if this list iterator has more elements 
             * when traversing the list in the forward direction.
             * @see java.util.ListIterator#hasNext()
             */
            @Override
            public boolean hasNext() {
                return current != tail;
            }

            /*
             * returns the next element in the list and advances the cursor position.
             * @see java.util.ListIterator#next()
             */
            @Override
            public E next() {
                if (current == null) {
                    current = head;
                    nextIndex++;
                    previousIndex++;
                    return current.getElement();
                }
                
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                
                current = current.getNext();
                nextIndex++;
                previousIndex++;
                return current.getElement();
            }

            /*
             * this method is not supported.
             * @see java.util.ListIterator#add(java.lang.Object)
             */
            @Override
            public void add(E element) {
                throw new UnsupportedOperationException();
            }

            /*
             * returns true if this list iterator has more elements 
             * when traversing the list in the reverse direction.
             * @see java.util.ListIterator#hasPrevious()
             */
            @Override
            public boolean hasPrevious() {
                return current != head;
            }

            /*
             * returns the next index of the element.
             * @see java.util.ListIterator#nextIndex()
             */
            @Override
            public int nextIndex() {
                return nextIndex;
            }

            /*
             * returns the previous index of the element.
             * @see java.util.ListIterator#previous()
             */
            @Override
            public E previous() {
                if (!hasPrevious()) {
                    throw new NoSuchElementException();
                }
                
                if (nextIndex == size()) {
                    nextIndex--;
                    previousIndex--;
                    return tail.getElement();
                }
                
                current = current.getPrevious();
                nextIndex--;
                previousIndex--;
                return current.getElement();
            }
            
            /*
             * returns the previous index of the element.
             * @see java.util.ListIterator#previousIndex()
             */
            @Override
            public int previousIndex() {
                return previousIndex;
            }

            /*
             * this method is not supported.
             * @see java.util.ListIterator#remove()
             */
            @Override
            public void remove() {
                throw new UnsupportedOperationException();                
            }

            /*
             * this method is not supported.
             * @see java.util.ListIterator#set(java.lang.Object)
             */
            @Override
            public void set(E element) {
                throw new UnsupportedOperationException();                
            }
        };
    }

    /**
     * removes a single instance of the specified element from the list.
     *
     * @param object that needs to be removed
     * @return true or false
     */
    @Override
    public boolean remove(Object object) {
        Node temp = head;
        while (temp != null) {
            if (temp.getElement().equals(object)) {
                if (temp.equals(head)) {
                    head = temp.getNext();
                    head.setPrevious(null);
                } else if (temp.equals(tail)) {
                    tail = temp.getPrevious();
                    tail.setNext(null);
                } else {
                    Node nextNode = temp.getNext();
                    Node prevNode = temp.getPrevious();
                    prevNode.setNext(nextNode);
                    nextNode.setPrevious(prevNode);
                }
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    /**
     * removes all of the list's elements that are contained in the specified collection.
     * @param collection - collection of objects
     * 
     * @return true or false
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        Node temp = head;
        boolean removed = false;
        while (temp != null) {
            if (collection.contains(temp.getElement())) {
                removed = this.remove(temp.getElement());
            }
            temp = temp.getNext();
        }
        return removed;
    }

    /**
     * retains only the elements in the list that are contained in the specified collection.
     * 
     * @param collection - collection of objects
     * @return true or false
     */
    @Override
    public boolean retainAll(Collection<?> collection) {
        boolean removed = false;
        Node temp = head;
        while (temp != null) {
            if (!collection.contains(temp.getElement())) {
                removed = this.remove(temp.getElement());
            }
            
            temp = temp.getNext();
        }
        
        return removed;
    }
    
    /**
     * returns the number of elements in this collection.
     * 
     * @return number of elements
     */
    @Override
    public int size() {
        int counter = 0;
        Node temp = head;
        while (temp != null) {
            counter++;
            temp = temp.getNext();
        }

        return counter;
    }

    /**
     * returns an array containing all of the elements in this collection.
     * 
     * @return object array
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size()];
        Node temp = head;
        int index = 0;
        while (temp != null) {
            array[index] = temp.getElement();
            temp = temp.getNext();
            index++;
        }
        return array;
    }

    /**
     * returns an array containing all of the elements in this collection; 
     * the runtime type of the returned array is that of the specified array.
     * 
     * @return generic type array
     */
    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] array) {
        Node temp = head;
        if (array.length < size()) {
            array = Arrays.copyOf(array, size());
        }
        
        int index = 0;
        while (temp != null) {
            array[index] = (T) temp.getElement();
            temp = temp.getNext();
            index++;
        }
        
        for (int i = index; i < array.length; i++) {
            array[i] = null;
        }
        
        return array;
    }

    /*
     * Node class has element, nextNode, and previousNode
     * It contains data, and two references. 
     * Those references tell what next Node and previous Node are.
     */
    private class Node {
        private E element;
        private Node next;
        private Node previous;
        
        /**
         * construct a node which holds an element.
         * @param element type
         */
        public Node(E element) {
            this.setElement(element);
            next = null;
            previous = null;
        }

        /**
         * gets element from the node.
         * @return the element
         */
        public E getElement() {
            return element;
        }

        /**
         * sets element to the node.
         * @param element the element to set
         */
        public void setElement(E element) {
            this.element = element;
        }

        /**
         * gets the next connected node.
         * @return the next
         */
        public Node getNext() {
            return next;
        }

        /**
         * sets the next node.
         * @param next the next to set
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /**
         * gets the previous connected node.
         * @return the previous
         */
        public Node getPrevious() {
            return previous;
        }

        /**
         * sets the previous node.
         * @param previous the previous to set
         */
        public void setPrevious(Node previous) {
            this.previous = previous;
        }
    }
}
