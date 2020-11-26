package com.travel.board.controller;

import com.travel.board.factory.ApiResponse;
import com.travel.board.dto.BoardBaseDTO;
import com.travel.board.factory.ApiResponseFactory;
import com.travel.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/v1/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/{creatorId}")
    public ResponseEntity<ApiResponse> selectBoardList(@PathVariable("creatorId") String creatorId) {

        List<BoardBaseDTO> boardBaseDTOs = boardService.selectBoardList(creatorId);

        return ApiResponseFactory.createSuccessResponse(boardBaseDTOs);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> insertBoard(@RequestBody BoardBaseDTO boardBaseDTO) {

        BoardBaseDTO savedBoardBaseDTO = null;
        try {
            savedBoardBaseDTO = boardService.insertBoard(boardBaseDTO);
        } catch (Exception e) {
            log.debug("게시글 생성 실패 : " + e.getMessage());
            ApiResponseFactory.createFailResponse();
        }

        return ApiResponseFactory.createSuccessResponse(savedBoardBaseDTO);
    }

    @PatchMapping
    public ResponseEntity<BoardBaseDTO> updateBoard(@RequestBody BoardBaseDTO boardBaseDTO) throws Exception {

        BoardBaseDTO updatedBoardBase = boardService.updateBoard(boardBaseDTO);

        return new ResponseEntity(updatedBoardBase, HttpStatus.OK);
    }

    @DeleteMapping("/{idx}")
    public ResponseEntity<BoardBaseDTO> deleteBoard(@PathVariable("idx") long idx, @RequestParam String creatorId) {

        List<BoardBaseDTO> boardBaseDTOS = boardService.deleteBoard(idx, creatorId);

        return new ResponseEntity(boardBaseDTOS, HttpStatus.OK);
    }

}
