import java.io.*;
import java.net.*;

public class MyClient1 {
    public static void main(String[] args) {
        Socket socket = null;
        DataInputStream input = null;
        DataOutputStream out = null;

        try {
            socket = new Socket("localhost", 5000);
            System.out.println("Connected");

            input = new DataInputStream(System.in);

            out = new DataOutputStream(socket.getOutputStream());
        } catch(UnknownHostException u) {
            System.out.println(u);
        } catch(IOException i) {
            System.out.println(i);
        }

        String line = "";
        while (!line.equals("Over")) {
            try {
                line = input.readLine();
                out.writeUTF(line);

                line = new DataInputStream(socket.getInputStream()).readUTF();
                System.out.println("Server: " + line);
            } catch(IOException i) {
                System.out.println(i);
            }
        }

        try {
            input.close();
            out.close();
            socket.close();
        } catch(IOException i) {
            System.out.println(i);
        }
    }
}