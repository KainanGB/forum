package forum.forum.dtos.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateUserDTO(
    @Size(max = 4, min = 4, message = "username must have 4 characters")
    String username,
    @Email(message = "email is not valid")
    String email,
    @NotNull
    @NotEmpty(message = "password must not be empty")
    String password
)
{
}
