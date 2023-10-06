package com.pockocmoc.server;

import com.pockocmoc.client.Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ServerWindow extends JFrame {
    public static final int WIDTH = 400;
    public static final int HEIGHT = 300;


    List<Client> clientList;

    Server server = new Server(ServerWindow.this);

    JButton btnStart, btnStop;
    JTextArea log;
    boolean work;

    public ServerWindow() {
        clientList = new ArrayList<>();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setLocationRelativeTo(null);

        createPanel();

        setVisible(true);
    }


    private void createPanel() {
        log = new JTextArea();
        add(log);
        add(createButtons(), BorderLayout.SOUTH);
    }

    private Component createButtons() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
        btnStart = new JButton("Start");
        btnStop = new JButton("Stop");

        btnStart.addActionListener(e -> {
            if (work) {
                server.appendLog("Сервер уже был запущен");
            } else {
                work = true;
                server.appendLog("Сервер запущен!");
            }
        });

        btnStop.addActionListener(e -> {
            if (!work) {
                server.appendLog("Сервер уже был остановлен");
            } else {
                work = false;
                for (Client client : clientList) {
                    server.disconnectUser(client);
                }
                //TODO поправить удаление
                server.appendLog("Сервер остановлен!");
            }
        });

        panel.add(btnStart);
        panel.add(btnStop);
        return panel;
    }


}