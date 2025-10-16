package com.example.lab09.application;

import com.example.lab09.application.port.in.SendMoneyUseCase;
import com.example.lab09.application.port.out.AccountRepositoryPort;
import com.example.lab09.application.port.out.NotificationPort;
import com.example.lab09.domain.Account;
import com.example.lab09.domain.Transfer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SendMoneyUseCaseImpl implements SendMoneyUseCase {

    private final AccountRepositoryPort accountRepository;
    private final NotificationPort notificationPort;

    public SendMoneyUseCaseImpl(AccountRepositoryPort accountRepository, NotificationPort notificationPort) {
        this.accountRepository = accountRepository;
        this.notificationPort = notificationPort;
    }

    @Override
    @Transactional
    public void sendMoney(Transfer transfer) {
        Account sourceAccount = accountRepository.findById(transfer.getSourceAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Cuenta origen no encontrada"));
        Account targetAccount = accountRepository.findById(transfer.getTargetAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Cuenta destino no encontrada"));

        sourceAccount.debit(transfer.getAmount());
        targetAccount.credit(transfer.getAmount());

        accountRepository.save(sourceAccount);
        accountRepository.save(targetAccount);

        notificationPort.notify(String.format(
                "Transferencia realizada: %d → %d por monto %.2f",
                transfer.getSourceAccountId(), transfer.getTargetAccountId(), transfer.getAmount()
        ));
    }
}
