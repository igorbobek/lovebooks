package com.lovebooks.lovebooks.Service;

import com.lovebooks.lovebooks.Dao.CommentUserBookDao;
import com.lovebooks.lovebooks.Model.Book;
import com.lovebooks.lovebooks.Model.Comment;
import com.lovebooks.lovebooks.Model.CommentUserBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentUserBookServiceImpl implements CommentUserBookService {

    @Autowired
    UserService userService;
    @Autowired
    CommentService commentService;
    @Autowired
    BookService bookService;
    @Autowired
    CommentUserBookDao commentUserBookDao;


    //Save comment
    @Override
    public void save(String commentStr, Long userId, Long bookId) {
        CommentUserBook commentUserBook = new CommentUserBook();
        Book book = bookService.findById(bookId);
        commentUserBook.setBook(bookService.findById(bookId));
        commentUserBook.setUser(userService.findById(userId));
        Comment comment = new Comment();
        comment.setContent(commentStr);
        comment.setDate(new Date());
        commentUserBook.setComment(comment);
        commentUserBookDao.save(commentUserBook);
    }


    //Search by user id (return list of CommentUserBook)
    @Override
    public List<CommentUserBook> getByUserId(Long userId) {
        return new ArrayList<>(commentUserBookDao.findByUserId(userId));
    }

    //Search by book id (return list of CommentUserBook)
    @Override
    public List<CommentUserBook> getByBookId(Long bookId) {
        return new ArrayList<>(commentUserBookDao.findByBookId(bookId));
    }

    //Search by comment id (return list of CommentUserBook)
    @Override
    public List<CommentUserBook> getByCommentId(Long commentId) {
        return new ArrayList<>(commentUserBookDao.findByCommentId(commentId));
    }


    //Get all CommentUserBook
    @Override
    public List<CommentUserBook> getAll() {
        List<CommentUserBook> commentUserBooks = new ArrayList<>();
        commentUserBookDao.findAll().forEach(commentUserBooks::add);
        return commentUserBooks;
    }
}
