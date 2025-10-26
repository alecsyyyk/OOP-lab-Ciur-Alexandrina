public class DataService {
    public void fetchLatest(AnalyticsDashboard dash) {
         int deltaActive = (int) (Math.random() * 11); // 0..10
        dash.setActiveUsers(dash.getActiveUsers() + deltaActive);
        dash.setUserCount(Math.max(dash.getUserCount(), dash.getActiveUsers()));
        dash.setRevenue(dash.getRevenue() + (float) (Math.random() * 200.0));
    }
}
