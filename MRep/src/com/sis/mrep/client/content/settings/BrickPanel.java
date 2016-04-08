package com.sis.mrep.client.content.settings;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.cell.client.ButtonCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.sis.mrep.client.content.util.PanelUtil;
import com.sis.mrep.client.proxy.AdminManager;
import com.sis.mrep.client.proxy.AdminManagerAsync;
import com.sis.mrep.shared.FieldVerifier;
import com.sis.mrep.shared.model.Brick;
import com.sis.mrep.shared.model.Territory;

public class BrickPanel {

	// A simple data type that represents a contact.
	static Brick brick;
	// The list of data to display.
	private static List<Brick> Bricks = new ArrayList<Brick>();
	
	private static VerticalPanel vPanel = null;
	private static List<Brick> list;
	
	private static AdminManagerAsync adminService = GWT.create(AdminManager.class);
	
	public static void loadBrickPanel(){
		
		adminService.getBrickList("", new AsyncCallback<List<Brick>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(List<Brick> brickList) {
						Bricks = brickList;
						//getTerritoryPanel();
						PanelUtil.displayThisWidget(BrickPanel.getBrickPanel());
					}
		});
		
	}
	
	public static Widget getBrickPanel() {
		if (vPanel != null) {
			return vPanel;
		}else{
			vPanel = new VerticalPanel();
		}
			
		CellTable<Brick> brickTable = new CellTable<Brick>();
		SimplePager pager = new SimplePager();

		// Set the cellList as the display.
		// Create a CellTable.
		
		pager.setDisplay(brickTable);

		// Create name column.
		TextColumn<Brick> idColumn = new TextColumn<Brick>() {
			@Override
			public String getValue(Brick brick) {
				return "" + brick.getId();
			}
		};

		// Create name column.
		TextColumn<Brick> nameColumn = new TextColumn<Brick>() {
			@Override
			public String getValue(Brick brick) {
				return brick.getName();
			}
		};

		// Make the name column sortable.
		nameColumn.setSortable(true);

		// Create address column.
		TextColumn<Brick> territoryColumn = new TextColumn<Brick>() {
			@Override
			public String getValue(Brick brick) {
				return "" + brick.getTerritory();
			}
		};
		
		Column editColumn = addColumn(new ButtonCell(), "Button",
				new GetValue<String>() {
					@Override
					public String getValue(Brick p) {
						return "Edit";
					}
				}, new FieldUpdater<Brick, String>() {
					@Override
					public void update(int index, Brick p, String value) {
						PanelUtil.appendToCurrentWidget(getUpdateWidget(p));
					}
				});

		// Add the columns.
		brickTable.addColumn(idColumn, "Id");
		brickTable.addColumn(nameColumn, "Name");
		brickTable.addColumn(territoryColumn, "Territory");
		brickTable.addColumn(editColumn, "Edit");

		// Create a data provider.
		ListDataProvider<Brick> dataProvider = new ListDataProvider<Brick>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(brickTable);

		// Add the data to the data provider, which automatically pushes it to
		// the
		// widget.
		list = dataProvider.getList();
		for (Brick brick : Bricks) {
			list.add(brick);
		}

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<Brick> columnSortHandler = new ListHandler<Brick>(list);
		columnSortHandler.setComparator(nameColumn, new Comparator<Brick>() {
			public int compare(Brick o1, Brick o2) {
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
		brickTable.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted alphabetically by default.
		brickTable.getColumnSortList().push(nameColumn);
		
		
		
		
		
		 // Add a selection model to handle user selection.
//	    final SingleSelectionModel<Brick> selectionModel = new SingleSelectionModel<Brick>();
//	    brickTable.setSelectionModel(selectionModel);
//	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//	      public void onSelectionChange(SelectionChangeEvent event) {
//	        Brick brick = selectionModel.getSelectedObject();
//	        if (brick != null) {
//	        	PanelUtil.appendToCurrentWidget(getUpdateWidget(brick));
//	        }
//	      }
//	    });

		  
		  vPanel.add(brickTable);
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
	
	public static Widget getUpdateWidget(Brick brick){
		updatePanel.setVisible(true);
		final TextBox  brickId = new TextBox();
		final TextBox  brickName = new TextBox();
		final Label  brickNamev = new Label();
		brickId.setEnabled(false);
		if (brick != null){
			brickId.setText(""+brick.getId());
			brickName.setText(brick.getName());
		}
		
		 FlexTable layout = new FlexTable();
		    layout.setCellSpacing(6);
		    FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

		 // Add a normal button
		    Button saveButton = new Button(
			        "Save", new ClickHandler() {
			          public void onClick(ClickEvent event) {
			        	  if (!FieldVerifier.isValidName(brickName.getText())) {
			        		  brickNamev.setText("Please enter at least four characters");
								return;
							 }
			        	  int id = (brickId.getText().equals(""))? 0:Integer.parseInt(brickId.getText());
			        	  int terId = Integer.parseInt(dropBox.getValue(dropBox.getSelectedIndex()));
			        	  Brick brick = new Brick( id , brickName.getText(), terId);
			        	  adminService.saveBrick(brick, new AsyncCallback<Brick>(){

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Unable to obtain server response: "
									      + caught.getMessage());	
								
							}

							@Override
							public void onSuccess(Brick brick) {
								list.remove(brick);
								list.add(brick);
							}
			        		  
			        	  });

			        	  updatePanel.setVisible(false);
			          }
			        });

		    saveButton.ensureDebugId("brick Save Button");
		    
		 // Add a normal button
		    Button cancelButton = new Button(
		        "Cancel", new ClickHandler() {
		          public void onClick(ClickEvent event) {
		        	  brickNamev.setText("");
		        	  updatePanel.setVisible(false);
		          }
		        });
		    cancelButton.ensureDebugId("cwBasicButton-normal");
		    
		    // Add a title to the form
		    layout.setHTML(0, 0, "Brick Information");
		    cellFormatter.setColSpan(0, 0, 2);
		    cellFormatter.setHorizontalAlignment(
		        0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		    
		    
		    // Add some standard form options
		    layout.setHTML(1, 0, "Brick Id");
		    layout.setWidget(1, 1, brickId);
		    
		    layout.setHTML(2, 0, "Brick Name");
		    layout.setWidget(2, 1, brickName);
		    layout.setWidget(2, 2, brickNamev);
		    
		    layout.setHTML(3, 0, "Territory");
		    layout.setWidget(3, 1, dropBox);

		    layout.setWidget(4, 0, saveButton);
		    layout.setWidget(4, 1, cancelButton);
		    
		    // Wrap the content in a DecoratorPanel
		 
		    adminService.getTerritoryList("",new AsyncCallback<List<Territory>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(List<Territory> Territorys) {
						loadTerritoryListBox(Territorys);
						
					}
		});
		    
		    updatePanel.setWidget(layout);
		    
			return updatePanel;

	}
	
	private static ListBox dropBox = new ListBox(false);
	
	public static ListBox loadTerritoryListBox(List<Territory> Territorys){
		   dropBox.clear();
		   if(dropBox.getItemCount() <= 0){

			for (Territory territory : Territorys) {
				dropBox.addItem(territory.getName(), ""+territory.getId());
			}
		   }
			return dropBox;
	}
	
	private static <C> Column<Brick, C> addColumn(Cell<C> cell,
			String headerText, final GetValue<C> getter,
			FieldUpdater<Brick, C> fieldUpdater) {
		Column<Brick, C> column = new Column<Brick, C>(cell) {
			@Override
			public C getValue(Brick object) {
				return getter.getValue(object);
			}
		};
		column.setFieldUpdater(fieldUpdater);
		return column;
	}

	private static interface GetValue<C> {
		C getValue(Brick contact);
	}

}
