package com.lovebooks.lovebooks.Controller;

import com.lovebooks.lovebooks.Model.Book;
import com.lovebooks.lovebooks.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookRestController {
    @Autowired
    BookService bookService;

    @Secured("ROLE_ADMIN")
    @GetMapping("/api/books")
    public ResponseEntity<List<Book>> listAllBooks(){
        List<Book> books = bookService.getAllBooks();
        books.forEach(e->{
            e.setCategories(null);
            e.setUsers(null);
            e.setCommentUserBook(null);
        });
        if(books.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(books,HttpStatus.OK);
    }

}
