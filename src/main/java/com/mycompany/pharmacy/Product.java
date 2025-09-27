/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
     */
package com.mycompany.pharmacy;

import java.util.ArrayList;

    /**
     *
     * @author hp
     */

    public abstract class Product implements Comparable<Product> {
        private String productName ;
        private int quantity;
        private double price ;
        private int id ;
        protected ArrayList<Integer> quantities = new ArrayList<>();



        public Product(String productName,int quantity, double price, int id) {
            if (productName != null ||productName.matches("^[a-zA-Z\\s]+$")) {
                this.productName = productName; }
            else{
                throw new IllegalArgumentException ("Invalid name, numbers and special characters aren't allowed.");
            }
            if (quantity <= 0)
                throw new IllegalArgumentException("Quantity should be positive number");
            if (price <= 0)
                throw new IllegalArgumentException("price should be positive number");
            if (id <= 0)
                throw new IllegalArgumentException("id should be positive number");

            this.quantity = quantity;
            this.price = price;
            this.id = id;

            quantities.add(quantity);
        }
        public int getQuantity() {
            int sumQuantity = 0;
            for(int i=0; i<quantities.size(); ++i){
                sumQuantity += quantities.get(i);
            }
            return sumQuantity;
        }
        public String getProductName() {
            return productName;
        }
        public double getPrice() {
            return price;
        }
        public int getId() {
            return id;
        }
        public ArrayList <Integer> getQuantities() {
            return quantities;
        }

        public void setPrice(double price) {
            if ( price <=0) throw new IllegalArgumentException("invalid input");
            this.price = price;
        }
        public void setProductName(String productName) {
            if (productName != null ||productName.matches("^[a-zA-Z\\s]+$")) {
                this.productName = productName; }
            else{
                throw new IllegalArgumentException ("Invalid name, numbers and special characters aren't allowed.");
            }
        }
        public void setQuantity(int quantity) {
            if (quantity <= 0) throw new IllegalArgumentException("Quantity should be positive number");
            this.quantity = quantity;
        }
        public void setId(int id) {
            if (id <= 0) throw new IllegalArgumentException("id should be positive number");
            this.id = id;
        }
        public abstract void displayInfo();

        public int compareTo(Product product){
            return Integer.compare(this.id, product.id);
        }
    }