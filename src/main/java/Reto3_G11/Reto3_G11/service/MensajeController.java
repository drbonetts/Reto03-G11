/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.service;


import Reto3_G11.Reto3_G11.entities.Mensaje;
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
@RequestMapping("/api/Message")
public class MensajeController {
    @Autowired
   private MensajeService mensajeService;
    
   @GetMapping("/all")
   public List<Mensaje> getMensaje() {return mensajeService.getAll();};

   @GetMapping("/{id}")
   public Optional<Mensaje> getMensaje(@PathVariable("id") int messageId) {
        return mensajeService.getMensaje(messageId);
      }
   @PostMapping("/save")
   @ResponseStatus(HttpStatus.CREATED)
    public Mensaje save(@RequestBody Mensaje mensaje) {
        return mensajeService.save(mensaje);
      };
   
   @PutMapping("/update")
   @ResponseStatus(HttpStatus.CREATED)
   public Mensaje update(@RequestBody Mensaje mensaje) {
        return mensajeService.update(mensaje);
    }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public boolean delete(@PathVariable("id") int Id) {
        return mensajeService.deleteMensaje(Id);
    }
         
}
