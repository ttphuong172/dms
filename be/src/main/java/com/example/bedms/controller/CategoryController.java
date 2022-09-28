package com.example.bedms.controller;

import com.example.bedms.model.Category;
import com.example.bedms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping("")
    public ResponseEntity<List<Category>> findAll(){
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity <Category> findById(@PathVariable int id){
        return new ResponseEntity<>(categoryService.findById(id),HttpStatus.OK);
    }
}
