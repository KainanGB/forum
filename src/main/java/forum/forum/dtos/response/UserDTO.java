package forum.forum.dtos.response;


public record UserDTO(
        Long user_id,
        String email,
        String username,
        String created_at
) {
}
