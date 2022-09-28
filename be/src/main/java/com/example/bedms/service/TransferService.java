package com.example.bedms.service;

import com.example.bedms.model.Transfer;

import java.util.List;

public interface TransferService {
    List<Transfer> findAll();
    void save(Transfer transfer);
    Transfer findById(int id);
}
