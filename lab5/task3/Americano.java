package task3;
public class Americano extends Coffee {
    private int mlOfWater;

    public Americano(Intensity coffeeIntensity, int mlOfWater) {
        super(coffeeIntensity);
        this.mlOfWater = mlOfWater;
    }

    public Americano makeAmericano() {
        System.out.println("Making Americano");
        System.out.println("Intensity set to " + getCoffeeIntensity());
        System.out.println("Adding " + mlOfWater + " ml of Water\n");
        return this;
    }

    @Override
    public void printCoffeeDetails() {
        System.out.println("Coffee Name: Americano");
        System.out.println("Coffee Intensity: " + getCoffeeIntensity());
        System.out.println("Water: " + mlOfWater + " ml");
        System.out.println();
    }
}