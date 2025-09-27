/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.pharmacy;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public interface Expirable {
    boolean isExpired();
    ArrayList <LocalDate> getExpiryDates();
    
}

