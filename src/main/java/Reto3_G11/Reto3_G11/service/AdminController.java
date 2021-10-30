/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.service;

import Reto3_G11.Reto3_G11.entities.Admin;
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
@RequestMapping("/api/Admin")
public class AdminController {
   @Autowired
   private AdminService adminService;
    
   @GetMapping("/all")
   public List<Admin> getAdmin() {return adminService.getAll();};

   @GetMapping("/{id}")
   public Optional<Admin> getAdmin(@PathVariable("id") int adminId) {
        return adminService.getAdmin(adminId);
      }
   @PostMapping("/save")
   @ResponseStatus(HttpStatus.CREATED)
    public Admin save(@RequestBody Admin admin) {
        return adminService.save(admin);
      };
    
   @PutMapping("/update")
   @ResponseStatus(HttpStatus.CREATED)
   public Admin update(@RequestBody Admin admin) {
        return adminService.update(admin);
    }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public boolean delete(@PathVariable("id") int Id) {
        return adminService.deleteAdmin(Id);
    }
       
}
