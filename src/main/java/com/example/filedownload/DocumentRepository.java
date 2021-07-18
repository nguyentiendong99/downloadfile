package com.example.filedownload;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Documents , Integer> {
}
