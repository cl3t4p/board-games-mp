package Util.Server;


import io.micronaut.runtime.Micronaut;

public class Server {

    public static void main(String[] args) {
        Micronaut.run(Server.class, args);
    }
}