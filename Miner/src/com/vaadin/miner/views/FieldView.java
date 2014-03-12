package com.vaadin.miner.views;

import com.vaadin.miner.models.FieldModel;

public class FieldView {
	private FieldModel model;
	private int size;
	public FieldView(FieldModel model) {
		this.model=model;
		size=model.getSize();
	}
	
}
