package com.cocoblue.securitytest.controller;

import com.cocoblue.securitytest.dto.Board;
import com.cocoblue.securitytest.dto.Comment;
import com.cocoblue.securitytest.dto.Customer;
import com.cocoblue.securitytest.service.BoardService;
import com.cocoblue.securitytest.service.CommentService;
import com.cocoblue.securitytest.service.CustomerService;
import com.cocoblue.securitytest.service.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(path = "/board")
public class PostController {
    private final BoardService boardService;
    private final CommentService commentService;
    private final CustomerService customerService;

    public PostController(BoardService boardService, CommentService commentService, CustomerService customerService) {
        this.boardService = boardService;
        this.commentService = commentService;
        this.customerService = customerService;
    }

    @RequestMapping("/")
    public String getPostList(Model model, @RequestParam(name = "page", required = false) Integer page,
                                            @RequestParam(name = "keyword", required = false) String keyword) {

        // requestparam이 없을 경우 기본 값 정의
        if(page == null) {
            page = 1;
        }

        List<Board> boards;

        if(keyword != null) {
            model.addAttribute("searchKeyword", keyword);

            boards = boardService.getPostsByKeyword(keyword, page - 1);
            model.addAttribute("pagesCount", getTotalPage(boardService.getPostsCountByKeyword(keyword)));
            model.addAttribute("searchStatus", "Success");

            // 검색 결과가 없을 경우, 1페이지를 갖고 옴.
            if(boards.size() == 0) {
                model.addAttribute("searchStatus", "Fail");

                boards = boardService.getPostsByPage(0);
                model.addAttribute("pagesCount", getTotalPage(boardService.getPostsCount()));
            }
        } else {
            boards = boardService.getPostsByPage(page - 1);

            // 해당 페이지에 글이 없을 경우 1페이지를 가져옴.
            if(boards.size() == 0) {
                boards = boardService.getPostsByPage(0);
            }

            model.addAttribute("pagesCount", getTotalPage(boardService.getPostsCount()));
        }

        model.addAttribute("boards", boards);
        model.addAttribute("nowPage", page);

        model = addLoginImf(model);

        return "posts/posts";
    }

    @GetMapping("read/{postId}")
    public String getPost(@PathVariable String postId, Model model) {
        Board board = boardService.getPost(postId);
        model.addAttribute("board", board);
        long commentCount = commentService.getCommentCount(postId);
        model.addAttribute("comment_count", commentCount);
        model.addAttribute("postId", postId);

        // 조회수 증가
        boardService.increaseViewNum(postId);

        if(commentCount != 0) {
            List<Comment> comments = commentService.getComments(postId);
            model.addAttribute("comments", comments);
        }

        model = addLoginImf(model);

        return "posts/read";
    }

    @RequestMapping("write")
    public String getWrite(Model model, @RequestParam(name="mode", required = false) String mode,
                           @RequestParam(name = "postId", required = false) String postId) {
        if(mode != null) {
            if(!mode.equals("modify")) {
                return "posts/write";
            }

            if(postId == null) {
                return "posts/write";
            }

            Board board = boardService.getPost(postId);
            model.addAttribute("board", board);

            // 작성자 = 수정자인지 체크
            CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if(customUserDetails.getCno() != board.getCno()) {
                return "posts/write";
            }

            model.addAttribute("mode", "modify");
            model.addAttribute("postId", postId);
        }

        return "posts/write";
    }

    @PostMapping("insertpost")
    public String writePost(@ModelAttribute Board board) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        board.setCno(customUserDetails.getCno());
        board.setWriteTime(LocalDateTime.now());

        boardService.writePost(board);
        return "redirect:/board/";
    }

    @RequestMapping("delete/{postId}")
    public String deletePost(Model model, @PathVariable(name = "postId") String postId) {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            // 비로그인 상태에서 접근한 경우
            model.addAttribute("result", "No Login Value");
        }

        Board board = boardService.getPost(postId);
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(customUserDetails.getCno() == board.getCno()) {
            if(boardService.deletePost(postId)) {
                // 게시글 삭제가 완료되면.
                model.addAttribute("result", "Success");
            } else {
                model.addAttribute("result", "Fail");
            }
        } else {
            // 로그인 된 ID와 게시글 작성자의 ID가 일치하지 않는 경우
            model.addAttribute("result", "Permission Error");
        }

        return "posts/deleteresult";
    }

    @RequestMapping("modifypost")
    public String updatePost(@ModelAttribute Board board, Model model, @RequestParam(name = "postId") String postId) {
        board.setPostId(Long.parseLong(postId));

        if(boardService.updatePost(board)) {
            // 게시글 변경이 완료되면.
            model.addAttribute("result", "Success");
            model.addAttribute("postId", board.getPostId());
        } else {
            model.addAttribute("result", "Fail");
        }

        return "posts/updateresult";
    }

    private Model addLoginImf(Model model) {
        Customer customer = customerService.getLoginUser();

        if(customer == null) {
            return model;
        }

        model.addAttribute("loginCno", customer.getCno());
        model.addAttribute("loginName", customer.getName());

        return model;
    }

    private long getTotalPage(long postsCount) {
        if(postsCount % 4 == 0) {
            return postsCount / 4;
        } else {
            return postsCount / 4 + 1;
        }
    }
}
