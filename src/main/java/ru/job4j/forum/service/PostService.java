package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostMem;

import java.util.Collection;
import java.util.Optional;

@Service
public class PostService {

    private final PostMem postMem;

    public PostService(PostMem postMem) {
        this.postMem = postMem;
    }

    public Collection<Post> getAll() {
        return postMem.getAll();
    }

    public void save(Post post) {
        postMem.save(post);
    }

    public Optional<Post> findById(int id) {
        return postMem.findById(id);
    }
}