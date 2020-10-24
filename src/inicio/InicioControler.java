package inicio;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import funciones.Conexion;
import funciones.Funciones;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;
import javafx.util.Duration;
import principal.PrincipalControler;






 
public class InicioControler extends Funciones implements Initializable {
	



	    @FXML
	    private Label labelValorActualReloj;

	    @FXML
	    private Label labelIzquierdoReloj;

	    @FXML
	    private Label labelderechoReloj;

		
			@FXML private Label labelLogroAlcanzado, labelusuariosRegistradosHoy, labelUltimoMes;
		
			
			@FXML
		    private LineChart<String, Number> lineChartMetas;

			private CategoryAxis xAxis = new CategoryAxis();
		    private NumberAxis yAxis = new NumberAxis();
			
		




		    @FXML
		    private Arc ArcMetaAzul;

		    @FXML
		    private Arc ArcMetaVerde;

		    @FXML
		    private Arc ArcMetaPrecaucion;

		    @FXML
		    private Arc ArcMetaPeligro;

		    @FXML
		    private Arc ArcFlechaIndicador;

		    @FXML
		    private Label labelFechaContenido;


	    
		    private XYChart.Series xYChartSeriesMetaEstablecidad = new XYChart.Series();
		    private XYChart.Series xYChartSeriesPeligro = new XYChart.Series();
		    private XYChart.Series xYChartSeriesPrecaucion = new XYChart.Series();
		    private XYChart.Series xYChartSeriesMetaOptimaVerde = new XYChart.Series();
		    private XYChart.Series xYChartSeriesMetaLograda = new XYChart.Series();
	
	

	

	@FXML Label labelNroUsuariosRegistrados;
	@FXML Label labelNroUsuariosCarnet;
    @FXML Label labelValorActualContenido;
    
    
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		mostrarNroUsuariosRegistrados();
		mostrarNroUsuariosConCarnet();
		mostrarDatos();
		mostrarNroUsuariosRegistradosUltimoMes();
		mostrarNroUsuariosRegistradosHoy();
		
		
		
		
		
		
	
	}
	
	public void mostrarNroUsuariosRegistrados(){

		 
      Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{    			  
        Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
			  pst=conn.prepareStatement("SELECT count(*) AS TOTAL FROM INSCRIPCION_CONADIS_PASCO;");
       
          rs = pst.executeQuery();
         while(rs.next()){
        	 labelNroUsuariosRegistrados.setText(String.valueOf(rs.getInt("TOTAL")));
        	 
             
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
		
	}
	

	

	public void mostrarNroUsuariosRegistradosUltimoMes(){

		 
	      Connection conn=null;
			  PreparedStatement pst=null;
			  ResultSet rs=null;
			  try{    			  
	        Conexion.conectarDB(); 
				  conn=Conexion.getConexion();
				  pst=conn.prepareStatement("SELECT COUNT(*) AS TOTAL FROM inscripcion_conadis_pasco WHERE DATE(FECHA_CREACION) BETWEEN DATE_SUB(NOW(), INTERVAL 1 MONTH) AND NOW();");
	       
	          rs = pst.executeQuery();
	         while(rs.next()){
	        	 labelUltimoMes.setText(String.valueOf(rs.getInt("TOTAL")));

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
			
		}
	
	
	public void mostrarNroUsuariosRegistradosHoy(){

		 
	      Connection conn=null;
			  PreparedStatement pst=null;
			  ResultSet rs=null;
			  try{    			  
	        Conexion.conectarDB(); 
				  conn=Conexion.getConexion();
				  pst=conn.prepareStatement("SELECT COUNT(*) AS TOTAL  FROM inscripcion_conadis_pasco where date(FECHA_CREACION)=CURDATE();");
	       
	          rs = pst.executeQuery();
	         while(rs.next()){
	        	 labelusuariosRegistradosHoy.setText(String.valueOf(rs.getInt("TOTAL")));

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
			
		}
	
	
	public void mostrarNroUsuariosConCarnet(){

		 
	      Connection conn=null;
			  PreparedStatement pst=null;
			  ResultSet rs=null;
			  try{    			  
	        Conexion.conectarDB(); 
	        
				  conn=Conexion.getConexion();
				  pst=conn.prepareStatement("SELECT  COUNT(*) AS NUMERO_CARNET, (SELECT NOMBRE FROM TIENE_INSCRIPCION_CONADIS WHERE ID=ID_TIENE_INSCRIPCION_CONADIS) AS NOMBRE_TIENE_CARNET FROM INSCRIPCION_CONADIS_PASCO WHERE  (SELECT NOMBRE FROM TIENE_INSCRIPCION_CONADIS WHERE ID=ID_TIENE_INSCRIPCION_CONADIS)='Si';");
	       
	          rs = pst.executeQuery();
	         while(rs.next()){
	        	 labelNroUsuariosCarnet.setText(String.valueOf(rs.getInt("NUMERO_CARNET")));
	        	 labelValorActualContenido.setText(String.valueOf(rs.getInt("NUMERO_CARNET")));
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
			
		}
	
	


	public void mostrarDatos(){
		lineChartMetas.getData().addAll(xYChartSeriesPeligro, xYChartSeriesPrecaucion, xYChartSeriesMetaEstablecidad,xYChartSeriesMetaLograda );
		
		xYChartSeriesPeligro.setName("Peligro");
		xYChartSeriesPrecaucion.setName("Precaucion");
		xYChartSeriesMetaEstablecidad.setName(" Meta ");
		xYChartSeriesMetaLograda.setName("Logro Alcanzado");
		   
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try{
			Conexion.conectarDB();
			//conn = Conexion.getConexion();
			//Class.forName("com.mysql.jdbc.Driver");
        	conn=Conexion.getConexion();
			
			
			pst = conn.prepareStatement("SELECT CURDATE() AS FECHA, COUNT(*) AS NUMERO_CARNET, (SELECT NOMBRE FROM TIENE_INSCRIPCION_CONADIS WHERE ID=ID_TIENE_INSCRIPCION_CONADIS) AS NOMBRE_TIENE_CARNET FROM INSCRIPCION_CONADIS_PASCO WHERE  (SELECT NOMBRE FROM TIENE_INSCRIPCION_CONADIS WHERE ID=ID_TIENE_INSCRIPCION_CONADIS)='Si';");

					
					rs = pst.executeQuery();
			
				
			while(rs.next()){
				//logica
				
				
				
					labelFechaContenido.setText("Fecha: "+rs.getDate("FECHA") );
			
				
					xYChartSeriesMetaEstablecidad.getData().add(new XYChart.Data(String.valueOf(rs.getDate("FECHA")), 8000.0));
				
				
					
					xYChartSeriesMetaLograda.getData().add(new XYChart.Data(String.valueOf(rs.getDate("FECHA")), rs.getDouble("NUMERO_CARNET")));
					
					xYChartSeriesPeligro.getData().add(new XYChart.Data(String.valueOf(rs.getDate("FECHA")), 2000.0));
					
					xYChartSeriesPrecaucion.getData().add(new XYChart.Data(String.valueOf(rs.getDate("FECHA")),5000.0));
					
					
					//xYChartSeries.getData().add(new XYChart.Data(String.valueOf(rs.getDate("FECHA")), rs.getDouble("META_VERDE_FINAL")));
					
					lineChartMetas.setTitle("Inscritos en el Conadis Nacional");
					lineChartMetas.setCursor(Cursor.HAND);
					
				
					
					labelValorActualContenido.setText(rs.getString("NUMERO_CARNET"));
			
					
					labelLogroAlcanzado.setText(rs.getString("NUMERO_CARNET"));
					
					
					
					
					final double metaRojoInicial=0;
					final double metaRojoFinal=2000;
					final double precaucion= 5000;
					final double meta=8000;
					final double metalograda=rs.getDouble("NUMERO_CARNET");
					
					mostrarSemaforo(metaRojoInicial, metaRojoFinal,precaucion, meta, metalograda);

					
					
				
					//logica
				
				
			}
			
	
			
			
			
		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();

				}
				if (pst!= null) {
					pst.close();

				}
				
				
				
				
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		
	}
	
	public void mostrarSemaforo( double peligroIncial, double peligro, double precaucion, double meta, double metaAlcanzada ){
		
		double tamano=0;
		double anguloPeligro=0;
		double anguloPrecaucion=0;
		double anguloMetaVerde=0;
		double anguloMetaSuperadaAzul=0;
		double anguloMetaAlcanzada=0;
		
		if(meta>peligroIncial){
			if(metaAlcanzada>meta){
				tamano=metaAlcanzada-peligroIncial;
				labelderechoReloj.setText(metaAlcanzada+"");
				labelIzquierdoReloj.setText(peligroIncial+"");
			}else{
				tamano=meta-peligroIncial;
				labelIzquierdoReloj.setText(peligroIncial+"");
				labelderechoReloj.setText(meta+"");
			}
			
			//angulo peligro
			anguloPeligro=((peligro-peligroIncial)*180)/tamano;
			ArcMetaPeligro.setStartAngle(180-anguloPeligro);
			ArcMetaPeligro.setLength(anguloPeligro);
			
			//angulo Precaucion
			anguloPrecaucion=((precaucion-peligroIncial)*180)/tamano;
			ArcMetaPrecaucion.setStartAngle(180-anguloPrecaucion);
			ArcMetaPrecaucion.setLength(anguloPrecaucion);
			
			//angulo Meta
			anguloMetaVerde=((meta-peligroIncial)*180)/tamano;
			ArcMetaVerde.setStartAngle(180-anguloMetaVerde);
			ArcMetaVerde.setLength(anguloMetaVerde);
			
			//angulo metaoptima
			
			//angulo meta alcanzada indicasor
			anguloMetaAlcanzada=((metaAlcanzada-peligroIncial)*180)/tamano;
			
			ArcFlechaIndicador.setStartAngle(180-anguloMetaAlcanzada-1);
		
			
			
			//new hilosIndicador(anguloMetaAlcanzada);
			
			
		}else{
			if(metaAlcanzada<meta){
				tamano=peligroIncial-metaAlcanzada;
				labelIzquierdoReloj.setText(metaAlcanzada+"");
				labelderechoReloj.setText(peligroIncial+"");
				
				
			}else{
				tamano=peligroIncial-meta;
				labelIzquierdoReloj.setText(meta+"");
				labelderechoReloj.setText(peligroIncial+"");
			}
			
			
			//angulo peligro
			anguloPeligro=((peligroIncial-peligro)*180)/tamano;
			ArcMetaPeligro.setStartAngle(0);
			ArcMetaPeligro.setLength(anguloPeligro);
			
			//angulo Precaucion
			anguloPrecaucion=((peligroIncial-precaucion)*180)/tamano;
			ArcMetaPrecaucion.setStartAngle(0);
			ArcMetaPrecaucion.setLength(anguloPrecaucion);
			
			//angulo Meta
			anguloMetaVerde=((peligroIncial-meta)*180)/tamano;
			ArcMetaVerde.setStartAngle(0);
			ArcMetaVerde.setLength(anguloMetaVerde);
			
			//angulo metaoptima
			
			//angulo meta alcanzada
			
			anguloMetaAlcanzada=((peligroIncial-metaAlcanzada)*180)/tamano;
			ArcFlechaIndicador.setStartAngle(anguloMetaAlcanzada-1);
			
			
			
			
		}
		
		
		
		
		
	}
	
	
	

	


  

}