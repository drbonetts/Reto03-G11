/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.service;

import Reto3_G11.Reto3_G11.dao.MotocicletaRepository;
import Reto3_G11.Reto3_G11.entities.Motocicleta;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class MotocicletaService {
    @Autowired
    MotocicletaRepository motocicletaRepository;
    
  public List<Motocicleta> getAll() {return (List<Motocicleta>) motocicletaRepository.getAll();};
  
  public Optional<Motocicleta> getMotocicleta(int id) {return motocicletaRepository.getMotocicleta(id);};
  
  public Motocicleta save(Motocicleta motocicleta) { 
       if (motocicleta.getId()== null){
           return motocicletaRepository.save(motocicleta);
       }
       else
       {
          Optional<Motocicleta> co =  motocicletaRepository.getMotocicleta(motocicleta.getId());
          if (co.isEmpty()){
              return motocicletaRepository.save(motocicleta);
          }
          else
          {
              return motocicleta;
          }
       }
 
    }
  
  public Motocicleta update(Motocicleta motocicleta){
        if(motocicleta.getId()!=null){
            Optional<Motocicleta> co=motocicletaRepository.getMotocicleta(motocicleta.getId());
            if(!co.isEmpty()){
                if(motocicleta.getName()!=null){
                    co.get().setName(motocicleta.getName());
                }
                if(motocicleta.getBrand()!=null){
                    co.get().setBrand(motocicleta.getBrand());
                }
                if(motocicleta.getYear()!=null){
                    co.get().setYear(motocicleta.getYear());
                }
                if(motocicleta.getDescription()!=null){
                    co.get().setDescription(motocicleta.getDescription());
                }
                //if(motocicleta.getCategory()!=null){
                    //co.get().setCategory(motocicleta.getCategory());
                //}
                motocicletaRepository.save(co.get());
                return co.get();
            }else{
                return motocicleta;
            }
        }else{
            return motocicleta;
        }
    }


    public boolean deleteMotocicleta(int motorbikeId) {
        Boolean aBoolean = getMotocicleta(motorbikeId).map(motocicleta -> {
            motocicletaRepository.delete(motocicleta);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
  
   
}
