import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DashboardManager {
    private final List<AnalyticsDashboard> dashboards = new ArrayList<>();
    private final DataService dataService = new DataService();

    public void addDashboard(AnalyticsDashboard dash) {
        dashboards.add(dash);
    }

    public void refreshAll() {
        for (AnalyticsDashboard d : dashboards) {
            dataService.fetchLatest(d);
        }
    }

    public void generateAllReports() {
        for (AnalyticsDashboard d : dashboards) {
            d.generateReport();
        }
    }

    public void exportAll(Path file) throws IOException {
        for (AnalyticsDashboard d : dashboards) {
            Exporter.exportToFile(d, file);
        }
    }

    public void displayAllMetrics() {
        for (AnalyticsDashboard d : dashboards) {
            d.displayMetrics();
            System.out.println("---");
        }
    }
}
