package com.alaa.auth2.repo;

import com.alaa.auth2.model.TransformedFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransformedFileRepository extends JpaRepository<TransformedFile, Integer> {
}
