package forum.forum.repositories;

import forum.forum.entities.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;



@Repository

public interface UsersRepository extends JpaRepository<UsersEntity, UUID> {

}
