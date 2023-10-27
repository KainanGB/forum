package forum.forum.dtos.response;

import java.util.List;

public record CommentDTO(
        Long user_id,
        Long comment_id,
        List<CommentDTO> childComments,
        String body,
        boolean deleted,
        List<Long> upvotes,
        String created_at,
        String updated_at
) {
}
