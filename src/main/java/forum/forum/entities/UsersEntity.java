
package forum.forum.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.awt.print.Book;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "USERS")
public class UsersEntity {

    @Id
    @Column(name = "user_id", unique = true, columnDefinition = "BINARY(16)")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID user_id;

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

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<PostsEntity> posts;

    @Column(name = "created_at")
    @CreationTimestamp
    private Timestamp created_at;
}
