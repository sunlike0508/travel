package com.travel.board.converter;

import com.travel.CommonMakeModel;
import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.dto.BoardDetailDTO;
import com.travel.board.dto.BoardFileDTO;
import com.travel.board.model.BoardBase;
import com.travel.board.repository.BoardBaseRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BoardBaseConverterTest {

    @Autowired
    private BoardBaseRepository boardBaseRepository;

    @Autowired
    private BoardBaseConverter boardBaseConverter;

    private CommonMakeModel commonMakeModel;

    @BeforeEach
    public void setUp() {
        commonMakeModel = new CommonMakeModel();
    }

    @Test
    public void convert_BoardBaseDTO를_BoardBase로_convert() throws IOException {

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
        boardBaseDTO.setEndDate(LocalDateTime.now().plusDays(1));
        boardBaseDTO.setMultipartFile(commonMakeModel.getMockMultipartFile(path, fileName, type));

        List<BoardDetailDTO> boardDetailDTOList = Lists.newArrayList();

        for(int i = 0; i < 2; i++) {
            BoardDetailDTO boardDetailDTO = new BoardDetailDTO();
            boardDetailDTO.setTravelDate(LocalDateTime.now().plusDays(i));

            List<BoardFileDTO> boardFileDTOS = Lists.newArrayList();

            for(int j = 0 ; j < 2 ; j++) {
                BoardFileDTO boardFileDTO = new BoardFileDTO();
                boardFileDTO.setFileSize(100);
                boardFileDTO.setMultipartFile(commonMakeModel.getMockMultipartFile(path, fileName, type));
                boardFileDTOS.add(boardFileDTO);
            }
            boardDetailDTO.setBoardFiles(boardFileDTOS);
            boardDetailDTOList.add(boardDetailDTO);
        }

        boardBaseDTO.setBoardDetails(boardDetailDTOList);

        BoardBase boardBase = boardBaseConverter.convertToDatabaseColumn(boardBaseDTO);

        boardBase.getBoardDetails().forEach(boardDetail -> System.out.println(boardDetail.getBoardFiles().toString()));

        assertThat(boardBase.getTitle(), is(boardBaseDTO.getTitle()));
        assertTrue(new File(boardBase.getMainPhotoPath()).exists());

        BoardBase savedBoardBase = boardBaseRepository.save(boardBase);

        savedBoardBase.getBoardDetails().forEach(boardDetail -> System.out.println(boardDetail.getBoardFiles().toString()));

        assertThat(savedBoardBase.getBoardDetails().size(), is(2));
    }

    @Transactional
    @Test
    public void BoardBase를_BoardBaseDTO를_convert() {
        BoardBase findBoardBase = boardBaseRepository.findById(141L).orElseThrow();

        BoardBaseDTO boardBaseDTO = boardBaseConverter.convertToEntityAttribute(findBoardBase);

        System.out.println(boardBaseDTO.toString());

        assertTrue(new File(boardBaseDTO.getMainPhotoPath()).exists());
        assertThat(boardBaseDTO.getBoardDetails().get(0).getBoardFiles().size(), is(2));
        assertThat(boardBaseDTO.getBoardDetails().get(1).getBoardFiles().size(), is(2));
    }
}