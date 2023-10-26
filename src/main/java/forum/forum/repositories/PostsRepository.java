package forum.forum.repositories;

import forum.forum.entities.PostsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PostsRepository extends JpaRepository<PostsEntity, Long> {
}
