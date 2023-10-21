package forum.forum.dtos;

import java.util.UUID;

public record UserDTO(UUID user_id, String username, String email, String created_at) {
}
