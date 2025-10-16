package com.example.lab09.domain;

import java.math.BigDecimal;

public class Transfer {
    private Long sourceAccountId;
    private Long targetAccountId;
    private BigDecimal amount;

    public Transfer() {}

    public Transfer(Long sourceAccountId, Long targetAccountId, BigDecimal amount) {
        this.sourceAccountId = sourceAccountId;
        this.targetAccountId = targetAccountId;
        this.amount = amount;
    }

    public Long getSourceAccountId() { return sourceAccountId; }
    public Long getTargetAccountId() { return targetAccountId; }
    public BigDecimal getAmount() { return amount; }

    public void setSourceAccountId(Long sourceAccountId) { this.sourceAccountId = sourceAccountId; }
    public void setTargetAccountId(Long targetAccountId) { this.targetAccountId = targetAccountId; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
}
