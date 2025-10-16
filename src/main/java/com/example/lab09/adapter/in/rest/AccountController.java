package com.example.lab09.adapter.in.rest;

import com.example.lab09.application.port.out.AccountRepositoryPort;
import com.example.lab09.domain.Account;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountRepositoryPort accountRepositoryPort;

    public AccountController(AccountRepositoryPort accountRepositoryPort) {
        this.accountRepositoryPort = accountRepositoryPort;
    }

    /**
     * Crear una cuenta.
     * Validaciones básicas: id obligatorio, balance no negativo.
     */
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody CreateAccountRequest req) {
        if (req.getId() == null) {
            return ResponseEntity.badRequest().body(new ErrorResponse("El campo 'id' es obligatorio"));
        }
        if (req.getBalance() == null) {
            return ResponseEntity.badRequest().body(new ErrorResponse("El campo 'balance' es obligatorio"));
        }
        if (req.getBalance().compareTo(BigDecimal.ZERO) < 0) {
            return ResponseEntity.badRequest().body(new ErrorResponse("El balance no puede ser negativo"));
        }

        Account a = new Account(req.getId(), req.getBalance());
        accountRepositoryPort.save(a);
        return ResponseEntity.status(201).body(new AccountResponse(a.getId(), a.getBalance()));
    }

    /**
     * Obtener una cuenta por id.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> getAccount(@PathVariable Long id) {
        return accountRepositoryPort.findById(id)
                .map(a -> ResponseEntity.ok(new AccountResponse(a.getId(), a.getBalance())))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Listar todas las cuentas.
     *
     * Nota: para que esto compile/funcione debes añadir el método
     * List<Account> findAll() en AccountRepositoryPort y su implementación
     * en AccountPersistenceAdapter que devuelva todas las cuentas.
     */
    @GetMapping
    public ResponseEntity<List<AccountResponse>> getAllAccounts() {
        List<Account> accounts = accountRepositoryPort.findAll(); // requiere findAll() en el puerto
        List<AccountResponse> response = accounts.stream()
                .map(a -> new AccountResponse(a.getId(), a.getBalance()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    // DTOs y respuestas
    public static class CreateAccountRequest {
        private Long id;
        private BigDecimal balance;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public BigDecimal getBalance() { return balance; }
        public void setBalance(BigDecimal balance) { this.balance = balance; }
    }

    public static class AccountResponse {
        private Long id;
        private BigDecimal balance;
        public AccountResponse(Long id, BigDecimal balance) { this.id = id; this.balance = balance; }
        public Long getId() { return id; }
        public BigDecimal getBalance() { return balance; }
    }

    public static class ErrorResponse {
        private String error;
        public ErrorResponse(String error) { this.error = error; }
        public String getError() { return error; }
    }
}
