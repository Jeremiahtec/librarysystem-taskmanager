package com.sqi.librarysystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestDTO {
    private String title;
    private String description;
    private int publicationYear;
    private String isbn;
    private int totalCopies;

    private Long authorId;
    private Long categoryId;
}