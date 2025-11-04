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
            System.out.println("The " + this.model + "is bigger than the"  + m.model);
        } else if (thisArea < otherArea) {
            System.out.println("The " + this.model + " is smaller than the " + m.model);
        } else {
            System.out.println("Both displays have the same size.");
        }
        System.out.println();
    }

    public void compareSharpness(Display m){
        if (this.ppi > m.ppi) {
            System.out.println( this.model + " is sharpness tahn " + this.ppi);
        } else if (this.ppi < m.ppi) {
            System.out.println( this.model + " is less sharpness than " + this.ppi);
        } else {
            System.out.println("Both displays have the same sharpness.");
        }

        System.out.println();
    }

    public void compareWithMonitor(Display m){
        
        System.out.println("Comparing " + this.model + " with " + m.model + ":");
        compareSize(m);
        compareSharpness(m);
    
        System.out.println();
        
    }

    public static void main(String[] args) {
        Display monitor1 = new Display(1920, 1080, 96.0f, "Samsung S24");
        Display monitor2 = new Display(2560, 1440, 109.0f, "Dell UltraSharp");
        Display monitor3 = new Display(3840, 2160, 163.0f, "LG 4K");

        System.out.println("Comparison of Display");

        monitor1.compareSize(monitor2);
        monitor2.compareSharpness(monitor3);
        monitor1.compareWithMonitor(monitor3);

        System.out.println("Comaprison complete.");


    }
}
