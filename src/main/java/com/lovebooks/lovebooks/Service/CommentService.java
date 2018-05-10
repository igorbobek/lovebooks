package com.lovebooks.lovebooks.Service;

import com.lovebooks.lovebooks.Model.Comment;

public interface CommentService {
    void save(Comment comment);
    Comment getById(Long id);

}
