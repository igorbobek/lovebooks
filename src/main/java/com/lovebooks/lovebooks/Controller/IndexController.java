package com.lovebooks.lovebooks.Controller;

import com.lovebooks.lovebooks.Dao.BookDao;
import com.lovebooks.lovebooks.Dao.RoleDao;
import com.lovebooks.lovebooks.Model.Book;
import com.lovebooks.lovebooks.Model.CommentUserBook;
import com.lovebooks.lovebooks.Model.User;
import com.lovebooks.lovebooks.Service.BookService;
import com.lovebooks.lovebooks.Service.CategoryService;
import com.lovebooks.lovebooks.Service.CommentUserBookService;
import com.lovebooks.lovebooks.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Year;
import java.util.HashSet;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    UserService userService;

    @Autowired
    CommentUserBookService commentUserBookService;

    @Autowired
    RoleDao roleDao;
    @Autowired
    BookService bookService;
    @Autowired
    BookDao bookDao;
    @Autowired
    CategoryService categoryService;


    @GetMapping("/")
    public ModelAndView index(){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("template", "books");
        if (10 < bookService.getAllBooks().size()){
            modelAndView.addObject("books", bookService.getAllBooks().subList(0,10));
        }else{
            modelAndView.addObject("books", bookService.getAllBooks().subList(0, bookService.getAllBooks().size()));
        }
        modelAndView.addObject("categories", categoryService.getAll());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)){
            modelAndView.addObject("user", userService.findByName(auth.getName()));
        }
        int pages = bookService.getAllBooks().size()/10;
        if (pages > 0) {
            modelAndView.addObject("pages", bookService.getAllBooks().size() / 10);
        }
        return modelAndView;
    }

    @GetMapping("/books/{page}")
    public ModelAndView index(@PathVariable(value="page") String pageStr){
        ModelAndView modelAndView = new ModelAndView("index");
        int page = 1;
        try{
            page = Integer.parseInt(pageStr);
        }catch (NumberFormatException e){
            System.err.println(e.getMessage());
        }
        modelAndView.addObject("template", "books");
        modelAndView.addObject("categories", categoryService.getAll());
        if ((page-1)*10+10 < bookService.getAllBooks().size()){
            modelAndView.addObject("books", bookService.getAllBooks().subList((page-1)*10, page*10));
        }else{
            modelAndView.addObject("books", bookService.getAllBooks().subList((page-1)*10, bookService.getAllBooks().size()));
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)){
            modelAndView.addObject("user", userService.findByName(auth.getName()));
        }
        int pages = bookService.getAllBooks().size()/10;
        if (pages > 0) {
            modelAndView.addObject("pages", bookService.getAllBooks().size() / 10);
        }
        return  modelAndView;
    }

    @GetMapping("/book/{page}")
    public ModelAndView indexBook(@PathVariable(value="page") String pageStr){
        ModelAndView modelAndView = new ModelAndView("index");
        int page = 1;
        try{
            page = Integer.parseInt(pageStr);
        }catch (NumberFormatException e){
            System.err.println(e.getMessage());
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)){
            modelAndView.addObject("user", userService.findByName(auth.getName()));
        }
        List<CommentUserBook> сс = commentUserBookService.getByBookId((long)page);
        modelAndView.addObject("comments", commentUserBookService.getByBookId((long)page));
        modelAndView.addObject("categories", categoryService.getAll());
        modelAndView.addObject("template", "book");
        modelAndView.addObject("book", bookService.findById(page));

        return  modelAndView;
    }


    @GetMapping("/category/{category}")
    public ModelAndView booksByCategory(@PathVariable(value = "category") String category){
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("categories", categoryService.getAll());
        modelAndView.addObject("books", bookService.getByCategory(category));
        modelAndView.addObject("template", "books");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)){
            modelAndView.addObject("user", userService.findByName(auth.getName()));
        }
        int pages = bookService.getByCategory(category).size()/10;
        if (pages > 0) {
            modelAndView.addObject("pages", bookService.getByCategory(category).size() / 10);
        }
        return modelAndView;
    }

    @PostMapping("/")
    public void index(HttpServletResponse httpServletResponse) throws IOException{
        httpServletResponse.sendRedirect("/");
    }

}
