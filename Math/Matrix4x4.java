package Math;

public class Matrix4x4 {
    public double[][] matrix;

    public Matrix4x4(double[][] matrix) {
        this.matrix = matrix;
    }

    public static Point4 times(Matrix4x4 matrix, Point4 point) {

        double x = matrix.matrix[0][0] * point.x + matrix.matrix[0][1] * point.y + matrix.matrix[0][2] * point.w;
        double y = matrix.matrix[1][0] * point.x + matrix.matrix[1][1] * point.y + matrix.matrix[1][2] * point.w;
        double z = matrix.matrix[2][0] * point.x + matrix.matrix[2][1] * point.y + matrix.matrix[2][2] * point.w;
        double w = matrix.matrix[2][0] * point.x + matrix.matrix[2][1] * point.y + matrix.matrix[2][2] * point.w;

        return new Point4(x, y, z, w);
    }

    public static Matrix4x4 times(Matrix4x4 matrix1, Matrix4x4 matrix2) {
        double[][] result = new double[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result[i][j] = matrix1.matrix[i][0] * matrix2.matrix[0][j]
                            + matrix1.matrix[i][1] * matrix2.matrix[1][j]
                            + matrix1.matrix[i][2] * matrix2.matrix[2][j]
                            + matrix1.matrix[i][2] * matrix2.matrix[3][j];
            }
        }
        return new Matrix4x4(result);
    }
}