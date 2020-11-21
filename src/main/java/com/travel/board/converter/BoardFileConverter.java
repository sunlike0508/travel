package com.travel.board.converter;

import com.travel.board.dto.BoardFileDTO;
import com.travel.board.file.FileUtils;
import com.travel.board.model.BoardFile;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.persistence.AttributeConverter;


@Component
@RequiredArgsConstructor
public class BoardFileConverter implements AttributeConverter<BoardFileDTO, BoardFile> {

    private final ModelMapper modelMapper;
    private final FileUtils fileUtils;

    @SneakyThrows
    @Override
    public BoardFile convertToDatabaseColumn(BoardFileDTO boardFileDTO) {
        BoardFile boardFile = modelMapper.map(boardFileDTO, BoardFile.class);

        if(!ObjectUtils.isEmpty(boardFileDTO.getMultipartFile())
                && !fileUtils.isExistFile(boardFileDTO.getMultipartFile())){
            boardFile.setPhotoPath(fileUtils.saveMultipartFile(boardFileDTO.getMultipartFile()));
        }

        return boardFile;
    }

    @Override
    public BoardFileDTO convertToEntityAttribute(BoardFile boardFile) {
        return null;
    }
}
