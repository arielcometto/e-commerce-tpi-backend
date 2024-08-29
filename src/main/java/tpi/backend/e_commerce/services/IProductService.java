package tpi.backend.e_commerce.services;

import java.util.List;

import tpi.backend.e_commerce.models.Product;

public interface IProductService {
    
    Product saveProduct(Product product);

    List<Product> findAll();

    List<Product> findAllDeleted();

    Product findById(Long id);
    
    void deleteById(Long id); //Borrado logico

    

}   