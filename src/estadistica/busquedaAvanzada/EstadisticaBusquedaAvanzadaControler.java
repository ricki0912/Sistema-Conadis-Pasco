package estadistica.busquedaAvanzada;


import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jfoenix.controls.JFXButton;
//import com.qoppa.b.d.m;

import funciones.Conexion;
import funciones.Funciones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
	

public class EstadisticaBusquedaAvanzadaControler extends Funciones implements Initializable {
	
    @FXML
    private BorderPane borderPaneDatosPersonal11;

    @FXML
    private TextArea textAreaConsultaSQL;

    @FXML
    private JFXButton FXButtonEjecutar;
    
    @FXML 
    private JFXButton jFXButtonExportarExcel;

    @FXML
    private Label labelInformacion;
    
    
    
    

    @FXML
    private BorderPane borderPaneConsulta;
 
    private ModeloEstadisticaBusquedaAvanzada modelo;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		FXButtonEjecutar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				modelo=new ModeloEstadisticaBusquedaAvanzada();
				modelo.ejecutarSentenciaSeleccionarSQL(textAreaConsultaSQL.getText().trim());
				TableView<Map> tableView=new TableView<>(modelo.obtenerMapDatosSQL());
				tableView.getSelectionModel().setCellSelectionEnabled(true);
				tableView.getSelectionModel().setSelectionMode(
					    SelectionMode.MULTIPLE
					);

					MenuItem item = new MenuItem("Copiar");
					item.setOnAction(new EventHandler<ActionEvent>() {
					    @Override
					    public void handle(ActionEvent event) {
					        ObservableList<TablePosition> posList = tableView.getSelectionModel().getSelectedCells();
					        int old_r = -1;
					        StringBuilder clipboardString = new StringBuilder();
					        for (TablePosition p : posList) {
					            int r = p.getRow();
					            int c = p.getColumn();
					            Object cell = tableView.getColumns().get(c).getCellData(r);
					            if (cell == null)
					                cell = "";
					            if (old_r == r)
					                clipboardString.append('\t');
					            else if (old_r != -1)
					                clipboardString.append('\n');
					            clipboardString.append(cell);
					            old_r = r;
					        }
					        final ClipboardContent content = new ClipboardContent();
					        content.putString(clipboardString.toString());
					        Clipboard.getSystemClipboard().setContent(content);
					    }
					});
					ContextMenu menu = new ContextMenu();
					menu.getItems().add(item);
					tableView.setContextMenu(menu);
					
					
					
					jFXButtonExportarExcel.setOnAction(new EventHandler<ActionEvent>() {
						
						@Override
						public void handle(ActionEvent event) {
							
							
							FileChooser fileChooser1 = new FileChooser();
							fileChooser1.setTitle("Exportar Tabla");
							FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos Excel(*.xls)", "*.xls");
			                fileChooser1.getExtensionFilters().add(extFilter);
							
							//System.out.println(pic.getId());
							File file = fileChooser1.showSaveDialog(null);
							//System.out.println(file);
							
							export(tableView, file);	
							
						}
					});
					
				
				
		        ObservableList<TableColumn<Map, String>> columnas=FXCollections.observableArrayList();
				for(int i=0;i<modelo.obtenerNombresColumnas().size();i++){
				    TableColumn<Map, String> tableColumnAux =  new TableColumn<>(modelo.obtenerNombresColumnas().get(i));
			        tableColumnAux.setCellValueFactory(new MapValueFactory(modelo.obtenerNombresColumnas().get(i)));
			        tableColumnAux.setMinWidth(130);
			        tableColumnAux.setMaxWidth(Double.MAX_VALUE);
			        columnas.add(tableColumnAux);
				}
				if(modelo.obtenerEstado()==ModeloEstadisticaBusquedaAvanzada.MALO){
					labelInformacion.setTextFill(Color.RED);
				}else{
					labelInformacion.setTextFill(Color.BLACK);

				}
				labelInformacion.setText(modelo.obtenerMensaje());
				tableView.getColumns().addAll(columnas);
				borderPaneConsulta.setCenter(tableView);
			}
		});
	}
	
	
	
	 public void export(TableView<Map> tableView, File file){

	        HSSFWorkbook hssfWorkbook=new HSSFWorkbook();
	        HSSFSheet hssfSheet=  hssfWorkbook.createSheet("Sheet1");
	        HSSFRow firstRow= hssfSheet.createRow(0);

	        ///set titles of columns
	        for (int i=0; i<tableView.getColumns().size();i++){

	            firstRow.createCell(i).setCellValue(tableView.getColumns().get(i).getText());

	        }


	        for (int row=0; row<tableView.getItems().size();row++){

	            HSSFRow hssfRow= hssfSheet.createRow(row+1);

	            for (int col=0; col<tableView.getColumns().size(); col++){

	                Object celValue = tableView.getColumns().get(col).getCellObservableValue(row).getValue();

	                try {
	                    if (celValue != null && Double.parseDouble(celValue.toString()) != 0.0) {
	                        hssfRow.createCell(col).setCellValue(Double.parseDouble(celValue.toString()));
	                    }
	                } catch (  NumberFormatException e ){

	                    hssfRow.createCell(col).setCellValue(celValue.toString());
	                }

	            }

	        }

	        //save excel file and close the workbook
	        try {
	            hssfWorkbook.write(new FileOutputStream(file));
	            hssfWorkbook.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }




	    }

	

	class ModeloEstadisticaBusquedaAvanzada{
		public static final int MALO=0;
		public static final int BUENO=1;
		private int estado=-1;
		private ObservableList<Map> mapDatos=FXCollections.observableArrayList();
		private ArrayList<String> nombresColumnas=new ArrayList<>();
		private String nombreTabla=null;
		private String mensaje=null;
	    public void  ejecutarSentenciaSeleccionarSQL(String sql){
	    	Connection conn=null;
	        PreparedStatement pst=null;
	        ResultSet rs=null;
	        ResultSetMetaData rsMd=null;
	    	try{           
	             Class.forName("com.mysql.jdbc.Driver");  
	             conn = DriverManager.getConnection(Conexion.SERVIDOR,"JULIO", "JULIO2017");     
	             //conn = DriverManager.getConnection(Conexion.SERVIDOR,Conexion.USER,Conexion.PASS);
	             pst = conn.prepareStatement(sql);
	             rs = pst.executeQuery();
	             rsMd=rs.getMetaData();
	             int numerosColumnas=rsMd.getColumnCount();
	             int contador=0;
	             while(rs.next()){
	            	 Map<String, String> fila=new HashMap<>();
	            	 for(int i=0;i<numerosColumnas;i++){
	            		 fila.put(rsMd.getColumnLabel(i+1),(rs.getObject(i+1)!=null)?rs.getObject(i+1).toString():null);
	            	 }
	            	 mapDatos.add(fila);
	            	 contador++;
	             }
	             nombreTabla=rsMd.getTableName(1);
	             for(int i=0;i<numerosColumnas;i++){
	            	 nombresColumnas.add(rsMd.getColumnLabel(i+1));
	             }
	             estado=BUENO;
	             mensaje=contador+" filas seleccionadas.";
	         }   
	         catch(Exception e){
	        	 estado=MALO;
	        	 mensaje=e.getMessage();
	             e.printStackTrace();
	         }finally{
	        	
	        		 try {
	        			if(rs!=null){
	        				 rs.close();
	        			}
	        			if(pst!=null){
	        				pst.close();
	        			}
	        			if(conn!=null){
	        				pst.close();
	        			}
						
	        		 } catch (SQLException e) {
						e.printStackTrace();
				
	        	 }
	        	 
	        	 
	         } 
	    	//return mapDatos;
	         
	     }
	    public ObservableList<Map> obtenerMapDatosSQL(){
	    	return mapDatos;
	    }
	    
	    public ArrayList<String> obtenerNombresColumnas(){
	    	return nombresColumnas;
	    }
	    public String obtenerMensaje(){
	    	return mensaje;
	    };
	    public int obtenerEstado(){
	    	return estado;
	    }
	}
		

}