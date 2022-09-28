package com.example.bedms.service;

import com.example.bedms.model.Campus;

import java.util.List;

public interface CampusService {
    List<Campus> findAll();
    Campus findById(int id);
}
