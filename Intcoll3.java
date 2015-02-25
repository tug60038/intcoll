//*********************************************************************
// FILE NAME    : Intcoll3.java
// DESCRIPTION  : This file contains the class Intcoll3.
//*********************************************************************

public class Intcoll3 {
    private boolean[] c;
    private int howmany;

    // Creates an empty collection of integers with a capactity of 500
    public Intcoll3() {
        c = new boolean[500+1];
        howmany = 0;
    }

    // Creates an empty collection of integers with a capactity of i
    //
    // @param   i   capacity of collection, i >= 0
    public Intcoll3(int i) {
        c = new boolean[i+1];
        howmany = 0;
    }

    // Overrides collection with a copy of the contents stored in the collection
    // of obj when both collections are not equal. Both collections will have 
    // the same capacity.
    //
    // @param   obj  reference to a collection
    public void copy(Intcoll3 obj) {
        if (this != obj) {
            c = new boolean[obj.c.length];
            int j = 1; int k = 0;
            while (k < obj.get_howmany()) {
                if (obj.c[j]) {
                    c[j] = obj.c[j]; 
                    k++;
                }
                j++;
            }
            howmany = k;
        }
    }

    // Returns true if integer of choice is in collection, otherwise returns false
    // 
    // @param   i    integer to be determined whether or not it is in collection
    // @return       indicates if i is in collection
    public boolean belongs(int i) {
        if ((i > 0) && (i < c.length))
            return c[i];
        else
            return false;
    }

    // Adds an integer to collection. If capacity is exceeded, the capacity will
    // double in size.
    //
    // @param   i    integer to be added, i > 0
    public void insert(int i) {
        if (i > 0) {
            if (i < c.length){
                if (!c[i]) {
                    c[i] = true;
                    howmany++;
                }
            } else {
                boolean[] d = c;
                c = new boolean[2*i+1];
                c[i] = true;
                int j = 0; i = 1;
                while (j < howmany) {
                    if (d[i]) {
                        c[i] = d[i];
                        j++;
                    }
                    i++;
                }
                howmany++;
            }
        }
    }

    // Removes number if it is in collection
    //
    // @param   i    integer to be removed, i > 0
    public void omit(int i) {
        if (i > 0 && i < c.length) {
            if (c[i]) {
                c[i] = false;
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
        int j = 0, k = 1;
        System.out.println();
        while (j < howmany) {
            if (c[k]) {
                System.out.println(k);
                j++;
            }
            k++;
        }
    }
    
    // returns true if collection contains every integer from the collection
    // of obj, and conversely.
    //
    // @param   obj  reference to a collection
    // @return       indicates if collections are equal
    public boolean equals(Intcoll3 obj) {
        int j = 0; int k = 1;
        boolean result;
        if (howmany == obj.get_howmany()) {
            result = true;
            while ((j < howmany) && result) {
                if (c[k]) {
                    result = obj.belongs(k);
                    j++;
                }
                k++;
            }
            j = 0; k = 1;
            while ((j < obj.get_howmany()) && result) {
                if (obj.c[k]) {
                    result = belongs(k);
                    j++;
                }
                k++;
            }
        }
        else
            result = false;
        return result;
    }
}