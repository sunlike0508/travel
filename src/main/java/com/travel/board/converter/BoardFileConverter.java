package com.travel.board.converter;

import com.travel.board.dto.BoardFileDTO;
import com.travel.board.file.FileUtils;
import com.travel.board.model.BoardFile;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@RequiredArgsConstructor
public class BoardFileConverter implements Converter<List<BoardFileDTO>, List<BoardFile>> {

    private final ModelMapper modelMapper;
    private final FileUtils fileUtils;

    @Override
    public List<BoardFile> convert(List<BoardFileDTO> boardFileDTOs) {

        List<BoardFile> boardFiles = new ArrayList<>();

        for(BoardFileDTO boardFileDTO : boardFileDTOs) {
            boardFiles.add(modelMapper.map(boardFileDTO, BoardFile.class));
        }

        return boardFiles;
    }
}
