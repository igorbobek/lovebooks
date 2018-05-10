package com.lovebooks.lovebooks.Service;

import com.lovebooks.lovebooks.Dao.CommentDao;
import com.lovebooks.lovebooks.Model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    CommentDao commentDao;

    @Override
    public void save(Comment comment) {
        commentDao.save(comment);
    }

    @Override
    public Comment getById(Long id) {
        if(commentDao.findById(id).isPresent()){
            return  commentDao.findById(id).get();
        }
        return null;
    }
}
