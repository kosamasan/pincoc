/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mcode.graph;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author home
 */
public class Graph {
    Hashtable vertices= new Hashtable();
     Hashtable edges= new Hashtable();
    
    public int addVertex(String label, int index) {
       if (!vertices.containsKey(label)) {
           vertices.put(label, new Vertex(label, index));
           index++;
       }
        return index;
    }
    
     public void addEdge(String label1, String label2) {
         String key1= label1+ "-"+ label2;
         
       
         edges.put(key1, new Edge(key1));
        
        
    }
     
     public Iterator nodesIterator () {
         return vertices.values().iterator();
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
      
      public int [] neighborsArray(int index) {
            Object[] vertices= getVertices();
             List list= new ArrayList();
             Vertex x= (Vertex) this.getVertex(index);
             
             for (int i=0;i< vertices.length; i++) {
                                     
                   Vertex y= (Vertex) vertices[i];
                   if (x==y)
                       continue;
                   
                   //check in the list of Edges if there is an edge between node X and Y or between Y and X
                   
                    if (edgeExists(x, y))
                        list.add(y.getRootGraphIndex());
              }
             
             int [] nArray= new int[list.size()];
             for (int i=0;i< list.size();i++) {
                 nArray[i]= ((Integer) list.get(i)).intValue();
             }
             return nArray;
      }
      
      public int getNodeCount () {
          return vertices.size();
      }
      
      public int getEdgeCount () {
          return edges.size();
      }
      
      
      public Vertex getVertex(int index) {
          Iterator iterator = vertices.values().iterator();
          while (iterator.hasNext()) {
              Vertex v= (Vertex) iterator.next();
              if (v.getRootGraphIndex() == index) 
                  return v;
          }
          return null;
      }
      
       public Graph createGraph(ArrayList list ) {
           int [] nodes= new int[list.size()];
           for (int i=0;i< list.size();i++) {
               Integer index= (Integer) list.get(i);
               nodes[i]= index.intValue();
           }
           return  createGraph(nodes);
       }
       
      public Graph createGraph(int [] nodes) {
          Graph graph= new Graph();
          
           for (int i=0;i< nodes.length;i++) {
               Vertex v= this.getVertex(nodes[i]);
               graph.addVertex(v.getLabel(), v.getRootGraphIndex());
           }
           
           for (int i=0;i< nodes.length;i++) {
               for (int j=0;j< nodes.length;j++) {
                   if (i==j)
                       continue;
                   Vertex x= this.getVertex(nodes[i]);
                   Vertex y= this.getVertex(nodes[j]);
                   
                   if (this.edgeExists(x, y))
                       graph.addEdge(x.getLabel(), y.getLabel());
               }    
           }
           return graph;
      }
      
   public int getDegree(Vertex v) {
       int index= v.getRootGraphIndex();
        int d=0;
       Iterator iterator= vertices.values().iterator();
        while (iterator.hasNext()) {
              Vertex y= (Vertex) iterator.next();
              if (y.getRootGraphIndex() == index) 
                  continue;
              if (this.edgeExists(v, y))
                  d++;
       }
        return d;
   }
   
   public int [] getNodeIndicesArray() {
        int [] nodes= new int[this.vertices.size()];
        int i=0;
         Iterator iterator= vertices.values().iterator();
          while (iterator.hasNext()) {
              Vertex y= (Vertex) iterator.next();
              nodes[i++]= y.getRootGraphIndex();
          }    
          
       return nodes;
   }
}
