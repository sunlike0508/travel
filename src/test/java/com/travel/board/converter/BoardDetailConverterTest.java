package com.travel.board.converter;

import com.travel.CommonMakeModel;
import com.travel.board.dto.BoardDetailDTO;
import com.travel.board.model.BoardDetail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@SpringBootTest
class BoardDetailConverterTest {

    @Autowired
    private BoardDetailConverter boardDetailConverter;

    @Test
    void convert() {

        List<BoardDetailDTO> boardDetailDTOS = new ArrayList<>();

        for(int i = 1; i < 5; i++){
            BoardDetailDTO boardDetailDTO = BoardDetailDTO.builder().travelDate(LocalDateTime.now()).boardBaseId(i).build();
            boardDetailDTOS.add(boardDetailDTO);
        }


        List<BoardDetail> boardDetails = boardDetailConverter.convertToDatabaseColumn(boardDetailDTOS);

        assertThat(boardDetails.size(), is(4));
    }
}