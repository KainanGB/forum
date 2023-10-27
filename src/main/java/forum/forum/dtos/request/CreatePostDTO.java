package forum.forum.dtos.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePostDTO(
        @NotNull(message = "user_id must be provided")
        Long user_id,
        @NotBlank(message = "title must be provided")
        String title,
        @NotBlank(message = "body must be provided")
        String body

) {
}
