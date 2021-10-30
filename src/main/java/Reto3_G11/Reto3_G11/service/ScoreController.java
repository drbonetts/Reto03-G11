/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Reto3_G11.Reto3_G11.service;

import Reto3_G11.Reto3_G11.entities.Score;
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
@RequestMapping("/api/Score")
public class ScoreController {
   @Autowired
   private ScoreService scoreService;
    
   @GetMapping("/all")
   public List<Score> getScore() {return scoreService.getAll();};

   @GetMapping("/{id}")
   public Optional<Score> getScore(@PathVariable("id") int scoreId) {
        return scoreService.getScore(scoreId);
      }
   @PostMapping("/save")
   @ResponseStatus(HttpStatus.CREATED)
    public Score save(@RequestBody Score score) {
        return scoreService.save(score);
      };
    
   @PutMapping("/update")
   @ResponseStatus(HttpStatus.CREATED)
   public Score update(@RequestBody Score score) {
        return scoreService.update(score);
    }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public boolean delete(@PathVariable("id") int Id) {
        return scoreService.deleteScore(Id);
    }
        
}
