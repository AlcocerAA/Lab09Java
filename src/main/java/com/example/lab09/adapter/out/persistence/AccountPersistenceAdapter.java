package com.example.lab09.adapter.out.persistence;

import com.example.lab09.application.port.out.AccountRepositoryPort;
import com.example.lab09.domain.Account;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AccountPersistenceAdapter implements AccountRepositoryPort {

    private final AccountRepository accountRepository;

    public AccountPersistenceAdapter(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id)
                .map(e -> new Account(e.getId(), e.getBalance()));
    }

    @Override
    public void save(Account account) {
        accountRepository.save(new AccountEntity(account.getId(), account.getBalance()));
    }

    @Override
    public List<Account> findAll() {
        return accountRepository.findAll()
                .stream()
                .map(e -> new Account(e.getId(), e.getBalance()))
                .collect(Collectors.toList());
    }
}
