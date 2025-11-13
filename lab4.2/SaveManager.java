import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SaveManager {
    private FileManager fileManager;
    private List<Student> students;
    private List<Faculty> faculties;
    
    // Constructor
    public SaveManager() {
        this.fileManager = new FileManager("students.json");
        this.students = new ArrayList<>();
        this.faculties = new ArrayList<>();
    }
    
    public void loadAllData() {
       
        this.students = StudentExample.loadStudentsFromJSON("students.json");
        
        // Create faculties and assign students
        createFacultiesAndAssignStudents();
        
        System.out.println(" All data loaded successfully!");
        System.out.println("  - " + students.size() + " students");
        System.out.println("  - " + faculties.size() + " faculties");
    }
    
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
            fcim.addStudent(students.get(0)); 
            fcim.addStudent(students.get(1));  
            fimit.addStudent(students.get(2)); 
            fta.addStudent(students.get(3));   
            fua.addStudent(students.get(4));   
        }
        
        faculties.add(fcim);
        faculties.add(fimit);
        faculties.add(fta);
        faculties.add(fua);
    }
  
    public void saveAllData() {
        fileManager.saveStudents(students);
        fileManager.saveFaculties(faculties, "faculties.txt");
        System.out.println("All data saved successfully!");
    }
    
    public List<Student> getStudents() {
        return students;
    }
    
    public List<Faculty> getFaculties() {
        return faculties;
    }
    
    public void addStudent(Student student) {
        students.add(student);
    }
    
    public void addStudentToFaculty(Student student, Faculty faculty) {
        if (!students.contains(student)) {
            students.add(student);
        }
        faculty.addStudent(student);
    }
    
    public void displayAllStudents() {
        System.out.println("\n All Students:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
    }
   
    public void displayAllFaculties() {
        System.out.println("\n All Faculties:");
        for (int i = 0; i < faculties.size(); i++) {
            Faculty f = faculties.get(i);
            System.out.println((i + 1) + ". " + f);
        }
    }
    
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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       
        SaveManager saveManager = new SaveManager();
        saveManager.loadAllData();
    
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
