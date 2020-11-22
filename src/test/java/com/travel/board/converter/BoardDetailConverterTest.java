package com.travel.board.converter;

import com.travel.board.dto.BoardDetailDTO;
import com.travel.board.dto.BoardFileDTO;
import com.travel.board.model.BoardDetail;
import com.travel.board.model.BoardFile;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest
class BoardDetailConverterTest {

    @Autowired
    private BoardDetailConverter boardDetailConverter;

    @Test
    void convert() {
        BoardFileDTO boardFileDTO = new BoardFileDTO();
        boardFileDTO.setId(11);

        List<BoardFileDTO> boardFileDTOList = Lists.newArrayList(boardFileDTO);

        LocalDateTime time = LocalDateTime.now();

        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
        boardDetailDTO.setBoardBaseId(1);
        boardDetailDTO.setTravelDate(LocalDateTime.now());
        boardDetailDTO.setBoardFiles(boardFileDTOList);

        BoardDetail boardDetail = boardDetailConverter.convertToDatabaseColumn(boardDetailDTO);

        assertThat(boardDetail.getTravelDate(), is(time));
    }
}