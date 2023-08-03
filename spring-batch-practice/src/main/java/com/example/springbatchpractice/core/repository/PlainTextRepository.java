package com.example.springbatchpractice.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbatchpractice.core.domain.PlainText;

public interface PlainTextRepository extends JpaRepository<PlainText, Integer> {
	Page<PlainText> findBy(Pageable pageable);
}
