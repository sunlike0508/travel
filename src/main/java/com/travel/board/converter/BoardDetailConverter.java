package com.travel.board.converter;

import com.travel.board.dto.BoardDetailDTO;
import com.travel.board.file.FileUtils;
import com.travel.board.model.BoardDetail;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class BoardDetailConverter implements Converter<List<BoardDetailDTO>, List<BoardDetail>> {

    private final ModelMapper modelMapper;
    private final FileUtils fileUtils;

    @Override
    public List<BoardDetail> convert(List<BoardDetailDTO> boardDetailDTOs) {

        List<BoardDetail> boardDetails = new ArrayList<>();

        for(BoardDetailDTO boardDetailDTO : boardDetailDTOs) {
            boardDetails.add(modelMapper.map(boardDetailDTO, BoardDetail.class));
        }

        return boardDetails;
    }
}
