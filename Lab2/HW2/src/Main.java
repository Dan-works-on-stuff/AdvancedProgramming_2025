import java.time.LocalDate;

enum ProjectType {
    THEORETICAL,
    PRACTICAL
}


public class Main {

    public static void main(String[] args) {
        Project theoreticalProject = new Project(ProjectType.THEORETICAL);
        theoreticalProject.setId(101);
        Project practicalProject = new Project(ProjectType.PRACTICAL);
        practicalProject.setId(102);

        Student student1 = new Student("Andrei", LocalDate.of(1990, 1, 1));
        student1.setregNr("1");
//        student1.setName("Andrei");
//        student1.setPreferredProject1(theoreticalProject);
//        student1.setPreferredProject2(practicalProject);

        Student student2 = new Student("Gion", LocalDate.of(1995, 1, 1));
        student2.setregNr("2");
//        student2.setName("Gion");
//        student2.setPreferredProject1(practicalProject);
//        student2.setPreferredProject2(theoreticalProject);

        System.out.println(student1);
        System.out.println(student2);
        System.out.println(theoreticalProject);
        System.out.println(practicalProject);
    }
}