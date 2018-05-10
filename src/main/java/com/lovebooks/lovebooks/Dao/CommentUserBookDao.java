package com.lovebooks.lovebooks.Dao;

import com.lovebooks.lovebooks.Model.CommentUserBook;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
public interface CommentUserBookDao extends CrudRepository<CommentUserBook, Long> {
    Set<CommentUserBook> findByUserId(Long userId);
    Set<CommentUserBook> findByBookId(Long userId);
    Set<CommentUserBook> findByCommentId(Long commentId);
}
