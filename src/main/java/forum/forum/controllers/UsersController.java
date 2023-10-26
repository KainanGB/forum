package forum.forum.controllers;

import forum.forum.dtos.response.UserDTO;
import forum.forum.dtos.request.UpdateUserDTO;
import forum.forum.dtos.request.CreateUserDTO;
import forum.forum.entities.UsersEntity;
import forum.forum.mappers.UserMapper;
import forum.forum.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

  private final UsersRepository usersRepository;
  private final UserMapper userMapper;

  @GetMapping
  public List<UserDTO> getUsers() {
    return userMapper.UserEntitiesToUserDTOs(usersRepository.findAll());
  }

  @GetMapping("/{id}")
  public UserDTO getUserById(@PathVariable Long id){
    return userMapper.UserEntityToUserDTO(usersRepository.findById(id).orElseThrow());
  }

  @GetMapping("/me/{id}")
  public UserDTO getUserProfile (@PathVariable Long id) {
    return userMapper.UserEntityToUserDTO(usersRepository.findById(id).orElseThrow());
  }

  @PostMapping
  public UserDTO create(@RequestBody CreateUserDTO data) {
    UsersEntity user = usersRepository.save(userMapper.CreateUserDTOToUsersEntity(data));
    return userMapper.UserEntityToUserDTO(user);
  }

  @PatchMapping("/{id}")
  public UserDTO update(@RequestBody UpdateUserDTO data, @PathVariable Long id) {
    var user = usersRepository.findById(id).orElseThrow();
    user.setUsername(data.username());
    usersRepository.save(user);
    return userMapper.UserEntityToUserDTO(user);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){
    usersRepository.deleteById(id);
  }
}
