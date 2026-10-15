package com.sqi.librarysystem.controller;

import com.sqi.librarysystem.dto.BookRequestDTO;
import com.sqi.librarysystem.model.Author;
import com.sqi.librarysystem.model.Book;
import com.sqi.librarysystem.model.Category;
import com.sqi.librarysystem.repository.AuthorRepository;
import com.sqi.librarysystem.repository.BookRepository;
import com.sqi.librarysystem.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    // Notice we now accept the DTO instead of the raw Book entity
    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody BookRequestDTO dto) {

        Book newBook = new Book();
        newBook.setTitle(dto.getTitle());
        newBook.setDescription(dto.getDescription());
        newBook.setPublicationYear(dto.getPublicationYear());
        newBook.setIsbn(dto.getIsbn());
        newBook.setTotalCopies(dto.getTotalCopies());
        newBook.setAvailableCopies(dto.getTotalCopies()); // Starts with all copies available

        // Link the Author if provided
        if (dto.getAuthorId() != null) {
            Author author = authorRepository.findById(dto.getAuthorId())
                    .orElseThrow(() -> new IllegalArgumentException("Author not found"));
            newBook.setAuthor(author);
        }

        // Link the Category if provided
        if (dto.getCategoryId() != null) {
            Category category = categoryRepository.findById(dto.getCategoryId())
                    .orElseThrow(() -> new IllegalArgumentException("Category not found"));
            newBook.setCategory(category);
        }

        Book savedBook = bookRepository.save(newBook);
        return ResponseEntity.ok(savedBook);
    }
}