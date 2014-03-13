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
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		Button button = new Button("Click Me");
		button.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				layout.addComponent(new Label("Thank you for clicking"));
			}
		});
		
	    Button sample = new Button("Click");
        sample.addClickListener(new Button.ClickListener() {
			
			@Override
			public void buttonClick(ClickEvent event) {
				// TODO Auto-generated method stub
				
			}
        });
		layout.addComponent(button);

		// Find the application directory
		String basepath = VaadinService.getCurrent()
		                  .getBaseDirectory().getAbsolutePath();

		// Show the image in the application
		final HorizontalLayout horLayout=new HorizontalLayout();
		horLayout.setMargin(true);
		setContent(horLayout);
		FileResource res= new FileResource(new File(basepath+"/WEB-INF/images/dum3.png"));
		final FileResource res2= new FileResource(new File(basepath+"/WEB-INF/images/dum2.png"));
		final FileResource res3= new FileResource(new File(basepath+"/WEB-INF/images/dum3.png"));
		//	horLayout.setWidth("50%");
		// Fill out the first column using coordinates.
		GridLayout grid = new GridLayout(4, 4);
		Image[][] images=new Image[4][4];
		setContent(grid);
		for (int i = 0; i < 4; i++) {
			Image im=new Image();
			images[0][i]=im;
			images[0][i].setSource(res);
		    grid.addComponent(images[0][i], i, 0);
		}
		
		for(int i=0;i<4;i++) {		
			Image im=new Image();
			im.setSource(res2);
			grid.addComponent(im, i, 1);
		}
		images[0][2].setSource(res2);	
	    
		grid.addLayoutClickListener( new LayoutClickListener() {
			
			@Override
			public void layoutClick(LayoutClickEvent event) {
				// TODO Auto-generated method stub
				Component com=event.getChildComponent();
				((Image) com).setSource(res2);
			}
		});
		
	}

}