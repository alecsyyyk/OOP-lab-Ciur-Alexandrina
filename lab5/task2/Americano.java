package task2;
public class Americano extends Coffee {
    private int mlOfWater;
    private final String coffeeName = "Americano";

    Americano(Intensity coffeeIntensity, int mlOfWater) {
        super(coffeeIntensity);
        this.mlOfWater = mlOfWater;
    }

    public int getMlOfWater() {
        return mlOfWater;
    }

    public void printCoffeeDetails(){
        System.out.println("Americano:");
        super.printCoffeeDetails();
        System.out.println("Water: " + mlOfWater + "ml");
        System.out.println();
    }

    
}
