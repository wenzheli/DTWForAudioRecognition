Dynamic Time Warpping for Audio Recognition. (JAVA implementation)
======================

The code is for one of my papers:  
    http://www.aaai.org/ocs/index.php/AAAI/AAAI11/paper/view/3791

Features include:
  - calculate the distance between two feature vectors for 1D
  - calculate the distance between two feature vectors for 2D
  

Java implementation of Dynamic Time Warpping algorithms. DTW is mainly used for calculating 
the distance between two feature vectors with different length. For the same length of features, we can 
directly calculate the euclidean distance (manhatan distance) based on 1-1 mappings. However, when two feature 
vectors have the different length of features, we need to use flexible matching mechanism, this is where DTW comes
in. 

For signal processing, the most common features are MFCCs.  And usually, we choose the first 12 coefficients.
We can represent each audio signal as:
  F(audio) = MFCCs(audio) = (f_{1}, f_{2},....f_{n}), where each f_{i} denotes the 12 coefficient vector, such that
  f_{i} = (f_{i1},...f_{i12}).  

By using DTW, we can easily calculate the minimum distance between two MFCCs feature vectors. 


Example Code:

<b> Get the feature vector from audio files <b>
```java
   // feature extraction. The code does not include library for audio feature extraction, 
   // you need to do it by yourself. 
   double[][] features1 = getMFCCFeature(audio1);
   double[][] features2 = getMFCCFeature(audio2);
```

<b> Calculate the distance between two audio files using DTW <b>
```java
  // initialize dyanamic time warpping object. The variance represents the variance for each dimension.
  // the purpose is to normalize each dimension when we do euclidean calculation, since the scale
  // of each dimension may quite different. 
  DynamicTimeWrapping2D dtw = new DynamicTimeWrapping(features1, features2, variance);
  
  // calculate the distance
  double distance = dtw.calDistance();
```

