/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.report;

import Reto3_G11.Reto3_G11.entities.Reservacion;

/**
 *
 * @author diego
 */
public class ReservacionAmount1 {
    
    Reservacion reservations;
    Long amountDates;

    public ReservacionAmount1(Reservacion reservations, Long amountDates) {
        this.reservations = reservations;
        this.amountDates = amountDates;
    }

    public Reservacion getReservations() {
        return reservations;
    }

    public void setReservations(Reservacion reservations) {
        this.reservations = reservations;
    }

    public Long getAmountDates() {
        return amountDates;
    }

    public void setAmountDates(Long amountDates) {
        this.amountDates = amountDates;
    }
    
    
    
}
