package com.cocoblue.securitytest.controller;

import com.cocoblue.securitytest.dto.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.cocoblue.securitytest.service.PostService;
import org.springframework.web.servlet.ModelAndView;

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
        System.out.println(posts);

        model.addAttribute("posts", posts);
        model.addAttribute("test", "메롱");
        return "posts/postlist";
    }
}
