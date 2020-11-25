package com.travel.board.service.impl;

import com.travel.CommonMakeModel;
import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.repository.BoardBaseRepository;
import com.travel.board.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BoardServiceImplTest {

    @Autowired
    private BoardBaseRepository boardBaseRepository;

    @Autowired
    private BoardService boardService;

    private CommonMakeModel commonMakeModel;

    @BeforeEach
    public void setUp() {
        commonMakeModel = new CommonMakeModel();
    }

    @Test
    void insertBoard() throws Exception {

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

    @Test
    void selectBoardList() {

        // TODO : 조회 만들기
//        String creatorId = "sunlike" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        List<BoardBase> boardBases = new ArrayList<>();
//
//        for(int i = 0 ; i < 5; i++){
//            BoardBase boardBase = new BoardBase();
//            boardBase.setTitle("글 작성" + i);
//            boardBase.setLocation("의정부" + i);
//            boardBase.setContents("의정부 놀러감" + i);
//            boardBase.setParties("친구들과" + i);
//            boardBase.setCreatorId(creatorId);
//            boardBase.setStartDate(LocalDateTime.now());
//            boardBase.setEndDate(LocalDateTime.now());
//            boardBase.setMainPhotoPath("/photo/main/" + LocalDateTime.now());
//
//            boardBases.add(boardBase);
//        }
//
//        boardBaseRepository.saveAll(boardBases);
//
//        List<BoardBaseDTO> boardBaseDTOs = boardService.selectBoardList(creatorId);
//
//        assertThat(boardBaseDTOs.size(), is(5));
    }

    @Test
    void deleteBoard() throws Exception {
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