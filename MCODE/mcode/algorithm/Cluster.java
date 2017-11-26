/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mcode.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import mcode.graph.Graph;

/**
 *
 * @author home
 */
public class Cluster {
  private ArrayList alCluster = null;
  private Graph gpCluster = null;
  
  private Integer seedNode;
  private HashMap nodeSeenHashMap;
  private double clusterScore;
  private String clusterName;
  private int rank;
  private String resultTitle;

  public String getResultTitle()
  {
    return this.resultTitle;
  }

  public void setResultTitle(String paramString)
  {
    this.resultTitle = paramString;
  }

  public String getClusterName()
  {
    return this.clusterName;
  }

  public void setClusterName(String paramString)
  {
    this.clusterName = paramString;
  }

  

  public double getClusterScore()
  {
    return this.clusterScore;
  }

  public void setClusterScore(double paramDouble)
  {
    this.clusterScore = paramDouble;
  }

  public Graph getGPCluster()
  {
    return this.gpCluster;
  }

  public void setGPCluster(Graph paramGraph)
  {
    this.gpCluster = paramGraph;
  }

  public ArrayList getALCluster()
  {
    return this.alCluster;
  }

  public void setALCluster(ArrayList paramArrayList)
  {
    this.alCluster = paramArrayList;
  }

  public Integer getSeedNode()
  {
    return this.seedNode;
  }

  public void setSeedNode(Integer paramInteger)
  {
    this.seedNode = paramInteger;
  }

  public HashMap getNodeSeenHashMap()
  {
    return this.nodeSeenHashMap;
  }

  public void setNodeSeenHashMap(HashMap paramHashMap)
  {
    this.nodeSeenHashMap = paramHashMap;
  }

  public int getRank()
  {
    return this.rank;
  }

  public void setRank(int paramInt)
  {
    this.rank = paramInt;
    this.clusterName = ("Cluster " + (paramInt + 1));
  }
}
