package com.travel.service.impl;

import com.travel.file.FileUtils;
import com.travel.model.Board;
import com.travel.model.BoardFile;
import com.travel.repository.BoardFileRepository;
import com.travel.repository.BoardRepository;
import com.travel.service.BoardService;
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
    public List<Board> selectBoardList(String creatorId) {
        return boardRepository.findAllByCreatorId(creatorId);
    }

    @Override
    public void insertBoard(Board board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        boardRepository.save(board);

        List<BoardFile> boardFiles = fileUtils.parseFileInfo(board.getIdx(), multipartHttpServletRequest);

        if(!CollectionUtils.isEmpty(boardFiles)){
            for(BoardFile boardFile : boardFiles) {
                boardFileRepository.save(boardFile);
            }
        }
    }

    @Override
    public void updateBoard(Board board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
        boardRepository.save(board);

        List<BoardFile> boardFiles = fileUtils.parseFileInfo(board.getIdx(), multipartHttpServletRequest);

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
