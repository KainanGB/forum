package forum.forum.controllers;


import forum.forum.Logger.Log;
import forum.forum.dtos.request.CreatePostDTO;
import forum.forum.dtos.request.PostRequestDTO;
import forum.forum.dtos.response.PostDTO;
import forum.forum.entities.PostsEntity;
import forum.forum.services.PostsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Posts", description = "Handle with CRUD for Posts")
public class PostsController {

  private final PostsService postsService;

  @Log
  @GetMapping
  @ResponseStatus(value = HttpStatus.OK)
  public Page<PostDTO> getPosts(PostRequestDTO data, @PageableDefault Pageable pageable) {
    return postsService.getPosts(data, pageable);
  }


  @Log
  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public PostDTO create(@RequestBody @Valid CreatePostDTO data) {
    return postsService.create(data);
  }


  @Log
  @PatchMapping("/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public PostsEntity update(@PathVariable Long id, @RequestBody @Valid CreatePostDTO data) {
   return postsService.update(id, data);
  }

  @Log
  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    postsService.delete(id);
  }
}
