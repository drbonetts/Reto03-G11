/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.dao;


import Reto3_G11.Reto3_G11.entities.Categoria;
import Reto3_G11.Reto3_G11.entities.CategoriaCrud;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
public class CategoriaRepository {
    @Autowired
  private CategoriaCrud categoriaCrudRepository;
  
  public List<Categoria> getAll() {return (List<Categoria>) categoriaCrudRepository.findAll();};
  
  public Optional<Categoria> getCategoria(int id) {return categoriaCrudRepository.findById(id);};
  
  public Categoria save(Categoria categoria) { return categoriaCrudRepository.save(categoria);};
  
  public void delete(Categoria categoria){ categoriaCrudRepository.delete(categoria);};
}
