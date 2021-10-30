/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.dao;


import Reto3_G11.Reto3_G11.entities.Score;
import Reto3_G11.Reto3_G11.entities.ScoreCrud;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author diego
 */
@Repository
public class ScoreRepository {
   @Autowired
  private ScoreCrud scoreCrudRepository;
  
  public List<Score> getAll() {return (List<Score>) scoreCrudRepository.findAll();};
  
  public Optional<Score> getScore(int id) {return scoreCrudRepository.findById(id);};
  
  public Score save(Score score) { return scoreCrudRepository.save(score);};
  
  public void delete(Score score){ scoreCrudRepository.delete(score);};  
}
