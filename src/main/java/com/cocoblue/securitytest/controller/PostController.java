package com.cocoblue.securitytest.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.cocoblue.securitytest.dto.Post;
import com.cocoblue.securitytest.service.PostService;
import com.cocoblue.securitytest.service.PostServiceImpl;

@Controller
@RequestMapping("/post")
public class PostController {
    PostService service;

    @RequestMapping("/list")
    public String list(Model model) {
        service = new PostServiceImpl();
        model.addAttribute("list", service.listAll());
        return "post/list";
    }
    @RequestMapping("/read")
    public String read(@RequestParam("number") Long number, Model model)
            throws Exception{
        service = new PostServiceImpl();
        model.addAttribute("board", service.read(number));
        return "post/update";
    }
    @GetMapping("write")
    public String writeForm() {
        return "post/write";
    }

    @PostMapping("write")
    public String write(@ModelAttribute("post") Post post) {
        service = new PostServiceImpl();
        System.out.println(post); //작업테스트
        service.insert(post);
        return "redirect:list";
    }

    @RequestMapping("update")
    public String modify(@ModelAttribute("post") Post post) {
        service = new PostServiceImpl();
        service.modify(post);
        return "redirect:list";
    }

    @RequestMapping("delete")
    public String delete(@RequestParam("number") Long number) {
        service = new PostServiceImpl();
        service.delete(number);
        return "post/view";
    }
}
