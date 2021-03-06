package com.vaadin.miner.views;

import java.io.File;
import java.util.HashMap;

import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.miner.controllers.FieldController;
import com.vaadin.miner.controllers.FieldController.eCellAction;
import com.vaadin.miner.models.FieldModel;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.MouseEventDetails.MouseButton;
import com.vaadin.ui.AbstractLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Notification;

public class FieldView {
	//Path for pictures files look FieldModel
	final private String CLOSED_EMPTY="closed_empty.png";
	final private String OPEN_EMPTY="open_empty.png";
	final private String BLOCKED="blocked.png";

	final private String OPEN_BOMB="open_bomb.png";
	final private String OPEN_ONE="open_one.png";
	final private String OPEN_TWO="open_two.png";
	final static String OPEN_THREE ="open_three.png";
	final static String OPEN_FOUR = "open_four.png";
	final static String OPEN_FIVE = "dum5.png";
	final static String OPEN_SIX = "dum6.png";
	final static String OPEN_SEVEN = "dum7.png";
	
	final private String CELLS_PIC_DIR="/WEB-INF/images/";
	final private String basepath = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath()
			+CELLS_PIC_DIR + "/";
	private CellView[][] cells;
	private HashMap<Integer, FileResource>pics=new HashMap<Integer, FileResource>();
	private GridLayout grid ;
	private FieldModel model;
	private FieldController controller;
	private int size;
	private AbstractLayout mainLayout;
	public FieldView(FieldModel model,FieldController controller,AbstractLayout mainLayout) {
		this.model=model;
		size=model.getSize();
		this.mainLayout=mainLayout;
		this.controller=controller;
		cells=new CellView[size][size];
		initPics();
	}

	/**
	 * Updates the picture of the cell
	 * @param row cell row
	 * @param col cell column
	 
	 * @param picId cell picture id
	 */
	public void updateCell(int row,int col,int picId) {
		if(!pics.containsKey(picId)) {
			Notification.show("NO such picture id in resoursces hash map");
		}
		cells[row][col].setSource(pics.get(picId));
	}
	/**
	 * initializate the HASH_MAP OF all pictures
	 * 
	 */
	private void initPics() {
		pics.put(FieldModel.CLOSED_EMPTY, new FileResource(new File(basepath+CLOSED_EMPTY)));
		pics.put(FieldModel.OPEN_EMPTY, new FileResource(new File(basepath+OPEN_EMPTY)));
		pics.put(FieldModel.OPEN_BOMB, new FileResource(new File(basepath+OPEN_BOMB)));
		
		pics.put(FieldModel.OPEN_ONE, new FileResource(new File(basepath+OPEN_ONE)));
		pics.put(FieldModel.OPEN_TWO, new FileResource(new File(basepath+OPEN_TWO)));
		pics.put(FieldModel.OPEN_THREE, new FileResource(new File(basepath+OPEN_THREE)));
		pics.put(FieldModel.OPEN_FOUR, new FileResource(new File(basepath+OPEN_FOUR)));
		pics.put(FieldModel.OPEN_FIVE, new FileResource(new File(basepath+OPEN_FIVE)));
		pics.put(FieldModel.OPEN_SIX, new FileResource(new File(basepath+OPEN_SIX)));
		pics.put(FieldModel.BLOCKED, new FileResource(new File(basepath+BLOCKED)));

	}
	public void init() {
		grid= new GridLayout(size, size);		
		mainLayout.addComponent(grid);
		for (int i = 0; i < size; i++) {
			for(int j=0;j<size;j++) {
				CellView cell=new CellView(i,j);
				cell.setWidth("10%");
				cell.setSource(pics.get(FieldModel.CLOSED_EMPTY));
				cells[i][j]=cell;
				grid.addComponent(cells[i][j],j,i);
			}
		}
		
		grid.addLayoutClickListener( new LayoutClickListener() {
			
			@Override
			public void layoutClick(LayoutClickEvent event) {
				Component com=event.getChildComponent();
				if ((event.isDoubleClick()) ){
					controller.cellClick((CellView) com, eCellAction.OPEN_NEIGHBORS);
				}
				else if(event.getButton()==MouseButton.LEFT) {
					controller.cellClick((CellView) com,eCellAction.OPEN);
				}
				else if (event.getButton()==MouseButton.RIGHT) {
					controller.cellClick((CellView) com,eCellAction.BLOCK);
				}
				
				
			}
		});
	}
}
