package com.javarush.test.level30.lesson15.big01;

import com.javarush.test.level30.lesson15.big01.client.BotClient;
import com.javarush.test.level30.lesson15.big01.client.Client;
import com.javarush.test.level30.lesson15.big01.client.ClientGuiController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    private static class Handler extends Thread{
        private Socket socket;
        public Handler(Socket socket) {
            this.socket = socket;
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message message = connection.receive();
                if (message.getType() != MessageType.USER_NAME) continue;
                String userName = message.getData();
                if (userName.isEmpty() || connectionMap.containsKey(userName)) continue;
                connectionMap.put(userName, connection);
                connection.send(new Message(MessageType.NAME_ACCEPTED));
                return userName;
            }

        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for (String name : connectionMap.keySet()) {
                if (!name.equals(userName)) {
                    connection.send(new Message(MessageType.USER_ADDED, name));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while (true) {
                Message message = connection.receive();
                if (message.getType() == MessageType.TEXT) {
                    sendBroadcastMessage(new Message(MessageType.TEXT, userName + ": " + message.getData()));
                } else {
                    ConsoleHelper.writeMessage("Не верный тип принятого сообщения");
                }
            }

        }

        @Override
        public void run() {
            ConsoleHelper.writeMessage("Установлено новое соединение с удалённым адресом " + socket.getRemoteSocketAddress());
            String userName = null;
            try (Connection connection = new Connection(socket)) {
                userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом");
            } catch (ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Произошла ошибка при обмене данными с удаленным адресом");
            } finally {
                if (userName != null) {
                    connectionMap.remove(userName);
                    sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                }
            }
            ConsoleHelper.writeMessage("Соединение с удалённым сервером " + socket.getRemoteSocketAddress() + " закрыто");
        }
    }

    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void sendBroadcastMessage(Message message) {
        for (Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
            try {
                entry.getValue().send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Не удалось отправить сообщение клиенту " + entry.getKey());
            }
        }
    }

    public static void main(String[] args) {
        try {
            ConsoleHelper.writeMessage("Введите порт сервера:");
            ServerSocket server = new ServerSocket(ConsoleHelper.readInt());
            ConsoleHelper.writeMessage("Сервер запущен");
            try {
                while (true) {
                    Handler handler = new Handler(server.accept());
                    handler.start();
                }
            } catch (IOException e2) {
                ConsoleHelper.writeMessage("main: не удалось добавить клиента");
            } finally {
                server.close();
            }
        } catch (Exception e) {
            ConsoleHelper.writeMessage("main: exception");
        }
        ClientGuiController controller = new ClientGuiController();
        controller.run();
        Client client = new Client();
        client.run();
        BotClient botClient = new BotClient();
        botClient.run();
    }
}

/*
11.1.	Выводить сообщение, что установлено новое соединение с удаленным
адресом, который можно получить с помощью метода getRemoteSocketAddress
11.2.	Создавать Connection, используя поле Socket
11.3.	Вызывать метод, реализующий рукопожатие с клиентом, сохраняя имя нового
клиента
11.4.	Рассылать всем участникам чата информацию об имени присоединившегося
участника (сообщение с типом USER_ADDED). Подумай, какой метод подойдет для
этого лучше всего.
11.5.	Сообщать новому участнику о существующих участниках
11.6.	Запускать главный цикл обработки сообщений сервером
11.7.	Обеспечить закрытие соединения при возникновении исключения
11.8.	Отловить все исключения типа IOException и ClassNotFoundException, вывести в
консоль информацию, что произошла ошибка при обмене данными с удаленным
адресом
11.9.	После того как все исключения обработаны, если п.11.3 отработал и возвратил
нам имя, мы должны удалить запись для этого имени из connectionMap и разослать
всем остальным участникам сообщение с типом USER_REMOVED и сохраненным
именем.
11.10.	Последнее, что нужно сделать в методе run() – вывести сообщение,
информирующее что соединение с удаленным адресом закрыто.
Наш сервер полностью готов. Попробуй его запустить.
 */
