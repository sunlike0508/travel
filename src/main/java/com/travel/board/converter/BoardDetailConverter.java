package com.travel.board.converter;

import com.travel.board.dto.BoardDetailDTO;
import com.travel.board.file.FileUtils;
import com.travel.board.model.BoardBase;
import com.travel.board.model.BoardDetail;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class BoardDetailConverter implements AttributeConverter<List<BoardDetailDTO>, List<BoardDetail>> {

    private final ModelMapper modelMapper;
    private final FileUtils fileUtils;

    @Override
    public List<BoardDetail> convertToDatabaseColumn(List<BoardDetailDTO> boardDetailDTOs) {

        List<BoardDetail> boardDetails = new ArrayList<>();

        for(BoardDetailDTO boardDetailDTO : boardDetailDTOs) {
            boardDetails.add(modelMapper.map(boardDetailDTO, BoardDetail.class));
        }

        return boardDetails;
    }

    @Override
    public List<BoardDetailDTO> convertToEntityAttribute(List<BoardDetail> dbData) {
        return null;
    }
}
