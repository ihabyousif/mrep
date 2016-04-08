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
import com.sis.mrep.client.content.util.PanelUtil;
import com.sis.mrep.client.proxy.AdminManager;
import com.sis.mrep.client.proxy.AdminManagerAsync;
import com.sis.mrep.shared.FieldVerifier;
import com.sis.mrep.shared.model.Product;
import com.sis.mrep.shared.model.Line;

public class ProductPanel {

	// A simple data type that represents a contact.
	static Product product;
	// The list of data to display.
	private static List<Product> Products = new ArrayList<Product>();
	
	private static VerticalPanel vPanel = null;
	private static List<Product> list;
	
	private static AdminManagerAsync adminService = GWT.create(AdminManager.class);
	
	public static void loadProductPanel(){
		
		adminService.getProductList("", new AsyncCallback<List<Product>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(List<Product> productList) {
						Products = productList;
						//getLinePanel();
						PanelUtil.displayThisWidget(ProductPanel.getProductPanel());
					}
		});
		
	}
	
	public static Widget getProductPanel() {
		if (vPanel != null) {
			return vPanel;
		}else{
			vPanel = new VerticalPanel();
		}
			
		CellTable<Product> productTable = new CellTable<Product>();
		SimplePager pager = new SimplePager();

		// Set the cellList as the display.
		// Create a CellTable.
		
		pager.setDisplay(productTable);

		// Create name column.
		TextColumn<Product> idColumn = new TextColumn<Product>() {
			@Override
			public String getValue(Product product) {
				return "" + product.getId();
			}
		};

		// Create name column.
		TextColumn<Product> nameColumn = new TextColumn<Product>() {
			@Override
			public String getValue(Product product) {
				return product.getName();
			}
		};

		// Make the name column sortable.
		nameColumn.setSortable(true);

		// Create address column.
		TextColumn<Product> territoryColumn = new TextColumn<Product>() {
			@Override
			public String getValue(Product product) {
				return "" + product.getLine();
			}
		};
		
		Column editColumn = addColumn(new ButtonCell(), "Button",
				new GetValue<String>() {
					@Override
					public String getValue(Product p) {
						return "Edit";
					}
				}, new FieldUpdater<Product, String>() {
					@Override
					public void update(int index, Product p, String value) {
						PanelUtil.appendToCurrentWidget(getUpdateWidget(p));
					}
				});

		// Add the columns.
		productTable.addColumn(idColumn, "Id");
		productTable.addColumn(nameColumn, "Name");
		productTable.addColumn(territoryColumn, "Line");
		productTable.addColumn(editColumn, "Edit");

		// Create a data provider.
		ListDataProvider<Product> dataProvider = new ListDataProvider<Product>();

		// Connect the table to the data provider.
		dataProvider.addDataDisplay(productTable);

		// Add the data to the data provider, which automatically pushes it to
		// the
		// widget.
		list = dataProvider.getList();
		for (Product product : Products) {
			list.add(product);
		}

		// Add a ColumnSortEvent.ListHandler to connect sorting to the
		// java.util.List.
		ListHandler<Product> columnSortHandler = new ListHandler<Product>(list);
		columnSortHandler.setComparator(nameColumn, new Comparator<Product>() {
			public int compare(Product o1, Product o2) {
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
		productTable.addColumnSortHandler(columnSortHandler);

		// We know that the data is sorted alphabetically by default.
		productTable.getColumnSortList().push(nameColumn);
		
		
		
		
		
		 // Add a selection model to handle user selection.
//	    final SingleSelectionModel<Product> selectionModel = new SingleSelectionModel<Product>();
//	    productTable.setSelectionModel(selectionModel);
//	    selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//	      public void onSelectionChange(SelectionChangeEvent event) {
//	        Product product = selectionModel.getSelectedObject();
//	        if (product != null) {
//	        	PanelUtil.appendToCurrentWidget(getUpdateWidget(product));
//	        }
//	      }
//	    });

		  
		  vPanel.add(productTable);
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
	
	public static Widget getUpdateWidget(Product product){
		updatePanel.setVisible(true);
		final TextBox  productId = new TextBox();
		final TextBox  productName = new TextBox();
		final Label  productNamev = new Label();
		productId.setEnabled(false);
		if (product != null){
			productId.setText(""+product.getId());
			productName.setText(product.getName());
		}
		
		 FlexTable layout = new FlexTable();
		    layout.setCellSpacing(6);
		    FlexCellFormatter cellFormatter = layout.getFlexCellFormatter();

		 // Add a normal button
		    Button saveButton = new Button(
			        "Save", new ClickHandler() {
			          public void onClick(ClickEvent event) {
			        	  if (!FieldVerifier.isValidName(productName.getText())) {
			        		  productNamev.setText("Please enter at least four characters");
								return;
							 }
			        	  int id = (productId.getText().equals(""))? 0:Integer.parseInt(productId.getText());
			        	  int terId = Integer.parseInt(dropBox.getValue(dropBox.getSelectedIndex()));
			        	  Product product = new Product( id , productName.getText(), terId);
			        	  adminService.saveProduct(product, new AsyncCallback<Product>(){

							@Override
							public void onFailure(Throwable caught) {
								Window.alert("Unable to obtain server response: "
									      + caught.getMessage());	
								
							}

							@Override
							public void onSuccess(Product product) {
								list.remove(product);
								list.add(product);
							}
			        		  
			        	  });

			        	  updatePanel.setVisible(false);
			          }
			        });

		    saveButton.ensureDebugId("product Save Button");
		    
		 // Add a normal button
		    Button cancelButton = new Button(
		        "Cancel", new ClickHandler() {
		          public void onClick(ClickEvent event) {
		        	  productNamev.setText("");
		        	  updatePanel.setVisible(false);
		          }
		        });
		    cancelButton.ensureDebugId("cwBasicButton-normal");
		    
		    // Add a title to the form
		    layout.setHTML(0, 0, "Product Information");
		    cellFormatter.setColSpan(0, 0, 2);
		    cellFormatter.setHorizontalAlignment(
		        0, 0, HasHorizontalAlignment.ALIGN_CENTER);

		    
		    
		    // Add some standard form options
		    layout.setHTML(1, 0, "Product Id");
		    layout.setWidget(1, 1, productId);
		    
		    layout.setHTML(2, 0, "Product Name");
		    layout.setWidget(2, 1, productName);
		    layout.setWidget(2, 2, productNamev);
		    
		    layout.setHTML(3, 0, "Line");
		    layout.setWidget(3, 1, dropBox);

		    layout.setWidget(4, 0, saveButton);
		    layout.setWidget(4, 1, cancelButton);
		    
		    // Wrap the content in a DecoratorPanel
		 
		    adminService.getLineList("",new AsyncCallback<List<Line>>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void onSuccess(List<Line> Lines) {
						loadLineListBox(Lines);
						
					}
		});
		    
		    updatePanel.setWidget(layout);
		    
			return updatePanel;

	}
	
	private static ListBox dropBox = new ListBox(false);
	
	public static ListBox loadLineListBox(List<Line> Lines){
		   dropBox.clear();
		   if(dropBox.getItemCount() <= 0){

			for (Line territory : Lines) {
				dropBox.addItem(territory.getName(), ""+territory.getId());
			}
		   }
			return dropBox;
	}
	
	private static <C> Column<Product, C> addColumn(Cell<C> cell,
			String headerText, final GetValue<C> getter,
			FieldUpdater<Product, C> fieldUpdater) {
		Column<Product, C> column = new Column<Product, C>(cell) {
			@Override
			public C getValue(Product object) {
				return getter.getValue(object);
			}
		};
		column.setFieldUpdater(fieldUpdater);
		return column;
	}

	private static interface GetValue<C> {
		C getValue(Product contact);
	}

}
