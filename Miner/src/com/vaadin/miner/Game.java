package com.vaadin.miner;

import com.vaadin.miner.controllers.FieldController;
import com.vaadin.miner.models.FieldModel;
import com.vaadin.miner.views.FieldView;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Component;

public class Game {
	private static Game instance=null;
	private Game() {
		
	}
	static Game getInstance() {
		if (instance == null) {
			return new Game();
		}
		return instance;
	}
	public void startGame(int size,int mines, AbstractLayout layout) {
		layout.removeAllComponents();
		FieldModel model=new FieldModel(size,mines);
		model.createField();
		FieldController controller=new FieldController(model);
		
		FieldView view = new FieldView(model, controller, layout);
		view.init();
		model.setView(view);
	}
}
