package com.travel.board.repository;

import com.travel.board.model.BoardFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BoardFileRepository extends JpaRepository<BoardFile, Long> {
}
