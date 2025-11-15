import java.util.ArrayList;
import java.util.List;

public class Faculty {
    private String name;
    private String abbreviation;
    private List<Student> students;
    private StudyField studyField;

    // Constructor
    public Faculty(String name, String abbreviation, StudyField studyField) {
        this.name = name;
        this.abbreviation = abbreviation;
        this.studyField = studyField;
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    // Task 2: Graduate a student from faculty
    public void graduateStudent(Student student) {
        if (students.contains(student)) {
            student.setGraduated(true);
            System.out.println(student.getFirstName() + " " + student.getLastName() + " has graduated from " + name);
        } else {
            System.out.println("Student not found in this faculty.");
        }
    }

    public String getName() {
        return name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public List<Student> getStudents() {
        return students;
    }

    public StudyField getStudyField() {
        return studyField;
    }

    // Task 3: Display current enrolled students (ignore graduates)
    public void displayEnrolledStudents() {
        System.out.println("\nCurrently Enrolled Students in " + name + ":");
        System.out.println("=" .repeat(50));
        boolean found = false;
        for (Student student : students) {
            if (!student.isGraduated()) {
                System.out.println("- " + student);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No currently enrolled students.");
        }
    }

    // Task 4: Display graduates (ignore currently enrolled students)
    public void displayGraduates() {
        System.out.println("\nGraduates from " + name + ":");
        System.out.println("=" .repeat(50));
        boolean found = false;
        for (Student student : students) {
            if (student.isGraduated()) {
                System.out.println("- " + student);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No graduates yet.");
        }
    }

    // Task 5: Check if a student belongs to this faculty
    public boolean belongsToFaculty(Student student) {
        return students.contains(student);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setStudyField(StudyField studyField) {
        this.studyField = studyField;
    }

    public String toString() {
        return name + " (" + abbreviation + ") - " + studyField + 
               " - " + students.size() + " students";
    }
}
