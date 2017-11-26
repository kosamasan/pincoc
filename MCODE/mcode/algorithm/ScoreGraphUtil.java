/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import mcode.graph.Graph;
import mcode.graph.Vertex;

/**
 *
 * @author home
 */
public class ScoreGraphUtil {
    ParameterSet params;
    
    public ScoreGraphUtil() {
         params= new ParameterSet();
    }
    
  protected Vertex calcNodeInfo(Graph graph, int paramInt)
  {
       
    int[] arrayOfInt2 = graph.neighborsArray(paramInt);
    if (arrayOfInt2.length < 2)
    {
      Vertex localObject = new Vertex();
      if (arrayOfInt2.length == 1)
      {
        ((Vertex)localObject).coreLevel = 1;
        ((Vertex)localObject).coreDensity = 1.0D;
        ((Vertex)localObject).density = 1.0D;
      }
      return localObject;
    }
    Arrays.sort(arrayOfInt2);
    int[] arrayOfInt1;
    if (Arrays.binarySearch(arrayOfInt2, paramInt) < 0)
    {
      arrayOfInt1 = new int[arrayOfInt2.length + 1];
      System.arraycopy(arrayOfInt2, 0, arrayOfInt1, 1, arrayOfInt2.length);
      arrayOfInt1[0] = paramInt;
    }
    else
    {
      arrayOfInt1 = arrayOfInt2;
    }
    
    Graph paramGraph= graph.createGraph(arrayOfInt1);
    Vertex localNodeInfo = new Vertex();
    localNodeInfo.density = calcDensity(paramGraph, this.params.isIncludeLoops());
    localNodeInfo.numNodeNeighbors = arrayOfInt1.length;
    
    Integer localInteger = null;
    Object[] arrayOfObject = getHighestKCore(paramGraph);
    localInteger = (Integer)arrayOfObject[0];
    
    
    localNodeInfo.coreLevel = localInteger.intValue();
    localNodeInfo.coreDensity = calcDensity(paramGraph, this.params.isIncludeLoops());
    localNodeInfo.nodeNeighbors = arrayOfInt1;
    return (Vertex)localNodeInfo;
  }

  public double calcDensity(Graph paramGraph, boolean paramBoolean)
  {
    int i = 0;
    int j = 0;
    int k = 0;
    double d = 0.0D;
    
    if (paramBoolean)
    {
      Iterator localIterator = paramGraph.nodesIterator();
      while (localIterator.hasNext())
      {
        Vertex localNode = (Vertex)localIterator.next();
        if (paramGraph.edgeExists(localNode, localNode))
          k++;
      }
      i = paramGraph.getNodeCount() * paramGraph.getNodeCount();
      j = paramGraph.getEdgeCount() - k;
    }
    else
    {
      i = paramGraph.getNodeCount() * paramGraph.getNodeCount();
      j = paramGraph.getEdgeCount();
    }
    d = (double) j / i;
    return d;
  }
 
 public Object[] getHighestKCore(Graph graph)
  {
  
    int i = 1;
   
    Graph localGraph1 = null;
    Graph localGraph2 = null;
    
    while ((localGraph1 = getKCore(graph, i)) != null)
    {
      graph = localGraph1;
      localGraph2 = localGraph1;
      i++;
    }
    Integer localInteger = new Integer(i - 1);
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = localInteger;
    arrayOfObject[1] = localGraph2;
    return arrayOfObject;
  }
 
 
  public Graph getKCore(Graph paramGraph, int paramInt)
  {
    
    int i = 1;
    Graph localGraph = null;
    
    while (true)
    {
      int j = 0;
      ArrayList localArrayList = new ArrayList(paramGraph.getNodeCount());
      Iterator localIterator1 = paramGraph.nodesIterator();
      while (localIterator1.hasNext())
      {
        Object localObject = (Vertex)localIterator1.next();
        if (paramGraph.getDegree((Vertex)localObject) >= paramInt)
          localArrayList.add(new Integer(((Vertex)localObject).getRootGraphIndex()));
        else
          j++;
      }
      if ((j <= 0) && (i == 0))
        break;
     int [] localObject = new int[localArrayList.size()];
      int k = 0;
      Iterator localIterator2 = localArrayList.iterator();
      while (localIterator2.hasNext())
      {
        localObject[k] = ((Integer)localIterator2.next()).intValue();
        k++;
      }
      localGraph = paramGraph.createGraph(localObject);
      if (localGraph.getNodeCount() == 0)
        return null;
      paramGraph = localGraph;
      if (i != 0)
        i = 0;
    }
    return localGraph;
  }
  
  protected double scoreNode(Vertex paramNodeInfo)
  {
    if (paramNodeInfo.numNodeNeighbors > this.params.getDegreeCutoff())
      paramNodeInfo.score = (paramNodeInfo.coreDensity * paramNodeInfo.coreLevel);
    else
      paramNodeInfo.score = 0.0D;
    return paramNodeInfo.score;
  }
}
