package forum.forum.dtos;

import java.util.UUID;

public record CreatePostDTO(String title, String body, UUID user_id) {
}
