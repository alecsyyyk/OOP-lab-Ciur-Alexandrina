package task4;
import java.util.Scanner;

public class Barista {
    private Scanner scanner = new Scanner(System.in);

    public void makeCoffee() {
        System.out.println("=".repeat(20));
        System.out.println("What type of coffee do you want?");
        System.out.println("1. Americano");
        System.out.println("2. Cappuccino");
        System.out.println("3. Syrup Cappuccino");
        System.out.println("4. Pumpkin Spice Latte");
        System.out.println("Choose coffee (1-4): ");
        System.out.println("=".repeat(20));

        int coffeeChoice = scanner.nextInt();

        String coffeeType = "";
        switch (coffeeChoice) {
            case 1:
                coffeeType = "Americano";
                break;
            case 2:
                coffeeType = "Cappuccino";
                break;
            case 3:
                coffeeType = "SyrupCappuccino";
                break;
            case 4:
                coffeeType = "PumpkinSpiceLatte";
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }
        System.out.println("=".repeat(20));
        System.out.println("What type of syrup do you want?");
        System.out.println("1. None");
        System.out.println("2. Macadami");
        System.out.println("3. Vanilla");
        System.out.println("4. Coconut");
        System.out.println("5. Caramel");
        System.out.println("6. Chocolate");
        System.out.println("7. Popcorn");
        System.out.println("Choose syrup (1-7): ");
        System.out.println("=".repeat(20));
        int syrupChoice = scanner.nextInt();
        SyrupType syrup = SyrupType.values()[syrupChoice - 1];

        System.out.println("=".repeat(15));
        System.out.println("Select the intensity of the coffee:");
        System.out.println("1. Light");
        System.out.println("2. Normal");
        System.out.println("3. Strong");
        System.out.println("Choose intensity (1-3): ");
        System.out.println("=".repeat(15));
        int intensityChoice = scanner.nextInt();
        Intensity intensity = Intensity.values()[intensityChoice - 1];

        Coffee coffee = null;

        switch (coffeeType) {
            case "Americano":
                coffee = new Americano(intensity, 200, syrup); // Water for Americano
                break;
            case "Cappuccino":
                coffee = new Cappuccino(intensity, 150); // Milk for Cappuccino
                break;
            case "SyrupCappuccino":
                coffee = new SyrupCappuccino(intensity, 150, syrup);
                break;
            case "PumpkinSpiceLatte":
                coffee = new PumpkinSpiceLatte(intensity, 150, 20,syrup); // Example spice amount
                break;
        }

        // Making the coffee and printing details
        if (coffee instanceof Americano) {
            ((Americano) coffee).makeAmericano();
        } else if (coffee instanceof Cappuccino) {
            ((Cappuccino) coffee).makeCappuccino();
            if (coffee instanceof SyrupCappuccino) {
                ((SyrupCappuccino) coffee).makeSyrupCappuccino();
            } else if (coffee instanceof PumpkinSpiceLatte) {
                ((PumpkinSpiceLatte) coffee).makePumpkinSpiceLatte();
            }
        }

        coffee.printCoffeeDetails();
    }
}