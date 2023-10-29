package forum.forum.controllers;


import forum.forum.Logger.Log;
import forum.forum.dtos.request.CreatePostDTO;
import forum.forum.dtos.request.PostRequestDTO;
import forum.forum.dtos.response.PostDTO;
import forum.forum.entities.PostsEntity;
import forum.forum.services.PostsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostsController {

  private final PostsService postsService;

  @GetMapping
  @ResponseStatus(value = HttpStatus.OK)
  @Log
  public Page<PostDTO> getPosts(PostRequestDTO data, @PageableDefault Pageable pageable) {
    return postsService.getPosts(data, pageable);
  }

  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  @Log
  public PostDTO create(@RequestBody @Valid CreatePostDTO data) {
    return postsService.create(data);
  }
  @PatchMapping("/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  @Log
  public PostsEntity update(@PathVariable Long id, @RequestBody @Valid CreatePostDTO data) {
   return postsService.update(id, data);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  @Log
  public void delete(@PathVariable Long id) {
    postsService.delete(id);
  }
}
