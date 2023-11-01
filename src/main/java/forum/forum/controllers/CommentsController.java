package forum.forum.controllers;


import forum.forum.Logger.Log;
import forum.forum.dtos.request.CreateCommentDTO;
import forum.forum.dtos.request.UpdateCommentDTO;
import forum.forum.dtos.response.CommentDTO;
import forum.forum.dtos.response.UserDTO;
import forum.forum.services.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentsController {

  private final CommentsService commentsService;
  @GetMapping
  @ResponseStatus(value = HttpStatus.OK)
  @Log
  public List<CommentDTO> getComments(@RequestParam Long post_id) {
    return commentsService.getComments(post_id);
  }
  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  @Log
  public CommentDTO create(@RequestBody CreateCommentDTO data) {
    return commentsService.create(data);
  }

  // TODO: CHECK FOR SAVEANDFLUSH
  @PostMapping("/{comment_id}/upvotes")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  @Log
  public void upvote(@PathVariable Long comment_id, @RequestBody UserDTO dataDTO) {
    commentsService.upvote(comment_id, dataDTO);
  }

  @PatchMapping
  @ResponseStatus(value = HttpStatus.OK)
  @Log
  public CommentDTO update(@RequestBody UpdateCommentDTO data) {
      return commentsService.update(data);
  }

  @DeleteMapping(path = "/{comment_id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  @Log
  public void delete(@PathVariable Long comment_id) {
    commentsService.delete(comment_id);
  }

}
