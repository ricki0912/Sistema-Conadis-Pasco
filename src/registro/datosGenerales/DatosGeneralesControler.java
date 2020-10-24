package registro.datosGenerales;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;


import com.jfoenix.controls.JFXButton;

import funciones.AbstractJasperReports;
import funciones.Conexion;
import funciones.Funciones;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import registro.personaConDiscapacidad.PersonaDiscapacidad;
import registro.personaConDiscapacidad.modificarRegistroPD.ModificarRegistroPDControler_v1;
import registro.personaConDiscapacidad.nuevoRegistroPD.Procedencia;
import registro.personaConDiscapacidad.nuevoRegistroPD.ProgramaSocial;
import registro.personaConDiscapacidad.nuevoRegistroPD.TipoTrabajo;
import registro.personaConDiscapacidad.nuevoRegistroPD.TipoVia;


public class DatosGeneralesControler extends Funciones implements Initializable {

	
	

    @FXML
    private BorderPane borderPaneDatosPersonal;

    @FXML
    private JFXButton jFXButtonAgregarTipoProcedencia;

    @FXML
    private TableView<Procedencia> tableViewTipoProcedencia;

    @FXML
    private TableColumn<Procedencia, String> tableViewTipoProcedencia_Nro;

    @FXML
    private TableColumn<Procedencia, String> tableViewTipoProcedencia_Nombre;

    @FXML
    private TableColumn<Procedencia, String> tableViewTipoProcedencia_Cod_Estadistico;

    @FXML
    private TableColumn<Procedencia, String> tableViewTipoProcedencia_Descripcion;
    
    @FXML
    private TableColumn<Procedencia, String> tableViewTipoProcedencia_Opciones;
    
    @FXML
    private BorderPane borderPaneDatosPersonal1;

    @FXML
    private JFXButton jFXButtonAgregarTipoVia;

    @FXML
    private TableView<TipoVia> tableViewTipoVia;

    @FXML
    private TableColumn<TipoVia, String> tableViewTipoVia_Nro;

    @FXML
    private TableColumn<TipoVia, String> tableViewTipoVia_Nombre;

    @FXML
    private TableColumn<TipoVia, String> tableViewTipoVia_Cod_Estadistico;

    @FXML
    private TableColumn<TipoVia, String> tableViewTipoVia_Descripcion;
    @FXML
    private TableColumn<TipoVia, String>  tableViewTipoVia_Opciones;

    @FXML
    private BorderPane borderPaneDatosPersonal11;

    @FXML
    private JFXButton jFXButtonAgregarTipoTrabajo;

    @FXML
    private TableView<TipoTrabajo> tableViewTipoTrabajo;

    @FXML
    private TableColumn<TipoTrabajo, String> tableViewTipoTrabajo_Nro;

    @FXML
    private TableColumn<TipoTrabajo, String> tableViewTipoTrabajo_Nombre;

    @FXML
    private TableColumn<TipoTrabajo, String> tableViewTipoTrabajo_Cod_Estadistico;

    @FXML
    private TableColumn<TipoTrabajo, String> tableViewTipoTrabajo_Descripcion;

    @FXML private TableColumn<TipoTrabajo, String> tableViewTipoTrabajo_Opciones;
    
    @FXML
    private BorderPane borderPaneDatosPersonal12;

    @FXML
    private JFXButton jFXButtonAgregarProgramaSocial;

    @FXML
    private TableView<ProgramaSocial> tableViewProgramaSocial;

    @FXML
    private TableColumn<ProgramaSocial, String> tableViewProgramaSocial_Nro;

    @FXML
    private TableColumn<ProgramaSocial, String> tableViewProgramaSocial_Nombre;

    @FXML
    private TableColumn<ProgramaSocial, String> tableViewProgramaSocial_Cod_Estadistico;

    @FXML
    private TableColumn<ProgramaSocial, String> tableViewProgramaSocial_Descripcion;
    
    @FXML private TableColumn<ProgramaSocial,String> tableViewProgramaSocial_opciones;
    
    
	private ObservableList<TipoVia> arrayTableViewTipoVia = FXCollections.observableArrayList();
	private ObservableList<Procedencia> arrayTableViewProcedencia=FXCollections.observableArrayList();
	private ObservableList<TipoTrabajo> arrayTableViewTipoTrabajo=FXCollections.observableArrayList();
	private ObservableList<ProgramaSocial> arrayTableViewProgramaSocial=FXCollections.observableArrayList();

	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		incializarTableProgramaSocia();
		mostrarDatosProgramaSocial();
		
		
		incializarTableTipoTrabajo();
		mostrarDatosTipoTrabajo();
		
		incializarTableTipoProcedencia();
		mostrarDatosTipoProcedencia();
		
		incializarTableTipoVia();
		mostrarDatosTipoVia();
		
	}

	//programa social 
	public void incializarTableProgramaSocia(){
		tableViewProgramaSocial.setItems(arrayTableViewProgramaSocial);
		tableViewProgramaSocial_Nro.setCellValueFactory(new PropertyValueFactory<>("nro"));
		tableViewProgramaSocial_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		tableViewProgramaSocial_Cod_Estadistico.setCellValueFactory(new PropertyValueFactory<>("codEstadistico"));
		tableViewProgramaSocial_Descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		tableViewProgramaSocial_opciones.setCellValueFactory(new PropertyValueFactory<>("opciones"));
	
	}
	public void mostrarDatosProgramaSocial() {
		mostrarDatosProgramaSocial("");
	}

	public void mostrarDatosProgramaSocial(String buscar) {
		// vamos limpiar el contenedor de los botones
		tableViewProgramaSocial.getItems().clear();
		// hacer la conexcioon con la base de datos y obtener alas busquedas
		// encontradas
		 Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement("SELECT ID, NOMBRE, CODIGO_ESTADISTICO, OBSERVACION FROM programa_social WHERE NOMBRE LIKE ?;");
			pst.setString(1, "%" + buscar + "%");
			
			rs = pst.executeQuery();

			System.out.println("estoy aqui 1");
			int contador=1;
			while (rs.next()) {
				ProgramaSocial auxiliar=new ProgramaSocial(rs.getInt("ID"), contador, rs.getString("NOMBRE"), rs.getInt("CODIGO_ESTADISTICO"), rs.getString("OBSERVACION"));
				
				tableViewProgramaSocial.getItems().add(auxiliar);
						contador=contador+1;

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
	}
	

	//tipo de trabajo 

	public void incializarTableTipoTrabajo(){
		tableViewTipoTrabajo.setItems(arrayTableViewTipoTrabajo);
		tableViewTipoTrabajo_Nro.setCellValueFactory(new PropertyValueFactory<>("nro"));
		tableViewTipoTrabajo_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		tableViewTipoTrabajo_Cod_Estadistico.setCellValueFactory(new PropertyValueFactory<>("codEstadistico"));
		tableViewTipoTrabajo_Descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		tableViewTipoTrabajo_Opciones.setCellValueFactory(new PropertyValueFactory<>("opciones"));
	
	}
	public void mostrarDatosTipoTrabajo() {
		mostrarDatosTipoTrabajo("");
	}

	public void mostrarDatosTipoTrabajo(String buscar) {
		// vamos limpiar el contenedor de los botones
		tableViewTipoTrabajo.getItems().clear();
		// hacer la conexcioon con la base de datos y obtener alas busquedas
		// encontradas
		 Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement("SELECT ID, NOMBRE, CODIGO_ESTADISTICO, OBSERVACION FROM TIPO_TRABAJO WHERE NOMBRE LIKE ?;");
			pst.setString(1, "%" + buscar + "%");
			
			rs = pst.executeQuery();

			System.out.println("estoy aqui 1");
			int contador=1;
			while (rs.next()) {
				TipoTrabajo auxiliar=new TipoTrabajo(rs.getInt("ID"), contador, rs.getString("NOMBRE"), rs.getInt("CODIGO_ESTADISTICO"), rs.getString("OBSERVACION"));
				
				tableViewTipoTrabajo.getItems().add(auxiliar);
						contador=contador+1;

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
	}

	//procedencia


	public void incializarTableTipoProcedencia(){
		tableViewTipoProcedencia.setItems(arrayTableViewProcedencia);
		tableViewTipoProcedencia_Nro.setCellValueFactory(new PropertyValueFactory<>("nro"));
		tableViewTipoProcedencia_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		tableViewTipoProcedencia_Cod_Estadistico.setCellValueFactory(new PropertyValueFactory<>("codEstadistico"));
		tableViewTipoProcedencia_Descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		tableViewTipoProcedencia_Opciones.setCellValueFactory(new PropertyValueFactory<>("opciones"));
	
	}
	public void mostrarDatosTipoProcedencia() {
		mostrarDatosTipoProcedencia("");
	}

	public void mostrarDatosTipoProcedencia(String buscar) {
		// vamos limpiar el contenedor de los botones
		tableViewTipoProcedencia.getItems().clear();
		// hacer la conexcioon con la base de datos y obtener alas busquedas
		// encontradas
		 Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement("SELECT ID, NOMBRE, CODIGO_ESTADISTICO, OBSERVACION FROM tipo_procedencia WHERE NOMBRE LIKE ?;");
			pst.setString(1, "%" + buscar + "%");
			
			rs = pst.executeQuery();

			System.out.println("estoy aqui 1");
			int contador=1;
			while (rs.next()) {
				Procedencia auxiliar=new Procedencia(rs.getInt("ID"), contador, rs.getString("NOMBRE"), rs.getInt("CODIGO_ESTADISTICO"), rs.getString("OBSERVACION"));
				
				tableViewTipoProcedencia.getItems().add(auxiliar);
						contador=contador+1;

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
	}

	
	//tipo via
	//procedencia


		public void incializarTableTipoVia(){
			tableViewTipoVia.setItems(arrayTableViewTipoVia);
			tableViewTipoVia_Nro.setCellValueFactory(new PropertyValueFactory<>("nro"));
			tableViewTipoVia_Nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			tableViewTipoVia_Cod_Estadistico.setCellValueFactory(new PropertyValueFactory<>("codEstadistico"));
			tableViewTipoVia_Descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
			tableViewTipoVia_Opciones.setCellValueFactory(new PropertyValueFactory<>("opciones"));
		
		}
		public void mostrarDatosTipoVia() {
			mostrarDatosTipoVia("");
		}

		public void mostrarDatosTipoVia(String buscar) {
			// vamos limpiar el contenedor de los botones
			tableViewTipoVia.getItems().clear();
			// hacer la conexcioon con la base de datos y obtener alas busquedas
			// encontradas
			 Connection conn = null;
			PreparedStatement pst = null;
			ResultSet rs = null;
			try {
				Conexion.conectarDB();
				conn = Conexion.getConexion();
				pst = conn.prepareStatement("SELECT ID, NOMBRE, CODIGO_ESTADISTICO, OBSERVACION FROM tipo_via WHERE NOMBRE LIKE ?;");
				pst.setString(1, "%" + buscar + "%");
				
				rs = pst.executeQuery();

				System.out.println("estoy aqui 1");
				int contador=1;
				while (rs.next()) {
					TipoVia auxiliar=new TipoVia(rs.getInt("ID"), contador, rs.getString("NOMBRE"), rs.getInt("CODIGO_ESTADISTICO"), rs.getString("OBSERVACION"));
					
					tableViewTipoVia.getItems().add(auxiliar);
							contador=contador+1;

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
		}

		
	
}