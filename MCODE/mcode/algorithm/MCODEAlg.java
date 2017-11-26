/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mcode.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import mcode.graph.Graph;
import mcode.graph.Vertex;

public class MCODEAlg
{
  private boolean cancelled = false;
  ParameterSet params;
  private HashMap currentNodeInfoHashMap = null;
  private TreeMap currentNodeScoreSortedMap = null;
  private HashMap nodeScoreResultsMap = new HashMap();
  private HashMap nodeInfoResultsMap = new HashMap();
  
  private long lastScoreTime;
  private long lastFindTime;

  public MCODEAlg()
  {
    params= new ParameterSet();
  }

  public ParameterSet getParams()
  {
    return this.params;
  }
  
   public void scoreGraph(Graph graph, String paramString)
  {
      ScoreGraphUtil scoreGraphUtil= new  ScoreGraphUtil();
    this.params = getParams();
    
   
    long l1 = System.currentTimeMillis();
    HashMap localHashMap = new HashMap(graph.getNodeCount());
    TreeMap localTreeMap = new TreeMap(new Comparator()
    {
      public int compare(Object paramObject1, Object paramObject2)
      {
        double d1 = ((Double)paramObject1).doubleValue();
        double d2 = ((Double)paramObject2).doubleValue();
        if (d1 == d2)
          return 0;
        if (d1 < d2)
          return 1;
        return -1;
      }
    });
    Vertex localNodeInfo = null;
    int i = 0;
    Iterator localIterator = graph.nodesIterator();
    while ((localIterator.hasNext()) && (!this.cancelled))
    {
      Vertex localNode = (Vertex)localIterator.next();
      localNodeInfo = scoreGraphUtil.calcNodeInfo(graph, localNode.getRootGraphIndex());
      localHashMap.put(new Integer(localNode.getRootGraphIndex()), localNodeInfo);
      
      double d = scoreGraphUtil.scoreNode(localNodeInfo);
      
      ArrayList localArrayList;
      if (localTreeMap.containsKey(new Double(d)))
      {
        localArrayList = (ArrayList)localTreeMap.get(new Double(d));
        localArrayList.add(new Integer(localNode.getRootGraphIndex()));
      }
      else
      {
        localArrayList = new ArrayList();
                
        localArrayList.add(new Integer(localNode.getRootGraphIndex()));
        localTreeMap.put(new Double(d), localArrayList);
      }
     
    }
    this.nodeScoreResultsMap.put(paramString, localTreeMap);
    this.nodeInfoResultsMap.put(paramString, localHashMap);
    this.currentNodeScoreSortedMap = localTreeMap;
    this.currentNodeInfoHashMap = localHashMap;
    long l2 = System.currentTimeMillis();
    this.lastScoreTime = (l2 - l1);
  }
 
 
  
  
  public Object[] findClusters(Graph paramGraph, String paramString)
  {
    ClusterGraphUtil clusterGraphUtil= new ClusterGraphUtil();
    
    TreeMap localTreeMap;
    HashMap localHashMap1;
    if (!this.nodeScoreResultsMap.containsKey(paramString))
    {
      localTreeMap = this.currentNodeScoreSortedMap;
      localHashMap1 = this.currentNodeInfoHashMap;
      this.nodeScoreResultsMap.put(paramString, localTreeMap);
      this.nodeInfoResultsMap.put(paramString, localHashMap1);
    }
    else
    {
      localTreeMap = (TreeMap)this.nodeScoreResultsMap.get(paramString);
      localHashMap1 = (HashMap)this.nodeInfoResultsMap.get(paramString);
    }
    this.params = getParams();
   
   
    long l1 = System.currentTimeMillis();
    HashMap localHashMap2 = new HashMap();
    int i = 0;
    int j = 0;
    
    Collection localCollection = localTreeMap.values();
    Object localObject1 = localCollection.iterator();
    ArrayList localArrayList1;
    while (((Iterator)localObject1).hasNext())
    {
      localArrayList1 = (ArrayList)((Iterator)localObject1).next();
      Object localObject2 = localArrayList1.iterator();
      while (((Iterator)localObject2).hasNext())
      {
        ((Iterator)localObject2).next();
        j++;
      }
    }
    localObject1 = new ArrayList();
    Object localObject2 = localCollection.iterator();
    Object localObject4;
    while (((Iterator)localObject2).hasNext())
    {
      localArrayList1 = (ArrayList)((Iterator)localObject2).next();
      for (int k = 0; k < localArrayList1.size(); k++)
      {
        Integer localInteger = (Integer)localArrayList1.get(k);
        if (!localHashMap2.containsKey(localInteger))
        {
         Cluster localMCODECluster1 = new Cluster();
          localMCODECluster1.setSeedNode(localInteger);
          HashMap localHashMap3 = new HashMap((HashMap)localHashMap2.clone());
          ArrayList localArrayList2 = clusterGraphUtil.getClusterCore(localInteger, localHashMap2, this.params.getNodeScoreCutoff(), this.params.getMaxDepthFromStart(), localHashMap1);
          if (localArrayList2.size() > 0)
          {
            if (!localArrayList2.contains(localInteger))
              localArrayList2.add(localInteger);
            
            localObject4 = paramGraph.createGraph(localArrayList2);
            
            if (!clusterGraphUtil.filterCluster((Graph)localObject4))
            {
              if (this.params.isHaircut())
                clusterGraphUtil.haircutCluster((Graph)localObject4, localArrayList2,  paramGraph);
              if (this.params.isFluff())
                clusterGraphUtil.fluffClusterBoundary(localArrayList2, localHashMap2, localHashMap1);
              localMCODECluster1.setALCluster(localArrayList2);
              localObject4 = paramGraph.createGraph(localArrayList2);
              localMCODECluster1.setGPCluster((Graph)localObject4);
              localMCODECluster1.setClusterScore(clusterGraphUtil.scoreCluster(localMCODECluster1));
              localMCODECluster1.setNodeSeenHashMap(localHashMap3);
              localMCODECluster1.setResultTitle(paramString);
              ((ArrayList)localObject1).add(localMCODECluster1);
            }
          }
        }
        
      }
    }
    localObject2 = new ArrayList();
    if (!this.params.getScope().equals(ParameterSet.NETWORK))
    {
      Object localObject3 = ((ArrayList)localObject1).iterator();
      while (((Iterator)localObject3).hasNext())
      {
        Cluster localMCODECluster2 = (Cluster)((Iterator)localObject3).next();
        ArrayList localArrayList3 = localMCODECluster2.getALCluster();
        localObject4 = new ArrayList();
        for (int i2 = 0; i2 < this.params.getSelectedNodes().length; i2++)
          ((ArrayList)localObject4).add(this.params.getSelectedNodes()[i2]);
        int i2 = 0;
        Iterator localIterator = ((ArrayList)localObject4).iterator();
        while (localIterator.hasNext())
        {
          if (!localArrayList3.contains((Integer)localIterator.next()))
            continue;
          i2 = 1;
        }
        if (i2 != 0)
          ((ArrayList)localObject2).add(localMCODECluster2);
      }
      localObject1 = localObject2;
    }
    Object[] localObject3 = new Cluster[((ArrayList)localObject1).size()];
    for (int n = 0; n < localObject3.length; n++)
      localObject3[n] = ((Cluster)((ArrayList)localObject1).get(n));
    long l2 = System.currentTimeMillis();
    this.lastFindTime = (l2 - l1);
    return localObject3;
  }
          
}