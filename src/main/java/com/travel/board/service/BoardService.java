package com.travel.board.service;

import com.travel.board.dto.BoardBaseDTO;

import java.util.List;

public interface BoardService {
    List<BoardBaseDTO> selectBoardList(String creatorId);

    BoardBaseDTO insertBoard(BoardBaseDTO boardBaseDTO);

    BoardBaseDTO updateBoard(BoardBaseDTO boardBaseDTO);

    void deleteBoard(long idx);
}
