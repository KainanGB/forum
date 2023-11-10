package forum.forum.services;

import forum.forum.dtos.request.CreateUserDTO;
import forum.forum.dtos.request.UpdateUserDTO;
import forum.forum.dtos.response.UserDTO;
import forum.forum.entities.UsersEntity;
import forum.forum.mappers.UserMapper;
import forum.forum.repositories.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UsersServiceTest {
    @InjectMocks
    private UsersService usersService;
    @Mock
    private UserMapper userMapper;
    @Mock
    private UsersRepository usersRepository;
    @Mock
    private UsersEntity usersEntity;
    @Mock
    private UserDTO userDTO;
    @Mock
    private CreateUserDTO createUserDTO;
    @Mock
    private UpdateUserDTO updateUserDTO;


    @Test
    @DisplayName("T=[UsersService] M=[getUsers] should list all users.")
    void getUsers() {
        BDDMockito.given(usersRepository.findAll()).willReturn(List.of(usersEntity));

        List<UserDTO> users = usersService.getUsers();

        Assertions.assertEquals(users.size(), 0);
    }

    @Test
    void getUserById() {
        BDDMockito.given(usersRepository.findById(1L)).willReturn(Optional.of(usersEntity));
        BDDMockito.given(usersService.getUserById(1L)).willReturn(userDTO);

        UserDTO user = usersService.getUserById(1L);

        Assertions.assertNotNull(user);

        Throwable exception = Assertions
            .assertThrows(NoSuchElementException.class,
                () -> usersService.getUserById(2L));

        Assertions.assertEquals("No value present", exception.getMessage());

    }

    @Test
    void create() {
        CreateUserDTO createUserDto = new CreateUserDTO("kain", "email.contato@gmail.com", "123456");
        UserDTO expectedUserDto = new UserDTO(1L, "kain", "email.contato@gmail.com", "123456");

        Mockito.when(usersService.create(createUserDto)).thenReturn(expectedUserDto);
        UserDTO createdUser = usersService.create(createUserDTO);
        Assertions.assertEquals(createdUser, expectedUserDto);
        Assertions.assertEquals(createdUser.user_id(), expectedUserDto.user_id());
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
        Throwable exception = Assertions
            .assertThrows(NoSuchElementException.class,
                () ->  usersService.delete(1L));
    }
}