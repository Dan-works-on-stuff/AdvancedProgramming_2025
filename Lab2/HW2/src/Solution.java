import java.util.HashSet;
import java.util.Set;

/**
 * Represents a solution to a problem using a greedy algorithm to assign projects to students.
 * The algorithm prioritizes assigning uniquely preferred projects first, then assigns remaining projects
 * based on student preferences and availability.
 * <p>
 * This solution ensures that each student is assigned to exactly one project, and each project is assigned
 * to at most one student.
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
     * Executes the greedy assignment algorithm which consists of two passes:
     * <ol>
     *   <li>First pass: Assigns projects that are uniquely preferred by one student.</li>
     *   <li>Second pass: Assigns remaining projects to students based on their preferences,
     *       or any available project if preferred ones are taken.</li>
     * </ol>
     *
     * @param problem the problem containing the students, teachers, and projects to process
     * @throws IllegalStateException if a student cannot be assigned a project due to no available projects
     */
    private void runGreedy(Problem problem) {
        Person[] allPersons = problem.getAllPersons();
        Student[] allStudents = extractStudents(allPersons);
        Project[] allProjects = problem.getProjects();

        if (allStudents.length == 0 || allProjects.length == 0) {
            return;
        }

        // Track project availability using IDs
        Set<Integer> availableProjectIds = new HashSet<>();
        for (Project project : allProjects) {
            availableProjectIds.add(project.getId());
        }

        // Track student assignments
        boolean[] isStudentAssigned = new boolean[allStudents.length];

        // First pass: Assign uniquely preferred projects
        for (Project project : allProjects) {
            int projectId = project.getId();
            if (countProjectPreferences(allStudents, projectId) == 1 && availableProjectIds.contains(projectId)) {
                for (int i = 0; i < allStudents.length; i++) {
                    if (!isStudentAssigned[i]) {
                        Student student = allStudents[i];
                        if (student.getPreferredProject1().getId() == projectId
                                || student.getPreferredProject2().getId() == projectId) {
                            assignProjectToStudent(student, project);
                            isStudentAssigned[i] = true;
                            availableProjectIds.remove(projectId);
                            break;
                        }
                    }
                }
            }
        }

        // Second pass: Assign remaining projects
        for (int i = 0; i < allStudents.length; i++) {
            if (!isStudentAssigned[i]) {
                Student student = allStudents[i];
                Project firstPref = student.getPreferredProject1();
                Project secondPref = student.getPreferredProject2();

                if (availableProjectIds.contains(firstPref.getId())) {
                    assignProjectToStudent(student, firstPref);
                    availableProjectIds.remove(firstPref.getId());
                    isStudentAssigned[i] = true;
                } else if (availableProjectIds.contains(secondPref.getId())) {
                    assignProjectToStudent(student, secondPref);
                    availableProjectIds.remove(secondPref.getId());
                    isStudentAssigned[i] = true;
                } else if (!availableProjectIds.isEmpty()) {
                    int anyProjectId = availableProjectIds.iterator().next();
                    Project anyProject = findProjectById(anyProjectId, allProjects);
                    assignProjectToStudent(student, anyProject);
                    availableProjectIds.remove(anyProjectId);
                    isStudentAssigned[i] = true;
                } else {
                    throw new IllegalStateException("No projects available for student " + i);
                }
            }
        }

        // Store results
        this.assignedStudents = allStudents;
        this.assignedProjects = new Project[allStudents.length];
        for (int i = 0; i < allStudents.length; i++) {
            this.assignedProjects[i] = allStudents[i].getAssignedProject();
        }
    }

    /**
     * Counts the number of students who have a specific project as one of their preferences.
     *
     * @param students the array of students to check
     * @param projectId the ID of the project to count preferences for
     * @return the total number of students who prefer the specified project
     */
    private int countProjectPreferences(Student[] students, int projectId) {
        int count = 0;
        for (Student student : students) {
            if (student.getPreferredProject1().getId() == projectId
                    || student.getPreferredProject2().getId() == projectId) {
                count++;
            }
        }
        return count;
    }

    /**
     * Finds a project by its ID within an array of projects.
     *
     * @param id the ID of the project to find
     * @param projects the array of projects to search
     * @return the project with the matching ID
     * @throws IllegalArgumentException if no project with the given ID exists in the array
     */
    private Project findProjectById(int id, Project[] projects) {
        for (Project project : projects) {
            if (project.getId() == id) {
                return project;
            }
        }
        throw new IllegalArgumentException("Project not found: " + id);
    }

    /**
     * Assigns a project to a student by setting the project's ID as the student's assigned project.
     *
     * @param student the student to assign the project to
     * @param project the project to be assigned
     */
    private void assignProjectToStudent(Student student, Project project) {
        student.setAssignedProject(project.getId());
    }

    /**
     * Extracts students from an array containing both students and teachers.
     *
     * @param allPersons array containing both students and non-student persons (e.g., teachers)
     * @return array of students with no null entries
     */
    private Student[] extractStudents(Person[] allPersons) {
        // First count how many students we have
        int studentCount = 0;
        for (Person person : allPersons) {
            if (person instanceof Student) {
                studentCount++;
            }
        }

        // Create array of exactly the right size
        Student[] students = new Student[studentCount];

        // Fill the array with actual students
        int index = 0;
        for (Person person : allPersons) {
            if (person instanceof Student) {
                students[index] = (Student) person;
                index++;
            }
        }

        return students;
    }

    /**
     * Gets the students who have been assigned to projects.
     *
     * @return an array of students, each assigned to a project
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