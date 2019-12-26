package homework2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


/**
 * This class implements a Bipartite Tree composed of White nodes and 
 * Black nodes. Different nodes can be linked by edges, but there is no edges 
 * that links between same color nodes. Each edge or node can have a  label 
 * associated to it. 
 * Two edges that enters a same node have different labels, idem for two edges 
 * that out a same node. 
 */
public class BipartiteGraph<T> {

	private Map<T, Node<T>> nodes;
	private T name;
	/**
	 * ABSTRACTION FUNCTION
	 * 
	 */

	/**
	 * REPRESENTATION INVARIANT
	 * 
	 */
	
	/**
	 * @requires none
	 * @modifies this
	 * @effects creates an empty graph
	 */
	public BipartiteGraph(T graphName) {
		this.nodes = new HashMap<T, Node<T>>();
		this.name = graphName;
		this.checkRep();
	}
	
	/**
	 * @requires none
	 * @modifies none
	 * @returns the name of the graph
	 */
	public T getGraphName() {
		return this.name;
	}
	
	/**
	 * @requires none
	 * @modifies none
	 * @returns a list containing all the nodes in the graph
	 */
	public List<T> getNodes(){
		List<T> result =  new ArrayList<T>();
		Collection<Node<T>> values = this.nodes.values();
		for(Node<T> node : values) {
			result.add(node.getLabel());
		}
		return result;
	}
	
	/**
	 * @requires nodeLabel != null
	 * @modifies this
	 * @effects adds the node to the graph
	 * @throws Exception if the nodeLabel already exists in the graph
	 */
	public void addNode(T nodeLabel, boolean color) throws Exception{
		if(this.nodes.containsKey(nodeLabel)) {
			throw new Exception("nodeLabel already exists in the graph");
		}
		Node<T> value = new Node<T>(nodeLabel, color);
		 this.nodes.put(nodeLabel, value);
	}
	
	/**
	 * @requires nodeLabel != null
	 * @modifies this
	 * @effects remove the node corresponding to nodeLabel from the graph
	 * @throws Exception if there is no node corresponding in the graph
	 */
	public void removeNode(T nodeLabel) throws Exception{
		if(this.nodes.containsKey(nodeLabel)) {
			this.nodes.remove(nodeLabel);
		}
		else {
			throw new Exception("nodeLabel doesn't exist in the graph");
		}
	}
	
	/**
	 * @requires edge != null
	 * @modifies this
	 * @effects add the edge to the graph 
	 * @throws Exception if one of the labels of the edge is not a node in the graph
	 * 									or if the edge already exists
	 * 									or if the edge is not legal (ie, links between two nodes of same color)
	 * 
	 */
	public void addEdge(T edgeLabel, T parentLabel, T childLabel) throws Exception{
		if(!this.nodes.containsKey(parentLabel) || !this.nodes.containsKey(childLabel)) {
			throw new Exception("nodeLabel doesn't exist in the graph");
		}
		Node<T> parentNode = this.getNodeFromLabel(parentLabel);
		Node<T> childNode = this.getNodeFromLabel(childLabel);
		if( parentNode.containsOutEdge(edgeLabel) || childNode.containsInEdge(edgeLabel) ) {
			throw new Exception("Edge already exists in the graph");
		}
		if( parentNode.getColor() == childNode.getColor() ) {
			throw new Exception("Edge is not legal");
		}
		
		Edge<T> edge = new Edge<T>(edgeLabel, parentLabel, childLabel);
		parentNode.addOutEdge(edge);
		childNode.addInEdge(edge);
	}
	
	/**
	 * @requires nodeLabel exists in the graph
	 * @modifies none
	 * @returns The node corresponding to the nodeLabel
	 */
	private Node<T> getNodeFromLabel(T nodeLabel){
		Collection<Node<T>> values =this.nodes.values();
		for(Node<T> node : values) {
			if(node.getLabel() == nodeLabel) {
				return node;
			}
		}
		return null;
	}
	
	/**
	 * @requires node != null
	 * @modifies this
	 * @effects remove the edge from the graph
	 * @throws Exception if there is no edge corresponding in the graph
	 */
	public void removeEdge(T edgeLabel, T parentLabel, T childLabel) throws Exception{
		if(!this.nodes.containsKey(parentLabel) || !this.nodes.containsKey(childLabel)) {
			throw new Exception("nodeLabel doesn't exist in the graph");
		}
		Node<T> parentNode = this.getNodeFromLabel(parentLabel);
		Node<T> childNode = this.getNodeFromLabel(childLabel);
		if(parentNode.containsOutEdge(edgeLabel)==false || childNode.containsInEdge(edgeLabel)==false) {
			throw new Exception("Edge doesn't exist in the graph");
		}
		parentNode.removeOutEdge(edgeLabel);
		childNode.removeInEdge(edgeLabel);
	}
	
	/**
	 * @requires none
	 * @modifies none
	 * @returns a list containing all the nodes of the corresponding color in the graph
	 */
	public List<T> getColorNodes(boolean color){
		Collection<Node<T>> values = this.nodes.values();
		List<T> result = new ArrayList<T>();
		for(Node<T> node : values) {
			if(node.getColor() == color) {
				result.add(node.getLabel());
			}
		}
		return result;
	}
	
	/**
	 * @requires nodeLabel != null
	 * @modifies none
	 * @returns a list containing all the children of the node corresponding to the label
	 * @throws Exception if the nodeLabel doesn't exist in the graph
	 */
	public List<T> getChildrens(T nodeLabel) throws Exception{
		if(this.nodes.containsKey(nodeLabel) == false) {
			throw new Exception("nodeLabel doesn't exist in the graph");
		}
		Node<T> node = this.getNodeFromLabel(nodeLabel);
		List<Edge<T>> edges = node.getOutEdgesList();
		List<T> result = new ArrayList<T>();
		for( Edge<T> edge : edges) {
			result.add(edge.getChildLabel());
		}
		return result;
	}
	
	/**
	 * @requires nodeLabel !=  null
	 * @modifies none
	 * @returns a list containing all the parents of the node corresponding to the label
	 * @throws Exception if the nodeLabel doesn't exist in the graph
	 */
	public List<T> getParents(T nodeLabel) throws Exception{
		if(this.nodes.containsKey(nodeLabel) == false) {
			throw new Exception("nodeLabel doesn't exist in the graph");
		}
		Node<T> node = this.getNodeFromLabel(nodeLabel);
		List<Edge<T>> edges = node.getInEdgesList();
		List<T> result = new ArrayList<T>();
		for( Edge<T> edge : edges) {
			result.add(edge.getParentLabel());
		}
		return result;
	}	
	
	/**
	 * @requires nodeLabel !=  null
	 * @modifies none
	 * @returns the color of the corresponding node
	 * @throws Exception if the nodeLabel doesn't exist in the graph
	 */	
	public boolean getNodeColor(T nodeLabel) throws Exception{
		if( this.nodes.containsKey(nodeLabel) == false ) {
			throw new Exception("nodeLabel doesn't exist in the graph");
		}
		return this.getNodeFromLabel(nodeLabel).getColor();
	}
	
	/**
	 * @requires nodeLabel !=  null
	 * @modifies none
	 * @returns a list of all the edges that enter the given node
	 * @throws Exception if the nodeLabel doesn't exist in the graph
	 */
	public List<T> getNodesInEdges(T nodeLabel) throws Exception{
		if(this.nodes.containsKey(nodeLabel) == false) {
			throw new Exception("nodeLabel doesn't exist in the graph");
		}
		Node<T> node = this.getNodeFromLabel(nodeLabel);
		List<Edge<T>> edges = node.getInEdgesList();
		List<T> result = new ArrayList<T>();
		for( Edge<T> edge : edges) {
			result.add(edge.getLabel());
		}
		return result;
	}

	/**
	 * @requires nodeLabel !=  null
	 * @modifies none
	 * @returns a list of all the edges that out the given node
	 * @throws Exception if the nodeLabel doesn't exist in the graph
	 */
	public List<T> getNodesOutEdges(T nodeLabel) throws Exception{
		if(this.nodes.containsKey(nodeLabel) == false) {
			throw new Exception("nodeLabel doesn't exist in the graph");
		}
		Node<T> node = this.getNodeFromLabel(nodeLabel);
		List<Edge<T>> edges = node.getOutEdgesList();
		List<T> result = new ArrayList<T>();
		for( Edge<T> edge : edges) {
			result.add(edge.getLabel());
		}
		return result;
	}
	
	// getChildEdge(T edgeLabel, T parentLabel) getParentEdge(T edgeLabel, T childLabel)
	
	private void checkRep() {
		assert this.nodes != null;
		assert this.name != null;
	}
}
