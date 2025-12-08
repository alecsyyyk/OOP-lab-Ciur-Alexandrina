public class RobotDineable implements Dineable {
    @Override
    public void serve(Car car) {
        System.out.println("Charging the robots in car " + car.getId());
    }
}
