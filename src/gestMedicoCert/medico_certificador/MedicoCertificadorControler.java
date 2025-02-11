package gestMedicoCert.medico_certificador;

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


public class MedicoCertificadorControler extends Funciones implements Initializable {
	
		@FXML
	    private TextField textFieldBuscar;

	    @FXML
	    private Button ButtonBuscar;

	    @FXML
	    private JFXButton jFXButtonNuevoMedicoCert;
	    
	    @FXML
	    private TableView<MedicoCertificador> tableViewMedicoCert;

	    @FXML
	    private TableColumn<MedicoCertificador, Number> tableColumnNro;

	    @FXML
	    private TableColumn<MedicoCertificador, String> tableColumnDNI;

	    @FXML
	    private TableColumn<MedicoCertificador, String> tableColumnCMP;

	    @FXML
	    private TableColumn<MedicoCertificador, String> tableColumnRNE;

	    @FXML
	    private TableColumn<MedicoCertificador, String> tableColumnNombresApellidos;

	    @FXML
	    private TableColumn<MedicoCertificador, String> tableColumnEspecialidad;

	    @FXML
	    private TableColumn<MedicoCertificador, String> tableColumnEstSalud;

	    @FXML
	    private TableColumn<MedicoCertificador, String> tableColumnOpciones;



	@Override
	public void initialize(URL location, ResourceBundle resources) {

		iniciarValoresTableViewMedicoCert();
		//mostrarDatosPersonaDiscapacidad();
		Task<Void> task=new Task<Void>(){

			@Override
			protected Void call() throws Exception {
				int i=0;
				while(true){
					tableViewMedicoCert.setItems(obtenerMedicoCertBD(textFieldBuscar.getText().trim()));
					tableViewMedicoCert.refresh();
					//tableViewPerDiscapacidad.getSelectionModel().select(personaDiscapacidadSelec);
					Thread.sleep(27000);
					i++;
				}
				
			}};
			
			Thread hilo=new Thread(task);
			hilo.setDaemon(true);
			hilo.start();
			
		textFieldBuscar.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				tableViewMedicoCert.setItems(obtenerMedicoCertBD(textFieldBuscar.getText().trim()));
				tableViewMedicoCert.refresh();

				//mostrarDatosPersonaDiscapacidad(textFieldBuscar.getText().trim());
			}
		});
		jFXButtonNuevoMedicoCert.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
		
				
				
				JFXButton tg = (JFXButton) event.getSource();
				Stage stagePadre = (Stage) tg.getScene().getWindow();
				
				//Stage cargando=Cargando.cargando(stagePadre);
				//cargando.show();
		
				StackPane stackPane = (StackPane) stagePadre.getScene().getRoot().getChildrenUnmodifiable().get(1);
				stackPane.setVisible(true);
				
				String urlFxml = "/registro/personaConDiscapacidad/nuevoRegistroPD/medicoCertificador/MedicoCertificadorInterfaz.fxml";
				String css = "/registro/personaConDiscapacidad/nuevoRegistroPD/medicoCertificador/medicoCertificadorInterfaz.css";
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

	
	public ObservableList<MedicoCertificador> obtenerMedicoCertBD(String buscar){
		ObservableList<MedicoCertificador> arrayPersonaDiscapacidad=FXCollections.observableArrayList();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
        	conn = DriverManager.getConnection(Conexion.SERVIDOR, Conexion.USER, Conexion.PASS);
			pst = conn.prepareStatement("SELECT ID, DNI, CMP, RNE, CONCAT(NOMBRES,', ',APELLIDOS ) AS NOM_APELL, ESPECIALIDAD, OBSERVACION,  (SELECT NOMBRE FROM ESTABLECIMIENTO_SALUD WHERE ID=ID_ESTABLECIMIENTO_SALUD) AS EST_SALUD FROM MEDICO "
					+"WHERE DNI LIKE ? OR CMP LIKE ? OR RNE LIKE ? OR CONCAT(NOMBRES,', ',APELLIDOS ) LIKE ?  ;");
			pst.setString(1, "%" + buscar + "%");
			pst.setString(2, "%" + buscar + "%");
			pst.setString(3, "%" + buscar + "%");

			pst.setString(4, "%" + buscar + "%");

		
			rs = pst.executeQuery();

			int contador=1;
			while (rs.next()) {
				MedicoCertificador auxiliar=new MedicoCertificador(contador++, rs.getInt("ID"), rs.getString("DNI"), rs.getString("CMP"), rs.getString("RNE"), rs.getString("NOM_APELL"), rs.getString("ESPECIALIDAD"), rs.getString("EST_SALUD"));
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
						
						String urlFxml = "/registro/personaConDiscapacidad/nuevoRegistroPD/medicoCertificador/MedicoCertificadorInterfaz.fxml";
						String css = "/registro/personaConDiscapacidad/nuevoRegistroPD/medicoCertificador/medicoCertificadorInterfaz.css";
						try {
							//mostrarInterfazModalShowAndWait(urlFxml, css);
							
							FXMLLoader fXMLLoader=new FXMLLoader();
							fXMLLoader.setLocation(getClass().getResource(urlFxml));
							fXMLLoader.load();
							registro.personaConDiscapacidad.nuevoRegistroPD.medicoCertificador.MedicoCertificadorControler medicoCertAux=fXMLLoader.getController();
							medicoCertAux.setAccion(registro.personaConDiscapacidad.nuevoRegistroPD.medicoCertificador.MedicoCertificadorControler.ACTUALIZAR);
							medicoCertAux.setIdActualizar(auxiliar.getId());
							medicoCertAux.mostrarMedicoCertificador(auxiliar.getId());
						
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

						Alert alert = new Alert(AlertType.CONFIRMATION, "�Est� seguro que desea eliminar este Establecimiento de Salud :( ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
						alert.showAndWait();

						if (alert.getResult() == ButtonType.YES) {
							eliaminarMedicoCert(auxiliar.getId());
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
	
	public void iniciarValoresTableViewMedicoCert() {
		
		
		//tableColumnEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
		
		tableColumnNro.setCellValueFactory(new PropertyValueFactory<MedicoCertificador,Number>("nro"));
		tableColumnDNI.setCellValueFactory(new PropertyValueFactory<MedicoCertificador,String>("dni"));
		tableColumnCMP.setCellValueFactory(new PropertyValueFactory<MedicoCertificador,String>("cmp"));
		tableColumnRNE.setCellValueFactory(new PropertyValueFactory<MedicoCertificador,String>("rne"));
		tableColumnNombresApellidos.setCellValueFactory(new PropertyValueFactory<MedicoCertificador,String>("nombres_apellido"));
		tableColumnEspecialidad.setCellValueFactory(new PropertyValueFactory<MedicoCertificador,String>("especialidad"));
		tableColumnEstSalud.setCellValueFactory(new PropertyValueFactory<MedicoCertificador,String>("est_salud"));
		tableColumnOpciones.setCellValueFactory(new PropertyValueFactory<MedicoCertificador,String>("opciones") );
		
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
	
	public void eliaminarMedicoCert(int idEstSalud){
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("DELETE FROM MEDICO WHERE ID=?;");
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