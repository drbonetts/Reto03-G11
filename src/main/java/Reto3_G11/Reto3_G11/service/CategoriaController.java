/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.service;


import Reto3_G11.Reto3_G11.entities.Categoria;
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
@RequestMapping("/api/Category")
public class CategoriaController {
    @Autowired
   private CategoriaService categoriaService;
    
   @GetMapping("/all")
   public List<Categoria> getCategoria() {return categoriaService.getAll();};

   @GetMapping("/{id}")
   public Optional<Categoria> getCategoria(@PathVariable("id") int categoryId) {
        return categoriaService.getCategoria(categoryId);
      }
   @PostMapping("/save")
   @ResponseStatus(HttpStatus.CREATED)
    public Categoria save(@RequestBody Categoria categoria) {
        return categoriaService.save(categoria);
      };
    
   @PutMapping("/update")
   @ResponseStatus(HttpStatus.CREATED)
   public Categoria update(@RequestBody Categoria categoria) {
        return categoriaService.update(categoria);
    }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public boolean delete(@PathVariable("id") int Id) {
        return categoriaService.deleteCategoria(Id);
    }
      
}
