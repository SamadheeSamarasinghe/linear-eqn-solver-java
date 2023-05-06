import java.util.Scanner;

public class LinearEqnSolver {
    // Method to calculate the determinant of a 2x2 matrix 
    public static double det(double[][] m) {
        return (m[0][0] * m[1][1]) - (m[0][1] * m[1][0]);
    }

    // Method to replace a column in a matrix 
    public static double[][] replaceCol(double[][] m, double[] col, int colIndex) {
        //Declaring a multi-dimensional array to store the matrix with replaced columns
        double[][] result = new double[m.length][m.length];
        //Iterating through the columns and replacing with constants
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m.length; j++) {
                if (j == colIndex) {
                    result[i][j] = col[i];
                } else {
                    result[i][j] = m[i][j];
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        // Declaring a multi-dimensional(2x2) array to store coefficients
        double[][] m = new double[2][2];
        // Declaring a single dimensional array(2x1) to store constants
        double[] c = new double[2];

        // Getting equations from the user
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter 2 linear equations with 2 variables: ");

        for (int i = 0; i < 2; i++) {
            System.out.println("Equation " + (i + 1) + ": ");
            // Declaring a string variable to store user input equations
            String eq = sc.nextLine();

            // Splitting 'eq' string variable to extract its coefficient elements to a separate string array
            String[] coefElements = eq.split("[xy]");
            // Declaring an integer variable to keep track of the current position of the elements
            int index = 0;

            // Iterating through the columns of the matrix
            for (int j = 0; j < 2; j++) {
                if (index >= coefElements.length) {
                    m[i][j] = 0.0;
                } else {
                    try {
                        // Parsing extracted string values to a double
                        m[i][j] = Double.parseDouble(coefElements[index]);
                        index++;
                        // Throws an exception for non-numeric characters
                    } catch (NumberFormatException e) {
                        // for -ve coefficients
                        if (coefElements[index].equals("-")) {
                            m[i][j] = -1.0;
                            index++;
                        }
                        // for +ve coefficients
                        else {
                            m[i][j] = 1.0;
                            index++;
                        }
                    }
                }
            }

            // Splitting 'eq' string variable to extract its constant elements to a separate string array
            String[] constElements = eq.split("=");
            try {
                // Extracting the constant by parsing the 2nd element of the array and assigning it to ith index of array c
                c[i] = Double.parseDouble(constElements[1]);
            } catch (NumberFormatException e) {
                System.out.println("Equation " + (i + 1) + " contains invalid constant");
            }
        }

        // Displaying the coefficients and the constants of the equations
        System.out.println("Coefficients       Constants");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(m[i][j] + "      ");
            }
            System.out.print("|   " + c[i]);
            System.out.println();
        }

        // Calculating determinant of the coefficient matrix using the det method
        double detA = det(m);

        // Applying Cramer's rule
        // Replacing column 1 with the constants using the replaceCol method
        double detAx = det(replaceCol(m, c, 0));

        // Replacing column 2 with the constants using the replaceCol method
        double detAy = det(replaceCol(m, c, 1));

        // Checking if the determinant is non-zero
        if (detA != 0) {
            // Calculating the values of variables x and y
            double x = detAx / detA;
            double y = detAy / detA;

            System.out.println("The solution for x, y are: ");
            System.out.println("x = " + x);
            System.out.println("y = " + y);

        } else {
            System.out.println("The system of equations has no unique solution.");
        }
    }
}
