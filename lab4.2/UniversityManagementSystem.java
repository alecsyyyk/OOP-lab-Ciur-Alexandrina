import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UniversityManagementSystem {
    
    private static List<Faculty> faculties = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // Initialize faculties
        faculties.add(new Faculty("Computer Science", "CS", StudyField.SOFTWARE_ENGINEERING));
        faculties.add(new Faculty("Mechanical Engineering", "ME", StudyField.MECHANICAL_ENGINEERING));
        faculties.add(new Faculty("Food Technology", "FT", StudyField.FOOD_TECHNOLOGY));
        faculties.add(new Faculty("Urbanism Architecture", "UA", StudyField.URBANISM_ARCHITECTURE));
        
        // Load students
        students = loadStudentsFromJSON("students.json");
        System.out.println("Loaded " + students.size() + " students\n");
        
        // Automatically assign all students to their faculties from JSON
        assignStudentsFromJSON();
        
        // Main loop
        while (true) {
            facultyOperationsMenu();
        }
    }
    
    private static void facultyOperationsMenu() {
        System.out.println("\n=== TUM BOARD - Faculty Operations ===");
        System.out.println("1. Assign student to faculty");
        System.out.println("2. Assign ALL students automatically");
        System.out.println("3. Graduate student");
        System.out.println("4. Display enrolled students");
        System.out.println("5. Display graduates");
        System.out.println("6. Check if student belongs to faculty");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        if (choice == 0) {
            System.out.println("Goodbye!");
            scanner.close();
            System.exit(0);
        }
        
        switch (choice) {
            case 1: assignStudentToFaculty(); break;
            case 2: assignAllStudentsAutomatically(); break;
            case 3: graduateStudent(); break;
            case 4: displayEnrolledStudents(); break;
            case 5: displayGraduates(); break;
            case 6: checkStudentBelongsToFaculty(); break;
        }
    }
    
    // Faculty Operation 1: Assign student to faculty
    private static void assignStudentToFaculty() {
        System.out.println("\n--- Assign Student to Faculty ---");
        
        // Show only enrolled students
        List<Student> enrolledStudents = new ArrayList<>();
        for (Student s : students) {
            if (!s.isGraduated()) {
                enrolledStudents.add(s);
            }
        }
        
        if (enrolledStudents.isEmpty()) {
            System.out.println("No enrolled students available!");
            return;
        }
        
        System.out.println("Enrolled students:");
        for (int i = 0; i < enrolledStudents.size(); i++) {
            System.out.println((i + 1) + ". " + enrolledStudents.get(i));
        }
        System.out.print("Select student: ");
        int studentIdx = scanner.nextInt() - 1;
        
        if (studentIdx < 0 || studentIdx >= enrolledStudents.size()) {
            System.out.println("Invalid choice!");
            return;
        }
        
        System.out.println("\nFaculties:");
        for (int i = 0; i < faculties.size(); i++) {
            System.out.println((i + 1) + ". " + faculties.get(i).getName());
        }
        System.out.print("Select faculty: ");
        int facultyIdx = scanner.nextInt() - 1;
        
        if (facultyIdx < 0 || facultyIdx >= faculties.size()) {
            System.out.println("Invalid choice!");
            return;
        }
        
        Student student = enrolledStudents.get(studentIdx);
        Faculty faculty = faculties.get(facultyIdx);
        faculty.addStudent(student);
        System.out.println("✓ " + student.getFirstName() + " " + student.getLastName() + 
                          " assigned to " + faculty.getName());
    }
    
    // Faculty Operation 2: Assign all students automatically
    private static void assignAllStudentsAutomatically() {
        System.out.println("\n--- Assigning All Students ---");
        
        int count = 0;
        for (Student student : students) {
            // Only assign enrolled students who are not already assigned
            if (!student.isGraduated() && student.getFacultyName() == null) {
                // Distribute students evenly across faculties
                Faculty faculty = faculties.get(count % faculties.size());
                faculty.addStudent(student);
                System.out.println(student.getFirstName() + " " + student.getLastName() + 
                                  " -> " + faculty.getName());
                count++;
            }
        }
        if (count == 0) {
            System.out.println("All students are already assigned!");
        } else {
            System.out.println("\nAssigned " + count + " students!");
        }
    }
    
    // Faculty Operation 3: Graduate student
    private static void graduateStudent() {
        System.out.println("\n--- Graduate Student ---");
        
        System.out.println("Select faculty:");
        for (int i = 0; i < faculties.size(); i++) {
            System.out.println((i + 1) + ". " + faculties.get(i).getName());
        }
        System.out.print("Choose: ");
        int facultyIdx = scanner.nextInt() - 1;
        
        if (facultyIdx < 0 || facultyIdx >= faculties.size()) {
            System.out.println("Invalid choice!");
            return;
        }
        
        Faculty faculty = faculties.get(facultyIdx);
        if (faculty.getStudents().isEmpty()) {
            System.out.println("No students in this faculty!");
            return;
        }
        
        System.out.println("\nStudents in " + faculty.getName() + ":");
        for (int i = 0; i < faculty.getStudents().size(); i++) {
            Student s = faculty.getStudents().get(i);
            System.out.println((i + 1) + ". " + s + " - " + (s.isGraduated() ? "GRADUATED" : "ENROLLED"));
        }
        System.out.print("Select student: ");
        int studentIdx = scanner.nextInt() - 1;
        
        if (studentIdx >= 0 && studentIdx < faculty.getStudents().size()) {
            faculty.graduateStudent(faculty.getStudents().get(studentIdx));
        } else {
            System.out.println("Invalid choice!");
        }
    }
    
    // Faculty Operation 4: Display enrolled students
    private static void displayEnrolledStudents() {
        System.out.println("\n--- Currently Enrolled Students ---");
        
        System.out.println("Select faculty:");
        for (int i = 0; i < faculties.size(); i++) {
            System.out.println((i + 1) + ". " + faculties.get(i).getName());
        }
        System.out.print("Choose: ");
        int facultyIdx = scanner.nextInt() - 1;
        
        if (facultyIdx < 0 || facultyIdx >= faculties.size()) {
            System.out.println("Invalid choice!");
            return;
        }
        
        faculties.get(facultyIdx).displayEnrolledStudents();
    }
    
    // Faculty Operation 5: Display graduates
    private static void displayGraduates() {
        System.out.println("\n--- Graduates ---");
        
        System.out.println("Select faculty:");
        for (int i = 0; i < faculties.size(); i++) {
            System.out.println((i + 1) + ". " + faculties.get(i).getName());
        }
        System.out.print("Choose: ");
        int facultyIdx = scanner.nextInt() - 1;
        
        if (facultyIdx < 0 || facultyIdx >= faculties.size()) {
            System.out.println("Invalid choice!");
            return;
        }
        
        faculties.get(facultyIdx).displayGraduates();
    }
    
    // Faculty Operation 6: Check if student belongs to faculty
    private static void checkStudentBelongsToFaculty() {
        System.out.println("\n--- Check Student Membership ---");
        
        System.out.println("Students:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
        System.out.print("Select student: ");
        int studentIdx = scanner.nextInt() - 1;
        
        if (studentIdx < 0 || studentIdx >= students.size()) {
            System.out.println("Invalid choice!");
            return;
        }
        
        System.out.println("\nSelect faculty:");
        for (int i = 0; i < faculties.size(); i++) {
            System.out.println((i + 1) + ". " + faculties.get(i).getName());
        }
        System.out.print("Choose: ");
        int facultyIdx = scanner.nextInt() - 1;
        
        if (facultyIdx < 0 || facultyIdx >= faculties.size()) {
            System.out.println("Invalid choice!");
            return;
        }
        
        Student student = students.get(studentIdx);
        Faculty faculty = faculties.get(facultyIdx);
        boolean belongs = faculty.belongsToFaculty(student);
        
        System.out.println("\n" + student.getFirstName() + " " + student.getLastName() +
                         (belongs ? " BELONGS" : " DOES NOT BELONG") + " to " + faculty.getName());
    }
    
    // Load students from JSON file
    private static List<Student> loadStudentsFromJSON(String filename) {
        List<Student> studentList = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String firstName = null, lastName = null, email = null;
            String enrollmentDate = null, dateOfBirth = null, facultyName = null;
            boolean graduated = false;
            
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                
                if (line.contains("\"firstName\"")) {
                    firstName = extractValue(line);
                } else if (line.contains("\"lastName\"")) {
                    lastName = extractValue(line);
                } else if (line.contains("\"email\"")) {
                    email = extractValue(line);
                } else if (line.contains("\"enrollmentDate\"")) {
                    enrollmentDate = extractValue(line);
                } else if (line.contains("\"dateOfBirth\"")) {
                    dateOfBirth = extractValue(line);
                } else if (line.contains("\"facultyName\"")) {
                    facultyName = extractValue(line);
                } else if (line.contains("\"graduated\"")) {
                    graduated = extractValue(line).equals("true");
                }
                
                if (line.startsWith("}") && firstName != null) {
                    Student student = new Student(firstName, lastName, email, enrollmentDate, dateOfBirth);
                    student.setGraduated(graduated);
                    student.setFacultyName(facultyName);
                    studentList.add(student);
                    
                    // Reset
                    firstName = lastName = email = enrollmentDate = dateOfBirth = facultyName = null;
                    graduated = false;
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        return studentList;
    }
    
    // Assign students from JSON to their faculties automatically
    private static void assignStudentsFromJSON() {
        for (Student student : students) {
            if (student.getFacultyName() != null) {
                // Find the faculty by name
                for (Faculty faculty : faculties) {
                    if (faculty.getName().equals(student.getFacultyName())) {
                        faculty.addStudent(student);
                        break;
                    }
                }
            }
        }
    }
    
    private static String extractValue(String line) {
        int start = line.indexOf(":") + 1;
        String value = line.substring(start).trim();
        return value.replace("\"", "").replace(",", "");
    }
}
