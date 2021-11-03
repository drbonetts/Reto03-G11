/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.dao;


import Reto3_G11.Reto3_G11.entities.Reservacion;
import Reto3_G11.Reto3_G11.entities.ReservacionCrud;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
public class ReservacionRepository {
    @Autowired
  private ReservacionCrud reservacionCrudRepository;
  
  public List<Reservacion> getAll() {return (List<Reservacion>) reservacionCrudRepository.findAll();};
  
  public Optional<Reservacion> getReservacion(int id) {return reservacionCrudRepository.findById(id);};
  
  public Reservacion save(Reservacion reservacion) { return reservacionCrudRepository.save(reservacion);};
  
  public void delete(Reservacion reservacion){ reservacionCrudRepository.delete(reservacion);}; 
  
  public List<Reservacion> getTopByDates(Date dateOne, Date dateTwo){
        return reservacionCrudRepository.findAllByStartDateAfterAndStartDateBefore(dateOne, dateTwo);
  }
  
  public List<Reservacion> getTopByStatus(String Status){
        return reservacionCrudRepository.findAllByStatus(Status);
  }
        
  public List<Object[]> getTopByCLient(){
        return reservacionCrudRepository.countTotalReservationsByClients();
    }
}
