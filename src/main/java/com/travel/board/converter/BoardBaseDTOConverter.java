package com.travel.board.converter;

import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.file.FileUtils;
import com.travel.board.model.BoardBase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class BoardBaseDTOConverter implements Converter<BoardBaseDTO, BoardBase> {

    private final ModelMapper modelMapper;
    private final FileUtils fileUtils;

    @SneakyThrows
    @Override
    public BoardBase convert(BoardBaseDTO boardBaseDTO) {

        BoardBase boardBase = modelMapper.map(boardBaseDTO, BoardBase.class);

        if(!ObjectUtils.isEmpty(boardBaseDTO.getMultipartFile())){
            boardBase.setMainPhotoPath(fileUtils.saveMultipartFile(boardBaseDTO.getMultipartFile()));
        }

        // TODO : boardBaseDetail, boardFile 가져오기

        return boardBase;
    }
}
