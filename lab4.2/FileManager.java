import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private String filePath;

    public FileManager(String filePath) {
        this.filePath = filePath;  
    }
    
    public void saveStudents(List<Student> students) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            
            // Write header line (column names)
            writer.write("firstName,lastName,email,enrollmentDate,dateOfBirth");
            writer.newLine();  
 
            for (Student student : students) {

                String line = student.getFirstName() + "," +
                             student.getLastName() + "," +
                             student.getEmail() + "," +
                             student.getEnrollmentDate() + "," +
                             student.getDateOfBirth();
                
                writer.write(line);
                writer.newLine();  
            }
            
            writer.close();
            
            System.out.println("Saved " + students.size() + " students to " + filePath);
            
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }
    
    public List<Student> loadStudents() {
       
        List<Student> students = new ArrayList<>();
        
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File not found. Starting with empty list.");
            return students;
        }
        
        try {
            
            FileReader fileReader = new FileReader(filePath);
            
            BufferedReader reader = new BufferedReader(fileReader);
            
            reader.readLine(); 

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
            System.out.println("Error loading students: " + e.getMessage());
        }
        
        return students;
    }
    
    public boolean fileExists() {
        File file = new File(filePath);
        return file.exists();  
    }
    
    public boolean deleteFile() {
        File file = new File(filePath);
        if (file.exists()) {
            boolean deleted = file.delete();  
            if (deleted) {
                System.out.println("Deleted file: " + filePath);
            }
            return deleted;  
        }
        System.out.println("File does not exist: " + filePath);
        return false;  
    }
    

    public void saveFaculties(List<Faculty> faculties, String facultyFilePath) {
        try(BufferedWriter writer = new  BufferedWriter(new FileWriter(facultyFilePath))){
    
            writer.write("name,abbreviation,studyField,studentEmails");
            writer.newLine();
            
            for (Faculty faculty : faculties) {
                
                String studentEmails = "";
                for (int i = 0; i < faculty.getStudents().size(); i++) {
                    studentEmails += faculty.getStudents().get(i).getEmail();
                    if (i < faculty.getStudents().size() - 1) {
                        studentEmails += ";";  
                    }
                }
                
              
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
            
            reader.readLine();
            
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 4);  
                
                if (parts.length >= 3) {  
                    String name = parts[0];
                    String abbreviation = parts[1];
                    String studyFieldStr = parts[2];
                    
                    // Convert string to StudyField enum
                    StudyField studyField = StudyField.valueOf(studyFieldStr);
                    
                    Faculty faculty = new Faculty(name, abbreviation, studyField);
                    
                    // If there are student emails, add them
                    if (parts.length == 4 && !parts[3].isEmpty()) {
                        String[] emails = parts[3].split(";");
                
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