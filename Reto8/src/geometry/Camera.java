package geometry;

import math.UVN4x4;
import math.Vector4;

public class Camera {
    Vector4 lookAt;
    Vector4 cameraPosition;
    Vector4 up;
    public double theta;
    public double phi;
    public double radius = 1000;
    public static double DELTA_THETA = 10 * Math.PI / 180;
    public static double DELTA_PHI = 10 * Math.PI / 180;
    public static double DELTA_RADIUS = 25;
    public static boolean DEBUG = false;
    ObjectTransformation ot;

    public Camera(Vector4 lookAt, Vector4 cameraPosition, Vector4 up, 
        ObjectTransformation ot) {
        this.lookAt = lookAt;
        this.cameraPosition = cameraPosition;
        this.up = up;
        this.ot = ot;
    }
    
    public Camera(ObjectTransformation ot) {
        this.ot = ot;
        //this.lookAt = new Vector4(0, 0, 0);
        this.lookAt = new Vector4(ot.centerX, ot.centerY, ot.centerZ);
        //this.cameraPosition = new Vector4(0, 0, 1000);
        double projectdR = Math.cos(phi) * radius;
        double y = Math.sin(phi) * radius + ot.centerY;
        double x = Math.sin(theta) * projectdR + ot.centerX;
        double z = Math.cos(theta) * projectdR + ot.centerZ;
        cameraPosition = new Vector4(x, y, z);
        this.up = new Vector4(0, 1, 0);
    }

    public UVN4x4 createUVN() {
        if(DEBUG) System.out.println("theta: " + theta);
        this.lookAt = new Vector4(ot.centerX, ot.centerY, ot.centerZ);
        double projectdR = Math.cos(phi) * radius;
        double y = Math.sin(phi) * radius + ot.centerY;
        double x = Math.sin(theta) * projectdR + ot.centerX;
        double z = Math.cos(theta) * projectdR + ot.centerZ;
        cameraPosition = new Vector4(x, y, z);
        this.up = new Vector4(0, 1, 0);
        UVN4x4 uvn = new UVN4x4(cameraPosition, lookAt, up);
        if(DEBUG) System.out.println("UVN: " + uvn);
        return uvn;
    }   

}
