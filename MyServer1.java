import java.io.*;
import java.net.*;

public class MyServer1 {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        DataInputStream in = null;
        DataOutputStream out = null;

        try {
            serverSocket = new ServerSocket(5000);
            System.out.println("Server started. Waiting for a client ...");

            socket = serverSocket.accept();
            System.out.println("Client accepted.");

            in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            out = new DataOutputStream(socket.getOutputStream());

            String line = "";
            while (!line.equals("Over")) {
                try {
                    line = in.readUTF();
                    System.out.println("Client: " + line);

                    System.out.print("Server: ");
                    line = new BufferedReader(new InputStreamReader(System.in)).readLine();

                    out.writeUTF(line);
                } catch(IOException i) {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");

            socket.close();
            in.close();
        } catch(IOException i) {
            System.out.println(i);
        }
    }
}
