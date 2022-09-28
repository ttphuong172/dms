package com.example.bedms.service.Impl;

import com.example.bedms.model.Type;
import com.example.bedms.repository.TypeRepository;
import com.example.bedms.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeRepository typeRepository;
    @Override
    public List<Type> findAll() {
        return typeRepository.findAllByOrderByName();
    }
}
