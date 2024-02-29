package stay.cruz.cruzstay.models.category;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 150)
    private String name;

    //1 = active, 0 = inactive
    @Column(columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean status;

    @Column(columnDefinition = "TIMESTAMP DEFAULT NOW()", insertable = false)
    private LocalDateTime creationDate;

    public Category(String name) {
        this.name = name;
        this.status = true;
    }
}
