package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import graph.Graph;
import graph.components.Edge;
import graph.components.Vertex;

import org.junit.Before;
import org.junit.Test;

import bellmanFord.BellmanFordAlgorithm;

import com.google.common.collect.Sets;

public final class BellmanFordOpsTest 
{
	 private Graph graph;
	 private Vertex source;
	 private BellmanFordAlgorithm bellmanFordAlgorithm;
	 private Map<Vertex,Vertex> parentByVertex;
	 
   @Before
   public void setup()
   {
	   Vertex s = Vertex.createVertex('s');
		 Vertex t = Vertex.createVertex('t');
		 Vertex x = Vertex.createVertex('x');
		 Vertex y = Vertex.createVertex('y');
		 Vertex z = Vertex.createVertex('z');
		 source = s;
		 
		 Edge st = Edge.createEdge(s, t, 6);
		 Edge sy = Edge.createEdge(s, y, 7);
		 Edge yx = Edge.createEdge(y, x, -3);
		 Edge yz = Edge.createEdge(y, z, 9);
		 Edge ty = Edge.createEdge(t, y, 8);
		 Edge tx = Edge.createEdge(t, x, 5);
		 Edge tz = Edge.createEdge(t, z, -4);
		 Edge xt = Edge.createEdge(x, t, -2);
		 Edge zs = Edge.createEdge(z, s, 2);
		 Edge zx = Edge.createEdge(z, x, 7);
		 
		 graph = Graph.createGraph(Sets.newHashSet(s,t,x,y,z), Sets.newHashSet(st,sy,yx,yz,ty,tx,tz,xt,zs,zx));
		 bellmanFordAlgorithm = BellmanFordAlgorithm.createBellmanFordAlgorithmInstance(graph, source);
		 
		 parentByVertex =  new HashMap<Vertex,Vertex>();
		  parentByVertex.put(y, s);
		  parentByVertex.put(t, x);
		  parentByVertex.put(x, y);
		  parentByVertex.put(z, t);
   }
   
    @Test
	  public void testBellmanFordAlgorithm()
	  {
		boolean doesShortestPathExist = bellmanFordAlgorithm.findShortestPath();
		assertTrue("The graph has a negative edge weight cycle.",doesShortestPathExist);
		
		for(Vertex vertex : graph.getVertices())
		{
			if(vertex==source)
			{
				continue;
			}

			System.out.println("There exists and edge between:" + vertex.getParent().getName() + " and:" + vertex.getName());
			
			Vertex parent = parentByVertex.get(vertex);
			assertEquals("Expected parent vertex not the same as actual parent vertex",parent,vertex.getParent());
		}
	  }
   
      @Test
	  public void testBellmanFordAlgorithmWithNegativeEdgeWeightCycle()
	  {
    	 Vertex src = Vertex.createVertex('s');
 		 Vertex a = Vertex.createVertex('a');
 		 Vertex b = Vertex.createVertex('b');
 		 Vertex c = Vertex.createVertex('c');
 		 Vertex d = Vertex.createVertex('d');
 		 Vertex e = Vertex.createVertex('e');
 		 Vertex f = Vertex.createVertex('f');
 		 Vertex g = Vertex.createVertex('g');
 		 
 		 Edge sa = Edge.createEdge(src, a, 3);
		 Edge sc = Edge.createEdge(src, c, 5);
		 Edge se = Edge.createEdge(src, e, 2);
		 Edge ab = Edge.createEdge(a, b, -4);
		 Edge cd = Edge.createEdge(c, d, 6);
		 Edge dc = Edge.createEdge(d, c, -3);
		 Edge dg = Edge.createEdge(d, g, 8);
		 Edge bg = Edge.createEdge(b, g, 4);
		 Edge ef = Edge.createEdge(e, f, 3);
		 Edge fe = Edge.createEdge(f, e, -6);
		 Edge fg = Edge.createEdge(f, g, 7);
		 
		 Graph graphWithNegativeEdges = Graph.createGraph(Sets.newHashSet(src,a,b,c,d,e,f,g), Sets.newHashSet(sa,sc,se,ab,cd,dc,dg,bg,ef,fe,fg));
		 BellmanFordAlgorithm bellmanFordAlgorithmWithNegativeEdges = BellmanFordAlgorithm.createBellmanFordAlgorithmInstance(graphWithNegativeEdges, src);
		 
		 parentByVertex =  new HashMap<Vertex,Vertex>();
		  parentByVertex.put(a, src);
		  parentByVertex.put(c, src);
		  parentByVertex.put(b, a);
		  parentByVertex.put(d, c);
 		 
		boolean doesShortestPathExist = bellmanFordAlgorithmWithNegativeEdges.findShortestPath();
		assertFalse("The graph doesn't have a negative edge weight cycle.",doesShortestPathExist);
		
		for(Vertex vertex : graphWithNegativeEdges.getVertices())
		{
			if(vertex==src || vertex==e || vertex==f || vertex==g)
			{
				continue;
			}

			System.out.println("There exists and edge between:" + vertex.getParent().getName() + " and:" + vertex.getName());
			
			Vertex parent = parentByVertex.get(vertex);
			assertEquals("Expected parent vertex not the same as actual parent vertex",parent,vertex.getParent());
		}
	  }
}
