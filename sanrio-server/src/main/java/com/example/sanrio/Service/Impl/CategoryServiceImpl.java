package com.example.sanrio.Service.Impl;

import com.example.sanrio.Entity.Category;
import com.example.sanrio.Pojo.CategoryPojo;
import com.example.sanrio.Repo.CategoryRepo;
import com.example.sanrio.Service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepo categoryRepo;
    @Override
    public void saveCategory(CategoryPojo categoryPojo){
        Category category=new Category();

        if(categoryPojo.getId()!=null){
            category=categoryRepo.findById(categoryPojo.getId()).get();
        }

        category.setName(categoryPojo.getName());

        categoryRepo.save(category); // insert query
        System.out.println(" This Category Saved Successfully");
    }


    @Override
    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepo.deleteById(id);
    }

    @Override
    public Optional<Category> findById(Integer id) {
        return categoryRepo.findById(id);
    }
}
