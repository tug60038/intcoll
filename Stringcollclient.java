//**********************************************************
// FILE: NAME   : Stringcollclient.java
// DESCRIPTION  : This is a client of class Stringcoll.
//**********************************************************

import java.util.*;

public class Stringcollclient {
    public static final String SENTINEL = "EXIT";
    
    public static void main(String[] args) {
        String value; char sign;
        Scanner keyboard = new Scanner(System.in);
        Stringcoll P = new Stringcoll(), N = new Stringcoll(), L = new Stringcoll();
        
        System.out.println("Enter a String to be inserted or enter the command, 'EXIT', to quit:");
        
        value = keyboard.next();
        while (!value.equals(SENTINEL)) {
            sign = value.charAt(0);
            value = value.substring(1);
            if (sign == '+') {
                P.insert(value);
                L.insert(value);
            } else if (sign == '-') {
                N.insert(value);
                L.omit(value);
            } else {
                System.out.println("Invalid entry.");
            }
            System.out.println("Enter next String to be inserted or enter the command 'EXIT' to quit:");
            value = keyboard.next();
        }
        System.out.println("\nThe values in collection P are:");
        P.print();
        System.out.println("\nThe values in collection N are:");
        N.print();
        System.out.println("\nThe values in collection L are:");
        L.print();
        if (P.equals(N)) {
            System.out.println("\nP and N are equal.");
        } else {
            System.out.println("\nP and N are NOT equal.");
        }
        Stringcoll A = new Stringcoll();
        A.copy(L);
        System.out.println("\nThe values in the copy of L are:\n");
        A.print();
    }
}