package com.travel.board.converter;

import com.google.common.collect.Lists;
import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.dto.BoardDetailDTO;
import com.travel.board.dto.BoardFileDTO;
import com.travel.board.file.FileUtils;
import com.travel.board.model.BoardBase;
import com.travel.board.model.BoardDetail;
import com.travel.board.model.BoardFile;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.persistence.AttributeConverter;
import java.util.List;

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
    public BoardBaseDTO convertToEntityAttribute(BoardBase boardBase) {

        List<BoardDetail> boardDetailList = boardBase.getBoardDetails();
        List<BoardDetailDTO> boardDetailDTOList = Lists.newArrayList();

        for(BoardDetail boardDetail : boardDetailList) {
//            List<BoardFile> boardFiles = boardDetail.getBoardFiles();
//
//            for(BoardFile boardFile : boardFiles) {
//                BoardFileDTO boardFileDTO = modelMapper.map(boardFile, BoardFileDTO.class);
//            }

            BoardDetailDTO boardDetailDTO = modelMapper.map(boardDetail, BoardDetailDTO.class);

            boardDetailDTOList.add(boardDetailDTO);
        }

        BoardBaseDTO boardBaseDTO = modelMapper.map(boardBase, BoardBaseDTO.class);

        boardBaseDTO.setBoardDetails(boardDetailDTOList);

        return boardBaseDTO;
    }
}
