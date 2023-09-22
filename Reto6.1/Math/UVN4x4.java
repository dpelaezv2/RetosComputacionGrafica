package Math;

public class UVN4x4 extends Matrix4x4 {

    public UVN4x4(Vector4 from, Vector4 lookAt, Vector4 up) {
        super();
        Vector4 n = Vector4.substract(from, lookAt);
        n.normalize();
        
        Vector4 u = up.crossProduct(n);
        u.normalize();

        Vector4 v = n.crossProduct(u);

        matrix[0][0] = u.vector[0];
        matrix[0][1] = u.vector[1];
        matrix[0][2] = u.vector[2];
        matrix[1][0] = v.vector[0];
        matrix[1][1] = v.vector[1];
        matrix[1][2] = v.vector[2];
        matrix[2][0] = n.vector[0];
        matrix[2][1] = n.vector[1];
        matrix[2][2] = n.vector[2];

        matrix[0][3] = Vector4.minus(u).dotProduct(from);
        matrix[1][3] = Vector4.minus(v).dotProduct(from);
        matrix[2][3] = Vector4.minus(n).dotProduct(from);
    }
}
