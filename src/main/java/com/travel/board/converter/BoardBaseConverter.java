package com.travel.board.converter;

import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.dto.BoardDetailDTO;
import com.travel.board.dto.BoardFileDTO;
import com.travel.board.file.FileUtils;
import com.travel.board.model.BoardBase;
import com.travel.board.model.BoardDetail;
import com.travel.board.model.BoardFile;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.persistence.AttributeConverter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BoardBaseConverter implements AttributeConverter<BoardBaseDTO, BoardBase> {

    private final ModelMapper modelMapper;
    private final FileUtils fileUtils;

    @SneakyThrows
    @Override
    public BoardBase convertToDatabaseColumn(BoardBaseDTO boardBaseDTO) {

        if(!ObjectUtils.isEmpty(boardBaseDTO.getMultipartFile())
                && !fileUtils.isExistFile(boardBaseDTO.getMultipartFile())){
            boardBaseDTO.setMainPhotoPath(fileUtils.saveMultipartFile(boardBaseDTO.getMultipartFile()));
        }

        List<BoardDetailDTO> boardDetailDTOS = boardBaseDTO.getBoardDetails();

        for(BoardDetailDTO boardDetailDTO : boardDetailDTOS) {
            List<BoardFileDTO> boardFileDTOS = boardDetailDTO.getBoardFiles();

            for(BoardFileDTO boardFileDTO : boardFileDTOS) {
                if(!ObjectUtils.isEmpty(boardFileDTO.getMultipartFile())
                        && !fileUtils.isExistFile(boardFileDTO.getMultipartFile())){
                    boardFileDTO.setPhotoPath(fileUtils.saveMultipartFile(boardFileDTO.getMultipartFile()));
                }
            }
        }

        BoardBase boardBase = modelMapper.map(boardBaseDTO, BoardBase.class);

        List<BoardDetail> boardDetails = boardBase.getBoardDetails();

        for(BoardDetail boardDetail : boardDetails) {
            boardDetail.setBoardBase(boardBase);

            List<BoardFile> boardFiles = boardDetail.getBoardFiles();

            for(BoardFile boardFile : boardFiles) {
                boardFile.setBoardDetail(boardDetail);
            }
        }

        return boardBase;
    }

    @SneakyThrows
    @Override
    public BoardBaseDTO convertToEntityAttribute(BoardBase boardBase) {

        BoardBaseDTO boardBaseDTO = modelMapper.map(boardBase, BoardBaseDTO.class);

        boardBaseDTO.setMultipartFile(getMultipartFile(boardBaseDTO.getMainPhotoPath()));

        List<BoardDetailDTO> boardDetailDTOS = boardBaseDTO.getBoardDetails();

        for(BoardDetailDTO boardDetailDTO : boardDetailDTOS) {

            List<BoardFileDTO> boardFileDTOS = boardDetailDTO.getBoardFiles();

            for(BoardFileDTO boardFileDTO : boardFileDTOS) {
                boardFileDTO.setMultipartFile(getMultipartFile(boardFileDTO.getPhotoPath()));
            }
        }

        return boardBaseDTO;
    }

    private MultipartFile getMultipartFile(String photoPath) throws IOException {
        File file = new File(photoPath);

        DiskFileItem fileItem = new DiskFileItem("file", Files.probeContentType(file.toPath()),
                false, file.getName(), (int) file.length() , file.getParentFile());

        IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());

        return new CommonsMultipartFile(fileItem);
    }
}
