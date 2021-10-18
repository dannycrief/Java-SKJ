package nr3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TCPClient {
    public static void main(String[] args) throws UnknownHostException {
        Socket socket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        String address = "";
        int port = 44444;

        try {
            socket = new Socket(address, port);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.out.println("Unknown host.");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("No I/O");
            System.exit(-1);
        }

        try {
            System.out.println(in.readLine() + " OK 1.");
            System.out.println(in.readLine() + " OK 2.");
            out.println("123");
            out.println("456");
            System.out.println(in.readLine() + " OK 3.");
        } catch (IOException e) {
            System.out.println("Error during communication");
        }

        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Cannot close the socket.");
            System.exit(-1);
        }
    }
}
