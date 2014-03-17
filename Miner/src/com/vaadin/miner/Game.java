package com.vaadin.miner;

import com.vaadin.miner.controllers.FieldController;
import com.vaadin.miner.models.FieldModel;
import com.vaadin.miner.views.FieldView;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Component;

public class Game {
	static private MainWindow mw;
	FieldModel model;
	public MainWindow getMw() {
		return this.mw;
	}
	public void setMw(MainWindow mw) {
		this.mw = mw;
		mw.getLblStatus().setValue("Specify level of difficulty");
		mw.getLblStatus().setStyleName("header");
	}
	private static Game instance=null;
	private Game() {

	}
	public static Game getInstance() {
		if (instance == null) {
			return new Game();
		}
		return instance;
	}
	public void startGame(int size,int mines, AbstractLayout layout) {
		layout.removeAllComponents();
		model=new FieldModel(size,mines);
		model.createField();
		FieldController controller=new FieldController(model);
		
		FieldView view = new FieldView(model, controller, layout);
		view.init();
		model.setView(view);
		mw.getLblStatus().setValue("");

	}
	public void onLostGame() {
		mw.getLblStatus().setStyleName("labelLose");
		mw.getLblStatus().setValue("YOU LOSE! TRY AGAIN!");
	}
	public void onWinGame() {
		mw.getLblStatus().setStyleName("labelWin");
		mw.getLblStatus().setValue("YOU WIN! TRY ONCE MORE!");
	}
}
