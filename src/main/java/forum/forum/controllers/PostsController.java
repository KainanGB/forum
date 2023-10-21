package forum.forum.controllers;


import com.fasterxml.jackson.annotation.JsonIgnore;
import forum.forum.dtos.CreatePostDTO;
import forum.forum.entities.PostsEntity;
import forum.forum.entities.UsersEntity;
import forum.forum.repositories.PostsRepository;
import forum.forum.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {

  private final PostsRepository postsRepository;
  private final UsersRepository usersRepository;

  @GetMapping
  public List<PostsEntity> getPosts() {
    return postsRepository.findAll();
  }

  @PostMapping

  public PostsEntity create(@RequestBody CreatePostDTO data) {
    var post = new PostsEntity();
    post.setBody(data.body());
    post.setTitle(data.title());
    UsersEntity user = usersRepository.findById(data.user_id()).orElseThrow();
    post.setUsers(user);
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
