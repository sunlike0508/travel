package com.travel.board.repository;

import com.travel.board.model.BoardDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardDetailRepository extends JpaRepository<BoardDetail, Long> {
    List<BoardDetail> findAllByBoardBaseId(Long boardBaseId);
}
