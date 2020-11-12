package com.travel.service;

import com.travel.model.BoardBase;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface BoardService {
    List<BoardBase> selectBoardList(String creatorId);

    void insertBoard(BoardBase boardBase, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

    void updateBoard(BoardBase boardBase, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

    void deleteBoard(long idx);
}
