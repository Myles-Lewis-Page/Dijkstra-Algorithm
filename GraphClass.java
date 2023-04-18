import java.util.LinkedList;
import java.util.Queue;
  
public class GraphClass {

	public static final int MaxDistance = 32000;
	Vertex[] vertices;
	Edge[] edges;
	int edgeCount;
	int maxEdge, maxVert;

	public static void main(String[] args) {
		int index;
		GraphClass myGraph = new GraphClass(4, 5);
		
		//makes the starting vertex
		index = myGraph.AddVertex('S');
        myGraph.GetVertex('S').setLength(0);
        myGraph.GetVertex('S').markVisited();
        
        //adds the other vertexs
        index = myGraph.AddVertex('T');
        index = myGraph.AddVertex('V');
        index = myGraph.AddVertex('U');
        
      
        //adds the edges
        index = myGraph.AddEdge('S', 'V', 1);
        index = myGraph.AddEdge('V', 'U', 2);
        index = myGraph.AddEdge('U', 'T', 3);
        index = myGraph.AddEdge('S', 'U', 4);
        index = myGraph.AddEdge('V', 'T', 5);
        
        //calls dijkstra
        myGraph.dijkstra();
        //prints the lengh it takes to get to each vertex
		myGraph.printVertices();
	
	}
	
	
	public void dijkstra() {
		//creates queue
		Queue<Vertex> queue = new LinkedList<>();
		//adds the fisrt poin to the array 
		queue.add(GetVertex('S'));
		
		//while the queue is not empty
		while(queue.size() > 0) {
			
			//pull the top value and removes it from the queue
			Vertex curVer = queue.poll();
			
			//loops all the edgeds and goes edge by edge and each is mapped to value n
			for(Edge n : edges){
				
				//checks if the from to vertex is the current one we are on
				if(n.fromV == curVer.getName())
                {
					//// checks if the weight of the current edge is less than the to length 
                    if(n.weight < GetVertex(n.toV).getLength())
                    {
                    	//checks if the weight and length of the new value is less than the total length
                    	if(n.weight + GetVertex(n.fromV).getLength() < GetVertex(n.toV).getLength()) {
                    		
                    		//markes the vertex that is selected as the best option is visited
                    		GetVertex(n.toV).markVisited();
                    		//than we set the vertex value to the from vertex the the already made weight
                            GetVertex(n.toV).setLength(GetVertex(n.fromV).getLength()+ n.weight);
                    	}
                        
                    }
                    queue.add(GetVertex(n.toV));
                }
			}
		}
	}
		
	public class Vertex {
		private char name; 
		private boolean visited; 
		private int length; 
		public Vertex(char nameLetter) {
			name = nameLetter;
			visited = false; 
			length = MaxDistance; 
		}
		
		
		public void markVisited() {
			visited = true;
		}
		public boolean isVisited() {
			return visited;
		}
		public void setLength(int newLength) {
			length = newLength;
		}
		public int getLength() {
			return length;
		}
		
		public char getName() {
			return name;
		}		
		public String print() {
			String result = "" + name;
			if (visited) {
				result = result + "* ";
			} 
			else {
				result = result + " ";
			}
			result = result + "Path length = " + length;
			return result;
		}	
	}
		
	public class Edge {
		char fromV, toV; //Stored as Vertex name letters
		int weight;
		public Edge(char fromname, char toname, int edgeweight) {
			fromV = fromname;
			toV = toname;
			weight = edgeweight;
		}
		public String print() {
			String result = "" + fromV + " -> " + toV + " Cost = " +
			Integer.toString(weight);
			return result;
		}	
	}
		
	public GraphClass(int numVerts, int numEdges) {
		vertices = new Vertex[numVerts];
		edges = new Edge[numEdges];
		maxVert = numVerts; // valid array indices are 0..numverts -1
		maxEdge = numEdges;
		edgeCount = 0;
	}
		
	// Converts the capital letter name to an integer index for direct access
	public int AddVertex(char name) {
		int result = -1; //default
		int index = (int) name - (int) 'S';
		if ((index < (maxVert)) && (index >= 0)) { // if room available
			vertices[index] = new Vertex(name);
			result = index;
		}
		return result;
	}
		
	//Returns vertex by name if present, else null
	public Vertex GetVertex(char vertexname) {
		Vertex result = null; //default
		int index = (int) vertexname - (int) 'S';
		if ((index < (maxVert)) && (index >= 0)) { // if valid
			result = vertices[index];
		}
		return result;
	}
		
	//Returns vertex by index if present, else null
	public Vertex GetVertex(int vertexindex) {
		Vertex result = null; //default
		if ((vertexindex < (maxVert)) && (vertexindex >= 0)) { // if valid
			result = vertices[vertexindex];
		}
		return result;
	}
		
	// Adds an edge using vertex names and edge weight
	public int AddEdge(char fromname, char toname, int edgeweight) {
		int result = -1; //default
		//int index = ;
		if (edgeCount < (maxEdge)) { // if room available
			edges[edgeCount] = new Edge(fromname, toname, edgeweight);
			result = edgeCount;
			edgeCount++;
		}
		return result;
	}
		
	// Number of defined Edges in the list now for loop limits
	public int EdgeCount() {
		return edgeCount;
	}
		
	// Get an Edge by index
	public Edge GetEdge(int index) {
		Edge result = null; //default
		if ((index < (maxEdge)) && (index >= 0)) { // if valid
			result = edges[index];
		}
		return result;
	}
		
	public void printVertices() {
		int i; //assumes all slots allocated have been used
		for (i = 0; i < maxVert; i++) {
			System.out.print(Integer.toString(i) + ": ");
			if (vertices[i] != null) {
				System.out.println(vertices[i].print());
			} 
			else {
				System.out.println("Vertex not initialized.");
			}
		}
	}
	
	
}