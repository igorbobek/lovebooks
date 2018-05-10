package com.lovebooks.lovebooks.Controller;

import com.lovebooks.lovebooks.Dao.CommentUserBookDao;
import com.lovebooks.lovebooks.Model.Book;
import com.lovebooks.lovebooks.Model.Category;
import com.lovebooks.lovebooks.Model.User;
import com.lovebooks.lovebooks.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CommentService commentService;
    @Autowired
    CommentUserBookDao commentUserBookDao;
    @Autowired
    RoleService roleService;

    @GetMapping("/admin")
    @Secured("ROLE_ADMIN")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin");
        modelAndView.addObject("book", new Book());
        return modelAndView;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/addBook")
    public void addBook(
            @RequestParam("name") String nameForm,
            @RequestParam("publisher") String publisherForm,
            @RequestParam("year") String yearForm,
            @RequestParam("pages") String pagesForm,
            @RequestParam("language") String languageForm,
            @RequestParam("url") String urlForm,
            @RequestParam("img") String imgForm,
            @RequestParam("description") String descriptionForm,
            @RequestParam("category") String categoryForm,
            HttpServletResponse httpServletResponse
                        ) throws Exception{
        Book book = new Book();
        book.setName(nameForm);
        book.setPublisher(publisherForm);
        book.setYear(yearForm);
        book.setPages(Integer.parseInt(pagesForm));
        book.setLanguage(languageForm);
        book.setUrl(urlForm);
        book.setImg(imgForm);
        book.setDescription(descriptionForm);
        book.getCategories().add(new Category(categoryForm));
        bookService.save(book);
        httpServletResponse.sendRedirect("/admin");

    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/deleteBook")
    public void deleteBook(HttpServletResponse httpServletResponse, @RequestParam(name = "name") String nameForm) throws IOException{
        if(!nameForm.equals("")){
            bookService.deleteByName(nameForm);
        }
        httpServletResponse.sendRedirect("/");
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/setRole")
    public void setRole(HttpServletResponse httpServletResponse, @RequestParam(name = "name") String nameForm, @RequestParam(name = "newRole") String roleForm) throws IOException{
        User user = userService.findByName(nameForm);
        user.getRoles().add(roleService.getRoleByName(roleForm));
        userService.update(user);
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/deleteRole")
    public void deleteRole(HttpServletResponse httpServletResponse, @RequestParam(name = "name") String nameForm, @RequestParam(name = "role") String roleForm) throws IOException{
        User user = userService.findByName(nameForm);
        user.getRoles().remove(roleService.getRoleByName(roleForm));
        userService.update(user);
    }


}
