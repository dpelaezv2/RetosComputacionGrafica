package Math;

public class Matrix3x3 {
    double A, B, C;
    double D, E, F;
    double G, H, I;

    /**
     * Constructor
     * [ A, B, C, ]
     * [ D, E, F, ]
     * [ G, H, I, ]
    **/
    public Matrix3x3(double a, double b, double c, double d, double e, double f, double g, double h, double i) {
        A = a;
        B = b;
        C = c;
        D = d;
        E = e;
        F = f;
        G = g;
        H = h;
        I = i;
    }

    public static Point3 times(Matrix3x3 matrix, Point3 point) {
        double x = point.x;
        double y = point.y;
        double w = point.w;
        double newX = matrix.A * x + matrix.B * y + matrix.C * w;
        double newY = matrix.D * x + matrix.E * y + matrix.F * w;
        double newZ = matrix.G * x + matrix.H * y + matrix.I * w;
        Point3 newPoint = new Point3(newX, newY, newZ);
        return newPoint;
    }

    static Matrix3x3 times(Matrix3x3 matrix1, Matrix3x3 matrix2) {
        double newA = matrix1.A * matrix2.A + matrix1.B * matrix2.D + matrix1.C * matrix2.G;
        double newB = matrix1.A * matrix2.B + matrix1.B * matrix2.E + matrix1.C * matrix2.H;
        double newC = matrix1.A * matrix2.C + matrix1.B * matrix2.F + matrix1.C * matrix2.I;
        double newD = matrix1.D * matrix2.A + matrix1.E * matrix2.D + matrix1.F * matrix2.G;
        double newE = matrix1.D * matrix2.B + matrix1.E * matrix2.E + matrix1.F * matrix2.H;
        double newF = matrix1.D * matrix2.C + matrix1.E * matrix2.F + matrix1.F * matrix2.I;
        double newG = matrix1.G * matrix2.A + matrix1.H * matrix2.D + matrix1.I * matrix2.G;
        double newH = matrix1.G * matrix2.B + matrix1.H * matrix2.E + matrix1.I * matrix2.H;
        double newI = matrix1.G * matrix2.C + matrix1.H * matrix2.F + matrix1.I * matrix2.I;

        Matrix3x3 newMatrix = new Matrix3x3(newA, newB, newC, newD, newE, newF, newG, newH, newI);
        return newMatrix;
    }
}
