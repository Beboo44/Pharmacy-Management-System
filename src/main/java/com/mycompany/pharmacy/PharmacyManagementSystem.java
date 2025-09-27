/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pharmacy;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Access
 */

public class PharmacyManagementSystem {

    private ArrayList<Pharmacist> pharmacistsList = new ArrayList<>() ;
    private ArrayList<Client> clientsList = new ArrayList<>();
    private ArrayList<Product> productsList = new ArrayList<>();

    private int numberOfPharmacists;
    private int numberOfClients;
    private int numberOfProducts;
    private double revenue;

    public PharmacyManagementSystem(){
        Employee.setSystem(this);
    }
    public void addPharmacist(Pharmacist p) {
        for (int i = 0; i < pharmacistsList.size(); i++) {
            if (pharmacistsList.get(i).getId() == p.getId()) {
                throw new IllegalArgumentException("id already exists");

            } else if ((pharmacistsList.get(i).getEmail()).equals(p.getEmail())) {
                throw new IllegalArgumentException("email already exists");

            }
            p.setSystem(this);
        }
        pharmacistsList.add(p);
    }

    public void removePharmacist(int id) {
        for (int i = 0; i < pharmacistsList.size(); i++) {
            if (pharmacistsList.get(i).getId() == id) {
                pharmacistsList.remove(i);
                System.out.println("Pharmacist with id " + id + " has been removed.");
                return;
            }
        }
        System.out.println("Pharmacist with id " + id + " not found.");
    }

    public void addClient(Client c) {
        for (int i = 0; i < clientsList.size(); i++) {
            if (clientsList.get(i).getPhoneNumber() == c.getPhoneNumber()) {
                throw new IllegalArgumentException("Phone number already exists");

            }
        }
        //c.setSystem(this);
        clientsList.add(c);
    }

    public void removeClient(String phoneNumber) {
        for (int i = 0; i < clientsList.size(); i++) {
            if (clientsList.get(i).getPhoneNumber() == phoneNumber) {
                clientsList.remove(i);
                System.out.println("Client has been removed.");
                return;
            }
        }
        System.out.println("Client not found.");
    }

    public void addProduct(Product p) {
        for (int i = 0; i < productsList.size(); i++) {
            if ((productsList.get(i).getId() == p.getId())&&(productsList.get(i).getProductName() != p.getProductName())) {
                throw new IllegalArgumentException("id already exists");
            }
            else if ((productsList.get(i)).getProductName() == p.getProductName()) {
                if((productsList.get(i))instanceof Expirable){
                   ArrayList<LocalDate> l= ((Expirable)(productsList.get(i))).getExpiryDates();
                   for(LocalDate ex: ((Expirable)p).getExpiryDates()){
                        l.add(ex);
                   }

                }
                ArrayList<Integer> q = (productsList.get(i)).getQuantities();
                for(Integer quantity: p.getQuantities()) {
                    q.add(quantity);
                }
                return;
            }
        }
        productsList.add(p);


    }




    public void removeProduct(String name) {
        for (int i = 0; i < productsList.size(); i++) {
            if ((productsList.get(i).getProductName()) .equals( name)) {
                productsList.remove(i);
                System.out.println("Product has been removed.");
                return;
            }
        }
        System.out.println("Product not found.");
        throw new IllegalArgumentException("Product not found");
    }

    public ArrayList<Pharmacist> getPharmacistsList() {
        return pharmacistsList;
    }

    public ArrayList<Client> getClientsList() {
        return clientsList;
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    public int getNumberOfPharmacists() {
        return pharmacistsList.size();
    }

    public int getNumberOfClients() {
        return clientsList.size();
    }

    public int getNumberOfProducts() {
        return productsList.size();
    }

    public double getRevenue() {
        return revenue;
    }

    public void updateRevenue(double revenue) {
        this.revenue += revenue;
    }
    public boolean containsPharmacist(Pharmacist p) {
        return pharmacistsList.contains(p);
    }
    public boolean containsClient(Client c) {
        return clientsList.contains(c);
    }
    public boolean containsProduct(Product p) {
        return productsList.contains(p);
    }

    public static void main( String args[]){

        PharmacyManagementSystem p = new PharmacyManagementSystem();
        LocalDate d = LocalDate.of(2026,5,28);
        Expirable med = new Medicine("panadol",5,30,"piece",false,12,"flu",4,d);
        ArrayList <LocalDate>exp = med.getExpiryDates();

        for(int i=0; i<exp.size(); ++i){
            System.out.println(exp.get(i).getYear() + "/" + exp.get(i).getMonthValue() + "/" + exp.get(i).getDayOfMonth());
        }



        Pharmacist ph1 = new Pharmacist("lotfy", "01228392057", "male", 12, 21, "lotfy@gmail.com", "admin",4,"06:00", "09:00");
        Pharmacist ph2 = new Pharmacist("hamada", "01228392057", "male", 12, 21, "hamada@gmail.com", "admin",5,"06:00", "09:00");
        Manager m = new Manager("Abanoub" ,"01228392057", "male", 100000, 20, "abanoub@gmail.com", "admin");
        Client c1 = new Client("Bassem", "01005317507", "male", null, 5);

        Person [] persons = {ph1,ph2,m,c1};

        System.out.println("before sorting =====================");
        for(int i=0; i<4; ++i){
            System.out.print(persons[i].getName() + " ");
            //persons[i].displayInfo();
        }

        java.util.Arrays.sort(persons);

        System.out.println("after sorting =====================");
        for (int i=0; i<4; ++i){
            System.out.print(persons[i].getName() + " ");
            persons[i].displayInfo();
        }


        System.out.println();

        //exception handling
        try{
            ((Medicine)med).setAmount(-2);
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }


        /// ///////////////////////////

        Product p1 = new Medicine("congestal",5,30,"piece",false,12,"flu",2,d);
        Product p2 = new FirstAid("Bandages",5,30,9,false,"wounds",d);
        Product p3 = new Device("Thermometer",5,30,1,"temperature","celesius",6);

        m.hirePharmacist(ph1);
        m.hirePharmacist(ph2);

        Person.setSystem(p);
        ph1.addProduct(p1);
        ph2.addProduct(p2);
        ph1.addProduct(p3);



        ArrayList<Product> product = p.getProductsList();
        System.out.println(product.size());
        for(int i=0; i<product.size(); ++i){
            product.get(i).displayInfo();
        }





    }




}
