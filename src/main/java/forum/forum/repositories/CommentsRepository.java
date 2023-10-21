package forum.forum.repositories;

import forum.forum.entities.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentsRepository extends JpaRepository<CommentsEntity, UUID> {
  List<CommentsEntity> findByPostsPostId(UUID post_id);

  // TODO: CHECK IF DELETED IS FALSE
  Optional<CommentsEntity> findByCommentIdAndDeletedFalse(UUID comment_id);

}
