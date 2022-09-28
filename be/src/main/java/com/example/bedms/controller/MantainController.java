package com.example.bedms.controller;

import com.example.bedms.model.Mantain;
import com.example.bedms.model.dto.MantainDTO;
import com.example.bedms.service.MantainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("api/mantains")
@CrossOrigin
public class MantainController {
    @Autowired
    private MantainService mantainService;
    @GetMapping("")
    public ResponseEntity<List<MantainDTO>> findAll(){
        List<Mantain> mantainList = mantainService.findAll();
        List<MantainDTO> mantainDTOList = new ArrayList<>();
        for (int i=0;i<mantainList.size();i++){
            MantainDTO mantainDTO= new MantainDTO();
            mantainDTO.setId(mantainList.get(i).getId());
            mantainDTO.setMantainDate(mantainList.get(i).getMantainDate());
            mantainDTO.setMantainContent(mantainList.get(i).getMantainContent());
            mantainDTO.setMantainDevice(mantainList.get(i).getMantainDevice());
            mantainDTOList.add(mantainDTO);
        }
        Collections.sort(mantainDTOList,(o1,o2)->o2.getMantainDate().compareTo(o1.getMantainDate()));
//        Collections.sort(roomList, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        return new ResponseEntity<>(mantainDTOList, HttpStatus.OK);
    }
}
