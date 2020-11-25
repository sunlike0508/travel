package com.travel.board.repository;

import com.travel.board.model.BoardBase;
import com.travel.board.model.BoardDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest
class BoardDetailRepositoryTest {

    @Autowired
    private BoardBaseRepository boardBaseRepository;

    @Autowired
    private BoardDetailRepository boardDetailRepository;


    @Test
    public void 글_상세_저장_테스트() {

        LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        BoardBase boardBase = new BoardBase();
        boardBase.setTitle("글 작성");
        boardBase.setLocation("의정부");
        boardBase.setContents("의정부 놀러감");
        boardBase.setParties("친구들과");
        boardBase.setCreatorId("sunlike0301");
        boardBase.setStartDate(time);
        boardBase.setEndDate(time.plusDays(3));
        boardBase.setMainPhotoPath("images/818656060257300_test.jpg");

        BoardBase savedBoardBase = boardBaseRepository.save(boardBase);

        BoardDetail boardDetail = new BoardDetail();
        boardDetail.setTravelDate(time);
        boardDetail.setBoardBase(savedBoardBase);

        BoardDetail savedBoardDetail = boardDetailRepository.save(boardDetail);

        assertThat(savedBoardDetail.getTravelDate(), is(boardDetail.getTravelDate()));
        assertThat(savedBoardBase.getId(), is(boardDetail.getBoardBase().getId()));
    }
}