import java.time.LocalDate;
import java.util.Objects;

public class Teacher implements Person {
    String name;
    LocalDate dob;
    String[] Projects;
    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalDate getDOB() {
        return dob;
    }

    public String[] getProjects() {
        return Projects;
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof Teacher)) return false;
        Teacher someTeacher = (Teacher) obj;
        return this.name.equals(someTeacher.name) && this.dob.equals(someTeacher.dob); // Compare regNr
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dob);
    }
}
