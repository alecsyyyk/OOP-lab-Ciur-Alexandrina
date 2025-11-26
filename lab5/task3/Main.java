package task3;
public class Main {
    public static void main(String[] args) {
        Americano americano = new Americano(Intensity.NORMAL, 200);
        americano.makeAmericano().printCoffeeDetails();

        Cappuccino cappuccino = new Cappuccino(Intensity.LIGHT, 150);
        cappuccino.makeCappuccino().printCoffeeDetails();

        SyrupCappuccino syrupCappuccino = new SyrupCappuccino(Intensity.NORMAL, 200, SyrupType.MACADAMIA);
        syrupCappuccino.makeSyrupCappuccino().printCoffeeDetails();

        PumpkinSpiceLatte pumpkinLatte = new PumpkinSpiceLatte(Intensity.LIGHT, 200, 20);
        pumpkinLatte.makePumpkinSpiceLatte().printCoffeeDetails();
    }
}