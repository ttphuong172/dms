package com.example.bedms.service;

import com.example.bedms.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(int id);
}
