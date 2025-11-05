import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    // Field to store the file path where data will be saved
    private String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;  
    }
    
    public void saveStudents(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            
            // Write header line (column names)
            writer.write("firstName,lastName,email,enrollmentDate,dateOfBirth");
            writer.newLine();  // Move to next line
            
            // Loop through each student
            for (Student student : students) {

                String line = student.getFirstName() + "," +
                             student.getLastName() + "," +
                             student.getEmail() + "," +
                             student.getEnrollmentDate() + "," +
                             student.getDateOfBirth();
                
                // Write the line to file
                writer.write(line);
                writer.newLine();  
            }
            
            writer.close();
            
            System.out.println("Saved " + students.size() + " students to " + filePath);
            
        } catch (IOException e) {
            // If something goes wrong 
            System.out.println("Error saving students: " + e.getMessage());
        }
    }
    
    public List<Student> loadStudents() {
       
        List<Student> students = new ArrayList<>();
        
        File file = new File(filePath);
        if (!file.exists()) {
            // File doesn't exist yet, return empty list
            System.out.println("File not found. Starting with empty list.");
            return students;
        }
        
        try {
            
            FileReader fileReader = new FileReader(filePath);
            
            BufferedReader reader = new BufferedReader(fileReader);
            
            String headerLine = reader.readLine();
            
            // Read the file line by line
            String line;
            while ((line = reader.readLine()) != null) {  
                String[] parts = line.split(",");
                
                if (parts.length == 5) {
                    
                    String firstName = parts[0];       
                    String lastName = parts[1];        
                    String email = parts[2];           
                    String enrollmentDate = parts[3];  
                    String dateOfBirth = parts[4];     
                    
                    Student student = new Student(firstName, lastName, email, 
                                                 enrollmentDate, dateOfBirth);
                    
                    students.add(student);
                }
            }
            
            reader.close();
            
            System.out.println("Loaded " + students.size() + " students from " + filePath);
            
        } catch (IOException e) {
            // IO error (can't read file, corrupted, etc.)
            System.out.println("Error loading students: " + e.getMessage());
        }
        
        return students;
    }
    
    // Check if the save file exists
    public boolean fileExists() {
        File file = new File(filePath);
        return file.exists();  // Returns true if file exists, false otherwise
    }
    
    // Delete the save file (useful for testing or reset)
    public boolean deleteFile() {
        File file = new File(filePath);
        if (file.exists()) {
            boolean deleted = file.delete();  // Try to delete
            if (deleted) {
                System.out.println("Deleted file: " + filePath);
            }
            return deleted;  
        }
        System.out.println("File does not exist: " + filePath);
        return false;  
    }
    
    // ========== FACULTY METHODS ==========
    
    // Save a list of faculties to a text file (CSV format)
    public void saveFaculties(List<Faculty> faculties, String facultyFilePath) {
        try {
            FileWriter fileWriter = new FileWriter(facultyFilePath);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            
            // Write header line
            writer.write("name,abbreviation,studyField,studentEmails");
            writer.newLine();
            
            // Loop through each faculty
            for (Faculty faculty : faculties) {
                // Get all student emails separated by semicolons
                String studentEmails = "";
                for (int i = 0; i < faculty.getStudents().size(); i++) {
                    studentEmails += faculty.getStudents().get(i).getEmail();
                    if (i < faculty.getStudents().size() - 1) {
                        studentEmails += ";";  // Use semicolon to separate emails
                    }
                }
                
                // Create line with faculty data
                String line = faculty.getName() + "," +
                             faculty.getAbbreviation() + "," +
                             faculty.getStudyField() + "," +
                             studentEmails;
                
                writer.write(line);
                writer.newLine();
            }
            
            writer.close();
            System.out.println("Saved " + faculties.size() + " faculties to " + facultyFilePath);
            
        } catch (IOException e) {
            System.out.println("Error saving faculties: " + e.getMessage());
        }
    }
    
    // Load faculties from file and link them to students
    public List<Faculty> loadFaculties(String facultyFilePath, List<Student> allStudents) {
        List<Faculty> faculties = new ArrayList<>();
        
        File file = new File(facultyFilePath);
        if (!file.exists()) {
            System.out.println("Faculty file not found. Starting with empty list.");
            return faculties;
        }
        
        try {
            FileReader fileReader = new FileReader(facultyFilePath);
            BufferedReader reader = new BufferedReader(fileReader);
            
            // Skip header line
            String headerLine = reader.readLine();
            
            // Read each faculty
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 4);  // Split into max 4 parts
                
                if (parts.length >= 3) {  // At least name, abbreviation, studyField
                    String name = parts[0];
                    String abbreviation = parts[1];
                    String studyFieldStr = parts[2];
                    
                    // Convert string to StudyField enum
                    StudyField studyField = StudyField.valueOf(studyFieldStr);
                    
                    // Create faculty
                    Faculty faculty = new Faculty(name, abbreviation, studyField);
                    
                    // If there are student emails, add them
                    if (parts.length == 4 && !parts[3].isEmpty()) {
                        String[] emails = parts[3].split(";");
                        
                        // Find and add each student by email
                        for (String email : emails) {
                            for (Student student : allStudents) {
                                if (student.getEmail().equals(email)) {
                                    faculty.addStudent(student);
                                    break;
                                }
                            }
                        }
                    }
                    
                    faculties.add(faculty);
                }
            }
            
            reader.close();
            System.out.println("Loaded " + faculties.size() + " faculties from " + facultyFilePath);
            
        } catch (IOException e) {
            System.out.println("Error loading faculties: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: Invalid study field in file");
        }
        
        return faculties;
    }
}