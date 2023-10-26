package forum.forum.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "POSTS")
public class PostsEntity {

  @Id
  @Column(name="post_id",  unique = true)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long postId;

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
