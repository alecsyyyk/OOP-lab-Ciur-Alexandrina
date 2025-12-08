public class CarStation {
    private final Dineable dinner;
    private final Fuelable fueler;
    private final Queue<Car> carsQueue;

    public CarStation(Dineable dinner, Fuelable fueler, Queue<Car> carsQueue) {
        this.dinner = dinner;
        this.fueler = fueler;
        this.carsQueue = carsQueue;
    }

    public void enqueue(Car car) {
        carsQueue.enqueue(car);
    }

    public void processAll() {
        while (!carsQueue.isEmpty()) {
            Car car = carsQueue.dequeue();
            
            // Always wash the car
            System.out.println("Washing car " + car.getId());
            
            // Fuel based on type
            fueler.fuel(car);
            
            // Serve dinner if needed
            if (car.isDining()) {
                dinner.serve(car);
            }
            
            System.out.println("Car " + car.getId() + " processing complete\n");
        }
    }

    public boolean isEmpty() {
        return carsQueue.isEmpty();
    }

    public int size() {
        return carsQueue.size();
    }
}
