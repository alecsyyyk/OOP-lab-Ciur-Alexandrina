package task3;

public class Display {
    int width;
    int height;
    float ppi;
    String model;

    //constructor
    public Display(int width, int height, float ppi, String model) {
        this.width = width;
        this.height = height;
        this.ppi = ppi;
        this.model = model;
    }
    
    //method
    public void compareSize(Display m) {
        int thisArea = this.width * this.height;
        int otherArea = m.width * m.height;

        if (thisArea > otherArea) {
            System.out.println("The " + this.model + " is bigger than the "  + m.model);
        } else if (thisArea < otherArea) {
            System.out.println("The " + this.model + " is smaller than the " + m.model);
        } else {
            System.out.println("Both displays have the same size.");
        }
    }

    public void compareSharpness(Display m){
        if (this.ppi > m.ppi) {
            System.out.println(this.model + " is sharper than " + m.model);
        } else if (this.ppi < m.ppi) {
            System.out.println(this.model + " is less sharp than " + m.model);
        } else {
            System.out.println("Both displays have the same sharpness.");
        }
    }

    public void compareWithMonitor(Display m){
        System.out.println("Comparing " + this.model + " with " + m.model + ":");
        compareSize(m);
        compareSharpness(m);
        System.out.println();
    }
}
