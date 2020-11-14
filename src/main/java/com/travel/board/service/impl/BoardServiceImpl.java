package com.travel.board.service.impl;

import com.travel.board.file.FileUtils;
import com.travel.board.model.BoardBase;
import com.travel.board.model.BoardFile;
import com.travel.board.repository.BoardFileRepository;
import com.travel.board.repository.BoardRepository;
import com.travel.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;

    private final FileUtils fileUtils;

    @Override
    public List<BoardBase> selectBoardList(String creatorId) {
        return boardRepository.findAllByCreatorId(creatorId);
    }

    @Override
    public BoardBase insertBoard(BoardBase boardBase, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        BoardBase savedBoardBase = boardRepository.save(boardBase);

//        List<BoardFile> boardFiles = fileUtils.parseFileInfo(boardBase.getId(), multipartHttpServletRequest);
//
//        if(!CollectionUtils.isEmpty(boardFiles)){
//            for(BoardFile boardFile : boardFiles) {
//                boardFileRepository.save(boardFile);
//            }
//        }

        return savedBoardBase;
    }

    @Override
    public void updateBoard(BoardBase boardBase, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        boardRepository.save(boardBase);

        List<BoardFile> boardFiles = fileUtils.parseFileInfo(boardBase.getId(), multipartHttpServletRequest);

        if(!CollectionUtils.isEmpty(boardFiles)){
            for(BoardFile boardFile : boardFiles) {
                boardFileRepository.save(boardFile);
            }
        }
    }

    @Override
    public void deleteBoard(long idx) {
        boardRepository.deleteById(idx);
    }
}
