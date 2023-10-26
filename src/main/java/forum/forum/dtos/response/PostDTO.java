package forum.forum.dtos.response;


public record PostDTO(Long post_id, UserDTO author, String body, String title, String created_at, String updated_at) {
}
