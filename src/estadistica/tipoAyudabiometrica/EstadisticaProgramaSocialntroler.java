package estadistica.tipoAyudabiometrica;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import funciones.Conexion;
import funciones.Funciones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
	

public class EstadisticaProgramaSocialntroler extends Funciones implements Initializable {
	
    @FXML
    private StackedBarChart<String,Number > stackeBarChartTipoDiscapacidad;
    
    
            
    CategoryAxis xAxis = new CategoryAxis();    
   
    
    NumberAxis yAxis = new NumberAxis(); 
       
    StackedBarChart<String, Number> stackedBarChart = new StackedBarChart<>(xAxis, yAxis);         
       
    XYChart.Series<String, Number> SeriesTipoDiscapacidad = new XYChart.Series<>();  
    
    
    
	private ObservableList<PieChart.Data> listPieChartProvincia=FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		llenarTipoDiscapacidad();
	
	}
	
	public void llenarTipoDiscapacidad(){
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
			  pst=conn.prepareStatement("SELECT COUNT(D_TIPO_DISCAPACIDAD_CERTIFICADO_MEDICO.ID_TIPO_DISCAPACIDAD) AS NUMERO,(SELECT NOMBRE FROM TIPO_DISCAPACIDAD WHERE ID= D_TIPO_DISCAPACIDAD_CERTIFICADO_MEDICO.ID_TIPO_DISCAPACIDAD) AS NOMBRE FROM D_TIPO_DISCAPACIDAD_CERTIFICADO_MEDICO GROUP BY D_TIPO_DISCAPACIDAD_CERTIFICADO_MEDICO.ID_TIPO_DISCAPACIDAD;");
          
			  rs = pst.executeQuery();
         while(rs.next()){
             	SeriesTipoDiscapacidad.getData().add(new XYChart.Data<String, Number>(rs.getString("NOMBRE"), rs.getInt("NUMERO")));
         
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
		  stackeBarChartTipoDiscapacidad.getData().addAll(SeriesTipoDiscapacidad);
		  stackeBarChartTipoDiscapacidad.setTitle("Persconas con discapacidad en (%) por provincia");
	}
	
	


}