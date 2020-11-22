package com.travel.board.service.impl;

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

    @Override
    public BoardBaseDTO insertBoard(BoardBaseDTO boardBaseDTO) {

        // board_base 저장
        BoardBase boardBase = boardBaseConverter.convertToDatabaseColumn(boardBaseDTO);
        BoardBase savedBoardBase = boardBaseRepository.save(boardBase);

        // board_detail 저장
        List<BoardDetailDTO> boardDetailDTOS = boardBaseDTO.getBoardDetails();
        boardDetailDTOS.stream().forEach(boardDetailDTO -> boardDetailDTO.setBoardBaseId(savedBoardBase.getId()));

        for(BoardDetailDTO boardDetailDTO : boardDetailDTOS) {

            BoardDetail boardDetail = boardDetailConverter.convertToDatabaseColumn(boardDetailDTO);
            BoardDetail savedBoardDetail = boardDetailRepository.save(boardDetail);

            // board_file 저장
            List<BoardFileDTO> boardFileDTOS = boardDetailDTO.getBoardFiles();
            boardFileDTOS.stream().forEach(boardFileDTO -> boardFileDTO.setBoardDetailId(savedBoardDetail.getId()));


            for(BoardFileDTO boardFileDTO : boardFileDTOS) {
                BoardFile boardFile = boardFileConverter.convertToDatabaseColumn(boardFileDTO);
                boardFileRepository.save(boardFile);
            }
        }

        // TODO : id를 가지고 다사 조회 하든가 아님 dto로 변환해서 return 하든가 결정
        BoardBase findedboardBase = boardBaseRepository.findById(savedBoardBase.getId()).get();
        
        return boardBaseConverter.convertToEntityAttribute(findedboardBase);
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
