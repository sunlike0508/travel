package com.travel.board.repository;

import com.travel.CommonMakeModel;
import com.travel.board.model.BoardBase;
import com.travel.board.model.BoardDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class BoardBaseRepositoryTest {

    @Autowired
    private BoardBaseRepository boardBaseRepository;

    private CommonMakeModel commonMakeModel;

    @BeforeEach
    public void setUp() {
        commonMakeModel = new CommonMakeModel();
    }

    @Test
    public void 글_저장_테스트() {

        // given
        BoardBase boardBase = commonMakeModel.getBoardBase();

        // when
        BoardBase expectedBoardBase = boardBaseRepository.save(boardBase);

        // then
        assertThat(boardBase.getTitle(), is(expectedBoardBase.getTitle()));
        assertThat(boardBase.getLocation(), is(expectedBoardBase.getLocation()));
        assertThat(boardBase.getContents(), is(expectedBoardBase.getContents()));
    }

    @Test
    public void 글_조회_테스트() {
        BoardBase boardBase = commonMakeModel.getBoardBase();

        BoardBase saveBoardBase = boardBaseRepository.save(boardBase);
        
        BoardBase findBoardBase = boardBaseRepository.findById(saveBoardBase.getId()).orElseThrow();
        
        assertNotNull(findBoardBase);

        List<BoardBase> boardBaseList = boardBaseRepository.findAllByCreatorId(saveBoardBase.getCreatorId());

        assertNotNull(boardBaseList);
    }

    @Test
    public void 글_삭제_테스트() {

        // given
        BoardBase boardBase = commonMakeModel.getBoardBase();

        BoardBase saveBoardBase = boardBaseRepository.save(boardBase);

        BoardBase findBoardBase = boardBaseRepository.findById(saveBoardBase.getId()).orElseThrow();

        assertThat(saveBoardBase.getId(),is(findBoardBase.getId()));

        // when
        boardBaseRepository.delete(saveBoardBase);

        // then
        NoSuchElementException expectedException
                = assertThrows(NoSuchElementException.class, () -> boardBaseRepository.findById(saveBoardBase.getId()).orElseThrow());

        assertThat(expectedException.getMessage(), is("No value present"));
    }

    @Transactional
    @Test
    public void 글_detail_file_전체_조회_테스트() {
        BoardBase boardBase = boardBaseRepository.findById(141L).orElseThrow();

        System.out.println(boardBase.toString());

        List<BoardDetail> boardDetails = boardBase.getBoardDetails();

        assertNotNull(boardDetails);

        boardDetails.forEach(boardDetail -> assertNotNull(boardDetail.getBoardFiles()));
    }
}