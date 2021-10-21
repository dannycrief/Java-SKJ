package nr3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;

public class TestServer {
    public static class ServerThread extends Thread {
        private final Socket socket;

        public ServerThread(Socket socket) {
            super();
            this.socket = socket;
        }

        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                String first = in.readLine();
                if (first.equals("s25748")) {
                    out.println("Question for Stepan Kozurak accepted.");
                    System.out.println("[s25748] - accept");
                    String second = in.readLine();
                    if (second.equals("1234password")) {
                        out.println("password accepted");
                        System.out.println("["+first+"] OK");
                    } else {
                        System.out.println(second);
                    }
                } else {
                    out.println("declined");
                    System.out.println("reject");
                }

            } catch (IOException e) {
                // do nothing
            }
            try {
                socket.close();
            } catch (IOException e) {
                // do nothing
            }
        }
    }

    public void listenSocket(int port) {
        ServerSocket server = null;
        Socket client = null;
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            System.out.println("Could not listen");
            System.exit(-1);
        }
        System.out.println("Server listens on port: " + server.getLocalPort());
        while (true) {
            try {
                client = server.accept();
            } catch (IOException e) {
                System.out.println("Accept failed");
                System.exit(-1);
            }
            (new ServerThread(client)).start();
        }
    }

    public static void main(String[] args) {
        TestServer server = new TestServer();
        server.listenSocket(44444);
    }

}