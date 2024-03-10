import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import java.net.InetAddress;
import java.util.List;

public class NTPClientExample {
    public static void main(String[] args) {
        NTPUDPClient client = new NTPUDPClient();
        client.setDefaultTimeout(1000);

        String[] timeServers = {
                "ntp1.example.com",
                "ntp2.example.com",
                "ntp3.example.com",
                "ntp4.example.com",
                "ntp5.example.com"
        };

        try {
            for (String server : timeServers) {
                InetAddress address = InetAddress.getByName(server);
                TimeInfo timeInfo = client.getTime(address);
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
        long delay = timeInfo.getDelay();
        double dispersion = timeInfo.getDispersion();

        System.out.println("stratum: " + stratum);
        System.out.println("version: " + version);
        System.out.println("mode: " + mode);
        System.out.println("poll: " + poll);
        System.out.println("precision: " + precision);
        System.out.println("delay: " + delay);
        System.out.println("dispersion(ms): " + dispersion);
        System.out.println();
    }
}