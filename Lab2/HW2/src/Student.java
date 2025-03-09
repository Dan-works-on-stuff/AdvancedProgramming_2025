import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a student in an academic system, implementing the {@link Person} interface.
 * Students have a registration number, name, date of birth, and preferences for projects.
 */
public class Student implements Person {
    private String regNr;
    private String name;
    final LocalDate dob;
    private Project preferredProject1;
    private Project preferredProject2;

    /**
     * Constructs a new Student with the specified registration number, name, and date of birth.
     *
     * @param regNr  the unique registration number of the student
     * @param name   the full name of the student
     * @param dob    the date of birth of the student
     */
    public Student(String regNr, String name, LocalDate dob) {
        this.name = name;
        this.dob = dob;
        this.regNr = regNr;
    }

    /**
     * Gets the student's registration number.
     *
     * @return the registration number as a String
     */
    public String getregNr() {
        return regNr;
    }

    /**
     * Sets/updates the student's registration number.
     *
     * @param regNr the new registration number to set
     */
    public void setregNr(String regNr) {
        this.regNr = regNr;
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
     * Sets/updates the student's name.
     *
     * @param name the new name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a string representation of the student in the format:
     * {@code Student{regNr=REG_NR, name='NAME'}}
     *
     * @return a string representation of the student
     */
    @Override
    public String toString() {
        return "Student{" +
                "regNr=" + getregNr() +
                ", name='" + getName() + "'}"
                ;
    }

    /**
     * Compares students for equality based on their registration number.
     *
     * @param obj the object to compare with
     * @return {@code true} if the objects are equal (same registration number),
     *         {@code false} otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student someStudent = (Student) obj;
        return this.regNr.equals(someStudent.regNr);
    }

    /**
     * Generates a hash code based on the student's registration number.
     *
     * @return a hash code value for this student
     */
    @Override
    public int hashCode() {
        return Objects.hash(regNr);
    }
}