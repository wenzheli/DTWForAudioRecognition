package com.ml.dtw;

public abstract class DynamicTimeWrapping {
    
    protected int globalPathConstraint = 20;  
    
    /**
     * Calculate the distance between two feature vectors. 
     * The feature vector can be 1-dimensional feature vector OR
     * 2-dimensioanl feature vector.  For speech processing, we usually
     * represent audio signal as MFCCs features, where each entry is again 
     * 1-dimensional feature vector (we usually choose the first 12 cofficients 
     * for MFCCs)
     * @return
     */
    public abstract double calDistance();
    
    public double getMin(double ... num){
        double min = Double.MAX_VALUE;
        for (int i=0; i<num.length; i++){
            if (num[i] < min)
                min = num[i];
        }
         
        return min;
    }
    
    public void setGlobalPathConstraint(int globalPathConstraint){
        this.globalPathConstraint = globalPathConstraint;
    }
    
}
