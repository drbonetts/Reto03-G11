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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    
    
    public List<Reservacion> getTopReservacionByDates(String d1, String d2){
        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
        Date dateOne=new Date();
        Date dateTwo=new Date();
        try{
            dateOne=parser.parse(d1);
            dateTwo=parser.parse(d2);
        }
        catch (ParseException e){
            e.printStackTrace();
        }
        if (dateOne.before(dateTwo)){
        return reservacionRepository.getTopByDates(dateOne, dateTwo);
        }else{
            return new ArrayList<>();
        }
      }
    
    
    public ReservacionAmount2 getTopReservacionByStatus(){
        List<Reservacion> completed= reservacionRepository.getTopByStatus("completed");
        List<Reservacion> cancelled= reservacionRepository.getTopByStatus("cancelled");
        ReservacionAmount2 res=new ReservacionAmount2(completed.size(), cancelled.size());
     
        return res;
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