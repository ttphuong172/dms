package com.example.bedms.service.Impl;

import com.example.bedms.model.Transfer;
import com.example.bedms.repository.TransferRepository;
import com.example.bedms.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferServiceImpl implements TransferService {
    @Autowired
    private TransferRepository transferRepository;

    @Override
    public List<Transfer> findAll() {
        return transferRepository.findAll();
    }

    @Override
    public void save(Transfer transfer) {
        transferRepository.save(transfer);
    }

    @Override
    public Transfer findById(int id) {
        return transferRepository.findById(id).orElse(null);
    }
}
