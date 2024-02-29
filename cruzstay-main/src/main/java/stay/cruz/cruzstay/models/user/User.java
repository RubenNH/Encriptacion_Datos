package stay.cruz.cruzstay.models.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stay.cruz.cruzstay.models.role.Role;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 30,unique = true)
    private String username;
    private String password;
    @Column(columnDefinition = "BOOL DEFAULT true")
    private Boolean status;
    private String lastAccess;
    @Column(columnDefinition = "BOOL DEFAULT true")
    private Boolean blocked;
    private String token;

    @Column(columnDefinition = "TIMESTAMP DEFAULT NOW()", insertable = false)
    private LocalDateTime createdAt;

    @ManyToMany(mappedBy = "users", cascade = CascadeType.MERGE)
    private Set<Role> roles;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.status = true;
        this.createdAt = LocalDateTime.now();
        this.blocked = true;
    }

}
