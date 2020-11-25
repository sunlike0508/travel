package com.travel.board.service.impl;

import com.travel.board.converter.BoardBaseConverter;
import com.travel.board.converter.BoardDetailConverter;
import com.travel.board.converter.BoardFileConverter;
import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.model.BoardBase;
import com.travel.board.repository.BoardBaseRepository;
import com.travel.board.repository.BoardDetailRepository;
import com.travel.board.repository.BoardFileRepository;
import com.travel.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardBaseRepository boardBaseRepository;
    private final BoardDetailRepository boardDetailRepository;
    private final BoardFileRepository boardFileRepository;

    private final BoardBaseConverter boardBaseConverter;
    private final BoardDetailConverter boardDetailConverter;
    private final BoardFileConverter boardFileConverter;


    @Override
    public List<BoardBaseDTO> selectBoardList(String creatorId) {

        List<BoardBase> boardBases = boardBaseRepository.findAllByCreatorId(creatorId);

        return null;
        //return boardBases.stream().map(boardBase -> boardBaseDTOConverter.convert(boardBase)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public BoardBaseDTO insertBoard(BoardBaseDTO boardBaseDTO) {

        // board_base 저장
        BoardBase boardBase = boardBaseConverter.convertToDatabaseColumn(boardBaseDTO);

        BoardBase saveBoardBase = boardBaseRepository.save(boardBase);

        BoardBase findBoardBase = boardBaseRepository.findById(saveBoardBase.getId()).orElseThrow();
        
        return boardBaseConverter.convertToEntityAttribute(findBoardBase);
    }

    @Override
    public BoardBaseDTO updateBoard(BoardBaseDTO boardBaseDTO) {
//        BoardBase covertedBoardBase = boardBaseConverter.convert(boardBaseDTO);
//
//        BoardBase updatedBoardBase = boardBaseRepository.save(covertedBoardBase);

        return null;
        //return convertedBoardBaseDTO;
    }

    @Override
    public void deleteBoard(long idx) {
        boardBaseRepository.deleteById(idx);
    }
}
