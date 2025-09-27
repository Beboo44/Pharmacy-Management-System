/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pharmacy;
import java.time.LocalDate;
import java.util.*;

public class FirstAid extends Product implements Expirable{
    private boolean reusable ;
    private String intendUsage ;
    private final LocalDate  expirydate;
    private ArrayList<LocalDate>expiryDates = new ArrayList<>();

    public FirstAid( String productName, int quantity, double price, int id ,boolean reusable, String intendUsage, LocalDate expirydate) {
        super(productName, quantity, price, id);
        if (intendUsage == null)
            throw new IllegalArgumentException("Intended usage should be available");
        if (expirydate == null)
            throw new IllegalArgumentException("Expiry date should be available");

        this.reusable = reusable;
        this.intendUsage = intendUsage;
        this.expirydate = expirydate;
        expiryDates.add(expirydate);
        
    }

    public boolean isReusable() {
        return reusable;
    }

    public String getIntendUsage() {
        return intendUsage;
    }

    @Override
    public ArrayList <LocalDate> getExpiryDates() {
        
        return expiryDates;
    }

    public void setReusable(boolean reusable) {
        this.reusable = reusable;
    }

    public void setIntendUsage(String intendUsage) {
        if (intendUsage == null)
            throw new IllegalArgumentException("Intended usage should be available");
        this.intendUsage = intendUsage;
    }
    
    @Override
        public void displayInfo() {        
        System.out.println("the name of product is "+ getProductName() +" : " );
        System.out.println("the quantity is "+ getQuantity());
        System.out.println("the price is "+ getPrice());
        System.out.println("the id is "+ getId() );
        System.out.println(intendUsage);
        if (reusable)    System.out.println("you can reuse it");
        else System.out.println("you can't use it again");
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