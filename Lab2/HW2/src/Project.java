import java.util.*;

public class Project {
    private int id;
    private final ProjectType type;

    public Project(ProjectType type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProjectType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", type=" + type +
                '}';
    }
    private Set<Student> students = new HashSet<>();
    private Set<Teacher> teachers = new HashSet<>();

    public Person[] getAllPersons() {
        List<Person> allPersons = new ArrayList<>();
        allPersons.addAll(students); // Students are Persons
        allPersons.addAll(teachers); // Teachers are Persons
        return allPersons.toArray(new Person[0]);
    }
}