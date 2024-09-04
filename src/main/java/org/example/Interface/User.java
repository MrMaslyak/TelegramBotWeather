package org.example.Interface;

import org.example.Bot;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface User {
    void handle(Bot bot, Update update);
}
