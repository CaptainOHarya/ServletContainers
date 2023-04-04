package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

// Stub
public class PostRepository {
    private Map<Long, Post> posts = new ConcurrentHashMap<>();
    private AtomicLong id = new AtomicLong();

    // Method of repository realizing
    // return all Post
    public List<Post> all() {
        List<Post> postList = new ArrayList<>(posts.values());
        return postList;
    }

    // return by id
    public Optional<Post> getById(long id) {
        // assume the transmitted value can be null
        Optional<Post> opt = Optional.ofNullable(posts.get(id));
        return opt;
    }

    // save Post
    public Post save(Post post) {
        Post newPost = new Post(post.getId(), post.getContent());
        posts.put(post.getId(), newPost);
        return post;
    }

    // remove Post
    public void removeById(long id) {
        posts.remove(id);
    }

    public Post change(Post post) {
        posts.get(post.getId()).setContent(post.getContent());
        return posts.get(post.getId());
    }

    public boolean isContainsId(Long id) {
        if (posts.containsKey(id))
            return true;
        return false;
    }

    public Long getNewId() {
        return id.incrementAndGet();
    }
}
