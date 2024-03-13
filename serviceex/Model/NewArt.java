package com.example.serviceex.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class NewArt {
    @NotNull(message = "id cannot be null")
    private int id;
    @NotEmpty(message = "title cannot be empty")
    @Size(max = 100,message = "title max characters length is 100")
    private String title;
    @NotEmpty(message = "author cannot be empty")
    @Size(min = 4,max = 20,message = " author must be between 4 and 20 characters")
    private String author;
    @NotEmpty(message = "content cannot be empty")
    @Size(min = 20,message = " content must be more than 20 character")
    private String content;
    @NotEmpty(message = "category cannot be empty")
    @Pattern(regexp ="^(politics|sports|technology)$", message = "category must be politics, sports, or technology only")
    private String category;
    @NotEmpty(message = "imageUrl cannot be empty")
    private String imageurl;
    @AssertFalse
    private boolean isPublished;
    private LocalDate publishedDate=null;
}
