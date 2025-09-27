/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.pharmacy;
import java.time.LocalDate;
import java.util.*;

/**
 *
 * @author hp
 */
public class SelfCare extends Product implements Expirable{

    protected String type ;
    protected String category ;
    protected int size ;
    LocalDate expirydate;
    private ArrayList<LocalDate>expiryDates = new ArrayList<>();
    


    public SelfCare( String productName, int quantity, double price, int id , String type, String category, int size,LocalDate  expirydate) {
        super( productName,quantity, price, id);
            if (type != null ||type.matches("^[a-zA-Z\\s]+$")) {
            this.type = productName; }
            else{
            throw new IllegalArgumentException ("Type should be available");
            }
        if (category == null)
            throw new IllegalArgumentException("Category should be available");
        if (size <= 0)
            throw new IllegalArgumentException("Size should be available");
        if (expirydate == null)
            throw new IllegalArgumentException("Expiry date should be available");

                this.type = type;
                this.category = category;
                this.size = size;
                this.expirydate = expirydate;
                expiryDates.add(expirydate);
            }

    public void setSize(int size) {
        if (size <= 0)
            throw new IllegalArgumentException("Size should be available");
        this.size = size;
    }

    public void setType(String type) {
            if (type != null ||type.matches("^[a-zA-Z\\s]+$")) {
            this.type = type; }
            else{
            throw new IllegalArgumentException ("Type should be available");
            }
    }

    public void setCategory(String category) {
        if (category == null)
            throw new IllegalArgumentException("Category should be available");
        this.category = category;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public int getSize() {
        return size;
    }

    @Override
    public ArrayList <LocalDate> getExpiryDates() {
        return expiryDates;
    }
    
    @Override
    public void displayInfo() {
        System.out.println("the name of product is "+ getProductName() +" :" );
        System.out.println("details : the quantity is "+ getQuantity());
        System.out.println("the price is "+ getPrice() );
        System.out.println("the id is "+ getId() );
        System.out.println("the category is " + category);
        System.out.println("the type is " + type); 
        System.out.println("the size is " +size);
        System.out.println("the expiry date : "+ expirydate);
    }
    @Override
    public boolean isExpired(){
        for(int i = 0; i< expiryDates.size(); ++i){
            if(expirydate.isBefore(LocalDate .now())){
                expiryDates.remove(i);
                quantities.remove(i);
                --i;
            }
        }
        return (expiryDates.isEmpty());
    }


}