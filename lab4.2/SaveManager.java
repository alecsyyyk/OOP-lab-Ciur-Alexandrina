import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveManager {
    private FileManager fileManager;
    private List<Student> students;
    private List<Faculty> faculties;
    
    // Constructor
    public SaveManager() {
        this.fileManager = new FileManager("students.txt");
        this.students = new ArrayList<>();
        this.faculties = new ArrayList<>();
    }
    
    // Load all data - students from JSON, create faculties programmatically
    public void loadAllData() {
       
        this.students = StudentExample.loadStudentsFromJSON("students.json");
        
        // Create faculties and assign students
        createFacultiesAndAssignStudents();
        
        System.out.println(" All data loaded successfully!");
        System.out.println("  - " + students.size() + " students");
        System.out.println("  - " + faculties.size() + " faculties");
    }
    
    // Create faculties and assign students based on email patterns
    private void createFacultiesAndAssignStudents() {
        // Create 4 faculties
        Faculty fcim = new Faculty("Faculty of Computers, Informatics and Microelectronics",
                                  "FCIM", StudyField.SOFTWARE_ENGINEERING);
        Faculty fimit = new Faculty("Faculty of Mechanical, Industrial Engineering and Transport",
                                   "FIMIT", StudyField.MECHANICAL_ENGINEERING);
        Faculty fta = new Faculty("Faculty of Food Technology",
                                 "FTA", StudyField.FOOD_TECHNOLOGY);
        Faculty fua = new Faculty("Faculty of Urbanism and Architecture",
                                 "FUA", StudyField.URBANISM_ARCHITECTURE);
        
       
        if (students.size() >= 5) {
            fcim.addStudent(students.get(0));  // Ana
            fcim.addStudent(students.get(1));  // Ion
            fimit.addStudent(students.get(2)); // Maria
            fta.addStudent(students.get(3));   // Andrei
            fua.addStudent(students.get(4));   // Elena
        }
        
        // Add faculties to list
        faculties.add(fcim);
        faculties.add(fimit);
        faculties.add(fta);
        faculties.add(fua);
    }
    // Save all data to files
    public void saveAllData() {
        fileManager.saveStudents(students);
        fileManager.saveFaculties(faculties, "faculties.txt");
        System.out.println("All data saved successfully!");
    }
    
    // Get students
    public List<Student> getStudents() {
        return students;
    }
    
    // Get faculties
    public List<Faculty> getFaculties() {
        return faculties;
    }
    
    // Add new student
    public void addStudent(Student student) {
        students.add(student);
    }
    
    // Add student to faculty
    public void addStudentToFaculty(Student student, Faculty faculty) {
        if (!students.contains(student)) {
            students.add(student);
        }
        faculty.addStudent(student);
    }
    
    // Display all students
    public void displayAllStudents() {
        System.out.println("\n All Students:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
    }
    
    // Display all faculties
    public void displayAllFaculties() {
        System.out.println("\n All Faculties:");
        for (int i = 0; i < faculties.size(); i++) {
            Faculty f = faculties.get(i);
            System.out.println((i + 1) + ". " + f);
        }
    }
    
    // Display students by faculty
    public void displayStudentsByFaculty(int facultyIndex) {
        if (facultyIndex >= 0 && facultyIndex < faculties.size()) {
            Faculty faculty = faculties.get(facultyIndex);
            System.out.println("\n " + faculty.getAbbreviation());
            System.out.println("Faculty: " + faculty.getName());
            System.out.println("Study Field: " + faculty.getStudyField());
            System.out.println("\nStudents:");
            
            if (faculty.getStudents().isEmpty()) {
                System.out.println("  No students enrolled.");
            } else {
                for (Student student : faculty.getStudents()) {
                    System.out.println("  - " + student);
                }
            }
        }
    }
    
    // Main method - program entry point
    public static void main(String[] args) {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        
        // Create SaveManager and load data
        SaveManager saveManager = new SaveManager();
        saveManager.loadAllData();
        
        // Main menu loop
        boolean running = true;
        while (running) {
            System.out.println("\n University Management System ");
            System.out.println("1. View all students");
            System.out.println("2. View all faculties");
            System.out.println("3. View students by faculty");
            System.out.println("4. Save data");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    saveManager.displayAllStudents();
                    break;
                case 2:
                    saveManager.displayAllFaculties();
                    break;
                case 3:
                    saveManager.displayAllFaculties();
                    System.out.print("\nChoose faculty (1-" + saveManager.getFaculties().size() + "): ");
                    int facultyChoice = scanner.nextInt();
                    scanner.nextLine();
                    saveManager.displayStudentsByFaculty(facultyChoice - 1);
                    break;
                case 4:
                    saveManager.saveAllData();
                    break;
                case 5:
                    System.out.print("Save before exit? (yes/no): ");
                    if (scanner.nextLine().equalsIgnoreCase("yes")) {
                        saveManager.saveAllData();
                    }
                    running = false;
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
        
        scanner.close();
    }
}
