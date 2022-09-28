package com.example.bedms.service;

import com.example.bedms.model.Mantain;

import java.util.List;

public interface MantainService {
    void save(Mantain mantain);
    List<Mantain> findAll();
}
