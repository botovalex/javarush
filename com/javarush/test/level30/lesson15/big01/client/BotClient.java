package com.javarush.test.level30.lesson15.big01.client;

import com.javarush.test.level30.lesson15.big01.ConsoleHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class BotClient extends Client {
    private static int botID = 0;

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected boolean shouldSentTextFromConsole() {
        return false;
    }

    @Override
    protected String getUserName() {
        if (botID == 99) botID = 0;
        return "date_bot_" + botID++;
    }

    public class BotSocketThread extends SocketThread {
        private final Map<String, String> dataFormats = new HashMap<>();
        {
            dataFormats.put("дата", "d.MM.YYYY");
            dataFormats.put("день", "d");
            dataFormats.put("месяц", "MMMM");
            dataFormats.put("год", "YYYY");
            dataFormats.put("время", "H:mm:ss");
            dataFormats.put("час", "H");
            dataFormats.put("минуты", "m");
            dataFormats.put("секунды", "s");
        }

        @Override
        protected void processIncomingMessage(String message) {
            ConsoleHelper.writeMessage(message);
            if (message.contains(":")) {
                String name = message.substring(0, message.indexOf(": "));
                String data = message.substring(message.indexOf(": ") + 2);
                if (dataFormats.containsKey(data)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(dataFormats.get(data));
                    sendTextMessage(String.format("Информация для %s: %s", name,
                            dateFormat.format(new GregorianCalendar().getTime())));
                }
            }
        }

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }
    }
}
