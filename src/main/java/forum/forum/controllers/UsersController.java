package forum.forum.controllers;

import forum.forum.dtos.UserDTO;
import forum.forum.dtos.UpdateUserDTO;
import forum.forum.dtos.CreateUserDTO;
import forum.forum.dtos.UserIdDTO;
import forum.forum.entities.UsersEntity;
import forum.forum.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

  private final UsersRepository usersRepository;

  @GetMapping
  public List<UserDTO> getUsers() {
    return usersRepository.findAll()
            .stream()
            .map(x -> new UserDTO(x.getUserId(), x.getUsername(), x.getEmail(), x.getCreated_at().toString()))
            .collect(Collectors.toList());
  }

  @GetMapping("/{id}")
  public UserDTO getUserById(@PathVariable UserDTO dataDTO){
    UsersEntity user =  usersRepository.findById(dataDTO.user_id()).orElseThrow(() -> new NoSuchElementException("User not found"));
    return new UserDTO(user.getUserId(), user.getUsername(), user.getEmail(), user.getCreated_at().toString());
  }

  @GetMapping("/me/{user_id}")
  public UserDTO getUserProfile (@PathVariable UserDTO dataDTO) {
    UsersEntity user =  usersRepository.findById(dataDTO.user_id()).orElseThrow();
    return new UserDTO(user.getUserId(), user.getUsername(), user.getEmail(), user.getCreated_at().toString());
  }

  @PostMapping
  public UserDTO create(@RequestBody CreateUserDTO data) {
    var user = new UsersEntity();
    user.setUsername(data.username());
    user.setPassword(data.password());
    user.setEmail(data.email());
    usersRepository.save(user);

    return new UserDTO(user.getUserId(), user.getUsername(), user.getEmail(), user.getCreated_at().toString());
  }

  @PatchMapping("/{id}")
  public UserDTO update(@RequestBody UpdateUserDTO data, @PathVariable UUID id) {
    UsersEntity user =  usersRepository.findById(id).orElseThrow();
    user.setUsername(data.username());
    usersRepository.save(user);

    return new UserDTO(user.getUserId(), user.getUsername(), user.getEmail(), user.getCreated_at().toString());
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable UUID id){
    usersRepository.deleteById(id);
  }
}
