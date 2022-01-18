import java.util.Arrays;

public class MESSolver {
    static int n;
    public static Chart chart = new Chart("x","u(x)");
    public MESSolver(int n){
         this.n = n;
     }
    public static void start(){
        System.out.println("n = "+ n);
        System.out.println("matrixB");
        float[] matrixL = new float[n];
        float[][] matrixB = new float[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                matrixB[i][j] = countB(i,j);
            }
            System.out.println(Arrays.toString(matrixB[i]));
        }
        for(int i=0;i<n;i++){
            matrixL[i] = countL(i);
        }
        System.out.println("matrixL");
        System.out.println(Arrays.toString(matrixL));
        float[] res = gaussElimination(matrixB,matrixL);
        drawChart(res);
    }

    private static float countXi(int i){
        return (float) (2*i)/ (float) n;
    }

    private static float countEi(int i,float x){
        float xi1 = countXi(i-1);
        float xi2 = countXi(i);
        float xi3 = countXi(i+1);
        if (x >= xi1 && x <= xi2 && i-1>=0){
            return (x-xi1)/(xi2-xi1);
        }
        else if(x >= xi2 && x <= xi3){
            return (xi3-x)/(xi3-xi2);
        }
        else{
            return 0;
        }
    }

    private static float countEiLow(int i,int type) {
        if (type == 0 && i-1 >= 0){
            return countXi(i - 1);
        }
        if(i <= n)
        {
            return countXi(i);
        }
        return Float.NEGATIVE_INFINITY;
    }
    private static float countEiHigh(int i,int type) {
        if (type == 0) {
            return countXi(i);
        }
        if (i+1 <= n)
        {
            return countXi(i+1);
        }
        return Float.NEGATIVE_INFINITY;

    }

    private static float countEiDerivative(int i,float x){
        float xi1 = countXi(i-1);
        float xi2 = countXi(i);
        float xi3 = countXi(i+1);
        if (x >= xi1 && x <= xi2 && i-1>=0){
            return (float) 1/(xi2-xi1);
        }
        else if(x >= xi2 && x <= xi3){
            return (float) (-1)/(xi3-xi2);
        }
        else{
            return 0;
        }
    }

    private static float countIntegralBoundaries(int i1,int i2){
         float res = 0;
         float i1FirstStart = countEiLow(i1,0);
         float i1SecondStart = countEiLow(i1,1);
         float i1FirstEnd = countEiHigh(i1,0);
         float i1SecondEnd = countEiHigh(i1,1);
        float i2FirstStart = countEiLow(i2,0);
        float i2SecondStart = countEiLow(i2,1);
        float i2FirstEnd = countEiHigh(i2,0);
        float i2SecondEnd = countEiHigh(i2,1);
        //first with first
        res += countIntegral(i1,i2,Math.max(i1FirstStart,i2FirstStart),Math.min(i1FirstEnd,i2FirstEnd));
        //first with second
        res += countIntegral(i1,i2,Math.max(i1FirstStart,i2SecondStart),Math.min(i1FirstEnd,i2SecondEnd));
        //second with first
        res += countIntegral(i1,i2,Math.max(i1SecondStart,i2FirstStart),Math.min(i1SecondEnd,i2FirstEnd));
        //second with second
        res += countIntegral(i1,i2,Math.max(i1SecondStart,i2SecondStart),Math.min(i1SecondEnd,i2SecondEnd));
        return res;
    }

    public static float countIntegral(int i1, int i2, float a, float b){
        float[] points = {(float) -0.774597, 0, (float) 0.774597};
        float[] weights = {(float) 0.555556, (float) 0.888889, (float) 0.555556};
        float res = 0;
        float x = 0;
        float x1 = 0;
        float x2 = 0;
        if(a<b && a != Float.NEGATIVE_INFINITY){
            for (int i = 0; i < 3; i++)
            {
                x = ((b-a)/2)*points[i]+(a+b)/2;
                if (b <= 1){
                    res += (float) 3*((b-a)/2)*weights[i]*countEiDerivative(i1,x) *countEiDerivative(i2,x);
                }
                else if (a >= 1){
                    res += (float) 5*((b-a)/2)*weights[i]*countEiDerivative(i1,x) *countEiDerivative(i2,x);
                }
                else{
                    x1 = ((1-a)/2)*points[i]+(a+1)/2;
                    x2 = ((b-1)/2)*points[i]+(1+b)/2;
                    res +=  (float) 3*((1-a)/2)*weights[i]*countEiDerivative(i1, x1)
                            *countEiDerivative(i2,x1);
                    res +=  (float) 5*((b-1)/2)*weights[i]*countEiDerivative(i1,x2)
                            *countEiDerivative(i2,x2);
                }

            }
            return res;
        }
        return 0;

    }


    private static float countB(int i1,int i2){
         return -3*countEi(i1,0)*countEi(i2,0)+countIntegralBoundaries(i1,i2);
    }

    private static float countL(int i2){
        return (float) -30*countEi(i2,0);
    }

    private static float[] gaussElimination(float matrixB[][],float matrixL[]) {
        float[][] matrix = new float[n][n+1];
        float[] res = new float[n];
        float sum = 0;
        float c;
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                matrix[i][j] = matrixB[i][j];
            }
            matrix[i][n] = matrixL[i];
        }
        //upper triangle
        for(int i=0; i<n-1; i++)
        {
            for(int j=i+1; j<n; j++)
            {
                c= matrix[j][i]/matrix[i][i];
                for(int k=0; k<n+1; k++)
                {
                    matrix[j][k] -= c*matrix[i][k];
                }

            }
        }
        //result
        res[n-1] = matrix[n-1][n]/matrix[n-1][n-1];
        for(int i=n-2; i>=0; i--)
        {
            sum=0;
            for(int j=i+1; j<n; j++)
            {
                sum+=matrix[i][j]*res[j];
            }
            res[i]=(matrix[i][n]-sum)/matrix[i][i];
        }
        System.out.println("solution");
        System.out.println(Arrays.toString(res));
        return res;
    }
    private static float countU(float[] res, float x){
         float sum = 0;
         for(int i = 0;i<n;i++){
             sum+=res[i]*countEi(i,x);
         }
         return sum;
    }

    private static void drawChart(float[] res){
         float nr = 0;
         while(nr <= 2){
             chart.addData(String.valueOf(nr),countU(res,nr));
             nr += 0.1;
         }
    }
}
