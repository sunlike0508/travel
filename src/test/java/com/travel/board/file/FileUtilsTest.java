package com.travel.board.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;

import java.io.File;
import java.io.FileInputStream;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {

    FileUtils fileUtils;

    @BeforeEach
    public void setUp() {
        fileUtils = new FileUtils();
    }

    @Test
    public void parseFileInfo_테스트() throws Exception {

        // given
        FileInputStream fileInputStream = new FileInputStream(new File("src/test/resources/file/origin/test.jpg"));
        MockMultipartFile mockMultipartFile = new MockMultipartFile("test", "test.jpg", "jps", fileInputStream);

        // when
        String path = fileUtils.saveMultipartFile(mockMultipartFile);

        // then
        assertTrue(new File(path).exists());
    }
}