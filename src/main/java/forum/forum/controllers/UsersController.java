package forum.forum.controllers;

import forum.forum.dtos.response.UserDTO;
import forum.forum.dtos.request.UpdateUserDTO;
import forum.forum.dtos.request.CreateUserDTO;
import forum.forum.services.UsersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

  private final UsersService userService;

  @GetMapping
  @ResponseStatus(value = HttpStatus.OK)
  public List<UserDTO> getUsers() {
    return userService.getUsers();
  }
  @GetMapping("/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public UserDTO getUserById(@PathVariable Long id){
    return userService.getUserById(id);
  }
  @GetMapping("/me/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public UserDTO getUserProfile (@PathVariable Long id) {
    return userService.getUserProfile(id);
  }
  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public UserDTO create(@RequestBody @Valid CreateUserDTO data) {
    return userService.create(data);
  }
  @PatchMapping("/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public UserDTO update(@RequestBody @Valid UpdateUserDTO data, @PathVariable Long id) {
    return userService.update(data, id);
  }
  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id){
    userService.delete(id);
  }
}
