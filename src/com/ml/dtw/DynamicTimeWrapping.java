/**
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
