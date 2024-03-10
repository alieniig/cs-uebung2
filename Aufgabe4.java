import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.NtpV3Packet;
import org.apache.commons.net.ntp.TimeInfo;
import java.net.InetAddress;


public class Aufgabe4 {
    public static void main(String[] args) {
        NTPUDPClient client = new NTPUDPClient();
        

        String[] timeServers = {
            "time.google.com",
            "time.nist.gov",
            "time.apple.com",
            "time.fu-berlin.de",
            "time.windows.com"
        };

        try {
            for (String server : timeServers) {
                InetAddress address = InetAddress.getByName(server);
                TimeInfo timeInfo = client.getTime(address);
                System.out.println(server);
                processTimeInfo(timeInfo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }

    private static void processTimeInfo(TimeInfo timeInfo) {
        if (timeInfo == null) {
            System.out.println("Failed to retrieve time information");
            return;
        }

        NtpV3Packet ntpPacket = timeInfo.getMessage();
        int stratum = ntpPacket.getStratum();
        int version = ntpPacket.getVersion();
        int mode = ntpPacket.getMode();
        int poll = ntpPacket.getPoll();
        int precision = ntpPacket.getPrecision();
        int delay = ntpPacket.getRootDelay(); 
        double dispersion = ntpPacket.getRootDispersionInMillis();
    
        System.out.println("stratum: " + stratum);
        System.out.println("version: " + version);
        System.out.println("mode: " + mode);
        System.out.println("poll: " + poll);
        System.out.println("precision: " + precision);
        System.out.println("delay: " + delay);
        System.out.println("dispersion(ms): " + dispersion);
        System.out.println("----------------");
    }
}