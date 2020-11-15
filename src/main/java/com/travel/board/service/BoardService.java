package com.travel.board.service;

import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.model.BoardBase;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface BoardService {
    List<BoardBaseDTO> selectBoardList(String creatorId);

    BoardBaseDTO insertBoard(BoardBaseDTO boardBaseDTO, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

    BoardBaseDTO updateBoard(BoardBaseDTO boardBaseDTO, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

    void deleteBoard(long idx);
}
