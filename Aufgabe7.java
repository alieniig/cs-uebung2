import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Aufgabe7 {

    public static void main(String[] args) throws Exception {
        start(1234);
    }
    public static void start(int port) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {
                        
                    System.out.println("Client connected");
                    out.println("Welcome to the echo server");
                    out.flush();

                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println("Received: " + line);
                        out.println("Echo: " + line);
                        out.flush();
                        if (line.trim().equals("BYE")) {
                            break;
                        }
                    }

                    System.out.println("Client disconnected");
                }
            }
        }
    }

    
}