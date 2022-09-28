package com.example.bedms.controller;

import com.example.bedms.model.Type;
import com.example.bedms.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/type")
@CrossOrigin
public class TypeController {
    @Autowired
    private TypeService typeService;
    @GetMapping
    public ResponseEntity<List<Type>> findAll(){
       return new ResponseEntity<>(typeService.findAll(), HttpStatus.OK);
    }
}
