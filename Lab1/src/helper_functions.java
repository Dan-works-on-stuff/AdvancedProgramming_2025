import java.util.Random;

public class helper_functions {

    public static int[][] generateAdjMatrix(int n, int k) {
        if (n < 2 * k) {
            return null; // Not possible to have both clique and stable set of size k
        }
        int[][] matrix = new int[n][n];
        Random rand = new Random();
        long counter = 0;
        int i = 0, j = 0;
        for (i = 0; i < k; i++) {
            for (j = i + 1; j < k; j++) {
                    matrix[i][j] = matrix[j][i] = 1;
                    counter++;
                    if (counter % 10000000 == 0) {
                        System.out.print(counter + " ");
                    }
                }
            }
        for (i = k; i < 2 * k; i++) {
            for (j = i + 1; j < 2 * k; j++) {
                matrix[i][j] = matrix[j][i] = 0;
                counter++;
                if (counter % 10000000 == 0) {
                    System.out.print(counter + " ");
                }
            }
        }
        for (i = 0; i < n; i++) {
            for (j = i + 1; j < n; j++) {
                // Skip if already part of clique or stable set
                if ((i < k && j < k) || (i >= k && i < 2 * k && j >= k && j < 2 * k)) {
                    continue;
                }
                matrix[i][j] = matrix[j][i] = rand.nextInt(2);
                counter++;
                if (counter % 10000000 == 0) {
                    System.out.print(counter + " ");
                }
            }
        }
        return matrix;
    }

    public static String turn_to_string(int[][] matrix, int len) {
        String str = "";
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                char c = Character.forDigit(matrix[i][j], 10);
                str += c;
                str += " ";
            }
            if (i < len - 1)
                str += System.lineSeparator();
        }
        return str;
    }

    public static int count_edges(int[][] matrix, int len) {
        int count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (matrix[i][j] == 1 && matrix[j][i] == 1) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void min_max_degree(int[][] matrix, int len, Main.DegreeResult result) {
        result.min_degree = Integer.MAX_VALUE;
        result.max_degree = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            int degree = 0;
            for (int j = 0; j < len; j++) {
                degree += matrix[i][j];
            }
            if (degree < result.min_degree) {
                result.min_degree = degree;
            } else if (degree > result.max_degree) {
                result.max_degree = degree;
            }
        }
    }

    public static int sum_of_degrees(int[][] matrix, int len) {
        int sum_of_degrees = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                sum_of_degrees += matrix[i][j];
            }
        }
        return sum_of_degrees;
    }
}
