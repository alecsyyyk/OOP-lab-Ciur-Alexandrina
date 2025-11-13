public class Student {
   
    private String firstName;
    private String lastName;
    private String email;
    private String enrollmentDate;  
    private String dateOfBirth;     
    
    public Student(String firstName, String lastName, String email, 
                   String enrollmentDate, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters
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

    // change the information
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

    public String toString() {
        return firstName + " " + lastName + " (" + email + ")";
    }
}
