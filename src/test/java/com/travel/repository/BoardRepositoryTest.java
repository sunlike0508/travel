package com.travel.repository;

import com.travel.model.BoardBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


@DataJpaTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    public void test() {

        BoardBase givenBoardBase = new BoardBase();

        BoardBase expectedBoardBase = boardRepository.save(givenBoardBase);

        assertThat(givenBoardBase.getTitle(), is(expectedBoardBase.getTitle()));
    }
}