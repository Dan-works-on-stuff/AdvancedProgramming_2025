//public class Main {
//    public static void main(String[] args) {
//        System.out.println("Hello, World!");
//        String[] languages= {"c++","C","c#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
//        int n = (int) (Math.random() * 1_000_000);
//        n=n*3;
//        System.out.println(n);
//        n+=Integer.parseInt("100101", 2);
//        n+=0b10101;
//        System.out.println();
//        System.out.println(n);
//        System.out.println();
//        n+=0xFF;
//        System.out.println(n);
//        n*=6;
//        System.out.println();
//        System.out.println(n);
//        int rez=n;
//        while (rez>9){
//            rez=SumOfDigits(rez);
//        }
//        System.out.println("Willy-nilly, this semester I will learn " + languages[rez]);
//    }
//    public static int SumOfDigits(int n) {
//        int sum = 0;
//        while (n > 0) {
//            sum = sum + n % 10;
//            n = n / 10;
//        }
//        return sum;
//    }
//}

public class Main {   //this is the HW
    public static void main(String[] args) {
        int n, k;
        if (args.length == 2) {
            n = Integer.parseInt(args[0]);
            k = Integer.parseInt(args[1]);
        } else {
            System.out.println("Invalid input");
            return;
        }
        int[][] a;
        a = helper_functions.generateAdjMatrix(n, k);
        if (a == null) {
            System.out.println("Not possible");
            return;
        }
        if (n < 100) {
            String matrix_str = helper_functions.turn_to_string(a, n);
            System.out.println(matrix_str);
        } else System.out.println();
        int m = helper_functions.count_edges(a, n);
        System.out.println("Number of edges: " + m);
        DegreeResult result = new DegreeResult();
        helper_functions.min_max_degree(a, n, result);
        System.out.println("\u03B4(G)= " + result.min_degree);
        System.out.println("\u0394(G)= " + result.max_degree);
        if (helper_functions.sum_of_degrees(a, n) == 2 * m)
            System.out.println("proper graph mate");
        else System.out.println("not proper graph mate");
    }

    public static class DegreeResult {
        public int min_degree;
        public int max_degree;
    }
}


//grading: quiz each lab from previous course, max 50, min 25
//project: max 20
//Each student will work individually.
//Teams of maximum two students are allowed, only if:
//    they both have reached 20 points at the lab;
//    they work together on a project that was announced;
//    the team will remain stable until the end of the semester;
//    both team members must be able to present the solutions;
//Participation in the exam is subject to getting at least 30 points at the laboratory + quizzes + project.
//The maximum score at the exam is 20 points and the minimum is 5 points.
//The final grade is calculated as follows:
//    gauss (lab + quizzes + project + exam)
//    0 <= exam <= 20
//    0 <= project <= 20
//    Integer.MIN_VALUE <= lab <= Integer.MAX_VALUE