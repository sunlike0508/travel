package com.travel.board.service.impl;

import com.travel.board.converter.BoardBaseDTOConverter;
import com.travel.board.converter.BoardBaseConverter;
import com.travel.board.converter.BoardDetailConverter;
import com.travel.board.converter.BoardFileConverter;
import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.dto.BoardDetailDTO;
import com.travel.board.dto.BoardFileDTO;
import com.travel.board.model.BoardBase;
import com.travel.board.model.BoardDetail;
import com.travel.board.model.BoardFile;
import com.travel.board.repository.BoardDetailRepository;
import com.travel.board.repository.BoardFileRepository;
import com.travel.board.repository.BoardBaseRepository;
import com.travel.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
    private final BoardDetailConverter boardDetailConverter;
    private final BoardFileConverter boardFileConverter;

    private final BoardBaseDTOConverter boardBaseDTOConverter;

    @Override
    public List<BoardBaseDTO> selectBoardList(String creatorId) {

        List<BoardBase> boardBases = boardBaseRepository.findAllByCreatorId(creatorId);

        return null;
        //return boardBases.stream().map(boardBase -> boardBaseDTOConverter.convert(boardBase)).collect(Collectors.toList());
    }

    @Override
    public BoardBaseDTO insertBoard(BoardBaseDTO boardBaseDTO) {

        // board_base 저장
        BoardBase convertedBoardBase = boardBaseConverter.convert(boardBaseDTO);
        BoardBase savedBoardBase = boardBaseRepository.save(convertedBoardBase);

        // TODO : board_detail 저장
        List<BoardDetailDTO> boardDetailDTOS = boardBaseDTO.getBoardDetailDTOs();
        List<BoardDetail> boardDetails = boardDetailConverter.convert(boardDetailDTOS);
        boardDetailRepository.saveAll(boardDetails);
        //List<BoardDetail> savedBoardDetails = boardDetailRepository.saveAll(boardDetails);


        // TODO : board_file 저장
        for(BoardDetailDTO boardDetailDTO : boardDetailDTOS) {
            List<BoardFileDTO> boardFileDTOS = boardDetailDTO.getBoardFileDTOs();
            List<BoardFile> boardFiles = boardFileConverter.convert(boardFileDTOS);
            boardFileRepository.saveAll(boardFiles);
            //List<BoardFile> savedBoardFiles = boardFileRepository.saveAll(boardFiles);
        }

        // TODO : id를 가지고 다사 조회 하든가 아님 dto로 변환해서 return 하든가 결정
        BoardBase boardBase = boardBaseRepository.findById(savedBoardBase.getId()).get();
        
        return boardBaseDTOConverter.convert(boardBase);
    }

    @Override
    public BoardBaseDTO updateBoard(BoardBaseDTO boardBaseDTO) {
        BoardBase covertedBoardBase = boardBaseConverter.convert(boardBaseDTO);

        BoardBase updatedBoardBase = boardBaseRepository.save(covertedBoardBase);

        return null;
        //return convertedBoardBaseDTO;
    }

    @Override
    public void deleteBoard(long idx) {
        boardBaseRepository.deleteById(idx);
    }
}
