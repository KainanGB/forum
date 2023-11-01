package forum.forum.repositories;

import forum.forum.entities.CommentsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CommentsRepository extends JpaRepository<CommentsEntity, Long> {
  List<CommentsEntity> findByPostsPostId(Long post_id);

  // TODO: CHECK IF DELETED IS FALSE
  Optional<CommentsEntity> findByCommentIdAndDeletedFalse(Long comment_id);

}
