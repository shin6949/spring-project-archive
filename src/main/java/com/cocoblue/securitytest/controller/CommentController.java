package com.cocoblue.securitytest.controller;

import com.cocoblue.securitytest.dto.Comment;
import com.cocoblue.securitytest.service.CommentService;
import com.cocoblue.securitytest.service.PostService;
import com.cocoblue.securitytest.service.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/comment")
public class CommentController {
    CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("insert/{postId}")
    public String insertComment(@ModelAttribute Comment comment, @PathVariable long postId, Model model) {
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            model.addAttribute("status", "login");
            return "posts/commentstatus";
        }

        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        comment.setWriterId(customUserDetails.getId());
        comment.setPostId(postId);

        if(commentService.writeComment(comment)) {
            model.addAttribute("status", "success");
        } else {
            model.addAttribute("status", "server");
        }
        return "posts/commentstatus";
    }
}
