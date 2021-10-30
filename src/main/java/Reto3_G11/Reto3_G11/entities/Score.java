/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author diego
 */
@Entity
@Table (name="score")
public class Score {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer idScore;
    private String messageText;
    private Integer qualification;

    @OneToOne
    @JoinColumn(name = "reservationsId")
    @JsonIgnoreProperties({"score","motorbike","message","client"})
    private Reservacion reservations;

    public Integer getIdScore() {
        return idScore;
    }

    public void setIdScore(Integer idScore) {
        this.idScore = idScore;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Integer getQualification() {
        return qualification;
    }

    public void setQualification(Integer qualification) {
        this.qualification = qualification;
    }

    public Reservacion getReservations() {
        return reservations;
    }

    public void setReservations(Reservacion reservations) {
        this.reservations = reservations;
    }

    
}
