package com.example.lab09.application.port.out;

import com.example.lab09.domain.Account;
import java.util.Optional;
import java.util.List;

public interface AccountRepositoryPort {
    Optional<Account> findById(Long id);
    void save(Account account);
    List<Account> findAll(); // <-- nueva firma
}
