package forum.forum.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record AuthenticateUser(
    @Email(message = "email is not valid")
    String email,
    @NotNull
    @NotEmpty(message = "password must not be empty")
    String password
) {
}
