package task4;
public class SyrupCappuccino extends Cappuccino {
    private final SyrupType syrup;

    public SyrupCappuccino(Intensity coffeeIntensity, int mlOfMilk, SyrupType syrup) {
        super(coffeeIntensity, mlOfMilk);
        this.syrup = syrup;
    }

    public void makeSyrupCappuccino() {
        System.out.println("=====Making Coffee=====");
        System.out.println("Intensity set to " + getCoffeeIntensity());
        System.out.println("Adding " + getMlOfMilk() + " ml of Milk");
        System.out.println("Adding " + syrup + " syrup");
        System.out.println("=".repeat(20));

    }

    @Override
    public void printCoffeeDetails() {
        System.out.println("=====Your Coffee=====");
        System.out.println("Coffee Name: Syrup Cappuccino");
        System.out.println("Coffee Intensity:" + getCoffeeIntensity());
        System.out.println("Milk:" + getMlOfMilk() + "ml");
        System.out.print("Syrup Type: " + syrup +"\n");
        System.out.println("=".repeat(20));

    }
}
