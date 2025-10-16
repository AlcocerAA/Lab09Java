package com.example.lab09.domain;

import java.math.BigDecimal;

public class Account {
    private Long id;
    private BigDecimal balance;

    public Account() {}

    public Account(Long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public Long getId() { return id; }
    public BigDecimal getBalance() { return balance; }

    public void setId(Long id) { this.id = id; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }

    public void debit(BigDecimal amount) {
        if (balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Saldo insuficiente");
        }
        this.balance = balance.subtract(amount);
    }

    public void credit(BigDecimal amount) {
        this.balance = balance.add(amount);
    }
}
