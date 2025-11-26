package task4;
public class PumpkinSpiceLatte extends Cappuccino {
    private final int mgOfPumpkinSpice;
    private final SyrupType syrup;

    public PumpkinSpiceLatte(Intensity coffeeIntensity, int mlOfMilk, int mgOfPumpkinSpice, SyrupType syrup) {
        super(coffeeIntensity, mlOfMilk);
        this.mgOfPumpkinSpice = mgOfPumpkinSpice;
        this.syrup = syrup;
    }

    public void makePumpkinSpiceLatte() {
        System.out.println("=====Making Coffee=====");
        System.out.println("Intensity set to " + getCoffeeIntensity());
        System.out.println("Adding " + getMlOfMilk() + " ml of Milk");
        System.out.println("Adding " + syrup + " syrup");
        System.out.println("Adding " + mgOfPumpkinSpice + " mg of Pumpkin Spice");
        System.out.println("=".repeat(20));

    }

    @Override
    public void printCoffeeDetails() {
        System.out.println("=====Your Coffee=====");
        System.out.println("Coffee Name: Pumpkin Spice Latte");
        System.out.println("Coffee Intensity:" + getCoffeeIntensity());
        System.out.println("Milk:" + getMlOfMilk() + "ml");
        System.out.println("Pumpkin Spice: " + mgOfPumpkinSpice + " mg");
        System.out.println("Syrup Type: " + syrup +"\n");
        System.out.println("=".repeat(20));
    }
}