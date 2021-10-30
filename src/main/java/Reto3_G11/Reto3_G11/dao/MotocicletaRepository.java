/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.dao;

import Reto3_G11.Reto3_G11.entities.Motocicleta;
import Reto3_G11.Reto3_G11.entities.MotocicletaCrud;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
public class MotocicletaRepository {
    @Autowired
  private MotocicletaCrud motocicletaCrudRepository;
  
  public List<Motocicleta> getAll() {return (List<Motocicleta>) motocicletaCrudRepository.findAll();};
  
  public Optional<Motocicleta> getMotocicleta(int id) {return motocicletaCrudRepository.findById(id);};
  
  public Motocicleta save(Motocicleta motocicleta) { return motocicletaCrudRepository.save(motocicleta);};
  
  public void delete(Motocicleta motocicleta){ motocicletaCrudRepository.delete(motocicleta);}; 
}
