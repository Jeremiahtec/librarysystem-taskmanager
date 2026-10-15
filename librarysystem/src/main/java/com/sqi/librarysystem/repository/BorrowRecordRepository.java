package com.sqi.librarysystem.repository;

import com.sqi.librarysystem.model.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowRecordRepository extends JpaRepository<BorrowRecord, Long> {

    List<BorrowRecord> findByStudentId(Long studentId);

    Optional<BorrowRecord> findByBookIdAndStudentIdAndStatus(Long bookId, Long studentId, String status);
}