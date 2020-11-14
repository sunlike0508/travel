package com.travel.board.service;

import com.travel.board.model.BoardBase;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface BoardService {
    List<BoardBase> selectBoardList(String creatorId);

    BoardBase insertBoard(BoardBase boardBase, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

    void updateBoard(BoardBase boardBase, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

    void deleteBoard(long idx);
}
