package forum.forum.controllers;

import forum.forum.dtos.response.UserDTO;
import forum.forum.dtos.request.UpdateUserDTO;
import forum.forum.dtos.request.CreateUserDTO;
import forum.forum.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {


  private final UsersService userService;

  @GetMapping
  public List<UserDTO> getUsers() {
    return userService.getUsers();
  }

  @GetMapping("/{id}")
  public UserDTO getUserById(@PathVariable Long id){
    return userService.getUserById(id);
  }

  @GetMapping("/me/{id}")
  public UserDTO getUserProfile (@PathVariable Long id) {
    return userService.getUserProfile(id);
  }

  @PostMapping
  public UserDTO create(@RequestBody CreateUserDTO data) {
    return userService.create(data);
  }

  @PatchMapping("/{id}")
  public UserDTO update(@RequestBody UpdateUserDTO data, @PathVariable Long id) {

    return userService.update(data, id);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id){
    userService.delete(id);
  }
}
