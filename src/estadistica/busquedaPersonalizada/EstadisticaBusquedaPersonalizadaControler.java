package estadistica.busquedaPersonalizada;


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
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Arc;
import javafx.stage.Stage;
import registro.personaConDiscapacidad.nuevoRegistroPD.NivelGravedad;
	

public class EstadisticaBusquedaPersonalizadaControler extends Funciones implements Initializable {
	
	@FXML
    private BorderPane borderPaneDatosPersonal11;

    @FXML
    private BorderPane borderPaneContenedorPiechartClasificacionSisfoh;

    @FXML
    private Arc arcPersonasEncontradas;
    @FXML
    private Arc arcEstado;

    @FXML
    private Label labelinformacion;

    @FXML
    private ProgressIndicator progressIndicatorCargando;

    @FXML
    private BorderPane borderPaneDatosPersonal111;

    @FXML
    private ComboBox<ObjetoBusquedaPersonalizada> comboBoxClasificacionSisfoh;

    @FXML
    private ComboBox<ObjetoBusquedaPersonalizada> comboBoxSexo;

    @FXML
    private ComboBox<ObjetoBusquedaPersonalizada> comboBoxProvincia;

    @FXML
    private ComboBox<ObjetoBusquedaPersonalizada> comboBoxDistrito;

    @FXML
    private ComboBox<ObjetoBusquedaPersonalizada> comboBoxTipoDiscapacidad;

    @FXML
    private ComboBox<ObjetoBusquedaPersonalizada> comboBoxNivelGravedad;

    @FXML
    private ComboBox<ObjetoBusquedaPersonalizada> comboBoxOrigenLimitacion;

    @FXML
    private ComboBox<ObjetoBusquedaPersonalizada> comboBoxLimitacionPermanente;

    @FXML
    private ComboBox<ObjetoBusquedaPersonalizada> comboBoxAyudaBiometrica;

    @FXML
    private ComboBox<ObjetoBusquedaPersonalizada> comboBoxSeguroMedico;

    @FXML
    private ComboBox<ObjetoBusquedaPersonalizada> comboBoxCertificadoConadis;

    @FXML
    private ComboBox<ObjetoBusquedaPersonalizada> comboBoxInscripcionConadisNacional;

    @FXML
    private ComboBox<ObjetoBusquedaPersonalizada> comboBoxEdad;

    @FXML
    private ComboBox<ObjetoBusquedaPersonalizada> comboBoxNivelEducativo;

    @FXML
    private ComboBox<ObjetoBusquedaPersonalizada> comboBoxTipoEmpleo;

    @FXML
    private GridPane gridPane;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		inicializarComboBox();
		for( Node node: gridPane.getChildren()) {
		    if( node instanceof ComboBox) {
		    	ComboBox<ObjetoBusquedaPersonalizada> comboboxAux=(ComboBox<ObjetoBusquedaPersonalizada>)node;
		    	comboboxAux.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						
						
						
						String consultaSQLConta="SELECT count(*) FROM per_discapacidad INNER JOIN inscripcion_conadis_pasco ON "+
								"per_discapacidad.ID=inscripcion_conadis_pasco.ID_PER_DISCAPACIDAD INNER JOIN certificado_medico "+ 
								"on inscripcion_conadis_pasco.ID= certificado_medico.ID  where ";
						boolean and=false;
						boolean boolCargarDistritos=true;
						for(Node node: gridPane.getChildren()){
							
							if(node instanceof ComboBox){
								
								ComboBox<ObjetoBusquedaPersonalizada> comboboxAuxx=(ComboBox<ObjetoBusquedaPersonalizada>)node;
								if(comboboxAuxx.getSelectionModel().getSelectedItem()!=null){
									System.out.println(comboboxAuxx.getId()+"holaaaaaa"+boolCargarDistritos);
									/*if(comboboxAuxx.getId().equals("comboBoxProvincia") && boolCargarDistritos){
										boolCargarDistritos=false;
										new ModeloBP(comboBoxDistrito, "SELECT ID,NOMBRE FROM distrito WHERE ID_PROVINCIA="+comboBoxProvincia.getSelectionModel().getSelectedItem().getId(), "");

									}*/
									ObjetoBusquedaPersonalizada bu=comboboxAuxx.getSelectionModel().getSelectedItem();
								
									if(comboboxAuxx.getSelectionModel().getSelectedItem().getSentenciaSql()!=null && !comboboxAuxx.getSelectionModel().getSelectedItem().getSentenciaSql().trim().equals("")){
										if(and){
											consultaSQLConta=consultaSQLConta+ " and ";
										}
										and=true;
								    	   	consultaSQLConta=consultaSQLConta+comboboxAuxx.getSelectionModel().getSelectedItem().getSentenciaSql();
 	
								    	   
									}
								}
							}
					    	
						}
						
						double contarAux=ejecutarSentenciaContar(consultaSQLConta);
						double contarTotal=ejecutarSentenciaContar("SELECT COUNT(*) FROM PER_DISCAPACIDAD;");
						Double porcentaje=(double) (contarAux/contarTotal)*100;
					
						labelinformacion.setText(contarAux+" ("+decimalReducido(porcentaje+"")+"%) personas encontradas.");
					}
				});
		    }

		}
	}
	public void inicializarComboBox(){
		new ModeloBP(comboBoxSexo, FXCollections.observableArrayList(new ObjetoBusquedaPersonalizada("1", "Masculino", "SEXO=1"),new ObjetoBusquedaPersonalizada("2", "Femenino", "SEXO=2")));
		new ModeloBP(comboBoxEdad, 
				FXCollections.observableArrayList(new ObjetoBusquedaPersonalizada("1","0-11" , "TIMESTAMPDIFF(YEAR,PER_DISCAPACIDAD.FECHA_NACIMIENTO,CURDATE()) BETWEEN 0 AND 11"),
						new ObjetoBusquedaPersonalizada("2","12-17" , "TIMESTAMPDIFF(YEAR,PER_DISCAPACIDAD.FECHA_NACIMIENTO,CURDATE()) BETWEEN 12 AND 17"), 
						new ObjetoBusquedaPersonalizada("3","18-25" , "TIMESTAMPDIFF(YEAR,PER_DISCAPACIDAD.FECHA_NACIMIENTO,CURDATE()) BETWEEN 18 AND 25"),
						new ObjetoBusquedaPersonalizada("4","26-59" , "TIMESTAMPDIFF(YEAR,PER_DISCAPACIDAD.FECHA_NACIMIENTO,CURDATE()) BETWEEN 26 AND 59"),
						new ObjetoBusquedaPersonalizada("5","60-100" , "TIMESTAMPDIFF(YEAR,PER_DISCAPACIDAD.FECHA_NACIMIENTO,CURDATE()) >59")));
		new ModeloBP(comboBoxNivelEducativo, "SELECT ID,NOMBRE FROM nivel_educativo;", "ID_NIVEL_EDUCATIVO="+ModeloBP.SEPARADOR_ID);
		new ModeloBP(comboBoxTipoEmpleo, "SELECT ID,NOMBRE FROM tipo_trabajo;", "ID_TIPO_TRABAJO="+ModeloBP.SEPARADOR_ID);
		new ModeloBP(comboBoxClasificacionSisfoh, "SELECT ID,NOMBRE FROM clasificacion_sisfoh", "ID_CLASIFICACION_SISFOH="+ModeloBP.SEPARADOR_ID);
		new ModeloBP(comboBoxProvincia, "SELECT ID,NOMBRE FROM provincia;", "(SELECT  ID_PROVINCIA FROM DISTRITO WHERE ID=(SELECT ID_DISTRITO FROM DOMICILIO WHERE ID=ID_DOMICILIO))="+ModeloBP.SEPARADOR_ID);
//		new ModeloBP(comboBoxDistrito, "SELECT ID,NOMBRE FROM distrito WHERE ID_PROVINCIA="+comboBoxProvincia.getSelectionModel().getSelectedItem().getId(), "");

		new ModeloBP(comboBoxDistrito, "SELECT ID,NOMBRE FROM distrito ORDER BY NOMBRE", "(SELECT ID_DISTRITO FROM DOMICILIO WHERE ID=ID_DOMICILIO)="+ModeloBP.SEPARADOR_ID);

		

		new ModeloBP(comboBoxTipoDiscapacidad, "SELECT ID,NOMBRE FROM tipo_discapacidad;", "(SELECT ID_TIPO_DISCAPACIDAD FROM d_tipo_discapacidad_certificado_medico WHERE ID_CERTIFICADO_MEDICO=certificado_medico.ID AND ID_TIPO_DISCAPACIDAD="+ModeloBP.SEPARADOR_ID+")");
		new ModeloBP(comboBoxNivelGravedad, "SELECT ID,NOMBRE FROM nivel_gravedad_limitacion;", "certificado_medico.ID_NIVEL_GRAVEDAD_LIMITACION="+ModeloBP.SEPARADOR_ID);
		new ModeloBP(comboBoxOrigenLimitacion, "SELECT ID,NOMBRE FROM origen_limitacion;", "certificado_medico.ID_ORIGEN_LIMITACION="+ModeloBP.SEPARADOR_ID);
		new ModeloBP(comboBoxLimitacionPermanente, "SELECT ID,NOMBRE FROM limitacion_permanente_para;", " (SELECT ID_LIMITACION_PERMANENTE_PARA FROM d_limitacion_permanente_para_certificado_medico WHERE ID_CERTIFICADO_MEDICO=certificado_medico.ID AND ID_LIMITACION_PERMANENTE_PARA="+ModeloBP.SEPARADOR_ID+") ;");
		new ModeloBP(comboBoxAyudaBiometrica, "SELECT ID,NOMBRE FROM tipo_ayuda_biomecanica;", " (SELECT ID_TIPO_AYUDA_BIOMECANICA FROM d_tipo_ayuda_biomecanica_certificado_medico WHERE ID_CERTIFICADO_MEDICO=certificado_medico.ID AND ID_TIPO_AYUDA_BIOMECANICA="+ModeloBP.SEPARADOR_ID+") ;");
		new ModeloBP(comboBoxSeguroMedico, "SELECT ID,NOMBRE FROM tipo_seguro;", " (SELECT ID_TIPO_SEGURO FROM d_ttipo_seguro_certificado_medico WHERE ID_CERTIFICADO_MEDICO=certificado_medico.ID AND ID_TIPO_SEGURO="+ModeloBP.SEPARADOR_ID+");");
		
		new ModeloBP(comboBoxCertificadoConadis, "SELECT ID,NOMBRE FROM tiene_certificado_discapacidad;", "inscripcion_conadis_pasco.ID_TIENE_CERTIFICADO_DISCAPACIDAD="+ModeloBP.SEPARADOR_ID);
		new ModeloBP(comboBoxInscripcionConadisNacional, "SELECT ID,NOMBRE FROM tiene_inscripcion_conadis;", "inscripcion_conadis_pasco.ID_TIENE_INSCRIPCION_CONADIS="+ModeloBP.SEPARADOR_ID);

		
		
		//new ModeloBP(comboBox, "SELECT ID,NOMBRE FROM provincia;", "");
		
		

	}
	
	public int ejecutarSentenciaContar(String sentencia){
		int contar=-1;
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement(sentencia);
			
			
			rs=pst.executeQuery();
			while(rs.next()){
			contar=rs.getInt(1);	
			}
			
		}catch(Exception e){
			
			
		}finally{
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
		return contar;
	}
	
	

}