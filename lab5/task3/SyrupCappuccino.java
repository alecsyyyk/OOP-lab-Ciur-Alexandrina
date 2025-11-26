package task3;
public class SyrupCappuccino extends Cappuccino {
    private SyrupType syrup;

    public SyrupCappuccino(Intensity coffeeIntensity, int mlOfMilk, SyrupType syrup) {
        super(coffeeIntensity, mlOfMilk);
        this.syrup = syrup;
    }

    public SyrupType getSyrup() {
        return syrup;
    }

    public SyrupCappuccino makeSyrupCappuccino() {
        System.out.println("Making Syrup Cappuccino");
        System.out.println("Intensity set to " + getCoffeeIntensity());
        System.out.println("Adding " + getMlOfMilk() + " ml of Milk");
        System.out.println("Adding " + syrup + " syrup\n");
        return this;
    }

    @Override
    public void printCoffeeDetails() {
        System.out.println("Coffee Name: Syrup Cappuccino");
        System.out.println("Coffee Intensity: " + getCoffeeIntensity());
        System.out.println("Milk: " + getMlOfMilk() + " ml");
        System.out.println("Syrup Type: " + syrup + "\n");
    }
}