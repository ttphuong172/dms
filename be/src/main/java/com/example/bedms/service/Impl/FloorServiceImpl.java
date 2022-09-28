package com.example.bedms.service.Impl;

import com.example.bedms.model.Floor;
import com.example.bedms.repository.FloorRepository;
import com.example.bedms.service.FloorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FloorServiceImpl implements FloorService {
    @Autowired
    private FloorRepository floorRepository;
    @Override
    public List<Floor> findAll() {
        return floorRepository.findAll();
    }

    @Override
    public List<Floor> findFloorByCampus_Name(String name) {
        return floorRepository.findFloorByCampus_Name(name);
    }

    @Override
    public Floor findById(int id) {
        return floorRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Floor floor) {
        floorRepository.save(floor);
    }


}
