package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostMem {

    private final List<Post> posts = new ArrayList<>();
    private final AtomicInteger counter = new AtomicInteger();

    public PostMem() {
    }

    public List<Post> getAll() {
        return posts;
    }

    public void save(Post post) {
        if (post.getId() == 0) {
            post.setId(counter.incrementAndGet());
            posts.add(post);
        } else {
            posts.set(post.getId() - 1, post);
        }
    }

    public Optional<Post> findById(int id) {
        return Optional.ofNullable(posts.get(id - 1));
    }
}
