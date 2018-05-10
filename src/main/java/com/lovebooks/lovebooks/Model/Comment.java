package com.lovebooks.lovebooks.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "comment")
public class Comment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Date date;

    @OneToMany(mappedBy = "comment",fetch = FetchType.EAGER )
    private Set<CommentUserBook> commentUserBook = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<CommentUserBook> getCommentUserBook() {
        return commentUserBook;
    }

    public void setCommentUserBook(Set<CommentUserBook> commentUserBook) {
        this.commentUserBook = commentUserBook;
    }
}
