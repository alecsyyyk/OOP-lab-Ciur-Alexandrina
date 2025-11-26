package task2;
public class Cappuccino extends Coffee {
    private int mlOfMilk;

    public Cappuccino(Intensity coffeeIntensity, int mlOfMilk) {
        super(coffeeIntensity);
        this.mlOfMilk = mlOfMilk;
    }

    public int getMlOfMilk() {
        return mlOfMilk;
    }

    public void printCoffeeDetails(){
        System.out.println("Cappuccino:");
        super.printCoffeeDetails();
        System.out.println("Milk: " + mlOfMilk + "ml");
        System.out.println();
    }
}
