/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pincoc.graph;

import java.util.Hashtable;

/**
 *
 * @author home
 */
public class Graph {
    Hashtable vertices= new Hashtable();
     Hashtable edges= new Hashtable();
    
    public void addVertex(String label) {
       if (!vertices.containsKey(label)) {
           vertices.put(label, new Vertex(label));
       }
        
    }
    
     public void addEdge(String label1, String label2) {
         String key1= label1+ "-"+ label2;
         
       
         edges.put(key1, new Edge(key1));
        
        
    }
     
     public Object[] getVertices() {
        return vertices.values().toArray();
     }
     
      public Object[] getEdges() {
        return edges.values().toArray();
     }
      
      public boolean edgeExists(Vertex x, Vertex y) {
        return ( (edges.get(x.getLabel()+"-"+y.getLabel()) != null) ||
                 (edges.get(y.getLabel()+"-"+x.getLabel()) != null)
                );
             
      }
}
