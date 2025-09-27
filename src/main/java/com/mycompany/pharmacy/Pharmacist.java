/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pharmacy;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


/**
 *
 * @author Access
 */
public class Pharmacist extends Employee{
    private int id;
    private String shiftStartTime;
    private String shiftEndTime;




    public Pharmacist(String name, String phoneNumber, String gender, double salary, int age, String email, String password,int id,String shiftStartTime, String shiftEndTime) {
        super(name, phoneNumber, gender, salary, age, email, password);
        setShiftTime(shiftStartTime,shiftEndTime);
        this.id = id;
    }



    public void setShiftTime(String shiftStartTime,String shiftEndTime) {
        try{ LocalTime startTime = LocalTime.parse(shiftStartTime);
            LocalTime endTime = LocalTime.parse(shiftEndTime);

            // Ensure start < end
            if (!startTime.isBefore(endTime)) {
                throw new IllegalArgumentException("Start time must be before end time.");}
        }
        catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format. Use HH:mm (e.g., 08:30).");
        }
    }

    public String getShiftTime() {
        return shiftStartTime+"-"+shiftEndTime;
    }

    public void setId(int id){
        this.id = id;
    }
    public int getId() {
        return id;
    }

    public void addProduct(Product p){
        if(getSystem().containsPharmacist(this))
            getSystem().addProduct(p);
        else
            System.out.println("This pharmacist doesn't have access to the pharmacy system");
    }

    public void removeProduct(String p){
        if(getSystem().containsPharmacist(this) )
            getSystem().removeProduct(p);
        else
            System.out.println("This pharmacist doesn't have access to the pharmacy system");
    }

    public void removeExpiredProducts(){
        ArrayList<Product> products = getSystem().getProductsList();
        for(Product p:products){
            if(p instanceof Expirable){
                if(((Expirable)p).isExpired()){
                    products.remove(p);
                }
            }
        }
    }

    @Override
    public void displayInfo(){
        super.displayInfo();
        System.out.println("ID:"+getId());
        System.out.println("Shift Time:"+getShiftTime());
    }

    public void updateInfo(String name, String phoneNumber, String gender, double salary, int age, String email, String password,String shiftStartTime, String shiftEndTime){
        super.updateInfo(name, phoneNumber, gender, salary, age, email, email, email);
        setShiftTime(shiftStartTime,shiftEndTime);
    }

    public static void searchByID(int id){
        ArrayList<Pharmacist> p=getSystem().getPharmacistsList();


        for (int i = 0; i < p.size(); i++) {
            if ((p.get(i)).getId() == id) {
                (p.get(i)).displayInfo();
                return;
            }

        }
        System.out.println("Pharmacist not found");

    }


}
