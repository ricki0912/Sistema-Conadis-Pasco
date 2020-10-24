package estadistica.edad_sexo;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import funciones.Conexion;
import funciones.Funciones;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.stage.Stage;
	

public class EstadisticaEdadSexoControler extends Funciones implements Initializable {
	@FXML private BorderPane borderPaneContenedorPiechart;
	@FXML private Label labelInformacionEstadistica, labelInformacionEstadisticaSexo;
	
	private Double sumaNumeroPersonas=0.0;
 
	@FXML private PieChart pieChartEdad, pieChartSexo;
	private ObservableList<PieChart.Data> listPieChartEdad=FXCollections.observableArrayList();
	private ObservableList<PieChart.Data> listPieChartSexo=FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		llenarPieChartEdad();
		llenarPieChartSexo();
		pieChartSexo.getData().stream().forEach(data->{
			
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
			
			labelInformacionEstadisticaSexo.setVisible(true);
		
			labelInformacionEstadisticaSexo.setTranslateX(e.getSceneX()-50-borderPaneContenedorPiechart.getWidth());
			labelInformacionEstadisticaSexo.setTranslateY(e.getSceneY()-160);
			labelInformacionEstadisticaSexo.setText(String.valueOf(data.getPieValue())+"\n"+Funciones.decimalReducido(String.valueOf(data.getPieValue()*100/sumaNumeroPersonas))+"%");  
       
		});
		
		});
		pieChartEdad.getData().stream().forEach(data->{
			
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
			
	
			labelInformacionEstadistica.setVisible(true);
			labelInformacionEstadistica.setTranslateX(e.getSceneX()-50);;
			labelInformacionEstadistica.setTranslateY(e.getSceneY()-160);
			labelInformacionEstadistica.setText(String.valueOf(data.getPieValue())+"\n"+Funciones.decimalReducido(String.valueOf(data.getPieValue()*100/sumaNumeroPersonas))+"%");  
       
		});
		
		});
		

	}
	

	
	public void llenarPieChartEdad(){
		sumaNumeroPersonas=0.0;
		 
      Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{    			  
        Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
			  pst=conn.prepareStatement("SELECT COUNT(*) AS NUMERO_PERSONAS, CASE WHEN TIMESTAMPDIFF(YEAR,PER_DISCAPACIDAD.FECHA_NACIMIENTO,CURDATE())<12 THEN '0-11'"+
					  " WHEN TIMESTAMPDIFF(YEAR,PER_DISCAPACIDAD.FECHA_NACIMIENTO,CURDATE()) <18 THEN '12-17'"+
					  " WHEN TIMESTAMPDIFF(YEAR,PER_DISCAPACIDAD.FECHA_NACIMIENTO,CURDATE())<26 THEN '18-25'"+
					  " WHEN TIMESTAMPDIFF(YEAR,PER_DISCAPACIDAD.FECHA_NACIMIENTO,CURDATE())<60 THEN '26-59'"+
					  " ELSE '60-100' END AS GRUPO_EDAD FROM PER_DISCAPACIDAD GROUP BY GRUPO_EDAD;");
       
          rs = pst.executeQuery();
         while(rs.next()){
        	 sumaNumeroPersonas=sumaNumeroPersonas+rs.getDouble("NUMERO_PERSONAS");
        	 PieChart.Data data=new PieChart.Data(rs.getString("GRUPO_EDAD"),rs.getDouble("NUMERO_PERSONAS"));
        	 
        	 listPieChartEdad.add(data);
            
        	 
        	 
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
		  pieChartEdad.setData(listPieChartEdad);
		  pieChartEdad.setTitle("Personas en números(n) y porcentajes(%) por edad.");
	}
	
	public void llenarPieChartSexo(){
		sumaNumeroPersonas=0.0;
		 
      Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{    			  
        Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
			  pst=conn.prepareStatement("SELECT COUNT(SEXO) AS NUMERO_PERSONAS, IF(SEXO=1, 'Masculino','Femenino') AS SEXO_VARCHAR FROM PER_DISCAPACIDAD  GROUP BY SEXO ; ");
       
          rs = pst.executeQuery();
         while(rs.next()){
        	 sumaNumeroPersonas=sumaNumeroPersonas+rs.getDouble("NUMERO_PERSONAS");
        	 PieChart.Data data=new PieChart.Data(rs.getString("SEXO_VARCHAR"),rs.getDouble("NUMERO_PERSONAS"));
        	 
        	 listPieChartSexo.add(data);

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
		  pieChartSexo.setData(listPieChartSexo);
		  pieChartSexo.setTitle("Personas en números(n) y porcentajes(%) por género.");
	}
	
	
	
	
	


}