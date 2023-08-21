package Math;

public class Matrix3x3 {

    public double[][] matrix;

    public Matrix3x3(double[][] matrix) {
        this.matrix = matrix;
    }

    public static Point3 times(Matrix3x3 matrix, Point3 point) {
        double x = matrix.matrix[0][0] * point.x + matrix.matrix[0][1] * point.y + matrix.matrix[0][2] * point.w;
        double y = matrix.matrix[1][0] * point.x + matrix.matrix[1][1] * point.y + matrix.matrix[1][2] * point.w;
        double w = matrix.matrix[2][0] * point.x + matrix.matrix[2][1] * point.y + matrix.matrix[2][2] * point.w;
        return new Point3(x, y, w);
    }

    public static Matrix3x3 times(Matrix3x3 matrix1, Matrix3x3 matrix2) {
        double[][] result = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result[i][j] = matrix1.matrix[i][0] * matrix2.matrix[0][j]
                            + matrix1.matrix[i][1] * matrix2.matrix[1][j]
                            + matrix1.matrix[i][2] * matrix2.matrix[2][j];
            }
        }
        return new Matrix3x3(result);
    }
}