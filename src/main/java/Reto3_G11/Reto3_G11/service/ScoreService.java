/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.service;


import Reto3_G11.Reto3_G11.dao.ScoreRepository;
import Reto3_G11.Reto3_G11.entities.Score;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author diego
 */
@Service
public class ScoreService {
     @Autowired
    ScoreRepository scoreRepository;
    
  public List<Score> getAll() {return (List<Score>) scoreRepository.getAll();};
  
  public Optional<Score> getScore(int id) {return scoreRepository.getScore(id);};
  
  public Score save(Score score) { 
       if (score.getIdScore()== null){
           return scoreRepository.save(score);
       }
       else
       {
          Optional<Score> co =  scoreRepository.getScore(score.getIdScore());
          if (co.isEmpty()){
              return scoreRepository.save(score);
          }
          else
          {
              return score;
          }
       }
 
    }
  
  public Score update(Score score){
        if(score.getIdScore()!=null){
            Optional<Score> co=scoreRepository.getScore(score.getIdScore());
            if(!co.isEmpty()){
                if(score.getMessageText()!=null){
                    co.get().setMessageText(score.getMessageText());
                }
                if(score.getQualification()!=null){
                    co.get().setQualification(score.getQualification());
                }
                if(score.getReservations()!=null){
                    co.get().setReservations(score.getReservations());
                }
                scoreRepository.save(co.get());
                return co.get();
            }else{
                return score;
            }
        }else{
            return score;
        }
    }


    public boolean deleteScore(int scoreId) {
        Boolean aBoolean = getScore(scoreId).map(score -> {
            scoreRepository.delete(score);
            return true;
        }).orElse(false);
        return aBoolean;
    }
    
  
   
}
