package task4;
class Coffee {
    private final Intensity coffeeIntensity;

    public Coffee(Intensity coffeeIntensity, SyrupType syrup) {
        this.coffeeIntensity = coffeeIntensity;
    }

    public Intensity getCoffeeIntensity() {
        return coffeeIntensity;
    }
    
    public void printCoffeeDetails() {
        System.out.println("Coffee Intensity: " + coffeeIntensity);
    }
}