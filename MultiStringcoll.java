//*********************************************************************
// FILE NAME    : MultiStringcoll.java
// DESCRIPTION  : This file contains the class MultiStringcoll.
//*********************************************************************

import java.util.*;

public class MultiStringcoll {
    private btNode c;
    private int howmany;

    // Creates an empty collection of Strings with a capactity of 500
    public MultiStringcoll() {
        c = null;
        howmany = 0;
    }

    // Creates an empty collection of Strings with a capactity of i
    //
    // @param   i   capacity of collection, i >= 0
    public MultiStringcoll(int i) {
        c = null;
        howmany = 0;
    }

    // Overrides collection with a copy of the contents stored in the collection
    // of obj when both collections are not equal. Both collections will have 
    // the same capacity.
    //
    // @param   obj  reference to a collection
    public void copy(MultiStringcoll obj) {
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
            root.count = t.count;
            root.left = copytree(t.left);
            root.right = copytree(t.right);
        }
        return root;
    }

    // Returns true if String of choice is in collection, otherwise returns false
    // 
    // @param   i    String to be determined whether or not it is in collection
    // @return       indicates if i is in collection
    public boolean belongs(String i) {
        btNode p = c;
        while ((p != null) && !(p.info.equals(i))) {
            if (p.info.compareTo(i) > 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return (p != null);
    }    

    // Adds a String to collection. If capacity is exceeded, the capacity will
    // double in size.
    //
    // @param   i    String to be added, i > 0
    public void insert(String i) {
        btNode pred = null, p = c;
        howmany++;
        while ((p != null) && !(p.info.equals(i))) {
            pred = p;
            if (p.info.compareTo(i) > 0) p = p.left;
            else p = p.right;
        }
        if (p != null) {
            p.count++;
        }
        else {
            p = new btNode(i, null, null);
            if (pred != null) {
                if (pred.info.compareTo(i) > 0) pred.left = p;
                else pred.right = p;
            } else c = p;
        }
    }

    // Removes String if it is in collection
    //
    // @param   i    String to be removed, i > 0
    public void omit(String i) {
        btNode p = c, pred = null;
        while ((p != null) && !(p.info.equals(i))) {
            pred = p;
            if (p.info.compareTo(i) > 0) p = p.left;
            else p = p.right;
        }
        if (p != null) {
            if (p.count > 1) p.count--;
            else {
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
                            if (pred.info.compareTo(p.info) > 0) pred.left = p.left;
                            else pred.right = p.left;
                        }
                    } else {
                        if (pred.info.compareTo(p.info) > 0) pred.left = p.right;
                        else pred.right = p.right;
                    }
                }
            }
            howmany--;
        }
    }
    
    // returns the number of distinct Strings stored in collection
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
            System.out.println(t.info + " (" + t.count + ")");
            printtree(t.right);
        }
    }
    
    // returns true if collection contains every String from the collection
    // of obj, and conversely.
    //
    // @param   obj  reference to a collection
    // @return       indicates if collections are equal
    public boolean equals(MultiStringcoll obj) {
        int j = 0; boolean result = (howmany == obj.howmany);
        if (result) {
            String[] a = new String[howmany];
            String[] b = new String[howmany];
            int[] e = new int[howmany]; // stores the amount of occurances for each string. corresponds to array a
            int[] f = new int[howmany]; // ditto, but for array b
            toarray(c, a, 0);
            toarray(obj.c, b, 0);
            toarray2(c, e, 0);
            toarray2(obj.c, f, 0);

            j = 0;
            while ((result) && (j < howmany)) {
                result = ((a[j].equals(b[j])) && e[j] == f[j]); j++;
            }
        }
        return result;
    }

    // An extension of the equals method. Array of the info of collection
    private static int toarray(btNode t, String[] a, int i) {
        int num_nodes = 0;
        if (t != null) {
            num_nodes = toarray(t.left, a, i);
            a[num_nodes + i] = t.info;
            num_nodes = num_nodes + 1 + toarray(t.right, a, num_nodes + i + 1);
        }
        return num_nodes;
    }

    // An extension of the equals method. Array of number of occurances of collection
    private static int toarray2(btNode t, int[] a, int i) {
        int num_nodes = 0;
        if (t != null) {
            num_nodes = toarray2(t.left, a, i);
            a[num_nodes + i] = t.count;
            num_nodes = num_nodes + 1 + toarray2(t.right, a, num_nodes + i + 1);
        }
        return num_nodes;
    }

    // returns total number of Strings in collection.
    public int get_total() {;
        return (total(c));
    }

    // An extension of the get_total method
    private static int total(btNode t) {
        if (t != null) {
            return (t.count + total(t.left) + total(t.right));
        }
        return 0;
    }

    // This is an inner class for a binary tree
    private static class btNode {
        private String info;
        private int count;
        private btNode left;
        private btNode right;

        private btNode(String s, btNode lt, btNode rt) {
            info = s; left = lt; right = rt;
            if (s != null) count = 1;
            else count = 0;
        }

        private btNode() {
            info = null; count = 0; left = null; right = null;
        }
    }
}