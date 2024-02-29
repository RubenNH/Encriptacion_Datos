package stay.cruz.cruzstay.controllers.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stay.cruz.cruzstay.controllers.category.dtos.CategoryDto;
import stay.cruz.cruzstay.models.category.Category;
import stay.cruz.cruzstay.services.category.CategoryService;
import stay.cruz.cruzstay.utils.Response;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = {"*"})
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping("/")
    public ResponseEntity<Response<List<Category>>> getAll() throws Exception {
        return new ResponseEntity<>(
                this.service.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Category>> getOne(
            @PathVariable("id") Long id
    ) {
        return new ResponseEntity<>(
                this.service.getById(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<Response<Category>> insert(@RequestBody CategoryDto category) throws Exception {
        return new ResponseEntity<>(
                this.service.insert(category.getCategory()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Category>> update( @PathVariable("id") Long id, @RequestBody CategoryDto category) {
        return new ResponseEntity<>(
                this.service.update(id, category.getCategory()),
                HttpStatus.OK
        );
    }

}
