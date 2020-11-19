package com.travel;

import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class CommonMakeModel {

    public MockMultipartFile getMockMultipartFile(String path, String fileName, String type) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File(path + fileName + "." + type));
        MockMultipartFile mockMultipartFile
                = new MockMultipartFile(fileName, fileName + "." + type, type, fileInputStream);

        return mockMultipartFile;
    }
}
