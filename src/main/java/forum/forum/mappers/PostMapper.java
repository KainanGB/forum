package forum.forum.mappers;

import forum.forum.dtos.request.CreatePostDTO;
import forum.forum.dtos.request.PostRequestDTO;
import forum.forum.dtos.response.PostDTO;
import forum.forum.entities.PostsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper(componentModel = "spring")
public interface PostMapper {

  List<PostDTO> PostsEntityToPostDTO(List<PostsEntity> data);

  @Mapping(source = "postId", target = "post_id")
  @Mapping(source = "users", target = "author")
  @Mapping(source = "users.userId", target = "author.user_id")
  PostDTO PostsEntityToPostDTO(PostsEntity data);
  PostsEntity CreatePostDTOToPostsEntity(CreatePostDTO data);
  @Mapping(source = "post_id", target = "postId")
  PostsEntity PostRequestDTOToPostsEntity(PostRequestDTO data);
}
