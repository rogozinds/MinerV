package com.vaadin.miner.models;

import java.util.Random;

import com.vaadin.miner.views.FieldView;

public class FieldModel {
	// these numbers are also used in view
	// for a particular Cell picture.
	// these numbers represent a state of particular field
	public final static int CLOSED_EMPTY = 0;
	public final static int OPEN_EMPTY = 10;
	public final static int OPEN_BOMB = 100;
	public final static int CLOSED_BOMB = -100;
	//these numbers represent either cell is blocked or not
	public final static int NOT_BLOCKED=0;
	public final static int BLOCKED=-10;
	// digit represents number of neighbor mines; + cell is open - cell is
	// closed;
	public final static int CLOSED_ONE = -1;
	public final static int CLOSED_TWO = -2;
	public final static int CLOSED_THREE = -3;
	public final static int CLOSED_FOUR = -4;
	public final static int CLOSED_FIVE = -5;
	public final static int CLOSED_SIX = -6;
	public final static int OPEN_ONE = 1;
	public final static int OPEN_TWO = 2;
	public final static int OPEN_THREE = 3;
	public final static int OPEN_FOUR = 4;
	public final static int OPEN_FIVE = 5;
	public final static int OPEN_SIX = 6;
	public final static int OPEN_SEVEN = 7;
	private int size;
	private int mines;
	private int[][] cells;
	private int[][] blockedCells;
	private int remainedMines;
	private FieldView view;

	public FieldView getView() {
		return view;
	}

	public void setView(FieldView view) {
		this.view = view;
	}

	public FieldModel(int size, int mines) {
		this.size = size;
		this.mines = mines;
		cells = new int[size][size];
		blockedCells = new int[size][size];
		remainedMines = size;
	}

	public FieldModel(int[][] cells, int mines) {
		this.mines = mines;
		this.size = cells.length;
		this.cells = cells;
		remainedMines = 0;

	}

	/**
	 * getter for size field
	 * 
	 * @return size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * adding mine to the field;
	 * 
	 * @return 1 if mine was added -1 otherwise
	 * 
	 */
	private int addMine() {
		Random randomGen = new Random();
		int row = randomGen.nextInt(size);
		int col = randomGen.nextInt(size);
		if (cells[row][col] == 0) {
			cells[row][col] = CLOSED_BOMB;
			return 1;
		}
		return -1;
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @return is current cell in range
	 */
	private boolean isInRange(int row, int col) {
		if ((row < 0) || (row >= size)) {
			return false;
		}
		if ((col < 0) || (col >= size)) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param row
	 *            row
	 * @param col
	 *            column
	 * @return Result has particular cell bomb or not
	 */
	public boolean hasBomb(int row, int col) {
		if (cells[row][col] == OPEN_BOMB) {
			return true;
		}
		if (cells[row][col] == CLOSED_BOMB) {
			return true;
		}
		return false;
	}
	public boolean isBlocked(int row,int col) {
		return blockedCells[row][col]==BLOCKED;
	}
	/**
	 * 
	 * @param row
	 * @param col
	 * @return is cell Open or not
	 */
	public boolean isOpen(int row, int col) {
		return cells[row][col] > 0;
	}

	/**
	 * 
	 * @param row
	 * @param col
	 * @return return number of neighbor bombs or 100 if it has a bomb
	 */
	public int nNeighborBombs(int row, int col) {
		if (hasBomb(row, col)) {
			return 100;

		}
		return Math.abs(cells[row][col]);
	}

	/**
	 * calculate neigbors for particular cell and update the number in the field
	 * neigbors are marked by #, x the mine # # # # x # # # #
	 * 
	 * @param row
	 *            - index of row
	 * @param col
	 *            - index of column
	 * @return void
	 * 
	 */
	private void addNeigbors(int row, int col) {
		// if current cell already has bomb, no need to count neighbors
		if (hasBomb(row, col)) {
			return;
		}
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (isInRange(row + i, col + j)) {
					if (hasBomb(row + i, col + j)) {
						cells[row][col]--;// add neigbors;
					}
				}
			}
		}
	}

	private void calcNeigbors() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				addNeigbors(i, j);
			}
		}
	}

	/**
	 * Opens Neighbors cells if current cell was empty
	 * 
	 * @param row
	 * @param col
	 */
	private void openNeigbors(int row, int col) {
		if (cells[row][col] != OPEN_EMPTY) {
			return;
		}
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (isInRange(row + i, col + j)) {
					openCell(row + i, col + j);
				}
			}
		}
	}

	/**
	 * Open cell
	 * 
	 * @param row
	 * @param col
	 */
	public void openCell(int row, int col) {
		int tmpValue = 0;
		if (isOpen(row, col) || (isBlocked(row, col)) ) {
			return;
		} else if (cells[row][col] == CLOSED_BOMB) {
			tmpValue = OPEN_BOMB;
		} else if (cells[row][col] == CLOSED_EMPTY) {
			cells[row][col] = OPEN_EMPTY;
			tmpValue = OPEN_EMPTY;
			openNeigbors(row, col);
		} else {
			tmpValue = Math.abs(cells[row][col]);
		}
		cells[row][col] = tmpValue;
		if (view != null) {
			view.updateCell(row, col, tmpValue);
		}
	}
	/**
	 * Blocking the cell, that it can't be open
	 * or unblock  if it is open. In classic miner this is the same as putting/removing flag
	 * @param row
	 * @param col
	 */
	public void blockUnblockCell(int row, int col) {
		if (isOpen(row, col)) {
			return;
		} else if (blockedCells[row][col]==BLOCKED) {
			blockedCells[row][col]=NOT_BLOCKED;
			view.updateCell(row, col, CLOSED_EMPTY);
		}
		else { 	
			blockedCells[row][col]=BLOCKED;
			view.updateCell(row, col, BLOCKED);
		}
	}
	public void createField() {
		while (this.remainedMines > 0) {
			if (addMine() == 1) {
				remainedMines--;
			}
		}
		calcNeigbors();
	}
}
