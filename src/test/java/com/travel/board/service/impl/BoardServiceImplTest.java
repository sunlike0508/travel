package com.travel.board.service.impl;

import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.model.BoardBase;
import com.travel.board.repository.BoardBaseRepository;
import com.travel.board.service.BoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class BoardServiceImplTest {

    @Autowired
    private BoardBaseRepository boardBaseRepository;

    @Autowired
    private BoardService boardService;

    @Test
    void selectBoardList() {
        String creatorId = "sunlike" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<BoardBase> boardBases = new ArrayList<>();

        for(int i = 0 ; i < 5; i++){
            BoardBase boardBase = new BoardBase();
            boardBase.setTitle("글 작성" + i);
            boardBase.setLocation("의정부" + i);
            boardBase.setContents("의정부 놀러감" + i);
            boardBase.setParties("친구들과" + i);
            boardBase.setCreatorId(creatorId);
            boardBase.setStartDate(LocalDateTime.now());
            boardBase.setEndDate(LocalDateTime.now());
            boardBase.setMainPhotoPath("/photo/main/" + LocalDateTime.now());

            boardBases.add(boardBase);
        }

        boardBaseRepository.saveAll(boardBases);

        List<BoardBaseDTO> boardBaseDTOs = boardService.selectBoardList(creatorId);

        assertThat(boardBaseDTOs.size(), is(5));
    }

    @Test
    void insertBoard() throws Exception {
        BoardBaseDTO boardBaseDTO = new BoardBaseDTO();
        boardBaseDTO.setTitle("글 작성");
        boardBaseDTO.setLocation("의정부");
        boardBaseDTO.setContents("의정부 놀러감");
        boardBaseDTO.setParties("친구들과");
        boardBaseDTO.setCreatorId("sunlike0301");
        boardBaseDTO.setStartDate(LocalDateTime.now());
        boardBaseDTO.setEndDate(LocalDateTime.now());
        boardBaseDTO.setMainPhotoPath("/photo/main/" + LocalDateTime.now());

        BoardBaseDTO savedBoardBaseDTO = boardService.insertBoard(boardBaseDTO, null);

        assertThat(boardBaseDTO.getTitle(), is(savedBoardBaseDTO.getTitle()));
        assertThat(boardBaseDTO.getLocation(), is(savedBoardBaseDTO.getLocation()));
        assertThat(boardBaseDTO.getContents(), is(savedBoardBaseDTO.getContents()));
    }

    @Test
    void deleteBoard() throws Exception {
        BoardBaseDTO boardBaseDTO = new BoardBaseDTO();
        boardBaseDTO.setTitle("글 작성");
        boardBaseDTO.setLocation("의정부");
        boardBaseDTO.setContents("의정부 놀러감");
        boardBaseDTO.setParties("친구들과");
        boardBaseDTO.setCreatorId("sunlike0301");
        boardBaseDTO.setStartDate(LocalDateTime.now());
        boardBaseDTO.setEndDate(LocalDateTime.now());
        boardBaseDTO.setMainPhotoPath("/photo/main/" + LocalDateTime.now());

        BoardBaseDTO savedBoardBaseDTO = boardService.insertBoard(boardBaseDTO, null);

        BoardBase findBoardBase = boardBaseRepository.findById(savedBoardBaseDTO.getId()).get();

        assertThat(savedBoardBaseDTO.getId(), is(findBoardBase.getId()));

        // when
        boardService.deleteBoard(savedBoardBaseDTO.getId());


        // then
        NoSuchElementException expectedException = assertThrows(NoSuchElementException.class,
                () -> boardBaseRepository.findById(savedBoardBaseDTO.getId()).get());

        assertThat(expectedException.getMessage(), is("No value present"));
    }
}