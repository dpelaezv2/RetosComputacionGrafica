package geometry;

import Math.UVN4x4;
import Math.Vector4;

public class Camera {
    double x, y, z;
    public double theta;
    public double phi;
    public Vector4 up;
    public Vector4 from;
    public Vector4 lookAt;
    public double r;

    public static final double DELTA_THETA = 10 * Math.PI / 180;
    public static final double DELTA_PHI = 10 * Math.PI / 180;

    public Camera() {
        theta = 0 * Math.PI / 180;
        phi = 0 * Math.PI / 180;
        up = new Vector4(0, 1, 0);
        lookAt = new Vector4(0, 0, 0); 
    }

    public UVN4x4 createUVN() {
        from = new Vector4(r * Math.cos(phi) * Math.sin(theta), r * Math.sin(phi), r * Math.cos(phi) * Math.cos(theta));
        UVN4x4 uvn = new UVN4x4(from, lookAt, up);
        //System.out.println("[" + uvn.matrix[0][0] + ", " + uvn.matrix[0][1] + ", " + uvn.matrix[0][2] + ", " + uvn.matrix[0][3] + ", \n" +
        //                    uvn.matrix[1][0] + ", " + uvn.matrix[1][1] + ", " + uvn.matrix[1][2] + ", " + uvn.matrix[1][3] + ", \n" + 
        //                    uvn.matrix[2][0] + ", " + uvn.matrix[2][1] + ", " + uvn.matrix[2][2] + ", " + uvn.matrix[2][3] + ", \n" + 
        //                    uvn.matrix[3][0] + ", " + uvn.matrix[3][1] + ", " + uvn.matrix[3][2] + ", " + uvn.matrix[3][3] + " ]");
        return uvn;
    }
}
