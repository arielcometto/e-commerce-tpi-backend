package tpi.backend.e_commerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import tpi.backend.e_commerce.models.Category;
import tpi.backend.e_commerce.services.ICategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    
    @Autowired
    private ICategoryService categoryService;
    
    @GetMapping
    public List<Category> findAll(){
        return (List<Category>) categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (optionalCategory.isPresent()) {
            return ResponseEntity.ok(optionalCategory.get()); 
            //De existir la categoria y estar activa lo devuelve con codigo 200
        }
    
        return ResponseEntity.notFound().build(); 
        //De no existir la categoria o existir y estar eliminada devuelve un codigo 404
    }

    @GetMapping("/deleted")
    public List<Category> findAllDeleted(){
        return categoryService.findAllDeleted();
    }

    @GetMapping("/deleted/{id}")
    public ResponseEntity<?> findDeletedById(@PathVariable Long id){ //Busca por id entre las categorias eliminadas
        Optional<Category> optionalCategory = categoryService.findDeletedById(id);
        if (optionalCategory.isPresent()) {
            return ResponseEntity.ok(optionalCategory.get()); 
            //De existir la categoria y estar eliminada lo devuelve con codigo 200
        }
        return ResponseEntity.notFound().build(); 
        //De no existir la categoria o existir y estar activa devuelve un codigo 404
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.saveCategory(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Category requestCategory){
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (optionalCategory.isPresent()) {
            Category dbCategory = optionalCategory.get();
            requestCategory.setId(dbCategory.getId());
            return ResponseEntity.ok(categoryService.saveCategory(requestCategory));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (optionalCategory.isPresent()) {
            categoryService.delete(optionalCategory.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}