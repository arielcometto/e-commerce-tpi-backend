package tpi.backend.e_commerce.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tpi.backend.e_commerce.models.Product;
import tpi.backend.e_commerce.repositories.IProductRepository;

@Service
public class ProductService implements IProductService {
    
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAllActive();
    }

    @Override
    public List<Product> findAllDeleted() {
        return (List<Product>) productRepository.findAllDeleted();
    }

    @Override
    public Product findById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            if (product.isDeleted()){
                throw new RuntimeException("Product have been deleted");
            }
            return product;
        }else{
            throw new RuntimeException("Product not found"); //De no encontrar el producto, lanzara una excepcion

        }
    }

    @Override
    public void deleteById(Long id) { //Borrado logico
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setDeleted(true); //Setea la flag en true, por lo que el producto no se mostrara
            productRepository.save(product);
        }  
        throw new RuntimeException("Product doesn't exist");
    }


}
