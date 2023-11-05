package com.ursma.techtask.bot;

import com.ursma.techtask.util.exception.MyExceptionHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;

@Component
@Slf4j
public class Bot extends TelegramLongPollingBot {

    private final String botName;
    private final BotService botService;

    public Bot(@Value("${bot.token}") String botToken,
               @Value("${bot.name}") String botName, BotService botService) {
        super(botToken);
        this.botName = botName;
        this.botService = botService;

        var botCommandList = new ArrayList<BotCommand>();
        botCommandList.add(new BotCommand("/start", "Получить ссылку"));

        try {
            this.execute(new SetMyCommands(botCommandList, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException ex) {
            throw new MyExceptionHandler(ex.getMessage(), ex);
        }
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        try {
            log.info("new Update");
            execute(botService.handleUpdate(update));
        } catch (TelegramApiException e) {
            throw new MyExceptionHandler(e.getMessage(), e);
        }
    }

    @Override
    public String getBotUsername() {
        return botName;
    }
}
