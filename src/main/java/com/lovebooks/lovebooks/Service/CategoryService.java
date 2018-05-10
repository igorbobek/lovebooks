package com.lovebooks.lovebooks.Service;

import com.lovebooks.lovebooks.Model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category getByName(String name);
}
