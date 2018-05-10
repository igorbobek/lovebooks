package com.lovebooks.lovebooks.Service;

import com.lovebooks.lovebooks.Model.Book;

import java.util.List;

public interface BookService {
    void save(Book book);
    Book findById(long id);
    List<Book> getAllBooks();
    List<Book> getBooksByUserId(long userId);
    List<Book> getByCategory(String category);
    void deleteByName(String name);
}
