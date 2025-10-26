
import java.io.IOException;
import java.nio.file.Paths;

public class MainApp {
     public static void main(String[] args) {
        DashboardManager manager = new DashboardManager();

        AnalyticsDashboard q1 = new AnalyticsDashboard(200, 5000.0f, 50, "Q1 2025");
        AnalyticsDashboard q2 = new AnalyticsDashboard(320, 8200.75f, 80, "Q2 2025");

        manager.addDashboard(q1);
        manager.addDashboard(q2);

        System.out.println("Initial reports:");
        manager.generateAllReports();

        System.out.println("\nRefreshing all dashboards...");
        manager.refreshAll();

        System.out.println("\nReports after refresh:");
        manager.generateAllReports();

        System.out.println("\nExporting all dashboards to output.csv ...");
        try {
            manager.exportAll(Paths.get("output.csv"));
            System.out.println("Export complete: output.csv");
        } catch (IOException e) {
            System.err.println("Export failed: " + e.getMessage());
        }

        System.out.println("\nDisplay metrics:");
        manager.displayAllMetrics();
    }
}
