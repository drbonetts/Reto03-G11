/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.service;


import Reto3_G11.Reto3_G11.entities.Cliente;
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
@RequestMapping("/api/Client")
public class ClienteController {
    @Autowired
   private ClienteService clienteService;
    
   @GetMapping("/all")
   public List<Cliente> getCliente() {return clienteService.getAll();};

   @GetMapping("/{id}")
   public Optional<Cliente> getCliente(@PathVariable("id") int clientId) {
        return clienteService.getCliente(clientId);
      }
   @PostMapping("/save")
   @ResponseStatus(HttpStatus.CREATED)
   public Cliente save(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
      };
   
    @PutMapping("/update")
   @ResponseStatus(HttpStatus.CREATED)
   public Cliente update(@RequestBody Cliente cliente) {
        return clienteService.update(cliente);
    }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public boolean delete(@PathVariable("id") int Id) {
        return clienteService.deleteCliente(Id);
    }
        
}
