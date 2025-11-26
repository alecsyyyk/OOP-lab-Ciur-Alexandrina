package task4;
public class Americano extends Coffee {
    private final int mlOfWater;
    private final SyrupType syrup;

    public Americano(Intensity coffeeIntensity, int mlOfWater, SyrupType syrup) {
        super(coffeeIntensity, syrup);
        this.mlOfWater = mlOfWater;
        this.syrup = syrup;
    }

    public void makeAmericano() {
        System.out.println("=====Making Coffee=====");
        System.out.println("Intensity set to " + getCoffeeIntensity());
        System.out.println("Adding " + mlOfWater + " ml of Water\n");
        System.out.println("Adding " + syrup + " syrup\n");
        System.out.println("=".repeat(20));

    }

    @Override
    public void printCoffeeDetails() {
        System.out.println("=====Your Coffee=====");
        System.out.println("Coffee Name: Americano");
        super.printCoffeeDetails();
        System.out.println("Water: " + mlOfWater + " ml");
        System.out.println("Syrup Type: " + syrup);
        System.out.println("=".repeat(20));

    }
}