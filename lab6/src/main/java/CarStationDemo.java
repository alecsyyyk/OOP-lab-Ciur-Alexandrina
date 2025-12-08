import com.google.gson.Gson;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CarStationDemo {
    public static void main(String[] args) {
        // Create implementations
        Dineable peopleDinner = new PeopleDineable();
        Dineable robotDinner = new RobotDineable();
        Fuelable electricFueler = new ElectricFuelable();
        Fuelable gasFueler = new GasFuelable();

        // Create car stations with different queue implementations
        CarStation electricPeopleStation = new CarStation(
            peopleDinner, 
            electricFueler, 
            new ArrayQueue<>(50)
        );
        
        CarStation electricRobotStation = new CarStation(
            robotDinner, 
            electricFueler, 
            new LinkedQueue<>(50)
        );
        
        CarStation gasPeopleStation = new CarStation(
            peopleDinner, 
            gasFueler, 
            new CircularQueue<>(50)
        );
        
        CarStation gasRobotStation = new CarStation(
            robotDinner, 
            gasFueler, 
            new ArrayQueue<>(50)
        );

        // Read JSON files from queue directory
        File queueDir = new File("queue");
        if (!queueDir.exists() || !queueDir.isDirectory()) {
            System.out.println("Queue directory not found. Run main.py first to generate cars.");
            return;
        }

        File[] jsonFiles = queueDir.listFiles((dir, name) -> name.endsWith(".json"));
        if (jsonFiles == null || jsonFiles.length == 0) {
            System.out.println("No car files found in queue directory.");
            return;
        }

        Gson gson = new Gson();
        int totalCars = 0;
        
        // Read and route cars to appropriate stations
        for (File jsonFile : jsonFiles) {
            try (FileReader reader = new FileReader(jsonFile)) {
                CarJson carJson = gson.fromJson(reader, CarJson.class);
                Car car = new Car(
                    carJson.id,
                    carJson.type,
                    carJson.passengers,
                    carJson.isDining,
                    carJson.consumption
                );
                
                // Route to appropriate station based on type and passengers
                if ("ELECTRIC".equals(car.getType())) {
                    if ("PEOPLE".equals(car.getPassengers())) {
                        electricPeopleStation.enqueue(car);
                    } else {
                        electricRobotStation.enqueue(car);
                    }
                } else { // GAS
                    if ("PEOPLE".equals(car.getPassengers())) {
                        gasPeopleStation.enqueue(car);
                    } else {
                        gasRobotStation.enqueue(car);
                    }
                }
                totalCars++;
            } catch (IOException e) {
                System.err.println("Error reading file " + jsonFile.getName() + ": " + e.getMessage());
            }
        }

        System.out.println("=== CAR WASH STATION ===");
        System.out.println("Total cars loaded: " + totalCars + "\n");

        // Process all stations
        System.out.println("--- Electric + People Station (ArrayQueue) ---");
        electricPeopleStation.processAll();
        
        System.out.println("--- Electric + Robot Station (LinkedQueue) ---");
        electricRobotStation.processAll();
        
        System.out.println("--- Gas + People Station (CircularQueue) ---");
        gasPeopleStation.processAll();
        
        System.out.println("--- Gas + Robot Station (ArrayQueue) ---");
        gasRobotStation.processAll();

        System.out.println("=== ALL CARS PROCESSED ===");
    }

    // Helper class for JSON deserialization
    private static class CarJson {
        int id;
        String type;
        String passengers;
        boolean isDining;
        int consumption;
    }
}
