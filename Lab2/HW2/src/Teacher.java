import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a teacher in an academic system, implementing the {@link Person} interface.
 * Teachers have a name, date of birth, and may be associated with projects (unused in current implementation).
 * <p>
 * <strong>Note:</strong> The projects functionality appears incomplete as the constructor does not initialize
 * the projects field, and there are no methods to modify it in the current implementation.
 */
public class Teacher implements Person {
    private String name;
    private LocalDate dob;
    String[] Projects; // Currently unused and uninitialized

    /**
     * Constructs a new Teacher with the specified name, date of birth, and projects.
     * <strong>Implementation Note:</strong> The projects parameter is currently unused.
     *
     * @param name    the full name of the teacher
     * @param dob     the date of birth of the teacher
     * @param Projects (unused in current implementation) array of projects
     */
    Teacher(String name, LocalDate dob, Project[] Projects) {
        this.name = name;
        this.dob = dob;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalDate getDOB() {
        return dob;
    }

    /**
     * Gets the projects associated with the teacher.
     * <strong>Note:</strong> This functionality is not properly implemented -
     * returns an empty/uninitialized array in current implementation.
     *
     * @return an array of project strings (currently unused)
     */
    public String[] getProjects() {
        return Projects;
    }

    /**
     * Compares teachers for equality based on their name and date of birth.
     *
     * @param obj the object to compare with
     * @return true if the objects are equal (same name and dob), false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Teacher)) return false;
        Teacher someTeacher = (Teacher) obj;
        return this.name.equals(someTeacher.name) && this.dob.equals(someTeacher.dob);
    }

    /**
     * Generates a hash code based on the teacher's name and date of birth.
     *
     * @return a hash code value for this teacher
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, dob);
    }

    /**
     * Returns a string representation of the teacher in the format:
     * {@code Teacher{name=NAME, date of birth='DOB'}}
     *
     * @return a string representation of the teacher
     */
    @Override
    public String toString() {
        return "Teacher{" +
                "name=" + getName() +
                ", date of birth='" + getDOB() + "'}";
    }
}