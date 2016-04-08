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
import com.sis.mrep.shared.model.Line;

public class LinePanel {

	// A simple data type that represents a contact.
	static Line line;
	// The list of data to display.
	private static List<Line> Lines = new ArrayList<Line>();
	
	private static VerticalPanel vPanel = null;
	private static List<Line> list;
	
	private static AdminManagerAsync adminService = GWT.create(AdminManager.class);
	
	public static void loadLinePanel(){
		
		adminService.getLineList("", new AsyncCallback<List<Line>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(List<Line> lineList) {
						Lines = lineList;
						//getTerritoryPanel();
						PanelUtil.displayThisWidget(LinePanel.getLinePanel());
					}
		});
		
	}
	
	interface Resources extends ClientBundle {
		  @Source("edit.gif")
		  ImageResource getImageResource();
		}
	
	
	public static Widget getLinePanel() {
		if (vPanel != null) {
			return vPanel;
		}else{
			vPanel = new VerticalPanel();
		}
			
		CellTable<Line> lineTable = new CellTable<Line>();
		SimplePager pager = new SimplePager();

		// Set the cellList as the display.
		// Create a CellTable.
		
		pager.setDisplay(lineTable);

		// Create name column.
		TextColumn<Line> idColumn = new TextColumn<Line>() {
			@Override
			public String getValue(Line line) {
				return "" + line.getId();
			}
		};

		// Create name column.
		TextColumn<Line> nameColumn = new TextColumn<Line>() {
			@Override
			public String getValue(Line line) {
				return line.getName();
			}
		};

		// Make the name column sortable.
		nameColumn.setSortable(true);

		
		

			final Resources resources = GWT.create(Resources.class);

//			Column<Line, ImageResource> editColumn =
//			  new Column<Line, ImageResource>(new ImageResourceCell()) {
//			    @Override
//			    public ImageResource getValue(Line object) {
//			      return resources.getImageResource();
//			    }
//			    
//			  };
//			  editColumn.setFieldUpdater(new FieldUpdater<Line, ImageResource>() {
//					@Override
//					public void update(int index, Line z, ImageResource value) {
//						//PanelUtil.appendToCurrentWidget(getUpdateWidget(p));
//						Window.alert("edit mode");
//					}
//				});
			  
			Column editColumn = addColumn(new ButtonCell(), "Button",
					new GetValue<String>() {
						@Override
						public String getValue(Line p) {
							return "Edit";
						}
					}, new FieldUpdater<Line, String>() {
						@Override
						public void update(int index, Line p, String value) {
							PanelUtil.appendToCurrentWidget(getUpdateWidget(p));
						}
					});

			Column deleteColumn = addColumn(new ButtonCell(), "Button",
					new GetValue<String>() {
						@Override
						public String getValue(Line p) {
							return "Delete";
						}
					}, new FieldUpdater<Line, String>() {
						@Override
						public void update(int index, Line p, String value) {
							PanelUtil.appendToCurrentWidget(getUpdateWidget(p));
						}
					});
		        

		// Add the columns.
		lineTable.addColumn(idColumn, "Id");
		lineTable.addColumn(nameColumn, "Name");
		lineTable.addColumn(editColumn, "Edit");
		//lineTable.addColumn(deleteColumn, "Remove");
		


		

		// Create a data provider.
		ListDataProvider<Line> dataProvider = new ListDataProvider<Line>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(lineTable);

		// Add the data to the data provider, which automatically pushes it to
		// the
		// widget.
		list = dataProvider.getList();
		for (Line line : Lines) {
			list.add(line);
		}

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<Line> columnSortHandler = new ListHandler<Line>(list);
		columnSortHandler.setComparator(nameColumn, new Comparator<Line>() {
			public int compare(Line o1, Line o2) {
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
		lineTable.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted alphabetically by default.
		lineTable.getColumnSortList().push(nameColumn);
		
		
		
		
		
		 // Add a selection model to handle user selection.
//	    final SingleSelectionModel<Line> selectionModel = new SingleSelectionModel<Line>();
//	    lineTable.setSelectionModel(selectionModel);
//	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//	      public void onSelectionChange(SelectionChangeEvent event) {
//	        Line line = selectionModel.getSelectedObject();
//	        if (line != null) {
//	        	PanelUtil.appendToCurrentWidget(getUpdateWidget(line));
//	        }
//	      }
//	    });

		  
		  vPanel.add(lineTable);
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
	
	public static Widget getUpdateWidget(Line line){
		updatePanel.setVisible(true);
		final TextBox  lineId = new TextBox();
		final TextBox  lineName = new TextBox();
		final Label  lineNamev = new Label();
		lineId.setEnabled(false);
		if (line != null){
			lineId.setText(""+line.getId());
			lineName.setText(line.getName());
		}
		
		 FlexTable layout = new FlexTable();
		    layout.setCellSpacing(6);
		    FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

		 // Add a normal button
		    Button saveButton = new Button(
			        "Save", new ClickHandler() {
			          public void onClick(ClickEvent event) {
			        	  if (!FieldVerifier.isValidName(lineName.getText())) {
			        		  lineNamev.setText("Please enter at least four characters");
								return;
							 }
			        	  int id = (lineId.getText().equals(""))? 0:Integer.parseInt(lineId.getText());
			        	  Line line = new Line( id , lineName.getText());
			        	  adminService.saveLine(line, new AsyncCallback<Line>(){

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Unable to obtain server response: "
									      + caught.getMessage());	
								
							}

							@Override
							public void onSuccess(Line line) {
								list.remove(line);
								list.add(line);
							}
			        		  
			        	  });

			        	  updatePanel.setVisible(false);
			          }
			        });

		    saveButton.ensureDebugId("line Save Button");
		    
		 // Add a normal button
		    Button cancelButton = new Button(
		        "Cancel", new ClickHandler() {
		          public void onClick(ClickEvent event) {
		        	  lineNamev.setText("");
		        	  updatePanel.setVisible(false);
		          }
		        });
		    cancelButton.ensureDebugId("cwBasicButton-normal");
		    
		    // Add a title to the form
		    layout.setHTML(0, 0, "Line Information");
		    cellFormatter.setColSpan(0, 0, 2);
		    cellFormatter.setHorizontalAlignment(
		        0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		    
		    
		    // Add some standard form options
		    layout.setHTML(1, 0, "Line Id");
		    layout.setWidget(1, 1, lineId);
		    
		    layout.setHTML(2, 0, "Line Name");
		    layout.setWidget(2, 1, lineName);
		    layout.setWidget(2, 2, lineNamev);
		    
		    layout.setWidget(4, 0, saveButton);
		    layout.setWidget(4, 1, cancelButton);
		    
		    updatePanel.setWidget(layout);
		    
			return updatePanel;

	}
	
	private static <C> Column<Line, C> addColumn(Cell<C> cell,
			String headerText, final GetValue<C> getter,
			FieldUpdater<Line, C> fieldUpdater) {
		Column<Line, C> column = new Column<Line, C>(cell) {
			@Override
			public C getValue(Line object) {
				return getter.getValue(object);
			}
		};
		column.setFieldUpdater(fieldUpdater);
		return column;
	}

	private static interface GetValue<C> {
		C getValue(Line contact);
	}

	
	

}
