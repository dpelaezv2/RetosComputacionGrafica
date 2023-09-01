package Math;

public class Vector3 {
    public double x;
    public double y;
    public double z;

    public Vector3(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Vector3 crossProduct(Vector3 v1, Vector3 v2) {
        double x = v1.y * v2.z - v1.z * v2.y;
        double y = v1.z * v2.x - v1.x * v2.z;
        double z = v1.x * v2.y - v1.y * v2.x;

        return new Vector3(x, y, z);
    }

    public static double dotProduct(Vector3 v1, Vector3 v2) {
        return v1.x * v2.x + v1.y * v2.y + v1.z * v2.z;
    }

    public double Magnitude() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public void Normalize() {
        double magnitude = Magnitude();
        if (magnitude != 0) {
            x /= magnitude;
            y /= magnitude;
            z /= magnitude;
        }
    }
}