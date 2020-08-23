package com.tcs;


/**
The N Queen is the problem of placing N chess queens on an N×N chessboard so that no two queens attack each other.
The expected output is a binary matrix which has 1s for the blocks where queens are placed. For example, following is the output matrix for above 4 queen solution.
 	      { 0,  1,  0,  0}
              { 0,  0,  0,  1}
              { 1,  0,  0,  0}
              { 0,  0,  1,  0}
**/
public class Solution {

	//	replace this value with the size of the board..
	int N = 16;
	int board[][];
	
	public void nQueens() {
		//	our actual board which will get printed
		board= new int[N][N];
		
		//	recursive dfs method to find the n queens solution
		helper(0);
		//	and then print the sol.
		printBoard();
	}

	private void printBoard() {
		//		printing the board
		for(int i =0;i<N;i++) {
			for(int j=0;j<N;j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	private boolean helper(int col) {
		
		//		if we have surpassed the last column i.e. all queens are placed , return true..
		if(col == N)
			return true;
		
		//		for all the rows in this column..
		for(int row = 0;row<N;row++) {
			//	if this is a safe place to place our queen
			if(isSafe(row, col)) {
				//	then place the queen..
				board[row][col] = 1;
				//	and check for the next columns
				if(helper(col+1))
					return true;
			}
			//	if placing the queen at [row,col] doesnt work, unmark this(backtrack) and check for the next rows..
			board[row][col] = 0;
		}
		//	if all the rows have been checked for this col., and there is no solution, return false saying that the previous columns
		//	where the queens had been placed were placed wrongly..and we need to modify those..
		return false;
	}

	//		this method tells whether the queen is safe to place at xth row & yth col.
	private boolean isSafe(int x, int y) {
		
		//			we mainly have to check for 3 directions.
		//			1. left dir. till 0th col
		//			2. upper left diag 
		//			3. lower left diag
		
		//			left
		for(int i =0;i<y;i++) {
			if(board[x][i] == 1)
				return false;
		}
		
		int p=x+1,q=y-1;
		
		//		lower left diag
		while(p < N && q >= 0) {
			if(board[x][y] == 1)
				return false;
			p++;
			q--;
		}
		
		p = x-1;q=y-1;
		//		upper left diag
		while(p>=0 && q >=0) {
			
			if(board[p][q] == 1)
				return false;
			p--;
			q--;
		}
		return true;
	}
	
	//		this is just a simple dfs solution to find out whether a N Queens sol. exists or not..
	//		no backtracking is involved here.
	private boolean helper2(int col) {
		
		if(col == N)
			return true;
		
		for(int row = 0;row<N;row++) {
			if(isSafe(row, col)) {
				if(helper(col+1))
					return true;
			}
		}
		return false;
	}
	
	public static void main(String args[]) {
		
		Solution ob = new Solution();
		ob.nQueens();
	}
}
