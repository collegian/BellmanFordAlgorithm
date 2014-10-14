package graph.components;


import java.util.Collections;
import java.util.List;

import com.google.common.base.Objects;

public final class Vertex 
{
  private char name;
  private Vertex parent;
  private int key;
  
  public static Vertex createVertex(char name)
  {
	Vertex v = new Vertex(name);
	return v;
  }
  
  /**
   * Constructor for Vertex. The parent of each new vertex is null and it's key is max int value.
   * @param name The name of the vertex being constructed.
   */
  private Vertex(char name)
  {
	  this.name=name; 
	  this.parent = null;
	  this.key = Integer.MAX_VALUE;
  }

  public char getName() 
  {
	return name;
  }
  
  public int getKey()
  {
	  return key;
  }
  
  public Vertex getParent()
  {
	  return parent;
  }
  
  public void setParent(Vertex parent)
  {
	  this.parent = parent;
  }
  
  public void setKey(int key)
  {
	  this.key = key;
  }
  
  @Override
  public boolean equals(Object obj) 
   {
		if (obj instanceof Vertex) 
		{
			Vertex other = (Vertex) obj;
			
			return Objects.equal(name, other.name);
		} 
		else 
		{
			return false;
		}
	}
  
    @Override
	public int hashCode() 
    {
		return Objects.hashCode(name);
	}
}
