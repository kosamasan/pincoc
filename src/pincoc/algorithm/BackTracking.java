/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pincoc.algorithm;

/**
 *
 * @author papamargaritis
 */
public class BackTracking {

   public B parse(int [][]  A, B Bi, double quality) {
        B temp= Bi;
        
        
       for (int k=0;k< Bi.index.size();k++) {
            Object o= Bi.index.remove(k);
            int tempi= ((Integer)o).intValue();
            boolean found = false;
            
           for (int j=0;j< A.length;j++) {
              if (Bi.index.contains(j))
                 continue;
              if (tempi == j)
                  continue;
                        
               B B_ = new B(A, Bi.index, j);
               
              double q= B_.Q();
               //System.out.println(j + ": "+ q + " "+ quality);
               
               if (q> quality) {
                   
                  quality= B_.quality;
                  temp= B_;
                  found= true;
              }
          
               
           }
           
           if (found)
               return temp;
           
           Bi.index.add(k, o);
       }  
           return temp;

    }
}
