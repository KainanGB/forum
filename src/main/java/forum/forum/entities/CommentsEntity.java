package forum.forum.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


// TODO: CHECK THIS ANNOTATIONS
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Comments")
public class CommentsEntity {

  @Id
  @Column(name="comment_id",  unique = true, updatable = false)
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long commentId;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private UsersEntity users;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "post_id", updatable = false)
  private PostsEntity posts;

  @Column(
          name = "body", updatable = true
  )
  public String body;

  // TODO: CHECK CASCADETYPES, FETCHTYPE,
  @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
  @JoinTable(
          name = "comment_comments",
          joinColumns = @JoinColumn(name = "parent_id"),
          inverseJoinColumns = @JoinColumn(name = "child_id")
  )
  private List<CommentsEntity> childComments = new ArrayList<>();


  @ManyToMany(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
  @JoinTable(
          name = "comment_upvotes",
          joinColumns = @JoinColumn(name = "comment_id", referencedColumnName = "comment_id", nullable = false, updatable = false),
          inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, updatable = false)
  )
  private List<UsersEntity> upvotes;

  @Column(name = "deleted", nullable = false)
  private boolean deleted;

  @Column(name = "created_at", updatable = false)
  @CreationTimestamp
  private Timestamp created_at;

  @Column(name = "updated_at", updatable = true)
  @UpdateTimestamp
  private Timestamp updated_at;
}
