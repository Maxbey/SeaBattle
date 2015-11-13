import controllers.SeaBattleServer;

import java.io.IOException;

public class ServerApp {
    public static void main(String[] args) throws Exception {
        SeaBattleServer server = new SeaBattleServer(6666);

        server.start();
    }
}
