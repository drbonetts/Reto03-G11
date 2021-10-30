/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.service;


import Reto3_G11.Reto3_G11.dao.AdminRepository;
import Reto3_G11.Reto3_G11.entities.Admin;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class AdminService {
    @Autowired
    AdminRepository adminRepository;
    
  public List<Admin> getAll() {return (List<Admin>) adminRepository.getAll();};
  
  public Optional<Admin> getAdmin(int id) {return adminRepository.getAdmin(id);};
  
  public Admin save(Admin admin) { 
       if (admin.getIdAdmin()== null){
           return adminRepository.save(admin);
       }
       else
       {
          Optional<Admin> co =  adminRepository.getAdmin(admin.getIdAdmin());
          if (co.isEmpty()){
              return adminRepository.save(admin);
          }
          else
          {
              return admin;
          }
       }
 
    }
  
  public Admin update(Admin admin){
        if(admin.getIdAdmin()!=null){
            Optional<Admin> co=adminRepository.getAdmin(admin.getIdAdmin());
            if(!co.isEmpty()){
                //if(admin.getEmail()!=null){
                    //co.get().setEmail(admin.getEmail());
                //}
                if(admin.getPassword()!=null){
                    co.get().setPassword(admin.getPassword());
                }
                if(admin.getName()!=null){
                    co.get().setName(admin.getName());
                }
                adminRepository.save(co.get());
                return co.get();
            }else{
                return admin;
            }
        }else{
            return admin;
        }
    }


    public boolean deleteAdmin(int adminId) {
        Boolean aBoolean = getAdmin(adminId).map(admin -> {
            adminRepository.delete(admin);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
  
   
}
