package com.travel.board.repository;

import com.travel.board.model.BoardBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardBaseRepository extends JpaRepository<BoardBase, Long> {
    List<BoardBase> findAllByCreatorId(String creatorId);
}
