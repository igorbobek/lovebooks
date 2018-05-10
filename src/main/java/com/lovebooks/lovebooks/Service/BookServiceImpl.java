package com.lovebooks.lovebooks.Service;

import com.lovebooks.lovebooks.Dao.BookDao;
import com.lovebooks.lovebooks.Dao.CategoryDao;
import com.lovebooks.lovebooks.Dao.UserDao;
import com.lovebooks.lovebooks.Model.Book;
import com.lovebooks.lovebooks.Model.Category;
import com.lovebooks.lovebooks.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BookDao bookDao;

    @Autowired
    UserDao userDao;

    @Autowired
    CategoryDao categoryDao;

    @Override
    public void save(Book book) {
        bookDao.save(book);
    }

    @Nullable
    @Override
    public Book findById(long id) {
        if(bookDao.findById(id).isPresent()){
            return bookDao.findById(id).get();
        }
        return null;
    }

    @Nullable
    @Override
    public List<Book> getAllBooks() {
        return new ArrayList<>(bookDao.findAll());
    }

    @Override
    public List<Book> getByCategory(String category) {
        return new ArrayList<>(categoryDao.findByName(category).getBooks());
    }

    @Nullable
    @Override
    public List<Book> getBooksByUserId(long userId) {
        List<Book> books = new ArrayList<>();
        if(userDao.findById(userId).isPresent()){
            User user = userDao.findById(userId).get();
            books.addAll(user.getBooks());
        }
        return books;
    }

    @Override
    public void deleteByName(String name) {
        bookDao.deleteByName(name);
    }
}
