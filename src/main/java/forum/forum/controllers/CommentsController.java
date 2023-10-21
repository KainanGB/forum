package forum.forum.controllers;


import forum.forum.dtos.CreateCommentDTO;
import forum.forum.dtos.UpdateCommentDTO;
import forum.forum.dtos.UserIdDTO;
import forum.forum.entities.CommentsEntity;
import forum.forum.entities.PostsEntity;
import forum.forum.entities.UsersEntity;
import forum.forum.repositories.CommentsRepository;
import forum.forum.repositories.PostsRepository;
import forum.forum.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentsController {

  private final CommentsRepository commentsRepository;
  private final PostsRepository postsRepository;
  private final UsersRepository  usersRepository;

  @GetMapping
  public List<CommentsEntity> getComments(@RequestParam String post_id) {
    return commentsRepository.findByPostsPostId(UUID.fromString(post_id));
  }
  @PostMapping
  public CommentsEntity create(@RequestBody CreateCommentDTO data) {
    var comment = CommentsEntity.builder()
            .body(data.body())
            .build();

    PostsEntity post = postsRepository.findById(UUID.fromString(data.post_id())).orElseThrow();

    // CHECK IF HAS PAYLOAD HAS PARENT_ID, THEN CREATES A CHILD COMMENT.
    if(data.parent_id() != null) {
      CommentsEntity parentComment = commentsRepository.findById(UUID.fromString(data.parent_id())).orElseThrow();
      parentComment.getChildComments().add(comment);
      return commentsRepository.save(parentComment);
    }

    // TODO: CHANGE PARENT RELATIONSHIP TO POST -> COMMENTS
    comment.setPosts(post);
    return commentsRepository.save(comment);
  }

  // TODO: CHECK FOR SAVEANDFLUSH
  @PostMapping("/{comment_id}/upvotes")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void upvote(@PathVariable UUID comment_id, @RequestBody UserIdDTO dataDTO) {
    CommentsEntity comment = commentsRepository.findById(comment_id).orElseThrow();
    UsersEntity user = usersRepository.findById(dataDTO.user_id()).orElseThrow();

    var hasUpvote = comment.getUpvotes()
            .stream()
            .filter(x -> x.getUserId().toString().equals(dataDTO.user_id().toString()))
            .count();

    if(hasUpvote == 0) {
      comment.getUpvotes().add(user);
      commentsRepository.saveAndFlush(comment);
      return;
    }

    comment.getUpvotes().remove(user);
    commentsRepository.saveAndFlush(comment);
  }


  @PatchMapping
  public CommentsEntity update(@RequestBody UpdateCommentDTO data) {
      var comment = commentsRepository.findByCommentIdAndDeletedFalse(UUID.fromString(data.comment_id())).orElseThrow();
      comment.setBody(data.body());
      return commentsRepository.save(comment);
  }


  @DeleteMapping(path = "/{comment_id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable UUID comment_id) {
    CommentsEntity parentComment = commentsRepository.findById(comment_id).orElseThrow();
    parentComment.setDeleted(true);
    commentsRepository.save(parentComment);
  }

}
