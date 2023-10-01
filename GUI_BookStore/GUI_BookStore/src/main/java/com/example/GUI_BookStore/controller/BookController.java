package com.example.GUI_BookStore.controller;

import com.example.GUI_BookStore.entity.Book;
import com.example.GUI_BookStore.entity.MyBookList;
import com.example.GUI_BookStore.service.BookService;
import com.example.GUI_BookStore.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private MyBookListService myBookListService;

    @GetMapping(value = "/")
    public String home() {
        return "home";
    }

    @GetMapping(value = "/book_register")
    public String bookRegister() {
        return "bookRegister";
    }

    @GetMapping(value = "/available_books")
    public ModelAndView getAllBook() {
        List<Book> list = bookService.getAllBook();
        //ModelAndView modelAndView = new ModelAndView();
        //modelAndView.setViewName("bookList");
        //modelAndView.addObject("book",list);
        return new ModelAndView("bookList","book",list);
    }

    @PostMapping(value = "/save")
    public String addBook(@ModelAttribute Book b) {
        bookService.save(b);
        return "redirect:/available_books";
    }

    @GetMapping(value = "/my_books")
    public String getMyBooks(Model model) {
        List<MyBookList> list = myBookListService.getAllMyBooks();
        model.addAttribute("book",list);
        return "myBooks";
    }

    @RequestMapping(value = "/mylist/{id}")
    public String getMyList(@PathVariable("id") int id) {
        Book b = bookService.getBookById(id);
        MyBookList mb = new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getPrice());
        myBookListService.saveMyBooks(mb);
        return "redirect:/my_books";
    }

    @RequestMapping(value = "/editBook/{id}")
    public String editBook(@PathVariable("id") int id,Model model) {
        Book b = bookService.getBookById(id);
        model.addAttribute("book",b);
        return "bookEdit";
    }

    @RequestMapping(value = "/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookService.deleteById(id);
        return "redirect:/available_books";
    }
}

