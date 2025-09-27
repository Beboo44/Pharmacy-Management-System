/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pharmacy;
import java.time.LocalDate;
import java.time.Period;



public class Device extends Product {
    private String measuredParameter;
    private String measuringUnit;
    private int guarantee;
    
    public Device(String productName, int quantity, double price, int id, String parameter, String unit, int period){
        super(productName, quantity, price, id);
        measuredParameter = parameter;
        measuringUnit = unit;
        if(period <=0){
            throw new IllegalArgumentException("invalid guarantee period");
        }
        guarantee = period;
    }

    public String getMeasuredParameter() {
        return measuredParameter;
    }

    public String getMeasuringUnit() {
        return measuringUnit;
    }

    public int getGurantee() {
        return guarantee;
    }

    public void setMeasuredParameter(String measuredParameter) {
        this.measuredParameter = measuredParameter;
    }

    public void setMeasuringUnit(String measuringUnit) {
        this.measuringUnit = measuringUnit;
    }

    public void setGurantee(int gurantee) {
        if(gurantee <=0){
            throw new IllegalArgumentException("invalid guarantee period");
        }
        
        this.guarantee = gurantee;
        
    }
    
    boolean isRefunded(LocalDate d){
        LocalDate currDate = LocalDate.now();
        
        Period period = Period.between(d, currDate);
        int diff = (period.getYears() * 12 ) + period.getMonths();
        
        if(diff<=guarantee){
            return true;
        }
        return false;
    }
    @Override
    public void displayInfo(){
        System.out.print("The Device is "+ getProductName() );
        System.out.print(" , the quantity is "+ getQuantity());
        System.out.print(" , the price is "+ getPrice() );
        System.out.println(" , the id is "+ getId());
        System.out.print("The device measures "+ measuredParameter);
        System.out.print("in "+ measuredParameter + " unit");
        System.out.print(" , it has a gurantee of "+ guarantee);
        
    }
    
}
