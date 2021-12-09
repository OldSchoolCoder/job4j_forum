package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostMem;
import ru.job4j.forum.store.PostRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Collection<Post> getAll() {
        return (Collection<Post>) postRepository.findAll();
    }

    public void save(Post post) {
        postRepository.save(post);
    }

    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }
}