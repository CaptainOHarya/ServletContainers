package ru.netology.repository;

import ru.netology.model.Post;

import java.util.*;

// Stub
public class PostRepository {
    private Map<Long, Post> posts = new HashMap<>();
    private long id;

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
    public synchronized Post save(Post post) {
        Post newPost = new Post(id, post.getContent());
        posts.put(id, newPost);
        // id++;
        return post;
    }

    // remove Post
    public synchronized void removeById(long id) {
        posts.remove(id);
    }

    public synchronized Post change(Post post) {
        posts.get(post.getId()).setContent(post.getContent());
        return posts.get(post.getId());
    }

    public synchronized boolean isContainsId(Long id) {
        if (posts.containsKey(id))
            return true;
        return false;
    }

    public synchronized Long getNewId() {
        return id++;
    }
}
