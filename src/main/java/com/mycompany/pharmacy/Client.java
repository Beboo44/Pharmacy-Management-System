/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.pharmacy;

import java.util.ArrayList;

/**
 *
 * @author Access
 */
class Client extends Person{
    private Order lastOrder;
    private int numberOfOrders;

    Client(String name, String phoneNumber, String gender, Order lastOrder, int numberOfOrders) {
        super(name, phoneNumber, gender);
        this.lastOrder = lastOrder;
        this.numberOfOrders = numberOfOrders;
    }

    public Order getLastOrder() {
        return lastOrder;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setLastOrder(Order lastOrder) {
        this.lastOrder = lastOrder;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }
    public void displayInfo(){
        super.displayInfo();
        System.out.println("Number of orders="+getNumberOfOrders());
    }
    public static void searchByPhoneNumber(String phoneNumber){
        ArrayList<Client> c=getSystem().getClientsList();


        for (int i = 0; i < c.size(); i++) {
            if ((c.get(i)).getPhoneNumber().equals( phoneNumber)) {
                (c.get(i)).displayInfo();
                return;
            }

        }
        System.out.println("Client not found");
    }

}