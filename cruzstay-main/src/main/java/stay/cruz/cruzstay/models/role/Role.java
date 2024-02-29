package stay.cruz.cruzstay.models.role;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stay.cruz.cruzstay.models.user.User;

import java.util.Set;

@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // CLIENTE, CLIENTE_NO_VERIFICADO, ADMINISTRADOR, EMPLEADO
    @Column(nullable = false,length = 50)
    private String name;

    @Column(nullable = false,columnDefinition = "TINYINT DEFAULT 1")
    private Boolean status;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonIgnore
    private Set<User> users;

    public Role(String name) {
        this.name = name;
        this.status = true;
    }
}
