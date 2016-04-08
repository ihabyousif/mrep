package com.sis.mrep.client.content.plan;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.DisclosurePanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sis.mrep.client.content.util.StaticMaps;
import com.sis.mrep.client.proxy.EmployeeManager;
import com.sis.mrep.client.proxy.EmployeeManagerAsync;
import com.sis.mrep.shared.model.Call;
import com.sis.mrep.shared.model.PlanItem;

public class AddCallPanel {


	private static EmployeeManagerAsync adminService = GWT
			.create(EmployeeManager.class);
	
	private static VerticalPanel vAddCallPanel = null;
	
	public static Widget getAddCallPanel(final PlanItem pi) {
		if (vAddCallPanel != null) {
			return vAddCallPanel;
		} else {
			vAddCallPanel = new VerticalPanel();
		}
		
		//////////////customer info
		FlexTable cilayout = new FlexTable();
		cilayout.setCellSpacing(6);
		FlexCellFormatter cellFormatter = cilayout.getFlexCellFormatter();
		cilayout.setHTML(0, 0, "Call Information");
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);
		
		
		cilayout.setHTML(1, 0, "Id :");
		cilayout.setWidget(1, 1, new Label(""+pi.getCusId()));
		
		cilayout.setHTML(1, 2, "Name :");
		cilayout.setWidget(1, 3, new Label(pi.getCusName()));
		
		cilayout.setHTML(2, 0, "Tier :");
		cilayout.setWidget(2, 1, new Label(StaticMaps.tierMap.get(pi.getCusTier())));
		
		cilayout.setHTML(2, 2, "Brick :");
		cilayout.setWidget(2, 3, new Label(""+pi.getBrick()));
		
		cilayout.setHTML(2, 4, "Speciality :");
		cilayout.setWidget(2, 5, new Label(StaticMaps.specialityMap.get(pi.getCusSpeciality())));
		DecoratorPanel dp1 = new DecoratorPanel();
		dp1.add(cilayout);

		////////////////fields
	    final TextArea message = new TextArea();
	    message.setVisibleLines(5);
	    
	    final ListBox companyFeedBackLB = StaticMaps.getCompanyFeedback();

	    final TextArea pharmFeedback = new TextArea();
	    message.setVisibleLines(5);
	    
	    
	    final CheckBox samplesCB = new CheckBox("samples");
	    final TextBox numberOfSamples = new TextBox();
	    numberOfSamples.setEnabled(false);
	    samplesCB.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			
				numberOfSamples.setEnabled(samplesCB.getValue());
				
			}
		});
	    
	    final CheckBox materialsCB = new CheckBox("Materials");
	    final ListBox materialsLB = StaticMaps.getMaterials();
	    materialsLB.setEnabled(false);
	    
	    materialsCB.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			
				materialsLB.setEnabled(materialsCB.getValue());
				
			}
		});
	    
		///////////Brands and Message
		FlexTable msgLayout = new FlexTable();
		msgLayout.setCellSpacing(6);
		FlexCellFormatter cellFormatter2 = msgLayout.getFlexCellFormatter();
		msgLayout.setHTML(0, 0, "Brand & Message");
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);
		
		msgLayout.setWidget(1, 0, getProductsPanel());
		cellFormatter.setColSpan(1, 0, 2);
		
		msgLayout.setHTML(2, 0, "Message :");
		msgLayout.setWidget(2, 1, message);
		cellFormatter.setColSpan(2, 0, 2);

		DecoratorPanel dp2 = new DecoratorPanel();
		dp2.add(msgLayout);
		///////////// feedback

		
		FlexTable fbLayout = new FlexTable();
		fbLayout.setCellSpacing(6);
		FlexCellFormatter cellFormatter3 = fbLayout.getFlexCellFormatter();
		fbLayout.setHTML(0, 0, "feed back");
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);
		
		fbLayout.setHTML(1, 0, "Company feed back :");
		fbLayout.setWidget(1, 1, companyFeedBackLB);
		
		fbLayout.setHTML(2, 0, "nearst pharmacy :");
		fbLayout.setWidget(2, 1, new Label("nearst pharmacy"));

		fbLayout.setHTML(2, 0, "Feedback :");
		fbLayout.setWidget(2, 1, pharmFeedback);
		
		DecoratorPanel dp3 = new DecoratorPanel();
		dp3.add(fbLayout);
		
		///////// Adv part
		FlexTable sLayout = new FlexTable();
		sLayout.setCellSpacing(6);
		FlexCellFormatter cellFormatter4 = sLayout.getFlexCellFormatter();
		sLayout.setHTML(0, 0, "Adv");
		cellFormatter.setColSpan(0, 0, 2);
		cellFormatter.setHorizontalAlignment(0, 0,
				HasHorizontalAlignment.ALIGN_CENTER);
		
		sLayout.setHTML(1, 0, "Samples :");
		sLayout.setWidget(1, 1, samplesCB);
		sLayout.setWidget(1, 2, numberOfSamples);
		
		sLayout.setHTML(2, 0, "Flayer Materials :");
		sLayout.setWidget(2, 1, materialsCB);
		sLayout.setWidget(2, 2, materialsLB);

		DecoratorPanel dp4 = new DecoratorPanel();
		dp4.add(sLayout);
		//HTML label = new HTML(new SafeHtmlBuilder().appendEscapedLines("<hr>").toSafeHtml());
		
		////////////////// buttons 
	    FlexTable buttonLayout = new FlexTable();
	    buttonLayout.setCellSpacing(6);
		FlexCellFormatter cellFormatter5 = buttonLayout.getFlexCellFormatter();
		
		DecoratorPanel dp5 = new DecoratorPanel();
		dp5.setWidget(buttonLayout);

		Button saveButton = new Button(
		        "Save", new ClickHandler() {
		          public void onClick(ClickEvent event) {
		        	  Call c = new Call();
		        	  c.setPiId(pi.getId());
		        	  c.setComment(message.getText());
		        	  c.setCompanyFeedback(Integer.parseInt(companyFeedBackLB.getValue(companyFeedBackLB.getSelectedIndex())));
		        	  c.setNphId(1);
		        	  c.setNphFeedback(pharmFeedback.getText());
		        	  //samplesCB / numberOfSamples / materialsCB / materialsLB
		        	  c.setSamples(samplesCB.getValue()?1:0);
		        	  c.setPromMaterials(materialsCB.getValue()?1:0);
		        	  
		        	  adminService.saveCall(c , new AsyncCallback<Call>() {

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("error in saving the call");
						}

						@Override
						public void onSuccess(Call result) {
							Window.alert("call saved");
						}
					});
		        	  vAddCallPanel.setVisible(false);
		            
		          }
		        });
		    

		// Add a close button at the bottom of the dialog
	    Button closeButton = new Button(
	        "Close", new ClickHandler() {
	          public void onClick(ClickEvent event) {
	        	  vAddCallPanel.setVisible(false);
	          }
	        });
	    
	    buttonLayout.setWidget(4, 2, saveButton);
	    buttonLayout.setWidget(4, 3, closeButton);

		dp1.setWidth("500px");
		dp2.setWidth("500px");
		dp3.setWidth("500px");
		dp4.setWidth("500px");
		dp5.setWidth("500px");
		
		vAddCallPanel.add(dp1);
		vAddCallPanel.add(dp2);
		vAddCallPanel.add(dp3);
		vAddCallPanel.add(dp4);
		vAddCallPanel.add(dp5);
		
		return vAddCallPanel;
	}

	
	
	
	private static DisclosurePanel getProductsPanel(){
	    Grid advancedOptions = new Grid(2, 2);
	    advancedOptions.setCellSpacing(6);
	    advancedOptions.setWidget(0, 0, new CheckBox("product1"));
	    advancedOptions.setWidget(0, 1, new CheckBox("product2"));
	    advancedOptions.setWidget(1, 0, new CheckBox("product3"));
	    advancedOptions.setWidget(1, 1, new CheckBox("product4"));

	    // Add advanced options to form in a disclosure panel
	    DisclosurePanel advancedDisclosure = new DisclosurePanel(
	        "Products");
	    advancedDisclosure.setAnimationEnabled(true);

	    advancedDisclosure.setContent(advancedOptions);
	    
	    return advancedDisclosure;
	}
	
	
	
}
