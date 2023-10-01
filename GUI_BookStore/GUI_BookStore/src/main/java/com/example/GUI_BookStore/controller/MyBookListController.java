package com.example.GUI_BookStore.controller;

import com.example.GUI_BookStore.service.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyBookListController {

    @Autowired
    private MyBookListService myBookListService;

    @RequestMapping(value = "/deletemylist/{id}")
    public String deleteMyList(@PathVariable("id") int id) {
        myBookListService.deleteById(id);
        return "redirect:/my_books";
    }
}
