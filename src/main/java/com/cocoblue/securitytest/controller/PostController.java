package com.cocoblue.securitytest.controller;

import com.cocoblue.securitytest.dto.Post;
import com.cocoblue.securitytest.service.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.cocoblue.securitytest.service.PostService;

import javax.swing.*;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping(path = "/board")
public class PostController {
    PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping("/posts")
    public String getPostList(Model model) {
        List<Post> posts = postService.getPostsAll("자유 게시판");
        model.addAttribute("posts", posts);
        return "posts/postlist";
    }

    @GetMapping("read/{id}")
    public String getPost(@PathVariable String id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);

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

        if(postService.writePost(post)) {
            return "redirect:/board/posts";
        } else {
            return "<script>"
                    + "alert(\"서버 오류로 업로드하지 못했습니다.\");"
                    + "</script>";
        }
    }

}
