public class Car {
    private int id;
    private String type;
    private String passengers;
    private boolean isDining;
    private int consumption;

    public Car(int id, String type, String passengers, boolean isDining, int consumption) {
        this.id = id;
        this.type = type;
        this.passengers = passengers;
        this.isDining = isDining;
        this.consumption = consumption;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getPassengers() {
        return passengers;
    }

    public boolean isDining() {
        return isDining;
    }

    public int getConsumption() {
        return consumption;
    }

    @Override
    public String toString() {
        return "Car{id=" + id + ", type='" + type + "', passengers='" + passengers + 
               "', isDining=" + isDining + ", consumption=" + consumption + "}";
    }
}
