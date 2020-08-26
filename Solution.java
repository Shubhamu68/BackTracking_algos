package com.tcs;

import java.util.ArrayList;
import java.util.List;

/**********************************************
 * DESCRIPTION :
 * 
 * Hamiltonian Path in an undirected graph is a path that visits each vertex exactly once. 
 * A Hamiltonian cycle (or Hamiltonian circuit) is a Hamiltonian Path such that there is an edge (in the graph) from the 
 * last vertex to the first vertex of the Hamiltonian Path. Determine whether a given graph contains Hamiltonian Cycle or not. 
 * If it contains, then print the path. 
 * 
 * *********************************************
 * 
 * This is a backtracking problem. Why ? We need to track the path which represents a hamiltonian cycle.
 * If question was just to check whether the hamiltonian path exists in the graph or not, then simple dfs approach would have been used.
 * 
 * In this problem, we will traverse through all the vertices of graph. We can start from any vertex. For simplicity, we will start from 0 here.
 * For every vertex, we will check its adjacent vertices which haven't been visited yet. any such index we will add to our list(path).
 * Then we'll check for the rest of the graph. If at the end, we are at the last index, and this last index is connected to our starting index,
 * that means we have found our path.
 * 
 * @author Shubham
 *
 */


public class Solution {
	//		to store the result path
	List<Integer> res ;
	
	/**	main function to check for hamiltonian path **/
	public int[] findHamiltonianPath(int g[][]) {
		
		res = new ArrayList<>();
		
		//		means we are starting from 0th vertex. We can start from any vertex.
		res.add(0);
		//		to not visit the same vertex twice
		boolean visited[] = new boolean[g.length];
		
		//		this will check whether a path exists or not.
		boolean res1 = helper(g, visited, 0);
		//		if it doesnt exist..
		if(!res1)
			return new int[] {};
		
		//		we are here if there is a path.
		int p[] = new int[g.length+1];
		
		//		store the path in an array and return it.
		for(int i=  0;i<res.size();i++) {
			p[i] = res.get(i);
		}
		return p;
	}
	
	private boolean helper(int[][] g, boolean[] visited, int index) {
		
		//		i.e. we have reached the last index and also this last index is adjacent to first one
		if(res.size() == g.length && g[index][0] == 1) {
			res.add(0);
			return true;
		}
		//		we have visited this index now. mark it as true.
		visited[index] = true;
		
		//		for all vertices in the graph
		for(int i=0;i<g[0].length;i++) {
			//		checking those which are adjacent to index & also have not been visited.
			if(g[index][i] == 1 && !visited[i]) {
				//		if yes, then consider them as a solution and add it to the list.(here 'i')
				res.add(i);
				//		if there is a solution from here, 
				if(helper(g, visited, i))
					return true;
				//		we reach here if there is no solution from ith index
				res.remove(res.size()-1);
			}
		}
		//		we reach here if we have checked all adjacent vertices of index, and there is no solution.
		//		that means we have to unmark this(backtrack) and remove this index from res which we added in the previous recursive call.
		visited[index] = false;
		return false;
	}

	public static void main(String[] args) {
		
		int g[][] = {{0, 1, 0, 1, 0}, 
	            {1, 0, 1, 1, 1}, 
	            {0, 1, 0, 0, 1}, 
	            {1, 1, 0, 0, 0}, 
	            {0, 1, 1, 0, 0}, 
	        };
		
		int res [] = new Solution().findHamiltonianPath(g);
		for(int i =0;i<res.length;i++) {
			System.out.print(res[i] + "  ");
		}
	}

}
