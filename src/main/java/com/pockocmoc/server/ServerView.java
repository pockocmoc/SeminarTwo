package com.pockocmoc.server;

import com.pockocmoc.client.Client;

public interface ServerView {
    void disconnectUser(Client client);
    boolean connectUser(Client client);
    String getHistory();

    void sendMessage(String text);
    void answerAll(String text);
    void saveInLog(String text);
    String readLog();
    void appendLog(String text);
}
