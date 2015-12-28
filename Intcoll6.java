/* CIS 2168
 * Prof. James Korsh
 *
 * Joseph Norkin 
 * Intcoll6.java
 * 
 * This file contains a class that implements a collection of positive integers.
 */

import java.util.*;

public class Intcoll6 {
    private btNode c;
    private int howmany;

    // Creates an empty collection of integers with a capactity of 500
    public Intcoll6() {
        c = null;
        howmany = 0;
    }

    // Creates an empty collection of integers with a capactity of i
    //
    // @param   i   capacity of collection, i >= 0
    public Intcoll6(int i) {
        c = null;
        howmany = 0;
    }

    // Overrides collection with a copy of the contents stored in the collection
    // of obj when both collections are not equal. Both collections will have 
    // the same capacity.
    //
    // @param   obj  reference to a collection
    public void copy(Intcoll6 obj) {
        if (this != obj) {
            howmany = obj.howmany;
            c = copytree(obj.c);
        }
    }

    // An extension of the copy method.
    private static btNode copytree(btNode t) {
        btNode root = null;
        if (t != null) {
            root = new btNode();
            root.info = t.info;
            root.left = copytree(t.left);
            root.right = copytree(t.right);
        }
        return root;
    }

    // Returns true if integer of choice is in collection, otherwise returns false
    // 
    // @param   i    integer to be determined whether or not it is in collection
    // @return       indicates if i is in collection
    public boolean belongs(int i) {
        btNode p = c;
        while ((p != null) && (p.info != i)) {
            if (p.info > i) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return (p != null);
    }    

    // Adds an integer to collection. If capacity is exceeded, the capacity will
    // double in size.
    //
    // @param   i    integer to be added, i > 0
    public void insert(int i) {
        btNode pred = null, p = c;

        while ((p != null) && (p.info != i)) {
            pred = p;
            if (p.info > i) p = p.left;
            else p = p.right;
        }
        if (p == null) {
            howmany++;
            p = new btNode(i, null, null);
            if (pred != null) {
                if (pred.info > i) pred.left = p;
                else pred.right = p;
            } else c = p;
        }
    }

    // Removes number if it is in collection
    //
    // @param   i    integer to be removed, i > 0
    public void omit(int i) {
        if (i > 0) {
            btNode p = c, pred = null;
            while ((p != null) && (p.info != i)) {
                pred = p;
                if (p.info > i) p = p.left;
                else p = p.right;
            }
            if (p != null) {
                if (pred == null) {
                    if (p.left != null) {
                        btNode last = p;
                        pred = p.left;
                        p = p.left.right;
                        while (p != null) {
                            last = pred;
                            pred = p;
                            p = p.right;
                        } 
                        c.info = pred.info;
                        if (last == c) c.left = pred.left;
                        else last.right = pred.left;
                    } else {
                        c = c.right;
                    }
                } else {
                    if (p.left != null) {
                        if(p.right != null) {
                            btNode d = p, last = p;
                            pred = d.left;
                            d = d.left.right;
                            while (d != null) {
                                last = pred;
                                pred = d;
                                d = d.right;
                            }
                            p.info = pred.info;
                            if (last == p) last.left = pred.left;
                            else last.right = pred.left;
                        } else {
                            if (pred.info > p.info) pred.left = p.left;
                            else pred.right = p.left;
                        }
                    } else {
                        if (pred.info > p.info) pred.left = p.right;
                        else pred.right = p.right;
                    }
                }
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
        printtree(c);
    }

    // An extension of the print method.
    private static void printtree(btNode t) {
        if (t != null) {
            printtree(t.left);
            System.out.println(t.info);
            printtree(t.right);
        }
    }
    
    // returns true if collection contains every integer from the collection
    // of obj, and conversely.
    //
    // @param   obj  reference to a collection
    // @return       indicates if collections are equal
    public boolean equals(Intcoll6 obj) {
        int j = 0; boolean result = (howmany == obj.howmany);
        if (result) {
            int[] a = new int[howmany];
            int[] b = new int[howmany];
            toarray(c, a, 0);
            toarray(obj.c, b, 0);

            j = 0;
            while ((result) && (j < howmany)) {
                result = (a[j] == b[j]); j++;
            }
        }
        return result;
    }

    // An extension of the equals method.
    private static int toarray(btNode t, int[] a, int i) {
        int num_nodes = 0;
        if (t != null) {
            num_nodes = toarray(t.left, a, i);
            a[num_nodes + i] = t.info;
            num_nodes = num_nodes + 1 + toarray(t.right, a, num_nodes + i + 1);
        }
        return num_nodes;
    }

    // This is an inner class for a binary tree
    private static class btNode {
        private int info;
        private btNode left;
        private btNode right;

        private btNode(int s, btNode lt, btNode rt) {
            info = s; left = lt; right = rt;
        }

        private btNode() {
            info = 0; left = null; right = null;
        }
    }
}