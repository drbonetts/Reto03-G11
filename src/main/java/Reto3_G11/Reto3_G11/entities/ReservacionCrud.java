/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Reto3_G11.Reto3_G11.entities;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
public interface ReservacionCrud extends CrudRepository<Reservacion, Integer> {
    
    @Query ("SELECT c.startDate, COUNT(c.startDate) from Reservacion AS c group by c.startDate order by COUNT(c.startDate) DESC")
     public List<Object[]> countTotalReservationsByDates();
    
    @Query ("SELECT c.status, COUNT(c.status) from Reservacion AS c group by c.status order by COUNT(c.status) DESC")
     public List<Object[]> countTotalReservationsByStatus();
    
    @Query ("SELECT c.client, COUNT(c.client) from Reservacion AS c group by c.client order by COUNT(c.client) DESC")
     public List<Object[]> countTotalReservationsByClients();
    
}
