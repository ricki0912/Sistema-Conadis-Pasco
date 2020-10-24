package estadistica.tipoDiscapacidad_gravedad;


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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
	

public class EstadisticaTipoDiscapacidadControler extends Funciones implements Initializable {
	
	private Double sumaNumeroPersonas=0.0;
	
	private ObservableList<PieChart.Data> listPieChartGravedad=FXCollections.observableArrayList();


    @FXML
    private BorderPane borderPaneDatosPersonal111;


    @FXML
    private Label labelInformacionEstadisticaTipoDIiscapacida;

    @FXML
    private BorderPane borderPaneDatosPersonal1111;

    @FXML
    private PieChart pieChartGravedad;

    @FXML
    private Label labelInformacionEstadisticaGravedad;
	
	
	
	
	
	
    @FXML
    private StackedBarChart<String,Number > stackeBarChartTipoDiscapacidad;
    
    
            
    CategoryAxis xAxis = new CategoryAxis();    
   
    
    NumberAxis yAxis = new NumberAxis(); 
       
    StackedBarChart<String, Number> stackedBarChart = new StackedBarChart<>(xAxis, yAxis);         
       
    XYChart.Series<String, Number> SeriesProgramaSocial = new XYChart.Series<>();  
    
    
    
	private ObservableList<PieChart.Data> listPieChartProvincia=FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		llenarTipoDiscapacidad();
		llenarPieChartGravedad();
		
		pieChartGravedad.getData().stream().forEach(data->{
			
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
			
			System.out.println(data.getName()+"hl");
			//Stage stage=(Stage)borderPaneContenedorPiechart.getScene().getWindow();
			labelInformacionEstadisticaGravedad.setVisible(true);
			//System.out.println(stage.getWidth());
			//System.out.println(borderPaneContenedorPiechart.getWidth());
			System.out.println(e.getSceneX());
			System.out.println(e.getX());
			labelInformacionEstadisticaGravedad.setTranslateX(e.getSceneX()-50-borderPaneDatosPersonal111.getWidth());
			labelInformacionEstadisticaGravedad.setTranslateY(e.getSceneY()-160);
			labelInformacionEstadisticaGravedad.setText(String.valueOf(data.getPieValue())+"\n"+Funciones.decimalReducido(String.valueOf(data.getPieValue()*100/sumaNumeroPersonas))+"%");  
       
		});
		
		});
		SeriesProgramaSocial.getData().stream().forEach(data->{
			
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
			
			//System.out.println(data.getName()+"hl");
			labelInformacionEstadisticaTipoDIiscapacida.setVisible(true);
			labelInformacionEstadisticaTipoDIiscapacida.setTranslateX(e.getSceneX()-50);;
			labelInformacionEstadisticaTipoDIiscapacida.setTranslateY(e.getSceneY()-160);
			labelInformacionEstadisticaTipoDIiscapacida.setText(String.valueOf(data.getYValue())+"\n"+Funciones.decimalReducido(String.valueOf(Integer.parseInt(String.valueOf(data.getYValue()))*100/sumaNumeroPersonas))+"%");  
       
		});
		
		});
		
	
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
			  		//+ "SELECT COUNT(D_PROGRAMA_SOCIAL_PER_DISCAPACIDAD.ID_PROGRAMA_SOCIAL) AS NUMERO, (SELECT NOMBRE FROM PROGRAMA_SOCIAL WHERE ID=D_PROGRAMA_SOCIAL_PER_DISCAPACIDAD.ID_PROGRAMA_SOCIAL) AS NOMBRE FROM D_PROGRAMA_SOCIAL_PER_DISCAPACIDAD GROUP BY D_PROGRAMA_SOCIAL_PER_DISCAPACIDAD.ID_PROGRAMA_SOCIAL;");
          
			  rs = pst.executeQuery();
         while(rs.next()){
             	SeriesProgramaSocial.getData().add(new XYChart.Data<String, Number>(rs.getString("NOMBRE"), rs.getInt("NUMERO")));
         
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
		  stackeBarChartTipoDiscapacidad.getData().addAll(SeriesProgramaSocial);
		  stackeBarChartTipoDiscapacidad.setTitle("Número (n) de Personas - Tipo de Discapacidad");

	}
	
	

	
	public void llenarPieChartGravedad(){
		sumaNumeroPersonas=0.0;
		 
      Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{    			  
        Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
			  pst=conn.prepareStatement("SELECT  count(*) AS NUMERO_PERSONAS,(SELECT NOMBRE FROM NIVEL_GRAVEDAD_LIMITACION WHERE ID=CERTIFICADO_MEDICO.ID_NIVEL_GRAVEDAD_LIMITACION) AS GRAVEDAD FROM CERTIFICADO_MEDICO GROUP BY CERTIFICADO_MEDICO.ID_NIVEL_GRAVEDAD_LIMITACION;");
       
          rs = pst.executeQuery();
         while(rs.next()){
        	 sumaNumeroPersonas=sumaNumeroPersonas+rs.getDouble("NUMERO_PERSONAS");
        	 PieChart.Data data=new PieChart.Data(rs.getString("GRAVEDAD"),rs.getDouble("NUMERO_PERSONAS"));
        	 
        	 listPieChartGravedad.add(data);
            
        	 
        	 
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
		  pieChartGravedad.setData(listPieChartGravedad);
		  pieChartGravedad.setTitle("Personas en numeros(n) y porcentajes(%) por gravedad");
	}
	
	

}