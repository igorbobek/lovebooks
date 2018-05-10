package com.lovebooks.lovebooks.Controller;

import com.lovebooks.lovebooks.Model.Category;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController {


    @Autowired
    CommentUserBookService commentUserBookService;
    @Autowired
    UserService userService;
    @Autowired
    BookService bookService;
    @Autowired
    CategoryService categoryService;

    @Secured("ROLE_ANONYMOUS")
    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }


    @ResponseBody
    @Secured("ROLE_ANONYMOUS")
    @PostMapping("/login")
    public ModelAndView login(Model model, Error errors){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByName(auth.getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @Secured("ROLE_ANONYMOUS")
    @GetMapping("/signup")
    public ModelAndView signup(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("signup");
        modelAndView.addObject("user", new User());
        return modelAndView;
    }

    @Secured("ROLE_ANONYMOUS")
    @PostMapping("/signup")
    public ModelAndView signup(@ModelAttribute("user") User userForm, @RequestParam("matchingPassword") String matchPass, ModelAndView modelAndView){

        if(userForm.getName().equals("") || userForm.getEmail().equals("") || userForm.getPassword().equals("")){
            modelAndView.setViewName("signup");
            modelAndView.addObject("error", "Вводимые поля не должны быть пустыми!");
            return modelAndView;
        }

        if (!userForm.getPassword().equals(matchPass)){
            modelAndView.setViewName("signup");
            modelAndView.addObject("error", "Оба введенных пароля должны быть идентичны!");
            return modelAndView;
        }
        try{
            userForm.setPassword(new BCryptPasswordEncoder().encode(userForm.getPassword()));
            userService.save(userForm);
            modelAndView = new ModelAndView(new RedirectView("/"));
            return modelAndView;
        }catch (Exception e){
            modelAndView.setViewName("signup");
            modelAndView.addObject("error", e.getMessage());
            System.err.println(e.getMessage());
        }

        return modelAndView;
    }


    @Secured({"ROLE_ADMIN","ROLE_USER"})
    @GetMapping("/mybooks")
    public ModelAndView getMyBooks(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("template", "books");
        modelAndView.addObject("books", bookService.getBooksByUserId(userService.findByName(auth.getName()).getId()));
        modelAndView.addObject("categories", categoryService.getAll());
        if(!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)){
            modelAndView.addObject("user", userService.findByName(auth.getName()));
        }
        int pages = bookService.getBooksByUserId(userService.findByName(auth.getName()).getId()).size()/10;
        if (pages > 1) {
            modelAndView.addObject("pages", pages / 10);
        }
        return modelAndView;
    }

    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @PostMapping("/addGoodBook")
    public void addGoodBook(@RequestParam("bookId") String bookIdStr, HttpServletResponse httpServletResponse) throws IOException{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        try{
            int bookId = Integer.parseInt(bookIdStr);
            User user = userService.findByName(auth.getName());
            user.getBooks().add(bookService.findById(bookId));
            userService.update(user);
        }catch (NumberFormatException e){
            System.err.println(e.getMessage());
        }finally {
            httpServletResponse.sendRedirect("/");
        }

    }

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping("/addComment")
    public String addComment(HttpServletRequest request, @RequestParam("comment") String commentForm, @RequestParam("bookId") String bookId){
        String referer = request.getHeader("Referer");

        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            commentUserBookService.save(commentForm, userService.findByName(auth.getName()).getId(), Long.parseLong(bookId));
        }catch (NumberFormatException e){
            System.err.println(e.getMessage());
        }

        return "redirect:"+ referer;
    }

}
