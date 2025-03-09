import java.util.Arrays;

/**
 * Represents a solution to a problem using a greedy algorithm to assign projects to students.
 * Projects are assigned in a round-robin fashion, cycling through the available projects repeatedly.
 * <p>
 * <strong>Note:</strong> The current implementation may include null values in assignments if non-student persons
 * (e.g., teachers) exist in the problem, as it does not properly filter students during processing.
 */
public class Solution {
    private int SolutionID;
    private String SolutionDescription;
    private Student[] assignedStudents;
    private Project[] assignedProjects;

    /**
     * Constructs a solution for a problem using the greedy assignment algorithm.
     *
     * @param SolutionID           the unique identifier for this solution
     * @param SolutionDescription  a textual description of the solution approach
     * @param problem              the problem to solve (contains students, teachers, and projects)
     * @see #runGreedy(Problem)    Initializes project assignments immediately
     */
    public Solution(int SolutionID, String SolutionDescription, Problem problem) {
        this.SolutionID = SolutionID;
        this.SolutionDescription = SolutionDescription;
        this.assignedStudents = new Student[0];
        this.assignedProjects = new Project[0];
        runGreedy(problem);
    }

    /**
     * Gets the solution's unique identifier.
     * @return the solution ID
     */
    public int getSolutionID() {
        return SolutionID;
    }

    /**
     * Gets the description of the solution strategy.
     * @return the solution description text
     */
    public String getSolutionDescription() {
        return SolutionDescription;
    }

    /**
     * Executes the greedy assignment algorithm:
     * <ol>
     *   <li>Gets all persons (students + teachers) from the problem</li>
     *   <li>Extracts students (with potential null entries for teachers)</li>
     *   <li>Cycles through projects repeatedly to assign to students</li>
     * </ol>
     * <strong>Implementation Limitation:</strong> May assign null students if teachers exist in the problem.
     *
     * @param problem the problem containing the data to process
     */
    private void runGreedy(Problem problem) {
        Person[] allPersons = problem.getAllPersons();
        Student[] allStudents = extractStudents(allPersons);
        Project[] allProjects = problem.getProjects();

        if(allStudents.length == 0 || allProjects.length == 0) { return; }

        Student[] tempStudents = new Student[allStudents.length];
        Project[] tempProjects = new Project[allStudents.length];
        int counter = 0;
        int projectCounter = 0;

        for(Student s : allStudents) {
            tempStudents[counter] = s;
            tempProjects[counter] = allProjects[projectCounter];
            counter++;
            projectCounter = (projectCounter + 1) % allProjects.length;
        }

        assignedStudents = Arrays.copyOf(tempStudents, counter);
        assignedProjects = Arrays.copyOf(tempProjects, counter);
    }

    /**
     * Filters students from a mixed array of persons.
     * <strong>Note:</strong> Returns an array where non-student persons become null entries.
     *
     * @param allPersons array containing both students and teachers
     * @return array with student instances and nulls for non-students
     */
    private Student[] extractStudents(Person[] allPersons) {
        Student[] students = new Student[allPersons.length];
        for (int i = 0; i < allPersons.length; i++) {
            if (allPersons[i] instanceof Student) {
                students[i] = (Student) allPersons[i];
            }
        }
        return students;
    }

    /**
     * Gets the students assigned to projects.
     * <strong>Warning:</strong> May contain null values if the problem included teachers.
     *
     * @return array of students (some entries may be null)
     */
    public Student[] getAssignedStudents() {
        return assignedStudents;
    }

    /**
     * Gets the projects assigned to students.
     * The order matches the student array from {@link #getAssignedStudents()}.
     *
     * @return array of assigned projects
     */
    public Project[] getAssignedProjects() {
        return assignedProjects;
    }
}