package homework2;


/**
 * Edge 
 * Edge represents an edge of the graph, linking between two nodes
 * Edge has a label (generic)
 * Edge has a parentNode and a childNode (defines its direction)
 * 
 */
public class Edge<T> {

	private T edgeLabel;
	private T parentLabel;
	private T childLabel;
	
	/**
	 * @requires edgeLabel != null, parentLabel != null, childLabel != null
	 * @modifies this
	 * @effects create a new edge with label edgeLabel, linking between node with parentLabel 
	 * 					and childLabel
	 */
	public Edge(T edgeLabel, T parentLabel, T childLabel) {
		this.edgeLabel = edgeLabel;
		this.parentLabel = parentLabel;
		this.childLabel = childLabel;
		this.checkRep();
	}
	
	/**
	 * @requires none
	 * @modifies none
	 * @effects none
	 * @return Label of the edge
	 */
	public T getLabel() {
		return this.edgeLabel;
	}

	/**
	 * @requires none
	 * @modifies none
	 * @effects none
	 * @return Label of the parent of the edge
	 */
	public T getParentLabel() {
		return this.parentLabel;
	}
	
	/**
	 * @requires none
	 * @modifies none
	 * @effects none
	 * @return Label of the child of the edge
	 */
	public T getChildLabel() {
		return this.childLabel;
	}
	
	 private void checkRep() {
			assert edgeLabel != null : "Label must be non null";
		  assert parentLabel != null : "Label must be non null";
			assert childLabel != null : "Label must be non null";
		}
}

