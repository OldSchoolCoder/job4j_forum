package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostMem {

    private final Map<Integer, Post> posts = new HashMap();
    private final AtomicInteger counter = new AtomicInteger();

    public Collection<Post> getAll() {
        return posts.values();
    }

    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(counter.incrementAndGet());
        }
        posts.put(post.getId(), post);
    }

    public Optional<Post> findById(int id) {
        return Optional.ofNullable(posts.get(id));
    }
}
