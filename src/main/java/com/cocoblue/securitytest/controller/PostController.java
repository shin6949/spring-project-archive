package com.cocoblue.securitytest.controller;

import com.cocoblue.securitytest.dto.Post;
import com.cocoblue.securitytest.service.CommentService;
import com.cocoblue.securitytest.service.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.cocoblue.securitytest.service.PostService;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(path = "/board")
public class PostController {
    PostService postService;
    CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @RequestMapping("/posts")
    public String getPostList(Model model, @RequestParam(name = "page", required = false) String page,
                                            @RequestParam(name = "keyword", required = false) String keyword) {

        // requestparam이 없을 경우 기본 값 정의
        if(page == null) {
            page = "1";
        }

        List<Post> posts = null;

        if(keyword != null) {
            model.addAttribute("searchKeyword", keyword);
            posts = postService.getPostsByKeyword("자유 게시판", keyword, Integer.parseInt(page) - 1);
            model.addAttribute("pagesCount", (postService.getPostsCountByKeyword("자유 게시판", keyword) / 4) + 1);
            model.addAttribute("searchStatus", "Success");

            // 검색 결과가 없을 경우, 1페이지를 갖고 옴.
            if(posts.size() == 0) {
                model.addAttribute("searchStatus", "Fail");

                posts = postService.getPostsByPage("자유 게시판", Integer.parseInt("0"));
                model.addAttribute("pagesCount", (postService.getPostsCount("자유 게시판") / 4) + 1);
            }
        } else {
            posts = postService.getPostsByPage("자유 게시판", Integer.parseInt(page) - 1);

            // 해당 페이지에 글이 없을 경우 1페이지를 가져옴.
            if(posts.size() == 0) {
                posts = postService.getPostsByPage("자유 게시판", Integer.parseInt("0"));
            }

            model.addAttribute("pagesCount", (postService.getPostsCount("자유 게시판") / 4) + 1);
        }

        model.addAttribute("posts", posts);
        model.addAttribute("nowPage", Integer.parseInt(page));

        model = addLoginImf(model);

        return "posts/postlist";
    }

    @GetMapping("read/{id}")
    public String getPost(@PathVariable String id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        long commentCount = commentService.getCommentCount(id);
        model.addAttribute("comment_count", commentCount);
        model.addAttribute("postId", id);

        // 조회수 증가
        postService.increaseViewNum(id);

        if(commentCount != 0) {
            model.addAttribute("comments", commentService.getComments(id));
        }

        return "posts/read";
    }

    @GetMapping("write")
    public String getWrite(Model model) {
        return "posts/write";
    }

    @PostMapping("insertpost")
    public String writePost(@ModelAttribute Post post) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setWriterId(customUserDetails.getId());
        post.setBoardId(1);
        post.setViewNumber(0);
        post.setWriteTime(LocalDateTime.now());

        postService.writePost(post);
        return "redirect:/board/posts";
    }

    private Model addLoginImf(Model model) {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            return model;
        }

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("loginedId", customUserDetails.getId());
        model.addAttribute("loginedName", customUserDetails.getName());

        System.out.println(customUserDetails.getName());

        return model;
    }
}
