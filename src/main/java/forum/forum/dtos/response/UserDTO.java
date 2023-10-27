package forum.forum.dtos.response;


public record UserDTO(
        Long user_id,
        String username,
        String email,
        String created_at
) {
}
