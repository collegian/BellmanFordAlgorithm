package bellmanFord;

import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import graph.Graph;
import graph.components.Edge;
import graph.components.Vertex;

public final class BellmanFordAlgorithm 
{
	private Set<Vertex> vertices;
	private Set<Edge> edges;
	private Vertex source;
	
	public static BellmanFordAlgorithm createBellmanFordAlgorithmInstance(Graph graph,Vertex source)
	{
		return new BellmanFordAlgorithm(graph, source);
	}
	
	private BellmanFordAlgorithm(Graph graph, Vertex source)
	{
		checkNotNull(graph, "graph:null");
		checkNotNull(source, "source:null");
		
		this.vertices=graph.getVertices();
		this.edges=graph.getEdges();
		this.source=source;
	}
	
    public boolean findShortestPath()
    {
    	checkState(vertices!=null && edges!=null,"vertices or edges:null");
    	
		initializeVertices();
		
		// Relax the edges |V|-1 times.
		for(int count = 1; count < vertices.size();count++)
		{
			for(Edge edge:edges)
			{
				relax(edge.getStartVertex(),edge.getEndVertex(),edge.getWeight());
			}
		}
		
		// If after initial sequence of relaxation there are still exist edges which can be relaxed, this implies that
		// a negative edge weight cycle exists and the keys of some vertices will be negative infinity. Hence, no shortest 
		// path is possible from source to such a vertex.
		for(Edge edge:edges)
		{
			if(edge.getEndVertex().getKey() > edge.getStartVertex().getKey() + edge.getWeight())
			{
				return false;
			}
		}
		
    	return true;
    }
    
    /**
	 * The vertex initializes parents to NULL and their keys to INTEGER.MAX_VALUE (the vertex constructor takes care of this).
	 * This simply sets the source key to 0.
	 */
	private void initializeVertices()
	{
		for(Vertex vertex:vertices)
		{
			if(vertex==source)
			{
				vertex.setKey(0);
				break;
			}
		}
	}
	
	private void relax(Vertex u,Vertex v,int edgeWeight)
	{
		// This is required to ensure that infinity == infinity + a very small value.
		if(v.getKey()==Integer.MAX_VALUE && u.getKey()==Integer.MAX_VALUE)
		{
			return;
		}
		
		if(v.getKey() > u.getKey() + edgeWeight)
		{
			v.setKey(u.getKey() + edgeWeight);
			v.setParent(u);
		}
	}
}
