package com.ursma.techtask.util.factory;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

public class MessageFactory {

    public static SendMessage sendMessage(String textForSend, Long id) {
        return SendMessage.builder()
                .text(textForSend)
                .chatId(id)
                .build();
    }

    public static SendMessage sendMessageWithButton(String text, Long chatId, List<List<InlineKeyboardButton>> button) {
        return SendMessage.builder()
                .text(text)
                .chatId(chatId)
                .replyMarkup(InlineKeyboardMarkup.builder()
                        .keyboard(button)
                        .build())
                .build();
    }

    public static SendMessage sendMessageWithKeyboard(String text, Long chatId, ReplyKeyboardMarkup keyboard) {
        return SendMessage.builder()
                .text(text)
                .chatId(chatId)
                .replyMarkup(keyboard)
                .build();
    }
}
