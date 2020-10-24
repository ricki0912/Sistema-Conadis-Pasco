package estadistica.provincia_distrito;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import funciones.AbstractJasperReports;
import funciones.Conexion;
import funciones.Funciones;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
	

public class EstadisticaProvinciaDistritoControler extends Funciones implements Initializable {
	
	
	@FXML private JFXButton jFXButtonExportarProvincia, jFXButtonExportarDistritos; 
	
	
	@FXML private BorderPane borderPaneContenedorPiechart;
	@FXML private Label labelInformacionEstadistica, labelInformacionEstadisticaDistritos;
	final Label caption = new Label("");
	
	private Double sumaNumeroPersonas=0.0;
	
	@FXML private StackedBarChart<String, Number> stackedBarChartDistritos;
	
    
    XYChart.Series<String, Number> seriesDistritos = new XYChart.Series<>();  

    CategoryAxis xAxis = new CategoryAxis();    
    

    
    NumberAxis yAxis = new NumberAxis(); 
       
	@FXML private PieChart pieChartProvincia;
	private ObservableList<PieChart.Data> listPieChartProvincia=FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		jFXButtonExportarProvincia.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				 Connection conn = null;
				 Conexion.conectarDB();
				conn = Conexion.getConexion();
				
				
				AbstractJasperReports.createReport(conn, "reports\\ReporteProvincia.jasper");
				AbstractJasperReports.showViewer();
			}
		});
		
		
		
		
		caption.setTextFill(Color.DARKORANGE);
		caption.setStyle("-fx-font: 24 arial;");
		llenarPieChartProvincia();
		llenarstackedBarChartDistritos();
		pieChartProvincia.getData().stream().forEach(data->{
			
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
			
			labelInformacionEstadistica.setVisible(true);
			labelInformacionEstadistica.setTranslateX(e.getSceneX()-50);;
			labelInformacionEstadistica.setTranslateY(e.getSceneY()-160);
			labelInformacionEstadistica.setText(String.valueOf(data.getPieValue())+"\n"+Funciones.decimalReducido(String.valueOf(data.getPieValue()*100/sumaNumeroPersonas))+"%");  
       
		});
		
		});
		
		
		seriesDistritos.getData().forEach(data->{
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
				labelInformacionEstadisticaDistritos.setVisible(true);
				labelInformacionEstadisticaDistritos.setTranslateX(e.getSceneX()-50-borderPaneContenedorPiechart.getWidth());;
				labelInformacionEstadisticaDistritos.setTranslateY(e.getSceneY()-160);
				System.out.println(labelInformacionEstadisticaDistritos.getLayoutY());
				labelInformacionEstadisticaDistritos.setText(String.valueOf(data.getYValue())+"\n"+Funciones.decimalReducido(String.valueOf(Integer.parseInt(String.valueOf(data.getYValue()))*100/sumaNumeroPersonas))+"%");  
	       
				
			});
		});
	/*	
		stackedBarChartDistritos.getData().stream().forEach(data->{
			
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
			
			System.out.println(data.getName()+"hl");
			labelInformacionEstadistica.setVisible(true);
			labelInformacionEstadistica.setTranslateX(e.getSceneX()-50);;
			labelInformacionEstadistica.setTranslateY(e.getSceneY()-160);
			//labelInformacionEstadisticaDistritos.setText(String.valueOf(data.getChart().getYAxis())+"\n"+Funciones.decimalReducido(String.valueOf(data.getChart().getXAxis().*100/sumaNumeroPersonas))+"%");  
       
		});
		
		});*/
	}
	
	public void llenarstackedBarChartDistritos(){
		xAxis.setLabel("Número de Personas(n)");
		yAxis.setLabel("Tipo de Discapacidad");
		
		
      Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{    			  
        Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
			  //pst=conn.prepareStatement("SELECT ((COUNT(DISTRITO.ID_PROVINCIA)*100)/(SELECT count(*)  FROM PER_DISCAPACIDAD INNER"
			  	//	+ " JOIN DOMICILIO ON PER_DISCAPACIDAD.ID_DOMICILIO=DOMICILIO.ID)) AS PORCENTAJE,DISTRITO.ID_PROVINCIA ,"
			  	//	+ " (SELECT PROVINCIA.NOMBRE FROM PROVINCIA WHERE ID=DISTRITO.ID_PROVINCIA) AS PROVINCIA_NOMBRE "
			  	//	+ " FROM PER_DISCAPACIDAD INNER JOIN DOMICILIO ON PER_DISCAPACIDAD.ID_DOMICILIO=DOMICILIO.ID INNER JOIN DISTRITO "
			  	//	+ " ON DISTRITO.ID=DOMICILIO.ID_DISTRITO GROUP BY DISTRITO.ID_PROVINCIA;");
			  pst=conn.prepareStatement("SELECT (SELECT NOMBRE FROM DISTRITO WHERE ID=ID_DISTRITO) AS NOMBRE_DISTRITO, (COUNT(ID_DISTRITO)) AS NUMERO_PERSONA FROM DOMICILIO GROUP BY DOMICILIO.ID_DISTRITO;");
          
			  rs = pst.executeQuery();
         while(rs.next()){
        	// XYChart.Data<Integer, Integer> data = new XYChart.Data<>(i + 1, y[i]);
        	 XYChart.Data<String, Number> data = new XYChart.Data<String, Number>(rs.getString("NOMBRE_DISTRITO"), rs.getInt("NUMERO_PERSONA"));
        	// data.setNode(caption);
        	 seriesDistritos.getData().add(data);
         
         	}
         
         }   
		  
		  
     catch(Exception e){
    
         e.printStackTrace();
    
     }  finally{
  	   try {
  		   if(rs!=null){
  			   rs.close();
				
  	   		}
  		   if(pst!=null){
  			   pst.close();
				
  	   		}if(conn!=null){
  	   			conn.close();
  	   		}
  	   
  	   } catch (SQLException e) {
					
					e.printStackTrace();
				}
  	   
     }
		  stackedBarChartDistritos.getData().add(seriesDistritos);
		  stackedBarChartDistritos.setTitle("Número (n) de Personas por Distritos");

		
	}
	
	public void llenarPieChartProvincia(){
		sumaNumeroPersonas=0.0;
		 
      Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{    			  
        Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
			  pst=conn.prepareStatement("SELECT (COUNT(DISTRITO.ID_PROVINCIA)) AS PORCENTAJE,DISTRITO.ID_PROVINCIA ,"
			  		+ " (SELECT PROVINCIA.NOMBRE FROM PROVINCIA WHERE ID=DISTRITO.ID_PROVINCIA) AS PROVINCIA_NOMBRE "
			  		+ " FROM PER_DISCAPACIDAD INNER JOIN DOMICILIO ON PER_DISCAPACIDAD.ID_DOMICILIO=DOMICILIO.ID INNER JOIN DISTRITO "
			  		+ " ON DISTRITO.ID=DOMICILIO.ID_DISTRITO GROUP BY DISTRITO.ID_PROVINCIA;");
       
          rs = pst.executeQuery();
         while(rs.next()){
        	 sumaNumeroPersonas=sumaNumeroPersonas+rs.getDouble("PORCENTAJE");
        	 PieChart.Data data=new PieChart.Data(rs.getString("PROVINCIA_NOMBRE"),rs.getDouble("PORCENTAJE"));
        	 
        	 listPieChartProvincia.add(data);
            
        	 
        	 
             	}
         }   
		  
		  
     catch(Exception e){
    
         e.printStackTrace();
    
     }  finally{
  	   try {
  		   if(rs!=null){
  			   rs.close();
				
  	   		}
  		   if(pst!=null){
  			   pst.close();
				
  	   		}if(conn!=null){
  	   			conn.close();
  	   		}
  	   
  	   } catch (SQLException e) {
					
					e.printStackTrace();
				}
  	   
     }
		  pieChartProvincia.setData(listPieChartProvincia);
		  pieChartProvincia.setTitle("Personas con discapacidad en (%) por provincia");
	}
	
	 class HoveredThresholdNode extends StackPane {
		    HoveredThresholdNode(int priorValue, int value) {
		      setPrefSize(15, 15);

		      final Label label = createDataThresholdLabel(priorValue, value);

		      setOnMouseEntered(new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent mouseEvent) {
		          getChildren().setAll(label);
		          setCursor(Cursor.NONE);
		          toFront();
		        }
		      });
		      setOnMouseExited(new EventHandler<MouseEvent>() {
		        @Override public void handle(MouseEvent mouseEvent) {
		          getChildren().clear();
		          setCursor(Cursor.HAND);
		        }
		      });
		    }

		    private Label createDataThresholdLabel(int priorValue, int value) {
		      final Label label = new Label(value + "");
		      label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
		      label.setStyle("-fx-font-size: 20; -fx-font-weight: bold;");

		      if (priorValue == 0) {
		        label.setTextFill(Color.DARKGRAY);
		      } else if (value > priorValue) {
		        label.setTextFill(Color.FORESTGREEN);
		      } else {
		        label.setTextFill(Color.FIREBRICK);
		      }

		      label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
		      return label;
		    }
		  }
	 
	
	
	


}