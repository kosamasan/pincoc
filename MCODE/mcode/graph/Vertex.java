/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mcode.graph;

/**
 *
 * @author home
 */
public class Vertex {
    String label;
    int index;
    public double density = 0.0D;
   public int numNodeNeighbors = 0;
   public int[] nodeNeighbors;
   public int coreLevel = 0;
   public double coreDensity = 0.0D;
  public  double score;
    
    public Vertex(String lbl, int i) {
        this.label= lbl;
        this.index= i;
    }
   
     public Vertex() {
        
    }
     
    public String getLabel() {
        return label;
    }
    
    public int getRootGraphIndex() {
        return index;
    }
}
