package com.example.bedms.service.Impl;

import com.example.bedms.model.Mantain;
import com.example.bedms.repository.MantainRepository;
import com.example.bedms.service.MantainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MantainServiceImpl implements MantainService {
    @Autowired
    private MantainRepository mantainRepository;
    @Override
    public void save(Mantain mantain) {
        mantainRepository.save(mantain);
    }

    @Override
    public List<Mantain> findAll() {
        return mantainRepository.findAll();
    }
}
