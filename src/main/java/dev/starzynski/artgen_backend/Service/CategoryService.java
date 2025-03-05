package dev.starzynski.artgen_backend.Service;

import dev.starzynski.artgen_backend.Model.Category;
import dev.starzynski.artgen_backend.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        try {
            return categoryRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
