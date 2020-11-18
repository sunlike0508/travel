package com.travel.board.controller;

import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{creatorId}")
    public ResponseEntity<List<BoardBaseDTO>> selectBoardList(@PathVariable("creatorId") String creatorId) {

        List<BoardBaseDTO> boardBaseDTOs = boardService.selectBoardList(creatorId);

        return new ResponseEntity<>(boardBaseDTOs, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BoardBaseDTO> insertBoard(@RequestBody BoardBaseDTO boardBaseDTO) throws Exception {

        BoardBaseDTO savedBoardDTOBase = boardService.insertBoard(boardBaseDTO);

        return new ResponseEntity(savedBoardDTOBase, HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<BoardBaseDTO> updateBoard(@RequestBody BoardBaseDTO boardBaseDTO) throws Exception {

        BoardBaseDTO updatedBoardBase = boardService.updateBoard(boardBaseDTO);

        return new ResponseEntity(updatedBoardBase, HttpStatus.OK);
    }

    @DeleteMapping("/{idx}")
    public ResponseEntity<BoardBaseDTO> deleteBoard(@PathVariable("idx") long idx) {

        boardService.deleteBoard(idx);

        return new ResponseEntity(null, HttpStatus.OK);
    }

}
