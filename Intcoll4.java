//*********************************************************************
// FILE NAME    : Intcoll4.java
// DESCRIPTION  : This file contains the class Intcoll4.
//*********************************************************************

public class Intcoll4 {
    private ListNode c;
    private int howmany;

    // Creates an empty collection of integers with a capactity of 500
    public Intcoll4() {
        c = null;
        howmany = 0;
    }

    // Creates an empty collection of integers with a capactity of i
    //
    // @param   i   capacity of collection, i >= 0
    public Intcoll4(int i) {
        c = null;
        howmany = 0;
    }

    // Overrides collection with a copy of the contents stored in the collection
    // of obj when both collections are not equal. Both collections will have 
    // the same capacity.
    //
    // @param   obj  reference to a collection
    public void copy(Intcoll4 obj) {
        if (this != obj) {
            if (obj.get_howmany() != 0) {
                c = new ListNode();
                ListNode p1 = obj.c, p2 = c;
                p2.info = p1.info;
                p1 = p1.link;
                while (p1 != null) {
                    p2.link = new ListNode();
                    p2 = p2.link;
                    p2.info = p1.info;
                    p1 = p1.link;
                }
            } else {
                c = null;
            }
            howmany = obj.get_howmany();
        }
    }

    // Returns true if integer of choice is in collection, otherwise returns false
    // 
    // @param   i    integer to be determined whether or not it is in collection
    // @return       indicates if i is in collection
    public boolean belongs(int i) {
        boolean result = false;
        if (i > 0) {
            ListNode p = c;
            while ((p != null) && (p.info != i))
                p = p.link;
            if (p != null)
                result = true;
        }
        return result;
    }

    // Adds an integer to collection. If capacity is exceeded, the capacity will
    // double in size.
    //
    // @param   i    integer to be added, i > 0
    public void insert(int i) {
        if (i > 0) {
            ListNode p = c;
            while ((p != null) && (p.info != i))
                p = p.link;
            if (p == null) {
                p = new ListNode();
                p.info = i;
                p.link = c;
                c = p;
                howmany++;
            }
        }
    }

    // Removes number if it is in collection
    //
    // @param   i    integer to be removed, i > 0
    public void omit(int i) {
        if (i > 0) {
            ListNode p = c, pred = null;
            while ((p != null) && (p.info != i)) {
                pred = p;
                p = p.link;
            }
            if (p != null) {
                if (pred == null)
                    c = p.link;
                else
                    pred.link = p.link;
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
        ListNode p = c;
        System.out.println();
        while (p != null) {
            System.out.println(p.info);
            p = p.link;
        }
    }
    
    // returns true if collection contains every integer from the collection
    // of obj, and conversely.
    //
    // @param   obj  reference to a collection
    // @return       indicates if collections are equal
    public boolean equals(Intcoll4 obj) {
        boolean result = false;
        if (howmany == obj.get_howmany()) {
            result = true;
            ListNode p1 = obj.c;
            while ((p1 != null) && (result)) {
                result = belongs(p1.info);
                p1 = p1.link;
            }
        }
        return result;
    }
    
    // This is an inner class for Intcoll4
    private class ListNode {
        private int info;
        private ListNode link;
        
        public ListNode() {
            info = 0;
            link = null;
        }
    }
}