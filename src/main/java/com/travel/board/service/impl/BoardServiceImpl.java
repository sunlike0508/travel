package com.travel.board.service.impl;

import com.travel.board.converter.BoardBaseConverter;
import com.travel.board.converter.BoardBaseDTOConverter;
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
    private final BoardBaseDTOConverter boardBaseDTOConverter;

    @Override
    public List<BoardBaseDTO> selectBoardList(String creatorId) {

        List<BoardBase> boardBases = boardBaseRepository.findAllByCreatorId(creatorId);

        return boardBases.stream().map(boardBase -> boardBaseConverter.convert(boardBase)).collect(Collectors.toList());
    }

    @Override
    public BoardBaseDTO insertBoard(BoardBaseDTO boardBaseDTO) {

        BoardBase convertedBoardBase = boardBaseDTOConverter.convert(boardBaseDTO);

        BoardBase savedBoardBase = boardBaseRepository.save(convertedBoardBase);
        // TODO : board_detail 저장
        // TODO : board_file 저장

        // TODO : id를 가지고 다사 조회 하든가 아님 dto로 변환해서 return 하든가 결정
        return new BoardBaseDTO();
    }

    @Override
    public BoardBaseDTO updateBoard(BoardBaseDTO boardBaseDTO) {
        BoardBase covertedBoardBase = boardBaseDTOConverter.convert(boardBaseDTO);

        BoardBase updatedBoardBase = boardBaseRepository.save(covertedBoardBase);


        BoardBaseDTO convertedBoardBaseDTO = boardBaseConverter.convert(updatedBoardBase);

        return convertedBoardBaseDTO;
    }

    @Override
    public void deleteBoard(long idx) {
        boardBaseRepository.deleteById(idx);
    }
}
