package estadistica.programaSocial_ClasificacionSisfoh;

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

public class EstadisticaProgramaSocial_ClasificacionSisfohControler extends Funciones implements Initializable {
	@FXML private BorderPane borderPaneContenedorPiechartClasificacionSisfoh;
	@FXML
	private Label labelInformacionClasificacionSisfoh;
	@FXML private PieChart pieChartClasificacionSisfoh;
	@FXML private Label labelInformacionProgramaSocial;
	private double sumaNumeroPersonas;
	
    @FXML
    private StackedBarChart<String,Number > stackeBarChartProgramaSocial;
	XYChart.Series<String, Number> seriesProgramaSocial = new XYChart.Series<>();

	private ObservableList<PieChart.Data> listPieChartClasificacionSisfoh=FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		llenarTipoProgramaSocial();
		llenarPieChartClasificacionSisfoh();
		pieChartClasificacionSisfoh.getData().stream().forEach(data->{
			
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
			
			labelInformacionClasificacionSisfoh.setVisible(true);
		
			labelInformacionClasificacionSisfoh.setTranslateX(e.getSceneX()-50-borderPaneContenedorPiechartClasificacionSisfoh.getWidth());
			labelInformacionClasificacionSisfoh.setTranslateY(e.getSceneY()-160);
			labelInformacionClasificacionSisfoh.setText(String.valueOf(data.getPieValue())+"\n"+Funciones.decimalReducido(String.valueOf(data.getPieValue()*100/sumaNumeroPersonas))+"%");  
       
		});
		
		});
		
		
		seriesProgramaSocial.getData().forEach(data->{
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
				labelInformacionProgramaSocial.setVisible(true);
				labelInformacionProgramaSocial.setTranslateX(e.getSceneX()-50);;
				labelInformacionProgramaSocial.setTranslateY(e.getSceneY()-160);
				labelInformacionProgramaSocial.setText(String.valueOf(data.getYValue())+"\n"+Funciones.decimalReducido(String.valueOf(Integer.parseInt(String.valueOf(data.getYValue()))*100/sumaNumeroPersonas))+"%");  
	       
				
			});
		});

	}

	public void llenarTipoProgramaSocial() {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			 
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			// pst=conn.prepareStatement("SELECT
			// ((COUNT(DISTRITO.ID_PROVINCIA)*100)/(SELECT count(*) FROM
			// PER_DISCAPACIDAD INNER"
			// + " JOIN DOMICILIO ON
			// PER_DISCAPACIDAD.ID_DOMICILIO=DOMICILIO.ID)) AS
			// PORCENTAJE,DISTRITO.ID_PROVINCIA ,"
			// + " (SELECT PROVINCIA.NOMBRE FROM PROVINCIA WHERE
			// ID=DISTRITO.ID_PROVINCIA) AS PROVINCIA_NOMBRE "
			// + " FROM PER_DISCAPACIDAD INNER JOIN DOMICILIO ON
			// PER_DISCAPACIDAD.ID_DOMICILIO=DOMICILIO.ID INNER JOIN DISTRITO "
			// + " ON DISTRITO.ID=DOMICILIO.ID_DISTRITO GROUP BY
			// DISTRITO.ID_PROVINCIA;");
			pst = conn.prepareStatement(
					"SELECT COUNT(D_PROGRAMA_SOCIAL_PER_DISCAPACIDAD.ID_PROGRAMA_SOCIAL) AS NUMERO, (SELECT NOMBRE FROM PROGRAMA_SOCIAL WHERE ID=D_PROGRAMA_SOCIAL_PER_DISCAPACIDAD.ID_PROGRAMA_SOCIAL) AS NOMBRE FROM D_PROGRAMA_SOCIAL_PER_DISCAPACIDAD GROUP BY D_PROGRAMA_SOCIAL_PER_DISCAPACIDAD.ID_PROGRAMA_SOCIAL;");

			rs = pst.executeQuery();
			while (rs.next()) {
				seriesProgramaSocial.getData()
						.add(new XYChart.Data<String, Number>(rs.getString("NOMBRE"), rs.getInt("NUMERO")));

			}

		}

		catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				if (rs != null) {
					rs.close();

				}
				if (pst != null) {
					pst.close();

				}
				if (conn != null) {
					conn.close();
				}

			} catch (SQLException e) {

				e.printStackTrace();
			}

		}
		stackeBarChartProgramaSocial.getData().addAll(seriesProgramaSocial);
		stackeBarChartProgramaSocial.setTitle("Número (n) de Personas - Programa Social");
		seriesProgramaSocial.getChart().getXAxis().setLabel("Programa Social");
		seriesProgramaSocial.getChart().getYAxis().setLabel("Número de Personas(n)");
	}
	
	
	
	public void llenarPieChartClasificacionSisfoh(){
		sumaNumeroPersonas = 0.0;
		 
      Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{    			  
        Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
			  pst=conn.prepareStatement("SELECT COUNT(*) AS NUMERO_PERSONAS, (SELECT NOMBRE FROM CLASIFICACION_SISFOH WHERE ID=PER_DISCAPACIDAD.ID_CLASIFICACION_SISFOH) AS CLASIFICACION_SISFOH FROM PER_DISCAPACIDAD GROUP BY ID_CLASIFICACION_SISFOH; ; ");
       
          rs = pst.executeQuery();
         while(rs.next()){
        	 sumaNumeroPersonas=sumaNumeroPersonas+rs.getDouble("NUMERO_PERSONAS");
        	 PieChart.Data data=new PieChart.Data(rs.getString("CLASIFICACION_SISFOH"),rs.getDouble("NUMERO_PERSONAS"));
        	 
        	 listPieChartClasificacionSisfoh.add(data);

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
		  pieChartClasificacionSisfoh.setData(listPieChartClasificacionSisfoh);
		  pieChartClasificacionSisfoh.setTitle("Personas en números(n) y porcentajes(%) por Clasificación del Sisfoh.");
	}

}