package com.pockocmoc.server;

import com.pockocmoc.client.Client;
import com.pockocmoc.client.ClientGUI;

import java.io.FileReader;
import java.io.FileWriter;

public class Server implements ServerView {


    public static final String LOG_PATH = "src/main/java/com/pockocmoc/repository/log.txt";
    ServerWindow serverWindow;

    public Server(ServerWindow serverWindow) {
        this.serverWindow = serverWindow;
    }




    @Override
    public void disconnectUser(Client client) {
        serverWindow.clientList.remove(client);
        if (client != null) {
            client.disconnect();
        }
    }

    public void diconnectClientGUI(ClientGUI clientGUI) {
        serverWindow.clientList.remove(clientGUI);
        if (clientGUI != null) {
            clientGUI.disconnectFromServer();
        }
    }

    @Override
    public boolean connectUser(Client client) {
        if (!serverWindow.work) {
            return false;
        }
        serverWindow.clientList.add(client);
        return true;
    }

    @Override
    public String getHistory() {
        return readLog();
    }

    @Override
    public void sendMessage(String text) {
        if (!serverWindow.work) {
            return;
        }
        appendLog(text);
        answerAll(text);
        saveInLog(text);
    }

    @Override
    public void answerAll(String text) {
        for (Client client : serverWindow.clientList) {
            client.printText(text);
        }
    }

    @Override
    public void saveInLog(String text) {
        try (FileWriter writer = new FileWriter(LOG_PATH, true)) {
            writer.write(text);
            writer.write("\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String readLog() {
        StringBuilder stringBuilder = new StringBuilder();
        try (FileReader reader = new FileReader(LOG_PATH)) {
            int c;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
            stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void appendLog(String text) {
        serverWindow.log.append(text + "\n");
    }

}
