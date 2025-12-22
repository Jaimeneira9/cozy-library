package com.example.demo.modelDTO;

import com.example.demo.model.googleBooks.Item;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GoogleBooksResponseDTO {
    private int totalItems;

    private List<Item> items;
}
