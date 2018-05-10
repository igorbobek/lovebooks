package com.lovebooks.lovebooks.Service;

import com.lovebooks.lovebooks.Model.Comment;
import com.lovebooks.lovebooks.Model.CommentUserBook;

import java.util.List;
import java.util.Set;

public interface CommentUserBookService {
    void save(String commentStr, Long userId, Long bookId);
    List<CommentUserBook> getByUserId(Long userId);
    List<CommentUserBook> getByBookId(Long bookId);
    List<CommentUserBook> getByCommentId(Long commentId);
    List<CommentUserBook> getAll();
}
