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
