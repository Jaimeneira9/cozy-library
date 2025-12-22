package com.example.demo.model.googleBooks;

import lombok.Data;

import java.util.List;

@Data
public class VolumeInfo {
    private String title;
    private List<String> authors;
    private String publishedDate;
    private String description;
    private ImageLinks imageLinks;
    private int pagecount;


}
