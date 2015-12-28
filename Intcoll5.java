/* CIS 2168
 * Prof. James Korsh
 *
 * Joseph Norkin 
 * Intcoll5.java
 * 
 * This file contains a class that impliments a collection of positive integers.
 */

import java.util.*;

public class Intcoll5 {
    private LinkedList<Integer> c;
    private int howmany;

    // Creates an empty collection of integers with a capactity of 500
    public Intcoll5() {
        c = new LinkedList<>();
        howmany = 0;
    }

    // Creates an empty collection of integers with a capactity of i
    //
    // @param   i   capacity of collection, i >= 0
    public Intcoll5(int i) {
        c = new LinkedList<>();
        howmany = 0;
    }

    // Overrides collection with a copy of the contents stored in the collection
    // of obj when both collections are not equal. Both collections will have 
    // the same capacity.
    //
    // @param   obj  reference to a collection
    public void copy(Intcoll5 obj) {
        if (this != obj) {
            c = new LinkedList<>();
            if (obj.get_howmany() > 0) {
                ListIterator<Integer> I = obj.c.listIterator();
                while (I.hasNext()) {
                    c.add(I.next());
                }
            }
            howmany = obj.get_howmany();
        }
    }

    // Returns true if integer of choice is in collection, otherwise returns false
    // 
    // @param   i    integer to be determined whether or not it is in collection
    // @return       indicates if i is in collection
    public boolean belongs(int i) {
        return (i > 0) && c.contains(new Integer(i));
    }

    // Adds an integer to collection. If capacity is exceeded, the capacity will
    // double in size.
    //
    // @param   i    integer to be added, i > 0
    public void insert(int i) {
        if ((i > 0) && !c.contains(new Integer(i))) {
            c.add(new Integer(i));
            howmany++;
        }
    }

    // Removes number if it is in collection
    //
    // @param   i    integer to be removed, i > 0
    public void omit(int i) {
        if ((i > 0) && c.remove(new Integer(i)))
        	howmany--;
    }
    
    // returns the number of integers stored in collection
    public int get_howmany() {
        return howmany;
    }
    
    // prints the members of collection
    public void print() {
        System.out.println();
        if (howmany > 0) {
            ListIterator<Integer> I = c.listIterator();
            while (I.hasNext()) {
                System.out.println(I.next().intValue());
            }
        }
    }
    
    // returns true if collection contains every integer from the collection
    // of obj, and conversely.
    //
    // @param   obj  reference to a collection
    // @return       indicates if collections are equal
    public boolean equals(Intcoll5 obj) {
        boolean value = false;
        if (howmany == obj.get_howmany()) {
            value = true;
            if (howmany > 0) {
                ListIterator<Integer> I = c.listIterator();
                while (I.hasNext() && value) {
                    value = obj.c.contains(I.next());
                }
            }
        }
        return value;
    }
}