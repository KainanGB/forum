package forum.forum.controllers;


import forum.forum.dtos.request.CreatePostDTO;
import forum.forum.dtos.response.PostDTO;
import forum.forum.entities.PostsEntity;
import forum.forum.entities.UsersEntity;
import forum.forum.mappers.PostMapper;
import forum.forum.mappers.UserMapper;
import forum.forum.repositories.PostsRepository;
import forum.forum.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {

  private final PostsRepository postsRepository;
  private final UsersRepository usersRepository;
  private final PostMapper postMapper;

  @GetMapping
  public List<PostDTO> getPosts() {
    return postMapper.PostsEntityToPostDTO(postsRepository.findAll());
  }

  @PostMapping

  public PostDTO create(@RequestBody CreatePostDTO data) {
    var post = PostsEntity.builder()
            .body(data.body())
            .title(data.title())
            .build();

    post.setUsers(usersRepository.findById(data.user_id()).orElseThrow());
    return postMapper.PostsEntityToPostDTO(postsRepository.save(post));
  }

  @PatchMapping("/{id}")
  public PostsEntity update(@PathVariable Long id, @RequestBody CreatePostDTO data) {
    var post = postsRepository.findById(id).orElseThrow();
    post.setTitle(data.title());
    post.setBody(data.body());
    return postsRepository.save(post);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
     postsRepository.deleteById(id);
  }
}
