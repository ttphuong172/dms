package com.example.bedms.service.Impl;

import com.example.bedms.model.Campus;
import com.example.bedms.repository.CampusRepository;
import com.example.bedms.service.CampusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CampusServiceImpl implements CampusService {
    @Autowired
    private CampusRepository campusRepository;
    @Override
    public List<Campus> findAll() {
        return campusRepository.findAll();
    }

    @Override
    public Campus findById(int id) {
        return campusRepository.findById(id).orElse(null);
    }


}
