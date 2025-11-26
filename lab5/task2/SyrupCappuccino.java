package task2;
public class SyrupCappuccino extends Cappuccino {
    private SyrupType syrup;
    private final String coffee = "SyrupCappuccino";

    public SyrupCappuccino(Intensity coffeeIntensity, int mlOfMilk, SyrupType syrup) {
        super(coffeeIntensity, mlOfMilk);
        this.syrup = syrup;
    }

    public SyrupType getSyrup() {
        return syrup;
    }

    public String getCoffee() {
        return coffee;
    }

    public void printCoffeeDetails(){
        System.out.println("Syrup Cappuccino:");
        System.out.println("Coffee intensity: " + getCoffeeIntensity());
        System.out.println("Cappuccino milk: " + getMlOfMilk() + "ml");
        System.out.println("Syrup type: " + syrup);
        System.out.println();
    }
}