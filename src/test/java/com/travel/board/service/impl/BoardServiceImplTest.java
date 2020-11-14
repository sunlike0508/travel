package com.travel.board.service.impl;

import com.travel.board.model.BoardBase;
import com.travel.board.repository.BoardRepository;
import com.travel.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    @Test
    void selectBoardList() {
    }

    @Test
    void insertBoard() throws Exception {
        BoardBase boardBase = new BoardBase();
        boardBase.setTitle("글 작성");
        boardBase.setLocation("의정부");
        boardBase.setContents("의정부 놀러감");
        boardBase.setParties("친구들과");
        boardBase.setCreatorId("sunlike0301");
        boardBase.setStartDate(LocalDateTime.now());
        boardBase.setEndDate(LocalDateTime.now());
        boardBase.setMainPhotoPath("/photo/main/" + LocalDateTime.now());

        BoardBase savedBoardBase = boardService.insertBoard(boardBase, null);

        assertThat(boardBase.getTitle(), is(savedBoardBase.getTitle()));
        assertThat(boardBase.getLocation(), is(savedBoardBase.getLocation()));
        assertThat(boardBase.getContents(), is(savedBoardBase.getContents()));
    }

    @Test
    void updateBoard() {
    }

    @Test
    void deleteBoard() {
    }
}