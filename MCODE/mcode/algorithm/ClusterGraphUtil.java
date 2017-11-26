/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mcode.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import mcode.graph.Graph;
import mcode.graph.Vertex;

/**
 *
 * @author home
 */
public class ClusterGraphUtil {
    
     ParameterSet params;
    
    public ClusterGraphUtil() {
         params= new ParameterSet();
    }
    
  protected ArrayList getClusterCore(Integer paramInteger, HashMap paramHashMap1, double paramDouble, int paramInt, HashMap paramHashMap2)
  {
    ArrayList localArrayList = new ArrayList();
    getClusterCoreInternal(paramInteger, paramHashMap1, ((Vertex)paramHashMap2.get(paramInteger)).score, 1, localArrayList, paramDouble, paramInt, paramHashMap2);
    return localArrayList;
  }

  private boolean getClusterCoreInternal(Integer paramInteger, HashMap paramHashMap1, double paramDouble1, int paramInt1, ArrayList paramArrayList, double paramDouble2, int paramInt2, HashMap paramHashMap2)
  {
    if (paramHashMap1.containsKey(paramInteger))
      return true;
    paramHashMap1.put(paramInteger, new Boolean(true));
    if (paramInt1 > paramInt2)
      return true;
    int i = 0;
    for (i = 0; i < ((Vertex)paramHashMap2.get(paramInteger)).numNodeNeighbors; i++)
    {
      Integer localInteger = new Integer(((Vertex)paramHashMap2.get(paramInteger)).nodeNeighbors[i]);
      if ((paramHashMap1.containsKey(localInteger)) || (((Vertex)paramHashMap2.get(localInteger)).score < paramDouble1 - paramDouble1 * paramDouble2))
        continue;
      if (!paramArrayList.contains(localInteger))
        paramArrayList.add(localInteger);
      getClusterCoreInternal(localInteger, paramHashMap1, paramDouble1, paramInt1 + 1, paramArrayList, paramDouble2, paramInt2, paramHashMap2);
    }
    return true;
  }
  
 protected boolean filterCluster(Graph paramGraph)
  {
    if (paramGraph == null)
      return true;
    
     ScoreGraphUtil scoreG= new ScoreGraphUtil();
    Graph localGraph = scoreG.getKCore(paramGraph, this.params.getKCore());
    return localGraph == null;
  }

  protected boolean haircutCluster(Graph paramGraph1, ArrayList paramArrayList, Graph paramGraph2)
  {
      ScoreGraphUtil scoreG= new ScoreGraphUtil();
    Graph localGraph = scoreG.getKCore(paramGraph1, 2);
    if (localGraph != null)
    {
      paramArrayList.clear();
      int[] arrayOfInt = localGraph.getNodeIndicesArray();
      for (int i = 0; i < arrayOfInt.length; i++)
        paramArrayList.add(new Integer(arrayOfInt[i]));
    }
    return true;
  }
  
 protected boolean fluffClusterBoundary(ArrayList paramArrayList, HashMap paramHashMap1, HashMap paramHashMap2)
  {
    int i = 0;
    int j = 0;
    ArrayList localArrayList = new ArrayList();
    HashMap localHashMap = new HashMap();
    for (int k = 0; k < paramArrayList.size(); k++)
    {
      i = ((Integer)paramArrayList.get(k)).intValue();
      for (int m = 0; m < ((Vertex)paramHashMap2.get(new Integer(i))).numNodeNeighbors; m++)
      {
        j = ((Vertex)paramHashMap2.get(new Integer(i))).nodeNeighbors[m];
        if ((paramHashMap1.containsKey(new Integer(j))) || (localHashMap.containsKey(new Integer(j))) || (((Vertex)paramHashMap2.get(new Integer(j))).density <= this.params.getFluffNodeDensityCutoff()))
          continue;
        localArrayList.add(new Integer(j));
        localHashMap.put(new Integer(j), new Boolean(true));
      }
    }
    if (localArrayList.size() > 0)
      paramArrayList.addAll(localArrayList.subList(0, localArrayList.size()));
    return true;
  }
 
  public double scoreCluster(Cluster paramMCODECluster)
  {
    int i = 0;
    double d1 = 0.0D;
    double d2 = 0.0D;
    i = paramMCODECluster.getGPCluster().getNodeCount();
    
    ScoreGraphUtil scoreG= new ScoreGraphUtil();
    
    d1 = scoreG.calcDensity(paramMCODECluster.getGPCluster(), true);
    d2 = d1 * i;
    return d2;
  }
}
