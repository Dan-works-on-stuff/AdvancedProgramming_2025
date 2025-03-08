import java.time.LocalDate;
import java.util.Objects;

public class Student implements Person {
    private String regNr;
    private String name;
    final LocalDate dob;
    private Project preferredProject1;
    private Project preferredProject2;

    public Student(String name, LocalDate dob) {
        this.name = name;
        this.dob=dob;
    }

//    public int getregNr() {
//        return regNr;
//    }

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

    public void setName(String name) {
        this.name = name;
    }

//    public Project getPreferredProject1() {
//        return preferredProject1;
//    }

//    public void setPreferredProject1(Project preferredProject1) {
//        this.preferredProject1 = preferredProject1;
//    }

//    public Project getPreferredProject2() {
//        return preferredProject2;
//    }

//    public void setPreferredProject2(Project preferredProject2) {
//        this.preferredProject2 = preferredProject2;
//    }

    @Override
    public String toString() {
        return "Student{" +
                "regNr=" + regNr +
                ", name='" + name + '\''
//                ", preferredProject1=" + (preferredProject1 != null ? preferredProject1.getId() : "null") +
//                ", preferredProject2=" + (preferredProject2 != null ? preferredProject2.getId() : "null") +
//                '}'
                ;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof Student)) return false;
        Student someStudent = (Student) obj;
        return this.regNr.equals(someStudent.regNr); // Compare regNr
    }

    @Override
    public int hashCode() {
        return Objects.hash(regNr);
    }

}