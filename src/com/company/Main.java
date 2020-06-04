package com.company;

public class Main {

    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();
        LinkedList linkedList1 = new LinkedList();

        linkedList.add(32); // index: 0
        linkedList.add(67); // index: 1
        linkedList.add(34); // index: 2
        linkedList.add(33); // index: 3

        linkedList1.add(67); // index: 0
        linkedList1.add(11); // index: 1
        linkedList1.add(44); // index: 2
        linkedList1.add(34); // index: 3

        linkedList.removeAll(linkedList1);


        System.out.println("===============================");
        for (int i =0; i< linkedList.size(); i++){
            System.out.println(linkedList.get(i));
        }

    }
}
