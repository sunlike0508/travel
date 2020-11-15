package com.travel.board.converter;

import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.model.BoardBase;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class BoardBaseConverter implements Converter<BoardBase, BoardBaseDTO> {

    private final ModelMapper modelMapper;

    @Override
    public BoardBaseDTO convert(BoardBase boardBase) {

        BoardBaseDTO boardBaseDTO = modelMapper.map(boardBase, BoardBaseDTO.class);

        // TODO : boardBaseDetail, boardFile 가져오기

        return boardBaseDTO;
    }

    public BoardBase convertDTO(BoardBaseDTO boardBaseDTO) {

        BoardBase boardBase = modelMapper.map(boardBaseDTO, BoardBase.class);

        // TODO : boardBaseDetail, boardFile 가져오기

        return boardBase;
    }
}
