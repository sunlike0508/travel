package com.travel.service;

import com.travel.model.Board;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface BoardService {
    List<Board> selectBoardList(String creatorId);

    void insertBoard(Board board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

    void updateBoard(Board board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

    void deleteBoard(long idx);
}
