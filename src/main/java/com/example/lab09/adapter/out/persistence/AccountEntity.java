package com.example.lab09.adapter.out.persistence;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    private Long id;

    @Column(nullable = false)
    private BigDecimal balance;

    public AccountEntity() {}

    public AccountEntity(Long id, BigDecimal balance) {
        this.id = id;
        this.balance = balance;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}
