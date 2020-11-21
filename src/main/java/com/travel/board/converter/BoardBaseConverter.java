package com.travel.board.converter;

import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.file.FileUtils;
import com.travel.board.model.BoardBase;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.AbstractConverter;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.persistence.AttributeConverter;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class BoardBaseConverter implements AttributeConverter<BoardBaseDTO, BoardBase> {

    private final ModelMapper modelMapper;
    private final FileUtils fileUtils;


    @SneakyThrows
    @Override
    public BoardBase convertToDatabaseColumn(BoardBaseDTO boardBaseDTO) {
        BoardBase boardBase = modelMapper.map(boardBaseDTO, BoardBase.class);

        if(!ObjectUtils.isEmpty(boardBaseDTO.getMultipartFile())
                && !fileUtils.isExistFile(boardBaseDTO.getMultipartFile())){
            boardBase.setMainPhotoPath(fileUtils.saveMultipartFile(boardBaseDTO.getMultipartFile()));
        }

        return boardBase;
    }

    @Override
    public BoardBaseDTO convertToEntityAttribute(BoardBase dbData) {
        return null;
    }
}
