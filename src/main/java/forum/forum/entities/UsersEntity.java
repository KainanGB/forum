
package forum.forum.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USERS")
public class UsersEntity {

    @Id
    @Column(name = "user_id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", nullable = false)
    @NotEmpty(message = "username must not be empty.")
    @Size(max = 4, message = "username must be with 4 characters max.")
    private String username;

    @Column(name = "email", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotEmpty(message = "email must not be empty")
    @Email
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "password must not be empty")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<PostsEntity> posts;

    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<CommentsEntity> comments;

    @JsonIgnore
    @ManyToMany(mappedBy = "upvotes", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<CommentsEntity> upvotes;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp created_at;
}
