package com.lovebooks.lovebooks.Model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String password;

    private String email;


    public User(){}

    public User(String name, String password, String email){
        this.name=name;
        this.password = password;
        this.email = email;
    }


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_role", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_book", joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_book", referencedColumnName = "id"))
    private Set<Book> books = new HashSet<>();

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER )
    private Set<CommentUserBook> commentUserBook = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> book) {
        this.books = book;
    }


    public Set<CommentUserBook> getCommentUserBook() {
        return commentUserBook;
    }

    public void setCommentUserBook(Set<CommentUserBook> commentUserBook) {
        this.commentUserBook = commentUserBook;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + email.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if(obj instanceof User){
            User user = (User)obj;
            if (user.getName().equals(this.getName())) {
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Name: " + this.name + ", Email: " + this.email + ", Password: " + this.password;
    }
}
