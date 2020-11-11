package com.travel.repository;

import com.travel.model.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {
}
