package com.example.bedms.service;


import com.example.bedms.model.Account;

public interface AccountService {
    Account findById(String username);
    void save(Account account);

}
