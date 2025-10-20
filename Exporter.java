import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Exporter {
      public static void exportToFile(AnalyticsDashboard dash, Path file) throws IOException {
        String header = "userCount,revenue,activeUsers,timePeriod\n";
        if (!Files.exists(file)) {
            Files.write(file, header.getBytes(), StandardOpenOption.CREATE);
        }
        String line = String.format("%d,%.2f,%d,%s%n",
                dash.getUserCount(), dash.getRevenue(), dash.getActiveUsers(), dash.getTimePeriod());
        Files.write(file, line.getBytes(), StandardOpenOption.APPEND);
    }
}
