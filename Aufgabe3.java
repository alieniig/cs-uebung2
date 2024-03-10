import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Aufgabe3 {
    public static void main(String[] args) {
          Scanner scanner = new Scanner(System.in);

        System.out.println("Hostname:");
        String domainName = scanner.nextLine();

        try {
            // Verbindung aufbauen
            Socket socket = new Socket(domainName, 13);

            // Daten lesen
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = reader.readLine();
            System.out.println("Received response: " + response);        

            // Verbindung abbauen
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
