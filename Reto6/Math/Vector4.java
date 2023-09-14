package Math;

public class Vector4 {
    public double [] vector = new double[4];

    public Vector4() {
        vector[0] = 0;
        vector[1] = 0;
        vector[2] = 0;
        vector[3] = 1;
    }

    public Vector4(double x, double y, double z) {
        vector[0] = x;
        vector[1] = y;
        vector[2] = z;
        vector[3] = 1;
    }

    public void normalize() {
        if(vector[3] == 0) {
            return;
        }
        double mag = Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1] + vector[2] * vector[2]);
        vector[0] /= mag;
        vector[1] /= mag;
        vector[2] /= mag;
        vector[3] = 1;
    }

    public void normalizeW() {
        if(vector[3] == 0) {
            return;
        }
        vector[0] /= vector[3];
        vector[1] /= vector[3];
        vector[2] /= vector[3];
        vector[3] = 1;
    }

    public String toString() {
        return "(" + vector[0] + ", " + vector[1] + ", " + vector[2] + ")";
    }

    public double Magnitude() {
        return Math.sqrt(vector[0] * vector[0] + vector[1] * vector[1] + vector[2] * vector[2]);
    }

    public Vector4 crossProduct(Vector4 v2) {
        return new Vector4(vector[1] * v2.vector[2] - vector[2] * v2.vector[1], 
                            vector[2] * v2.vector[0] - vector[0] * v2.vector[2], 
                            vector[0] * v2.vector[1] - vector[1] * v2.vector[0]);
    }

    public double dotProduct(Vector4 v2) {
        return vector[0] * v2.vector[0] + vector[1] * v2.vector[1] + vector[2] * v2.vector[2];
    }
    
    public static Vector4 substract(Vector4 v1, Vector4 v2) {
        return new Vector4(v1.vector[0] - v2.vector[0], v1.vector[1] - v2.vector[1], v1.vector[2] - v2.vector[2]);
    }

    public static Vector4 minus(Vector4 v1) {
        return new Vector4(-v1.vector[0], -v1.vector[1], -v1.vector[2]);
    }
}
