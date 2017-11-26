/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mcode;

import java.util.ArrayList;
import mcode.algorithm.Cluster;
import mcode.algorithm.MCODEAlg;
import mcode.graph.GraphUploader;

/**
 *
 * @author home
 */
public class MCODE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //reads data from file to create a Graph instance
        GraphUploader uploader= new GraphUploader ();
        try {
            uploader.readGraph(args[0]);
          //    uploader.readGraph("C:\\personal\\Anto\\2589\\2589\\human.txt");
            
            MCODEAlg alg= new MCODEAlg();
            alg.scoreGraph(uploader.getGraph(), "MCODE");
          Object [] clusters=  alg.findClusters(uploader.getGraph(), "MCODE");
          for (int i=0;i< clusters.length;i++) {
              Cluster cluster= (Cluster) clusters[i];
              System.out.println("Cluster -"+ i);
              ArrayList list= cluster.getALCluster();
              for (int j=0;j< list.size(); j++) 
                   System.out.print (list.get(j).toString() + " ");
              System.out.println();
          }
               System.out.println();
          
        }catch(Exception e) {e.printStackTrace();}
        
    }
}
