package forum.forum.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UpdateCommentDTO(
        @NotNull(message = "comment_id must be provided")
        Long comment_id,
        String body
) {
}
