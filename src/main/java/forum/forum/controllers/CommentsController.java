package forum.forum.controllers;


import forum.forum.Logger.Log;
import forum.forum.dtos.request.CreateCommentDTO;
import forum.forum.dtos.request.UpdateCommentDTO;
import forum.forum.dtos.response.CommentDTO;
import forum.forum.dtos.response.UserDTO;
import forum.forum.services.CommentsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Comments", description = "Handle with CRUD for comments")
public class CommentsController {

  private final CommentsService commentsService;
  @Log
  @GetMapping
  @ResponseStatus(value = HttpStatus.OK)
  public List<CommentDTO> getComments(@RequestParam Long post_id) {
    return commentsService.getComments(post_id);
  }
  @Log
  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)

  public CommentDTO create(@RequestBody CreateCommentDTO data) {
    return commentsService.create(data);
  }

  // TODO: CHECK FOR SAVEANDFLUSH
  @Log
  @PostMapping("/{comment_id}/upvotes")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void upvote(@PathVariable Long comment_id, @RequestBody UserDTO dataDTO) {
    commentsService.upvote(comment_id, dataDTO);
  }


  @Log
  @PatchMapping
  @ResponseStatus(value = HttpStatus.OK)
  public CommentDTO update(@RequestBody UpdateCommentDTO data) {
      return commentsService.update(data);
  }


  @Log
  @DeleteMapping(path = "/{comment_id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long comment_id) {
    commentsService.delete(comment_id);
  }

}
