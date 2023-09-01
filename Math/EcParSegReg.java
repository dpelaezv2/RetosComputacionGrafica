package Math;

public class EcParSegReg {

    public double x1;
    public double y1;
    public double x2;
    public double y2;

    public EcParSegReg(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public static double[] solve(double epsr1, double epsr2) {
        double[] solutions = new double[2];

        if (epsr1 == epsr2) {
            solutions[0] = 0.0;
            solutions[1] = 0.0;
        } else {
            solutions[0] = (epsr1 - epsr2) / (epsr1 + epsr2);
            solutions[1] = (2 * epsr2) / (epsr1 + epsr2);
        }

        return solutions;
    }
}