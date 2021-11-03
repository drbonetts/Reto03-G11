/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.service;

import Reto3_G11.Reto3_G11.dao.ReservacionRepository;
import Reto3_G11.Reto3_G11.entities.Cliente;
import Reto3_G11.Reto3_G11.entities.Reservacion;
import Reto3_G11.Reto3_G11.report.ReservacionAmount1;
import Reto3_G11.Reto3_G11.report.ReservacionAmount2;
import Reto3_G11.Reto3_G11.report.ReservacionAmount3;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class ReservacionService {
    @Autowired
    ReservacionRepository reservacionRepository;
    
  public List<Reservacion> getAll() {return (List<Reservacion>) reservacionRepository.getAll();};
  
  public Optional<Reservacion> getReservacion(int id) {return reservacionRepository.getReservacion(id);};
  
  public Reservacion save(Reservacion reservacion) { 
       if (reservacion.getIdReservation()== null){
           return reservacionRepository.save(reservacion);
       }
       else
       {
          Optional<Reservacion> co =  reservacionRepository.getReservacion(reservacion.getIdReservation());
          if (co.isEmpty()){
              return reservacionRepository.save(reservacion);
          }
          else
          {
              return reservacion;
          }
       }
 
    }
  
  public Reservacion update(Reservacion reservacion){
        if(reservacion.getIdReservation()!=null){
            Optional<Reservacion> co=reservacionRepository.getReservacion(reservacion.getIdReservation());
            if(!co.isEmpty()){
                if(reservacion.getStartDate()!=null){
                    co.get().setStartDate(reservacion.getStartDate());
                }
                if(reservacion.getDevolutionDate()!=null){
                    co.get().setDevolutionDate(reservacion.getDevolutionDate());
                }
                if(reservacion.getStatus()!=null){
                    co.get().setStatus(reservacion.getStatus());
                }
                 //if(reservacion.getMotorbike()!=null){
                    //co.get().setMotorbike(reservacion.getMotorbike());
                //}
                //if(reservacion.getClient()!=null){
                    //co.get().setClient(reservacion.getClient());
                //}
                if(reservacion.getScore()!=null){
                    co.get().setScore(reservacion.getScore());
                }
                reservacionRepository.save(co.get());
                return co.get();
            }else{
                return reservacion;
            }
        }else{
            return reservacion;
        }
    }


    public boolean deleteReservacion(int reservationId) {
        Boolean aBoolean = getReservacion(reservationId).map(reservacion -> {
            reservacionRepository.delete(reservacion);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
    
    public List<ReservacionAmount1> getTopReservacionByDates(){
        List<Object[]> report1= reservacionRepository.getTopByDates();
        List<ReservacionAmount1>res1=new ArrayList<>();
        for(int i=0;i<report1.size();i++){
            res1.add(new ReservacionAmount1((Reservacion)report1.get(i)[0],(Long) report1.get(i)[1]));
        }
        return res1;
      }
    
    
    public List<ReservacionAmount2> getTopReservacionByStatus(){
        List<Object[]> report2= reservacionRepository.getTopByStatus();
        List<ReservacionAmount2>res2=new ArrayList<>();
        for(int i=0;i<report2.size();i++){
            res2.add(new ReservacionAmount2((String)report2.get(i)[0],(Long) report2.get(i)[1]));
        }
        return res2;
      }
  
    public List<ReservacionAmount3> getTopReservacionByCLient(){
        List<Object[]> report3= reservacionRepository.getTopByCLient();
        List<ReservacionAmount3>res3=new ArrayList<>();
        for(int i=0;i<report3.size();i++){
            res3.add(new ReservacionAmount3((Cliente)report3.get(i)[0],(Long) report3.get(i)[1]));
        }
        return res3;
      }
     
}
