package com.travel.board.service.impl;

import com.travel.CommonMakeModel;
import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BoardServiceImplTest {

    @Autowired
    private BoardService boardService;

    private CommonMakeModel commonMakeModel;

    @BeforeEach
    public void setUp() {
        commonMakeModel = new CommonMakeModel();
    }

    @Test
    void insertBoard() throws IOException {

        // given
        BoardBaseDTO boardBaseDTO = commonMakeModel.getBoardBaseDTO();

        // when
        BoardBaseDTO saveBoardBaseDTO = boardService.insertBoard(boardBaseDTO);

        // then
        assertThat(boardBaseDTO.getTitle(), is(saveBoardBaseDTO.getTitle()));
        assertThat(boardBaseDTO.getLocation(), is(saveBoardBaseDTO.getLocation()));
        assertThat(boardBaseDTO.getContents(), is(saveBoardBaseDTO.getContents()));
        assertTrue(new File(saveBoardBaseDTO.getMainPhotoPath()).exists());
        assertTrue(new File(boardBaseDTO.getBoardDetails().get(0).getBoardFiles().get(0).getPhotoPath()).exists());
    }

    @Transactional
    @Test
    void selectBoardList() throws IOException {

        // given
        BoardBaseDTO boardBaseDTO = commonMakeModel.getBoardBaseDTO();
        BoardBaseDTO saveBoardBaseDTO = boardService.insertBoard(boardBaseDTO);

        // when
        List<BoardBaseDTO> boardBaseDTOs = boardService.selectBoardList(saveBoardBaseDTO.getCreatorId());

        // then
        assertNotNull(boardBaseDTOs);
        assertTrue(new File(boardBaseDTOs.get(0).getMainPhotoPath()).exists());
        assertTrue(new File(boardBaseDTO.getBoardDetails().get(0).getBoardFiles().get(0).getPhotoPath()).exists());
    }

    @Test
    void deleteBoard() {
        // TODO : 삭제 만들기
//        BoardBaseDTO boardBaseDTO = new BoardBaseDTO();
//        boardBaseDTO.setTitle("글 작성");
//        boardBaseDTO.setLocation("의정부");
//        boardBaseDTO.setContents("의정부 놀러감");
//        boardBaseDTO.setParties("친구들과");
//        boardBaseDTO.setCreatorId("sunlike0301");
//        boardBaseDTO.setStartDate(LocalDateTime.now());
//        boardBaseDTO.setEndDate(LocalDateTime.now());
//
//        BoardBaseDTO savedBoardBaseDTO = boardService.insertBoard(boardBaseDTO);
//
//        BoardBase findBoardBase = boardBaseRepository.findById(savedBoardBaseDTO.getId()).get();
//
//        assertThat(savedBoardBaseDTO.getId(), is(findBoardBase.getId()));
//
//        // when
//        boardService.deleteBoard(savedBoardBaseDTO.getId());
//
//
//        // then
//        NoSuchElementException expectedException = assertThrows(NoSuchElementException.class,
//                () -> boardBaseRepository.findById(savedBoardBaseDTO.getId()).get());
//
//        assertThat(expectedException.getMessage(), is("No value present"));
    }
}