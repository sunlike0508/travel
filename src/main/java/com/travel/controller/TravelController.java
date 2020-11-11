package com.travel.controller;

import com.travel.model.Board;
import com.travel.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Slf4j
@RestController("/board")
@RequiredArgsConstructor
public class TravelController {

    private final BoardService boardService;

    @GetMapping("/{creatorId}")
    public ResponseEntity<List<Board>> selectBoardList(@PathVariable("creatorId") String creatorId) {

        List<Board> boards = boardService.selectBoardList(creatorId);

        return new ResponseEntity<>(boards, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Board> insertBoard(@RequestBody Board board ,
                                             MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {

        boardService.insertBoard(board, multipartHttpServletRequest);

        return new ResponseEntity(null, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Board> updateBoard(@RequestBody Board board,
                                             MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {

        boardService.updateBoard(board, multipartHttpServletRequest);

        return new ResponseEntity(null, HttpStatus.OK);
    }

    @DeleteMapping("/{idx}")
    public ResponseEntity<Board> deleteBoard(@PathVariable("idx") long idx) {

        boardService.deleteBoard(idx);

        return new ResponseEntity(null, HttpStatus.OK);
    }

}
