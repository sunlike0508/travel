package com.travel.board.repository;

import com.travel.board.model.BoardDetail;
import com.travel.board.model.BoardFile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardFileRepositoryTest {

    @Autowired
    private BoardFileRepository boardFileRepository;

    @Test
    public void BoardFile_저장_테스트() {
        BoardDetail boardDetail = new BoardDetail();
        boardDetail.setId(1);

        BoardFile boardFile = new BoardFile();
        boardFile.setBoardDetail(boardDetail);
        boardFile.setPhotoPath("images/469862237954100_test.jpg");
        boardFile.setFileSize(100);
        BoardFile savedBoardFile = boardFileRepository.save(boardFile);

        assertThat(savedBoardFile.getPhotoPath(), is("images/469862237954100_test.jpg"));
        assertThat(savedBoardFile.getFileSize(), is(100L));
    }
}