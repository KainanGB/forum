package forum.forum.services;


import forum.forum.dtos.request.CreateUserDTO;
import forum.forum.dtos.request.UpdateUserDTO;
import forum.forum.dtos.response.UserDTO;
import forum.forum.entities.UsersEntity;
import forum.forum.mappers.UserMapper;
import forum.forum.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UsersService {

  private final UsersRepository usersRepository;
  private final UserMapper userMapper;

  public List<UserDTO> getUsers() {
    return userMapper.UserEntitiesToUserDTOs(usersRepository.findAll());
  }


  public UserDTO getUserById(@PathVariable Long id){
    return userMapper.UserEntityToUserDTO(usersRepository.findById(id).orElseThrow());
  }

  public UserDTO getUserProfile (@PathVariable Long id) {
    return userMapper.UserEntityToUserDTO(usersRepository.findById(id).orElseThrow());
  }

  public UserDTO create(@RequestBody CreateUserDTO data) {
    UsersEntity user = usersRepository.save(userMapper.CreateUserDTOToUsersEntity(data));
    return userMapper.UserEntityToUserDTO(user);
  }

  public UserDTO update(@RequestBody UpdateUserDTO data, @PathVariable Long id) {
    var user = usersRepository.findById(id).orElseThrow();
    user.setUsername(data.username());
    usersRepository.save(user);
    return userMapper.UserEntityToUserDTO(user);
  }

  public void delete(@PathVariable Long id){
    usersRepository.deleteById(id);
  }


}
