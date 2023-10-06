package com.pockocmoc;

import com.pockocmoc.client.ClientGUI;
import com.pockocmoc.server.Server;
import com.pockocmoc.server.ServerWindow;

public class Main {
    public static void main(String[] args) {

        Server server = new Server(new ServerWindow());
        new ClientGUI(server);
        new ClientGUI(server);

    }
}