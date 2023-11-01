package forum.forum.dtos.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public record CreateUserDTO(
    @Size(max = 4, min = 4, message = "username must have 4 characters")
    String username,
    @Email(message = "email is not valid")
    String email,
    @NotNull
    @NotEmpty(message = "password must not be empty")
    String password
) {
    public CreateUserDTO(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = encodePassword(password);
    }

    private String encodePassword(String password) {
        var encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
