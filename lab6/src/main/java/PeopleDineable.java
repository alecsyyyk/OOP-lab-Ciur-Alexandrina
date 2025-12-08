public class PeopleDineable implements Dineable {
    @Override
    public void serve(Car car) {
        System.out.println("Feeding the people in car " + car.getId());
    }
}
