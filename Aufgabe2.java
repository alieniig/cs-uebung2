import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Aufgabe2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter a domain name or an IP address (or 'Ende' to quit):");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("Ende")) {
                break;
            }

            try {
                InetAddress address = InetAddress.getByName(input);
                if (isIpAddress(input)) {
                    System.out.println("Domain Name: " + address.getHostName());
                } else {
                    System.out.println("IP Address: " + address.getHostAddress());
                }
            } catch (UnknownHostException e) {
                System.out.println("Unknown host");
            }
        }

        scanner.close();
    }

    private static boolean isIpAddress(String input) {
        String[] parts = input.split("\\.");
        if (parts.length != 4) {
            return false;
        }
        for (String part : parts) {
            int value;
            try {
                value = Integer.parseInt(part);
            } catch (NumberFormatException e) {
                return false;
            }
            if (value < 0 || value > 255) {
                return false;
            }
        }
        return true;
    }
}