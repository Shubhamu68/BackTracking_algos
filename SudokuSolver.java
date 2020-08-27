package com.tcs;

/**

PROBLEM DESCRIPTION: 

Given a partially filled 9×9 2D array ‘grid[9][9]’, the goal is to assign digits (from 1 to 9) to the empty cells so that every 
row, column, and subgrid of size 3×3 contains exactly one instance of the digits from 1 to 9.

**/


public class Solution {
	
	int N = 9;
	public void sudokuSolver(int sud[][]) {
		
		//		main recursive backtracking function to fill the sudoku
		if(helper(sud,0,0))
			System.out.println("Solved");
		printSolvedSudoku(sud);
	}
	
	private void printSolvedSudoku(int[][] sud) {
		
		for(int i =0;i<N;i++) {
			for(int j = 0;j< N;j++) {
				System.out.print(sud[i][j] + " ");
			}
			System.out.println();
		}
	}

	private boolean helper(int[][] sud, int row, int col) {
		
		
		//		if we have reached the last index, that means all the grids have been filled and sudoku has been solved..return true in this case.
		if(row == N-1 && col == N) {
			return true;
		}
		
		//		if we have reached the end of column, advance to the next row..
		if(col == N)
			return helper(sud,row+1,0);
		
		//		if out of bounds, return false..
		if(row >= N || col >= N)
			return false;
		
		//		if current grid needs to be filled, 
		if(sud[row][col] == 0) {
			//		check by putting all the numbers from 1-9
			for(int i =1;i<=N;i++){
				//		if putting i here is safe
				if(isSafe(sud,row,col,i)) {
					//	put it..
					sud[row][col] = i;
					//	and move to the next col.
					if(helper(sud,row,col+1))
						return true;
					//	we will come here only if i was put wrongly... try other numbers now..
					sud[row][col] = 0;
				}
			}
		}else {
			//	this grid is already filled, move to the next col.
			return helper(sud,row,col+1);
		}
		//		we are here if all the numbers have been tried but no solution exists.. backtrack and return false....
		return false;
	}

	private boolean isSafe(int[][] sud, int row, int col, int num) {
		
		//		3 checks we need to do before returning true..
		
		//		1. Row check
		//		2. Col. check
		//		3. Block check
		
		
		//		here we are doing row and col. check
		for(int i=0;i<N;i++) {
			if(sud[row][i] == num)
				return false;
			if(sud[i][col] == num)
				return false;
		}
		
		//		below is the block check algo
		int r =0,c=0;
		int blocks[][] = {{0,2},{3,5},{6,8}};
		for(int i =0;i<blocks.length;i++) {
			if(row>=blocks[i][0] && row <= blocks[i][1]) {
				r = i;
			}
			if(col>=blocks[i][0] && col<=blocks[i][1]) {
				c = i;
			}
		}
		
		for(int i = blocks[r][0];i<=blocks[r][1];i++) {
			for(int j = blocks[c][0];j<=blocks[c][1];j++) {
				if(sud[i][j] == num)
					return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {

		int sudoku[][] = { {3, 0, 6, 5, 0, 8, 4, 0, 0}, 
		         {5, 2, 0, 0, 0, 0, 0, 0, 0}, 
		         {0, 8, 7, 0, 0, 0, 0, 3, 1}, 
		         {0, 0, 3, 0, 1, 0, 0, 8, 0}, 
		         {9, 0, 0, 8, 6, 3, 0, 0, 5}, 
		         {0, 5, 0, 0, 9, 0, 6, 0, 0}, 
		         {1, 3, 0, 0, 0, 0, 2, 5, 0}, 
		         {0, 0, 0, 0, 0, 0, 0, 7, 4}, 
		         {0, 0, 5, 2, 0, 6, 3, 0, 0} };
		
		Solution ob = new Solution();
		ob.sudokuSolver(sudoku);
	}

}
