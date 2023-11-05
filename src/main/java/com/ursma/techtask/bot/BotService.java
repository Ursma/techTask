package com.ursma.techtask.bot;

import com.ursma.techtask.handlers.CallBackHandler;
import com.ursma.techtask.handlers.MessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
@Slf4j
public class BotService {

    private final CallBackHandler callBackHandler;
    private final MessageHandler messageHandler;

    public BotApiMethod<? extends Serializable> handleUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            return handleMessage(update.getMessage());
        }
        if (update.hasCallbackQuery()) {
            return handleCallbackQuery(update.getCallbackQuery());
        }
        return sendError(update);
    }

    private BotApiMethod<? extends Serializable> handleCallbackQuery(CallbackQuery callbackQuery) {
        String username = callbackQuery.getFrom().getUserName();
        Long chatId = callbackQuery.getMessage().getChatId();

        log.info("New callbackQuery from User: {}, chatId: {} with data: {}", username, chatId, callbackQuery.getData());

        return callBackHandler.handleCallBack(callbackQuery);
    }

    private SendMessage handleMessage(Message message) {
        String username = message.getFrom().getUserName();
        Long chatId = message.getChatId();

        log.info("New message from User: {}, chatId: {}, with text: {}", username, chatId, message.getText());

        return messageHandler.handleMessage(message);
    }

    private static SendMessage sendError(Update update) {
        if (update.getCallbackQuery() == null) {
            return SendMessage.builder()
                    .text("error_unknown")
                    .chatId(update.getMessage().getChatId())
                    .build();
        } else {
            return SendMessage.builder()
                    .text("error_unknown")
                    .chatId(update.getCallbackQuery().getMessage().getChatId())
                    .build();
        }
    }
}
