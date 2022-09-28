package com.example.bedms.service;

import com.example.bedms.model.Floor;

import java.util.List;

public interface FloorService {
    List<Floor> findAll();
    List<Floor> findFloorByCampus_Name(String name);
    Floor findById(int id);
    void save(Floor floor);
}
