package stay.cruz.cruzstay.services.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import stay.cruz.cruzstay.models.category.Category;
import stay.cruz.cruzstay.models.category.CategoryRepository;
import stay.cruz.cruzstay.utils.EncryptionService;
import stay.cruz.cruzstay.utils.Response;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private EncryptionService encryptionService;
    @Transactional(readOnly = true)
    public Response<List<Category>> getAll() throws Exception {
        List<Category> categories = this.repository.findAll();
        for (Category category : categories){
            category.setName(encryptionService.encrypt(category.getName()));
        }
        return new Response<>(
            categories, false, 200, "OK"
        );
    }

    @Transactional(readOnly = true)
    public Response<Category> getById(Long id){
        if(this.repository.existsById(id)){
            return new Response<>(
                this.repository.findById(id).get(), false, 200, "OK"
            );
        }
        return new Response<>(
            null, true, 400, "No se encontro la categoria"
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public Response<Category> insert(Category category) throws Exception {
        //tha name comes encrypted, so we need to decrypt it
        category.setName(encryptionService.decrypt(category.getName()));
        Optional<Category> exists = this.repository.findByName(category.getName());
        category.setStatus(true);
        if(exists.isPresent()){
            return new Response<>(
                null, true, 400, "La categoria ya existe"
            );
        }
        this.repository.saveAndFlush(category);
        return new Response<>(
            null, false, 201, "Creado Correctamente"
        );
    }

    @Transactional(rollbackFor = {Exception.class})
    public Response<Category> update(String id, Category category) throws Exception {
        //decrypt the id and parse it to Long
        id = encryptionService.decrypt(id);
        Long idLong = Long.parseLong(id);
        //tha name comes encrypted, so we need to decrypt it
        category.setName(encryptionService.decrypt(category.getName()));
        if(this.repository.existsById(idLong)){
            category.setId(idLong);

            this.repository.saveAndFlush(category);
            return new Response<>(
                category, false, 200, "Actualizado Correctamente"
            );
        }
        return new Response<>(
            null, true, 400, "No se encontro la categoria"
        );
    }
}
