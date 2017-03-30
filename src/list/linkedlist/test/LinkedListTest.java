package list.linkedlist.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Random;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import list.linkedlist.LinkedList;


/**
 * Tests the class LinkedList.
 * 
 * @author Tzu Hsiang Chen (Jason)
 * @version 1.0
 * @since March 23, 2017
 *
 */
public class LinkedListTest {
        
    private LinkedList<String> myLinkedList;
    final String[] values = { "hello", "test", "kale", "nomad", "apple", "fish" };
    final String[] test1 = { "test", "nomad", "fish" };
    final String[] test2 = { "apple", "fish", "car", "garage", "apple" };

    /**
     * setup new LinkedList with string type.
     * 
     * @throws java.lang.Exception - when the object cannot be created
     * 
     */
    @Before
    public void setUp() throws Exception {
        myLinkedList = new LinkedList<String>();
    }

    /**
     * destroys the LinkedList.
     * 
     * @throws java.lang.Exception - when the object cannot be destroy
     */
    @After
    public void tearDown() throws Exception {
        myLinkedList = null;
    }

    /**
     * Test method for passing one parameter.
     * {@link ca.bcit.comp2526.a3.LinkedList#LinkedList(java.lang.Object)}.
     */
    @Test
    public void testLinkedListE() {
        LinkedList<String> l3 = new LinkedList<String>("mouse");
        assertEquals(l3.size(), 1);
        assertTrue(l3.contains("mouse"));
    }

    /**
     * Test method for adding one node to empty list.
     * {@link ca.bcit.comp2526.a3.LinkedList#add(java.lang.Object)}
     */
    @Test
    public void testAddOneNodeToEmptyList() {
        assertTrue(myLinkedList.add("Pashan"));
        assertEquals(myLinkedList.size(), 1);
        assertNotNull(myLinkedList.getHead());
        assertNotNull(myLinkedList.getTail());
        assertEquals(myLinkedList.getHead(), myLinkedList.getTail());
    }
    
    /**
     * Test method for adding elements to list.
     * {@link ca.bcit.comp2526.a3.LinkedList#add(java.lang.Object)}
     */
    @Test
    public void testAddElementsToList() {
        for (String value : values) {
            assertTrue(myLinkedList.add(value));
        }
        assertEquals(myLinkedList.size(), values.length);
        assertNotNull(myLinkedList.getHead());
        assertNotNull(myLinkedList.getTail());
    }
    
    /**
     * Test method for adding multiple elements to the list.
     * {@link ca.bcit.comp2526.a3.LinkedList#addAll(java.lang.Object)}
     */
    @Test
    public void testAddAll() {
        for (String value : values) {
            assertTrue(myLinkedList.add(value));
        }
        List<String> items = Arrays.asList(test1);
        myLinkedList.addAll(items);
        assertEquals(myLinkedList.size(), values.length + test1.length);
        assertNotNull(myLinkedList.getHead());
        assertNotNull(myLinkedList.getTail());
    }

    /**
     * Test method for clearing the list.
     * {@link ca.bcit.comp2526.a3.LinkedList#clear()}.
     */
    @Test
    public void testClear() {
        myLinkedList.clear();
        assertTrue(myLinkedList.isEmpty());
        assertEquals(myLinkedList.size(), 0);
    }
    
    /**
     * Test method for the middle element.
     * {@link ca.bcit.comp2526.a3.LinkedList#remove()}.
     */
    @Test
    public void testRemoveMiddle() {
        for (String value : values) {
            myLinkedList.add(value);
        }
        
        myLinkedList.remove("kale");
        assertFalse(myLinkedList.contains("kale"));
        assertEquals(myLinkedList.size(), 5);
    }
    
    /**
     * Test method for the head element.
     * {@link ca.bcit.comp2526.a3.LinkedList#remove()}.
     */
    @Test
    public void testRemoveHead() {
        for (String value : values) {
            myLinkedList.add(value);
        }
        
        myLinkedList.remove("hello");
        assertFalse(myLinkedList.contains("hello"));
        assertNotNull(myLinkedList.getHead());
        assertTrue(myLinkedList.getHeadElement().equals("test"));
        assertEquals(myLinkedList.size(), 5);
    }
    
    /**
     * Test method for the tail element.
     * {@link ca.bcit.comp2526.a3.LinkedList#remove()}.
     */
    @Test
    public void testRemoveTail() {
        for (String value : values) {
            myLinkedList.add(value);
        }
        
        myLinkedList.remove("fish");
        assertFalse(myLinkedList.contains("fish"));
        assertNotNull(myLinkedList.getTail());
        assertEquals(myLinkedList.size(), 5);
    }
    
    /**
     * Test method for remove all.
     * {@link ca.bcit.comp2526.a3.LinkedList#removeAll()}.
     */
    @Test
    public void testRemoveAll() {
        for (String value : test2) {
            myLinkedList.add(value);
        }
        List<String> removeItems = Arrays.asList("apple", "car");
        myLinkedList.removeAll(removeItems);
        assertFalse(myLinkedList.contains("apple"));
        assertTrue(myLinkedList.contains("fish"));
        assertNotNull(myLinkedList.getTail());
        assertNotNull(myLinkedList.getHead());
        assertEquals(myLinkedList.size(), 2);
    }

    /**
     * Test method for retain all.
     * {@link ca.bcit.comp2526.a3.LinkedList#retainAll()}.
     */
    @Test
    public void testRetainAll() {
        for (String value : test2) {
            myLinkedList.add(value);
        }
        List<String> retainItems = Arrays.asList("apple", "car");
        myLinkedList.retainAll(retainItems);
        assertTrue(myLinkedList.contains("apple"));
        assertFalse(myLinkedList.contains("fish"));
        assertNotNull(myLinkedList.getTail());
        assertNotNull(myLinkedList.getHead());
        assertEquals(myLinkedList.size(), 3);
        
        int appear = 0;
        for (String item : myLinkedList) {
            if (item.equals("apple")) {
                appear++;
            }
        }
        assertEquals(appear, 2);
    }

    
    /**
     * Test method for finding same value in the list.
     * {@link ca.bcit.comp2526.a3.LinkedList#contains(java.lang.Object)}.
     */
    @Test
    public void testContains() {
        for (String value : values) {
            myLinkedList.add(value);
        }
        
        assertTrue(myLinkedList.contains("kale"));
        assertFalse(myLinkedList.contains("apples"));
    }

    /**
     * Test method for checking if the list is empty.
     * {@link ca.bcit.comp2526.a3.LinkedList#isEmpty()}.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(myLinkedList.isEmpty());
        myLinkedList.add("apple");
        assertFalse(myLinkedList.isEmpty());
    }

    /**
     * Test method for converting the list to Object[].
     * {@link ca.bcit.comp2526.a3.LinkedList#toArray()}.
     */
    @Test
    public void testToArray() {
        for (String value : values) {
            myLinkedList.add(value);
        }
        
        Object[] array = myLinkedList.toArray();
        for (int i = 0; i < values.length && i < array.length; i++) {
            assertEquals(values[i], array[i]);
            assertTrue(values[i].equals(array[i]));
        }
    }

    /**
     * Test method for converting the list to generic type.
     * {@link ca.bcit.comp2526.a3.LinkedList#toArray(T[])}.
     */
    @Test
    public void testToArrayTArray() {
        for (String value : values) {
            myLinkedList.add(value);
        }
        
        String[] stringArray = myLinkedList.toArray(new String[0]);
        int index = 0;
        
        assertEquals(myLinkedList.size(), stringArray.length);
        for (String data : myLinkedList) {
            assertEquals(stringArray[index], data);
            index++;
        }
    }
    
    /**
     * Test method for iterating an empty list.
     * {@link ca.bcit.comp2526.a3.LinkedList#iterator()}.
     */
    @Test(expected = NullPointerException.class)
    public void testIteratorEmptyList() {   
        ListIterator<String> iterator = myLinkedList.iterator();
        assertFalse(iterator.hasNext());
        iterator.next();
    }
    
    /**
     * Test method for hasNext in size-one list.
     * {@link ca.bcit.comp2526.a3.LinkedList#iterator()}.
     */
    @Test
    public void testIteratorHasNextListSizeOne() {
        myLinkedList.add("Paul");
        ListIterator<String> iterator = myLinkedList.iterator();
        assertTrue(iterator.hasNext());
    }
    
    /**
     * Test method for next in size-one list.
     * {@link ca.bcit.comp2526.a3.LinkedList#iterator()}.
     */
    @Test(expected = NoSuchElementException.class)
    public void testIteratorNextListSizeOne() {
        myLinkedList.add("Paul");
        ListIterator<String> iterator = myLinkedList.iterator();
        assertTrue(iterator.hasNext());
        String value = iterator.next();
        assertSame(value, "Paul");
        iterator.next(); // should throw a no such element exception here
    }
    
    /**
     * Test method for hasNext in the large list.
     * {@link ca.bcit.comp2526.a3.LinkedList#iterator()}.
     */
    @Test(expected = NoSuchElementException.class)
    public void testIteratorHasNextLargList() {
        Random rand = new Random();
        int num = rand.nextInt(5) + 10;
        for (int i = 0; i < num; i++) {
            myLinkedList.add("" + i);
        }
        
        ListIterator<String> iterator = myLinkedList.iterator();
        for (int i = 0; i < num; i++) {
            assertTrue(iterator.hasNext());
            assertNotNull(iterator.next());
        }
        
        assertFalse(iterator.hasNext());
        iterator.next(); // should throw a NoSuchElementException here
    }
    
    /**
     * Test method for hasPrevious in the large list.
     * {@link ca.bcit.comp2526.a3.LinkedList#iterator()}
     */
    @Test(expected = NoSuchElementException.class)
    public void testIteratorHasPreviousLargList() {
        Random rand = new Random();
        int num = rand.nextInt(5) + 10;
        for (int i = 0; i < num; i++) {
            myLinkedList.add("" + i);
        }
        
        ListIterator<String> it = (ListIterator<String>) myLinkedList.iterator();
        while (it.hasNext()) {
            it.next();
        }
        
        for (int i = 0; i < num; i++) {
            assertTrue(it.hasPrevious());
            assertNotNull(it.previous());
        }
        
        assertFalse(it.hasPrevious());   
        it.previous(); // should throw a NoSuchElementException here
    }
    
    /**
     * Test method for iterating forward and backward.
     * {@link ca.bcit.comp2526.a3.LinkedList#iterator()}.
     */
    @Test
    public void testIteratorNextAndPrevious() {
        for (String value : values) {
            myLinkedList.add(value);
        }
        int size = 0;
        int index = 0;
        ListIterator<String> it = (ListIterator<String>) myLinkedList.iterator();
        while (it.hasNext()) {
            String value = it.next();
            assertSame(value, values[index++]);
            assertEquals(index, it.nextIndex());
            size++;
        }
        
        while (it.hasPrevious()) {
            assertEquals(--index, it.previousIndex());
            String value = it.previous();
            assertSame(value, values[index]);            
        }
        
        assertEquals(myLinkedList.size(), size);
    }
}
