package com.travel.board.converter;

import com.travel.CommonMakeModel;
import com.travel.board.dto.BoardFileDTO;
import com.travel.board.model.BoardFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BoardFileConverterTest {

    @Autowired
    private BoardFileConverter boardFileConverter;

    private CommonMakeModel commonMakeModel;

    @BeforeEach
    public void setUp() {
        commonMakeModel = new CommonMakeModel();
    }

    @Test
    void convertToDatabaseColumn() throws IOException {

        String path = "src/test/resources/file/origin/";
        String fileName = "test";
        String type = "jpg";

        BoardFileDTO boardFileDTO = new BoardFileDTO();
        boardFileDTO.setFileSize(100);
        boardFileDTO.setMultipartFile(commonMakeModel.getMockMultipartFile(path, fileName, type));

        BoardFile boardFile = boardFileConverter.convertToDatabaseColumn(boardFileDTO);

        assertNotNull(boardFile.getPhotoPath());
        assertThat(boardFile.getFileSize(), is(100L));
    }

    @Test
    void convertToEntityAttribute() {
    }
}