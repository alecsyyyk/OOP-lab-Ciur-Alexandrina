package task3;
public class PumpkinSpiceLatte extends Cappuccino {
    private int mgOfPumpkinSpice;

    public PumpkinSpiceLatte(Intensity coffeeIntensity, int mlOfMilk, int mgOfPumpkinSpice) {
        super(coffeeIntensity, mlOfMilk);
        this.mgOfPumpkinSpice = mgOfPumpkinSpice;
    }

    public PumpkinSpiceLatte makePumpkinSpiceLatte() {
        System.out.println("Making Pumpkin Spice Latte");
        System.out.println("Intensity set to " + getCoffeeIntensity());
        System.out.println("Adding " + getMlOfMilk() + " ml of Milk");
        System.out.println("Adding " + mgOfPumpkinSpice + " mg of Pumpkin Spice\n");
        return this;
    }

    @Override
    public void printCoffeeDetails() {
        System.out.println("Coffee Name: Pumpkin Spice Latte");
        System.out.println("Coffee Intensity: " + getCoffeeIntensity());
        System.out.println("Milk: " + getMlOfMilk() + " ml");
        System.out.println("Pumpkin Spice: " + mgOfPumpkinSpice + " mg\n");
    }
}