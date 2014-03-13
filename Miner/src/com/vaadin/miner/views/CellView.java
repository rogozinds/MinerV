package com.vaadin.miner.views;

import com.vaadin.ui.Image;
/**
 * Represents a cell View
 * @author rogozin
 *
 */
public class CellView extends Image {
	int row;
	int col;

	public CellView(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}
	public int getRow() {
		return row;
	}
	public int getCol() {
		return col;
	}

}
