package com.travel.board.repository;

import com.travel.board.model.BoardBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void 맴버저장_테스트() {

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
}