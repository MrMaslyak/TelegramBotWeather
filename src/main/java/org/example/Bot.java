package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {

    private Map<Long, User> userStates = new HashMap<>();

    @Override
    public String getBotUsername() {
        return "MaslyakWeatherBot";
    }

    @Override
    public String getBotToken() {
        return "7127897577:AAFZRTZ6hKhwrJBb4javxotyMtEj3xImDdM";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            long idFromUser = update.getMessage().getFrom().getId();
            String text = update.getMessage().getText();

            if (text.equalsIgnoreCase("restart")) {
                setUserState(idFromUser, new ActionBot.StartState());
                sendText(idFromUser, "\uD83E\uDEE1 Переворот Погоды выполнен! \uD83E\uDEE1 \n Введи что угодно, чтобы я обновился \uD83E\uDEE8");
                return;
            }

            User state = userStates.getOrDefault(idFromUser, new ActionBot.StartState());
            System.out.println("Current State: " + state.getClass().getSimpleName());
            state.handle(this, update);
        } else {
            System.out.println("No message or text found in update");
        }
    }

    public void setUserState(long userId, User state) {
        userStates.put(userId, state);
    }

    public void sendText(Long who, String what) {
        SendMessage sm = SendMessage.builder()
                .chatId(who.toString())
                .text(what)
                .parseMode("Markdown")
                .build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public String getWeather(String city) {
        switch (city.toLowerCase()) {
            case "шанхай":
            case "1":
                String urlShanghai = "https://sinoptik.ua/%D0%BF%D0%BE%D0%B3%D0%BE%D0%B4%D0%B0-%D1%88%D0%B0%D0%BD%D1%85%D0%B0%D0%B9";
                try {
                    Document doc = Jsoup.connect(urlShanghai).get();


                    Element temperatureElement = doc.selectFirst("._6fYCPKSx");
                    Element weatherDescElement = doc.selectFirst(".DGqLtBkd");

                    if (temperatureElement != null && weatherDescElement != null) {
                        String temperatureText = temperatureElement.text().trim();
                        String description = weatherDescElement.text().trim();

                        String temperatureNumber = temperatureText.replaceAll("[^\\d]", "");
                        if (!temperatureNumber.isEmpty()) {
                            int temperature = Integer.parseInt(temperatureNumber);
                            return "В Шанхае сейчас " + temperature + "°C, Погода: " + description;
                        } else {
                            return "Не удалось получить данные о температуре";
                        }
                    } else {
                        return "Не удалось получить данные о погоде";
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Ошибка при получении данных о погоде";
                }
            case "днепр":
            case "2":
                String urlDnipro = "https://sinoptik.ua/%D0%BF%D0%BE%D0%B3%D0%BE%D0%B4%D0%B0-%D0%B4%D0%BD%D0%B5%D0%BF%D1%80-303007131";
                try {
                    Document doc = Jsoup.connect(urlDnipro).get();

                    Element nowWeatherElement = doc.selectFirst(".today-temp");
                    Element weatherDescElement = doc.selectFirst(".description");

                    if (nowWeatherElement != null && weatherDescElement != null) {
                        String temperatureText = nowWeatherElement.text().trim();
                        String description = weatherDescElement.text().trim();

                        String temperatureNumber = temperatureText.replaceAll("[^\\d]", "");
                        if (!temperatureNumber.isEmpty()) {
                            int temperature = Integer.parseInt(temperatureNumber);
                            return "В Днепре сейчас " + temperature + "°C, Погода: " + description;
                        } else {
                            return "Не удалось получить данные о температуре";
                        }
                    } else {
                        return "Не удалось получить данные о погоде";
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Ошибка при получении данных о погоде";
                }
            case "нью-йорк":
            case "3":
                String urlNewYork = "https://sinoptik.ua/%D0%BF%D0%BE%D0%B3%D0%BE%D0%B4%D0%B0-%D0%BD%D1%8C%D1%8E-%D0%B9%D0%BE%D1%80%D0%BA";
                try {
                    Document doc = Jsoup.connect(urlNewYork).get();

                    Element temperatureElement = doc.selectFirst(".today-temp");
                    Element weatherDescElement = doc.selectFirst(".description");

                    if (temperatureElement != null && weatherDescElement != null) {
                        String temperatureText = temperatureElement.text().trim();
                        String description = weatherDescElement.text().trim();

                        String temperatureNumber = temperatureText.replaceAll("[^\\d]", "");
                        if (!temperatureNumber.isEmpty()) {
                            int temperature = Integer.parseInt(temperatureNumber);
                            return "В Нью-Йорке сейчас " + temperature + "°C, Погода: " + description;
                        } else {
                            return "Не удалось получить данные о температуре";
                        }
                    } else {
                        return "Не удалось получить данные о погоде";
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Ошибка при получении данных о погоде";
                }
            case "дубай":
            case "4":
                String urlDubai = "https://sinoptik.ua/%D0%BF%D0%BE%D0%B3%D0%BE%D0%B4%D0%B0-%D0%B4%D1%83%D0%B1%D0%B0%D0%B9";
                try {
                    Document doc = Jsoup.connect(urlDubai).get();

                    Element temperatureElement = doc.selectFirst("._6fYCPKSx");
                    Element weatherDescElement = doc.selectFirst(".DGqLtBkd");

                    if (temperatureElement != null && weatherDescElement != null) {
                        String temperatureText = temperatureElement.text().trim();
                        String description = weatherDescElement.text().trim();

                        String temperatureNumber = temperatureText.replaceAll("[^\\d]", "");
                        if (!temperatureNumber.isEmpty()) {
                            int temperature = Integer.parseInt(temperatureNumber);
                            return "В Дубае сейчас " + temperature + "°C, Погода: " + description;
                        } else {
                            return "Не удалось получить данные о температуре";
                        }
                    } else {
                        return "Не удалось получить данные о погоде";
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Ошибка при получении данных о погоде";
                }
            default:
                return "Извини, я пока не знаю, какая погода в этом городе.";
        }
    }
}
