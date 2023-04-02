package ru.netology.service;

import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;
import ru.netology.repository.PostRepository;

import java.util.List;

public class PostService {
  private final PostRepository repository;

  public PostService(PostRepository repository) {
    this.repository = repository;
  }

  public List<Post> all() {
    return repository.all();
  }

  public Post getById(long id) {
    return repository.getById(id).orElseThrow(NotFoundException::new);
  }


  public Post save(Post post) {
    if (post.getId() == 0) {
      post.setId(repository.getNewId());
      return repository.save(post);
    } if (repository.isContainsId(post.getId())) {
      return repository.change(post);
    }
    throw new NotFoundException("Post not found!!!");


  }

  public void removeById(long id) {
    repository.removeById(id);
  }
}

