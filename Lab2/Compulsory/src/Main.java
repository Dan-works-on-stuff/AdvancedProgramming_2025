enum ProjectType {
    THEORETICAL,
    PRACTICAL
}

public class Main {

    public static class Student {
        private int id;
        private String name;
        private Project preferredProject1;
        private Project preferredProject2;

        public Student() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Project getPreferredProject1() {
            return preferredProject1;
        }

        public void setPreferredProject1(Project preferredProject1) {
            this.preferredProject1 = preferredProject1;
        }

        public Project getPreferredProject2() {
            return preferredProject2;
        }

        public void setPreferredProject2(Project preferredProject2) {
            this.preferredProject2 = preferredProject2;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", preferredProject1=" + (preferredProject1 != null ? preferredProject1.getId() : "null") +
                    ", preferredProject2=" + (preferredProject2 != null ? preferredProject2.getId() : "null") +
                    '}';
        }
    }

    public static class Project {
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
    }

    public static void main(String[] args) {
        Project theoreticalProject = new Project(ProjectType.THEORETICAL);
        theoreticalProject.setId(101);
        Project practicalProject = new Project(ProjectType.PRACTICAL);
        practicalProject.setId(102);

        Student student1 = new Student();
        student1.setId(1);
        student1.setName("Andrei");
        student1.setPreferredProject1(theoreticalProject);
        student1.setPreferredProject2(practicalProject);

        Student student2 = new Student();
        student2.setId(2);
        student2.setName("Gion");
        student2.setPreferredProject1(practicalProject);
        student2.setPreferredProject2(theoreticalProject);

        System.out.println(student1);
        System.out.println(student2);
        System.out.println(theoreticalProject);
        System.out.println(practicalProject);
    }
}