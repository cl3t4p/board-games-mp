import Util.ServerHandler;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //GUI.startAPP(args);

        ServerHandler serverHandler = new ServerHandler(5000);
        serverHandler.start();
    }


}
