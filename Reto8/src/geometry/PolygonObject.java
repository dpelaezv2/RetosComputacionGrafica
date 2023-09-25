package geometry;


import java.util.ArrayList;
import java.util.Scanner;

import math.Matrix4x4;
import math.TranslScalRot4x4;
import math.UVN4x4;
import math.Projection4x4;
import math.Vector4;
import java.io.File;
import display.GuiMain;

public class PolygonObject {
    ArrayList<Vector4> vertices;
    public ArrayList<Vector4> transformedVertices; // vertices after transformation
    public ArrayList<Edge> edges;
    GuiMain canvas;
    public ObjectTransformation ot;
    public Camera camera;

    int nUCP;           // number of control points in U direction   
    int nVCP;           // number of control points in V direction

    int nUDiv;          // number of divisions in U direction
    int nVDiv;          // number of divisions in V direction

    Vector4 [][] controlPoints; // matrix of control points
    int [][] verticesMatrix;    // index to the vertex list of the vertex

    public PolygonObject() {
        vertices = new ArrayList<Vector4>();
        transformedVertices = new ArrayList<Vector4>();
        edges = new ArrayList<Edge>();
        ot = new ObjectTransformation();  
        camera = new Camera(ot);      
    }

    public void setCanvas(GuiMain canvas) {
        this.canvas = canvas;
    }

    public void readBezierSurface(String filename) {
        try {
            // read Number of control points in U direction and in V direction
            Scanner in = new Scanner(new File(filename));
            nUCP = in.nextInt();
            nVCP = in.nextInt();

            controlPoints = new Vector4[nUCP+1][nVCP+1];

            // read the matrix of nUCP+1 x nVCP+1 control points
            for(int u = 0; u <= nUCP; u++) {
                for(int v = 0; v <= nVCP; v++) {
                    double x = in.nextDouble();
                    double y = in.nextDouble();
                    double z = in.nextDouble();
                    controlPoints[u][v] = new Vector4(x, y, z);
                }
            }

            // read the number of divisions in U direction and in V direction
            nUDiv = in.nextInt();
            nVDiv = in.nextInt();

            verticesMatrix = new int[nUDiv+1][nVDiv+1];

            // read the center of the object
            ot.centerX = in.nextInt();
            ot.centerY = in.nextInt();
            ot.centerZ = in.nextInt();
        } catch (Exception e) {
            System.out.println("Error reading file");
        }
        createSurface();
        resetVertices();
    }

    public void createSurface() {
        // create the vertices
        for(int u = 0; u <= nUDiv; u++) {
            for(int v = 0; v <= nVDiv; v++) {
                double x = 0;
                double y = 0;
                double z = 0;
                // calculate the x, y, z coordinates of the vertex
                // at the u-th division in the U direction and the
                // v-th division in the V direction
                for(int i = 0; i <= nUCP; i++) {
                    for(int j = 0; j <= nVCP; j++) {
                        double Bui = Bezier(nUCP, i, u/(double)nUDiv);
                        double Bvj = Bezier(nVCP, j, v/(double)nVDiv);
                        x += Bui * Bvj * controlPoints[i][j].vector[0];
                        y += Bui * Bvj * controlPoints[i][j].vector[1];
                        z += Bui * Bvj * controlPoints[i][j].vector[2];
                    }
                }
                Vector4 vertex = new Vector4(x, y, z);
                vertices.add(vertex);
                verticesMatrix[u][v] = vertices.size() - 1;         // index to the vertex list
            }
        }
        // create the edges
        for(int u = 0; u < nUDiv; u++) {
            for(int v = 0; v < nVDiv; v++) {
                //int start = u * (nVDiv + 1) + v;
                //int end = start + 1;
                // Vertical edge
                int start = verticesMatrix[u][v];  
                int end = verticesMatrix[u+1][v]; 
                edges.add(new Edge(start, end));
                // Horizontal edge
                start = verticesMatrix[u][v];  
                end = verticesMatrix[u][v+1];
                edges.add(new Edge(start, end));
                // Diagonal edge
                start = verticesMatrix[u+1][v];
                end = verticesMatrix[u][v+1];
                edges.add(new Edge(start, end));
            }
        }
        // Bottom edges
        int u = nUDiv;
        for(int v = 0; v < nVDiv; v++) {
            int start = verticesMatrix[u][v];
            int end = verticesMatrix[u][v+1];
            edges.add(new Edge(start, end));
        }
        // Right edges
        int v = nVDiv;
        for(u = 0; u < nUDiv; u++) {
            int start = verticesMatrix[u][v];
            int end = verticesMatrix[u+1][v];
            edges.add(new Edge(start, end));
        }
        resetVertices();
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
            //ot.projectionDistance = in.nextInt();
        } catch (Exception e) {
            System.out.println("Error reading file");
        }
        resetVertices();
    }

    public double Bezier(int n, int i, double u) {
        return binomial(n, i) * Math.pow(u, i) * Math.pow(1-u, n-i);
    }

    public int binomial(int n, int i) {
        return factorial(n) / (factorial(i) * factorial(n-i));
    }

    public int factorial(int n) {
        int result = 1;
        for(int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
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
