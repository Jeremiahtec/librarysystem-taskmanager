package com.sqi.librarysystem.controller;

import com.sqi.librarysystem.model.BorrowRecord;
import com.sqi.librarysystem.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transactions")
@RequiredArgsConstructor
public class BorrowController {

    private final BorrowService borrowService;

    @PostMapping("/borrow")
    public ResponseEntity<?> borrowBook(
            @RequestParam Long studentId,
            @RequestParam Long bookId,
            @RequestParam Long staffId) {

        try {
            BorrowRecord record = borrowService.borrowBook(studentId, bookId, staffId);
            return ResponseEntity.ok(record);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/return/{recordId}")
    public ResponseEntity<?> returnBook(@PathVariable Long recordId) {

        try {
            BorrowRecord updatedRecord = borrowService.returnBook(recordId);
            return ResponseEntity.ok(updatedRecord);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}