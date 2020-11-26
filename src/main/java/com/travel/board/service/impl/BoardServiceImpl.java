package com.travel.board.service.impl;

import com.travel.board.converter.BoardBaseConverter;
import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.model.BoardBase;
import com.travel.board.repository.BoardBaseRepository;
import com.travel.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardBaseRepository boardBaseRepository;

    private final BoardBaseConverter boardBaseConverter;

    @Override
    public List<BoardBaseDTO> selectBoardList(String creatorId) {

        List<BoardBase> boardBases = boardBaseRepository.findAllByCreatorId(creatorId);

        return boardBases.stream().map(boardBaseConverter::convertToEntityAttribute).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public BoardBaseDTO insertBoard(BoardBaseDTO boardBaseDTO) {

        BoardBase boardBase = boardBaseConverter.convertToDatabaseColumn(boardBaseDTO);

        BoardBase saveBoardBase = boardBaseRepository.save(boardBase);

        return boardBaseConverter.convertToEntityAttribute(saveBoardBase);
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
