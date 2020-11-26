package com.travel.board.service.impl;

import com.travel.CommonMakeModel;
import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.repository.BoardBaseRepository;
import com.travel.board.service.BoardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

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
    void insertBoardTest() throws IOException {

        // given
        BoardBaseDTO boardBaseDTO = commonMakeModel.getBoardBaseDTO();

        // when
        BoardBaseDTO saveBoardBaseDTO = boardService.insertBoard(boardBaseDTO);

        // then
        assertThat(boardBaseDTO.getTitle(), is(saveBoardBaseDTO.getTitle()));
        assertThat(boardBaseDTO.getLocation(), is(saveBoardBaseDTO.getLocation()));
        assertThat(boardBaseDTO.getContents(), is(saveBoardBaseDTO.getContents()));
        assertTrue(new File(saveBoardBaseDTO.getMainPhotoPath()).exists());
        assertTrue(new File(saveBoardBaseDTO.getBoardDetails().get(0).getBoardFiles().get(0).getPhotoPath()).exists());
    }

    @Transactional
    @Test
    void selectBoardListTest() throws IOException {

        // given
        BoardBaseDTO boardBaseDTO = commonMakeModel.getBoardBaseDTO();
        BoardBaseDTO saveBoardBaseDTO = boardService.insertBoard(boardBaseDTO);

        // when
        List<BoardBaseDTO> boardBaseDTOs = boardService.selectBoardList(saveBoardBaseDTO.getCreatorId());

        // then
        assertNotNull(boardBaseDTOs);
        assertTrue(new File(boardBaseDTOs.get(0).getMainPhotoPath()).exists());
        assertTrue(new File(boardBaseDTOs.get(0).getBoardDetails().get(0).getBoardFiles().get(0).getPhotoPath()).exists());
    }

    @Transactional
    @Test
    void updateBoardTest() throws IOException {
        // given
        BoardBaseDTO boardBaseDTO = commonMakeModel.getBoardBaseDTO();
        BoardBaseDTO saveBoardBaseDTO = boardService.insertBoard(boardBaseDTO);

        assertThat(boardBaseDTO.getTitle(), is(saveBoardBaseDTO.getTitle()));
        assertThat(boardBaseDTO.getLocation(), is(saveBoardBaseDTO.getLocation()));
        assertThat(boardBaseDTO.getContents(), is(saveBoardBaseDTO.getContents()));
        assertTrue(new File(saveBoardBaseDTO.getMainPhotoPath()).exists());
        assertTrue(new File(saveBoardBaseDTO.getBoardDetails().get(0).getBoardFiles().get(0).getPhotoPath()).exists());

        // when
        String createId = "sunlike_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        saveBoardBaseDTO.setCreatorId(createId);
        BoardBaseDTO updateBoardBaseDTO = boardService.updateBoard(saveBoardBaseDTO);

        // then
        assertNotNull(updateBoardBaseDTO);
        assertThat(updateBoardBaseDTO.getCreatorId(), is(createId));
        assertTrue(new File(updateBoardBaseDTO.getMainPhotoPath()).exists());
        assertTrue(new File(updateBoardBaseDTO.getBoardDetails().get(0).getBoardFiles().get(0).getPhotoPath()).exists());
    }

    @Transactional
    @Test
    public void deleteTest() throws IOException {
        // given
        BoardBaseDTO boardBaseDTO = commonMakeModel.getBoardBaseDTO();
        BoardBaseDTO saveBoardBaseDTO = boardService.insertBoard(boardBaseDTO);

        assertThat(boardBaseDTO.getTitle(), is(saveBoardBaseDTO.getTitle()));
        assertThat(boardBaseDTO.getLocation(), is(saveBoardBaseDTO.getLocation()));
        assertThat(boardBaseDTO.getContents(), is(saveBoardBaseDTO.getContents()));
        assertTrue(new File(saveBoardBaseDTO.getMainPhotoPath()).exists());
        assertTrue(new File(saveBoardBaseDTO.getBoardDetails().get(0).getBoardFiles().get(0).getPhotoPath()).exists());

        boardService.deleteBoard(saveBoardBaseDTO.getId(), saveBoardBaseDTO.getCreatorId());

        NoSuchElementException expectedException = assertThrows(NoSuchElementException.class,
                () -> boardBaseRepository.findById(saveBoardBaseDTO.getId()).orElseThrow());

        assertThat(expectedException.getMessage(), is("No value present"));

    }
}