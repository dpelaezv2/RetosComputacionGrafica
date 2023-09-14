package geometry;


import java.util.ArrayList;
import java.util.Scanner;

import Math.Matrix4x4;
import Math.Projection4x4;
import Math.TranslScalRot4x4;
import Math.Vector4;
import Math.UVN4x4;

import java.io.File;
import display.Draw3D;

public class PolygonObject {
    ArrayList<Vector4> vertices;
    public ArrayList<Vector4> transformedVertices; // vertices after transformation
    public ArrayList<Edge> edges;
    Draw3D canvas;
    public ObjectTransformation ot;
    public Camera camera;

    public PolygonObject() {
        vertices = new ArrayList<Vector4>();
        transformedVertices = new ArrayList<Vector4>();
        edges = new ArrayList<Edge>();
        ot = new ObjectTransformation();   
        camera = new Camera();     
    }

    public void setCanvas(Draw3D canvas) {
        this.canvas = canvas;
    }

    public void readObject(String filename) {
        try {
            // read the number of vertices and then the vertices
            Scanner in = new Scanner(new File(filename));
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                double x = in.nextDouble();
                double y = in.nextDouble();
                double z = in.nextDouble();
                vertices.add(new Vector4(x, y, z));
            }
            // read the number of edges and then the edge indices
            n = in.nextInt();
            for (int i = 0; i < n; i++) {
                int start = in.nextInt();
                int end = in.nextInt();
                edges.add(new Edge(start, end));
            }
            // read the center of the object
            // rotations and scaling are done with respect to the center
            ot.centerX = in.nextInt();
            ot.centerY = in.nextInt();
            ot.centerZ = in.nextInt();
            // read the Z coordinate of the the projection plane.
            // Since the camera is at the origin looking into the negative
            // z axis, the projection plane is at z = -projectionDistance
            ot.projectionDistance = in.nextInt();

            // P0 of the camera 
            camera.x = in.nextDouble();
            camera.y = in.nextDouble();
            camera.z = in.nextDouble();
            camera.r = Math.sqrt((camera.x * camera.x) + (camera.y * camera.y) + (camera.z * camera.z));
        } catch (Exception e) {
            System.out.println("Error reading file");
        }
        resetVertices();
    }

    public void draw() {
        if (this.canvas != null) {
            for (Edge e : edges) {
                // draw the transformed edges
                Vector4 v1 = transformedVertices.get(e.start);
                Vector4 v2 = transformedVertices.get(e.end);
                int x1 = (int) v1.vector[0];
                int y1 = (int) v1.vector[1];
                int x2 = (int) v2.vector[0];
                int y2 = (int) v2.vector[1];
                canvas.drawOneLine(x1, y1, x2, y2);
            }
        }
    }

    public void resetVertices() {
        ot.reset();
        transformedVertices.clear();
        for (Vector4 v : vertices) {
            Vector4 newVertex = new Vector4(v.vector[0], v.vector[1], v.vector[2]);
            transformedVertices.add(newVertex);
        }
    }

    public void transformObject() {
        transformedVertices.clear();
        TranslScalRot4x4 tsr = ot.createTransformation();
        for (Vector4 v : vertices) {
            Vector4 newVertex = Matrix4x4.times(tsr, v);
            transformedVertices.add(newVertex);
        }
    }

    public void projectObject() {
        ArrayList<Vector4> projectedVertices = new ArrayList<>();
        Projection4x4 p = ot.createProjection();
        for (Vector4 v : transformedVertices) {
            Vector4 newVertex = Matrix4x4.times(p, v);
            newVertex.normalizeW();
            projectedVertices.add(newVertex);
        }
        transformedVertices = projectedVertices;
    }
    
    public void setCamera() {
        ArrayList<Vector4> cameraTransformed = new ArrayList<>();
        UVN4x4 uvn = camera.createUVN();
        for (Vector4 v : transformedVertices) {
            Vector4 newVertex = Matrix4x4.times(uvn, v);
            newVertex.normalizeW();
            cameraTransformed.add(newVertex);
        }
        transformedVertices = cameraTransformed;
    }
}
