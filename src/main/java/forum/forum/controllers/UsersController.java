package forum.forum.controllers;

import forum.forum.dtos.UpdateUserDTO;
import forum.forum.dtos.CreateUserDTO;
import forum.forum.entities.UsersEntity;
import forum.forum.repositories.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

  private final UsersRepository usersRepository;

  @GetMapping
  public List<UsersEntity> getUsers() {
    return usersRepository.findAll();
  }

  @GetMapping("/{id}")
  public Optional<UsersEntity> getUserById(@PathVariable UUID id){
    return usersRepository.findById(id);
  }

  @PostMapping
  public UsersEntity create(@RequestBody CreateUserDTO data) {
    var user = new UsersEntity();
    user.setUsername(data.username());
    user.setPassword(data.password());
    user.setEmail(data.email());

    return usersRepository.save(user);
  }

  @PatchMapping("/{id}")
  public UsersEntity update(@RequestBody UpdateUserDTO data, @PathVariable UUID id) {
    UsersEntity user =  usersRepository.findById(id).orElseThrow();
    user.setUsername(data.username());
    return usersRepository.save(user);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable UUID id){
    usersRepository.deleteById(id);
  };
}
