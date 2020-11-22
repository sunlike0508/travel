package com.travel.board.converter;

import com.travel.CommonMakeModel;
import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.dto.BoardDetailDTO;
import com.travel.board.model.BoardBase;
import com.travel.board.model.BoardDetail;
import com.travel.board.repository.BoardBaseRepository;
import com.travel.board.repository.BoardDetailRepository;
import com.travel.board.repository.BoardFileRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAmount;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardBaseConverterTest {

    @Autowired
    private BoardBaseRepository boardBaseRepository;

    @Autowired
    private BoardDetailRepository boardDetailRepository;

    @Autowired
    private BoardFileRepository boardFileRepository;

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

            boardDetailDTOList.add(boardDetailDTO);
        }

        boardBaseDTO.setBoardDetails(boardDetailDTOList);


        BoardBase boardBase = boardBaseConverter.convertToDatabaseColumn(boardBaseDTO);

        System.out.println(boardBase.toString());
        System.out.println(boardBase.getBoardDetails().toString());

        assertThat(boardBase.getTitle(), is(boardBaseDTO.getTitle()));
        assertTrue(new File(boardBase.getMainPhotoPath()).exists());
    }

    @Transactional
    @Test
    public void BoardBase를_BoardBaseDTO를_convert() {
        BoardBase findedboardBase = boardBaseRepository.findById(112L).get();

        List<BoardDetail> boardDetails = boardDetailRepository.findAllByBoardBaseId(findedboardBase.getId());

        System.out.println("111");
        System.out.println(boardDetails.get(0).toString());

        findedboardBase.setBoardDetails(boardDetails);

        BoardBaseDTO boardBaseDTO = boardBaseConverter.convertToEntityAttribute(findedboardBase);

        System.out.println("222");
        System.out.println(boardBaseDTO.toString());
    }
}