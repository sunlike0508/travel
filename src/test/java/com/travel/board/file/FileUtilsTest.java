package com.travel.board.file;

import com.travel.CommonMakeModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FileUtilsTest {

    private FileUtils fileUtils;

    private CommonMakeModel commonMakeModel;

    @BeforeEach
    public void setUp() {
        fileUtils = new FileUtils();
        commonMakeModel = new CommonMakeModel();
    }

    @Test
    public void saveMultipartFile_이미_파일이_존재하는_경우() throws IOException {

        // given
        String path = "images/";
        String fileName = "469862237954100_test";
        String type = "jpg";
        MockMultipartFile mockMultipartFile = commonMakeModel.getMockMultipartFile(path, fileName, type);

        // expected
        assertTrue(fileUtils.isExistFile(mockMultipartFile));
    }

    @Test
    public void saveMultipartFile_새로_파일_생성하는_경우_테스트() throws IOException {

        // given
        String path = "src/test/resources/file/origin/";
        String fileName = "test";
        String type = "jpg";
        MockMultipartFile mockMultipartFile = commonMakeModel.getMockMultipartFile(path, fileName, type);

        // when
        String savedPath = fileUtils.saveMultipartFile(mockMultipartFile);

        // then
        assertTrue(new File(savedPath).exists());
    }
}