package server;

import tools.LoadProperties;

import java.io.IOException;

public enum SocketConfig {
    Instance;
    public int PORT;
    SocketConfig() {
        LoadProperties p = new LoadProperties("server.properties");
        PORT = Integer.parseInt(p.getProp("port"));
    }
}
