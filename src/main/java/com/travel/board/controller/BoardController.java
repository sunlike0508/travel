package com.travel.board.controller;

import com.travel.board.model.BoardBase;
import com.travel.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/v1/board/{creatorId}")
    public ResponseEntity<List<BoardBase>> selectBoardList(@PathVariable("creatorId") String creatorId) {

        List<BoardBase> boardBases = boardService.selectBoardList(creatorId);

        return new ResponseEntity<>(boardBases, HttpStatus.OK);
    }

    @PostMapping("/v1/board")
    public ResponseEntity<BoardBase> insertBoard(@RequestBody BoardBase boardBase,
                                                 MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {

        boardService.insertBoard(boardBase, multipartHttpServletRequest);

        return new ResponseEntity(null, HttpStatus.OK);
    }

    @PatchMapping("/v1/board")
    public ResponseEntity<BoardBase> updateBoard(@RequestBody BoardBase boardBase,
                                                 MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {

        boardService.updateBoard(boardBase, multipartHttpServletRequest);

        return new ResponseEntity(null, HttpStatus.OK);
    }

    @DeleteMapping("/v1/board/{idx}")
    public ResponseEntity<BoardBase> deleteBoard(@PathVariable("idx") long idx) {

        boardService.deleteBoard(idx);

        return new ResponseEntity(null, HttpStatus.OK);
    }

}
