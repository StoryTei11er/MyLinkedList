package com.company;

public class Main {

    public static void main(String[] args) {

        LinkedList linkedList = new LinkedList();


        linkedList.add(32); // index: 0
        linkedList.add(32); // index: 1
        linkedList.add(34); // index: 2
        linkedList.add(32); // index: 3

        System.out.println(linkedList.indexOf(32));
        System.out.println(linkedList.lastIndexOf(32));





        System.out.println("===============================");
        for (int i =0; i< linkedList.size(); i++){
            System.out.println(linkedList.get(i));
        }

    }
}
