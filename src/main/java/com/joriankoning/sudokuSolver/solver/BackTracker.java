package com.joriankoning.sudokuSolver.solver;

import com.joriankoning.sudokuSolver.model.SudokuBoard;

import java.util.Arrays;
import java.util.Stack;

public class BackTracker {
	private final SudokuBoard sudoku;

	public BackTracker(SudokuBoard sudoku) {
		this.sudoku = sudoku;
	}

	public void start() {
		Stack<FieldSituation> fieldSituations = new Stack<>();

		int positioningCounter = 0;
		int numberCounter = 1;

		while (positioningCounter < sudoku.getFieldSize() * sudoku.getFieldSize()) {
			int x = positioningCounter % sudoku.getFieldSize();
			int y = positioningCounter / sudoku.getFieldSize();
			if (sudoku.getField()[y][x] == 0) {

				int[][] fieldBefore = deepCopy(sudoku.getField());

				int placedNumber = setNextAvailableNumber(x, y, numberCounter);

				if (placedNumber != 0) {
					new SudokuSolver(sudoku).solveSudoku();
					if (sudoku.isDone()) {
						return;
					} else {
						fieldSituations.push(new FieldSituation(fieldBefore, positioningCounter, placedNumber));

						positioningCounter++;
						numberCounter = 1;
					}
				} else {
					if (fieldSituations.size() > 0) {
						FieldSituation fieldSituation = fieldSituations.pop();
						numberCounter = fieldSituation.number + 1;
						sudoku.setField(fieldSituation.field);
						positioningCounter = fieldSituation.position;
					} else {
						return;
					}
				}
			} else {
				positioningCounter++;
			}
		}
	}

	private int setNextAvailableNumber(int x, int y, int startAt) {
		for (int number = startAt; number <= sudoku.getFieldSize(); number++) {
			if (sudoku.isNumberOk(x, y, number)) {
				sudoku.setNumber(x, y, number);
				if (sudoku.isStillSolvable(x, y, number)) {
					return number;
				}
				sudoku.setNumber(x, y, 0);
			}
		}
		return 0;
	}

	private int[][] deepCopy(int[][] field) {
		if (field == null) {
			return null;
		}

		final int[][] result = new int[field.length][];
		for (int i = 0; i < field.length; i++) {
			result[i] = Arrays.copyOf(field[i], field[i].length);
		}
		return result;
	}


	private class FieldSituation {
		private final int[][] field;
		private final int position;
		private final int number;

		FieldSituation(int[][] field, int position, int number) {
			this.field = field;
			this.position = position;
			this.number = number;
		}
	}
}

