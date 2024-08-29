package tpi.backend.e_commerce.services;

import java.util.List;

import tpi.backend.e_commerce.models.Category;

public interface ICategoryService {
    
    public Category saveCategory(Category category);

    public List<Category> findAll();

    public Category findById(Long id);

    public void deleteById(Long id);
}