package com.joriankoning.sudokuSolver.controller;

import com.joriankoning.sudokuSolver.data.SudokuInputOutput;
import com.joriankoning.sudokuSolver.service.SudokuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sudoku")
public class SudokuController {

	@Autowired
	private SudokuService sudokuService;

	@PostMapping(value = "/solve")
	public SudokuInputOutput solveSudoku(@RequestBody SudokuInputOutput sudokuInput) {
		return sudokuService.solveSudoku(sudokuInput);
	}
}
