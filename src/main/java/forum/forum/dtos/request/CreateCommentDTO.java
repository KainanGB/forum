package forum.forum.dtos.request;


public record CreateCommentDTO(String body, Long user_id, Long post_id, Long parent_id) {
}
