/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.service;

import Reto3_G11.Reto3_G11.dao.CategoriaRepository;
import Reto3_G11.Reto3_G11.entities.Categoria;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class CategoriaService {
    @Autowired
    CategoriaRepository categoriaRepository;
    
  public List<Categoria> getAll() {return (List<Categoria>) categoriaRepository.getAll();};
  
  public Optional<Categoria> getCategoria(int id) {return categoriaRepository.getCategoria(id);};
  
  public Categoria save(Categoria categoria) { 
       if (categoria.getId()== null){
           return categoriaRepository.save(categoria);
       }
       else
       {
          Optional<Categoria> co =  categoriaRepository.getCategoria(categoria.getId());
          if (co.isEmpty()){
              return categoriaRepository.save(categoria);
          }
          else
          {
              return categoria;
          }
       }
 
    }
  
  public Categoria update(Categoria categoria){
        if(categoria.getId()!=null){
            Optional<Categoria> co=categoriaRepository.getCategoria(categoria.getId());
            if(!co.isEmpty()){
                if(categoria.getName()!=null){
                    co.get().setName(categoria.getName());
                }
                if(categoria.getDescription()!=null){
                    co.get().setDescription(categoria.getDescription());
                }
                categoriaRepository.save(co.get());
                return co.get();
            }else{
                return categoria;
            }
        }else{
            return categoria;
        }
    }


    public boolean deleteCategoria(int categoryId) {
        Boolean aBoolean = getCategoria(categoryId).map(categoria -> {
            categoriaRepository.delete(categoria);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
