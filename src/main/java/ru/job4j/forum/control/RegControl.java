package ru.job4j.forum.control;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class RegControl {

    private static final Logger LOGGER = Logger.getLogger(RegControl
            .class.getName());
    private final PostService postService;

    public RegControl(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        postService.reg(user);
        return "redirect:/login";
    }

    @GetMapping("/reg")
    public String reg() {
        return "reg";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleException(DataIntegrityViolationException e,
                                  Model model) {
        model.addAttribute("error", e.getMessage());
        return "error";
    }
}
