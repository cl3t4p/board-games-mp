package Util;


import Games.TicTacToe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerHandler extends Thread {
    DateFormat fordate = new SimpleDateFormat("yyyy/MM/dd");
    DateFormat fortime = new SimpleDateFormat("hh:mm:ss");
    final ServerSocket ss;
    public ServerHandler(int port) throws IOException {
        ss = new ServerSocket(port);
        Socket s = null;

    }

    @Override
    public void run() {
        String received;
        String toreturn;
        Socket socket = null;
        try {
            socket = ss.accept();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true){


            try {

                System.out.println("A new client is connected : " + socket);
                DataInputStream dis = new DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                dos.writeUTF("Game : \n"+
                        "Type exit to terminate connection.");

                received = dis.readUTF();

                if(received.equals("exit"))
                {
                    System.out.println("Client " + socket + " sends exit...");
                    System.out.println("Closing this connection.");
                    socket.close();
                    System.out.println("Connection closed");
                    break;
                }

                Date date = new Date();

                // write on output stream based on the
                // answer from the client
                switch (received) {

                    case "TTT":
                        dos.writeUTF("Player 2 name : ?");

                        toreturn = fordate.format(date);
                        dos.writeUTF(toreturn);
                        break;

                    case "Time":
                        toreturn = fortime.format(date);
                        dos.writeUTF(toreturn);
                        break;

                    default:
                        dos.writeUTF("Invalid input");
                        break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
