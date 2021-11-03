/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.report;

import Reto3_G11.Reto3_G11.entities.Cliente;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author diego
 */
public class ReservacionAmount3 {
    
    
    Long total;
    Cliente client;

    public ReservacionAmount3(Cliente client, Long total) {
        this.client = client;
        this.total = total;
    }

    public Cliente getClient() {
        return client;
    }

    public void setClient(Cliente client) {
        this.client = client;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    
    

    

    
    
}
