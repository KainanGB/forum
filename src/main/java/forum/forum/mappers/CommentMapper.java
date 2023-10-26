package forum.forum.mappers;

import forum.forum.dtos.response.CommentDTO;
import forum.forum.dtos.response.UserDTO;
import forum.forum.entities.CommentsEntity;
import forum.forum.entities.UsersEntity;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)

public interface CommentMapper {
  List<CommentDTO> CommentsEntityToCommentDTO(List<CommentsEntity> data);
  @Mappings({
          @Mapping(source = "commentId", target = "comment_id", qualifiedByName = "mapCommentId"),
          @Mapping(source = "users.userId", target = "user_id"),
          @Mapping(source = "upvotes", target = "upvotes", qualifiedByName = "mapUpvotes")
  })
  CommentDTO CommentsEntityToCommentDTO(CommentsEntity data);


  @Named("mapUpvotes")
  default Long mapUpvote(UsersEntity user) {
    return user.getUserId(); // Or handle the case where upvote is null
  }
  @Named("mapCommentId")
  default Long mapCommentId(Long commentId) {
    // Check if commentId exists (not null) before mapping
    if (commentId != null) {
      return commentId;
    }
    // If commentId is null, you can return a default value or handle it as needed.
    // Here, we return null, but you can change it to a default value or any other logic.
    return null;

    // COULD ONLY RETURN commentId because we would be null or valued.
  }
}
