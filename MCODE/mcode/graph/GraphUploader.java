/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mcode.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.StringTokenizer;

/**
 *
 * @author home
 */
public class GraphUploader {
    Graph graph;
    int [][] adjacentMatrix;
    
        
    public void readGraph(String file) throws Exception {
      graph = new Graph();
        int vindex=1;
        
     BufferedReader  bfr= new BufferedReader (new FileReader(file));
     while (bfr.ready()) {
         
         //read a line from the file: the line contains a node A and a node B
         String line= bfr.readLine();

        

         StringTokenizer str= new StringTokenizer(line, " \t");

         String vertXStr= str.nextToken();
         //add node to List of Vertices if this node has not been added already
         vindex= graph.addVertex(vertXStr, vindex);
         
          String vertYStr= str.nextToken();
          //add node to List of Vertices if this node has not been added already
           vindex= graph.addVertex(vertYStr, vindex);
           
          //add edge for node A and node B
          graph.addEdge(vertXStr, vertYStr);
     }
     
     //uses the vertice and edge data to build the Adjacency Matrix
     adjacentMatrix= buildAdjacencyMatrix(graph);
     
           
    }
   
   int [][] buildAdjacencyMatrix(Graph graph) {
       Object[] vertices= graph.getVertices();
       int [][] adjacentMatrix= new int[vertices.length][vertices.length];
       
       for (int i=0;i< vertices.length; i++) {
           for (int j=0;j< vertices.length; j++) {
               if (i==j)
                   adjacentMatrix[i][j]=1;
               else {
                   Vertex x= (Vertex) vertices[i];
                   Vertex y= (Vertex) vertices[j];
                   
                   //check in the list of Edges if there is an edge between node X and Y or between Y and X
                   
                   if (graph.edgeExists(x, y)) {
                      
                       adjacentMatrix[i][j]=1;
                       adjacentMatrix[j][i]=1;
                   }
               }
                   
           }
       }
       
       return adjacentMatrix;
   }
    
   public int [][] getAdjacentMatrix() {
       return adjacentMatrix;
   }
   
   public Graph getGraph() {return this.graph;}
   
   public String getLabel (int index) {
       Vertex v= (Vertex) graph.getVertices()[index];
       return v.getLabel();
   }
   
   public void print() {
       for (int i=0;i< adjacentMatrix.length; i++) 
           for (int j=0;j< adjacentMatrix.length; j++) {
               System.out.print(i + "-"+ j + " = "+ adjacentMatrix[i][j] + " ");
               if (j== adjacentMatrix.length -1)
                   System.out.println();
           }
   }
}
