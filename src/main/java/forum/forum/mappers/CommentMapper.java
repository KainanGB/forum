package forum.forum.mappers;

import forum.forum.dtos.response.CommentDTO;
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
    return user.getUserId();
  }
  @Named("mapCommentId")
  default Long mapCommentId(Long commentId) {
    return commentId;
  }
}
