package homework2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


/**
 * Node 
 * Node represents a node of the graph. Each node is defined by a label (generic).
 * Node has a map of out-going edges and in-coming edges 
 * Node has a color : black or white
 */
public class Node<T> {
	/**
	 * ABSTRACTION FUNCTION
	 * color represents the color of the node : false is black and true is white
	 */

	/**
	 * REPRESENTATION INVARIANT
	 * 
	 */
	private T label;
	private Map<T, Edge<T>> inEdges;
	private Map<T, Edge<T>> outEdges;
	private boolean color; 

	/**
	 * @requires label != null
	 * @modifies this
	 * @effects create a new node with label and color and with no edges 
	 * 					coming of going from it
	 */
	public Node(T label, boolean color) {
		this.color = color;
		this.label = label;
		this.inEdges = new HashMap<T, Edge<T>>();
		this.outEdges = new HashMap<T, Edge<T>>();
		
		this.checkRep();
	}
	
	/**
	 * @return label of the node
	 */
	public T getLabel() {
		return this.label;
	}
	
	/**
	 * @return a list of all incoming edges of the node
	 */
	public List<Edge<T>> getInEdgesList(){
		List<Edge<T>> in = new ArrayList<Edge<T>>(this.inEdges.values());
		return in;
	}

	/**
	 * @return a list of all outgoing edges of the node
	 */
	public List<Edge<T>> getOutEdgesList(){
		List<Edge<T>> out = new ArrayList<Edge<T>>(this.outEdges.values());
		return out;
	}
	
	/**
	 * @return the color of the node
	 */
	public boolean getColor(){
		return this.color;
	}

	/**
	 * @requires edge != null
	 * @modifies this
	 * @effects add the edge to incoming edges of the node
	 */
	public void addInEdge(Edge<T> edge) {
		if(this.inEdges.containsKey(edge.getLabel())) { return;} 
		this.inEdges.put(edge.getLabel(), edge);
	}
	
	/**
	 * @requires edge != null
	 * @modifies this
	 * @effects add the edge to outgoing edges of the node
	 */
	public void addOutEdge(Edge<T> edge) {
		if(this.outEdges.containsKey(edge.getLabel())) { return;} 
		this.outEdges.put(edge.getLabel(), edge);
	}

	/**
	 * @requires none
	 * @modifies none
	 * @returns true if there exist an edge in the incoming edges with edgeLabel
	 */
	public boolean containsInEdge(T edgeLabel) {
		return this.inEdges.containsKey(edgeLabel);
	}

	/**
	 * @requires none
	 * @modifies none
	 * @returns true if there exist an edge in the outgoing edges with edgeLabel
	 */
	public boolean containsOutEdge(T edgeLabel) {
		return this.outEdges.containsKey(edgeLabel);
	}
	
	/**
	 * @requires edge != null
	 * @modifies this
	 * @throws Exception if the edgeLabel doesn't correspond to any incoming edge
	 * @effects remove the edge from the incoming edges of the node
	 */
	public void removeInEdge(T edgeLabel) throws Exception{
		if( this.containsInEdge(edgeLabel) ) {
			this.inEdges.remove(edgeLabel);
		}
		else {
			throw new Exception("Edge doesn't exist in node's inEdges");
		}
	}

	/**
	 * @requires edge != null
	 * @modifies this
	 * @throws Exception if the edgeLabel doesn't correspond to any outgoing edge
	 * @effects remove the edge from the outgoing edges of the node
	 */
	public void removeOutEdge(T edgeLabel) throws Exception{
		if( this.containsOutEdge(edgeLabel)) {
			this.outEdges.remove(edgeLabel);
		}
		else {
			throw new Exception("Edge doesn't exist in node's outEdges");
		}
	}

	/**
	 * @requires none
	 * @modifies none
	 * @throws Exception if the edgeLabel doesn't correspond to any incoming edge
	 * @returns the incoming edge corresponding to the edgeLabel if it exists
	 */
	public Edge<T> getInEdge(T edgeLabel) throws Exception{
		if( this.containsInEdge(edgeLabel) ) {
			return this.inEdges.get(edgeLabel);
		}
		else {
			throw new Exception("Edge doesn't exist in node's inEdges");
		}
	}
	
	/**
	 * @requires none
	 * @modifies none
	 * @throws Exception if the edgeLabel doesn't correspond to any outgoing edge
	 * @returns the outgoing edge corresponding to the edgeLabel if it exists
	 */
	public Edge<T> getOutEdge(T edgeLabel) throws Exception{
		if( this.containsInEdge(edgeLabel) ) {
			return this.outEdges.get(edgeLabel);
		}
		else {
			throw new Exception("Edge doesn't exist in node's outEdges");
		}
	}
	
	
	 private void checkRep() {
			assert label != null;
		}
}
