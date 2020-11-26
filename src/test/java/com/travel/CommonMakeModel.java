package com.travel;

import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.dto.BoardDetailDTO;
import com.travel.board.dto.BoardFileDTO;
import com.travel.board.model.BoardBase;
import org.assertj.core.util.Lists;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CommonMakeModel {

    public MockMultipartFile getMockMultipartFile(String path, String fileName, String type) throws IOException {

        FileInputStream fileInputStream = new FileInputStream(new File(path + fileName + "." + type));

        return new MockMultipartFile(fileName, fileName + "." + type, type, fileInputStream);
    }

    public BoardBase getBoardBase() {
        BoardBase boardBase = new BoardBase();
        boardBase.setTitle("글 작성");
        boardBase.setLocation("의정부");
        boardBase.setContents("의정부 놀러감");
        boardBase.setParties("친구들과");
        boardBase.setCreatorId("sunlike0301");
        boardBase.setStartDate(LocalDateTime.now());
        boardBase.setEndDate(LocalDateTime.now());
        boardBase.setMainPhotoPath("images/818656060257300_test.jpg");
        return boardBase;
    }

    public BoardBaseDTO getBoardBaseDTO() throws IOException {
        BoardBaseDTO boardBaseDTO = new BoardBaseDTO();
        boardBaseDTO.setTitle("글 작성");
        boardBaseDTO.setLocation("의정부");
        boardBaseDTO.setContents("의정부 놀러감");
        boardBaseDTO.setParties("친구들과");
        boardBaseDTO.setCreatorId("sunlike_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        boardBaseDTO.setStartDate(LocalDateTime.now());
        boardBaseDTO.setEndDate(LocalDateTime.now());
        boardBaseDTO.setMultipartFile(getMockMultipartFile());

        BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
        boardDetailDTO.setTravelDate(LocalDateTime.now());

        BoardFileDTO boardFileDTO = new BoardFileDTO();
        boardFileDTO.setFileSize(100L);
        boardFileDTO.setMultipartFile(getMockMultipartFile());

        boardDetailDTO.setBoardFiles(Lists.newArrayList(boardFileDTO));
        boardBaseDTO.setBoardDetails(Lists.newArrayList(boardDetailDTO));

        return boardBaseDTO;
    }

    private MockMultipartFile getMockMultipartFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(new File("src/test/resources/file/origin/test.jpg"));
        return new MockMultipartFile("test", "test.jpg", "jpg", fileInputStream);
    }
}
