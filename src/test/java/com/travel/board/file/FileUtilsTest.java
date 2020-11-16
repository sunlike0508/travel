package com.travel.board.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockMultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;

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
        MockMultipartHttpServletRequest multipartHttpServletRequest = new MockMultipartHttpServletRequest();
        multipartHttpServletRequest.setRequestURI("/src/test/resources/file/new/");

        FileInputStream fileInputStream = new FileInputStream(new File("src/test/resources/file/origin/test.jpg"));
        MockMultipartFile mockMultipartFile = new MockMultipartFile("test", "test.jpg", "jps", fileInputStream);

        multipartHttpServletRequest.addFile(mockMultipartFile);

        // when
        String path = fileUtils.parseFileInfo(multipartHttpServletRequest);

        // then
        assertTrue(new File(path).exists());
    }
}