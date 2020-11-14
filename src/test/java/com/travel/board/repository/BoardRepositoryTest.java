package com.travel.board.repository;

import com.travel.board.model.BoardBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void 글_저장_테스트() {

        BoardBase givenBoardBase = new BoardBase();
        givenBoardBase.setTitle("글 작성");
        givenBoardBase.setLocation("의정부");
        givenBoardBase.setContents("의정부 놀러감");
        givenBoardBase.setParties("친구들과");
        givenBoardBase.setCreatorId("sunlike0301");
        givenBoardBase.setStartDate(LocalDateTime.now());
        givenBoardBase.setEndDate(LocalDateTime.now());
        givenBoardBase.setMainPhotoPath("/photo/main/" + LocalDateTime.now());

        BoardBase expectedBoardBase = boardRepository.save(givenBoardBase);

        System.out.println(expectedBoardBase.toString());

        assertThat(givenBoardBase.getTitle(), is(expectedBoardBase.getTitle()));
        assertThat(givenBoardBase.getLocation(), is(expectedBoardBase.getLocation()));
        assertThat(givenBoardBase.getContents(), is(expectedBoardBase.getContents()));
    }

    @Test
    public void 글_조회_테스트() {
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

        boardRepository.saveAll(boardBases);

        List<BoardBase> boardBaseList = boardRepository.findAllByCreatorId(creatorId);

        assertThat(boardBaseList.size(), is(5));
    }
}