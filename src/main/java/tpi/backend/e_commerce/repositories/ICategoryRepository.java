package tpi.backend.e_commerce.repositories;
import org.springframework.data.repository.CrudRepository;

import tpi.backend.e_commerce.models.Category;



public interface ICategoryRepository extends CrudRepository<Category,Long>{
    
}

