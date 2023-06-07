package com.joriankoning.sudokuSolver.solver;

import com.joriankoning.sudokuSolver.model.SudokuBoard;

public class SudokuSolver {
	private final SudokuBoard sudokuBoard;

	public SudokuSolver(SudokuBoard sudokuBoard) {
		this.sudokuBoard = sudokuBoard;
	}

	public void solveSudoku() {
		logicalSolve();
	}

	private void logicalSolve() {
		boolean emptySpots = checkEmptySpots();
		boolean rows = checkRows();
		boolean columns = checkColumns();
		boolean blocks = checkBlocks();
		if (emptySpots || rows || columns || blocks) {
			logicalSolve();
		}
	}

	private boolean checkEmptySpots() {
		boolean change = false;

		for (int y = 0; y < sudokuBoard.getFieldSize(); y++) {
			for (int x = 0; x < sudokuBoard.getFieldSize(); x++) {
				if (sudokuBoard.getField()[y][x] == 0) {
					int hits = 0;
					int numberThatIsOk = 0;
					for (int number : sudokuBoard.getNumbersInSudoku()) {
						if (sudokuBoard.isNumberOk(x, y, number)) {
							hits++;
							numberThatIsOk = number;
						}
					}
					if (hits == 1) {
						sudokuBoard.setNumber(x, y, numberThatIsOk);
						change = true;
					}
				}
			}
		}

		return change;
	}

	private boolean checkRows() {
		boolean change = false;
		for (int number : sudokuBoard.getNumbersInSudoku()) {
			for (int y = 0; y < sudokuBoard.getFieldSize(); y++) {
				int counter = 0;
				int numThatIsOk = 0;
				int xThatIsOk = 0;
				int yThatIsOk = 0;
				for (int x = 0; x < sudokuBoard.getFieldSize(); x++) {
					if (sudokuBoard.isNumberOk(x, y, number)) {
						counter++;
						numThatIsOk = number;
						xThatIsOk = x;
						yThatIsOk = y;
					}
				}
				if (counter == 1) {
					sudokuBoard.setNumber(xThatIsOk, yThatIsOk, numThatIsOk);
					change = true;
				}
			}
		}
		return change;
	}

	private boolean checkColumns() {
		boolean change = false;
		for (int number : sudokuBoard.getNumbersInSudoku()) {
			for (int x = 0; x < sudokuBoard.getFieldSize(); x++) {
				int counter = 0;
				int numThatIsOk = 0;
				int xThatIsOk = 0;
				int yThatIsOk = 0;
				for (int y = 0; y < sudokuBoard.getFieldSize(); y++) {
					if (sudokuBoard.isNumberOk(x, y, number)) {
						counter++;
						numThatIsOk = number;
						xThatIsOk = x;
						yThatIsOk = y;
					}
				}
				if (counter == 1) {
					sudokuBoard.setNumber(xThatIsOk, yThatIsOk, numThatIsOk);
					change = true;
				}
			}
		}
		return change;
	}

	private boolean checkBlocks() {
		boolean change = false;
		for (int x = 0; x < sudokuBoard.getFieldSize(); x += sudokuBoard.getBlockSize()) {
			for (int y = 0; y < sudokuBoard.getFieldSize(); y += sudokuBoard.getBlockSize()) {
				if (checkOneBlock(x, y)) {
					change = true;
				}
			}
		}
		return change;
	}

	private boolean checkOneBlock(int left, int top) {
		boolean change = false;
		for (int number : sudokuBoard.getNumbersInSudoku()) {
			int counter = 0;
			int numThatIsOk = 0;
			int xThatIsOk = 0;
			int yThatIsOk = 0;
			for (int x = 0; x < sudokuBoard.getBlockSize(); x++) {
				for (int y = 0; y < sudokuBoard.getBlockSize(); y++) {
					if (sudokuBoard.isNumberOk(x + left, y + top, number)) {
						counter++;
						numThatIsOk = number;
						xThatIsOk = x + left;
						yThatIsOk = y + top;
					}
				}
			}
			if (counter == 1) {
				sudokuBoard.setNumber(xThatIsOk, yThatIsOk, numThatIsOk);
				change = true;
			}
		}
		return change;
	}
}

