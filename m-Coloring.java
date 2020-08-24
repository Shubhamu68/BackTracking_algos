package com.tcs;

/**	DESCRIPTION :
 * 
 * Given an undirected graph and a number m, determine if the graph can be coloured with at most m colours such that no two adjacent vertices 
 * of the graph are colored with the same color. Here coloring of a graph means the assignment of colors to all vertices.
 * 
 * Input:

	A 2D array graph[V][V] where V is the number of vertices in graph and graph[V][V] is adjacency matrix representation of the graph. A value graph[i][j] is 1 if there is a direct edge from i to j, otherwise graph[i][j] is 0.
	An 	integer m which is the maximum number of colors that can be used.
 * 
 * 
 * Output:
	An array color[V] that should have numbers from 1 to m. color[i] should represent the color assigned to the ith vertex. 
	The code should also return false if the graph cannot be colored with m colors.
 * @author Shubham
 *
 */

/**
 * 
 * Again asking why this is a backtracking problem.. If the problem description had asked "Check whether the given graph can be colored with at most m colors" ,
 * then we could have just used simple dfs approach for every 1-m color coding for each vertex..
 * The only extra steps here is the adding and removing of color blocks in color array.
 * 
 * @author Shubham
 *
 */


public class Solution {
	
	//		no. of vertices
	int v;
	//		output array
	int colors[];
	public void print() {
		
		for (int i=0;i<v;i++) {
			System.out.print(colors[i] + "  ");
		}
	}
	
	public void colorGraph(int [][]g, int m) {
		
		v = g.length;
		colors = new int[v];
		
		helper(g,m,0);
		print();
	}
	
	public boolean helper(int [][]g, int m , int vertex) {
		//		we reach here if and only if all the vertices have been colored... return true 
		if(vertex == v)
			return true;
		//		tring for all values for m..
		for(int i =1;i<=m;i++) {
			//		if this is a safe move i.e. if vertex can be colored with i'th color..
			if(isSafe(g,vertex,i)) {
				//	mark colors[vertex] as color of m i.e. i in this case..
				colors[vertex] = i;
				//	and proceed with the next index
				if (helper(g,m,vertex+1))
					return true;
			}
			//			if we are here, it means marking vertex with color i didn't worked and now we need to reset the value back to 0..
			colors[vertex] = 0;
		}
		//		and if we reach here, it means no solution exist from this vertex i.e we need to modify the colors assigned to our previous vertices.. i.e. backtrack
		return false;
	}
	
	private boolean isSafe(int[][] g, int vertex, int m) {
		
		//		we have to check whether vertex's adjacent vertices do not have the same color as m
		//		traverse over the vertex'th row and check for 1's..
		//		the cols containing 1 will be those nodes which are adjacent to vertex..
		//		now check their assigned color in colors[] array
		
		for(int i =0;i<v;i++) {
			//	ith node has been assigned same color which we r planning for vertex && this ith node is adjacent to vertex, return false.. else continue.
			if(colors[i] == m && g[vertex][i] == 1)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		
		int graph[][] = { 
	            { 0, 1, 1, 1 }, 
	            { 1, 0, 1, 0 }, 
	            { 1, 1, 0, 1 }, 
	            { 1, 0, 1, 0 }};
		
		int m = 3;
		Solution ob = new Solution();
		ob.colorGraph(graph, m);
	}
}
