/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pharmacy;


public class Payment {
    private double totalCost;
    private String paymentMethod ;
    private String  cardId ;
    private int password ;
    private int paymentAmount ; 

    public Payment(double totalCost, String paymentMethod,int paymentAmount) {
        if (totalCost <= 0)        throw new IllegalArgumentException("the cost should be positive number");
        if (paymentAmount <= 0)        throw new IllegalArgumentException("amount should be positive number");
        if(totalCost>paymentAmount ){throw new IllegalArgumentException("The money is insufficient.");}
        this.totalCost = totalCost;
        setPaymentMethod(paymentMethod);
        this.paymentAmount = paymentAmount;
    }
    
    public Payment(double totalCost, String paymentMethod, String cardId, int password) {
        if (totalCost <= 0)        throw new IllegalArgumentException("the cost should be positive number");
        this.totalCost = totalCost;
        setPaymentMethod(paymentMethod); // reuses validation
        setCardId(cardId);
        this.password = password;
    }


    public double getRemainingAmount(){
        return (paymentAmount-totalCost );
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getCardId() {
        return cardId;
    }

    public int getPaymentAmount() {
        return paymentAmount;
    }

    public void setTotalCost(double totalCost) {
        if (totalCost <= 0)        throw new IllegalArgumentException("amount should be positive number");
        this.totalCost = totalCost;
    }

    public void setPaymentMethod(String paymentMethod) {
        if (paymentMethod == null || (!paymentMethod.equalsIgnoreCase("visa") && !paymentMethod.equalsIgnoreCase("cash"))) {throw new IllegalArgumentException("invalid method");}
        this.paymentMethod = paymentMethod;
    }

    public void setCardId(String cardId) {
        if (cardId.length() != 12&&(!paymentMethod.equals("visa"))) {throw new IllegalArgumentException("Value must be exactly 12 digits.");}
        this.cardId = cardId;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public void setPaymentAmount(int paymentAmount) {
        if (paymentAmount <= 0)        throw new IllegalArgumentException("amount should be positive number");
        if(paymentAmount<totalCost){throw new IllegalArgumentException("The money is insufficient.");}
        this.paymentAmount = paymentAmount;
    }
    public void displayInfo(){
        System.out.println("the total cost of the payment : "+totalCost);
        System.out.println("the payment method : " +paymentMethod);
    if ("cash".equalsIgnoreCase(paymentMethod)) {
        System.out.println("Remaining amount: " + getRemainingAmount());
        System.out.println("Paid amount: " + paymentAmount);
    } else if ("visa".equalsIgnoreCase(paymentMethod)) {
        System.out.println("Successful process");
    } else {
        System.out.println("Unknown payment method");
    }
    }
    
}
