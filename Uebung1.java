import java.net.InetAddress;
import java.net.*;
import java.util.Scanner;

public class Uebung1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a domain name:");
        String domainName = scanner.nextLine();

        try {
            InetAddress address = InetAddress.getByName(domainName);
            System.out.println("IP Address: " + address.getHostAddress());
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
        }

        System.out.println("Enter an IP address:");
        String ipAddress = scanner.nextLine();

        try {
            InetAddress address = InetAddress.getByName(ipAddress);
            System.out.println("Domain Name: " + address.getHostName());
        } catch (UnknownHostException e) {
            System.out.println("Unknown host");
        }

        scanner.close();
    }
}

