package com.joriankoning.sudokuSolver.service;

import com.joriankoning.sudokuSolver.data.SudokuInputOutput;
import com.joriankoning.sudokuSolver.model.SudokuBoard;
import com.joriankoning.sudokuSolver.solver.BackTracker;
import com.joriankoning.sudokuSolver.solver.SudokuSolver;
import org.springframework.stereotype.Service;

@Service
public class SudokuService {

	public SudokuInputOutput solveSudoku(SudokuInputOutput sudokuInput) {

		SudokuBoard sudokuBoard = new SudokuBoard(sudokuInput.getBoard());

		new SudokuSolver(sudokuBoard).solveSudoku();

		if (!sudokuBoard.isDone()) {
			new BackTracker(sudokuBoard).start();
		}

		SudokuInputOutput output = new SudokuInputOutput();
		output.setBoard(sudokuBoard.getField());
		return output;
	}
}
