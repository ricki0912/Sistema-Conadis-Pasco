package gestMedicoCert.establecimiento_salud;

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
import javafx.scene.control.ButtonType;
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


public class EstablecimientoSaludControler extends Funciones implements Initializable {
	
		@FXML
	    private TextField textFieldBuscar;

	    @FXML
	    private Button ButtonBuscar;

	    @FXML
	    private JFXButton jFXButtonNuevoEstSalud;
	
		@FXML
	    private TableView<EstablecimientoSalud> tableViewEstSalud;

	    @FXML
	    private TableColumn<EstablecimientoSalud, Number> tableColumnNro;

	    @FXML
	    private TableColumn<EstablecimientoSalud, String> tableColumnNombres;

	    @FXML
	    private TableColumn<EstablecimientoSalud, String> tableColumnDirector;

	    @FXML
	    private TableColumn<EstablecimientoSalud, String> tableColumnObservacion;

	    @FXML
	    private TableColumn<EstablecimientoSalud, String> tableColumnOpciones;


	@Override
	public void initialize(URL location, ResourceBundle resources) {

		iniciarValoresTableViewEstablecimientoSalud();
		//mostrarDatosPersonaDiscapacidad();
		Task<Void> task=new Task<Void>(){

			@Override
			protected Void call() throws Exception {
				int i=0;
				while(true){
					tableViewEstSalud.setItems(obtenerPersonaDiscapacidadBD(textFieldBuscar.getText().trim()));
					tableViewEstSalud.refresh();
					//tableViewPerDiscapacidad.getSelectionModel().select(personaDiscapacidadSelec);
					Thread.sleep(29000);
					i++;
				}
				
			}};
			
			Thread hilo=new Thread(task);
			hilo.setDaemon(true);
			hilo.start();
			
		textFieldBuscar.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				tableViewEstSalud.setItems(obtenerPersonaDiscapacidadBD(textFieldBuscar.getText().trim()));
				tableViewEstSalud.refresh();

				//mostrarDatosPersonaDiscapacidad(textFieldBuscar.getText().trim());
			}
		});
		jFXButtonNuevoEstSalud.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
		
				
				
				JFXButton tg = (JFXButton) event.getSource();
				Stage stagePadre = (Stage) tg.getScene().getWindow();
				
				//Stage cargando=Cargando.cargando(stagePadre);
				//cargando.show();
		
				StackPane stackPane = (StackPane) stagePadre.getScene().getRoot().getChildrenUnmodifiable().get(1);
				stackPane.setVisible(true);
				
				String urlFxml = "/registro/personaConDiscapacidad/nuevoRegistroPD/establecimientoSalud/EstablecimientoSaludInterfaz.fxml";
				String css = "/registro/personaConDiscapacidad/nuevoRegistroPD/establecimientoSalud/establecimientoSaludInterfaz.css";
				try {
					//mostrarInterfazModalShowAndWait(urlFxml, css);
					
					FXMLLoader fXMLLoader=new FXMLLoader();
					fXMLLoader.setLocation(getClass().getResource(urlFxml));
					fXMLLoader.load();
					//registro.personaConDiscapacidad.nuevoRegistroPD.establecimientoSalud.EstablecimientoSaludControler estSaludAux=fXMLLoader.getController();
					//estSaludAux.setAccion(registro.personaConDiscapacidad.nuevoRegistroPD.establecimientoSalud.EstablecimientoSaludControler.ACTUALIZAR);
					//estSaludAux.setIdActualizar(idActualizar);
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

				

				
				stackPane.setVisible(false);

							}
		});

	}

	
	public ObservableList<EstablecimientoSalud> obtenerPersonaDiscapacidadBD(String buscar){
		ObservableList<EstablecimientoSalud> arrayPersonaDiscapacidad=FXCollections.observableArrayList();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
        	conn = DriverManager.getConnection(Conexion.SERVIDOR, Conexion.USER, Conexion.PASS);
			pst = conn.prepareStatement("SELECT ID, NOMBRE, (SELECT CONCAT(NOMBRES, ', ', APELLIDOS) FROM DIRECTOR WHERE ID=ID_DIRECTOR) AS DIRECTOR,ID_DIRECTOR , OBSERVACION FROM ESTABLECIMIENTO_SALUD WHERE (SELECT CONCAT(NOMBRES, ', ', APELLIDOS) "
					+ "					 FROM DIRECTOR WHERE ID=ID_DIRECTOR) LIKE ? OR NOMBRE LIKE ?;");
			pst.setString(1, "%" + buscar + "%");
			pst.setString(2, "%" + buscar + "%");
		
			rs = pst.executeQuery();

			int contador=1;
			while (rs.next()) {
				EstablecimientoSalud auxiliar=new EstablecimientoSalud(rs.getInt("ID"), contador++, rs.getString("NOMBRE"), rs.getInt("ID_DIRECTOR"), rs.getString("DIRECTOR"));
				/*
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
				});*/
				
				auxiliar.getOpciones().getjFXButtonEditar().setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent arg0) {
						JFXButton tg = (JFXButton) arg0.getSource();
						Stage stagePadre = (Stage) tg.getScene().getWindow();
						
						//Stage cargando=Cargando.cargando(stagePadre);
						//cargando.show();
				
						StackPane stackPane = (StackPane) stagePadre.getScene().getRoot().getChildrenUnmodifiable().get(1);
						stackPane.setVisible(true);
						
						String urlFxml = "/registro/personaConDiscapacidad/nuevoRegistroPD/establecimientoSalud/EstablecimientoSaludInterfaz.fxml";
						String css = "/registro/personaConDiscapacidad/nuevoRegistroPD/establecimientoSalud/establecimientoSaludInterfaz.css";
						try {
							//mostrarInterfazModalShowAndWait(urlFxml, css);
							
							FXMLLoader fXMLLoader=new FXMLLoader();
							fXMLLoader.setLocation(getClass().getResource(urlFxml));
							fXMLLoader.load();
							registro.personaConDiscapacidad.nuevoRegistroPD.establecimientoSalud.EstablecimientoSaludControler estSaludAux=fXMLLoader.getController();
							estSaludAux.setAccion(registro.personaConDiscapacidad.nuevoRegistroPD.establecimientoSalud.EstablecimientoSaludControler.ACTUALIZAR);
							estSaludAux.setIdActualizar(auxiliar.getIdEstSalud());
							estSaludAux.mostrarDatosEstablecimientoSalud(auxiliar.getIdEstSalud());
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

						

						
						stackPane.setVisible(false);

					}
				});
				
				auxiliar.getOpciones().getjFXButtonEliminar().setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent arg0) {
						
						JFXButton tg = (JFXButton) arg0.getSource();
						Stage stagePadre = (Stage) tg.getScene().getWindow();
						
						//Stage cargando=Cargando.cargando(stagePadre);
						//cargando.show();
				
						StackPane stackPane = (StackPane) stagePadre.getScene().getRoot().getChildrenUnmodifiable().get(1);
						stackPane.setVisible(true);

						Alert alert = new Alert(AlertType.CONFIRMATION, "¿Está seguro que desea eliminar este Establecimiento de Salud :( ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
						alert.showAndWait();

						if (alert.getResult() == ButtonType.YES) {
							eliaminarEstSalud(auxiliar.getIdEstSalud());
						}
						stackPane.setVisible(false);

					}
				});
				auxiliar.getOpciones().getChildren().remove(auxiliar.getOpciones().getjFXButtonVer());
				auxiliar.getOpciones().getChildren().remove(auxiliar.getOpciones().getjFXButtonExportar());
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
	
	public void iniciarValoresTableViewEstablecimientoSalud() {
		
		
		//tableColumnEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
		
		tableColumnNro.setCellValueFactory(new PropertyValueFactory<EstablecimientoSalud,Number>("nro"));
		tableColumnNombres.setCellValueFactory(new PropertyValueFactory<EstablecimientoSalud,String>("nombreEstSalud"));
		tableColumnDirector.setCellValueFactory(new PropertyValueFactory<EstablecimientoSalud,String>("nombreDirector"));

		tableColumnObservacion.setCellValueFactory(new PropertyValueFactory<EstablecimientoSalud,String>("obervacion"));
		tableColumnOpciones.setCellValueFactory(new PropertyValueFactory<EstablecimientoSalud,String>("opciones") );
		
		//nuevas modificaciones
		
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
	
	public void eliaminarEstSalud(int idEstSalud){
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("DELETE FROM ESTABLECIMIENTO_SALUD WHERE ID=?;");
			pst.setInt(1, idEstSalud );
			pst.executeUpdate();
			
		}catch(Exception e){
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error al eliminar");
			alert.setHeaderText(null);
			alert.setContentText("Ooops, Ocurrio un error al intentar eliminar!..."+e.getMessage());
			alert.showAndWait();
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
	
	}

}