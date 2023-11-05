package com.ursma.techtask.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import static com.ursma.techtask.util.factory.MessageFactory.sendMessage;

@Service
@RequiredArgsConstructor
public class MessageHandler {
    public SendMessage handleMessage(Message message) {
        var messageText = message.getText();
        return switch (messageText) {
            case "/start" ->
                    sendMessage("Перейдите по ссылке \uD83D\uDE48 -> https://t.me/+DpwEa-SIwM4yMTRi", message.getChatId());
            default -> sendMessage("Hello", message.getChatId());
        };
    }
}
