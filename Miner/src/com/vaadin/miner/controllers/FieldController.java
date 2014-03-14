package com.vaadin.miner.controllers;

import com.vaadin.miner.models.FieldModel;
import com.vaadin.miner.views.CellView;
import com.vaadin.miner.views.FieldView;

public class FieldController {
	public enum eCellAction{OPEN,BLOCK};
	FieldModel model;
	public FieldController(FieldModel m) {
		this.model=m;
	}
	public void cellClick(CellView cell,eCellAction action) {
		if (action==eCellAction.OPEN) {
			model.openCell(cell.getRow(), cell.getCol());
		}
		else if (action==eCellAction.BLOCK) {
			model.blockUnblockCell(cell.getRow(), cell.getCol());
		}
	}
}
