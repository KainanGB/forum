package forum.forum.controllers;

import forum.forum.Logger.Log;
import forum.forum.dtos.request.CreateUserDTO;
import forum.forum.dtos.request.UpdateUserDTO;
import forum.forum.dtos.response.UserDTO;
import forum.forum.services.UsersService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Users", description = "Handle with CRUD for users")
public class UsersController {

  private final UsersService userService;

  @Log
  @GetMapping
  @ResponseStatus(value = HttpStatus.OK)
  public List<UserDTO> getUsers() {
    return userService.getUsers();
  }
  @Log
  @GetMapping("/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public UserDTO getUserById(@PathVariable Long id){
    return userService.getUserById(id);
  }
  @Log
  @GetMapping("/me/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public UserDTO getUserProfile (@PathVariable Long id) {
    return userService.getUserProfile(id);
  }

  @Log
  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public UserDTO create(@RequestBody @Valid CreateUserDTO data) {
    return userService.create(data);
  }
  @Log
  @PatchMapping("/{id}")
  @ResponseStatus(value = HttpStatus.OK)
  public UserDTO update(@RequestBody @Valid UpdateUserDTO data, @PathVariable Long id) {
    return userService.update(data, id);
  }
  @Log
  @DeleteMapping("/{id}")
  @ResponseStatus(value = HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id){
    userService.delete(id);
  }
}
