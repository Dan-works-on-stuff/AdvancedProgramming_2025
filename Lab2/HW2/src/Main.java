import java.time.LocalDate;
import java.util.Arrays;

/**
 * The Main class serves as the entry point for the application.
 * It demonstrates the creation and interaction of entities such as Projects, Students, Teachers, Problems, and Solutions.
 */
public class Main {

    /**
     * The main method initializes sample data and demonstrates the functionality of the Problem and Solution classes.
     * It creates instances of projects, students, teachers, and a problem. It then assigns these entities to a solution
     * and prints the assignments.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Project instances with theoretical and practical types
        Project[] listaProiecte1 = {new Project(11), new Project(54)};
        Project[] listaProiecte2 = {new Project(16)};
        Project[] listaProiecte3 = {new Project(12)};


        // Create a problem and add projects to it
        Problem problem1 = new Problem(1, "Hanoi", "E o problema de algoritmica");
        problem1.addProject(new Project(19));
        problem1.addProject(new Project(34));
        problem1.addMultipleProjects(listaProiecte1);
        problem1.addMultipleProjects(listaProiecte2);
        problem1.addMultipleProjects(listaProiecte3);

        // Create students
        Student student1 = new Student("1AB3", "Andrei", LocalDate.of(1990, 1, 1),listaProiecte1[0], listaProiecte1[1]);
        Student student2 = new Student("2AC4", "Gion", LocalDate.of(1995, 1, 1), listaProiecte2[0], listaProiecte3[0]);

        // Create teachers with their respective projects
        Teacher teacher1 = new Teacher("Hamster", LocalDate.of(1964, 4, 12), listaProiecte1);
        Teacher teacher2 = new Teacher("Constantinescu", LocalDate.of(2000, 2, 22), listaProiecte2);

        // Add teachers and students to the problem
        problem1.addTeacher(teacher1);
        problem1.addStudent(student1);
        problem1.addStudent(student2);

        // Print all persons associated with the problem
        System.out.println(Arrays.toString(problem1.getAllPersons()));

        // Create a solution for the problem and print assignments
        Solution solution = new Solution(1, "Greedy bro", problem1);
//        System.out.println(solution.getSolutionID());

        // Retrieve and print student-project assignments
        Student[] students = solution.getAssignedStudents();
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i].getName() + " --> " + students[i].getAssignedProject());
        }
    }
}