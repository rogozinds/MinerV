package com.vaadin.miner.controllers;

import com.vaadin.miner.models.FieldModel;
import com.vaadin.miner.views.CellView;
import com.vaadin.miner.views.FieldView;

public class FieldController {
	FieldModel model;
	FieldView view;

	public void cellClick(CellView cell) {
		model.openCell(cell.getRow(), cell.getCol());
	}
}
