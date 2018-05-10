package com.lovebooks.lovebooks.Dao;

import com.lovebooks.lovebooks.Model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CommentDao extends CrudRepository<Comment, Long>{
}
