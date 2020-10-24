package estadistica.tipo_trabajo;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import funciones.Conexion;
import funciones.Funciones;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class EstadisticaTipoTrabajoControler extends Funciones implements Initializable {
	
	@FXML
	private AnchorPane anchorPaneConenedorGrafico;
	
	@FXML
    private TextField textFieldEdadMenor;

	
	
    @FXML
    private TextField textFieldEdadMayor;

    @FXML
    private JFXButton jFXButtonBuscar;
	
    


    @FXML
    private Label labelInformacionNivelEducativo;
	
	
	private double sumaNumeroPersonas;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		cargarGraficaNivelEducativo();
	
		
		
		
		
		jFXButtonBuscar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				cargarGraficaNivelEducativo();
			}
		});

	}


	public void cargarGraficaNivelEducativo(){
		sumaNumeroPersonas=0;
		Label labelInformacionNivelEducativo=new Label();
		labelInformacionNivelEducativo.setStyle("-fx-background-color:  rgba(0,0,0,0.6);"
											    + "-fx-background-radius:  5;"
											    + "-fx-text-fill: WHITE;"
											    + "-fx-font-weight: bold; ");
		CategoryAxis categoryAxisNivelEducativo=new CategoryAxis();
		NumberAxis numberAxisNivelEducativo=new NumberAxis();
		 BarChart<String, Number> barChartNivelEducativo=new BarChart<String, Number>(categoryAxisNivelEducativo, numberAxisNivelEducativo);
		

		XYChart.Series<String, Number> seriesNivelEducativo_Masculino =new XYChart.Series<String,Number>();;
		XYChart.Series<String, Number> seriesNivelEducatio_Femenino = new XYChart.Series<String, Number>();

		barChartNivelEducativo.setTitle("Número (n) de Personas - Tipo de Trabajo");
		numberAxisNivelEducativo.setLabel("Número de Personas(n)");
		categoryAxisNivelEducativo.setLabel("Tipo de Trabajo");
		seriesNivelEducatio_Femenino.setName("Femenino");
		seriesNivelEducativo_Masculino.setName("Masculino");
	
		

		
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			 
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement(
					" SELECT COUNT(*) AS NUMERO,SEXO,IF(SEXO=1,'Masculino','Femenino') AS SEXO, IFNULL((SELECT NOMBRE FROM TIPO_TRABAJO WHERE ID=ID_TIPO_TRABAJO),'No especifica') AS TIPO_TRABAJO FROM PER_DISCAPACIDAD WHERE  TIMESTAMPDIFF(YEAR,PER_DISCAPACIDAD.FECHA_NACIMIENTO,CURDATE()) BETWEEN ? AND ? GROUP BY ID_TIPO_TRABAJO,SEXO;");
			pst.setInt(1, Integer.parseInt((textFieldEdadMenor.getText().trim().isEmpty())?"-1":textFieldEdadMenor.getText().trim()));
			pst.setInt(2, Integer.parseInt((textFieldEdadMenor.getText().trim().isEmpty())?"-1":textFieldEdadMayor.getText().trim()));

			rs = pst.executeQuery();
			while (rs.next()) {
				sumaNumeroPersonas=sumaNumeroPersonas+rs.getInt("NUMERO");
				if(rs.getInt("SEXO")==1/*Masculino*/){
					
					seriesNivelEducativo_Masculino.getData().add(new XYChart.Data<String, Number>(rs.getString("TIPO_TRABAJO"), rs.getInt("NUMERO")));

				}else if(rs.getInt("SEXO")==2)/*Femenino*/{
					seriesNivelEducatio_Femenino.getData().add(new XYChart.Data<String, Number>(rs.getString("TIPO_TRABAJO"), rs.getInt("NUMERO")));
				}
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
		barChartNivelEducativo.getData().addAll(seriesNivelEducatio_Femenino,seriesNivelEducativo_Masculino);

		seriesNivelEducativo_Masculino.getData().forEach(data->{
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
				labelInformacionNivelEducativo.setVisible(true);
				labelInformacionNivelEducativo.setTranslateX(e.getSceneX()-50);;
				labelInformacionNivelEducativo.setTranslateY(e.getSceneY()-160);
				labelInformacionNivelEducativo.setText(String.valueOf(data.getYValue())+"\n"+Funciones.decimalReducido(String.valueOf(Integer.parseInt(String.valueOf(data.getYValue()))*100/sumaNumeroPersonas))+"%");  
	       
				
			});
		});
		seriesNivelEducatio_Femenino.getData().forEach(data->{
			data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, e->{
				labelInformacionNivelEducativo.setVisible(true);
				labelInformacionNivelEducativo.setTranslateX(e.getSceneX()-50);;
				labelInformacionNivelEducativo.setTranslateY(e.getSceneY()-160);
				labelInformacionNivelEducativo.setText(String.valueOf(data.getYValue())+"\n"+Funciones.decimalReducido(String.valueOf(Integer.parseInt(String.valueOf(data.getYValue()))*100/sumaNumeroPersonas))+"%");  
	       
				
			});
		});
		

		
		barChartNivelEducativo.layout();

	anchorPaneConenedorGrafico.getChildren().clear();
	
	
	AnchorPane.setBottomAnchor(barChartNivelEducativo, 0.0);
	AnchorPane.setLeftAnchor(barChartNivelEducativo, 0.0);
	AnchorPane.setRightAnchor(barChartNivelEducativo, 0.0);
	AnchorPane.setTopAnchor(barChartNivelEducativo, 0.0);
	anchorPaneConenedorGrafico.getChildren().addAll(barChartNivelEducativo,labelInformacionNivelEducativo);
	}
	
	
	 
	
	

}