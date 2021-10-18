package nr3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

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
                out.println("This is Stepan Kozurak.");
                out.println("715338.");
                String first = in.readLine();
                String second = in.readLine();
                out.println(first + second);
                System.out.println(first + " " + second);

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