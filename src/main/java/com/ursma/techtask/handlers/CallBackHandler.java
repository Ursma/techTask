package com.ursma.techtask.handlers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.io.Serializable;

@Service
@RequiredArgsConstructor
public class CallBackHandler {

    public BotApiMethod<? extends Serializable> handleCallBack(CallbackQuery callbackQuery) {
        return null;
    }
}