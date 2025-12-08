public class GasFuelable implements Fuelable {
    @Override
    public void fuel(Car car) {
        System.out.println("Refilling gas for car " + car.getId() + 
                         " (" + car.getConsumption() + " liters)");
    }
}
