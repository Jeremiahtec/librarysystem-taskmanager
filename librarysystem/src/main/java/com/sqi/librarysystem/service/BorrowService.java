package com.sqi.librarysystem.service;

import com.sqi.librarysystem.model.Book;
import com.sqi.librarysystem.model.BorrowRecord;
import com.sqi.librarysystem.model.Staff;
import com.sqi.librarysystem.model.Student;
import com.sqi.librarysystem.repository.BookRepository;
import com.sqi.librarysystem.repository.BorrowRecordRepository;
import com.sqi.librarysystem.repository.StaffRepository;
import com.sqi.librarysystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class BorrowService {

    private final BorrowRecordRepository borrowRecordRepository;
    private final BookRepository bookRepository;
    private final StudentRepository studentRepository;
    private final StaffRepository staffRepository;

    @Transactional
    public BorrowRecord borrowBook(Long studentId, Long bookId, Long staffId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Student not found"));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new IllegalArgumentException("Staff not found"));

        if (book.getAvailableCopies() <= 0) {
            throw new IllegalStateException("Sorry, this book is currently out of stock!");
        }

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        bookRepository.save(book);

        BorrowRecord record = new BorrowRecord();
        record.setStudent(student);
        record.setBook(book);
        record.setIssuedBy(staff);
        record.setBorrowDate(LocalDateTime.now());
        record.setDueDate(LocalDateTime.now().plusDays(14));
        record.setStatus("BORROWED");

        return borrowRecordRepository.save(record);
    }

    @Transactional
    public BorrowRecord returnBook(Long borrowRecordId) {

        BorrowRecord record = borrowRecordRepository.findById(borrowRecordId)
                .orElseThrow(() -> new IllegalArgumentException("Borrow record not found"));

        if ("RETURNED".equals(record.getStatus())) {
            throw new IllegalStateException("This book has already been returned!");
        }

        record.setStatus("RETURNED");
        record.setReturnDate(LocalDateTime.now());

        Book book = record.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);

        bookRepository.save(book);
        return borrowRecordRepository.save(record);
    }
}