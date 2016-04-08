package com.sis.mrep.client.content.settings;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.sis.mrep.client.content.util.PanelUtil;
import com.sis.mrep.client.proxy.AdminManager;
import com.sis.mrep.client.proxy.AdminManagerAsync;

import com.sis.mrep.shared.FieldVerifier;
import com.sis.mrep.shared.model.Territory;
import com.sis.mrep.shared.model.Zone;
import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class TerritoryPanel {


	// A simple data type that represents a contact.
	static Territory territory;
	// The list of data to display.
	private static List<Territory> Territorys = new ArrayList<Territory>();
	
	private static VerticalPanel vPanel = null;
	private static List<Territory> list;
	private static AdminManagerAsync adminService = GWT.create(AdminManager.class);
	
	public static void loadTerritoryPanel(){
		
		adminService.getTerritoryList("", new AsyncCallback<List<Territory>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(List<Territory> territoryList) {
						Territorys = territoryList;
						//getTerritoryPanel();
						PanelUtil.displayThisWidget(TerritoryPanel.getTerritoryPanel());
					}
		});
		
	}
	

	public static Widget getTerritoryPanel() {
		if (vPanel != null) {
			return vPanel;
		}else{
			vPanel = new VerticalPanel();
		}
			
		CellTable<Territory> territoryTable = new CellTable<Territory>();
		SimplePager pager = new SimplePager();

		// Set the cellList as the display.
		// Create a CellTable.
		
		pager.setDisplay(territoryTable);

		// Create name column.
		TextColumn<Territory> idColumn = new TextColumn<Territory>() {
			@Override
			public String getValue(Territory territory) {
				return "" + territory.getId();
			}
		};

		// Create name column.
		TextColumn<Territory> nameColumn = new TextColumn<Territory>() {
			@Override
			public String getValue(Territory territory) {
				return territory.getName();
			}
		};

		// Make the name column sortable.
		nameColumn.setSortable(true);

		// Create address column.
		TextColumn<Territory> zoneColumn = new TextColumn<Territory>() {
			@Override
			public String getValue(Territory territory) {
				return "" + territory.getZone();
			}
		};
		Column editColumn = addColumn(new ButtonCell(), "Button",
				new GetValue<String>() {
					@Override
					public String getValue(Territory p) {
						return "Edit";
					}
				}, new FieldUpdater<Territory, String>() {
					@Override
					public void update(int index, Territory p, String value) {
						PanelUtil.appendToCurrentWidget(getUpdateWidget(p));
					}
				});

		// Add the columns.
		territoryTable.addColumn(idColumn, "Id");
		territoryTable.addColumn(nameColumn, "Name");
		territoryTable.addColumn(zoneColumn, "Zone");
		territoryTable.addColumn(editColumn, "Edit");

		// Create a data provider.
		ListDataProvider<Territory> dataProvider = new ListDataProvider<Territory>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(territoryTable);

		// Add the data to the data provider, which automatically pushes it to
		// the
		// widget.
		list = dataProvider.getList();
		for (Territory territory : Territorys) {
			list.add(territory);
		}

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<Territory> columnSortHandler = new ListHandler<Territory>(list);
		columnSortHandler.setComparator(nameColumn, new Comparator<Territory>() {
			public int compare(Territory o1, Territory o2) {
				if (o1 == o2) {
					return 0;
				}

				// Compare the name columns.
				if (o1 != null) {
					return (o2 != null) ? o1.getName().compareTo(o2.getName())
							: 1;
				}
				return -1;
			}
		});
		territoryTable.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted alphabetically by default.
		territoryTable.getColumnSortList().push(nameColumn);
		
		
		
		
		
		 // Add a selection model to handle user selection.
//	    final SingleSelectionModel<Territory> selectionModel = new SingleSelectionModel<Territory>();
//	    territoryTable.setSelectionModel(selectionModel);
//	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//	      public void onSelectionChange(SelectionChangeEvent event) {
//	        Territory territory = selectionModel.getSelectedObject();
//	        if (territory != null) {
//	        	PanelUtil.appendToCurrentWidget(getUpdateWidget(territory));
//	        }
//	      }
//	    });

		  
		  vPanel.add(territoryTable);
		  vPanel.add(pager);
		  Anchor addNew = new Anchor("Add new");
		  addNew.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				PanelUtil.appendToCurrentWidget(getUpdateWidget(null));
			}
		});
		  vPanel.add(addNew);
		    
		return vPanel;
	}
	
	
	final static DecoratorPanel updatePanel = new DecoratorPanel();
	
	public static Widget getUpdateWidget(Territory territory){
		updatePanel.setVisible(true);
		final TextBox  territoryId = new TextBox();
		final TextBox  territoryName = new TextBox();
		final Label  territoryNamev = new Label();
		territoryId.setEnabled(false);
		if (territory != null){
			territoryId.setText(""+territory.getId());
			territoryName.setText(territory.getName());
		}
		
		 FlexTable layout = new FlexTable();
		    layout.setCellSpacing(6);
		    FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

		 // Add a normal button
		    Button saveButton = new Button(
		        "Save", new ClickHandler() {
		          public void onClick(ClickEvent event) {
		        	  if (!FieldVerifier.isValidName(territoryName.getText())) {
		        		  territoryNamev.setText("Please enter at least four characters");
							return;
						 }
		        	  int id = (territoryId.getText().equals(""))? 0:Integer.parseInt(territoryId.getText());
		        	  int zoneId = Integer.parseInt(dropBox.getValue(dropBox.getSelectedIndex()));
		        	  Territory t = new Territory( id , territoryName.getText(), zoneId);
		        	  adminService.saveTerritory(t, new AsyncCallback<Territory>(){

						@Override
						public void onFailure(Throwable caught) {
							Window.alert("Unable to obtain server response: "
								      + caught.getMessage());	
							
						}

						@Override
						public void onSuccess(Territory t) {
							territoryNamev.setText("");
							list.remove(t);
							list.add(t);
						}
		        		  
		        	  });

		        	  updatePanel.setVisible(false);
		          }
		        });
		    saveButton.ensureDebugId("Zone Save Button");
		    
		 // Add a normal button
		    Button cancelButton = new Button(
		        "Cancel", new ClickHandler() {
		          public void onClick(ClickEvent event) {
		        	  territoryNamev.setText("");
		        	  updatePanel.setVisible(false);
		          }
		        });
		    cancelButton.ensureDebugId("cwBasicButton-normal");
		    
		    // Add a title to the form
		    layout.setHTML(0, 0, "Territory Information");
		    cellFormatter.setColSpan(0, 0, 2);
		    cellFormatter.setHorizontalAlignment(
		        0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		    
		    
		    // Add some standard form options
		    layout.setHTML(1, 0, "Territory Id");
		    layout.setWidget(1, 1, territoryId);
		    
		    layout.setHTML(2, 0, "Territory Name");
		    layout.setWidget(2, 1, territoryName);
		    layout.setWidget(2, 2, territoryNamev);
		    
		    layout.setHTML(3, 0, "Zone");
		    layout.setWidget(3, 1, dropBox);
		    
		    layout.setWidget(4, 0, saveButton);
		    layout.setWidget(4, 1, cancelButton);
		    
		    // Wrap the content in a DecoratorPanel
		    
		    adminService.getZoneList("",new AsyncCallback<List<Zone>>() {

				@Override
				public void onFailure(Throwable caught) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onSuccess(List<Zone> Zones) {
					loadZoneListBox(Zones);
					
				}
	});
		 
		    updatePanel.setWidget(layout);
			
			return updatePanel;

	}
	
	static ListBox dropBox = new ListBox(false);
	
	public static ListBox loadZoneListBox(List<Zone> Zones){
		dropBox.clear();
		if(dropBox.getItemCount() <= 0){
			for (Zone zone : Zones) {
				dropBox.addItem(zone.getName(), ""+zone.getId());
			}
		}
			return dropBox;
	}
	
	
	private static <C> Column<Territory, C> addColumn(Cell<C> cell,
			String headerText, final GetValue<C> getter,
			FieldUpdater<Territory, C> fieldUpdater) {
		Column<Territory, C> column = new Column<Territory, C>(cell) {
			@Override
			public C getValue(Territory object) {
				return getter.getValue(object);
			}
		};
		column.setFieldUpdater(fieldUpdater);
		return column;
	}

	private static interface GetValue<C> {
		C getValue(Territory contact);
	}
	
}
