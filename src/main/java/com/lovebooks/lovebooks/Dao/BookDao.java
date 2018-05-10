package com.lovebooks.lovebooks.Dao;

import com.lovebooks.lovebooks.Model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Transactional

public interface BookDao extends CrudRepository<Book, Long> {

    Optional<Book> findById(long aLong);
    void deleteByName(String name);
    Set<Book> findAll();
}
