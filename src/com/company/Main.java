package com.company;

import java.util.ListIterator;

public class Main {

    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();
        LinkedList linkedList1 = new LinkedList();

        linkedList.add(32); // index: 0
        linkedList.add(67); // index: 1
        linkedList.add(34); // index: 2
        linkedList.add(33); // index: 3

        ListIterator listIterator = linkedList.listIterator();

        System.out.println(listIterator.hasPrevious());
        listIterator.next();
        listIterator.next();
        listIterator.next();
        listIterator.next();




        System.out.println("===============================");
        for (int i =0; i< linkedList.size(); i++){
            System.out.println(linkedList.get(i));
        }

    }
}
