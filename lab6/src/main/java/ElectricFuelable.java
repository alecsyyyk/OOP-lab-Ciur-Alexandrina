public class ElectricFuelable implements Fuelable {
    @Override
    public void fuel(Car car) {
        System.out.println("Refilling electricity for car " + car.getId() + 
                         " (" + car.getConsumption() + " kWh)");
    }
}
