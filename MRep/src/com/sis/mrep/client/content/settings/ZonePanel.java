package com.sis.mrep.client.content.settings;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ImageResourceCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
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
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.sis.mrep.client.content.util.PanelUtil;
import com.sis.mrep.client.proxy.AdminManager;
import com.sis.mrep.client.proxy.AdminManagerAsync;

import com.sis.mrep.shared.FieldVerifier;
import com.sis.mrep.shared.model.Zone;

public class ZonePanel {

	// A simple data type that represents a contact.
	static Zone zone;
	// The list of data to display.
	private static List<Zone> Zones = new ArrayList<Zone>();
	
	private static VerticalPanel vPanel = null;
	private static List<Zone> list;
	
	private static AdminManagerAsync adminService = GWT.create(AdminManager.class);
	
	public static void loadZonePanel(){
		
		adminService.getZoneList("", new AsyncCallback<List<Zone>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(List<Zone> zoneList) {
						Zones = zoneList;
						//getTerritoryPanel();
						PanelUtil.displayThisWidget(ZonePanel.getZonePanel());
					}
		});
		
	}
	
	interface Resources extends ClientBundle {
		  @Source("edit.gif")
		  ImageResource getImageResource();
		}
	
	
	public static Widget getZonePanel() {
		if (vPanel != null) {
			return vPanel;
		}else{
			vPanel = new VerticalPanel();
		}
			
		CellTable<Zone> zoneTable = new CellTable<Zone>();
		SimplePager pager = new SimplePager();

		// Set the cellList as the display.
		// Create a CellTable.
		
		pager.setDisplay(zoneTable);

		// Create name column.
		TextColumn<Zone> idColumn = new TextColumn<Zone>() {
			@Override
			public String getValue(Zone zone) {
				return "" + zone.getId();
			}
		};

		// Create name column.
		TextColumn<Zone> nameColumn = new TextColumn<Zone>() {
			@Override
			public String getValue(Zone zone) {
				return zone.getName();
			}
		};

		// Make the name column sortable.
		nameColumn.setSortable(true);

		
		

			final Resources resources = GWT.create(Resources.class);

//			Column<Zone, ImageResource> editColumn =
//			  new Column<Zone, ImageResource>(new ImageResourceCell()) {
//			    @Override
//			    public ImageResource getValue(Zone object) {
//			      return resources.getImageResource();
//			    }
//			    
//			  };
//			  editColumn.setFieldUpdater(new FieldUpdater<Zone, ImageResource>() {
//					@Override
//					public void update(int index, Zone z, ImageResource value) {
//						//PanelUtil.appendToCurrentWidget(getUpdateWidget(p));
//						Window.alert("edit mode");
//					}
//				});
			  
			Column editColumn = addColumn(new ButtonCell(), "Button",
					new GetValue<String>() {
						@Override
						public String getValue(Zone p) {
							return "Edit";
						}
					}, new FieldUpdater<Zone, String>() {
						@Override
						public void update(int index, Zone p, String value) {
							PanelUtil.appendToCurrentWidget(getUpdateWidget(p));
						}
					});

			Column deleteColumn = addColumn(new ButtonCell(), "Button",
					new GetValue<String>() {
						@Override
						public String getValue(Zone p) {
							return "Delete";
						}
					}, new FieldUpdater<Zone, String>() {
						@Override
						public void update(int index, Zone p, String value) {
							PanelUtil.appendToCurrentWidget(getUpdateWidget(p));
						}
					});
		        

		// Add the columns.
		zoneTable.addColumn(idColumn, "Id");
		zoneTable.addColumn(nameColumn, "Name");
		zoneTable.addColumn(editColumn, "Edit");
		//zoneTable.addColumn(deleteColumn, "Remove");
		


		

		// Create a data provider.
		ListDataProvider<Zone> dataProvider = new ListDataProvider<Zone>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(zoneTable);

		// Add the data to the data provider, which automatically pushes it to
		// the
		// widget.
		list = dataProvider.getList();
		for (Zone zone : Zones) {
			list.add(zone);
		}

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<Zone> columnSortHandler = new ListHandler<Zone>(list);
		columnSortHandler.setComparator(nameColumn, new Comparator<Zone>() {
			public int compare(Zone o1, Zone o2) {
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
		zoneTable.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted alphabetically by default.
		zoneTable.getColumnSortList().push(nameColumn);
		
		
		
		
		
		 // Add a selection model to handle user selection.
//	    final SingleSelectionModel<Zone> selectionModel = new SingleSelectionModel<Zone>();
//	    zoneTable.setSelectionModel(selectionModel);
//	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//	      public void onSelectionChange(SelectionChangeEvent event) {
//	        Zone zone = selectionModel.getSelectedObject();
//	        if (zone != null) {
//	        	PanelUtil.appendToCurrentWidget(getUpdateWidget(zone));
//	        }
//	      }
//	    });

		  
		  vPanel.add(zoneTable);
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
	
	public static Widget getUpdateWidget(Zone zone){
		updatePanel.setVisible(true);
		final TextBox  zoneId = new TextBox();
		final TextBox  zoneName = new TextBox();
		final Label  zoneNamev = new Label();
		zoneId.setEnabled(false);
		if (zone != null){
			zoneId.setText(""+zone.getId());
			zoneName.setText(zone.getName());
		}
		
		 FlexTable layout = new FlexTable();
		    layout.setCellSpacing(6);
		    FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

		 // Add a normal button
		    Button saveButton = new Button(
			        "Save", new ClickHandler() {
			          public void onClick(ClickEvent event) {
			        	  if (!FieldVerifier.isValidName(zoneName.getText())) {
			        		  zoneNamev.setText("Please enter at least four characters");
								return;
							 }
			        	  int id = (zoneId.getText().equals(""))? 0:Integer.parseInt(zoneId.getText());
			        	  Zone zone = new Zone( id , zoneName.getText());
			        	  adminService.saveZone(zone, new AsyncCallback<Zone>(){

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Unable to obtain server response: "
									      + caught.getMessage());	
								
							}

							@Override
							public void onSuccess(Zone zone) {
								list.remove(zone);
								list.add(zone);
							}
			        		  
			        	  });

			        	  updatePanel.setVisible(false);
			          }
			        });

		    saveButton.ensureDebugId("zone Save Button");
		    
		 // Add a normal button
		    Button cancelButton = new Button(
		        "Cancel", new ClickHandler() {
		          public void onClick(ClickEvent event) {
		        	  zoneNamev.setText("");
		        	  updatePanel.setVisible(false);
		          }
		        });
		    cancelButton.ensureDebugId("cwBasicButton-normal");
		    
		    // Add a title to the form
		    layout.setHTML(0, 0, "Zone Information");
		    cellFormatter.setColSpan(0, 0, 2);
		    cellFormatter.setHorizontalAlignment(
		        0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		    
		    
		    // Add some standard form options
		    layout.setHTML(1, 0, "Zone Id");
		    layout.setWidget(1, 1, zoneId);
		    
		    layout.setHTML(2, 0, "Zone Name");
		    layout.setWidget(2, 1, zoneName);
		    layout.setWidget(2, 2, zoneNamev);
		    
		    layout.setWidget(4, 0, saveButton);
		    layout.setWidget(4, 1, cancelButton);
		    
		    updatePanel.setWidget(layout);
		    
			return updatePanel;

	}
	
	private static <C> Column<Zone, C> addColumn(Cell<C> cell,
			String headerText, final GetValue<C> getter,
			FieldUpdater<Zone, C> fieldUpdater) {
		Column<Zone, C> column = new Column<Zone, C>(cell) {
			@Override
			public C getValue(Zone object) {
				return getter.getValue(object);
			}
		};
		column.setFieldUpdater(fieldUpdater);
		return column;
	}

	private static interface GetValue<C> {
		C getValue(Zone contact);
	}

	
	

}
