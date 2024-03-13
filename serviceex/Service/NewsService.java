package com.example.serviceex.Service;

import com.example.serviceex.Model.NewArt;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class NewsService {

    ArrayList<NewArt> newArts=new ArrayList<>();

    public ArrayList<NewArt> getNews() {
        return newArts;
    }

    public void addNews(NewArt newArt){
        newArts.add(newArt);
    }

    public boolean updateNews(int id, NewArt newArt){
        for (int i = 0; i < newArts.size(); i++) {
            if(newArts.get(i).getId()==id){
               newArts.set(i,newArt);
               return true;
            }
        }
        return false;
    }

    public boolean deleteNews(int id){
        for (int i = 0; i < newArts.size(); i++) {
            if(newArts.get(i).getId()==id){
                newArts.remove(i);
                return true;
            }
        }
        return false;
    }

    public void publish(int id){

        for(NewArt n:newArts){
            if (n.getId() == id) {
        if(n.isPublished()==false)
             n.setPublished(true);
             n.setPublishedDate(LocalDate.now());
        }}}

    public ArrayList<NewArt> getAll(boolean isPublished){
        ArrayList<NewArt>newArts1=new ArrayList<>();
        for(NewArt n : newArts){
            if(n.isPublished()==true){
                newArts1.add(n);

            }
        }return newArts1;

        }

    public NewArt search(String category){

        for(NewArt n :newArts){
            if(n.getCategory().equalsIgnoreCase(category)){
                return n;
            }
        }
        return null;
    }}

