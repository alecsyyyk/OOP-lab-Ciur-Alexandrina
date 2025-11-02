public class Display {
    int width;
    int height;
    float ppi;
    String model;

    public Display(int width, int height, float ppi, String model) {
        this.width = width;
        this.height = height;
        this.ppi = ppi;
        this.model = model;
    }
    
    public void compareSize(Display m) {
        int thisArea = this.width * this.height;
        int otherArea = m.width * m.height;

        if (thisArea > otherArea) {
            System.out.println("The " + this.model + " display (" + this.width + "x" + this.height + 
                            ") is bigger than the " + m.model + " display (" + m.width + "x" + m.height + ")");
        } else if (thisArea < otherArea) {
            System.out.println("The " + this.model + " display (" + this.width + "x" + this.height + 
                             ") is smaller than the " + m.model + " display (" + m.width + "x" + m.height + ").");
        } else {
            System.out.println("Both displays have the same size.");
        }
    }

    public void compareSharpness(Display m){
        if (this.ppi > m.ppi) {
            System.out.println("The " + this.model + " display with " + this.ppi + " PPI is sharper than the " + 
                             m.model + " display with " + m.ppi + " PPI.");
        } else if (this.ppi < m.ppi) {
            System.out.println("The " + this.model + " display with " + this.ppi + " PPI is less sharp than the " + 
                             m.model + " display with " + m.ppi + " PPI.");
        } else {
            System.out.println("Both displays have the same sharpness.");
        }
    }

    public void compareWithMonitor(Display m){
        int thisArea = this.width * this.height;
        int otherArea = m.width * m.height;

        System.out.println("Comparison Display:" );
        System.out.println("Comparing" + this.model + " vs" + m.model);

        // Size comparison
        if (thisArea > otherArea) {
            System.out.println("Size: " + this.model + " is bigger (" + thisArea + " vs " + otherArea + " pixels)");
        } else if (thisArea < otherArea) {
            System.out.println("Size: " + m.model + " is bigger (" + otherArea + " vs " + thisArea + " pixels)");
        } else {
            System.out.println("Size: Both have equal size");
        }
        
        // Sharpness comparison
        if (this.ppi > m.ppi) {
            System.out.println("Sharpness: " + this.model + " is sharper (" + this.ppi + " vs " + m.ppi + " PPI)");
        } else if (this.ppi < m.ppi) {
            System.out.println("Sharpness: " + m.model + " is sharper (" + m.ppi + " vs " + this.ppi + " PPI)");
        } else {
            System.out.println("Sharpness: Both have equal sharpness");
        }
    }

    public static void main(String[] args) {
        Display monitor1 = new Display(1920, 1080, 96.0f, "Samsung S24");
        Display monitor2 = new Display(2560, 1440, 109.0f, "Dell UltraSharp");
        Display monitor3 = new Display(3840, 2160, 163.0f, "LG 4K");

        System.out.println("Let's just try :");

        System.out.println("Comparing monitors with Size and Sharpness:");
        monitor1.compareSize(monitor2);
        monitor1.compareSharpness(monitor2);

        monitor2.compareSize(monitor3);
        monitor2.compareSharpness(monitor3);

        monitor1.compareWithMonitor(monitor3);
    }
}
