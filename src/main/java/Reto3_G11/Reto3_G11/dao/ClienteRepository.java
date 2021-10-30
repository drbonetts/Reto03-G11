/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.dao;


import Reto3_G11.Reto3_G11.entities.Cliente;
import Reto3_G11.Reto3_G11.entities.ClienteCrud;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
public class ClienteRepository {
    @Autowired
  private ClienteCrud clienteCrudRepository;
  
  public List<Cliente> getAll() {return (List<Cliente>) clienteCrudRepository.findAll();};
  
  public Optional<Cliente> getCliente(int id) {return clienteCrudRepository.findById(id);};
  
  public Cliente save(Cliente cliente) { return clienteCrudRepository.save(cliente);};
  
  public void delete(Cliente cliente){ clienteCrudRepository.delete(cliente);}; 
}
