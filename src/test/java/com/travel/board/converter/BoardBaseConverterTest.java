package com.travel.board.converter;

import com.travel.CommonMakeModel;
import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.model.BoardBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardBaseConverterTest {

    @Autowired
    private BoardBaseConverter boardBaseConverter;

    private CommonMakeModel commonMakeModel;

    @BeforeEach
    public void setUp() {
        commonMakeModel = new CommonMakeModel();
    }

    @Test
    void convert_BoardBaseDTO를_BoardBase로_convert() throws IOException {

        String path = "src/test/resources/file/origin/";
        String fileName = "test";
        String type = "jpg";

        BoardBaseDTO boardBaseDTO = new BoardBaseDTO();
        boardBaseDTO.setTitle("글 작성");
        boardBaseDTO.setLocation("의정부");
        boardBaseDTO.setContents("의정부 놀러감");
        boardBaseDTO.setParties("친구들과");
        boardBaseDTO.setCreatorId("sunlike0301");
        boardBaseDTO.setStartDate(LocalDateTime.now());
        boardBaseDTO.setEndDate(LocalDateTime.now());
        boardBaseDTO.setMultipartFile(commonMakeModel.getMockMultipartFile(path, fileName, type));

        BoardBase boardBase = boardBaseConverter.convert(boardBaseDTO);

        assertThat(boardBase.getTitle(), is(boardBaseDTO.getTitle()));
        assertTrue(new File(boardBase.getMainPhotoPath()).exists());
    }
}