package com.example.lab09.adapter.in.rest;

import com.example.lab09.application.port.in.SendMoneyUseCase;
import com.example.lab09.domain.Transfer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    private final SendMoneyUseCase sendMoneyUseCase;

    public TransferController(SendMoneyUseCase sendMoneyUseCase) {
        this.sendMoneyUseCase = sendMoneyUseCase;
    }

    @PostMapping
    public ResponseEntity<String> sendMoney(@RequestBody TransferRequest request) {
        Transfer transfer = new Transfer(
                request.getSourceAccountId(),
                request.getTargetAccountId(),
                request.getAmount()
        );
        sendMoneyUseCase.sendMoney(transfer);
        return ResponseEntity.ok("Transferencia realizada exitosamente");
    }

    public static class TransferRequest {
        private Long sourceAccountId;
        private Long targetAccountId;
        private java.math.BigDecimal amount;

        public Long getSourceAccountId() { return sourceAccountId; }
        public void setSourceAccountId(Long sourceAccountId) { this.sourceAccountId = sourceAccountId; }
        public Long getTargetAccountId() { return targetAccountId; }
        public void setTargetAccountId(Long targetAccountId) { this.targetAccountId = targetAccountId; }
        public java.math.BigDecimal getAmount() { return amount; }
        public void setAmount(java.math.BigDecimal amount) { this.amount = amount; }
    }
}
