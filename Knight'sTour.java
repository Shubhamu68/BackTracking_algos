package com.tcs;

import java.util.Arrays;

/**
The knight is placed on the first block of an empty board and, moving according to the rules of chess, must visit each square exactly once.
Chess is represented by an 8*8 grid. Knight will be initially placed at [0][0] position and filled with value 0(meaning this is the 0th move).
**/

public class Solution {
	
	int N = 8;
	//		the chess board we will use to print the sequence of moves..
	int board[][] = new int[N][N];
	//		moves the knight can make from any position...
	int dirs[][] = {{2,1},{1,2},{-1,2},{-2,1},{-2,-1},{-1,-2},{1,-2},{2,-1}};
	
	
	public void knightTour() {
		
		for(int b[]:board) {
			Arrays.fill(b, -1);
		}
		int moveNumber = 0;
		helper(moveNumber,0,0);
		printPath();
	}
	
	public void printPath() {
		
		for(int i =0;i<N;i++) {
			for(int j = 0;j<N;j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public boolean helper(int move,int x, int y) {
		
		if(board[x][y] != -1)
			return false;
		
		if(move == N*N - 1) {
			board[x][y] = N*N-1;
			return true;
		}
		
		board[x][y] = move;
		
		for(int dir[]:dirs) {		//		for every move(from the 8 moves a knight can make)
			
			int x1 = dir[0] + x;	//		new x-axis position 
			int y1 = dir[1] + y;	//		new y-axis position
			
			if(!outOfBounds(x1,y1) && helper(move+1, x1, y1)) {		//			if not out of bounds, try traversing this path
				return true;			//			if this condition is ever reached, that means we have found the solution
			}
		}
		board[x][y] = -1;				//		if we are here, that means we didnt found the solution from the current position and we need to free up this position.
										//		i.e we will backtrack(unmark the position/free it) and return false saying that there is no further solution from this pos
		return false;
	}

	private boolean outOfBounds(int x, int y) {		
		
		if(x >= N || y >= N || x < 0 || y < 0)
			return true;
		return false;
	}
	
	public static void main(String p[]) {
		
		Solution ob = new Solution();
		ob.knightTour();
	}
}
