package com.vaadin.miner;

import java.io.File;

import javax.servlet.annotation.WebServlet;

import com.google.gwt.user.client.ui.ClickListener;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ClassResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Embedded;
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
		layout.addComponent(button);

		// Find the application directory
		String basepath = VaadinService.getCurrent()
		                  .getBaseDirectory().getAbsolutePath();

		// Show the image in the application
		final HorizontalLayout horLayout=new HorizontalLayout();
		horLayout.setMargin(true);
		setContent(horLayout);
		FileResource res= new FileResource(new File(basepath+"/WEB-INF/images/one.jpg"));
		horLayout.setWidth("50%");
		for(int i=0;i<10;i++) {		
			Image im=new Image("", res);
			//com.vaadin.event.MouseEvents.ClickListener listener=new com.vaadin.event.MouseEvents.ClickListener();
			//im.addClickListener(listener);
			horLayout.addComponent(new Image("", res));
		}
		
	}

}