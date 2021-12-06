package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class PostControl {

    private final PostService postService;
    private static final Logger LOGGER = Logger.getLogger(PostControl
            .class.getName());

    public PostControl(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/post")
    public String show(@RequestParam("id") int id, Model model) {
        Optional<Post> postOptional = postService.findById(id);
        model.addAttribute("post", postOptional.orElseThrow());
        return "post";
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        postService.save(post);
        return "redirect:/";
    }

    @PostMapping("/addComment")
    public String addComment(@RequestParam("id") int id,
                             HttpServletRequest request,
                             Model model) {
        String comment = request.getParameter("comment");
        Optional<Post> postOptional = postService.findById(id);
        Post post = postOptional.orElseThrow();
        post.addComment(comment);
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/update")
    public String edit(@RequestParam("id") int id, Model model) {
        Optional<Post> postOptional = postService.findById(id);
        model.addAttribute("post", postOptional.orElseThrow());
        return "edit";
    }
}
