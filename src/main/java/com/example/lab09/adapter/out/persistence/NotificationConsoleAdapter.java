package com.example.lab09.adapter.out.persistence;

import com.example.lab09.application.port.out.NotificationPort;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsoleAdapter implements NotificationPort {

    @Override
    public void notify(String message) {
        System.out.println("[NOTIFICACIÓN] " + message);
    }
}
