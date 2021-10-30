/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.service;

import Reto3_G11.Reto3_G11.dao.MensajeRepository;
import Reto3_G11.Reto3_G11.entities.Mensaje;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class MensajeService {
    @Autowired
    MensajeRepository mensajeRepository;
    
  public List<Mensaje> getAll() {return (List<Mensaje>) mensajeRepository.getAll();};
  
  public Optional<Mensaje> getMensaje(int id) {return mensajeRepository.getMensaje(id);};
  
  public Mensaje save(Mensaje mensaje) { 
       if (mensaje.getIdMessage()== null){
           return mensajeRepository.save(mensaje);
       }
       else
       {
          Optional<Mensaje> co =  mensajeRepository.getMensaje(mensaje.getIdMessage());
          if (co.isEmpty()){
              return mensajeRepository.save(mensaje);
          }
          else
          {
              return mensaje;
          }
       }
 
    }
  
  public Mensaje update(Mensaje mensaje){
        if(mensaje.getIdMessage()!=null){
            Optional<Mensaje> co=mensajeRepository.getMensaje(mensaje.getIdMessage());
            if(!co.isEmpty()){
                if(mensaje.getMessageText()!=null){
                    co.get().setMessageText(mensaje.getMessageText());
                }
                //if(mensaje.getMotorbike()!=null){
                    //co.get().setMotorbike(mensaje.getMotorbike());
                //}
                if(mensaje.getClient()!=null){
                    co.get().setClient(mensaje.getClient());
                }
                mensajeRepository.save(co.get());
                return co.get();
            }else{
                return mensaje;
            }
        }else{
            return mensaje;
        }
    }


    public boolean deleteMensaje(int messageId) {
        Boolean aBoolean = getMensaje(messageId).map(mensaje -> {
            mensajeRepository.delete(mensaje);
            return true;
        }).orElse(false);
        return aBoolean;
    }
  
   
}
