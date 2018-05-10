package com.lovebooks.lovebooks.Model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String publisher;
    private String year;
    private int pages;
    private String language;
    private String url;
    private String img;
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_book", joinColumns = @JoinColumn(name = "id_book", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id"))
    private Set<Role> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "book_category", joinColumns = @JoinColumn(name = "id_book", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_category", referencedColumnName = "id"))
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "book",fetch = FetchType.EAGER )
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

    public String getYear() {
        return year.split("-")[0];
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Role> getUsers() {
        return users;
    }

    public void setUsers(Set<Role> users) {
        this.users = users;
    }


    public void setCommentUserBook(Set<CommentUserBook> commentUserBook) {
        this.commentUserBook = commentUserBook;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<CommentUserBook> getCommentUserBook() {
        return commentUserBook;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if(obj instanceof User){
            Book user = (Book)obj;
            if (user.getName().equals(this.getName())) {
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
}
