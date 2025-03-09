import java.util.Arrays;

/**
 * Represents an academic problem that can be associated with students, teachers, and projects.
 * Maintains these associations while preventing duplicate entries using object equality checks.
 */
public class Problem {
    private int problemID;
    private String problemName;
    private String problemDescription;

    /**
     * Constructs a new Problem with the specified identifier, name, and description.
     *
     * @param problemID          the unique identifier for the problem
     * @param problemName        the title/name of the problem
     * @param problemDescription a detailed explanation of the problem
     */
    public Problem(int problemID, String problemName, String problemDescription) {
        this.problemID = problemID;
        this.problemName = problemName;
        this.problemDescription = problemDescription;
    }

    /**
     * Gets the problem's unique identifier.
     * @return the problem ID
     */
    public int getProblemID() {
        return problemID;
    }

    /**
     * Gets the problem's name/title.
     * @return the problem name
     */
    public String getProblemName() {
        return problemName;
    }

    /**
     * Gets the problem's detailed description.
     * @return the problem description
     */
    public String getProblemDescription() {
        return problemDescription;
    }

    private Student[] students = new Student[0];
    private Teacher[] teachers = new Teacher[0];
    private Project[] projects = new Project[0];

    /**
     * Checks if an object exists in the given array using {@link Object#equals(Object)}.
     * @param array the array to search
     * @param obj   the object to find
     * @return true if the object exists in the array, false otherwise
     */
    private boolean contains(Object[] array, Object obj) {
        for (Object element : array) {
            if (element.equals(obj)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a student to the problem's association list if not already present.
     * Uses {@link Student#equals(Object)} for duplicate checks.
     * @param student the student to add
     */
    public void addStudent(Student student) {
        if (!contains(students, student)) {
            students = Arrays.copyOf(students, students.length + 1);
            students[students.length - 1] = student;
        }
    }

    /**
     * Adds a teacher to the problem's association list if not already present.
     * Uses {@link Teacher#equals(Object)} for duplicate checks.
     * @param teacher the teacher to add
     */
    public void addTeacher(Teacher teacher) {
        if (!contains(teachers, teacher)) {
            teachers = Arrays.copyOf(teachers, teachers.length + 1);
            teachers[teachers.length - 1] = teacher;
        }
    }

    /**
     * Adds a project to the problem's association list if not already present.
     * Uses {@link Project#equals(Object)} for duplicate checks.
     * @param project the project to add
     */
    public void addProject(Project project) {
        if (!contains(projects, project)) {
            projects = Arrays.copyOf(projects, projects.length + 1);
            projects[projects.length - 1] = project;
        }
    }

    /**
     * Returns all persons (students and teachers) associated with the problem.
     * The returned array is a new array containing references to the actual objects.
     *
     * @return a combined array of students and teachers in the order:
     *         students first, followed by teachers
     */
    public Person[] getAllPersons() {
        Person[] allPersons = new Person[students.length + teachers.length];
        System.arraycopy(students, 0, allPersons, 0, students.length);
        System.arraycopy(teachers, 0, allPersons, students.length, teachers.length);
        return allPersons;
    }

    /**
     * Gets all projects associated with the problem.
     * <strong>Note:</strong> The returned array is the internal projects array.
     * Modifications to the array elements (e.g., via {@link Project#setId(int)})
     * will affect the problem's state.
     *
     * @return the array of projects associated with this problem
     */
    public Project[] getProjects() {
        return projects;
    }
}