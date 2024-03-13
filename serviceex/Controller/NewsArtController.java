package com.example.serviceex.Controller;

import com.example.serviceex.ApiResponce.ApiResponce;
import com.example.serviceex.Model.NewArt;
import com.example.serviceex.Model.NewArt;
import com.example.serviceex.Service.NewsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/news")
@RequiredArgsConstructor
public class NewsArtController {

   private final NewsService newsService;

   @GetMapping("/get")
   public ResponseEntity getNews(){
      ArrayList<NewArt> news=newsService.getNews();
      return ResponseEntity.status(200).body(news);
   }

   @PostMapping("/add")
   public ResponseEntity addNews(@RequestBody @Valid NewArt newArt, Errors errors){
      if(errors.hasErrors()){
         String message=errors.getFieldError().getDefaultMessage();
         return ResponseEntity.status(400).body(message);
      }
      newsService.addNews(newArt);
      return ResponseEntity.status(200).body(new ApiResponce("news added"));
   }

   @PutMapping("/update/{id}")
   public ResponseEntity updateNews(@PathVariable int id,@RequestBody @Valid NewArt newArt,Errors errors){
      if(errors.hasErrors()){
         String message=errors.getFieldError().getDefaultMessage();
         return ResponseEntity.status(400).body(message);
      }
      boolean isUpdate = newsService.updateNews(id,newArt);
      if(isUpdate){
         return ResponseEntity.status(200).body(new ApiResponce("news updated"));
      }
      return ResponseEntity.status(400).body(new ApiResponce("new not found"));

   }

   @DeleteMapping("/delete/{id}")
   public ResponseEntity deleteNews(@PathVariable int id){
      boolean isDeleted=newsService.deleteNews(id);
      if(isDeleted){
         return ResponseEntity.status(200).body(new ApiResponce("news deleted"));
      }
      return ResponseEntity.status(400).body(new ApiResponce("new not found"));
   }

   @PutMapping("/publish/{id}")
    public ResponseEntity publish(@PathVariable int id){
       newsService.publish(id);
       return ResponseEntity.status(200).body("is published");
    }

    @GetMapping("getall/{ispublished}")
    public ResponseEntity allPublish(boolean ispublished){
      ArrayList<NewArt> getAll=newsService.getAll(ispublished);
      if(getAll==null){
         return ResponseEntity.status(400).body("not found");
      }
      return ResponseEntity.status(200).body(getAll);
    }

   @GetMapping("/search/{category}")
   public ResponseEntity search(@PathVariable String category){
      NewArt search= newsService.search(category);
      if(search==null){
         return ResponseEntity.status(400).body("not found");
      }
      return ResponseEntity.status(200).body(search);
   }


}
