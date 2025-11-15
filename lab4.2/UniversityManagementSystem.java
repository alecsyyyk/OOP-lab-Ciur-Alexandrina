import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UniversityManagementSystem {
    public static void main(String[] args) {
        // Create faculties
        Faculty computerScience = new Faculty("Computer Science", "CS", StudyField.SOFTWARE_ENGINEERING);
        Faculty engineering = new Faculty("Mechanical Engineering", "ME", StudyField.MECHANICAL_ENGINEERING);
        
        // Load students from JSON file
        List<Student> students = loadStudentsFromJSON("students.json");
        
        if (students.isEmpty()) {
            System.out.println("No students loaded. Please check students.json file.");
            return;
        }
        
        System.out.println(" Loaded " + students.size() + " students from students.json\n");
        
        Scanner scanner = new Scanner(System.in);
        
        // Menu system
        while (true) {
            System.out.println("~ UNIVERSITY MANAGEMENT SYSTEM MENU ~");
            System.out.println("1. Assign a student to a faculty");
            System.out.println("2. Graduate a student from a faculty");
            System.out.println("3. Display currently enrolled students");
            System.out.println("4. Display graduates");
            System.out.println("5. Check if student belongs to faculty");
            System.out.println("6. View student details (see if graduated)");
            System.out.println("7. Display all faculties");
            System.out.println("8. Exit");
            System.out.print("\nEnter your choice (1-8): ");
            
            int choice = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (choice) {
                case 1:
                    // Task 1: Create and assign a student to a faculty
                    System.out.println("\n--- Assign Student to Faculty ---");
                    System.out.println("Available students:");
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println((i + 1) + ". " + students.get(i));
                    }
                    System.out.print("Select student (1-" + students.size() + "): ");
                    int studentChoice = scanner.nextInt();
                    
                    System.out.println("\nAvailable faculties:");
                    System.out.println("1. " + computerScience.getName());
                    System.out.println("2. " + engineering.getName());
                    System.out.print("Select faculty (1-2): ");
                    int facultyChoice = scanner.nextInt();
                    
                    if (studentChoice < 1 || studentChoice > students.size()) {
                        System.out.println("Invalid student choice!");
                        break;
                    }
                    
                    Student selectedStudent = students.get(studentChoice - 1);
                    
                    Faculty selectedFaculty = facultyChoice == 1 ? computerScience : engineering;
                    selectedFaculty.addStudent(selectedStudent);
                    System.out.println( selectedStudent.getFirstName() + " " + selectedStudent.getLastName() 
                                     + " assigned to " + selectedFaculty.getName());
                    break;
                    
                case 2:
                    // Task 2: Graduate a student from a faculty
                    System.out.println("\n--- Graduate Student ---");
                    System.out.println("Select faculty:");
                    System.out.println("1. " + computerScience.getName());
                    System.out.println("2. " + engineering.getName());
                    System.out.print("Choice (1-2): ");
                    int gradFacultyChoice = scanner.nextInt();
                    
                    Faculty gradFaculty = gradFacultyChoice == 1 ? computerScience : engineering;
                    
                    if (gradFaculty.getStudents().isEmpty()) {
                        System.out.println("No students in this faculty!");
                        break;
                    }
                    
                    System.out.println("\nStudents in " + gradFaculty.getName() + ":");
                    for (int i = 0; i < gradFaculty.getStudents().size(); i++) {
                        Student s = gradFaculty.getStudents().get(i);
                        System.out.println((i + 1) + ". " + s + " - " + 
                                         (s.isGraduated() ? "GRADUATED" : "ENROLLED"));
                    }
                    System.out.print("Select student to graduate: ");
                    int gradStudentIdx = scanner.nextInt() - 1;
                    
                    if (gradStudentIdx >= 0 && gradStudentIdx < gradFaculty.getStudents().size()) {
                        gradFaculty.graduateStudent(gradFaculty.getStudents().get(gradStudentIdx));
                    } else {
                        System.out.println("Invalid choice!");
                    }
                    break;
                    
                case 3:
                    // Task 3: Display current enrolled students
                    System.out.println("\n--- Currently Enrolled Students ---");
                    System.out.println("Select faculty:");
                    System.out.println("1. " + computerScience.getName());
                    System.out.println("2. " + engineering.getName());
                    System.out.print("Choice (1-2): ");
                    int enrolledChoice = scanner.nextInt();
                    
                    Faculty enrolledFaculty = enrolledChoice == 1 ? computerScience : engineering;
                    enrolledFaculty.displayEnrolledStudents();
                    break;
                    
                case 4:
                    // Task 4: Display graduates
                    System.out.println("\n--- Graduates ---");
                    System.out.println("Select faculty:");
                    System.out.println("1. " + computerScience.getName());
                    System.out.println("2. " + engineering.getName());
                    System.out.print("Choice (1-2): ");
                    int gradChoice = scanner.nextInt();
                    
                    Faculty gradsFaculty = gradChoice == 1 ? computerScience : engineering;
                    gradsFaculty.displayGraduates();
                    break;
                    
                case 5:
                    // Task 5: Check if student belongs to faculty
                    System.out.println("\n--- Check Student Membership ---");
                    System.out.println("Available students:");
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println((i + 1) + ". " + students.get(i));
                    }
                    System.out.print("Select student (1-" + students.size() + "): ");
                    int checkStudentChoice = scanner.nextInt();
                    
                    System.out.println("Select faculty:");
                    System.out.println("1. " + computerScience.getName());
                    System.out.println("2. " + engineering.getName());
                    System.out.print("Choice (1-2): ");
                    int checkFacultyChoice = scanner.nextInt();
                    
                    if (checkStudentChoice < 1 || checkStudentChoice > students.size()) {
                        System.out.println("Invalid choice!");
                        break;
                    }
                    
                    Student checkStudent = students.get(checkStudentChoice - 1);
                    
                    Faculty checkFaculty = checkFacultyChoice == 1 ? computerScience : engineering;
                    boolean belongs = checkFaculty.belongsToFaculty(checkStudent);
                    
                    System.out.println("\n" + checkStudent.getFirstName() + " " + checkStudent.getLastName() 
                                     + (belongs ? " DOES" : " DOES NOT") + " belong to " 
                                     + checkFaculty.getName());
                    break;
                    
                case 6:
                    // View student details (see if graduated)
                    System.out.println("\n--- View Student Details ---");
                    System.out.println("Available students:");
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println((i + 1) + ". " + students.get(i));
                    }
                    System.out.print("Select student (1-" + students.size() + "): ");
                    int viewStudentChoice = scanner.nextInt();
                    
                    if (viewStudentChoice < 1 || viewStudentChoice > students.size()) {
                        System.out.println("Invalid choice!");
                        break;
                    }
                    
                    Student viewStudent = students.get(viewStudentChoice - 1);
                    System.out.println("\n=== Student Details ===");
                    System.out.println("Name: " + viewStudent.getFirstName() + " " + viewStudent.getLastName());
                    System.out.println("Email: " + viewStudent.getEmail());
                    System.out.println("Enrollment Date: " + viewStudent.getEnrollmentDate());
                    System.out.println("Date of Birth: " + viewStudent.getDateOfBirth());
                    System.out.println("Status: " + (viewStudent.isGraduated() ? "GRADUATED ✓" : "ENROLLED"));
                    break;
                    
                case 7:
                    // Display all faculties
                    System.out.println("\n--- All Faculties ---");
                    System.out.println(computerScience);
                    System.out.println(engineering);
                    break;
                    
                case 8:
                    System.out.println("\nExiting... Goodbye!");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Invalid choice! Please select 1-8.");
            }
        }
    }
    
    // Load students from JSON file
    public static List<Student> loadStudentsFromJSON(String filename) {
        List<Student> students = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String firstName = null, lastName = null, email = null;
            String enrollmentDate = null, dateOfBirth = null;
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
                } else if (line.contains("\"graduated\"")) {
                    String gradValue = extractValue(line);
                    graduated = gradValue.equals("true");
                }
                
                // Check if we have a complete student (when we hit closing brace)
                if (line.startsWith("}") && firstName != null && lastName != null && email != null 
                    && enrollmentDate != null && dateOfBirth != null) {
                    Student student = new Student(firstName, lastName, email, 
                                            enrollmentDate, dateOfBirth);
                    student.setGraduated(graduated);
                    students.add(student);
                    System.out.println("Loaded: " + student.getFirstName() + " " + student.getLastName() 
                                     + (graduated ? " (GRADUATED)" : " (ENROLLED)"));
                    // Reset for next student
                    firstName = lastName = email = enrollmentDate = dateOfBirth = null;
                    graduated = false;
                }
            }
            
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        
        return students;
    }
    
    private static String extractValue(String line) {
        int start = line.indexOf(":") + 1;
        String value = line.substring(start).trim();
        // Remove quotes and comma
        value = value.replace("\"", "").replace(",", "");
        return value;
    }
}
