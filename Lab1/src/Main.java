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

import java.util.*;
import java.util.Random;
import java.util.Arrays;

public class Main {   //this is the HW
    public static void main(String[] args) {
        int n, k;
        if (args.length == 2) {
            n = Integer.parseInt(args[0]);
            k = Integer.parseInt(args[1]);

        }
        else {
            System.out.println("Invalid input");
            return;
        }
        int i=0, j=0;
        int [][] a = new int[n][n];
        a=generateAdjMatrix(n);
        String matrix_str = turn_to_string(a, n);
        System.out.println(matrix_str);
        int m=count_edges(a, n);
        System.out.println("Number of edges: " + m);
        DegreeResult result = new DegreeResult();
        min_max_degree(a, n, result);
        System.out.println("\u03B4(G)= " + result.min_degree);
        System.out.println("\u0394(G)= " + result.max_degree);
        if(sum_of_degrees(a, n) == 2*m)
            System.out.println("proper graph mate");
        else System.out.println("not proper graph mate");
    }

    public static class DegreeResult{
        public int min_degree;
        public int max_degree;
    }
    public static int[][] generateAdjMatrix(int n) {
        int[][] matrix = new int[n][n];
        Random rand = new Random();
        int i=0, j=0;
        for (i=0; i<n; i++) {
            for (j=i+1; j<n; j++) {
                matrix[i][j] = matrix[j][i] = rand.nextInt(2);

            }
        }
        return matrix;
    }
    public static String turn_to_string(int[][] matrix, int len) {
        String str = "";
        for (int i=0; i<len; i++) {
            for (int j=0; j<len; j++) {
                char c = Character.forDigit(matrix[i][j], 10);
                str += c;
                str += " ";
            }
            if (i<len-1)
                str+= System.lineSeparator();
        }
        return str;
    }
    public static int count_edges(int[][] matrix, int len) {
        int count = 0;
        for (int i=0; i<len; i++) {
            for (int j=i+1; j<len; j++) {
                if (matrix[i][j] == 1 && matrix[j][i] == 1) {
                    count++;
                }
            }
        }
        return count;
    }
    public static void min_max_degree(int[][] matrix, int len, DegreeResult result) {
        result.min_degree = Integer.MAX_VALUE;
        result.max_degree = Integer.MIN_VALUE;
        for (int i=0; i<len; i++) {
            int degree=0;
            for (int j=0; j<len; j++) {
                    degree+=matrix[i][j];
            }if (degree < result.min_degree) {
                result.min_degree = degree;
            }else if (degree > result.max_degree) {
                result.max_degree = degree;
            }
        }
    }
    public static int sum_of_degrees(int[][] matrix, int len) {
        int sum_of_degrees = 0;
        for (int i=0; i<len; i++) {
            for (int j=0; j<len; j++) {
                sum_of_degrees+=matrix[i][j];
            }
        }
        return sum_of_degrees;
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