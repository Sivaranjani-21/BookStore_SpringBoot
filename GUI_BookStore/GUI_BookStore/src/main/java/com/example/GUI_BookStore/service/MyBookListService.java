package com.example.GUI_BookStore.service;

import com.example.GUI_BookStore.entity.MyBookList;
import com.example.GUI_BookStore.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookListService {

    @Autowired
    private MyBookRepository myBookRepository;
    public void saveMyBooks(MyBookList book) {
        myBookRepository.save(book);
    }

    public List<MyBookList> getAllMyBooks() {
        return myBookRepository.findAll();
    }

    public void deleteById(int id) {
        myBookRepository.deleteById(id);
    }
}
