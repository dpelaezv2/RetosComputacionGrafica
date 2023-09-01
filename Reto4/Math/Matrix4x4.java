package Math;

public class Matrix4x4 {
    int A, B, C, D;
    int E, F, G, H;
    int I, J, K, L;
    int M, N, O, P;
    
    /**
     * Constructor
     * [ A, B, C, D, ]
     * [ E, F, G, H, ]
     * [ I, J, K, L, ]
     * [ M, N, O, P, ]
     */
    public Matrix4x4(int A, int B, int C, int D, int E, int F, int G, int H, int I, int J, int K, int L, int M, int N, int O, int P) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.D = D;
        this.E = E;
        this.F = F;
        this.G = G;
        this.H = H;
        this.I = I;
        this.J = J;
        this.K = K;
        this.L = L;
        this.M = M;
        this.N = N;
        this.O = O;
        this.P = P;
    }

    static Point4 times(Matrix4x4 matrix, Point4 point) {
        return new Point4(
            matrix.A * point.x + matrix.B * point.y + matrix.C * point.z + matrix.D * point.w,
            matrix.E * point.x + matrix.F * point.y + matrix.G * point.z + matrix.H * point.w,
            matrix.I * point.x + matrix.J * point.y + matrix.K * point.z + matrix.L * point.w,
            matrix.M * point.x + matrix.N * point.y + matrix.O * point.z + matrix.P * point.w
        );
    }
}
