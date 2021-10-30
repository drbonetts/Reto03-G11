/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.dao;


import Reto3_G11.Reto3_G11.entities.Mensaje;
import Reto3_G11.Reto3_G11.entities.MensajeCrud;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
public class MensajeRepository {
    @Autowired
  private MensajeCrud mensajeCrudRepository;
  
  public List<Mensaje> getAll() {return (List<Mensaje>) mensajeCrudRepository.findAll();};
  
  public Optional<Mensaje> getMensaje(int id) {return mensajeCrudRepository.findById(id);};
  
  public Mensaje save(Mensaje mensaje) { return mensajeCrudRepository.save(mensaje);};
  
  public void delete(Mensaje mensaje){ mensajeCrudRepository.delete(mensaje);}; 
}
