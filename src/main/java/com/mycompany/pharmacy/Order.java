
package com.mycompany.pharmacy;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

class Order {                       // place order method,interface
    private ArrayList<Integer >quantity = new ArrayList<>();
    private LocalDate orderDate;
    private String clientName;
    private ArrayList<Product> requestedProducts = new ArrayList<>();
    
    public Order(String clientName, ArrayList<Product>requestedProducts,ArrayList<Integer> quantity,LocalDate orderDate) {
        for (Product p : requestedProducts) {
            if (p == null) {
                throw new IllegalArgumentException(" product should not be empty");
            }
        }
        for (int q : quantity) {
            if (q <= 0) {
                throw new IllegalArgumentException("quantitie should be positive numbers.");
            }
        }
        if (orderDate == null) {
            throw new IllegalArgumentException("Order date should not be null");
        }
        if(clientName ==null || !clientName.matches("[a-zA-Z ]+")){
            throw new IllegalArgumentException("client name should not be empty");
        }
         
        this.clientName = clientName;
        this.requestedProducts = new ArrayList<>(requestedProducts);
        this.quantity = new ArrayList<>(quantity);
        this.orderDate = orderDate;
    }

    // Getters
    public String getClientName() {
        return clientName;
    }
    public ArrayList<Integer> getQuantity() {
        return new ArrayList<>(quantity);
    }

    

    public LocalDate getOrderDate() {
        return orderDate;
    }
    public double getTotalCost(){
        double cost = 0;
        for(int i = 0; i<requestedProducts.size(); ++i){
            cost+=requestedProducts.get(i).getPrice() * quantity.get(i);
        }
        return cost;
    }
    
    public ArrayList<Product> getProducts() {
        return new ArrayList<>(requestedProducts);
    }

    public void placeOrder(){
        
        for(int i=0; i<requestedProducts.size(); ++i){
            Product p = requestedProducts.get(i);
            int q = quantity.get(i);
            if(q>p.getQuantity()){
                throw new IllegalStateException("No sufficient quantity of " + p.getProductName());
            }
        }
        
        for(int i=0; i<requestedProducts.size(); ++i){
            Product p = requestedProducts.get(i);
            int q = quantity.get(i);
            ArrayList<Integer> q2 = p.getQuantities();
            for(int j = 0; j<q2.size() && q>0; ++j){
                if(q2.get(j)>=q){
                    q2.set(j, q2.get(j)-q);
                    q = 0;
                }
                else{
                    q-=q2.get(j);
                    q2.set(j, 0);
                }
                if(q2.get(j)==0){
                    q2.remove(j);
                    if(p instanceof Expirable){
                        ((Expirable) p).getExpiryDates().remove(j);
                    }
                    --j;
                }
            }
        }
        
        System.out.println("The order is placed successfully");
        this.printBill();
        System.out.println("Make your payment");
    }
    // Setters
    public void setClientName(String clientName) {
        if (clientName == null || !clientName.matches("[a-zA-Z ]+")) {
            throw new IllegalArgumentException("client name should contain only letters and spaces");
        }
        this.clientName = clientName;
    }

    public void setQuantity(ArrayList<Integer> quantity) {
        for (int q : quantity) {
            if (q <= 0) {
                throw new IllegalArgumentException("Quantities should be positive numbers.");
            }
        }
        this.quantity = new ArrayList<>(quantity);
    }

    

    public void setOrderDate(LocalDate orderDate) {
        if (orderDate == null) {
            throw new IllegalArgumentException("Order date should not be null");
        }
        this.orderDate = orderDate;
    }
    public void setProducts(ArrayList<Product> products) {
        if (products == null || products.isEmpty()) {
            throw new IllegalArgumentException("Product list cannot be empty");
        }
        this.requestedProducts = new ArrayList<>(products);
    }


    
    private int getTotalQuantity() {
        int totalQuantity = 0;
        for (int q : quantity) {
            totalQuantity += q;
        }
        return totalQuantity;
    }
      protected void printBill() {
        System.out.println("--------- Order Bill ---------");
        System.out.println("Client: " + clientName);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Products:");
        for (int i = 0; i < requestedProducts.size(); i++) {
            Product p = requestedProducts.get(i);
            int q = quantity.get(i);
            System.out.println("  - " + p.getProductName() + " | Quantity: " + q +
                               " - Price: " + p.getPrice() +
                               " - Total: " + (p.getPrice() * q));
        }
        System.out.println("Total Quantity: " + getTotalQuantity());
        System.out.println("Total Cost: " + getTotalCost());
        System.out.println("----------------------------");
    }

}