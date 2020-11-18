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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileInputStream;


@Component
@RequiredArgsConstructor
public class BoardBaseConverter implements Converter<BoardBase, BoardBaseDTO> {

    private final ModelMapper modelMapper;
    private final FileUtils fileUtils;

    @SneakyThrows
    @Override
    public BoardBaseDTO convert(BoardBase boardBase) {

        BoardBaseDTO boardBaseDTO = modelMapper.map(boardBase, BoardBaseDTO.class);
        //FileInputStream fileInputStream = new FileInputStream(new File(boardBase.getMainPhotoPath()));
        // 파일 다운로드 기능 추가
        //boardBaseDTO.setMultipartFile();

        // TODO : boardBaseDetail, boardFile 가져오기

        return boardBaseDTO;
    }

    public BoardBase convertDTO(BoardBaseDTO boardBaseDTO) throws Exception {

        BoardBase boardBase = modelMapper.map(boardBaseDTO, BoardBase.class);

        if(!ObjectUtils.isEmpty(boardBaseDTO.getMultipartFile())){
            boardBase.setMainPhotoPath(fileUtils.saveMultipartFile(boardBaseDTO.getMultipartFile()));
        }

        // TODO : boardBaseDetail, boardFile 가져오기

        return boardBase;
    }
}
