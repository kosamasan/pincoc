/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mcode.algorithm;

/**
 *
 * @author home
 */
public class ParameterSet {
 
  public static String NETWORK = "network";
  public static String SELECTION = "selection";
  private String scope;
  private Integer[] selectedNodes;
  private boolean includeLoops;
  private int degreeCutoff;
  private int kCore;
  private boolean optimize;
  private int maxDepthFromStart;
  private double nodeScoreCutoff;
  private boolean fluff;
  private boolean haircut;
  private double fluffNodeDensityCutoff;
  private int defaultRowHeight;

  public ParameterSet()
  {
    setDefaultParams();
    this.defaultRowHeight = 80;
  }

  public ParameterSet(String paramString, Integer[] paramArrayOfInteger, boolean paramBoolean1, int paramInt1, int paramInt2, boolean paramBoolean2, int paramInt3, double paramDouble1, boolean paramBoolean3, boolean paramBoolean4, double paramDouble2)
  {
    setAllAlgorithmParams(paramString, paramArrayOfInteger, paramBoolean1, paramInt1, paramInt2, paramBoolean2, paramInt3, paramDouble1, paramBoolean3, paramBoolean4, paramDouble2);
    this.defaultRowHeight = 80;
  }

  public void setDefaultParams()
  {
    setAllAlgorithmParams(NETWORK, new Integer[0], false, 2, 2, false, 100, 0.3D, false, true, 0.1D);
  }

  public void setAllAlgorithmParams(String paramString, Integer[] paramArrayOfInteger, boolean paramBoolean1, int paramInt1, int paramInt2, boolean paramBoolean2, int paramInt3, double paramDouble1, boolean paramBoolean3, boolean paramBoolean4, double paramDouble2)
  {
    this.scope = paramString;
    this.selectedNodes = paramArrayOfInteger;
    this.includeLoops = paramBoolean1;
    this.degreeCutoff = paramInt1;
    this.kCore = paramInt2;
    this.optimize = paramBoolean2;
    this.maxDepthFromStart = paramInt3;
    this.nodeScoreCutoff = paramDouble1;
    this.fluff = paramBoolean3;
    this.haircut = paramBoolean4;
    this.fluffNodeDensityCutoff = paramDouble2;
  }

  public ParameterSet copy()
  {
    ParameterSet localMCODEParameterSet = new ParameterSet();
    localMCODEParameterSet.setScope(this.scope);
    localMCODEParameterSet.setSelectedNodes(this.selectedNodes);
    localMCODEParameterSet.setIncludeLoops(this.includeLoops);
    localMCODEParameterSet.setDegreeCutoff(this.degreeCutoff);
    localMCODEParameterSet.setKCore(this.kCore);
    localMCODEParameterSet.setOptimize(this.optimize);
    localMCODEParameterSet.setMaxDepthFromStart(this.maxDepthFromStart);
    localMCODEParameterSet.setNodeScoreCutoff(this.nodeScoreCutoff);
    localMCODEParameterSet.setFluff(this.fluff);
    localMCODEParameterSet.setHaircut(this.haircut);
    localMCODEParameterSet.setFluffNodeDensityCutoff(this.fluffNodeDensityCutoff);
    localMCODEParameterSet.setDefaultRowHeight(this.defaultRowHeight);
    return localMCODEParameterSet;
  }

  public String getScope()
  {
    return this.scope;
  }

  public void setScope(String paramString)
  {
    this.scope = paramString;
  }

  public Integer[] getSelectedNodes()
  {
    return this.selectedNodes;
  }

  public void setSelectedNodes(Integer[] paramArrayOfInteger)
  {
    this.selectedNodes = paramArrayOfInteger;
  }

  public boolean isIncludeLoops()
  {
    return this.includeLoops;
  }

  public void setIncludeLoops(boolean paramBoolean)
  {
    this.includeLoops = paramBoolean;
  }

  public int getDegreeCutoff()
  {
    return this.degreeCutoff;
  }

  public void setDegreeCutoff(int paramInt)
  {
    this.degreeCutoff = paramInt;
  }

  public int getKCore()
  {
    return this.kCore;
  }

  public void setKCore(int paramInt)
  {
    this.kCore = paramInt;
  }

  public void setOptimize(boolean paramBoolean)
  {
    this.optimize = paramBoolean;
  }

  public boolean isOptimize()
  {
    return this.optimize;
  }

  public int getMaxDepthFromStart()
  {
    return this.maxDepthFromStart;
  }

  public void setMaxDepthFromStart(int paramInt)
  {
    this.maxDepthFromStart = paramInt;
  }

  public double getNodeScoreCutoff()
  {
    return this.nodeScoreCutoff;
  }

  public void setNodeScoreCutoff(double paramDouble)
  {
    this.nodeScoreCutoff = paramDouble;
  }

  public boolean isFluff()
  {
    return this.fluff;
  }

  public void setFluff(boolean paramBoolean)
  {
    this.fluff = paramBoolean;
  }

  public boolean isHaircut()
  {
    return this.haircut;
  }

  public void setHaircut(boolean paramBoolean)
  {
    this.haircut = paramBoolean;
  }

  public double getFluffNodeDensityCutoff()
  {
    return this.fluffNodeDensityCutoff;
  }

  public void setFluffNodeDensityCutoff(double paramDouble)
  {
    this.fluffNodeDensityCutoff = paramDouble;
  }

  public int getDefaultRowHeight()
  {
    return this.defaultRowHeight;
  }

  public void setDefaultRowHeight(int paramInt)
  {
    this.defaultRowHeight = paramInt;
  }

  public String toString()
  {
    String str = System.getProperty("line.separator");
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("   Network Scoring:" + str + "      Include Loops: " + this.includeLoops + "  Degree Cutoff: " + this.degreeCutoff + str);
    localStringBuffer.append("   Cluster Finding:" + str + "      Node Score Cutoff: " + this.nodeScoreCutoff + "  Haircut: " + this.haircut + "  Fluff: " + this.fluff + (this.fluff ? "  Fluff Density Cutoff " + this.fluffNodeDensityCutoff : "") + "  K-Core: " + this.kCore + "  Max. Depth from Seed: " + this.maxDepthFromStart + str);
    return localStringBuffer.toString();
  }
}
