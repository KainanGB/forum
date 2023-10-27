package forum.forum.controllers;


import forum.forum.dtos.request.CreateCommentDTO;
import forum.forum.dtos.request.UpdateCommentDTO;
import forum.forum.dtos.response.CommentDTO;
import forum.forum.dtos.response.UserDTO;
import forum.forum.entities.CommentsEntity;
import forum.forum.entities.PostsEntity;
import forum.forum.entities.UsersEntity;
import forum.forum.mappers.CommentMapper;
import forum.forum.repositories.CommentsRepository;
import forum.forum.repositories.PostsRepository;
import forum.forum.repositories.UsersRepository;
import forum.forum.services.CommentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentsController {

  private final CommentsService commentsService;

  @GetMapping
  public List<CommentDTO> getComments(@RequestParam Long post_id) {
    return commentsService.getComments(post_id);
  }
  @PostMapping
  public CommentDTO create(@RequestBody CreateCommentDTO data) {
    return commentsService.create(data);
  }

  // TODO: CHECK FOR SAVEANDFLUSH
  @PostMapping("/{comment_id}/upvotes")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void upvote(@PathVariable Long comment_id, @RequestBody UserDTO dataDTO) {
    commentsService.upvote(comment_id, dataDTO);
  }


  @PatchMapping
  public CommentDTO update(@RequestBody UpdateCommentDTO data) {
      return commentsService.update(data);
  }


  @DeleteMapping(path = "/{comment_id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long comment_id) {
    commentsService.delete(comment_id);
  }

}
