/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.service;

import Reto3_G11.Reto3_G11.entities.Motocicleta;
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
@RequestMapping("/api/Motorbike")
public class MotocicletaController {
   @Autowired
   private MotocicletaService motocicletaService;
    
   @GetMapping("/all")
   public List<Motocicleta> getMotocicleta() {return motocicletaService.getAll();};

   @GetMapping("/{id}")
   public Optional<Motocicleta> getMotocicleta(@PathVariable("id") int motorbikeId) {
        return motocicletaService.getMotocicleta(motorbikeId);
      }
   @PostMapping("/save")
   @ResponseStatus(HttpStatus.CREATED)
    public Motocicleta save(@RequestBody Motocicleta motocicleta) {
        return motocicletaService.save(motocicleta);
      };
    
   @PutMapping("/update")
   @ResponseStatus(HttpStatus.CREATED)
   public Motocicleta update(@RequestBody Motocicleta motocicleta) {
        return motocicletaService.update(motocicleta);
    }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public boolean delete(@PathVariable("id") int Id) {
        return motocicletaService.deleteMotocicleta(Id);
    }
      
}
