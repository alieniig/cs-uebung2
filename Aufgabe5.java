import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;

public class Aufgabe5 {

    public static void main(String[] args) throws Exception {
        get("http://www.google.de/index.html");
        get("https://www.tagesschau.de/index.html");
    }

    public static void get(String urlString) throws Exception {
        URL url = new URL(urlString);
        String host = url.getHost();
        String path = url.getPath().isEmpty() ? "/" : url.getPath();

        try (Socket socket = new Socket(host, 80);
             PrintWriter request = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader response = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            request.print("GET " + path + " HTTP/1.1\r\n");
            request.print("Host: " + host + "\r\n");
            request.print("\r\n");
            request.flush();

            String line;
            while ((line = response.readLine()) != null) {
                System.out.println(line);
            }
        }
    }

    
}