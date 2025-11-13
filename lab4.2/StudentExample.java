import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentExample {
    public static void main(String[] args) {

        List<Student> students = loadStudentsFromJSON("students.json");

        System.out.println("All Students");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i));
        }
        
        System.out.println();
        
        if(students.size()>0) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter student number ( 1-" + students.size() + ") to see details: ");

            int choice = scanner.nextInt();

            if(choice >= 1 && choice <= students.size()){
                 System.out.println(); 
                 System.out.println("Details of Student " + choice + " ===");
                 Student selectedStudent = students.get(choice - 1); // -1 because list starts at 0
                 System.out.println("First name: " + selectedStudent.getFirstName());
                 System.out.println("Last name: " + selectedStudent.getLastName());
                 System.out.println("Email: " + selectedStudent.getEmail());
                 System.out.println("Enrolled: " + selectedStudent.getEnrollmentDate());
                 System.out.println("Born: " + selectedStudent.getDateOfBirth());

                 System.out.println();

                 System.out.println("Change email? (yes/no): ");
                 scanner.nextLine();
                 String answer = scanner.nextLine();

                 if (answer.equalsIgnoreCase("yes")) {
                     System.out.print("Enter new email: ");
                     String newEmail = scanner.nextLine();
                     selectedStudent.setEmail(newEmail);
                     System.out.println("Updated student: " + selectedStudent);
        }
    } else {
        System.out.println("Invalid choice!");
    }
    
    scanner.close();
    }

}
        
    public static List<Student> loadStudentsFromJSON(String filename) {
        List<Student> students = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String firstName = null, lastName = null, email = null;
            String enrollmentDate = null, dateOfBirth = null;
            
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
                    
                    if (firstName != null && lastName != null && email != null 
                        && enrollmentDate != null && dateOfBirth != null) {
                        students.add(new Student(firstName, lastName, email, 
                                                enrollmentDate, dateOfBirth));
                        // Reset for next student
                        firstName = lastName = email = enrollmentDate = dateOfBirth = null;
                    }
                }
            }
            
            System.out.println("Loaded " + students.size() + " students from " + filename);
            
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
