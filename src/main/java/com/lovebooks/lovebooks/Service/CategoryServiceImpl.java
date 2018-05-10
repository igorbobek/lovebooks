package com.lovebooks.lovebooks.Service;

import com.lovebooks.lovebooks.Dao.CategoryDao;
import com.lovebooks.lovebooks.Model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryDao categoryDao;

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        categoryDao.findAll().forEach(categories::add);

        return categories;
    }

    @Override
    public Category getByName(String name) {
        return categoryDao.findByName(name);
    }
}
