package forum.forum.controllers;


import forum.forum.dtos.CreatePostDTO;
import forum.forum.entities.PostsEntity;
import forum.forum.repositories.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {

  private final PostsRepository postsRepository;

  @GetMapping
  public List<PostsEntity> getPosts() {
    return postsRepository.findAll();
  }

  @PostMapping
  public PostsEntity create(@RequestBody CreatePostDTO data) {
    var post = new PostsEntity();
    post.setBody(data.body());
    post.setTitle(data.title());
    return postsRepository.save(post);
  }

  @PatchMapping("/{id}")
  public PostsEntity update(@PathVariable UUID id, @RequestBody CreatePostDTO data) {
    var post = postsRepository.findById(id).orElseThrow();
    post.setTitle(data.title());
    post.setBody(data.body());
    return postsRepository.save(post);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable UUID id) {
     postsRepository.deleteById(id);
  }
}
