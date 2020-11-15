package com.travel.board.converter;

import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.model.BoardBase;
import com.travel.board.repository.BoardBaseRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BoardBaseConverterTest {

    @Autowired
    private BoardBaseConverter boardBaseConverter;

    @Test
    void convert_BoardBaseDTO를_BoardBase로_convert() {

        BoardBase boardBase = new BoardBase();
        boardBase.setTitle("글 작성");
        boardBase.setLocation("의정부");
        boardBase.setContents("의정부 놀러감");
        boardBase.setParties("친구들과");
        boardBase.setCreatorId("sunlike0301");
        boardBase.setStartDate(LocalDateTime.now());
        boardBase.setEndDate(LocalDateTime.now());
        boardBase.setMainPhotoPath("/photo/main/" + LocalDateTime.now());

        BoardBaseDTO boardBaseDTO = boardBaseConverter.convert(boardBase);

        assertThat(boardBaseDTO.getId(), is(boardBase.getId()));
        assertThat(boardBaseDTO.getTitle(), is(boardBase.getTitle()));
        assertThat(boardBaseDTO.getMainPhotoPath(), is(boardBase.getMainPhotoPath()));

    }

    @Test
    void convert_BoardBase를_BoardBaseDTO로_convert() {

        BoardBaseDTO boardBaseDTO = new BoardBaseDTO();
        boardBaseDTO.setTitle("글 작성");
        boardBaseDTO.setLocation("의정부");
        boardBaseDTO.setContents("의정부 놀러감");
        boardBaseDTO.setParties("친구들과");
        boardBaseDTO.setCreatorId("sunlike0301");
        boardBaseDTO.setStartDate(LocalDateTime.now());
        boardBaseDTO.setEndDate(LocalDateTime.now());
        boardBaseDTO.setMainPhotoPath("/photo/main/" + LocalDateTime.now());

        BoardBase boardBase = boardBaseConverter.convertDTO(boardBaseDTO);

        assertThat(boardBase.getId(), is(boardBaseDTO.getId()));
        assertThat(boardBase.getTitle(), is(boardBaseDTO.getTitle()));
        assertThat(boardBase.getMainPhotoPath(), is(boardBaseDTO.getMainPhotoPath()));

    }
}