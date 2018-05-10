package com.lovebooks.lovebooks.Dao;

import com.lovebooks.lovebooks.Model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CategoryDao extends CrudRepository<Category, Long> {
    Category findByName(String name);
}
