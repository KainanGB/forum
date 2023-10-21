package forum.forum.dtos;

import java.util.Optional;
import java.util.UUID;

public record CreateCommentDTO(String body, String post_id, String parent_id) {
}
