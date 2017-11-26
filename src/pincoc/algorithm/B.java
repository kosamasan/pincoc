/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pincoc.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author home
 */
public class B {


    List index = new ArrayList();
    int [][] A;
    double quality;

    public B(int [][] A, int i) {
        //transforms an existing instance of B by adding a new row/column
        index.add(i);
          this.A= A;
    }

    public B(int [][] A, List list, int i) {
        //transforms an existing instance of B by adding a set of rows/columns
        
        for (int k=0;k< list.size();k++)
            index.add(list.get(k));
                    
        index.add(i);
          this.A= A;
    }
    
    
    
    public B(int [][] A) {
        //generates a new instance by selecting a random row/column of A
        
          Random r= new Random(); 
          int i= r.nextInt(A.length-1);

          this.index.add(i);
          this.A= A;
    }

    public B flip_move(double quality) throws Exception {
        //performs a flip_move - try to find a row/column of A that improves quality
        
        B temp= this;

        for (int j=0;j< A.length;j++) {
            if (index.contains(j))
                continue;
            
            B B_ = new B(A, index, j);
            
            if (B_.Q()> quality) {
                 quality= B_.quality;
                 temp= B_;
                 return temp;
             }
        }
           return this;

    }

   
       

   public void remove_min() {
       //removes a row/column of Bi that causes the minimum decrease in the quality value
       
       List qual_diff= new ArrayList();
       double cur_quality= this.Q();
       double min= 1000000;
       int index_to_remove=-1;
       
         for (int k=0;k< index.size();k++) {
            Object o= index.remove(k);
            double new_quality= this.Q();
            if (min > cur_quality - new_quality) {
                min= cur_quality - new_quality;
                index_to_remove= k;
            }
            index.add(k, o);
         }   
         
         if (index_to_remove>-1)
           index.remove(index_to_remove);
       
    }

  
   
   
    
   
    public double Q() {
        //calculates the quality value
        
         quality= MrB() * vB();
        return quality;
     }
    
    int vB() {
        //returns the volume of 1s in the Bi rows/columns
        
        int sum=0;

        for (int k=0;k< index.size();k++) {
            int l= ((Integer)index.get(k)).intValue();
            for (int i=0;i< A.length; i++) 
                sum += A[i][l];
        }  

        return sum;
    }  
    
  
    
    double MrB() {
        //returns the power mean of 1s in the Bi rows/columns - r=2
        
        double total_sum=0.0;
        
        for (int k=0;k< index.size();k++) {
            int l= ((Integer)index.get(k)).intValue();
            
            double sum=0.0;

            for (int i=0;i< A.length; i++)
               sum += A[i][l];

              sum= Math.pow(sum, 2);
              total_sum += sum/ A.length;
              
              
        }    
        return total_sum / index.size();
    }
    
  
    
   

   
      

   
}
