package forum.forum.dtos.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CreateCommentDTO(
        @NotNull(message = "user_id must not be null")
        Long user_id,

        @NotNull(message = "post_id must not be null")
        Long post_id,
        @NotNull(message = "parent_id must not be null")
        Long parent_id,
        @NotBlank(message = "body must be provided")
        String body
) {
}
