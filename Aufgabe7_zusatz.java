import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CopyOnWriteArrayList;

public class Aufgabe7_zusatz {

    private static CopyOnWriteArrayList<PrintWriter> clientOutputs = new CopyOnWriteArrayList<>();

    public static void main(String[] args) throws Exception {
        start(1234);
    }

    public static void start(int port) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                new Thread(() -> handleClient(clientSocket)).start();
            }
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true)) {

            clientOutputs.add(out);

            System.out.println("Client connected");
            out.println("Welcome to the echo server");

            String line;
            while ((line = in.readLine()) != null) {
                System.out.println("Received: " + line);
                for (PrintWriter writer : clientOutputs) {
                    writer.println("Echo: " + line);
                }
                if (line.trim().equals("BYE")) {
                    break;
                }
            }

            clientOutputs.remove(out);
            System.out.println("Client disconnected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}