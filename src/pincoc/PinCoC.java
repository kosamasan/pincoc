/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pincoc;

import java.util.List;
import pincoc.algorithm.PinCoCAlg;
import pincoc.graph.GraphUploader;

/**
 *
 * @author home
 */
public class PinCoC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //reads data from file to create a Graph instance
        GraphUploader uploader= new GraphUploader ();
        try {
            uploader.readGraph(args[0]);
          //    uploader.readGraph("C:\\personal\\Anto\\2589\\2589\\human.txt");
            
            
            PinCoCAlg alg= new PinCoCAlg();
            
            //invokes the PinCoC algorithm
            //takes as parameter the adjacency Matrix, the number of moves and the possibility P
           List list= alg.PiNCoC(uploader.getAdjacentMatrix(), 100, 0.1);

           
           //at the end of the execution it prints the results - that is the generated clusters
           for (int i=0;i< list.size();i++) {
               List res= (List) list.get(i);
               System.out.println("cluster: "+ i);
               for (int j=0;j< res.size();j++) {
                   int index= ((Integer)res.get(j)).intValue();
                  
                  System.out.print( uploader.getLabel(index)+ " ");
               }   
               System.out.println();
           }
        }catch(Exception e) {e.printStackTrace();}
        
    }
}
