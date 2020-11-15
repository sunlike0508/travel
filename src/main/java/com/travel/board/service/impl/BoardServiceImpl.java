package com.travel.board.service.impl;

import com.travel.board.converter.BoardBaseConverter;
import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.file.FileUtils;
import com.travel.board.model.BoardBase;
import com.travel.board.repository.BoardDetailRepository;
import com.travel.board.repository.BoardFileRepository;
import com.travel.board.repository.BoardBaseRepository;
import com.travel.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardBaseRepository boardBaseRepository;
    private final BoardDetailRepository boardDetailRepository;
    private final BoardFileRepository boardFileRepository;

    private final BoardBaseConverter boardBaseConverter;

    private final FileUtils fileUtils;

    @Override
    public List<BoardBaseDTO> selectBoardList(String creatorId) {

        List<BoardBase> boardBases = boardBaseRepository.findAllByCreatorId(creatorId);

        return boardBases.stream().map(boardBase -> boardBaseConverter.convert(boardBase)).collect(Collectors.toList());
    }

    @Override
    public BoardBaseDTO insertBoard(BoardBaseDTO boardBaseDTO, MultipartHttpServletRequest multipartHttpServletRequest)
            throws Exception {

        BoardBase convertedBoardBase = boardBaseConverter.convertDTO(boardBaseDTO);

        BoardBase savedBoardBase = boardBaseRepository.save(convertedBoardBase);

        BoardBaseDTO convertedBoardBaseDTO = boardBaseConverter.convert(savedBoardBase);

//        List<BoardFile> boardFiles = fileUtils.parseFileInfo(savedBoardBase.getId(), multipartHttpServletRequest);
//
//        if(!CollectionUtils.isEmpty(boardFiles)){
//            for(BoardFile boardFile : boardFiles) {
//                boardFileRepository.save(boardFile);
//            }
//        }

        return convertedBoardBaseDTO;
    }

    @Override
    public BoardBaseDTO updateBoard(BoardBaseDTO boardBaseDTO, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        BoardBase covertedBoardBase = boardBaseConverter.convertDTO(boardBaseDTO);

        BoardBase updatedBoardBase = boardBaseRepository.save(covertedBoardBase);

//        List<BoardFile> boardFiles = fileUtils.parseFileInfo(boardBase.getId(), multipartHttpServletRequest);
//
//        if(!CollectionUtils.isEmpty(boardFiles)){
//            for(BoardFile boardFile : boardFiles) {
//                boardFileRepository.save(boardFile);
//            }
//        }

        BoardBaseDTO convertedBoardBaseDTO = boardBaseConverter.convert(updatedBoardBase);

        return convertedBoardBaseDTO;
    }

    @Override
    public void deleteBoard(long idx) {
        boardBaseRepository.deleteById(idx);
    }
}
