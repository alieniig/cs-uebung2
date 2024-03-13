import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

public class Aufgabe3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
<<<<<<< HEAD
    
        System.out.println("Hostname:");
        String domainName = scanner.nextLine();
  
=======
        
        System.out.println("Hostname:");
        String domainName = scanner.nextLine();
        if(scanner.nextLine()=="quit"){
            scanner.close();
        }

>>>>>>> test-repo/main
        try {
            // Verbindung aufbauen
            Socket socket = new Socket(domainName, 13);

            // Daten lesen
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String response = reader.readLine();
            System.out.println("Received response: " + response);        

            // Verbindung abbauen
            socket.close();
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
