/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pharmacy;

import java.time.LocalDate;
import java.util.*;
public class Medicine extends Product implements Expirable {

    private String type ;
    private boolean isNeedPrispiction;
    private int amount ;
    private String symptom;
    private  LocalDate expirydate;
    private ArrayList<LocalDate>expiryDates = new ArrayList<>();

    

        public Medicine( String productName, int  quantity, double price,String type, boolean isNeedPrispiction, int amount, String symptom, int id ,LocalDate  expirydate) {
            super(productName,quantity,  price, id);
            if (type == null)
            throw new IllegalArgumentException("type should be avilable");
            if (amount <= 0)
            throw new IllegalArgumentException("amount should be positive number");
            if (symptom == null)
            throw new IllegalArgumentException("symptom should be avilable");
            if (expirydate == null)
            throw new IllegalArgumentException("expiry date should be avilable");
            this.type = type;
            this.isNeedPrispiction = isNeedPrispiction;
            this.amount = amount;
            this.symptom = symptom;
            this.expirydate=expirydate;
            expiryDates.add(expirydate);
            quantities.add(quantity);
        }

    public void setIsNeedPrispiction(boolean isNeedPrispiction) {
        this.isNeedPrispiction = isNeedPrispiction;
    }

    public void setAmount(int amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("amount should be positive number");
        this.amount = amount;
    }

    public void setType(String type) {
        if (type == null)
            throw new IllegalArgumentException("type should be avilable");
        this.type = type;
    }

    public void setSymptom(String symptom) {
        if (symptom == null)
            throw new IllegalArgumentException("symptom should be avilable");
        this.symptom = symptom;
    }

    public boolean isIsNeedPrispiction() {
        return isNeedPrispiction;
    }

    public int getAmount() {
        return amount;
    }

    public String getSymptom() {
        return symptom;
    }

    @Override
    public ArrayList <LocalDate> getExpiryDates() {
        
        return expiryDates;
    }

    public String getType() {
            return type;
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

        @Override
        public void displayInfo() {        
        System.out.println("the name of product is "+ getProductName()  );
        System.out.println("the quantity is "+ getQuantity());
        System.out.println("the price is "+ getPrice() );
        System.out.println("the id is "+ getId() );
            if(type =="piece")System.out.println(amount +" piece and ");
            else if (type == " liquid")System.out.println(amount +" ml and ");
            if (isNeedPrispiction)System.out.print("need prespiction  ");
            else System.out.println("don't need prespiction  ");
        System.out.println("the expiry date : "+ expirydate);
        }
  
   }