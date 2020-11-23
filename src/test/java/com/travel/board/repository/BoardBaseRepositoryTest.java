package com.travel.board.repository;

import com.travel.board.model.BoardBase;
import com.travel.board.model.BoardDetail;
import com.travel.board.model.BoardFile;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class BoardBaseRepositoryTest {

    @Autowired
    private BoardBaseRepository boardBaseRepository;

    @Test
    public void 글_저장_테스트() {

        BoardBase givenBoardBase = new BoardBase();
        givenBoardBase.setTitle("글 작성");
        givenBoardBase.setLocation("의정부");
        givenBoardBase.setContents("의정부 놀러감");
        givenBoardBase.setParties("친구들과");
        givenBoardBase.setCreatorId("sunlike0301");
        givenBoardBase.setStartDate(LocalDateTime.now());
        givenBoardBase.setEndDate(LocalDateTime.now());
        givenBoardBase.setMainPhotoPath("/photo/main/" + LocalDateTime.now());

        BoardDetail boardDetail = new BoardDetail();
        //boardDetail


        BoardBase expectedBoardBase = boardBaseRepository.save(givenBoardBase);

        assertThat(givenBoardBase.getTitle(), is(expectedBoardBase.getTitle()));
        assertThat(givenBoardBase.getLocation(), is(expectedBoardBase.getLocation()));
        assertThat(givenBoardBase.getContents(), is(expectedBoardBase.getContents()));
    }

//    @Test
//    public void 글_저장_테스트() {
//
//        BoardBase givenBoardBase = new BoardBase();
//        givenBoardBase.setTitle("글 작성");
//        givenBoardBase.setLocation("의정부");
//        givenBoardBase.setContents("의정부 놀러감");
//        givenBoardBase.setParties("친구들과");
//        givenBoardBase.setCreatorId("sunlike0301");
//        givenBoardBase.setStartDate(LocalDateTime.now());
//        givenBoardBase.setEndDate(LocalDateTime.now());
//        givenBoardBase.setMainPhotoPath("/photo/main/" + LocalDateTime.now());
//
//        BoardBase expectedBoardBase = boardBaseRepository.save(givenBoardBase);
//
//        assertThat(givenBoardBase.getTitle(), is(expectedBoardBase.getTitle()));
//        assertThat(givenBoardBase.getLocation(), is(expectedBoardBase.getLocation()));
//        assertThat(givenBoardBase.getContents(), is(expectedBoardBase.getContents()));
//    }

    @Test
    public void 글_조회_테스트() {
        String creatorId = "sunlike" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        List<BoardBase> boardBases = new ArrayList<>();

        for(int i = 0 ; i < 5; i++){
            BoardBase boardBase = new BoardBase();
            boardBase.setTitle("글 작성" + i);
            boardBase.setLocation("의정부" + i);
            boardBase.setContents("의정부 놀러감" + i);
            boardBase.setParties("친구들과" + i);
            boardBase.setCreatorId(creatorId);
            boardBase.setStartDate(LocalDateTime.now());
            boardBase.setEndDate(LocalDateTime.now());
            boardBase.setMainPhotoPath("/photo/main/" + LocalDateTime.now());

            boardBases.add(boardBase);
        }

        boardBaseRepository.saveAll(boardBases);

        List<BoardBase> boardBaseList = boardBaseRepository.findAllByCreatorId(creatorId);

        assertThat(boardBaseList.size(), is(5));
    }

    @Test
    public void 글_삭제_테스트() {

        BoardBase boardBase = new BoardBase();
        boardBase.setTitle("글 작성");
        boardBase.setLocation("의정부");
        boardBase.setContents("의정부 놀러감");
        boardBase.setParties("친구들과");
        boardBase.setCreatorId("sunlike0301");
        boardBase.setStartDate(LocalDateTime.now());
        boardBase.setEndDate(LocalDateTime.now());
        boardBase.setMainPhotoPath("/photo/main/" + LocalDateTime.now());

        BoardBase savedBoardBase = boardBaseRepository.save(boardBase);

        BoardBase findBoardBase = boardBaseRepository.findById(savedBoardBase.getId()).get();

        assertThat(savedBoardBase.getId(),is(findBoardBase.getId()));

        // when
        boardBaseRepository.delete(savedBoardBase);

        // then
        NoSuchElementException expectedException
                = assertThrows(NoSuchElementException.class, () -> boardBaseRepository.findById(savedBoardBase.getId()).get());

        assertThat(expectedException.getMessage(), is("No value present"));
    }

    @Test
    public void 글_detail_file_전체_조회_테스트() {
        BoardBase boardBase = boardBaseRepository.findById(112L).get();

        System.out.println(boardBase.toString());

        List<BoardDetail> boardDetails = boardBase.getBoardDetails();

        for(BoardDetail boardDetail : boardDetails) {

            System.out.println(boardDetail.toString());

            List<BoardFile> boardFiles = boardDetail.getBoardFiles();

            boardFiles.stream().forEach(boardFile -> System.out.println(boardFile.toString()));
        }
    }
}