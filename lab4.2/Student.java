public class Student {
    // Fields - information about the student
    private String firstName;
    private String lastName;
    private String email;
    private String enrollmentDate;  
    private String dateOfBirth;     
    
    // Constructor - creates a new student
    public Student(String firstName, String lastName, String email, 
                   String enrollmentDate, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters - read the information
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    // Setters - change the information
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    // Print student information nicely
    public String toString() {
        return firstName + " " + lastName + " (" + email + ")";
    }
}
