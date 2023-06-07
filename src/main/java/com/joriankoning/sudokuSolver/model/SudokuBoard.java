package com.joriankoning.sudokuSolver.model;

import lombok.Getter;
import lombok.Setter;

import java.util.stream.IntStream;

public class SudokuBoard {

	@Getter
	private final int fieldSize = 9;
	@Getter
	private final int blockSize = 3;
	@Getter
	@Setter
	private int[][] field;

	@Getter
	private int[] numbersInSudoku;

	public SudokuBoard(int[][] field) {
		this.field = field;
		initNumbersInSudoku();
	}

	private void initNumbersInSudoku() {
		numbersInSudoku = new int[fieldSize];
		for (int i = 1; i <= fieldSize; i++) {
			numbersInSudoku[i - 1] = i;
		}
	}

	public void setNumber(int x, int y, int num) {
		field[y][x] = num;
	}

	public boolean isNumberOk(int x, int y, int number) {
		if (field[y][x] != 0)
			return false;

		int[] row = getRow(y);
		int[] column = getColumn(x);
		int[] block = getBlockAsRow(x, y);

		if (arrayContainsNumber(row, number))
			return false;
		if (arrayContainsNumber(column, number))
			return false;
		return !arrayContainsNumber(block, number);
	}

	private int[] getRow(int y) {
		return field[y];
	}

	private int[] getColumn(int x) {
		int[] result = new int[fieldSize];
		for (int y = 0; y < fieldSize; y++) {
			result[y] = field[y][x];
		}
		return result;
	}

	private int[][] getBlock(int x, int y) {
		x = (x / blockSize) * blockSize;
		y = (y / blockSize) * blockSize;

		int[][] result = new int[blockSize][];

		for (int blockY = 0; blockY < blockSize; blockY++) {
			result[blockY] = new int[blockSize];
			System.arraycopy(field[y + blockY], x, result[blockY], 0, blockSize);
		}
		return result;
	}

	private int[] getBlockAsRow(int x, int y) {
		int[][] numbersInBlock = getBlock(x, y);
		int[] result = new int[fieldSize];
		int counter = 0;
		for (int[] blockRow : numbersInBlock) {
			for (int number : blockRow) {
				result[counter++] = number;
			}
		}
		return result;
	}

	public boolean isStillSolvable(int x, int y, int number) {
		x = (x / blockSize) * blockSize;
		y = (y / blockSize) * blockSize;

		for (int i = 0; i < fieldSize; i += blockSize) {
			if (i == x) continue;
			if (!isPossibleForBlock(i, y, number)) {
				return false;
			}
		}
		for (int i = 0; i < fieldSize; i += blockSize) {
			if (i == y) continue;
			if (!isPossibleForBlock(x, i, number)) {
				return false;
			}
		}
		return true;
	}

	private boolean isPossibleForBlock(int x, int y, int number) {
		x = (x / blockSize) * blockSize;
		y = (y / blockSize) * blockSize;

		int[] block = getBlockAsRow(x, y);
		if (arrayContainsNumber(block, number)) {
			return true;
		}

		for (int blockX = 0; blockX < blockSize; blockX++) {
			for (int blockY = 0; blockY < blockSize; blockY++) {
				if (isNumberOk(x + blockX, y + blockY, number)) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean isDone() {
		for (int[] row : field) {
			for (int num : row) {
				if (num == 0) return false;
			}
		}
		return true;
	}

	private boolean arrayContainsNumber(int[] arrayToCheck, int number) {
		return IntStream.of(arrayToCheck).anyMatch(x -> x == number);
	}
}
