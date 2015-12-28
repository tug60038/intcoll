/* CIS 2168
 * Prof. James Korsh
 *
 * Joseph Norkin 
 * Intcoll2.java
 * 
 * This file contains a class that implements a collection of positive integers.
 */

public class Intcoll2 {
    private int[] c;
    private int howmany;

    // Creates an empty collection of integers with a capactity of 500
    public Intcoll2() {
        c = new int[500];
        howmany = 0;
    }

    // Creates an empty collection of integers with a capactity of i
    //
    // @param   i   capacity of collection, i >= 0
    public Intcoll2(int i) {
        c = new int[i];
        howmany = 0;
    }

    // Overrides collection with a copy of the contents stored in the collection
    // of obj when both collections are not equal. Both collections will have 
    // the same capacity.
    //
    // @param   obj  reference to a collection
    public void copy(Intcoll2 obj) {
        if (this != obj) {
            c = new int[obj.c.length];
            int j = 0;
            while (j < obj.get_howmany()) {
                c[j] = obj.c[j];
                j++;
            }
            howmany = obj.get_howmany();
        }
    }

    // Returns true if integer of choice is in collection, otherwise returns false
    // 
    // @param   i    integer to be determined whether or not it is in collection
    // @return       indicates if i is in collection
    public boolean belongs(int i) {
        int j = 0;
        while ((j < howmany) && (c[j] != i)) {
            j++;
        }
        return ((i > 0) && (c[j] == i));
    }

    // Adds an integer to collection. If capacity is exceeded, the capacity will
    // double in size.
    //
    // @param   i    integer to be added, i > 0
    public void insert(int i) {
        if (i > 0) {
            int j = 0;
            while ((j < howmany) && (c[j] != i)) {
                j++;
            }
            if (j == howmany) {
                if (j == c.length) {
                    int[] d = c;
                    c = new int[2*j];
                    int k = 0;
                    while (k < howmany) {
                        c[k] = d[k];
                        k++;
                    }
                }
                c[j] = i;
                howmany++;
            }
        }
    }

    // Removes number if it is in collection
    //
    // @param   i    integer to be removed, i > 0
    public void omit(int i) {
        if (i > 0) {
            int j = 0;
            while ((j < howmany) && (c[j] != i)) {
                j++;
            }
            if (j < howmany) {
                if (j < howmany - 1) {
                	c[j] = c[howmany - 1];
            	}
                c[howmany - 1] = 0;
                howmany--;
            }
        }
    }
    
    // returns the number of integers stored in collection
    public int get_howmany() {
        return howmany;
    }
    
    // prints the members of collection
    public void print() {
        int j = 0;
        System.out.println();
        while (j < howmany) {
            System.out.println(c[j]);
            j++;
        }
    }
    
    // returns true if collection contains every integer from the collection
    // of obj, and conversely.
    //
    // @param   obj  reference to a collection
    // @return       indicates if collections are equal
    public boolean equals(Intcoll2 obj) {
        int j = 0;
        boolean result;
        if (howmany == obj.get_howmany()) {
            result = true;
            while ((j < howmany) && result) {
                result = obj.belongs(c[j]);
                j++;
            }
            j = 0;
            while ((j < obj.get_howmany()) && result) {
                result = belongs(obj.c[j]);
                j++;
            }
        }
        else 
             result = false;
        return result;
    }
}