package com.travel.repository;

import com.travel.model.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


@DataJpaTest
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    public void test() {

        Board givenBoard = Board.builder().build();

        Board expectedBoard = boardRepository.save(givenBoard);

        assertThat(givenBoard.getTitle(), is(expectedBoard.getTitle()));
    }
}