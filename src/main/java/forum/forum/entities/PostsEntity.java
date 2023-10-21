package forum.forum.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "POSTS")
public class PostsEntity {

  @Id
  @Column(name="post_id",  unique = true, columnDefinition = "BINARY(16)")
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID postId;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UsersEntity users;

  @Column(name = "upvote")
  private Long upvote;

  @Column(name = "title", nullable = false)
  @NotEmpty(message = "username must not be empty.")
  private String title;

  @Column(name = "body", nullable = false)
  @NotEmpty(message = "username must not be empty.")
  private String body;

  @OneToMany(mappedBy = "posts", cascade = CascadeType.ALL)
  @JsonIgnore
  private List<CommentsEntity> comments;

  @Column(name = "created_at")
  @CreationTimestamp
  private Timestamp created_at;

  @Column(name = "updated_at")
  @UpdateTimestamp
  private Timestamp updated_at;
}
