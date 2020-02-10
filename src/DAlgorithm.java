import java.util.Random;

public class DAlgorithm {

    private int lineNum;        // the number of lines
    private char[] Vexs;       //  vertexes sets
    private int[][] Matrix;    // the matrix
    private static final int INM = 1000000;   // the max value of the int
    public DAlgorithm(char[] vexs, int[][] matrix) {

        int vertexLen = vexs.length;

        Vexs = new char[vertexLen];
        for (int i = 0; i < Vexs.length; i++)
            Vexs[i] = vexs[i];

        Matrix = new int[vertexLen][vertexLen];
        for (int i = 0; i < vertexLen; i++)
            for (int j = 0; j < vertexLen; j++)
                Matrix[i][j] = matrix[i][j];

        // counting the line
        lineNum = 0;
        for (int i = 0; i < vertexLen; i++)
            for (int j = i+1; j < vertexLen; j++)
                if (Matrix[i][j]!= INM)
                    lineNum++;
    }

    public void print() {
        System.out.printf("Matrix Graph:\n");
        for (int i = 0; i < Vexs.length; i++) {
            for (int j = 0; j < Vexs.length; j++)
                System.out.printf("%10d ", Matrix[i][j]);
            System.out.printf("\n");
        }
    }

    public void random() {
        System.out.printf("Randomised Matrix Graph:\n");
        for (int i = 0; i < Vexs.length; i++) {
            for (int j = 0; j < Vexs.length; j++)
            {
                Random rn = new Random();
                int answer = rn.nextInt(9) + 1;
                if(Matrix[i][j]!=1000000)
                    Matrix[i][j]=answer;
                System.out.printf("%10d ", Matrix[i][j]);
            }
            System.out.printf("\n");
        }
    }

    public void dijkstra(int startPos, int[] OtherPos, int[] dist) {
        // isFound[i]=true means the shortest distance between vertex VS and vertex i has been found
        boolean[] isFound = new boolean[Vexs.length];

        for (int i = 0; i < Vexs.length; i++) {
            isFound[i] = false;
            OtherPos[i] = startPos;
            dist[i] = Matrix[startPos][i];
        }

        isFound[startPos] = true;
        dist[startPos] = 0;

        // after the algorism has found the shortest path to a point
        int a=0;
        for (int i = 1; i < Vexs.length; i++) {
            //searching for the shortest path
            // finding the a value
            int min = INM;
            for (int j = 0; j < Vexs.length; j++) {
                if (isFound[j]==false && dist[j]<min) {
                    min = dist[j];
                    a = j;
                    System.out.printf("I am here now:"+Vexs[i]);
                    System.out.printf("\n");
                }
            }
            isFound[a] = true;

            // fixing the data set
            // after the path is found，update the set
            for (int j = 0; j < Vexs.length; j++) {
                int tmp = (Matrix[a][j]== INM ? INM : (min + Matrix[a][j]));
                if (isFound[j]==false && (tmp<dist[j]) ) {
                    dist[j] = tmp;
                    OtherPos[j] = a;

                }
            }
        }

        // printing the shortest path
        System.out.printf("dijkstra(%c): \n", Vexs[startPos]);
        for (int i=0; i < Vexs.length; i++) {
            StringBuilder path=new StringBuilder();
            setPath(path, startPos, i, OtherPos);
            System.out.printf("  shortest(%c, %c)=%d, %s\n", Vexs[startPos], Vexs[i], dist[i], path.toString());
        }
    }
    void setPath(StringBuilder path, int vs, int cur, int[] prev) {
        if (cur!=vs) {
            for (int i=0; i < Vexs.length; i++) {
                if (i==prev[cur]) {
                    setPath(path, vs, i, prev);
                    break;
                }
            }
        }
        path.append(Vexs[cur]);
    }
    public static void main(String[] args) {
        char[] vexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N'};
        int matrix[][] = {
                /**
                 * A= Senior Lot
                 * B= SecurityGate
                 * C= Rock
                 * D= EntCenter
                 * E= DiningHall
                 * F= Science
                 * G= Boys
                 * H= Chapel
                 * I= Alumni
                 * J= Girls
                 * K= VA
                 * L= UsClasses
                 * M= TeacherLot
                 * N= Admission
                */
                /*A*//*B*//*C*//*D*//*E*//*F*//*G*//*H*//*I*//*J*//*K*//*L*//*M*//*N*/
                /*A*/ {   0,   1, INM, INM, INM, INM, INM, INM, INM, INM, INM, INM, INM,   2},
                /*B*/ {   1,   0,   1, INM, INM, INM, INM, INM, INM, INM, INM, INM,   3,   1},
                /*C*/ {INM,   1,   0,   2,   3, INM, INM, INM, INM, INM, INM, INM, INM, INM},
                /*D*/ {INM, INM,   2,   0,   2, INM, INM,   5, INM, INM, INM, INM, INM, INM},
                /*E*/ {INM, INM,   3,   2,   0,   1, INM, INM, INM, INM, INM, INM, INM, INM},
                /*F*/ {INM, INM, INM, INM,   1,   0,   1, INM, INM, INM, INM, INM, INM, INM},
                /*G*/ {INM, INM, INM, INM, INM,   1,   0,   1, INM, INM, INM, INM, INM, INM},
                /*H*/ {INM, INM, INM,   5, INM, INM,   1,   0,   2, INM, INM, INM, INM, INM},
                /*I*/ {INM, INM, INM, INM, INM, INM, INM,   2,   0,   3, INM, INM, INM,   4},
                /*J*/ {INM, INM, INM, INM, INM, INM, INM, INM,   3,   0,   1, INM,  2, INM},
                /*K*/ {INM, INM, INM, INM, INM, INM, INM, INM, INM,   1,   0,   1, INM, INM},
                /*L*/ {INM, INM, INM, INM, INM, INM, INM, INM, INM, INM,   1,   0,   1,   1},
                /*M*/ {INM,   3, INM, INM, INM, INM, INM, INM, INM,   2, INM,   1,   0, INM},
                /*N*/ {   2,   1, INM, INM, INM, INM, INM, INM,   4, INM, INM,   1, INM,   0},
        };

        DAlgorithm a;
        a = new DAlgorithm(vexs, matrix);
        a.print();
        int[] prev = new int[a.Vexs.length];
        int[] SumDistance = new int[a.Vexs.length];
        a.dijkstra(1, prev, SumDistance);
        System.out.printf("\n");
        a.random();
        int[] prev1 = new int[a.Vexs.length];
        int[] SumDistance1 = new int[a.Vexs.length];
        a.dijkstra(0, prev1, SumDistance1);

    }
}
