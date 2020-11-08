package com.cocoblue.securitytest.service;

import com.cocoblue.securitytest.config.ApplicationConfig;
import com.cocoblue.securitytest.config.SecurityConfig;
import com.cocoblue.securitytest.dto.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class})
public class BoardServiceTest {
    @Autowired
    BoardService boardService;

//    @Test
//    public void writeTest() {
//        Board board = new Board();
//        board.setCno(7);
//        board.setTitle("테스트");
//        board.setWriteTime(LocalDateTime.now());
//        board.setContent("테스트입니다!");
//        System.out.println(board.toString());
//
//        boardService.writePost(board);
//    }

//    @Test
//    public void getPostTest() {
//        String postId = "1";
//
//        Board board = boardService.getPost(postId);
//        System.out.println(board.toString());
//    }

    @Test
    public void getPostAllTest() {
        List<Board> boards = boardService.getPostsAll();
        System.out.println(boards);
    }
}
