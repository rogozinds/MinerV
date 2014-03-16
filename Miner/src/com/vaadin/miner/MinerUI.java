package com.vaadin.miner;

import java.awt.event.ComponentEvent;
import java.io.File;

import javax.servlet.annotation.WebServlet;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.event.LayoutEvents.LayoutClickEvent;
import com.vaadin.event.LayoutEvents.LayoutClickListener;
import com.vaadin.miner.controllers.FieldController;
import com.vaadin.miner.models.FieldModel;
import com.vaadin.miner.views.FieldView;
import com.vaadin.server.ClassResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("miner")
public class MinerUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = MinerUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		
		MainWindow mw= new MainWindow();
		setContent(mw);
		
		/*
		FieldModel model=new FieldModel(10,10);
		model.createField();
		FieldController controller=new FieldController(model);
		FieldView view = new FieldView(model, controller, mw.getLayout());
		view.init();
		model.setView(view);
		*/
	}

}