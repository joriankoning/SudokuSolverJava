package com.joriankoning.sudokuSolver;

import com.joriankoning.sudokuSolver.model.SudokuBoard;
import com.joriankoning.sudokuSolver.solver.SudokuSolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SudokuSolverApplication {

	public static void main(String[] args) {

		SudokuBoard sudokuBoard = new SudokuBoard();

		int[][] field;

		field = new int[][]{
				{0,8,0,5,0,0,0,9,1},
				{0,1,5,0,4,0,7,2,3},
				{0,0,0,0,0,1,0,0,0},
				{0,0,0,6,2,4,0,0,0},
				{0,0,8,0,0,0,3,0,0},
				{0,0,0,0,0,5,9,0,6},
				{0,0,9,0,0,0,0,5,0},
				{0,0,0,0,7,0,0,0,0},
				{2,7,1,0,0,0,4,0,9}
		};

		sudokuBoard.setField(field);

		SudokuSolver solver = new SudokuSolver(sudokuBoard);
		solver.solveSudoku();


		SpringApplication.run(SudokuSolverApplication.class, args);
	}

}
