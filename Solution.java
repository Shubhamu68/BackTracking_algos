package com.tcs;

public class Solution {

	int res[][];
	public int[][] findPath(int[][] path){
		
		int x = 0;
		int y = 0;
		
		res = new int[path.length][path.length];
		
		helper(x, y, path);
		return res;
		
	}
	
	
	private boolean helper(int x, int y, int[][] path) {
		//			if we have reached the end, return true(saying we have found the solution)..
		if(x == path.length-1 && y == path[0].length - 1 && path[x][y] == 1) {
			res[path.length-1][path[0].length-1] = 1;
			return true;
		}
		
		//			if we go out of bounds, or if it is a dead end, return
		if(x >= path.length || y >= path[0].length || path[x][y] != 1 )
			return false;
		
		//			if we reach here, that means this is a valid path.. mark it as travelled -> 1
		res[x][y] = 1;
		
		//			now check for other paths from here.. we can go in 2 directions - forward and down..
		if(helper(x+1,y,path) || helper(x, y+1, path))
			return true;
		
		//			if we are here, it means we didnt found any solution from this position, lets backtrack(mark this position as unused/free)...
		res[x][y] = 0;
		//			and return a false to the previous caller saying there is no solution from here..
		return false;
	}

	public boolean checkIfPathExists(int[][] path) {
		
		boolean res = helper2(path,0,0,path.length);
		return res;
	}

	private boolean helper2(int[][] path, int x, int y, int length) {
		
		//		if out of bounds, return false..
		if(x >= length || y >= length || path[x][y] != 1)
			return false;
		
		//		if at the bottom right, and it is valid, return true..
		if(x == length-1 && y == length-1 && path[x][y] == 1)
			return true;
		
		//		if none of the above, that means we are sitting somewhere in the mase and we can go to 2 different directions... return if any of those 2 paths result in a 
		//		solution..
		return helper2(path,x+1,y,length) || helper2(path,x,y+1,length);
	}

	public static void main(String[] args) {
	
		int path[][] =  {   { 1, 0, 0, 0 }, 
			                { 1, 1, 0, 1 }, 
			                { 0, 0, 0, 1 }, 
			                { 0, 0, 0, 1 } };
		
		System.out.println(new Solution().checkIfPathExists(path));
		//		new Solution().printPath(res);
	}

	private void printPath(int[][] r) {
		
		for(int i =0;i<r.length;i++) {
			
			for(int j = 0;j<r.length;j++) {
				System.out.print(r[i][j] + " ");
			}
			System.out.println();
		}
	}
}
