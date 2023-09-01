package Math;

public class Vector3 {
    double x, y, z;

    //Constructor
    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    static Vector3 crossProduct(Vector3 v1, Vector3 v2) {
        return new Vector3(v1.y * v2.z - v1.z * v2.y, v1.z * v2.x - v1.x * v2.z, v1.x * v2.y - v1.y * v2.x);
    }

    static double dotProduct(Vector3 v1, Vector3 v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    double Magnitude(Vector3 v) {
        return Math.sqrt(v.x * v.x + v.y * v.y + v.z * v.z);
    }

    Vector3 Normalize(Vector3 v) {
        double magnitude = Magnitude(v);
        
        double xnorm = v.x/magnitude;
        double ynorm = v.y/magnitude;
        double znorm = v.z/magnitude;

        return new Vector3(xnorm, ynorm, znorm);
    }
}
