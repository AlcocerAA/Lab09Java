package com.example.lab09.application.port.in;

import com.example.lab09.domain.Transfer;

public interface SendMoneyUseCase {
    void sendMoney(Transfer transfer);
}
