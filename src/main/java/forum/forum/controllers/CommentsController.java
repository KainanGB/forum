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
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentsController {

  private final CommentsRepository commentsRepository;
  private final PostsRepository postsRepository;
  private final UsersRepository  usersRepository;
  private final CommentMapper commentMapper;

  @GetMapping
  public List<CommentDTO> getComments(@RequestParam Long post_id) {
    return commentMapper.CommentsEntityToCommentDTO(commentsRepository.findByPostsPostId(post_id));
  }
  @PostMapping
  public CommentDTO create(@RequestBody CreateCommentDTO data) {
    UsersEntity user = usersRepository.findById(data.user_id()).orElseThrow();

    var comment = CommentsEntity.builder()
            .body(data.body()).users(user)
            .build();
    PostsEntity post = postsRepository.findById(data.post_id()).orElseThrow();

    // CHECK IF HAS PAYLOAD HAS PARENT_ID, THEN CREATES A CHILD COMMENT.
    if(data.parent_id() != null) {
      CommentsEntity parentComment = commentsRepository.findById(data.parent_id()).orElseThrow();
      parentComment.getChildComments().add(comment);
      return commentMapper.CommentsEntityToCommentDTO(commentsRepository.save(parentComment));
    }

    // TODO: CHANGE PARENT RELATIONSHIP TO POST -> COMMENTS
    comment.setPosts(post);
    return commentMapper.CommentsEntityToCommentDTO(commentsRepository.save(comment));
  }

  // TODO: CHECK FOR SAVEANDFLUSH
  @PostMapping("/{comment_id}/upvotes")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void upvote(@PathVariable Long comment_id, @RequestBody UserDTO dataDTO) {
    CommentsEntity comment = commentsRepository.findById(comment_id).orElseThrow();
    UsersEntity user = usersRepository.findById(dataDTO.user_id()).orElseThrow();

   try{
     comment.getUpvotes().add(user);
     commentsRepository.saveAndFlush(comment);
   } catch (DataIntegrityViolationException err){
     comment.getUpvotes().remove(user);
     commentsRepository.saveAndFlush(comment);
   };
  }


  @PatchMapping
  public CommentDTO update(@RequestBody UpdateCommentDTO data) {
      var comment = commentsRepository.findByCommentIdAndDeletedFalse(data.comment_id()).orElseThrow();
      comment.setBody(data.body());
      return commentMapper.CommentsEntityToCommentDTO(commentsRepository.save(comment));
  }


  @DeleteMapping(path = "/{comment_id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long comment_id) {
    CommentsEntity parentComment = commentsRepository.findById(comment_id).orElseThrow();
    parentComment.setDeleted(true);
    commentsRepository.save(parentComment);
  }

}
