package task2;
public class Coffee {
     private Intensity coffeeIntensity;
    private final String name = "Coffee";

    public Coffee(Intensity coffeeIntensity) {
        this.coffeeIntensity = coffeeIntensity;
    }

    public Intensity getCoffeeIntensity() {
        return coffeeIntensity;
    }

    public String getName() {
        return name;
    }

    public void printCoffeeDetails(){
        System.out.println("Coffee intensity: "+ coffeeIntensity);
    }
}
