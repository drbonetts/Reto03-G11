/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.service;


import Reto3_G11.Reto3_G11.entities.Reservacion;
import Reto3_G11.Reto3_G11.report.ReservacionAmount1;
import Reto3_G11.Reto3_G11.report.ReservacionAmount2;
import Reto3_G11.Reto3_G11.report.ReservacionAmount3;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author diego
 */
@RestController
@RequestMapping("/api/Reservation")
public class ReservacionController {
    @Autowired
   private ReservacionService reservacionService;
    
   @GetMapping("/all")
   public List<Reservacion> getReservacion() {return reservacionService.getAll();};

   @GetMapping("/{id}")
   public Optional<Reservacion> getReservacion(@PathVariable("id") int reservationId) {
        return reservacionService.getReservacion(reservationId);
      }
   @PostMapping("/save")
   @ResponseStatus(HttpStatus.CREATED)
    public Reservacion save(@RequestBody Reservacion reservacion) {
        return reservacionService.save(reservacion);
      };
    
   @PutMapping("/update")
   @ResponseStatus(HttpStatus.CREATED)
   public Reservacion update(@RequestBody Reservacion reservacion) {
        return reservacionService.update(reservacion);
    }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public boolean delete(@PathVariable("id") int Id) {
        return reservacionService.deleteReservacion(Id);
    }
   
  
    //Report!
   @GetMapping("/report/{startDate}/{devolutionDate}")
    public List<ReservacionAmount1> getReportDates(){
        return reservacionService.getTopReservacionByDates();
    }
    
   
    @GetMapping("/report-status")
    public List<ReservacionAmount2> getReportStatus(){
        return reservacionService.getTopReservacionByStatus();
    }
    
    @GetMapping("/report-clients")
    public List<ReservacionAmount3> getReportCLient(){
        return reservacionService.getTopReservacionByCLient();
    }
      
}
