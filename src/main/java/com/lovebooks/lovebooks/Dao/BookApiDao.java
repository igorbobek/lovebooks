package com.lovebooks.lovebooks.Dao;

import com.lovebooks.lovebooks.Model.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
public interface BookApiDao extends CrudRepository<Book, Long> {
    @Secured("ROLE_ADMIN")
    Set<Book> findAll();
}
