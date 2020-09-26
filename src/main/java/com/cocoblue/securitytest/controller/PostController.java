package com.cocoblue.securitytest.controller;

import com.cocoblue.securitytest.dto.Comment;
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
    public String getPostList(Model model) {
        List<Post> posts = postService.getPostsAll("자유 게시판");
        model.addAttribute("posts", posts);
        return "posts/Postlist";
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
}
