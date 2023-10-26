package forum.forum.dtos.request;


public record CreatePostDTO(String title, String body, Long user_id) {
}
