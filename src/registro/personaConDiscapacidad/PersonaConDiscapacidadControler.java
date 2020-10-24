package registro.personaConDiscapacidad;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;

import com.jfoenix.controls.JFXButton;

import cargando.Cargando;
import funciones.AbstractJasperReports;
import funciones.Conexion;
import funciones.Funciones;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import registro.personaConDiscapacidad.modificarRegistroPD.ModificarRegistroPDControler;
import registro.personaConDiscapacidad.modificarRegistroPD.ModificarRegistroPDControler_v1;
import registro.personaConDiscapacidad.nuevoRegistroPD.NuevoRegistroPDControler;
import registro.personaConDiscapacidad.nuevoRegistroPD.RequerimientoApoyo;


public class PersonaConDiscapacidadControler extends Funciones implements Initializable {
	
	//scene y controler
	private Scene sceneModalNuevoRegistro;
	private NuevoRegistroPDControler nuevoRegistroPDControler;
	private static Task<Void> task;
		
	@FXML private ProgressIndicator progressIndicatorMostrarDatosPerDiscapacidad;
	
	
	@FXML
	private JFXButton jFXButtonNuevoRegistro;

	@FXML
	private BorderPane borderPaneDatosPersonal;

	@FXML
	private TableView<PersonaDiscapacidad> tableViewPerDiscapacidad;

	@FXML
	private TableColumn<PersonaDiscapacidad, String> tableColumnDNI_CUI;

	@FXML
	private TableColumn<PersonaDiscapacidad, String> tableColumnApellidosYNombres;

	@FXML
	private TableColumn<PersonaDiscapacidad, Number> tableColumnEdad;

	@FXML
	private TableColumn<PersonaDiscapacidad, String> tableColumnSexo;

	@FXML
	private TableColumn<PersonaDiscapacidad, String> tableColumnProvincia;

	@FXML
	private TableColumn<PersonaDiscapacidad, String> tableColumnDistrito;

	@FXML
	private TableColumn<PersonaDiscapacidad, String> tableColumnNombreVia;

	@FXML
	private TableColumn<PersonaDiscapacidad, String> tableColumnTipoDiscapacidad;

	@FXML
	private TableColumn<PersonaDiscapacidad, String> tableColumnNivelGravedadDiscapacidad;

	@FXML
	private TableColumn<PersonaDiscapacidad, String> tableColumnTiieneCertificadoDiscapacidad;

	@FXML
	private TableColumn<PersonaDiscapacidad, String> tableColumnObservacion;

	@FXML
	private TableColumn<PersonaDiscapacidad, String> tableColumnOpciones;

	
	//nuevas modificaciones 
	
	@FXML private TableColumn<PersonaDiscapacidad, String> tableColumnNumeroCertificadoDiscapacidad;
	@FXML private TableColumn<PersonaDiscapacidad, String> tableColumnTiieneInscripcionConNac;
	@FXML private TableColumn<PersonaDiscapacidad, String> tableColumnNumeroCarne;
	@FXML private TableColumn<PersonaDiscapacidad, String> tableColumnNumeroResolucion;

	@FXML
	private BorderPane borderPaneContenedorBusqueda;

	@FXML
	private TextField textFieldBuscar;

	@FXML
	private Button ButtonBuscar;
	
	private ObservableList<PersonaDiscapacidad> arrayTableViewPerDiscapacidad = FXCollections.observableArrayList();

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		iniciarValoresTableViewDatosPersonaDiscapacidad();
		//mostrarDatosPersonaDiscapacidad();
		Task<Void> task=new Task<Void>(){

			@Override
			protected Void call() throws Exception {
				int i=0;
				while(true){
					//PersonaDiscapacidad personaDiscapacidadSelec=tableViewPerDiscapacidad.getSelectionModel().getSelectedItem();
					//tableViewPerDiscapacidad.getItems().clear();
					tableViewPerDiscapacidad.setItems(obtenerPersonaDiscapacidadBD(textFieldBuscar.getText().trim()));
					tableViewPerDiscapacidad.refresh();
					//tableViewPerDiscapacidad.getSelectionModel().select(personaDiscapacidadSelec);
					Thread.sleep(30000);
					i++;
				}
				
			}};
			
			Thread hilo=new Thread(task);
			hilo.setDaemon(true);
			hilo.start();
			
			cargarModalNuevoRegistroPD();
		textFieldBuscar.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				tableViewPerDiscapacidad.setItems(obtenerPersonaDiscapacidadBD(textFieldBuscar.getText().trim()));
				tableViewPerDiscapacidad.refresh();

				//mostrarDatosPersonaDiscapacidad(textFieldBuscar.getText().trim());
			}
		});
		jFXButtonNuevoRegistro.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
		
				
				
				JFXButton tg = (JFXButton) event.getSource();
				Stage stagePadre = (Stage) tg.getScene().getWindow();
				
				//Stage cargando=Cargando.cargando(stagePadre);
				//cargando.show();
		
				StackPane stackPane = (StackPane) stagePadre.getScene().getRoot().getChildrenUnmodifiable().get(1);
				stackPane.setVisible(true);
				
				TextInputDialog dialog = new TextInputDialog();
				dialog.setTitle("Nuevo Registro");
				dialog.setHeaderText("Nuevo Registro de Persona con Discapacidad");
				dialog.setContentText("Ingrese por favor el Nro de DNI:");

				// Traditional way to get the response value.
				Optional<String> result = dialog.showAndWait();
				if (result.isPresent()){
				    if(!verificarRegistroExistente(result.get())){
				    	String urlFxml = "/registro/personaConDiscapacidad/nuevoRegistroPD/NuevoRegistroPD.fxml";
						String css = "/registro/personaConDiscapacidad/nuevoRegistroPD/nuevoRegistroPD.css";
						try {
							//mostrarInterfazModalShowAndWait(urlFxml, css);
							
							FXMLLoader fXMLLoader=new FXMLLoader();
							fXMLLoader.setLocation(getClass().getResource(urlFxml));
							fXMLLoader.load();
							NuevoRegistroPDControler nuevoRegistroPD=fXMLLoader.getController();
							nuevoRegistroPD.getRadioButtonDNI().setSelected(true);
							nuevoRegistroPD.getTextFieldDocId().setText(result.get());
							Parent parent= fXMLLoader.getRoot();
							Scene scene=new Scene(parent);
							scene.setFill(new Color(0,0,0,0));
							scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
							
							
									stackPane.setVisible(true);
									Stage stage=new Stage();
									stage.setScene(scene);
									stage.initModality(Modality.APPLICATION_MODAL);
									stage.initStyle(StageStyle.TRANSPARENT);
									stage.showAndWait();;

									//mostrarDatosPersonaDiscapacidad();

							
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }else{
				    	Alert alert = new Alert(AlertType.INFORMATION);
				    	alert.setTitle("Información");
				    	alert.setHeaderText(null);
				    	alert.setContentText("Ya existe un registro con este DNI :)!");

				    	alert.showAndWait();
				    }
					
					
				}

				

				
				stackPane.setVisible(false);

				
				
				/*

				Stage stage=new Stage();
				stage.setScene(sceneModalNuevoRegistro);
				stage.initModality(Modality.APPLICATION_MODAL);
				stage.initStyle(StageStyle.TRANSPARENT);
				stage.showAndWait();;
								
				stackPane.setVisible(false);**
				//System.out.println(stagePadre.getScene().getRoot().getChildrenUnmodifiable().get(1));
				/*
				
				Task<Void> task=new Task<Void>(){

					@Override
					protected Void call() throws Exception {
						
						String urlFxml = "/registro/personaConDiscapacidad/nuevoRegistroPD/NuevoRegistroPD.fxml";
						String css = "/registro/personaConDiscapacidad/nuevoRegistroPD/nuevoRegistroPD.css";
						try {
							//mostrarInterfazModalShowAndWait(urlFxml, css);
							
							FXMLLoader fXMLLoader=new FXMLLoader();
							fXMLLoader.setLocation(getClass().getResource(urlFxml));
							fXMLLoader.load();
							Parent parent= fXMLLoader.getRoot();
							Scene scene=new Scene(parent);
							scene.setFill(new Color(0,0,0,0));
							scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
							
							Platform.runLater(new Runnable() {
								
								@Override
								public void run() {
									stackPane.setVisible(true);
									Stage stage=new Stage();
									stage.setScene(scene);
									stage.initModality(Modality.APPLICATION_MODAL);
									stage.initStyle(StageStyle.TRANSPARENT);
									cargando.close();
									stage.showAndWait();;
									stackPane.setVisible(true);

									mostrarDatosPersonaDiscapacidad();

								}
							});
							
							
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						
						return null;
					}
					
				}; 
				
				
				Thread hilo=new Thread(task);
				hilo.setDaemon(true);
				hilo.start();
				
				
				
				

				stackPane.setVisible(false);
*/
			}
		});

	}

	
	public ObservableList<PersonaDiscapacidad> obtenerPersonaDiscapacidadBD(String buscar){
		ObservableList<PersonaDiscapacidad> arrayPersonaDiscapacidad=FXCollections.observableArrayList();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
        	conn = DriverManager.getConnection(Conexion.SERVIDOR, Conexion.USER, Conexion.PASS);
			pst = conn.prepareStatement(
					"SELECT INSCRIPCION_CONADIS_PASCO.ID,INSCRIPCION_CONADIS_PASCO.ID_PER_DISCAPACIDAD, CONCAT_WS( '/',PER_DISCAPACIDAD.DNI,PER_DISCAPACIDAD.CUI) AS DNI_CUI,"+
"CONCAT(PER_DISCAPACIDAD.APELL_PATERNO,' ',PER_DISCAPACIDAD.APELL_MATERNO,', ', PER_DISCAPACIDAD.NOMBRES) AS   APELLIDOS_NOMBRES, "+
"TIMESTAMPDIFF(YEAR,PER_DISCAPACIDAD.FECHA_NACIMIENTO,CURDATE()) AS EDAD, IF(PER_DISCAPACIDAD.SEXO=2,'Femenino','Masculino') AS SEXO,"+ 
"(SELECT PROVINCIA.NOMBRE FROM PROVINCIA WHERE ID=(SELECT DISTRITO.ID_PROVINCIA FROM DISTRITO WHERE "+
"ID=(SELECT DOMICILIO.ID_DISTRITO FROM DOMICILIO WHERE ID=PER_DISCAPACIDAD.ID_DOMICILIO))) AS PROVINCIA,"+
"(SELECT DISTRITO.NOMBRE FROM DISTRITO WHERE ID=(SELECT DOMICILIO.ID_DISTRITO FROM DOMICILIO WHERE ID=PER_DISCAPACIDAD.ID_DOMICILIO)) AS DISTRITO,"+
"(SELECT DOMICILIO.NOMBRE_VIA FROM DOMICILIO WHERE ID=PER_DISCAPACIDAD.ID_DOMICILIO) AS NOMBRE_VIA_DOMICILIO ,"+
"(SELECT TIENE_CERTIFICADO_DISCAPACIDAD.NOMBRE FROM TIENE_CERTIFICADO_DISCAPACIDAD WHERE ID=INSCRIPCION_CONADIS_PASCO.ID_TIENE_CERTIFICADO_DISCAPACIDAD) AS TIENE_CERTIFICADO_DISCAPACIDAD,"+
"(SELECT TIENE_INSCRIPCION_CONADIS.NOMBRE FROM TIENE_INSCRIPCION_CONADIS WHERE ID=INSCRIPCION_CONADIS_PASCO.ID_TIENE_INSCRIPCION_CONADIS) AS TIENE_INSCRIPCION_CONADIS,"+
"(SELECT GROUP_CONCAT((SELECT TIPO_DISCAPACIDAD.NOMBRE FROM TIPO_DISCAPACIDAD WHERE ID=D_TIPO_DISCAPACIDAD_CERTIFICADO_MEDICO.ID_TIPO_DISCAPACIDAD))  FROM D_TIPO_DISCAPACIDAD_CERTIFICADO_MEDICO WHERE ID_CERTIFICADO_MEDICO=INSCRIPCION_CONADIS_PASCO.ID) AS GROUP_TIPO_DISCAPACIDAD,"+
"(SELECT NIVEL_GRAVEDAD_LIMITACION.NOMBRE FROM NIVEL_GRAVEDAD_LIMITACION WHERE ID=(SELECT CERTIFICADO_MEDICO.ID_NIVEL_GRAVEDAD_LIMITACION FROM CERTIFICADO_MEDICO WHERE CERTIFICADO_MEDICO.ID=INSCRIPCION_CONADIS_PASCO.ID)) AS NIVEL_GRAVEDAD "
+ ""
+ " , "
+ " NRO_CARNE, "
+"ANNO_INCRIPCCION, "
+" (SELECT FECHA_CERTIFICADO FROM certificado_medico WHERE ID= INSCRIPCION_CONADIS_PASCO.ID) AS FECHA_CERTIFICADO_MEDICO_EMISION, "

+"(SELECT  NUMERO_CERTIFICADO FROM certificado_medico WHERE ID=INSCRIPCION_CONADIS_PASCO.ID) AS NUMERO_CERTIFICADO,"

+"(SELECT NRO_RESOLUCION FROM resolucion WHERE ID=INSCRIPCION_CONADIS_PASCO.ID) AS NRO_RESOLUCION "
+ ""+


"FROM INSCRIPCION_CONADIS_PASCO INNER JOIN PER_DISCAPACIDAD ON INSCRIPCION_CONADIS_PASCO.ID_PER_DISCAPACIDAD=PER_DISCAPACIDAD.ID WHERE PER_DISCAPACIDAD.APELL_PATERNO LIKE ? OR PER_DISCAPACIDAD.APELL_MATERNO LIKE ? OR PER_DISCAPACIDAD.NOMBRES LIKE ? OR "+
 "CONCAT_WS( '/',PER_DISCAPACIDAD.DNI,PER_DISCAPACIDAD.CUI) LIKE ? order by INSCRIPCION_CONADIS_PASCO.FECHA_MODIFICACION desc ;");
			pst.setString(1, "%" + buscar + "%");
			pst.setString(2, "%" + buscar + "%");
			pst.setString(3, "%" + buscar + "%");
			pst.setString(4, "%" + buscar + "%");
			rs = pst.executeQuery();


			while (rs.next()) {
				
			
				
				PersonaDiscapacidad auxiliar=new PersonaDiscapacidad(rs.getInt("ID"),rs.getInt("ID_PER_DISCAPACIDAD"),
						rs.getString("DNI_CUI"), rs.getString("APELLIDOS_NOMBRES"), rs.getString("EDAD"),
						rs.getString("SEXO"), rs.getString("PROVINCIA"), rs.getString("DISTRITO"),
						rs.getString("NOMBRE_VIA_DOMICILIO"), rs.getString("GROUP_TIPO_DISCAPACIDAD"),
						rs.getString("NIVEL_GRAVEDAD"), rs.getString("TIENE_CERTIFICADO_DISCAPACIDAD"), "",
						rs.getString("NUMERO_CERTIFICADO"),
						rs.getString("TIENE_INSCRIPCION_CONADIS"),
						rs.getString("NRO_CARNE"),
						rs.getString("NRO_RESOLUCION"));
				
				auxiliar.getOpciones().getjFXButtonVer().setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						 Connection conn = null;
						 Conexion.conectarDB();
						conn = Conexion.getConexion();
						Map<String, Object> parameters=new HashMap();
						parameters.put("id_usuario", auxiliar.getId_inscripcion_conadis());
						AbstractJasperReports.createReport(conn, "reports\\Flower.jasper",parameters);
						AbstractJasperReports.showViewer();
					}
				});
				
				auxiliar.getOpciones().getjFXButtonExportar().setOnAction(new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
						FileChooser fileChooser = new FileChooser();
						
				        FileChooser.ExtensionFilter extFilterpdf = new FileChooser.ExtensionFilter("pdf files (*.pdf)", "*.pdf");
				        fileChooser.getExtensionFilters().addAll(extFilterpdf);
				        File file;
				        file = fileChooser.showSaveDialog(null);
				        
				        Connection conn = null;
						 Conexion.conectarDB();
						conn = Conexion.getConexion();
						Map<String, Object> parameters=new HashMap();
						parameters.put("id_usuario", auxiliar.getId_inscripcion_conadis());
						AbstractJasperReports.createReport(conn, "reports\\Flower.jasper",parameters);
						System.out.println(file.getPath());
						AbstractJasperReports.exportToPDF(file.getPath());
				       
		
						
					}
				});
				
				auxiliar.getOpciones().getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent arg0) {
						JFXButton tg = (JFXButton) arg0.getSource();

						Stage stage = (Stage) tg.getScene().getWindow();
						StackPane stackPane = (StackPane) stage.getScene().getRoot().getChildrenUnmodifiable().get(1);
						stackPane.setVisible(true);
						System.out.println(stage.getScene().getRoot().getChildrenUnmodifiable().get(1));

						String urlFxml = "/registro/personaConDiscapacidad/modificarRegistroPD/ModificarRegistroPD.fxml";
						String css = "/registro/personaConDiscapacidad/modificarRegistroPD/modificarRegistroPD.css";
						try {
							System.out.println("Hola estoy dento de, metodo de modal :D");
							FXMLLoader fXMLLoader=new FXMLLoader();
							fXMLLoader.setLocation(getClass().getResource(urlFxml));
							fXMLLoader.load();
							ModificarRegistroPDControler modificar=fXMLLoader.getController();
							modificar.cargarDatos(auxiliar.getId_inscripcion_conadis());
							Parent parent= fXMLLoader.getRoot();
							Scene scene=new Scene(parent);
							scene.setFill(new Color(0,0,0,0));
							scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
							Stage stage1=new Stage();
							stage1.setScene(scene);
							stage1.initModality(Modality.APPLICATION_MODAL);
							stage1.initStyle(StageStyle.TRANSPARENT);
							stage1.showAndWait();;
							mostrarDatosPersonaDiscapacidad();

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						stackPane.setVisible(false);
					}
				});
			
				arrayPersonaDiscapacidad.add(auxiliar);
				
							}
		} catch (Exception e) {

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

		
		
		return arrayPersonaDiscapacidad;
	}
	
	public void iniciarValoresTableViewDatosPersonaDiscapacidad() {
		
		tableViewPerDiscapacidad.setItems(arrayTableViewPerDiscapacidad);
		tableColumnDNI_CUI.setCellValueFactory(new PropertyValueFactory<PersonaDiscapacidad,String>("dni_Cui"));
		tableColumnApellidosYNombres.setCellValueFactory(new PropertyValueFactory<PersonaDiscapacidad,String>("nombresYApellidos"));
		tableColumnEdad.setCellValueFactory(new PropertyValueFactory<PersonaDiscapacidad,Number>("edad"));
		//tableColumnEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

		tableColumnSexo.setCellValueFactory(new PropertyValueFactory<PersonaDiscapacidad,String>("sexo"));
		tableColumnProvincia.setCellValueFactory(new PropertyValueFactory<PersonaDiscapacidad,String>("provincia"));
		tableColumnDistrito.setCellValueFactory(new PropertyValueFactory<PersonaDiscapacidad,String>("distrito"));
		tableColumnNombreVia.setCellValueFactory(new PropertyValueFactory<PersonaDiscapacidad,String>("nombreVia"));
		tableColumnTipoDiscapacidad.setCellValueFactory(new PropertyValueFactory<PersonaDiscapacidad,String>("tipoDiscapacidad"));
		tableColumnNivelGravedadDiscapacidad.setCellValueFactory(new PropertyValueFactory<PersonaDiscapacidad,String>("nivelGravedad"));
		tableColumnTiieneCertificadoDiscapacidad.setCellValueFactory(new PropertyValueFactory<>("tieneCertificado"));
		tableColumnObservacion.setCellValueFactory(new PropertyValueFactory<PersonaDiscapacidad,String>("obervacion"));
		tableColumnOpciones.setCellValueFactory(new PropertyValueFactory<PersonaDiscapacidad,String>("opciones") );
		
		//nuevas modificaciones
		tableColumnNumeroCertificadoDiscapacidad.setCellValueFactory(new PropertyValueFactory<PersonaDiscapacidad,String>("numeroCertificado") );
		tableColumnTiieneInscripcionConNac.setCellValueFactory(new PropertyValueFactory<PersonaDiscapacidad,String>("tieneInscripcionNacional") );
		tableColumnNumeroCarne.setCellValueFactory(new PropertyValueFactory<PersonaDiscapacidad,String>("numeroCarne") );;
		tableColumnNumeroResolucion.setCellValueFactory(new PropertyValueFactory<PersonaDiscapacidad,String>("numeroResolucion") );;
		
		
	
		
		
		tableColumnNivelGravedadDiscapacidad.setCellFactory(new  Callback<TableColumn<PersonaDiscapacidad,String>, TableCell<PersonaDiscapacidad,String>>() {

			@Override
			public TableCell<PersonaDiscapacidad, String> call(TableColumn<PersonaDiscapacidad, String> param) {
				
				return new TableCell<PersonaDiscapacidad, String>() {
					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						
		                if (!empty) {
		                	setText(item);
		                	PersonaDiscapacidad person=(PersonaDiscapacidad)this.getTableRow().getItem();
		                	//DEFINIREMOS EL NUEVO COLOR PARA DECIR SI ES GRAVE O LEVE O MODERADO
		                	setTextFill(Color.RED);
		                } else {
		                	setText(null);
		                }
					}
				};
			}
		});

	}

	public void mostrarDatosPersonaDiscapacidad() {
		mostrarDatosPersonaDiscapacidad(textFieldBuscar.getText().trim());
	}

	public void mostrarDatosPersonaDiscapacidad(String buscar) {
		
		if(task!=null){
			task.cancel();
		}
		
		progressIndicatorMostrarDatosPerDiscapacidad.setVisible(true);
		task=new Task<Void>() {

			@Override
			protected Void call() throws Exception {
				
				//inicio del hilo xD
				
				// vamos limpiar el contenedor de los botones
				tableViewPerDiscapacidad.getItems().clear();
				// hacer la conexcioon con la base de datos y obtener alas busquedas
				// encontradas
				 Connection conn = null;
				PreparedStatement pst = null;
				ResultSet rs = null;
				try {
					Conexion.conectarDB();
					conn = Conexion.getConexion();
					pst = conn.prepareStatement(
							"SELECT INSCRIPCION_CONADIS_PASCO.ID,INSCRIPCION_CONADIS_PASCO.ID_PER_DISCAPACIDAD, CONCAT_WS( '/',PER_DISCAPACIDAD.DNI,PER_DISCAPACIDAD.CUI) AS DNI_CUI,"+
		"CONCAT(PER_DISCAPACIDAD.APELL_PATERNO,' ',PER_DISCAPACIDAD.APELL_MATERNO,', ', PER_DISCAPACIDAD.NOMBRES) AS   APELLIDOS_NOMBRES, "+
		"TIMESTAMPDIFF(YEAR,PER_DISCAPACIDAD.FECHA_NACIMIENTO,CURDATE()) AS EDAD, IF(PER_DISCAPACIDAD.SEXO=2,'Femenino','Masculino') AS SEXO,"+ 
		"(SELECT PROVINCIA.NOMBRE FROM PROVINCIA WHERE ID=(SELECT DISTRITO.ID_PROVINCIA FROM DISTRITO WHERE "+
		"ID=(SELECT DOMICILIO.ID_DISTRITO FROM DOMICILIO WHERE ID=PER_DISCAPACIDAD.ID_DOMICILIO))) AS PROVINCIA,"+
		"(SELECT DISTRITO.NOMBRE FROM DISTRITO WHERE ID=(SELECT DOMICILIO.ID_DISTRITO FROM DOMICILIO WHERE ID=PER_DISCAPACIDAD.ID_DOMICILIO)) AS DISTRITO,"+
		"(SELECT DOMICILIO.NOMBRE_VIA FROM DOMICILIO WHERE ID=PER_DISCAPACIDAD.ID_DOMICILIO) AS NOMBRE_VIA_DOMICILIO ,"+
		"(SELECT TIENE_CERTIFICADO_DISCAPACIDAD.NOMBRE FROM TIENE_CERTIFICADO_DISCAPACIDAD WHERE ID=INSCRIPCION_CONADIS_PASCO.ID_TIENE_CERTIFICADO_DISCAPACIDAD) AS TIENE_CERTIFICADO_DISCAPACIDAD,"+
		"(SELECT TIENE_INSCRIPCION_CONADIS.NOMBRE FROM TIENE_INSCRIPCION_CONADIS WHERE ID=INSCRIPCION_CONADIS_PASCO.ID_TIENE_INSCRIPCION_CONADIS) AS TIENE_INSCRIPCION_CONADIS,"+
		"(SELECT GROUP_CONCAT((SELECT TIPO_DISCAPACIDAD.NOMBRE FROM TIPO_DISCAPACIDAD WHERE ID=D_TIPO_DISCAPACIDAD_CERTIFICADO_MEDICO.ID_TIPO_DISCAPACIDAD))  FROM D_TIPO_DISCAPACIDAD_CERTIFICADO_MEDICO WHERE ID_CERTIFICADO_MEDICO=INSCRIPCION_CONADIS_PASCO.ID) AS GROUP_TIPO_DISCAPACIDAD,"+
		"(SELECT NIVEL_GRAVEDAD_LIMITACION.NOMBRE FROM NIVEL_GRAVEDAD_LIMITACION WHERE ID=(SELECT CERTIFICADO_MEDICO.ID_NIVEL_GRAVEDAD_LIMITACION FROM CERTIFICADO_MEDICO WHERE CERTIFICADO_MEDICO.ID=INSCRIPCION_CONADIS_PASCO.ID)) AS NIVEL_GRAVEDAD "
		+ ""
		+ " , "
		+ " NRO_CARNE, "
		+"ANNO_INCRIPCCION, "
		+" (SELECT FECHA_CERTIFICADO FROM certificado_medico WHERE ID= INSCRIPCION_CONADIS_PASCO.ID) AS FECHA_CERTIFICADO_MEDICO_EMISION, "

		+"(SELECT  NUMERO_CERTIFICADO FROM certificado_medico WHERE ID=INSCRIPCION_CONADIS_PASCO.ID) AS NUMERO_CERTIFICADO,"

		+"(SELECT NRO_RESOLUCION FROM resolucion WHERE ID=INSCRIPCION_CONADIS_PASCO.ID) AS NRO_RESOLUCION "
		+ ""+


		"FROM INSCRIPCION_CONADIS_PASCO INNER JOIN PER_DISCAPACIDAD ON INSCRIPCION_CONADIS_PASCO.ID_PER_DISCAPACIDAD=PER_DISCAPACIDAD.ID WHERE PER_DISCAPACIDAD.APELL_PATERNO LIKE ? OR PER_DISCAPACIDAD.APELL_MATERNO LIKE ? OR PER_DISCAPACIDAD.NOMBRES LIKE ? OR "+
		 "CONCAT_WS( '/',PER_DISCAPACIDAD.DNI,PER_DISCAPACIDAD.CUI) LIKE ? order by INSCRIPCION_CONADIS_PASCO.FECHA_MODIFICACION desc ;");
					pst.setString(1, "%" + buscar + "%");
					pst.setString(2, "%" + buscar + "%");
					pst.setString(3, "%" + buscar + "%");
					pst.setString(4, "%" + buscar + "%");
					rs = pst.executeQuery();

					while (rs.next()) {
						
					
						
						PersonaDiscapacidad auxiliar=new PersonaDiscapacidad(rs.getInt("ID"),rs.getInt("ID_PER_DISCAPACIDAD"),
								rs.getString("DNI_CUI"), rs.getString("APELLIDOS_NOMBRES"), rs.getString("EDAD"),
								rs.getString("SEXO"), rs.getString("PROVINCIA"), rs.getString("DISTRITO"),
								rs.getString("NOMBRE_VIA_DOMICILIO"), rs.getString("GROUP_TIPO_DISCAPACIDAD"),
								rs.getString("NIVEL_GRAVEDAD"), rs.getString("TIENE_CERTIFICADO_DISCAPACIDAD"), "",
								rs.getString("NUMERO_CERTIFICADO"),
								rs.getString("TIENE_INSCRIPCION_CONADIS"),
								rs.getString("NRO_CARNE"),
								rs.getString("NRO_RESOLUCION"));
						
						auxiliar.getOpciones().getjFXButtonVer().setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent event) {
								 Connection conn = null;
								 Conexion.conectarDB();
								conn = Conexion.getConexion();
								Map<String, Object> parameters=new HashMap();
								parameters.put("id_usuario", auxiliar.getId_inscripcion_conadis());
								AbstractJasperReports.createReport(conn, "reports\\Flower.jasper",parameters);
								AbstractJasperReports.showViewer();
							}
						});
						
						auxiliar.getOpciones().getjFXButtonExportar().setOnAction(new EventHandler<ActionEvent>() {

							@Override
							public void handle(ActionEvent arg0) {
								FileChooser fileChooser = new FileChooser();
								
						        FileChooser.ExtensionFilter extFilterpdf = new FileChooser.ExtensionFilter("pdf files (*.pdf)", "*.pdf");
						        fileChooser.getExtensionFilters().addAll(extFilterpdf);
						        File file;
						        file = fileChooser.showSaveDialog(null);
						        
						        Connection conn = null;
								 Conexion.conectarDB();
								conn = Conexion.getConexion();
								Map<String, Object> parameters=new HashMap();
								parameters.put("id_usuario", auxiliar.getId_inscripcion_conadis());
								AbstractJasperReports.createReport(conn, "reports\\Flower.jasper",parameters);
								System.out.println(file.getPath());
								AbstractJasperReports.exportToPDF(file.getPath());
						       
				
								
							}
						});
						
						auxiliar.getOpciones().getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {
							
							@Override
							public void handle(ActionEvent arg0) {
								JFXButton tg = (JFXButton) arg0.getSource();

								Stage stage = (Stage) tg.getScene().getWindow();
								StackPane stackPane = (StackPane) stage.getScene().getRoot().getChildrenUnmodifiable().get(1);
								stackPane.setVisible(true);
								System.out.println(stage.getScene().getRoot().getChildrenUnmodifiable().get(1));

								String urlFxml = "/registro/personaConDiscapacidad/modificarRegistroPD/ModificarRegistroPD.fxml";
								String css = "/registro/personaConDiscapacidad/modificarRegistroPD/modificarRegistroPD.css";
								try {
									System.out.println("Hola estoy dento de, metodo de modal :D");
									FXMLLoader fXMLLoader=new FXMLLoader();
									fXMLLoader.setLocation(getClass().getResource(urlFxml));
									fXMLLoader.load();
									ModificarRegistroPDControler modificar=fXMLLoader.getController();
									modificar.cargarDatos(auxiliar.getId_inscripcion_conadis());
									Parent parent= fXMLLoader.getRoot();
									Scene scene=new Scene(parent);
									scene.setFill(new Color(0,0,0,0));
									scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
									Stage stage1=new Stage();
									stage1.setScene(scene);
									stage1.initModality(Modality.APPLICATION_MODAL);
									stage1.initStyle(StageStyle.TRANSPARENT);
									stage1.showAndWait();;
									mostrarDatosPersonaDiscapacidad();

								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								stackPane.setVisible(false);
							}
						});
					
						if(textFieldBuscar.getText().trim().equals(buscar)){
							Platform.runLater(new Runnable() {
								
								@Override
								public void run() {
									tableViewPerDiscapacidad.getItems().add(auxiliar);
								}
							});
						}else{
							
							break;
						}
					}
				} catch (Exception e) {

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
		
				//fin del hilo 
				return null;
				}
			};
			
			task.stateProperty().addListener(new ChangeListener<Worker.State>() {

				@Override
				public void changed(ObservableValue<? extends State> arg0, State arg1, Worker.State arg2) {
					if(arg2==Worker.State.SUCCEEDED){
						progressIndicatorMostrarDatosPerDiscapacidad.setVisible(false);
					}
				}
			});
			
			Thread hilo=new Thread(task);
			hilo.setDaemon(true);
			hilo.start();
			
				
			}
			
			
			
	public void cargarModalNuevoRegistroPD(){
		String urlFxml = "/registro/personaConDiscapacidad/nuevoRegistroPD/NuevoRegistroPD.fxml";
		String css = "/registro/personaConDiscapacidad/nuevoRegistroPD/nuevoRegistroPD.css";	try {
			FXMLLoader fXMLLoader=new FXMLLoader();
			fXMLLoader.setLocation(getClass().getResource(urlFxml));
			fXMLLoader.load();
			nuevoRegistroPDControler=fXMLLoader.getController();
			Parent parent= fXMLLoader.getRoot();
			sceneModalNuevoRegistro=new Scene(parent);
			sceneModalNuevoRegistro.setFill(new Color(0,0,0,0));
			sceneModalNuevoRegistro.getStylesheets().add(getClass().getResource(css).toExternalForm());
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public boolean verificarRegistroExistente(String numeroDni){
		boolean existeRegitro=false;
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID FROM PER_DISCAPACIDAD WHERE DNI=? OR CUI=?;");
			pst.setString(1,numeroDni );
			pst.setString(2, numeroDni);
			rs=pst.executeQuery();
			while(rs.next()){
				existeRegitro=true;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
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
	
		return existeRegitro;
		
		

	}

}