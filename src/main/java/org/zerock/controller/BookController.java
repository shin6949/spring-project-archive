package org.zerock.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.KeepBookService;

@Controller
@RequestMapping("/book/*")
@AllArgsConstructor
@Log4j
public class BookController {
    private final KeepBookService keepBookService;

    @GetMapping("/list")
    public String list(Criteria cri, Model model) {
        log.info("list: " + cri);
        model.addAttribute("list", keepBookService.selectKeepBookWithPaging(cri));

        int total = keepBookService.getTotalCount();
        log.info("total: " + total);

        model.addAttribute("pageMaker", new PageDTO(cri, total));

        return "book/list";
    }
}
