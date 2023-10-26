package forum.forum.mappers;

import forum.forum.dtos.request.CreateUserDTO;
import forum.forum.dtos.response.UserDTO;
import forum.forum.entities.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
@Mapper(componentModel = "spring")
public interface UserMapper {
  List<UserDTO> UserEntitiesToUserDTOs(List<UsersEntity> data);
  @Mapping(source = "userId", target = "user_id")
  UserDTO UserEntityToUserDTO(UsersEntity data);
  UsersEntity CreateUserDTOToUsersEntity(CreateUserDTO data);
}
