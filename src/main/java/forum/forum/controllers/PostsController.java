package forum.forum.controllers;


import forum.forum.dtos.request.CreatePostDTO;
import forum.forum.dtos.response.PostDTO;
import forum.forum.entities.PostsEntity;
import forum.forum.services.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {

  private final PostsService postsService;

  @GetMapping
  public List<PostDTO> getPosts() {
    return postsService.getPosts();
  }

  @PostMapping

  public PostDTO create(@RequestBody CreatePostDTO data) {
    return postsService.create(data);
  }

  @PatchMapping("/{id}")
  public PostsEntity update(@PathVariable Long id, @RequestBody CreatePostDTO data) {
   return postsService.update(id, data);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    postsService.delete(id);
  }
}
