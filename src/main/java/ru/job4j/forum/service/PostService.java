package ru.job4j.forum.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.store.AuthorityRepository;
import ru.job4j.forum.store.PostRepository;
import ru.job4j.forum.store.UserRepository;

import java.util.Collection;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthorityRepository authorityRepository;
    private final PasswordEncoder encoder;


    public PostService(PostRepository postRepository,
                       UserRepository userRepository,
                       AuthorityRepository authorityRepository,
                       PasswordEncoder encoder) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
        this.encoder = encoder;
    }

    public Collection<Post> getAll() {
        return (Collection<Post>) postRepository.findAll();
    }

    public void save(Post post) {
        String onlineUser = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User owner = userRepository.findUserByUsername(onlineUser)
                .orElseThrow();
        post.setUser(owner);
        postRepository.save(post);
    }

    public Optional<Post> findById(int id) {
        return postRepository.findById(id);
    }

    public void reg(User user) throws Exception {
        String name = user.getUsername();
        Optional<User> optionalUser = userRepository.findUserByUsername(name);
        if (optionalUser.isPresent()) {
            throw new Exception("Registration Error! User already exists!");
        } else {
            user.setEnabled(true);
            user.setPassword(encoder.encode(user.getPassword()));
            user.setAuthority(authorityRepository.findByAuthority("ROLE_USER"));
            userRepository.save(user);
        }
    }

    public void saveComment(Post post) {
        postRepository.save(post);
    }

    public boolean isOwner(int id) {
        Post post = postRepository.findById(id).orElseThrow();
        String onlineUser = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        String owner = post.getUser().getUsername();
        return owner.equals(onlineUser);
    }
}