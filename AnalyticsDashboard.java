public class AnalyticsDashboard {
    
    private int userCount;
    private float revenue;
    private int activeUsers;
    private String timePeriod;

    public AnalyticsDashboard(int userCount, float revenue, int activeUsers, String timePeriod) {
        this.userCount = userCount;
        this.revenue = revenue;
        this.activeUsers = activeUsers;
        this.timePeriod = timePeriod;
    }

    public AnalyticsDashboard() {
        this(0, 0.0f, 0, "unspecified");
    }

    public void generateReport() {
        System.out.println("=== Analytics Report (" + timePeriod + ") ===");
        System.out.println("Total users: " + userCount);
        System.out.printf("Revenue: $%.2f%n", revenue);
        System.out.println("Active users: " + activeUsers);
    }

    public void refreshData() {
        
        activeUsers += 5;
        userCount = Math.max(userCount, activeUsers);
        revenue += 100.0f;
        System.out.println("Data refreshed.");
    }

    public void exportData() {
        // print CSV line to stdout (could be saved to file later)
        System.out.println("userCount,revenue,activeUsers,timePeriod");
        System.out.printf("%d,%.2f,%d,%s%n", userCount, revenue, activeUsers, timePeriod);
    }

    public void displayMetrics() {
        System.out.println("- userCount: " + userCount);
        System.out.println("- revenue: " + revenue);
        System.out.println("- activeUsers: " + activeUsers);
        System.out.println("- timePeriod: " + timePeriod);
    }

    // simple getters/setters
    public int getUserCount() { return userCount; }
    public void setUserCount(int userCount) { this.userCount = userCount; }

    public float getRevenue() { return revenue; }
    public void setRevenue(float revenue) { this.revenue = revenue; }

    public int getActiveUsers() { return activeUsers; }
    public void setActiveUsers(int activeUsers) { this.activeUsers = activeUsers; }

    public String getTimePeriod() { return timePeriod; }
    public void setTimePeriod(String timePeriod) { this.timePeriod = timePeriod; }

}