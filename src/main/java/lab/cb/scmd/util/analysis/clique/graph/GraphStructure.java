
package lab.cb.scmd.util.analysis.clique.graph;
/*
 * Created on 2003/10/26
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author sesejun
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GraphStructure {
//	List vertexList;
//	List edgeList;
	VertexDescriptor root = null;
	/*
	 * isDirectedGraph -- true: directed graph, false: undirected 
	 */
	boolean isDirectedGraph = true;
	/*
	 * allowParallelEdge -- true: allow the insertion of parallel edge, false: don't 
	 */
	boolean allowParallelEdge = false;

	public void setRoot(VertexDescriptor v) {
		root = v;
	}
	
	public VertexDescriptor getRoot() {
		return root;
	}
	
	public void setUndirected() {
		isDirectedGraph = false;
	}
	
	public void setDirected() {
		isDirectedGraph = true;
	}

	public void addVertex(VertexDescriptor v) {
	}
	
	public VertexDescriptor getVertex(VertexDescriptor v) {
		return getVertex(v.getId());
	}

	public VertexDescriptor getVertex(Integer vertexId) {
		return null;
	}


	public static void main(String[] args) {
	}
}
