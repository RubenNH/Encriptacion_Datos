package stay.cruz.cruzstay.controllers.category.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import stay.cruz.cruzstay.models.category.Category;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
    private Long id;
    private String name;
    private Boolean status;

    public Category getCategory(){
        return new Category(
                getId(),
                getName(),
                getStatus(),
                null
        );
    }
}
