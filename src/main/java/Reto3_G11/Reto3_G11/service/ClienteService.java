/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.service;

import Reto3_G11.Reto3_G11.dao.ClienteRepository;
import Reto3_G11.Reto3_G11.entities.Cliente;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class ClienteService {
    @Autowired
    ClienteRepository clienteRepository;
    
  public List<Cliente> getAll() {return (List<Cliente>) clienteRepository.getAll();};
  
  public Optional<Cliente> getCliente(int id) {return clienteRepository.getCliente(id);};
  
  public Cliente save(Cliente cliente) { 
       if (cliente.getIdClient()== null){
           return clienteRepository.save(cliente);
       }
       else
       {
          Optional<Cliente> co =  clienteRepository.getCliente(cliente.getIdClient());
          if (co.isEmpty()){
              return clienteRepository.save(cliente);
          }
          else
          {
              return cliente;
          }
       }
 
    }
  
  public Cliente update(Cliente cliente){
        if(cliente.getIdClient()!=null){
            Optional<Cliente> co=clienteRepository.getCliente(cliente.getIdClient());
            if(!co.isEmpty()){
                //if(cliente.getEmail()!=null){
                    //co.get().setEmail(cliente.getEmail());
                //}
                if(cliente.getPassword()!=null){
                    co.get().setPassword(cliente.getPassword());
                }
                if(cliente.getName()!=null){
                    co.get().setName(cliente.getName());
                }
                if(cliente.getAge()!=null){
                    co.get().setAge(cliente.getAge());
                }
                clienteRepository.save(co.get());
                return co.get();
            }else{
                return cliente;
            }
        }else{
            return cliente;
        }
    }


    public boolean deleteCliente(int clientId) {
        Boolean aBoolean = getCliente(clientId).map(cliente -> {
            clienteRepository.delete(cliente);
            return true;
        }).orElse(false);
        return aBoolean;
    }
  
   
}
