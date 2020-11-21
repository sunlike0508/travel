package com.travel.board.converter;

import com.travel.board.dto.BoardDetailDTO;
import com.travel.board.model.BoardDetail;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest
class BoardDetailConverterTest {

    @Autowired
    private BoardDetailConverter boardDetailConverter;

    @Test
    void convert() {

        LocalDateTime time = LocalDateTime.now();

        BoardDetailDTO boardDetailDTO = BoardDetailDTO.builder().travelDate(time).build();

        BoardDetail boardDetail = boardDetailConverter.convertToDatabaseColumn(boardDetailDTO);

        assertThat(boardDetail.getTravelDate(), is(time));
    }
}