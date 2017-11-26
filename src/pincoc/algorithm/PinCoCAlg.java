/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pincoc.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author home
 */
public class PinCoCAlg {
    
    public ArrayList PiNCoC (int [][] A, int max_flips, double p) throws Exception {
         ArrayList B_list= new ArrayList();

       //as long as the length of the array is >0
       while (notEmpty(A)) {
           
           //create new instance of Bi- adds a single row/column: a random index of the A array
            B Bi= new B(A);
            int f= 0;
            double quality = - 9999999.0;
            boolean local_maximum= false;
           
            while ( (f < max_flips) && (! local_maximum) ) {
                double p_ = Math.random();
                 
                if (p_ > p) {
                    
                    //generate a new flip_move, i.e. it tries to add a new row/column in Bi that improves quality
                     B Bi_ = Bi.flip_move(quality);
                     
                   //if the new Bi instance improves the quality of the original Bi we use this now on
                   //the quality value is updated
                    if (Bi_.Q() > Bi.Q()) {
                        
                        Bi= Bi_;
                        quality= Bi.quality;
                    }
                    else
                        local_maximum= true;
                }
                else {
                    //if reached a local_maximum, the algorithm tries to remove a row/column which
                    //causes the minimum decrease in quality
                    Bi.remove_min();
                }    
                f++;     
            } //end while

            
            BackTracking btrack= new BackTracking();
            //the backtracking method tries to remove a node, i.e. a row/column, and add another row/column
            //that maximises quality
            Bi = btrack.parse(A, Bi, quality);
             
             
             //the transformed Bi is added in the B set
            B_list.add(Bi.index);
            
            //generates a new adjacency matrix removing of A the rows/columns of Bi   
            A= getNewA(A, Bi.index);
            
            //displays the intermediate results
            System.out.println("A: " + A.length);
            
            for (int i=0;i< B_list.size();i++) {
               System.out.print(B_list.get(i).toString() + " ");
           }
            System.out.println();
            System.gc();
            
      } //end while

         return B_list;
    }
    
    
    
    boolean notEmpty(int [][] A) {
       return A.length > 0;
       
    }
    
    int [][] getNewA(int [][] A, List I) {
        int [][] A_ = new int[A.length-I.size()][A.length-I.size()];

        int k=0;

        for (int i=0;i< A.length; i++) {
            int l=0;
            if (I.contains(i))
               continue;
            
           for (int j=0;j< A.length; j++) {
               if (I.contains(j))
                 continue;
              A_[k][l++]= A[i][j];
        
           }
            k++;
        }

        return A_;
    }
}
