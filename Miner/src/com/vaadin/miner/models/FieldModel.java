package com.vaadin.miner.models;

import java.util.Random;

public class FieldModel {
	// these numbers represent a state of particular field
	final static int CLOSED_EMPTY = 0;
	final static int OPEN_EMPTY = 10;
	final static int OPEN_BOMB = 100;
	final static int CLOSED_BOMB = -100;
	// digit represents number of neighbor mines; + cell is open - cell is
	// closed;
	final static int CLOSED_ONE = -1;
	final static int CLOSED_TWO = -2;
	final static int CLOSED_THREE = -3;
	final static int CLOSED_FOUR = -4;
	final static int CLOSED_FIVE = -5;
	final static int CLOSED_SIX = -6;
	final static int OPEN_ONE = 1;
	final static int OPEN_TWO = 2;
	final static int OPEN_THREE = 3;
	final static int OPEN_FOUR = 4;
	final static int OPEN_FIVE = 5;
	final static int OPEN_SIX = 6;
	final static int OPEN_SEVEN = 7;
	private int size;
	private int mines;
	private int[][] cells;
	private int remainedMines;

	public FieldModel(int size, int mines) {
		this.size = size;
		this.mines = mines;
		cells = new int[size][size];
		remainedMines = size;
	}

	public FieldModel(int[][] cells, int mines) {
		this.mines = mines;
		this.size = cells.length;
		this.cells = cells;
		remainedMines = 0;

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
	boolean isInRange(int row, int col) {
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
		//if current cell already has bomb, no need to count neighbors
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
	 * @param row
	 * @param col
	 */
	private void openNeigbors(int row,int col) {
		if(cells[row][col]!=OPEN_EMPTY) {
			return;
		}
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (isInRange(row + i, col + j)) {
					openCell(row+i,col+j);
				}
			}
		}		
	}
	/**
	 * Open cell 
	 * @param row
	 * @param col
	 */
	public void openCell(int row,int col) {
		if (isOpen(row,col)) {
			return;
		}
		else if(cells[row][col]==CLOSED_BOMB) {
			cells[row][col]=OPEN_BOMB;
		}
		else if(cells[row][col]==CLOSED_EMPTY) {
			cells[row][col]=OPEN_EMPTY;
			openNeigbors(row,col);
		}
		else {
			cells[row][col]=Math.abs(cells[row][col]);
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
