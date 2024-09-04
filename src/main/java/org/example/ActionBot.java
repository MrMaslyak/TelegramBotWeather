package org.example;

import org.example.Interface.User;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ActionBot {


    static class StartState implements User {
        @Override
        public void handle(Bot bot, Update update) {
            long userId = update.getMessage().getFrom().getId();
            String welcomeMessage = getWelcomeMessage();
            bot.sendText(userId, welcomeMessage);
            bot.sendText(userId, "Вводи число или название города");
            bot.setUserState(userId, new WaitCityState());
        }

        private String getWelcomeMessage() {
            return "\uD83C\uDF1F *Приветствую тебя!* \uD83C\uDF1F\n" +
                    "\n" +
                    "Я Maslyak - твой персональный погодный ассистент. \uD83C\uDF26\uFE0F Если хочешь узнать, какая погода сейчас в разных уголках мира, ты попал по адресу!\n" +
                    "\n" +
                    "Вот список городов, для которых я могу рассказать прогноз:\n" +
                    "\n" +
                    "\uD83C\uDF0D *1. Шанхай* — Китай \uD83C\uDDE8\uD83C\uDDF3  \n" +
                    "\uD83C\uDFD9\uFE0F *2. Днепр* — Украина \uD83C\uDDFA\uD83C\uDDE6  \n" +
                    "\uD83D\uDDFD *3. Нью-Йорк* — США \uD83C\uDDFA\uD83C\uDDF8  \n" +
                    "\uD83C\uDFD6\uFE0F *4. Дубай* — ОАЭ \uD83C\uDDE6\uD83C\uDDEA\n" +
                    "\n" +
                    "\uD83D\uDCAC *Как это работает?*\n" +
                    "Просто напиши номер или название города, который тебя интересует, ой только не сделай ошибок в словах, пиши также, как написал , и я мгновенно предоставлю тебе свежие данные о погоде! ☀\uFE0F☁\uFE0F\uD83C\uDF27\uFE0F\n" +
                    "\n" +
                    "❓ _Не можешь выбрать?_  \n" +
                    "Пиши номер или название города, и я найду нужную информацию для тебя!\n" +
                    "\n" +
                    "Так что не стесняйся, выбирай город, и давай узнаем, что там происходит за окном прямо сейчас! \uD83D\uDE09\n";
        }
    }


    static class WaitCityState implements User {
        public void handle(Bot bot, Update update) {
            long userId = update.getMessage().getFrom().getId();
            String textUser = update.getMessage().getText();
            if (textUser.equalsIgnoreCase("Шанхай") || textUser.equalsIgnoreCase("Днепр") || textUser.equalsIgnoreCase("Нью-Йорк") || textUser.equalsIgnoreCase("Дубай")
                    || textUser.equals("1") || textUser.equals("2") || textUser.equals("3") || textUser.equals("4")) {
                bot.sendText(userId, "\uD83D\uDE3D Молодец! Ты правильно ввел название или нумерацию города \uD83D\uDE3D");
                String weather = bot.getWeather(textUser, update);
                bot.sendText(userId,  weather);
                if (weather == null) {
                    bot.sendText(userId, "Извини, я не знаю, какая погода в этом городе.");
                }
                bot.sendText(userId, " \uD83D\uDE31 Если хочешь узнать погоду в другом городе, то напиши еще раз число или название\uD83E\uDDD0");
            } else {
                bot.sendText(userId, "Почему ты не понимаешь с первого раза никогда? \uD83D\uDE11 \nВведи нормально название или нумерацию города\uD83D\uDC47");
            }

        }
    }

}
