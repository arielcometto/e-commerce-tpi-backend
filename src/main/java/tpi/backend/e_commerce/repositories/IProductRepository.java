package tpi.backend.e_commerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tpi.backend.e_commerce.models.Product;

public interface IProductRepository extends CrudRepository<Product,Long>{
    
    @Query("select p from Product p where p.deleted = false")
    List<Product> findAllActive(); //Busca todos los productos que no han sido eliminados

    @Query("select p from Product p where p.deleted = true")
    List<Product> findAllDeleted();    
}
