package com.ml.dtw;


/**
 * This class used to calculate the distance between two vectors. 
 * these two vectors might have different length, so use dynamic 
 * programming is used to calculate the minimum distance. 
 * 
 * There are FIVE things we must specify 
 * 
 * <li> End point constraint </li> 
 * First Point in test pattern meet with first point in reference pattern
 * Last Point in test pattern meet with last point in reference pattern 
 *
 * <li> local continuity constraint </li>
 * Support five different local path  
 * p1 --> (1,0)  
 * p2 --> (2,1)    
 * p3 --> (1,1)
 * p4 --> (1,2)
 * p5 --> (0,1)
 * 
 * <li> Global Path constraint </li>
 * |i(k) - j(k)| <= R   R is constraint value, k is the timing index.
 * 
 * <li> Axis Orientation </li>
 * x-axis refers to the test pattern, y-axis refers to reference/template pattern
 * by default, we use the symmetric case. 
 * 
 * <li> Distance Measure </li>
 * Use Euclidean distance measurement. For the weighting function,
 * choose W(k) = i(k) - i(k-1) + j(k) - j(k-1), which is symmetric function, the advantage
 * of choosing this weighting function is, we don't need to consider the normalization value 
 * for all the summation. Because for all cases, the normalization value will be constant.  
 *  
 * For technical detail, please look at my paper: 
 *      http://www.aaai.org/ocs/index.php/AAAI/AAAI11/paper/view/3791 
 *      
 * @author liwenzhe
 * 
 */
public class DynamicTimeWrapping1D extends DynamicTimeWrapping{
    
    private double[] test;      // query feature vector  
    private double[] reference; // reference feature vector. 
      
    public DynamicTimeWrapping1D(double[] test, double[] reference)
    {
        this.test = test;
        this.reference = reference;
    }
    
    
    /**
     * Calculate the minimum distance between two vectors.  
     * following is the DP formula: 
     * 
     *  D(n,m) = MIN {
     *              D(n-1,m-1) + 2d(n,m), 
     *              D(n-1,m-2) + 3d(n,m),
     *              D(n-2,m-1) + 3d(n,m),
     *              D(n-1,m) + d(n,m),
     *              D(n,m-1) + d(n,m)
     *            }
     *          
     * @return  distance between two vectors. 
     */
    @Override
    public double calDistance() {
        int n = test.length;
        int m = reference.length;
        if (n < 1 || m < 1){
            System.out.println("One of the feature vectors has zero length!");
            return -1;
        }
        
        // DP for calculating the minimum distance between two vector. 
        // DTW[i,j] = minimum distance between vector test[0..i] and reference[0..j]
        double[][] DTW = new double[n][m];
        
        // initialization. 
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                DTW[i][j] = Double.MAX_VALUE;
        
        // initialize start point
        DTW[0][0] =  getDistance(test[0],reference[0]);  
        
        // initialize boundary cases. 
        for (int i = 1; i < n; i++)
            DTW[i][0] = DTW[i-1][0] + getDistance(test[i],reference[0]);  
        
        for(int i = 1; i < m; i++)
            DTW[0][i] = DTW[0][i-1] + getDistance(test[0],reference[i]);
        
        // DP comes here...
        for (int i = 1; i < n; i++)
        {
            for (int j = Math.max(1, i- globalPathConstraint/2); j < Math.min(m, i+ globalPathConstraint/2); j++)
            {   
                double cost = getDistance(test[i],reference[j]);
                // d1,d2,d3,d4,d5 denotes the five different moves. 
                double d1 = cost + DTW[i-1][j];
                double d2 = cost + DTW[i][j-1];
                double d3 = 2 * cost + DTW[i-1][j-1];
                double d4 = Double.MAX_VALUE;
                if (j >1)
                    d4 = 3 * cost + DTW[i-1][j-2];
                double d5 = Double.MAX_VALUE;
                if (i > 2)
                    d5 = 3 * cost + DTW[i-2][j-1];
                
                DTW[i][j] = getMin(d1,d2,d3,d4,d5); 
            }
        }
        
        return DTW[n-1][m-1];  
    }   
    
    // Euclidean distance between two points.  
    private double getDistance(double d1, double d2)
    {
        return Math.sqrt((d1-d2)* (d1-d2));
    }
    
}   
