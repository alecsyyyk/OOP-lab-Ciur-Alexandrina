package task2;

public class Main {
    public static void main(String[] args) {
        Coffee americano = new Americano(Intensity.NORMAL, 200);
        americano.printCoffeeDetails();

        Coffee cappuccino = new Cappuccino(Intensity.LIGHT, 150);
        cappuccino.printCoffeeDetails();

        Coffee pumpkinLatte = new PumpkinSpiceLatte(Intensity.LIGHT, 200, 20);
        pumpkinLatte.printCoffeeDetails();

        Coffee syrupCappuccino = new SyrupCappuccino(Intensity.NORMAL, 200, SyrupType.MACADAMIA);
        syrupCappuccino.printCoffeeDetails();
    }
}
