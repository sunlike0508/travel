package com.travel.board.converter;

import com.travel.board.dto.BoardDetailDTO;
import com.travel.board.model.BoardDetail;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;


@Component
@RequiredArgsConstructor
public class BoardDetailConverter implements AttributeConverter<BoardDetailDTO, BoardDetail> {

    private final ModelMapper modelMapper;

    @Override
    public BoardDetail convertToDatabaseColumn(BoardDetailDTO boardDetailDTO) {

        BoardDetail boardDetail = modelMapper.map(boardDetailDTO, BoardDetail.class);

        return boardDetail;
    }


    @Override
    public BoardDetailDTO convertToEntityAttribute(BoardDetail boardDetail) {
        return null;
    }
}
