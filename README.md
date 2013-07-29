Dynamic Time Warpping for Audio Recognition. (JAVA implementation)
======================

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

```java
  int aaa =1;
```

