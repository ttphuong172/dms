package com.example.bedms.service.Impl;



import com.example.bedms.model.Account;
import com.example.bedms.repository.AccountRepository;
import com.example.bedms.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Account findById(String username) {
        return accountRepository.findById(username).orElseThrow(()->new UsernameNotFoundException("Not found"));
    }

    @Override
    public void save(Account account) {
        accountRepository.save(account);
    }
}
