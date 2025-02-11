package registro.personaConDiscapacidad.nuevoRegistroPD;


import java.io.IOException;
import java.net.URL;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.Format;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.ResourceBundle;

import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.IndexedCheckModel;
import org.controlsfx.control.textfield.TextFields;
import org.ietf.jgss.ChannelBinding;

import com.jfoenix.controls.JFXButton;
import funciones.Conexion;
import funciones.Funciones;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import registro.personaConDiscapacidad.PersonaDiscapacidad;
import registro.personaConDiscapacidad.modificarRegistroPD.ModificarRegistroPDControler_v1;
import registro.personaConDiscapacidad.nuevoRegistroPD.directorEstablecimiento.DirectorEstablecimientoControler;
import registro.personaConDiscapacidad.nuevoRegistroPD.establecimientoSalud.EstablecimientoSaludControler;
import registro.personaConDiscapacidad.nuevoRegistroPD.medicoCertificador.MedicoCertificadorControler;
import registro.personaConDiscapacidad.nuevoRegistroPD.programaSocial.ProgramaSocialControler;
import sesion.Sesion;
import venEmergentes.entradas.uno.EntradaUnoControler;

public class NuevoRegistroPDControler extends Funciones implements Initializable {
	
	//nuevas modificacionees 2018
	@FXML private CheckComboBox<NecesitaAprender> checkComboBoxNecesitaAprender;
	@FXML private CheckComboBox<TieneConocimientoEn> checkComboBoxTieneConocimientoEn;
	@FXML private JFXButton jFXButtonAnadirTieneConocimientoEn;
	@FXML private JFXButton jFXButtonAnadirTieneNecesitaAprender;
	@FXML private ComboBox<ClasificacionSisfoh> comboBoxClasificacionSisfoh;
	
	private ObservableList<NecesitaAprender> arrayNecesitaAprender=FXCollections.observableArrayList();
	private ObservableList<TieneConocimientoEn> arrayTieneConocimiento=FXCollections.observableArrayList();
	private ObservableList<ClasificacionSisfoh> arrayClasificacionSisfoh=FXCollections.observableArrayList();
	//
	
	
	@FXML private JFXButton jFXButtonAnadirMedicoCEertificador;
	@FXML private JFXButton jFXButtonAnadirDirectorEstablecimiento;
	@FXML private JFXButton  jFXButtonAnadirEstablecimientoSalud;
	@FXML private JFXButton  jFXButtonAnadirProgramaSocial;
	
	//ultimos modificaciones xD
	@FXML private JFXButton jFXButtonAyudaBiometrica;
	@FXML private JFXButton jFXButtonLimitacionPermanente;
	@FXML private JFXButton jFXButtonTipoDiscapacidad;
	@FXML private JFXButton jFXButtonTipoSeguroMedico;
	@FXML private JFXButton jFXButtonRequerimientoApoyo;
	@FXML private JFXButton jFXButtonProcedencia;
	@FXML private JFXButton jFXButtonTipoVia;
	@FXML private DatePicker datePickerRegistroConadisNacional;
	
	private ObservableList<CheckBoxTieneCertificadoDiscapacidad> arrayCheckBoxCertificadoDiscapacidad=FXCollections.observableArrayList();
	private ObservableList<CheckBoxTieneInscripcionConadis> arrayCheckBoxTieneIncripcionConadis=FXCollections.observableArrayList();
	private ObservableList<CheckBoxSexo> arrayCheckBoxSexo=FXCollections.observableArrayList();
	
	@FXML private TableView<DiagnosticoEnfermedades_CIE> TableViewCie;
	@FXML private TableColumn<DiagnosticoEnfermedades_CIE,String> TableColumnNroCie;
	@FXML private TableColumn<DiagnosticoEnfermedades_CIE,String> TableCodigoCie;
	@FXML private TableColumn< DiagnosticoEnfermedades_CIE, String> TableColumnNombreCie;
	private ObservableList<DiagnosticoEnfermedades_CIE> arrayTableViewDiagnosticoEnfermedadesCIE = FXCollections.observableArrayList();
	
	//Set<String> posiblesPalabrasCie=HashSet<>();
	
	private Tooltip tooltipAdvertencia;
	@FXML private TextField textFieldBuscarCie;
	@FXML private TextField textFieldOcupacionDatosPersonales;
	@FXML private TextField textFiedUltimoAnio;
	@FXML private TextField textFieldEmail;
	@FXML private TextField textFieldApellNumContacto;
	@FXML private TextArea textAreaObervacionDatosPersonales;
	@FXML private TextField textFieldNroCertificado;
	public TextField getTextFieldDocId() {
		return textFieldDocId;
	}


	public void setTextFieldDocId(TextField textFieldDocId) {
		this.textFieldDocId = textFieldDocId;
	}


	public RadioButton getRadioButtonDNI() {
		return RadioButtonDNI;
	}


	public void setRadioButtonDNI(RadioButton radioButtonDNI) {
		RadioButtonDNI = radioButtonDNI;
	}

	@FXML private DatePicker datePickerFechaEmisionCertificado;
	@FXML private TextField textFieldRestrincionParticipacion;
	@FXML private TextField textFieldRestriccionDiferido;
	@FXML private CheckComboBox checkComboBoxTipoSeguroMedicoCertificacion;
	@FXML private TextArea textAreaMotivoInscripcionConadis;
	@FXML private TextField textFieldDiagnosticoMedicoCie;
	@FXML private TextArea textAreaObservacionesCertificacion;
	@FXML private DatePicker datePickerRegistroConadis;
	@FXML private TextField textFieldNumeroCarne;
	@FXML private TextField textFieldNumeroResolucion;
	@FXML private DatePicker datePickerFechaEmisionResolcuion;
	@FXML private TextField tFieldSeResuelve;
	@FXML private Label labelInformacion;
	@FXML private HBox hBoxTieneInscripcionConadis;
	@FXML private HBox hBoxTieneCertificadoDiscapacidad;
	@FXML private HBox hBoxSexo;
	@FXML private ScrollPane scrollPaneContenedor;
	
	@FXML private CheckComboBox<TipoDiscapacidad> checkComboBoxTipoDiscapacidad;
	@FXML private CheckComboBox<TipoAyudaBiomecanica> checkComboBoxTipoAyudaBiometrica;
	@FXML private CheckComboBox<TipoSeguroMedico> checkComboBoxTipoSeguroMedico;
 	@FXML private CheckComboBox<LimitacionPermanente> chekcComboBoxLimitacionPermanente;
 	//@FXML private CheckComboBox<OrigenLimitacion> checkComboBoxOrigenLimitacion;

	@FXML
    private DatePicker datePickerFechaNacimiento;


    @FXML
    private ComboBox<NivelEducativo> comboBoxNivelEducativo;

    @FXML
    private ComboBox<Distrito> comboBoxDistrito;

    @FXML
    private TextField textFieldApellPaterno;
    
    @FXML private TextField textFieldSeResuelve;

    @FXML
    private AnchorPane anchorPaneModalNuevoRegistroPD;

    @FXML
    private ComboBox<Departamento> comboBoxRegion;

    @FXML
    private TextField textFieldLoteDatosDomicilio;

    @FXML
    private TextField textFieldNroDatosDomicilio;

    @FXML
    private CheckBox checkBoxFemenino;

    @FXML
    private TextField textFieldApellMaterno;

    @FXML
    private TextField textFieldDpto_IntDatosDomicilio;

    @FXML
    private RadioButton RadioButtonCUI;
    
    @FXML private RadioButton RadioButtonS_D;

    @FXML
    private Button buttonCancelar;

    @FXML
    private ComboBox<Provincia> comboBoxProvincia;

    @FXML
    private BorderPane borderPaneTituloNuevoRegistroPD;

    @FXML
    private TextField textFieldMzDatosDomicilio;

    @FXML
    private TextField textFieldEdificioDatosDomicilio;

    @FXML
    private TextField textFieldDocId;

    @FXML
    private ComboBox<TipoVia> comboBoxTipoVia;

    @FXML
    private RadioButton RadioButtonDNI;

    @FXML
    private TextField textFieldEtapaDatosDomicilio;

    @FXML
    private Label labelTituloNuevoRegistroPD;

    @FXML
    private Button buttonValidar;

    @FXML
    private TextField textFieldApellNombres;

    @FXML
    private ToggleGroup toggleGroupDocId;

    @FXML
    private CheckBox checkBoxMasculino;

    @FXML
    private TextField textFieldNombreVia;

    @FXML
    private JFXButton jFXButtonClose;

    @FXML
    private ComboBox<TipoTrabajo> comboBoxTipoTrabajo;
    @FXML
    private ComboBox<OrigenLimitacion> comboBoxOrigenLimitacion;
    @FXML private MenuButton menuButtonLimitacionPermanente;
    
    @FXML
    private TextField textFieldUrb_AAHH_CP_PJ_OtroDatosDomicilio;
    


    
    @FXML private ComboBox<NivelGravedad> comboBoxNivelGravedad;
    @FXML private TextField textFieldDiagnosticoMedico;
  


	@FXML private ComboBox<EstadoCivil> comboBoxEstadoCivil;
	
	@FXML private CheckComboBox<ProgramaSocial> checkComboBoxProgramaSocial;
	@FXML private ComboBox<Procedencia> comboBoxProcedencia;
	@FXML private ComboBox<TipoTramite> comboBoxTipoTramite;
	
	
	@FXML private ComboBox<EstablecimientoSalud> comboBoxEstablecimientoSalud;
	@FXML private ComboBox<DirectorEstablecimiento> comboBoxDirectorEstablecimiento;
	@FXML private ComboBox<MedicoCertificador> comboBoxMedicoCertificador;

	@FXML private CheckComboBox<RequerimientoApoyo> chekcComboBoxRequerimientoApoyo;
	private ObservableList<RequerimientoApoyo> arrayRequerimientoApoyo=FXCollections.observableArrayList();
	private ObservableList<DirectorEstablecimiento> arrayDirectorEstablecimiento=FXCollections.observableArrayList();
	private ObservableList<MedicoCertificador> arrayMedicoCertificador=FXCollections.observableArrayList();
	private ObservableList<EstablecimientoSalud> arrayEstablecimientoSalud=FXCollections.observableArrayList();
    private ObservableList<TipoTramite> arrayTipoTramite=FXCollections.observableArrayList();
	private ObservableList<EstadoCivil> arrayEstadoCivil=FXCollections.observableArrayList();
	private ObservableList<Procedencia> arrayProcedencia=FXCollections.observableArrayList();
    private ObservableList<ProgramaSocial> arrayProgramaSocial=FXCollections.observableArrayList();
    private ObservableList<Departamento> arrayDepartamento=FXCollections.observableArrayList();
    private ObservableList<Provincia> arrayProvincias=FXCollections.observableArrayList();
    private ObservableList<Distrito> arrayDistritos=FXCollections.observableArrayList();
    private ObservableList<NivelEducativo> arrayNivelEducativo=FXCollections.observableArrayList();
    private ObservableList<TipoTrabajo> arrayTipoTrabajo=FXCollections.observableArrayList();
    private ObservableList<TipoVia> arrayTipoVia=FXCollections.observableArrayList();
    private ObservableList<NivelGravedad> arrayNivelGravedad=FXCollections.observableArrayList();

    
    private ObservableList<LimitacionPermanente> arrayLimitacionPermanente=FXCollections.observableArrayList(); 
    private ObservableList<TipoDiscapacidad> arrayTipoDiscapacidad=FXCollections.observableArrayList(); 
    private ObservableList<TipoAyudaBiomecanica> arrayTipoAyudaBiometrica=FXCollections.observableArrayList(); 
    private ObservableList<TipoSeguroMedico> arrayTipoSeguroMedico=FXCollections.observableArrayList(); 
    private ObservableList<OrigenLimitacion> arrayOrigenLimitacion=FXCollections.observableArrayList(); 


    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		mostrarCoboBoxClasificacionSisfoh();
		
		iniciarValoresTableViewDiagnosticoEnfermedadesCIE();
		mostrarDatosDiagnosticoEnfermedadesCIE();
		
		mostrarDepartamentos();
		mostrarNivelEducativo();
		mostrarTipoTrabajo();
		mostrarTipoVia();
		mostrarProcedencia();
		mostrarComboBoxOrigenLimitacion();
		mostrarEstadoCivil();
		mostrarDirectorEstablecimiento();
		mostrarEstablecimientoSalud();
		mostrarMedicoCertificador();
		mostrarTipoTramite();
		
		mostrarNivelGravedad();
		mostrarCheckBoxTieneCertificadoDiscapacidad();
		mostrarCheckBoxSexo();
		
		mostrarCheckComboBoxRequerimientoApoyo();
		restricciones();
		
		mostrarCheckComboBoxProgramaSocial();
		mostrarCheckComboBoxTipoDiscapacidad();
		mostrarCheckComboBoxTipoAyudaBiometrica();
		mostrarCheckComboBoxTipoSeguroMedico();
		mostrarCheckComboBoxLimitacionPermanente();
		mostrarCheckComboBoxNecesitaAprender();
		mostrarCheckComboBoxTieneConocimientoEn();
		
		
		mostrarCheckBoxTieneInscripcionConadis();
		
		agregarRestriccionCamposNoVacios();
		
		TableViewCie.setOnMouseReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				String diagMediSelec=TableViewCie.getSelectionModel().getSelectedItem().getCodCie();
				String[] vectorCie=textFieldDiagnosticoMedicoCie.getText().trim().split(",");
				boolean aux=true;
				for(int i=0;i<vectorCie.length;i++){
					if(vectorCie[i].equals(diagMediSelec)){
						aux=false;
					}
				}
				if(textFieldDiagnosticoMedicoCie.getText().trim().isEmpty()){
					textFieldDiagnosticoMedicoCie.setText(diagMediSelec);
				}else if(aux){
					textFieldDiagnosticoMedicoCie.setText(textFieldDiagnosticoMedicoCie.getText().trim()+","+diagMediSelec);
				}
				
			
				
				/*				String [] vectoPalabras=textFieldDiagnosticoMedicoCie.getText().split(",");
				textFieldDiagnosticoMedicoCie.setText(vectoPalabras[0]);
			
				for(int i=1;i<=vectoPalabras.length;i++){
					textFieldDiagnosticoMedicoCie.setText(textFieldDiagnosticoMedicoCie.getText().trim()+", "+vectoPalabras[1]);

				}
				*/
			}
		});
		
		jFXButtonAyudaBiometrica.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				EntradaUnoControler entradaUno=new EntradaUnoControler();
				entradaUno.cargarModal(EntradaUnoControler.TIPO_AYUDA_BIOMETRICA,"Tipo de Ayuda Biometrica", "Nuevo Tipo de Ayuda Biometrica");
				mostrarCheckComboBoxTipoAyudaBiometrica();
			}
		});
		
		jFXButtonProcedencia.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				EntradaUnoControler entradaUno=new EntradaUnoControler();
				entradaUno.cargarModal(EntradaUnoControler.PROCEDENCIA,"Procedencia", "Nueva Procedencia");
				mostrarProcedencia();
			}
		});
		
		jFXButtonTipoVia.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				EntradaUnoControler entradaUno=new EntradaUnoControler();
				entradaUno.cargarModal(EntradaUnoControler.TIPO_VIA,"Tipo Via", "Nuevo Tipo de Via");
				mostrarTipoVia();;
			}
		});
		
		jFXButtonTipoDiscapacidad.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				EntradaUnoControler entradaUno=new EntradaUnoControler();
				entradaUno.cargarModal(EntradaUnoControler.TIPO_DISCAPACIDAD,"Tipo de Discapacidad", "Nuevo Tipo de Discapacidad");
				mostrarCheckComboBoxTipoDiscapacidad();
			}
		});
		
		jFXButtonLimitacionPermanente.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				EntradaUnoControler entradaUno=new EntradaUnoControler();
				entradaUno.cargarModal(EntradaUnoControler.LIMITACION_PERMANENTE,"Limitaci�n Permanente", "Nuevo Tipo de Limitaci�n Permanente");
				mostrarCheckComboBoxLimitacionPermanente();
			}
		});
		jFXButtonRequerimientoApoyo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				EntradaUnoControler entradaUno=new EntradaUnoControler();
				entradaUno.cargarModal(EntradaUnoControler.REQUERIMIENTO_APOYO,"Requerimiento de Apoyo", "Nuevo Requerimiento de Apoyo");
				mostrarCheckComboBoxRequerimientoApoyo();
			}
		});
		
		jFXButtonTipoSeguroMedico.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				EntradaUnoControler entradaUno=new EntradaUnoControler();
				entradaUno.cargarModal(EntradaUnoControler.TIPO_SEGURO_MEDICO,"Tipo de Seguro Medico", "Nuevo Tipo de Seguro Medico");
				mostrarCheckComboBoxTipoSeguroMedico();
			}
		});
		
		jFXButtonAnadirProgramaSocial.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				EntradaUnoControler entradaUno=new EntradaUnoControler();
				entradaUno.cargarModal(EntradaUnoControler.PROGRAMA_SOCIAL,"Programa Social", "Nuevo Programa Social");
				mostrarCheckComboBoxProgramaSocial();
				
			}
		});
		
		

		jFXButtonAnadirTieneConocimientoEn.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				EntradaUnoControler entradaUno=new EntradaUnoControler();
				entradaUno.cargarModal(EntradaUnoControler.TIENE_CONOCIMIENTO_EN,"Conocimiento", "Tiene conocimiento en:");
				mostrarCheckComboBoxTieneConocimientoEn();
				
			}
		});
		
		

		jFXButtonAnadirTieneNecesitaAprender.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				EntradaUnoControler entradaUno=new EntradaUnoControler();
				entradaUno.cargarModal(EntradaUnoControler.NECESITA_APRENDER,"Necesita aprender", "Necesita aprender");
				mostrarCheckComboBoxNecesitaAprender();
				
			}
		});
	
		
		jFXButtonClose.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Button button=(Button)event.getSource();
				Stage stage=(Stage)button.getScene().getWindow();
				stage.close();
				
			}
		});
		
		
		buttonCancelar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Button button=(Button)event.getSource();
				Stage stage=(Stage)button.getScene().getWindow();
				stage.close();
				
				
				
			}
		});
	
		
		
		comboBoxRegion.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				//ComboBox<Departamento> comboBoxRegion=(ComboBox<Departamento>)arg0.getSource();
				mostrarProvincias(comboBoxRegion.getSelectionModel().getSelectedItem().getId());
				
				
			}
		});
		
		comboBoxProvincia.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				mostrarDistritos(comboBoxProvincia.getSelectionModel().getSelectedItem().getId());
				
			}
		});
		textFieldBuscarCie.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				mostrarDatosDiagnosticoEnfermedadesCIE(textFieldBuscarCie.getText().trim());
			}
		});
	
		buttonValidar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				//probar();
				if(verifcarCamposBlancos()){
					//insertarDatosPersonaDiscapacidad();
					insertarDatosPersonaDiscapacidad_v1();

				}
				
				
			}
		});
		
		jFXButtonAnadirMedicoCEertificador.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				JFXButton tg = (JFXButton) event.getSource();
				//Stage stage = (Stage) tg.getScene().getWindow();
				//stage.hide();
				String urlFxml = "/registro/personaConDiscapacidad/nuevoRegistroPD/medicoCertificador/MedicoCertificadorInterfaz.fxml";
				String css = "/registro/personaConDiscapacidad/nuevoRegistroPD/medicoCertificador/medicoCertificadorInterfaz.css";
				
			
				
				try {
					System.out.println("Hola estoy dento de, metodo de modal :D");
					FXMLLoader fXMLLoader=new FXMLLoader();
					fXMLLoader.setLocation(getClass().getResource(urlFxml));
					fXMLLoader.load();
					MedicoCertificadorControler estab=fXMLLoader.getController();
					
					Parent parent= fXMLLoader.getRoot();
					Scene scene=new Scene(parent);
					scene.setFill(new Color(0,0,0,0));
					scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
					Stage stage1=new Stage();
					stage1.setScene(scene);
					stage1.initModality(Modality.APPLICATION_MODAL);
					stage1.initStyle(StageStyle.TRANSPARENT);
					stage1.showAndWait();;
					MedicoCertificador medico =comboBoxMedicoCertificador.getSelectionModel().getSelectedItem();
					mostrarMedicoCertificador();
					if(estab.getUltimoIDMedico()!=-1){
						comboBoxEstablecimientoSalud.getSelectionModel().select(new EstablecimientoSalud(estab.getUltimoIDMedico(), null));
					}else{
						comboBoxMedicoCertificador.getSelectionModel().select(medico);
					}
					//actualizar datos xD
					DirectorEstablecimiento director=comboBoxDirectorEstablecimiento.getSelectionModel().getSelectedItem();
					mostrarDirectorEstablecimiento();
					comboBoxDirectorEstablecimiento.getSelectionModel().select(director);
					
					//actualizar datos
					EstablecimientoSalud establecimiento=comboBoxEstablecimientoSalud.getSelectionModel().getSelectedItem();
					mostrarEstablecimientoSalud();
					comboBoxEstablecimientoSalud.getSelectionModel().select(establecimiento);
					
					
					//estab.getIdUltimoEstablecimientoSalud();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
				

				//stage.show();
				
			}
		});
		
		jFXButtonAnadirEstablecimientoSalud.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				String urlFxml = "/registro/personaConDiscapacidad/nuevoRegistroPD/establecimientoSalud/EstablecimientoSaludInterfaz.fxml";
				String css = "/registro/personaConDiscapacidad/nuevoRegistroPD/establecimientoSalud/establecimientoSaludInterfaz.css";
				
				
				
				
				
				try {
					System.out.println("Hola estoy dento de, metodo de modal :D");
					FXMLLoader fXMLLoader=new FXMLLoader();
					fXMLLoader.setLocation(getClass().getResource(urlFxml));
					fXMLLoader.load();
					EstablecimientoSaludControler estab=fXMLLoader.getController();
					
					Parent parent= fXMLLoader.getRoot();
					Scene scene=new Scene(parent);
					scene.setFill(new Color(0,0,0,0));
					scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
					Stage stage1=new Stage();
					stage1.setScene(scene);
					stage1.initModality(Modality.APPLICATION_MODAL);
					stage1.initStyle(StageStyle.TRANSPARENT);
					stage1.showAndWait();;
					
					EstablecimientoSalud estable=comboBoxEstablecimientoSalud.getSelectionModel().getSelectedItem();
					mostrarEstablecimientoSalud();
					if(estab.getIdUltimoEstablecimientoSalud()!=-1){
						comboBoxEstablecimientoSalud.getSelectionModel().select(new EstablecimientoSalud(estab.getIdUltimoEstablecimientoSalud(), null));
					}else{
						comboBoxEstablecimientoSalud.getSelectionModel().select(estable);
					}
					DirectorEstablecimiento director=comboBoxDirectorEstablecimiento.getSelectionModel().getSelectedItem();
					mostrarDirectorEstablecimiento();
					comboBoxDirectorEstablecimiento.getSelectionModel().select(director);
					estab.getIdUltimoEstablecimientoSalud();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			
			}
		});
		
		jFXButtonAnadirDirectorEstablecimiento.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String urlFxml = "/registro/personaConDiscapacidad/nuevoRegistroPD/directorEstablecimiento/DirectorEstablecimientoInterfaz.fxml";
				String css = "/registro/personaConDiscapacidad/nuevoRegistroPD/directorEstablecimiento/directorEstablecimientoInterfaz.css";
				
				
				
				
				
				try {
					System.out.println("Hola estoy dento de, metodo de modal :D");
					FXMLLoader fXMLLoader=new FXMLLoader();
					fXMLLoader.setLocation(getClass().getResource(urlFxml));
					fXMLLoader.load();
					DirectorEstablecimientoControler estab=fXMLLoader.getController();
					
					Parent parent= fXMLLoader.getRoot();
					Scene scene=new Scene(parent);
					scene.setFill(new Color(0,0,0,0));
					scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
					Stage stage1=new Stage();
					stage1.setScene(scene);
					stage1.initModality(Modality.APPLICATION_MODAL);
					stage1.initStyle(StageStyle.TRANSPARENT);
					stage1.showAndWait();;
					DirectorEstablecimiento director=comboBoxDirectorEstablecimiento.getSelectionModel().getSelectedItem();

					mostrarDirectorEstablecimiento();
					
					if(estab.getUltimoIDDirector()!=-1){
						comboBoxDirectorEstablecimiento.getSelectionModel().select(new DirectorEstablecimiento(estab.getUltimoIDDirector(), null));
					}else{
						comboBoxDirectorEstablecimiento.getSelectionModel().select(director);
					}
					EstablecimientoSalud establecimiento=comboBoxEstablecimientoSalud.getSelectionModel().getSelectedItem();
					mostrarEstablecimientoSalud();
					comboBoxEstablecimientoSalud.getSelectionModel().select(establecimiento);
					estab.getUltimoIDDirector();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
		
	
		
	/*	jFXButtonAnadirProgramaSocial.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String urlFxml = "/registro/personaConDiscapacidad/nuevoRegistroPD/programaSocial/ProgramaSocialInterfaz.fxml";
				String css = "/registro/personaConDiscapacidad/nuevoRegistroPD/programaSocial/ProgramaSocialInterfaz.css";
				
				
				
				
			     ObservableList<ProgramaSocial> arrayProgramaSocial_auxiliar=FXCollections.observableArrayList();
			     arrayProgramaSocial_auxiliar=checkComboBoxProgramaSocial.getCheckModel().getCheckedItems();
				try {
					System.out.println("Hola estoy dento de, metodo de modal :D");
					FXMLLoader fXMLLoader=new FXMLLoader();
					fXMLLoader.setLocation(getClass().getResource(urlFxml));
					fXMLLoader.load();
					ProgramaSocialControler estab=fXMLLoader.getController();
					
					Parent parent= fXMLLoader.getRoot();
					Scene scene=new Scene(parent);
					scene.setFill(new Color(0,0,0,0));
					scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
					Stage stage1=new Stage();
					stage1.setScene(scene);
					stage1.initModality(Modality.APPLICATION_MODAL);
					stage1.initStyle(StageStyle.TRANSPARENT);
					stage1.showAndWait();;
					mostrarCheckComboBoxProgramaSocial();
					checkComboBoxProgramaSocial.getCheckModel().check(new ProgramaSocial(estab.getUltimoIDProgramaSocial(), null));
					for(int i=0;i<arrayProgramaSocial_auxiliar.size();i++){
						checkComboBoxProgramaSocial.getCheckModel().check(new ProgramaSocial(arrayProgramaSocial_auxiliar.get(i).getId(), null));

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});*/
		
		
	}
	
	
	public void agregarRestriccionCamposNoVacios (){
	
		textFieldApellPaterno.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ObservableList<String> styleClass = textFieldApellPaterno.getStyleClass();
		        if (textFieldApellPaterno.getText().trim().length()==0) {
		            if (! styleClass.contains("error")) {
		                styleClass.add("error");
		            }
		        } else {
		            // remove all occurrences:
		            styleClass.removeAll(Collections.singleton("error"));                    
		        }
			}
		});
		
		
		
		
		textFieldApellMaterno.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				ObservableList<String> styleClass = textFieldApellMaterno.getStyleClass();
		        if (textFieldApellMaterno.getText().trim().length()==0) {
		            if (! styleClass.contains("error")) {
		                styleClass.add("error");
		            }
		        } else {
		            // remove all occurrences:
		            styleClass.removeAll(Collections.singleton("error"));                    
		        }
				
			}
		});
		
		
		textFieldApellNombres.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				ObservableList<String> styleClass = textFieldApellNombres.getStyleClass();
		        if (textFieldApellNombres.getText().trim().length()==0) {
		            if (! styleClass.contains("error")) {
		                styleClass.add("error");
		            }
		        } else {
		            // remove all occurrences:
		            styleClass.removeAll(Collections.singleton("error"));                    
		        
				
		        }
			}
		});
		
		
		
		
		RadioButtonS_D.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				textFieldDocId.setDisable(true);
			}
		});
		RadioButtonDNI.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				textFieldDocId.setDisable(false);
			}
		});
		RadioButtonCUI.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				textFieldDocId.setDisable(false);
			}
		});
		
		textFieldDocId.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				ObservableList<String> styleClass = textFieldDocId.getStyleClass();
		        if (textFieldDocId.getText().trim().length()<8) {
		            if (! styleClass.contains("error")) {
		                styleClass.add("error");
		            }
		        } else {
		            // remove all occurrences:
		            styleClass.removeAll(Collections.singleton("error"));                    
		        
				
		        }
			}
		});
		
		
	
		textFieldDiagnosticoMedico.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				ObservableList<String> styleClass = textFieldDiagnosticoMedico.getStyleClass();
		        if (textFieldDiagnosticoMedico.getText().trim().length()==0) {
		            if (! styleClass.contains("error")) {
		                styleClass.add("error");
		            }
		        } else {
		            // remove all occurrences:
		            styleClass.removeAll(Collections.singleton("error"));                    
		        }
				
			}
		});
		
		/*
		datePickerFechaNacimiento.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				System.out.println(datePickerFechaNacimiento.getValue());
				ObservableList<String> styleClass=datePickerFechaNacimiento.getStyleClass();
				try{
					java.util.Date date =java.util.Date.from(datePickerFechaNacimiento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
					styleClass.removeAll(Collections.singleton("error"));
				}catch(Exception e){
					styleClass.add("error");
					if(!styleClass.contains("error")){
						
					}
				}
				
				
			}
		});*/
		
		arrayNivelEducativo.addListener(new ListChangeListener<NivelEducativo>() {

			@Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends NivelEducativo> c) {
				
				System.out.println("fasdfa "+comboBoxNivelEducativo.getSelectionModel().getSelectedIndex());
				ObservableList<String> styleClass = comboBoxNivelEducativo.getStyleClass();
		        if (comboBoxNivelEducativo.getSelectionModel().getSelectedIndex()==-1) {
		            if (! styleClass.contains("error")) {
		                styleClass.add("error");
		            }
		        } else {
		            // remove all occurrences:
		            styleClass.removeAll(Collections.singleton("error"));                    
		        
				
		        }
				
			}
		});;
		
		
	}
	public Tooltip getTooltipAdvertencia(String mensaje){
		tooltipAdvertencia=new Tooltip(mensaje);
		return tooltipAdvertencia;
	}
	
	

	public void restricciones(){
		
		//restriccion des de bloqueo de campos
		
		//restricciones de formato de fecha
		//datePickerFechaEmisionCertificado.
		
		
		//restricciones de campo vacio xD
		textFieldApellPaterno.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        char character=event.getCharacter().charAt(0);
		        labelInformacion.setVisible(false);

		        System.out.println(character);
		        if(!(Character.isLetter(character) || character==' ')){
		        	event.consume();
		        }          
		        //textFieldApellPaterno.setText((textFieldApellPaterno.getText()+character).toUpperCase());
		        
		    }});
		
	
		
		
		
		textFieldApellMaterno.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        char character=event.getCharacter().charAt(0);
		        labelInformacion.setVisible(false);

		        if(!(Character.isLetter(character) || character==' ')){
		        	event.consume();
		        }                    
		    }});
		textFieldApellNombres.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        char character=event.getCharacter().charAt(0);
		        labelInformacion.setVisible(false);

		        if(!(Character.isLetter(character) || character==' ')){
		        	event.consume();
		        }                    
		    }});
		textFieldDocId.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        char numero=event.getCharacter().charAt(0);
		        labelInformacion.setVisible(false);
		        if(!Character.isDigit(numero) || textFieldDocId.getText().length()>7){
		        	event.consume();
		        }                    
		    }});
		
		textFiedUltimoAnio.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        char numero=event.getCharacter().charAt(0);
		        labelInformacion.setVisible(false);
		        if(!Character.isDigit(numero) || textFiedUltimoAnio.getText().length()>4){
		        	event.consume();
		        }                    
		    }});
		textFieldRestrincionParticipacion.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        char numero=event.getCharacter().charAt(0);
		        labelInformacion.setVisible(false);
		        if(!(Funciones.esValidoDecimal(textFieldRestrincionParticipacion.getText()+numero))){
		        	event.consume();
		        }                    
		    }});
		
		
	
		/*textFieldDiagnosticoMedicoCie.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {

				String[] aux=textFieldDiagnosticoMedicoCie.getText().trim().split(",");
			
				String auxconcat="";
				for(int i=0;i<aux.length-1;i++){
					if(i==0){
						auxconcat=aux[i];
					}else{
						auxconcat=auxconcat+","+aux[i];
					}
				}
				textFieldDiagnosticoMedicoCie.setText(auxconcat);
				textFieldDiagnosticoMedicoCie.selectPositionCaret(auxconcat.length());
				
				
				event.consume();
				
			}
		});*/
	
	}
	
	public void iniciarValoresTableViewDiagnosticoEnfermedadesCIE() {
		
		
		
		TableViewCie.setItems(arrayTableViewDiagnosticoEnfermedadesCIE);
		TableColumnNroCie.setCellValueFactory(new PropertyValueFactory<>("nro"));
		TableCodigoCie.setCellValueFactory(new PropertyValueFactory<>("codCie"));
		TableColumnNombreCie.setCellValueFactory(new PropertyValueFactory<>("nombre"));
	}
	
	public void mostrarCheckBoxSexo(){
		hBoxSexo.getChildren().clear();
		hBoxSexo.getChildren().addAll(arrayCheckBoxSexo);
		
		
		EventHandler<ActionEvent> evento=new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				CheckBoxSexo checkBoxAuliarEvent=(CheckBoxSexo)event.getSource();
				if(checkBoxAuliarEvent.isSelected()){
					for(int i=0;i<hBoxSexo.getChildren().size();i++){
						CheckBoxSexo checkBoxauxiliarArray=(CheckBoxSexo) hBoxSexo.getChildren().get(i);
						
						checkBoxauxiliarArray.setSelected(false);
					}
				}
				checkBoxAuliarEvent.setSelected(true);
					
			
				

			}
		};
	
			CheckBoxSexo checkBoxMasculino=new CheckBoxSexo(1, "Masculino");
			checkBoxMasculino.setOnAction(evento);
			hBoxSexo.getChildren().add(checkBoxMasculino);
			
			CheckBoxSexo checkBoxFemenino=new CheckBoxSexo(2, "Femenino");
			checkBoxFemenino.setOnAction(evento);
			hBoxSexo.getChildren().add(checkBoxFemenino);
		
	}
	public boolean verifcarCamposBlancos(){
		
		
		
		
		
		//dni seleccionado 
				if(!(RadioButtonCUI.isSelected() || RadioButtonDNI.isSelected() || RadioButtonS_D.isSelected())){
					RadioButtonDNI.requestFocus();
					scrollPaneContenedor.setVvalue(0);
					return false; 
				}
				
	 //campo de dni vacio 
				ObservableList<String> 	styleClass=textFieldDocId.getStyleClass();
				if(textFieldDocId.getText().trim().isEmpty() && !RadioButtonS_D.isSelected()){
					
					if (! styleClass.contains("error")) {
		                styleClass.add("error");
		              
		            }
				   scrollPaneContenedor.setVvalue(0);
				   textFieldDocId.requestFocus();
					
					
					return false;
				}else{
					styleClass.removeAll(Collections.singleton("error"));

				}
				
		
		
		//campo de apellido paterno 
	styleClass = textFieldApellPaterno.getStyleClass();
		if(textFieldApellPaterno.getText().trim().isEmpty()){
			   if (! styleClass.contains("error")) {
	                styleClass.add("error");
	              
	            }
			   scrollPaneContenedor.setVvalue(0);
			textFieldApellPaterno.requestFocus();
	
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));
		}
		
		//campo apellido materno 
		styleClass=textFieldApellMaterno.getStyleClass();
		if(textFieldApellMaterno.getText().trim().isEmpty()){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
		   scrollPaneContenedor.setVvalue(0);
			textFieldApellMaterno.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		} 
		
		//campo de nombres
		styleClass=textFieldApellNombres.getStyleClass();
		if(textFieldApellNombres.getText().trim().isEmpty()){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
		   scrollPaneContenedor.setVvalue(0);
			textFieldApellNombres.requestFocus();
			return false;
		}else {
			styleClass.removeAll(Collections.singleton("error"));
		}
		
		//verificar que este seleccionado un sexo 
		boolean sexoSeleccionado=false;
		for(int i=0;i<hBoxSexo.getChildren().size();i++){
			CheckBoxSexo checBoxSexo=(CheckBoxSexo)hBoxSexo.getChildren().get(i);
			if(checBoxSexo.isSelected()){
				System.out.println(checBoxSexo.getNombre());
				sexoSeleccionado=true;
				break;
			}
		}
		
		
		
		if(!sexoSeleccionado){
			for(int i=0;i<hBoxSexo.getChildren().size();i++){
				CheckBoxSexo checBoxSexo=(CheckBoxSexo)hBoxSexo.getChildren().get(i);
			   checBoxSexo.requestFocus();		
			}
			 scrollPaneContenedor.setVvalue(0);
			return false;
		}
		
		
		
		
		//datpicker
		styleClass=datePickerFechaNacimiento.getStyleClass();
		try{
			java.util.Date date =java.util.Date.from(datePickerFechaNacimiento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
			styleClass.removeAll(Collections.singleton("error"));
			
		}catch(Exception e){
			if(!styleClass.contains("error")){
				styleClass.add("error");
			}
			scrollPaneContenedor.setVvalue(0);
			datePickerFechaNacimiento.requestFocus();
			return false;
		}
		//campo de fecha vacio 
		/*styleClass=comboBoxNivelEducativo.getStyleClass();
		if(comboBoxNivelEducativo.getSelectionModel().getSelectedItem()==null){
			if(!styleClass.contains("error")){
				styleClass.add("error");
			}
			scrollPaneContenedor.setVvalue(0);
			comboBoxNivelEducativo.requestFocus();
			return false;
		
		}else{
			styleClass.removeAll(Collections.singleton("error"));

		}
		
		styleClass=comboBoxEstadoCivil.getStyleClass();
		if(comboBoxEstadoCivil.getSelectionModel().getSelectedItem()==null){
			if(!styleClass.contains("error")){
				styleClass.add("error");
			}
			scrollPaneContenedor.setVvalue(0);
			comboBoxEstadoCivil.requestFocus();
		}else{
			styleClass.retainAll(Collections.singleton("error"));
		}
		
		//campo 
		
		if(comboBoxTipoTrabajo.getSelectionModel().getSelectedItem()==null){
			comboBoxTipoTrabajo.requestFocus();
			scrollPaneContenedor.setVvalue(0);
			return false;
		
		}*/
		
		
		if(comboBoxRegion.getSelectionModel().getSelectedItem()==null){
			comboBoxRegion.requestFocus();
			scrollPaneContenedor.setVvalue(0.1);
			return false;
		
		}
		
		if(comboBoxProvincia.getSelectionModel().getSelectedItem()==null){
			comboBoxProvincia.requestFocus();
			scrollPaneContenedor.setVvalue(0.1);
			return false;
		
		}
		
		if(comboBoxDistrito.getSelectionModel().getSelectedItem()==null){
			comboBoxDistrito.requestFocus();
			scrollPaneContenedor.setVvalue(0.1);
			return false;
		
		}
		/*
		if(comboBoxProcedencia.getSelectionModel().getSelectedItem()==null){
			comboBoxProcedencia.requestFocus();
			scrollPaneContenedor.setVvalue(0.1);
			return false;
		
		}
		*/
		if(comboBoxTipoVia.getSelectionModel().getSelectedItem()==null){
			comboBoxTipoVia.requestFocus();
			scrollPaneContenedor.setVvalue(0.3);
			return false;
		
		}

		
		if(textFieldNombreVia.getText().trim().isEmpty()){
			textFieldNombreVia.requestFocus();
			scrollPaneContenedor.setVvalue(0.3);
			return false;
		}
		
		
		//tiene certificado medico
		boolean tieneCertificadoDiscapacidad=false;
		String tieneCertificadoDiscapacidad_Si="";
		for(int i=0;i<hBoxTieneCertificadoDiscapacidad.getChildren().size();i++){
			CheckBoxTieneCertificadoDiscapacidad checBoxTieneCertD=(CheckBoxTieneCertificadoDiscapacidad)hBoxTieneCertificadoDiscapacidad.getChildren().get(i);
			if(checBoxTieneCertD.isSelected()){
				tieneCertificadoDiscapacidad=true;
				tieneCertificadoDiscapacidad_Si=checBoxTieneCertD.getNombre();
				
				break;
			}
		}
		
		
		
		if(!tieneCertificadoDiscapacidad){
			for(int i=0;i<hBoxTieneCertificadoDiscapacidad.getChildren().size();i++){
				CheckBoxTieneCertificadoDiscapacidad checBoxTieneCertD=(CheckBoxTieneCertificadoDiscapacidad)hBoxTieneCertificadoDiscapacidad.getChildren().get(i);
				checBoxTieneCertD.requestFocus();
				scrollPaneContenedor.setVvalue(0.5);
				break;
			}
			return false;
		}
		
		/*
		styleClass = textFieldNroCertificado.getStyleClass();
		if(textFieldNroCertificado.getText().trim().isEmpty() && tieneCertificadoDiscapacidad_Si.toUpperCase().equals("SI".toUpperCase())){
			   if (! styleClass.contains("error")) {
	                styleClass.add("error");
	              
	            }
			   scrollPaneContenedor.setVvalue(0.5);
			   textFieldNroCertificado.requestFocus();
	
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));
		}
		
		*/
		
		/*
		styleClass=checkComboBoxTipoDiscapacidad.getStyleClass();
		if(checkComboBoxTipoDiscapacidad.getCheckModel().getCheckedIndices().isEmpty()){
			checkComboBoxTipoDiscapacidad.requestFocus();
			 scrollPaneContenedor.setVvalue(0.5);
			if(!styleClass.contains("error_check")){
				styleClass.add("error_check");
			}
			return false;
		}else{
			styleClass.removeAll(Collections.singleton("error_check"));
		}
		

		styleClass=comboBoxNivelGravedad.getStyleClass();
		if(comboBoxNivelGravedad.getSelectionModel().getSelectedItem()==null){
			if(!styleClass.contains("error")){
				styleClass.add("error");
			}
			comboBoxNivelGravedad.requestFocus();
			return false;
		}else{
			styleClass.removeAll(Collections.singleton("error"));

		}*/

		/*
		styleClass=textFieldDiagnosticoMedico.getStyleClass();

		if(textFieldDiagnosticoMedico.getText().trim().isEmpty()){
			textFieldDiagnosticoMedico.requestFocus();
			 scrollPaneContenedor.setVvalue(0.5);
			if(!styleClass.contains("error")){
				styleClass.add("error");
			}
			return false;
		}else{
			styleClass.removeAll(Collections.singleton("error"));
		}
		*/
		/*
		styleClass=chekcComboBoxLimitacionPermanente.getStyleClass();
		if(chekcComboBoxLimitacionPermanente.getCheckModel().getCheckedIndices().isEmpty()){
			if(!styleClass.contains("error_check")){
				styleClass.add("error_check");
			}
			chekcComboBoxLimitacionPermanente.requestFocus();
			return false;
		}else{
			styleClass.removeAll(Collections.singleton("error_check"));

		}
		
		styleClass=checkComboBoxTipoAyudaBiometrica.getStyleClass();

		if(checkComboBoxTipoAyudaBiometrica.getCheckModel().getCheckedIndices().isEmpty()){
			if(!styleClass.contains("error_check")){
				styleClass.add("error_check");
			}
			 scrollPaneContenedor.setVvalue(0.8);

			checkComboBoxTipoAyudaBiometrica.requestFocus();
			return false;
		}else{
			styleClass.removeAll(Collections.singleton("error_check"));

		}
		
		
		styleClass=checkComboBoxTipoSeguroMedico.getStyleClass();

		if(checkComboBoxTipoSeguroMedico.getCheckModel().getCheckedIndices().isEmpty()){
			if(!styleClass.contains("error_check")){
				styleClass.add("error_check");
			}
			checkComboBoxTipoSeguroMedico.requestFocus();
			return false;
		}else{
			styleClass.removeAll(Collections.singleton("error_check"));

		}
		
	
		
		
		
		styleClass=comboBoxOrigenLimitacion.getStyleClass();
		if(comboBoxOrigenLimitacion.getSelectionModel().getSelectedItem()==null){
			if(!styleClass.contains("error")){
				styleClass.add("error");
			}
			comboBoxOrigenLimitacion.requestFocus();
			return false;
		}else{
			styleClass.removeAll(Collections.singleton("error"));

		}*/
		
		
	
		

		
		//tieneincripcoion
		boolean tieneInscripcionCondis=false;
		String tieneInscripcionCondis_SI="";
		for(int i=0;i<hBoxTieneInscripcionConadis.getChildren().size();i++){
			CheckBoxTieneInscripcionConadis checBoxTieneCertD=(CheckBoxTieneInscripcionConadis)hBoxTieneInscripcionConadis.getChildren().get(i);
			if(checBoxTieneCertD.isSelected()){
				tieneInscripcionCondis=true;
				tieneInscripcionCondis_SI=checBoxTieneCertD.getNombre();
				break;
			}
		}
		
		
		
		if(!tieneInscripcionCondis){
			for(int i=0;i<hBoxTieneInscripcionConadis.getChildren().size();i++){
				CheckBoxTieneInscripcionConadis checBoxTieneCertD=(CheckBoxTieneInscripcionConadis)hBoxTieneInscripcionConadis.getChildren().get(i);
				checBoxTieneCertD.requestFocus();	
				break;
			}
			return false;
		}
		
		/*
		styleClass = textFieldNumeroCarne.getStyleClass();
		if(textFieldNumeroCarne.getText().trim().isEmpty() && tieneInscripcionCondis_SI.toUpperCase().equals("SI".toUpperCase())){
			   if (! styleClass.contains("error")) {
	                styleClass.add("error");
	              
	            }
			  // scrollPaneContenedor.setVvalue();
			   textFieldNumeroCarne.requestFocus();
	
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));
		}
		
	
		styleClass = textFieldNumeroResolucion.getStyleClass();
		if(textFieldNumeroResolucion.getText().trim().isEmpty() && tieneInscripcionCondis_SI.toUpperCase().equals("SI".toUpperCase())){
			   if (! styleClass.contains("error")) {
	                styleClass.add("error");
	              
	            }
			  // scrollPaneContenedor.setVvalue();
			   textFieldNumeroResolucion.requestFocus();
	
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));
		}*/
		
		return true;
	}
	

	
	
	
	public void mostrarDepartamentos(){
		comboBoxRegion.setItems(arrayDepartamento);
		comboBoxRegion.getItems().clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM DEPARTAMENTO");
			
			
			rs=pst.executeQuery();
			while(rs.next()){
				comboBoxRegion.getItems().add(new Departamento(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	}
	
	
	
	
	public void mostrarProvincias(int idRegion){
		comboBoxProvincia.setItems(arrayProvincias);
		comboBoxProvincia.getItems().clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM PROVINCIA WHERE ID_DEPARTAMENTO=?");
			pst.setInt(1, idRegion);;
			
			rs=pst.executeQuery();
			while(rs.next()){
				comboBoxProvincia.getItems().add(new Provincia(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	}
	
	
	public void mostrarDistritos(int idProvincia){
		comboBoxDistrito.setItems(arrayDistritos);
		comboBoxDistrito.getItems().clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM DISTRITO WHERE ID_PROVINCIA=?");
			pst.setInt(1, idProvincia);;
			
			rs=pst.executeQuery();
			while(rs.next()){
				comboBoxDistrito.getItems().add(new Distrito(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	}
	
	
	
	
	
	
	public void mostrarTipoVia(){
		comboBoxTipoVia.setItems(arrayTipoVia);
		comboBoxTipoVia.getItems().clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM TIPO_VIA");
			
			
			rs=pst.executeQuery();
			while(rs.next()){
				comboBoxTipoVia.getItems().add(new TipoVia(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	}
	

	
	public void mostrarMedicoCertificador(){
		comboBoxMedicoCertificador.setItems(arrayMedicoCertificador);
		comboBoxMedicoCertificador.getItems().clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, CONCAT(NOMBRES, ', ',APELLIDOS) AS NOMBRE FROM MEDICO ORDER BY NOMBRES");
			
			
			rs=pst.executeQuery();
			while(rs.next()){
				comboBoxMedicoCertificador.getItems().add(new MedicoCertificador(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	}
	

	
	public void mostrarDirectorEstablecimiento(){
		comboBoxDirectorEstablecimiento.setItems(arrayDirectorEstablecimiento);
		comboBoxDirectorEstablecimiento.getItems().clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, CONCAT(NOMBRES, ', ',APELLIDOS) AS NOMBRE FROM DIRECTOR ORDER BY NOMBRE");
			
			
			rs=pst.executeQuery();
			while(rs.next()){
				comboBoxDirectorEstablecimiento.getItems().add(new DirectorEstablecimiento(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	}
	
	
	public void mostrarEstablecimientoSalud(){
		comboBoxEstablecimientoSalud.setItems(arrayEstablecimientoSalud);
		comboBoxEstablecimientoSalud.getItems().clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM ESTABLECIMIENTO_SALUD ORDER BY NOMBRE");
			
			
			rs=pst.executeQuery();
			while(rs.next()){
				comboBoxEstablecimientoSalud.getItems().add(new EstablecimientoSalud(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	}
	

	
	
	
	
	
	public void mostrarTipoTramite(){
		comboBoxTipoTramite.setItems(arrayTipoTramite);
		comboBoxTipoTramite.getItems().clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM TIPO_DE_TRAMITE");
			
			
			rs=pst.executeQuery();
			while(rs.next()){
				comboBoxTipoTramite.getItems().add(new TipoTramite(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	}
	
	

	
	public void mostrarEstadoCivil(){
		comboBoxEstadoCivil.setItems(arrayEstadoCivil);
		comboBoxEstadoCivil.getItems().clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM ESTADO_CIVIL");
			
			
			rs=pst.executeQuery();
			while(rs.next()){
				comboBoxEstadoCivil.getItems().add(new EstadoCivil(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	}
	
	
	public void mostrarProcedencia(){
		comboBoxProcedencia.setItems(arrayProcedencia);
		comboBoxProcedencia.getItems().clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM TIPO_PROCEDENCIA");
			
			
			rs=pst.executeQuery();
			while(rs.next()){
				comboBoxProcedencia.getItems().add(new Procedencia(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	}
	
	
	public void probar(){
	
		ObservableList<String>  styleClass=checkComboBoxTipoDiscapacidad.getStyleClass();
		if(checkComboBoxTipoDiscapacidad.getCheckModel().getCheckedIndices().isEmpty()){
			checkComboBoxTipoDiscapacidad.requestFocus();
			if(!styleClass.contains("error_check")){
				styleClass.add("error_check");
			}
			
		}else{
			styleClass.removeAll(Collections.singleton("error_check"));
		}
	}
	

	public void mostrarNivelGravedad(){
		comboBoxNivelGravedad.setItems(arrayNivelGravedad);
		comboBoxNivelGravedad.getItems().clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM NIVEL_GRAVEDAD_LIMITACION ORDER BY NOMBRE");
			
			
			rs=pst.executeQuery();
			while(rs.next()){
				comboBoxNivelGravedad.getItems().add(new NivelGravedad(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	}
	
	public void mostrarCheckBoxTieneCertificadoDiscapacidad(){
		hBoxTieneCertificadoDiscapacidad.getChildren().clear();
		hBoxTieneCertificadoDiscapacidad.getChildren().addAll(arrayCheckBoxCertificadoDiscapacidad);
		
		

		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM TIENE_CERTIFICADO_DISCAPACIDAD ORDER BY ID");
			
			
			rs=pst.executeQuery();
			while(rs.next()){
				CheckBoxTieneCertificadoDiscapacidad checkBoxauxiliar=new CheckBoxTieneCertificadoDiscapacidad(rs.getInt("ID"), rs.getString("NOMBRE"));
				checkBoxauxiliar.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						CheckBoxTieneCertificadoDiscapacidad checkBoxAuliarEvent=(CheckBoxTieneCertificadoDiscapacidad)event.getSource();
						if(checkBoxAuliarEvent.isSelected()){
							for(int i=0;i<hBoxTieneCertificadoDiscapacidad.getChildren().size();i++){
								CheckBoxTieneCertificadoDiscapacidad checkBoxauxiliarArray=(CheckBoxTieneCertificadoDiscapacidad) hBoxTieneCertificadoDiscapacidad.getChildren().get(i);
								
								checkBoxauxiliarArray.setSelected(false);
							}
						}
						checkBoxAuliarEvent.setSelected(true);
						if(checkBoxauxiliar.getNombre().equals("Si")){
							//datePickerRegistroConadis.setDisable(false);
							//comboBoxTipoTramite.setDisable(false);
							//textAreaMotivoInscripcionConadis.setDisable(false);
							textFieldNroCertificado.setDisable(false);
							datePickerFechaEmisionCertificado.setDisable(false);
							comboBoxEstablecimientoSalud.setDisable(false);
							comboBoxDirectorEstablecimiento.setDisable(false);
							comboBoxMedicoCertificador.setDisable(false);
							jFXButtonAnadirEstablecimientoSalud.setDisable(false);
							jFXButtonAnadirDirectorEstablecimiento.setDisable(false);
							jFXButtonAnadirMedicoCEertificador.setDisable(false);
						}else{
							//datePickerRegistroConadis.setDisable(true);
							//comboBoxTipoTramite.setDisable(true);
							//textAreaMotivoInscripcionConadis.setDisable(true);
							textFieldNroCertificado.setDisable(true);
							datePickerFechaEmisionCertificado.setDisable(true);
							comboBoxEstablecimientoSalud.setDisable(true);
							comboBoxDirectorEstablecimiento.setDisable(true);
							comboBoxMedicoCertificador.setDisable(true);
							jFXButtonAnadirEstablecimientoSalud.setDisable(true);
							jFXButtonAnadirDirectorEstablecimiento.setDisable(true);
							jFXButtonAnadirMedicoCEertificador.setDisable(true);
							//campos blancos
							//datePickerRegistroConadis.setValue(null);
							//comboBoxTipoTramite.getSelectionModel().select(null);;
							//textAreaMotivoInscripcionConadis.setText("");
							textFieldNroCertificado.setText("");
							datePickerFechaEmisionCertificado.setValue(null);
							comboBoxEstablecimientoSalud.getSelectionModel().select(null);
							comboBoxDirectorEstablecimiento.getSelectionModel().select(null);
							comboBoxMedicoCertificador.getSelectionModel().select(null);
				
							
						}
							
					
		

					}
				});
				hBoxTieneCertificadoDiscapacidad.getChildren().add(checkBoxauxiliar);

				
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
	}
	
	
	
	
	
	
	public void mostrarCheckBoxTieneInscripcionConadis(){
		hBoxTieneInscripcionConadis.getChildren().clear();
		hBoxTieneInscripcionConadis.getChildren().addAll(arrayCheckBoxTieneIncripcionConadis);
		
		

		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM TIENE_INSCRIPCION_CONADIS ORDER BY ID");
			
			
			rs=pst.executeQuery();
			while(rs.next()){
				CheckBoxTieneInscripcionConadis checkBoxauxiliar=new CheckBoxTieneInscripcionConadis(rs.getInt("ID"), rs.getString("NOMBRE"));
				checkBoxauxiliar.setOnAction(new EventHandler<ActionEvent>() {
					
					@Override
					public void handle(ActionEvent event) {
						CheckBoxTieneInscripcionConadis checkBoxAuliarEvent=(CheckBoxTieneInscripcionConadis)event.getSource();
						if(checkBoxAuliarEvent.isSelected()){
							for(int i=0;i<hBoxTieneCertificadoDiscapacidad.getChildren().size();i++){
								CheckBoxTieneInscripcionConadis checkBoxauxiliarArray=(CheckBoxTieneInscripcionConadis) hBoxTieneInscripcionConadis.getChildren().get(i);
								
								checkBoxauxiliarArray.setSelected(false);
							}
						}
						checkBoxAuliarEvent.setSelected(true);
						
						if(checkBoxauxiliar.getNombre().equals("Si")){
							textFieldNumeroCarne.setDisable(false);
							textFieldNumeroResolucion.setDisable(false);
							datePickerFechaEmisionResolcuion.setDisable(false);
							textFieldSeResuelve.setDisable(false);
							datePickerRegistroConadisNacional.setDisable(false);
						}else{
							textFieldNumeroCarne.setDisable(true);
							textFieldNumeroResolucion.setDisable(true);
							datePickerFechaEmisionResolcuion.setDisable(true);
							textFieldSeResuelve.setDisable(true);
							datePickerRegistroConadisNacional.setDisable(true);
							
							
							
							//CAMPOS VACIOS
							textFieldNumeroCarne.setText("");
							textFieldNumeroResolucion.setText("");;
							datePickerFechaEmisionResolcuion.setValue(null);
							textFieldSeResuelve.setText("");;
							datePickerRegistroConadisNacional.setValue(null);
						}
							
							
					
						

					}
				});
				hBoxTieneInscripcionConadis.getChildren().add(checkBoxauxiliar);

				
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
	}
	
	
	public void mostrarTipoTrabajo(){
		comboBoxTipoTrabajo.setItems(arrayTipoTrabajo);
		comboBoxTipoTrabajo.getItems().clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM TIPO_TRABAJO ORDER BY ID");
			
			
			rs=pst.executeQuery();
			while(rs.next()){
				comboBoxTipoTrabajo.getItems().add(new TipoTrabajo(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	}

	
	public void mostrarNivelEducativo(){
		comboBoxNivelEducativo.setItems(arrayNivelEducativo);
		comboBoxNivelEducativo.getItems().clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM NIVEL_EDUCATIVO ORDER BY ID");
			
			
			rs=pst.executeQuery();
			while(rs.next()){
				comboBoxNivelEducativo.getItems().add(new NivelEducativo(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	}

	
	public void mostrarCheckComboBoxTipoDiscapacidad(){
		checkComboBoxTipoDiscapacidad.getItems().clear();
		arrayTipoDiscapacidad.clear();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM TIPO_DISCAPACIDAD  ORDER BY ID");
			System.out.println("Estuve aqui xD");
			
			rs=pst.executeQuery();
			while(rs.next()){
				System.out.println("aqui tambien");
				System.out.println("Holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				arrayTipoDiscapacidad.add(new TipoDiscapacidad(rs.getInt("ID"), rs.getString("NOMBRE"), null));
				//checkComboBoxTipoDiscapacidad.getItems().add(new TipoDiscapacidad(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	
		checkComboBoxTipoDiscapacidad.getItems().addAll(arrayTipoDiscapacidad);
		
		
		

	}
	
	

	public void mostrarCheckComboBoxRequerimientoApoyo(){
		chekcComboBoxRequerimientoApoyo.getItems().clear();
		arrayRequerimientoApoyo.clear();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM REQUERIMIENTO_APOYO  ORDER BY ID");
			
			rs=pst.executeQuery();
			while(rs.next()){
				arrayRequerimientoApoyo.add(new RequerimientoApoyo(rs.getInt("ID"), rs.getString("NOMBRE")));
				//checkComboBoxTipoDiscapacidad.getItems().add(new TipoDiscapacidad(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	
		chekcComboBoxRequerimientoApoyo.getItems().addAll(arrayRequerimientoApoyo);
		
		
		

	}
	

	
	public void mostrarCheckComboBoxProgramaSocial(){
		
		arrayProgramaSocial=FXCollections.observableArrayList();;
		checkComboBoxProgramaSocial.getItems().clear();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM PROGRAMA_SOCIAL  ORDER BY ID");
			System.out.println("Estuve aqui xD");
			
			rs=pst.executeQuery();
			while(rs.next()){
				System.out.println("aqui tambien");
				System.out.println("Holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				arrayProgramaSocial.add(new ProgramaSocial(rs.getInt("ID"), rs.getString("NOMBRE")));
				//checkComboBoxTipoDiscapacidad.getItems().add(new TipoDiscapacidad(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	
		checkComboBoxProgramaSocial.getItems().addAll(arrayProgramaSocial);
		
		
		

	}
	
	
	public void mostrarCheckComboBoxTipoAyudaBiometrica(){
		checkComboBoxTipoAyudaBiometrica.getItems().clear();
		arrayTipoAyudaBiometrica.clear();
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM TIPO_AYUDA_BIOMECANICA  ORDER BY NOMBRE");
			System.out.println("Estuve aqui xD");
			
			rs=pst.executeQuery();
			while(rs.next()){
				System.out.println("aqui tambien");
				
				arrayTipoAyudaBiometrica.add(new TipoAyudaBiomecanica(rs.getInt("ID"), rs.getString("NOMBRE"), null));
				//checkComboBoxTipoDiscapacidad.getItems().add(new TipoDiscapacidad(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	
		checkComboBoxTipoAyudaBiometrica.getItems().addAll(arrayTipoAyudaBiometrica);
		
		
		

	}
	

	
	public void mostrarCheckComboBoxTipoSeguroMedico(){
		
		checkComboBoxTipoSeguroMedico.getItems().clear();
		arrayTipoSeguroMedico.clear();
		
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM TIPO_SEGURO  ORDER BY NOMBRE");
			System.out.println("Estuve aqui xD");
			
			rs=pst.executeQuery();
			while(rs.next()){
				System.out.println("aqui tambien");
				
				arrayTipoSeguroMedico.add(new TipoSeguroMedico(rs.getInt("ID"), rs.getString("NOMBRE"), null));
				//checkComboBoxTipoDiscapacidad.getItems().add(new TipoDiscapacidad(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	
		checkComboBoxTipoSeguroMedico.getItems().addAll(arrayTipoSeguroMedico);
		
		
		

	}
	



	
	public void mostrarCheckComboBoxLimitacionPermanente(){
		chekcComboBoxLimitacionPermanente.getItems().clear();
		arrayLimitacionPermanente.clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM LIMITACION_PERMANENTE_PARA  ORDER BY NOMBRE");
			System.out.println("Estuve aqui xD");
			
			rs=pst.executeQuery();
			while(rs.next()){
				System.out.println("aqui tambien");
				
				arrayLimitacionPermanente.add(new LimitacionPermanente(rs.getInt("ID"), rs.getString("NOMBRE"), null));
				//checkComboBoxTipoDiscapacidad.getItems().add(new TipoDiscapacidad(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	
		chekcComboBoxLimitacionPermanente.getItems().addAll(arrayLimitacionPermanente);
		
		
		

	}
		/*Diagnostico Medico*/
		public boolean insertarNuevoTipoDiscapacidad(){
			boolean estado =false;
			Connection conn=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
			String nombre=textFieldDiagnosticoMedico.getText().trim();
			try{
				Conexion.conectarDB();
				conn=Conexion.getConexion();
				pst=conn.prepareStatement("INSERT INTO TIPO_DISCAPACIDAD(NOMBRE) VALUES(?)",PreparedStatement.RETURN_GENERATED_KEYS);
				pst.setString(1, nombre);
				
				pst.executeUpdate();
				rs=pst.getGeneratedKeys();
				while(rs.next()){
					TipoDiscapacidad tipoDsic=new TipoDiscapacidad(rs.getInt(1), nombre, null);
					System.out.println(rs.getInt(1));
					checkComboBoxTipoDiscapacidad.getItems().add(tipoDsic);
					checkComboBoxTipoDiscapacidad.getCheckModel().check(tipoDsic);
					estado=true;
				}
				
			}catch(Exception e){
				estado=false;
				
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
			return estado;
		}
	/*
	public void insertarDatosPersonaDiscapacidad(){
		
		
		int idTemporal=-1;
		Connection conn=null;
		int rsInsercion=0;
		PreparedStatement pstDomicilio=null;
		PreparedStatement pstPerDiscapacidad=null;
		PreparedStatement pstDatosDiscapacidad=null;
		PreparedStatement pstLimitacion=null;
		PreparedStatement pstAyudaBiomecanica=null;
		PreparedStatement pstTipoSEguro=null;
		PreparedStatement pstTipoDiscapacidad=null;
		PreparedStatement pstProgramaSocial=null;
		ResultSet rsConsulta=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			conn.setAutoCommit(false);
			pstDomicilio=conn.prepareStatement("INSERT INTO DOMICILIO(ID_DISTRITO, ID_TIPO_VIA, NOMBRE_VIA,NRO,EDIFICIO,DPTO_INT,MZ,LOTE,ETAPA,URB_AAHH_CP_PJ_OTRO, ID_TIPO_PROCEDENCIA,) VALUES "
					+ "										(?,?,?,?,?,?,?,?,?,?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
			//codigo de insercion
			pstDomicilio.setInt(1, comboBoxDistrito.getSelectionModel().getSelectedItem().getId());
			pstDomicilio.setInt(2, comboBoxTipoVia.getSelectionModel().getSelectedItem().getId());
			pstDomicilio.setString(3, textFieldNombreVia.getText().trim());
			if(!textFieldNroDatosDomicilio.getText().trim().isEmpty()){
				pstDomicilio.setString(4, (textFieldNroDatosDomicilio.getText().trim()));
			}else {
				pstDomicilio.setNull(4, Types.VARCHAR);;
			}
			if(!textFieldEdificioDatosDomicilio.getText().trim().isEmpty()){
				pstDomicilio.setString(5, textFieldEdificioDatosDomicilio.getText().trim());
			}else {
				pstDomicilio.setNull(5, Types.VARCHAR);
			}
			if(!textFieldDpto_IntDatosDomicilio.getText().trim().isEmpty()){
				pstDomicilio.setString(6, textFieldDpto_IntDatosDomicilio.getText().trim());
			}else{
				pstDomicilio.setNull(6,Types.VARCHAR);
			}
			
			if(!textFieldMzDatosDomicilio.getText().trim().isEmpty()){
				pstDomicilio.setString(7, textFieldMzDatosDomicilio.getText().trim());
			}else{
				pstDomicilio.setNull(7, Types.VARCHAR);
			}
			
			if(!textFieldLoteDatosDomicilio.getText().trim().isEmpty()){
				pstDomicilio.setString(8, textFieldLoteDatosDomicilio.getText().trim());
			}else {
				pstDomicilio.setNull(8, Types.VARCHAR);
			}
			
			if(!textFieldEtapaDatosDomicilio.getText().trim().isEmpty()){
				pstDomicilio.setString(9, textFieldEtapaDatosDomicilio.getText().trim());
			}else{
				pstDomicilio.setNull(9, Types.VARCHAR);
			}
			
			if(!textFieldUrb_AAHH_CP_PJ_OtroDatosDomicilio.getText().trim().isEmpty()){
				pstDomicilio.setString(10, textFieldUrb_AAHH_CP_PJ_OtroDatosDomicilio.getText().trim());				
			}else{
				pstDomicilio.setNull(10, Types.VARCHAR);
			}
			
			pstDomicilio.setInt(11, comboBoxProcedencia.getSelectionModel().getSelectedItem().getId());

			rsInsercion=pstDomicilio.executeUpdate();
			if(rsInsercion==1){
				labelInformacion.setText("Datos de Domicilio.. Inserci�n Correcta...");	
			}
			
			
			rsConsulta=pstDomicilio.getGeneratedKeys();
			
			while(rsConsulta.next()){
				idTemporal=rsConsulta.getInt(1);
				System.out.println(idTemporal);
			}
			
			rsConsulta.close();
			
			//datos de personales
			
			pstPerDiscapacidad=conn.prepareStatement("INSERT INTO PER_DISCAPACIDAD(NOMBRES, APELL_PATERNO,APELL_MATERNO,DNI,CUI,FECHA_NACIMIENTO,SEXO,ID_NIVEL_EDUCATIVO,ID_TIPO_TRABAJO,ID_DOMICILIO,CREADO_POR,MODIFICADO_POR, "
					+ "ID_ESTADO_CIVIL,EMAIL, NUM_CONTACTO,OBSERVACION, UTLIMO_A�O_NIVEL_EDUCATIVO,OCUPACION_DESEMPENNA) "
					+ "		   VALUES(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);", PreparedStatement.RETURN_GENERATED_KEYS);

			pstPerDiscapacidad.setString(1, textFieldApellNombres.getText().trim());
			pstPerDiscapacidad.setString(2, textFieldApellPaterno.getText().trim());
			pstPerDiscapacidad.setString(3, textFieldApellMaterno.getText().trim());
			if(RadioButtonDNI.isSelected()){
				pstPerDiscapacidad.setString(4, textFieldDocId.getText().trim());
				pstPerDiscapacidad.setNull(5, java.sql.Types.VARCHAR);
			}else if(RadioButtonCUI.isSelected()){
				pstPerDiscapacidad.setNull(4, java.sql.Types.VARCHAR);
				pstPerDiscapacidad.setString(5, textFieldDocId.getText().trim());		
			}
			java.util.Date date =java.util.Date.from(datePickerFechaNacimiento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				pstPerDiscapacidad.setDate(6, sqlDate);
			
			
			int idSexoSeleccionado=-1;
			for(int i=0;i<hBoxSexo.getChildren().size();i++){
				CheckBoxSexo checBoxSexo=(CheckBoxSexo)hBoxSexo.getChildren().get(i);
				if(checBoxSexo.isSelected()){
					System.out.println(checBoxSexo.getNombre());
					idSexoSeleccionado=checBoxSexo.getId_();
					break;
				}
			}
			
			pstPerDiscapacidad.setInt(7,idSexoSeleccionado);
			pstPerDiscapacidad.setInt(8, comboBoxNivelEducativo.getSelectionModel().getSelectedItem().getId());
			pstPerDiscapacidad.setInt(9, comboBoxTipoTrabajo.getSelectionModel().getSelectedItem().getId());
			pstPerDiscapacidad.setInt(10, idTemporal );
			pstPerDiscapacidad.setString(11, Sesion.DNI);
			pstPerDiscapacidad.setString(12, Sesion.DNI);
			
			//nuevas modificaciones 
			pstPerDiscapacidad.setInt(13, comboBoxEstadoCivil.getSelectionModel().getSelectedItem().getId());
			if(!textFieldEmail.getText().trim().isEmpty()){
				pstPerDiscapacidad.setString(14,textFieldEmail.getText().trim());
			}else{
				pstPerDiscapacidad.setNull(14,java.sql.Types.NULL);

			}
			if(!textFieldApellNumContacto.getText().trim().isEmpty()){
				pstPerDiscapacidad.setString(15, textFieldApellNumContacto.getText().trim() );
			}else{
				pstPerDiscapacidad.setNull(15, java.sql.Types.NULL);
			}
			if(!textAreaObervacionDatosPersonales.getText().trim().isEmpty()){
				pstPerDiscapacidad.setString(16, textAreaObervacionDatosPersonales.getText().trim());
			}else{
				pstPerDiscapacidad.setNull(16,java.sql.Types.NULL);
			}
			
			if(!textFiedUltimoAnio.getText().trim().isEmpty()){
				pstPerDiscapacidad.setInt(17, Integer.parseInt(textFiedUltimoAnio.getText().trim()));
			}else{
				pstPerDiscapacidad.setNull(17, java.sql.Types.INTEGER);
			}
			
			if(!textFieldOcupacionDatosPersonales.getText().trim().isEmpty()){
				pstPerDiscapacidad.setString(18,textFieldOcupacionDatosPersonales.getText().trim());
			}else{
				pstPerDiscapacidad.setNull(18, java.sql.Types.VARCHAR);
			}
			//
			rsInsercion=pstPerDiscapacidad.executeUpdate();
			if(rsInsercion==1){
				labelInformacion.setText("Datos de Persona.. Inserci�n Correcta...");	
			}
			
			
			rsConsulta=pstPerDiscapacidad.getGeneratedKeys();			
			while(rsConsulta.next()){
				idTemporal=rsConsulta.getInt(1);
				System.out.println(idTemporal);
			}
			rsConsulta.close();
			//datos detalle perDiscapacidad-programa social
			pstProgramaSocial=conn.prepareStatement("INSERT INTO D_PROGRAMA_SOCIAL_PER_DISCAPACIDAD (ID_PER_DISCAPACIDAD,ID_PROGRAMA_SOCIAL) VALUES(?,?);");

			for(int i=0;i<checkComboBoxProgramaSocial.getCheckModel().getItemCount();i++){
				if(checkComboBoxProgramaSocial.getCheckModel().isChecked(checkComboBoxProgramaSocial.getCheckModel().getItem(i))){
					pstProgramaSocial.setInt(1, idTemporal);
					pstProgramaSocial.setInt(2, checkComboBoxProgramaSocial.getCheckModel().getItem(i).getId());
					pstProgramaSocial.addBatch();
				}
						
			}
			pstProgramaSocial.executeBatch();

			labelInformacion.setText("Datos de Ayuda Tipo Discapacidad.. Inserci�n Correcta...");	

			
			//datos discapacidad
					
			pstDatosDiscapacidad=conn.prepareStatement("INSERT INTO DATOS_DISCAPACIDAD(ID_PER_DISCAPACIDAD,ID_TIENE_CERTIFICADO_DISCAPACIDAD,ID_TIENE_INSCRIPCION_CONADIS,ID_NIVEL_GRAVEDAD_LIMITACION,ID_ORIGEN_LIMITACION,CREADO_POR,MODIFICADO_POR ) "
					+ "												VALUES (?, ?, ?,?,?,?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstDatosDiscapacidad.setInt(1, idTemporal);
			int idTieneCertificadoDiscapacidadSeleccionado=-1;
			for(int i=0;i<hBoxTieneCertificadoDiscapacidad.getChildren().size();i++){
				CheckBoxTieneCertificadoDiscapacidad checBoxCertificadoDiscapacidad=(CheckBoxTieneCertificadoDiscapacidad)hBoxTieneCertificadoDiscapacidad.getChildren().get(i);
				if(checBoxCertificadoDiscapacidad.isSelected()){
					System.out.println(checBoxCertificadoDiscapacidad.getNombre());
					idTieneCertificadoDiscapacidadSeleccionado=checBoxCertificadoDiscapacidad.getId_();
					break;
				}
			}
			
			pstDatosDiscapacidad.setInt(2, idTieneCertificadoDiscapacidadSeleccionado);
			int idTieneIncripcionConadis=-1;
			for(int i=0;i<hBoxTieneInscripcionConadis.getChildren().size();i++){
				CheckBoxTieneInscripcionConadis checBoxIncripcionConadis=(CheckBoxTieneInscripcionConadis)hBoxTieneInscripcionConadis.getChildren().get(i);
				if(checBoxIncripcionConadis.isSelected()){
					System.out.println(checBoxIncripcionConadis.getNombre());
					idTieneIncripcionConadis=checBoxIncripcionConadis.getId_();
					break;
				}
			}
			pstDatosDiscapacidad.setInt(3,idTieneIncripcionConadis);
			pstDatosDiscapacidad.setInt(4, comboBoxNivelGravedad.getSelectionModel().getSelectedItem().getId());
			pstDatosDiscapacidad.setInt(5, comboBoxOrigenLimitacion.getSelectionModel().getSelectedItem().getId());
			pstDatosDiscapacidad.setString(6, Sesion.DNI);
			pstDatosDiscapacidad.setString(7, Sesion.DNI);
			
			
			
			rsInsercion=pstDatosDiscapacidad.executeUpdate();
			if(rsInsercion==1){
				labelInformacion.setText("Datos de Discapacidad.. Inserci�n Correcta...");	
			}
			
		
			rsConsulta=pstDatosDiscapacidad.getGeneratedKeys();			
			while(rsConsulta.next()){
				idTemporal=rsConsulta.getInt(1);
			}
			rsConsulta.close();
			
			
			pstLimitacion=conn.prepareStatement("INSERT INTO D_LIMITACION_PERMANENTE_PARA_DATOS_DISCAPACIDAD(ID_DATOS_DISCAPACIDAD,ID_LIMITACION_PERMANENTE_PARA) VALUES (?,?)");
			for(int i=0;i<chekcComboBoxLimitacionPermanente.getCheckModel().getItemCount();i++){
				if(chekcComboBoxLimitacionPermanente.getCheckModel().isChecked(chekcComboBoxLimitacionPermanente.getCheckModel().getItem(i))){
					pstLimitacion.setInt(1, idTemporal);
					pstLimitacion.setInt(2, chekcComboBoxLimitacionPermanente.getCheckModel().getItem(i).getId());
					pstLimitacion.addBatch();
	
				}
						
			}
			
			pstLimitacion.executeBatch();
			labelInformacion.setText("Datos de Limitacion.. Inserci�n Correcta...");	
			
			pstAyudaBiomecanica=conn.prepareStatement("INSERT INTO D_TIPO_AYUDA_BIOMECANICA_DATOS_DISCAPACIDAD(ID_DATOS_DISCAPACIDAD,ID_TIPO_AYUDA_BIOMECANICA ) VALUES (?,?);");

			for(int i=0;i<checkComboBoxTipoAyudaBiometrica.getCheckModel().getItemCount();i++){
				if(checkComboBoxTipoAyudaBiometrica.getCheckModel().isChecked(checkComboBoxTipoAyudaBiometrica.getCheckModel().getItem(i))){
					
					pstAyudaBiomecanica.setInt(1, idTemporal);
					pstAyudaBiomecanica.setInt(2, checkComboBoxTipoAyudaBiometrica.getCheckModel().getItem(i).getId());
					pstAyudaBiomecanica.addBatch();
					
				}
						
			}
			
			pstAyudaBiomecanica.executeBatch();
			
			labelInformacion.setText("Datos de Ayuda Biomecanica.. Inserci�n Correcta...");	
			
			pstTipoSEguro=conn.prepareStatement("INSERT INTO D_TTIPO_SEGURO_DATOS_DISCAPACIDAD(ID_DATOS_DISCAPACIDAD, ID_TIPO_SEGURO) VALUES(?, ?);");

			for(int i=0;i<checkComboBoxTipoSeguroMedico.getCheckModel().getItemCount();i++){
				if(checkComboBoxTipoSeguroMedico.getCheckModel().isChecked(checkComboBoxTipoSeguroMedico.getCheckModel().getItem(i))){
					pstTipoSEguro.setInt(1, idTemporal);
					pstTipoSEguro.setInt(2, checkComboBoxTipoSeguroMedico.getCheckModel().getItem(i).getId());
					pstTipoSEguro.addBatch();
				}
						
			}
			pstTipoSEguro.executeBatch();
			labelInformacion.setText("Datos de Ayuda Tipo Seguro.. Inserci�n Correcta...");	


			pstTipoDiscapacidad=conn.prepareStatement("INSERT INTO D_TIPO_DISCAPACIDAD_DATOS_DISCAPACIDAD (ID_DATOS_DISCAPACIDAD,ID_TIPO_DISCAPACIDAD) VALUES(?,?);");

			for(int i=0;i<checkComboBoxTipoDiscapacidad.getCheckModel().getItemCount();i++){
				if(checkComboBoxTipoDiscapacidad.getCheckModel().isChecked(checkComboBoxTipoDiscapacidad.getCheckModel().getItem(i))){
					pstTipoDiscapacidad.setInt(1, idTemporal);
					pstTipoDiscapacidad.setInt(2, checkComboBoxTipoDiscapacidad.getCheckModel().getItem(i).getId());
					pstTipoDiscapacidad.addBatch();
				}
						
			}
			pstTipoDiscapacidad.executeBatch();

			labelInformacion.setText("Datos de Ayuda Tipo Discapacidad.. Inserci�n Correcta...");	

			
		

			
			conn.commit();
			labelInformacion.setText("Inserci�n Correcta...");	
		}catch(Exception e){
			
			e.printStackTrace();
		}finally{
	      	   try {
	      		   if(rsConsulta!=null){
	      			 rsConsulta.close();
	      		   }
	      		   	      		   
	      		   if(pstDomicilio!=null){
	      			 pstDomicilio.close();
					
	      	   		}
	      		   
	      		   if(pstPerDiscapacidad!=null){
	      			 pstPerDiscapacidad.close();
					
	      	   		}
	      		 if(pstDatosDiscapacidad!=null){
	      			pstDatosDiscapacidad.close();
					
	      	   		}
	      		if(pstLimitacion!=null){
	      			pstLimitacion.close();
					
	      	   		}
	      		if(pstAyudaBiomecanica!=null){
	      			pstAyudaBiomecanica.close();
					
	      	   		}
	      		if(pstTipoSEguro!=null){
	      			pstTipoSEguro.close();
					
	      	   		}
	      		if(pstTipoDiscapacidad!=null){
	      			pstTipoDiscapacidad.close();
					
	      	   		}
	      		   if(conn!=null){
	      	   			conn.close();
	      	   		}
	      	   
	      	   } catch (SQLException e) {
						
						e.printStackTrace();
					}
	      	   
	         }
		
	}
	*/

	public void insertarDatosPersonaDiscapacidad_v1(){
		labelInformacion.setVisible(true);
		ObservableList<String> styleClass = labelInformacion.getStyleClass();
        styleClass.removeAll(Collections.singleton("labelInformacionincorrecto"));  
        styleClass.removeAll(Collections.singleton("labelInformacioncorrecto"));  
   

		//int idTemporal=-1;
		int idTemporalDatosDomicilio=-1;
		int idTemporalPerDiscapacidad=-1;
		int idTemporalDatosDiscapacidad=-1;
		int idTemporalInscripcionConadis=-1;
		Connection conn=null;
		int rsInsercion=0;
		PreparedStatement pstDomicilio=null;
		PreparedStatement pstPerDiscapacidad=null;
		PreparedStatement pstDatosDiscapacidad=null;
		PreparedStatement pstLimitacion=null;
		PreparedStatement pstAyudaBiomecanica=null;
		PreparedStatement pstTipoSEguro=null;
		PreparedStatement pstTipoDiscapacidad=null;
		PreparedStatement pstProgramaSocial=null;
		PreparedStatement pstDatosCertificacion=null;
		PreparedStatement pstInscricpcion_conadis=null;
		PreparedStatement pstCertificadoMedico=null;
		PreparedStatement pstRequerimientoApoyo=null;
		PreparedStatement pstResolucion=null;
		PreparedStatement pstDiagnosticoCIE=null;
		
		PreparedStatement pstTieneConocimientoEn=null;
		PreparedStatement pstNecesitaAprender=null;
		
		
		ResultSet rsConsulta=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			conn.setAutoCommit(false);
			pstDomicilio=conn.prepareStatement("INSERT INTO DOMICILIO(ID_DISTRITO, ID_TIPO_VIA, NOMBRE_VIA,NRO,EDIFICIO,DPTO_INT,MZ,LOTE,ETAPA,URB_AAHH_CP_PJ_OTRO, ID_TIPO_PROCEDENCIA) VALUES "
					+ "										(?,?,?,?,?,?,?,?,?,?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
			//codigo de insercion
			if(comboBoxDistrito.getSelectionModel().getSelectedItem()!=null){
				pstDomicilio.setInt(1, comboBoxDistrito.getSelectionModel().getSelectedItem().getId());
			}else{
				pstDomicilio.setNull(1, Types.INTEGER);
			}
			if(comboBoxTipoVia.getSelectionModel().getSelectedItem()!=null){
				pstDomicilio.setInt(2, comboBoxTipoVia.getSelectionModel().getSelectedItem().getId());
			}else{
				pstDomicilio.setNull(2, Types.INTEGER);
			}
			if(!textFieldNombreVia.getText().trim().isEmpty()){
				pstDomicilio.setString(3, textFieldNombreVia.getText().trim());
			}else{
				pstDomicilio.setNull(3, Types.VARCHAR);
			}
			if(!textFieldNroDatosDomicilio.getText().trim().isEmpty()){
				pstDomicilio.setString(4, (textFieldNroDatosDomicilio.getText().trim()));
			}else {
				pstDomicilio.setNull(4, Types.VARCHAR);;
			}
			if(!textFieldEdificioDatosDomicilio.getText().trim().isEmpty()){
				pstDomicilio.setString(5, textFieldEdificioDatosDomicilio.getText().trim());
			}else {
				pstDomicilio.setNull(5, Types.VARCHAR);
			}
			if(!textFieldDpto_IntDatosDomicilio.getText().trim().isEmpty()){
				pstDomicilio.setString(6, textFieldDpto_IntDatosDomicilio.getText().trim());
			}else{
				pstDomicilio.setNull(6,Types.VARCHAR);
			}
			
			if(!textFieldMzDatosDomicilio.getText().trim().isEmpty()){
				pstDomicilio.setString(7, textFieldMzDatosDomicilio.getText().trim());
			}else{
				pstDomicilio.setNull(7, Types.VARCHAR);
			}
			
			if(!textFieldLoteDatosDomicilio.getText().trim().isEmpty()){
				pstDomicilio.setString(8, textFieldLoteDatosDomicilio.getText().trim());
			}else {
				pstDomicilio.setNull(8, Types.VARCHAR);
			}
			
			if(!textFieldEtapaDatosDomicilio.getText().trim().isEmpty()){
				pstDomicilio.setString(9, textFieldEtapaDatosDomicilio.getText().trim());
			}else{
				pstDomicilio.setNull(9, Types.VARCHAR);
			}
			
			if(!textFieldUrb_AAHH_CP_PJ_OtroDatosDomicilio.getText().trim().isEmpty()){
				pstDomicilio.setString(10, textFieldUrb_AAHH_CP_PJ_OtroDatosDomicilio.getText().trim());				
			}else{
				pstDomicilio.setNull(10, Types.VARCHAR);
			}
			if(comboBoxProcedencia.getSelectionModel().getSelectedItem()!=null){
				pstDomicilio.setInt(11, comboBoxProcedencia.getSelectionModel().getSelectedItem().getId());
			}else{
				pstDomicilio.setNull(11, Types.INTEGER);
			}
			rsInsercion=pstDomicilio.executeUpdate();
			if(rsInsercion==1){
				labelInformacion.setText("Datos de Domicilio.. Inserci�n Correcta...");	
			}
			
			
			rsConsulta=pstDomicilio.getGeneratedKeys();
			
			while(rsConsulta.next()){
				idTemporalDatosDomicilio=rsConsulta.getInt(1);
				System.out.println(idTemporalDatosDomicilio);
			}
			
			rsConsulta.close();
			
			//datos de personales
			
			pstPerDiscapacidad=conn.prepareStatement("INSERT INTO PER_DISCAPACIDAD(NOMBRES, APELL_PATERNO,APELL_MATERNO,DNI,CUI,FECHA_NACIMIENTO,SEXO,ID_NIVEL_EDUCATIVO,ID_TIPO_TRABAJO,ID_DOMICILIO,CREADO_POR,MODIFICADO_POR, "
					+ "ID_ESTADO_CIVIL,EMAIL, NUM_CONTACTO,OBSERVACION, UTLIMO_A�O_NIVEL_EDUCATIVO,OCUPACION_DESEMPENNA, ID_CLASIFICACION_SISFOH) "
					+ "		   VALUES(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);", PreparedStatement.RETURN_GENERATED_KEYS);

			pstPerDiscapacidad.setString(1, textFieldApellNombres.getText().trim());
			pstPerDiscapacidad.setString(2, textFieldApellPaterno.getText().trim());
			pstPerDiscapacidad.setString(3, textFieldApellMaterno.getText().trim());
			if(RadioButtonDNI.isSelected()){
				pstPerDiscapacidad.setString(4, textFieldDocId.getText().trim());
				pstPerDiscapacidad.setNull(5, java.sql.Types.VARCHAR);
			}else if(RadioButtonCUI.isSelected()){
				pstPerDiscapacidad.setNull(4, java.sql.Types.VARCHAR);
				pstPerDiscapacidad.setString(5, textFieldDocId.getText().trim());		
			}else if (RadioButtonS_D.isSelected()){
				pstPerDiscapacidad.setNull(4, java.sql.Types.VARCHAR);
				pstPerDiscapacidad.setNull(5, java.sql.Types.VARCHAR);
			}
			java.util.Date date =java.util.Date.from(datePickerFechaNacimiento.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				java.sql.Date sqlDate = new java.sql.Date(date.getTime());
				pstPerDiscapacidad.setDate(6, sqlDate);
			
			
			int idSexoSeleccionado=-1;
			for(int i=0;i<hBoxSexo.getChildren().size();i++){
				CheckBoxSexo checBoxSexo=(CheckBoxSexo)hBoxSexo.getChildren().get(i);
				if(checBoxSexo.isSelected()){
					System.out.println(checBoxSexo.getNombre());
					idSexoSeleccionado=checBoxSexo.getId_();
					break;
				}
			}
			
			pstPerDiscapacidad.setInt(7,idSexoSeleccionado);
			if(comboBoxNivelEducativo.getSelectionModel().getSelectedItem()!=null){
				pstPerDiscapacidad.setInt(8, comboBoxNivelEducativo.getSelectionModel().getSelectedItem().getId());
			}else{
				pstPerDiscapacidad.setNull(8, java.sql.Types.INTEGER);
			}
			if(comboBoxTipoTrabajo.getSelectionModel().getSelectedItem()!=null){
				pstPerDiscapacidad.setInt(9, comboBoxTipoTrabajo.getSelectionModel().getSelectedItem().getId());
			}else{
				pstPerDiscapacidad.setNull(9, java.sql.Types.INTEGER);
			}
			
			pstPerDiscapacidad.setInt(10, idTemporalDatosDomicilio );
			pstPerDiscapacidad.setString(11, Sesion.DNI);
			pstPerDiscapacidad.setString(12, Sesion.DNI);
			
			//nuevas modificaciones 
			
			if(comboBoxEstadoCivil.getSelectionModel().getSelectedItem()!=null){
				pstPerDiscapacidad.setInt(13, comboBoxEstadoCivil.getSelectionModel().getSelectedItem().getId());
			}else{
				pstPerDiscapacidad.setNull(13, java.sql.Types.INTEGER);
			}
			
			
			if(!textFieldEmail.getText().trim().isEmpty()){
				pstPerDiscapacidad.setString(14,textFieldEmail.getText().trim());
			}else{
				pstPerDiscapacidad.setNull(14,java.sql.Types.VARCHAR);

			}
			if(!textFieldApellNumContacto.getText().trim().isEmpty()){
				pstPerDiscapacidad.setString(15, textFieldApellNumContacto.getText().trim() );
			}else{
				pstPerDiscapacidad.setNull(15, java.sql.Types.VARCHAR);
			}
			if(!textAreaObervacionDatosPersonales.getText().trim().isEmpty()){
				pstPerDiscapacidad.setString(16, textAreaObervacionDatosPersonales.getText().trim());
			}else{
				pstPerDiscapacidad.setNull(16,java.sql.Types.VARCHAR);
			}
			
			if(!textFiedUltimoAnio.getText().trim().isEmpty()){
				pstPerDiscapacidad.setInt(17, Integer.parseInt(textFiedUltimoAnio.getText().trim()));
			}else{
				pstPerDiscapacidad.setNull(17, java.sql.Types.INTEGER);
			}
			
			if(!textFieldOcupacionDatosPersonales.getText().trim().isEmpty()){
				pstPerDiscapacidad.setString(18,textFieldOcupacionDatosPersonales.getText().trim());
			}else{
				pstPerDiscapacidad.setNull(18, java.sql.Types.VARCHAR);
			}
			//moficaciones 2018
			
			if(comboBoxClasificacionSisfoh.getSelectionModel().getSelectedItem()!=null){
				pstPerDiscapacidad.setInt(19, comboBoxClasificacionSisfoh.getSelectionModel().getSelectedItem().getId());
			}else{
				pstPerDiscapacidad.setNull(19, java.sql.Types.NULL);
			}
			
			//s
			rsInsercion=pstPerDiscapacidad.executeUpdate();
			if(rsInsercion==1){
				labelInformacion.setText("Datos de Persona.. Inserci�n Correcta...");	
			}
			
			
			rsConsulta=pstPerDiscapacidad.getGeneratedKeys();			
			while(rsConsulta.next()){
				idTemporalPerDiscapacidad=rsConsulta.getInt(1);
				System.out.println(idTemporalPerDiscapacidad);
			}
			rsConsulta.close();

			//datos detalle perDiscapacidad-programa social
			pstProgramaSocial=conn.prepareStatement("INSERT INTO D_PROGRAMA_SOCIAL_PER_DISCAPACIDAD (ID_PER_DISCAPACIDAD,ID_PROGRAMA_SOCIAL) VALUES(?,?);");

			for(int i=0;i<checkComboBoxProgramaSocial.getCheckModel().getItemCount();i++){
				if(checkComboBoxProgramaSocial.getCheckModel().isChecked(checkComboBoxProgramaSocial.getCheckModel().getItem(i))){
					pstProgramaSocial.setInt(1, idTemporalPerDiscapacidad);
					pstProgramaSocial.setInt(2, checkComboBoxProgramaSocial.getCheckModel().getItem(i).getId());
					pstProgramaSocial.addBatch();
				}
						
			}
			pstProgramaSocial.executeBatch();

			labelInformacion.setText("Datos de Programa Social.. Inserci�n Correcta...");	
			//modificaciones 2018
			
			//TieneConocimiento en 
			pstTieneConocimientoEn=conn.prepareStatement("INSERT INTO D_TIENE_CONOCIMIENTO_EN_PER_DISCAPACIDAD (ID_PER_DISCAPACIDAD,ID_TIENE_CONOCIMIENTO_EN) VALUES(?,?);");

			for(int i=0;i<checkComboBoxTieneConocimientoEn.getCheckModel().getItemCount();i++){
				if(checkComboBoxTieneConocimientoEn.getCheckModel().isChecked(checkComboBoxTieneConocimientoEn.getCheckModel().getItem(i))){
					pstTieneConocimientoEn.setInt(1, idTemporalPerDiscapacidad);
					pstTieneConocimientoEn.setInt(2, checkComboBoxTieneConocimientoEn.getCheckModel().getItem(i).getId());
					pstTieneConocimientoEn.addBatch();
				}
						
			}
			pstTieneConocimientoEn.executeBatch();

			labelInformacion.setText("Datos de Tiene Conocimiento En.. Inserci�n Correcta...");	

			//Necesita aprender
			pstNecesitaAprender=conn.prepareStatement("INSERT INTO D_NECESITA_APRENDER_PER_DISCAPACIDAD (ID_PER_DISCAPACIDAD,ID_NECESITA_APRENDER) VALUES(?,?);");

			for(int i=0;i<checkComboBoxNecesitaAprender.getCheckModel().getItemCount();i++){
				if(checkComboBoxNecesitaAprender.getCheckModel().isChecked(checkComboBoxNecesitaAprender.getCheckModel().getItem(i))){
					pstNecesitaAprender.setInt(1, idTemporalPerDiscapacidad);
					pstNecesitaAprender.setInt(2, checkComboBoxNecesitaAprender.getCheckModel().getItem(i).getId());
					pstNecesitaAprender.addBatch();
				}
						
			}
			pstNecesitaAprender.executeBatch();

			labelInformacion.setText("Datos de Necesita Aprender.. Inserci�n Correcta...");	

			
			//Datos de Incripcion
			
			pstInscricpcion_conadis=conn.prepareStatement("INSERT INTO INSCRIPCION_CONADIS_PASCO (ID_TIPO_DE_TRAMITE,MOTIVO,FECHA_REGISTRO_CONADIS,ID_PER_DISCAPACIDAD,"+
			"ID_TIENE_CERTIFICADO_DISCAPACIDAD,ID_TIENE_INSCRIPCION_CONADIS,NRO_CARNE,ANNO_INCRIPCCION,CREADO_POR,MODIFICADO_POR)"+
			"VALUES(?,?,?,?,?,?,?,?,?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
			if(comboBoxTipoTramite.getSelectionModel().getSelectedItem()!=null){
				pstInscricpcion_conadis.setInt(1, comboBoxTipoTramite.getSelectionModel().getSelectedItem().getId());
			}else{
				pstInscricpcion_conadis.setNull(1, java.sql.Types.INTEGER);
			}
			if(!textAreaMotivoInscripcionConadis.getText().trim().isEmpty()){
				pstInscricpcion_conadis.setString(2, textAreaMotivoInscripcionConadis.getText().trim());
			}else{
				pstInscricpcion_conadis.setNull(2, java.sql.Types.VARCHAR);
			}
			
			

		
				
			
			if(datePickerRegistroConadis.getValue()!=null){
				java.util.Date dateRegistroConadis =java.util.Date.from(datePickerRegistroConadis.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				java.sql.Date sqlDateRegistroConadis = new java.sql.Date(dateRegistroConadis.getTime());
				pstInscricpcion_conadis.setDate(3,sqlDateRegistroConadis);
			}else{
				pstInscricpcion_conadis.setNull(3,java.sql.Types.DATE);
			}
			pstInscricpcion_conadis.setInt(4, idTemporalPerDiscapacidad);
			
			int idTieneCertificadoDiscapacidadSeleccionado=-1;
			for(int i=0;i<hBoxTieneCertificadoDiscapacidad.getChildren().size();i++){
				CheckBoxTieneCertificadoDiscapacidad checBoxCertificadoDiscapacidad=(CheckBoxTieneCertificadoDiscapacidad)hBoxTieneCertificadoDiscapacidad.getChildren().get(i);
				if(checBoxCertificadoDiscapacidad.isSelected()){
					System.out.println(checBoxCertificadoDiscapacidad.getNombre());
					idTieneCertificadoDiscapacidadSeleccionado=checBoxCertificadoDiscapacidad.getId_();
					break;
				}
			}
			
			pstInscricpcion_conadis.setInt(5, idTieneCertificadoDiscapacidadSeleccionado);
			int idTieneIncripcionConadis=-1;
			for(int i=0;i<hBoxTieneInscripcionConadis.getChildren().size();i++){
				CheckBoxTieneInscripcionConadis checBoxIncripcionConadis=(CheckBoxTieneInscripcionConadis)hBoxTieneInscripcionConadis.getChildren().get(i);
				if(checBoxIncripcionConadis.isSelected()){
					System.out.println(checBoxIncripcionConadis.getNombre());
					idTieneIncripcionConadis=checBoxIncripcionConadis.getId_();
					break;
				}
			}
			pstInscricpcion_conadis.setInt(6,idTieneIncripcionConadis);
			if(!textFieldNumeroCarne.getText().trim().isEmpty()){
				pstInscricpcion_conadis.setString(7, textFieldNumeroCarne.getText().trim());
			}else{
				pstInscricpcion_conadis.setNull(7, java.sql.Types.VARCHAR);
			}
			
			if(datePickerRegistroConadisNacional.getValue()!=null){
				java.util.Date dateRegistroConadis =java.util.Date.from(datePickerRegistroConadisNacional.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				java.sql.Date sqlDateRegistroConadis = new java.sql.Date(dateRegistroConadis.getTime());
				pstInscricpcion_conadis.setDate(8,sqlDateRegistroConadis);
			}else{
				pstInscricpcion_conadis.setNull(8, java.sql.Types.DATE);
			}
			
			
			pstInscricpcion_conadis.setString(9,Sesion.DNI);
			pstInscricpcion_conadis.setString(10, Sesion.DNI);
			
				
			rsInsercion=pstInscricpcion_conadis.executeUpdate();
			if(rsInsercion==1){
				labelInformacion.setText("Datos de Incripcion.. Inserci�n Correcta...");	
			}
			
			
			
			rsConsulta=pstInscricpcion_conadis.getGeneratedKeys();			
			while(rsConsulta.next()){
				idTemporalInscripcionConadis=rsConsulta.getInt(1);
				System.out.println(idTemporalInscripcionConadis);
			}
			rsConsulta.close();
			
			
			pstCertificadoMedico=conn.prepareStatement("INSERT INTO CERTIFICADO_MEDICO(ID,NUMERO_CERTIFICADO,FECHA_CERTIFICADO,PORCENTAJE_RES_PARTI,PORCENTAJE_RES_PARTI_DIFERIDO,"+
			"ID_NIVEL_GRAVEDAD_LIMITACION,ID_MEDICO,ID_ESTABLECIMIENTO_SALUD,ID_DIRECTOR,OBSERVACIONES_CERTIFICADO_MEDICO,DIAGNOSTICO_MEDICO,"+
			"ID_ORIGEN_LIMITACION, OBSERVACIONES_OTROS,CREADO_POR,MODIFICADO_POR)"+
			"VALUES(?,?, ?,?,?,?,?,?,?,?,?,?,?,?,?);");
			pstCertificadoMedico.setInt(1,idTemporalInscripcionConadis);
			if(!textFieldNroCertificado.getText().trim().isEmpty()){
				pstCertificadoMedico.setString(2, textFieldNroCertificado.getText().trim());
			}else{
				pstCertificadoMedico.setNull(2, java.sql.Types.VARCHAR);
			}
			
			if(datePickerFechaEmisionCertificado.getValue()!=null){
				java.util.Date dateEmisionCertificado =java.util.Date.from(datePickerFechaEmisionCertificado.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				java.sql.Date sqlDateEmisionCertificado = new java.sql.Date(dateEmisionCertificado.getTime());
				pstCertificadoMedico.setDate(3,sqlDateEmisionCertificado);
			}else{
				pstCertificadoMedico.setNull(3, java.sql.Types.DATE);
			}
			
			if(!textFieldRestrincionParticipacion.getText().trim().isEmpty()){
				pstCertificadoMedico.setDouble(4,Double.parseDouble(textFieldRestrincionParticipacion.getText()));
			}else{
				pstCertificadoMedico.setNull(4, java.sql.Types.DOUBLE);				
			}
			
			if(!textFieldRestriccionDiferido.getText().trim().isEmpty()){
				pstCertificadoMedico.setString(5, textFieldRestriccionDiferido.getText().trim());
			}else{
				pstCertificadoMedico.setNull(5, java.sql.Types.VARCHAR);
			}
			
			if(comboBoxNivelGravedad.getSelectionModel().getSelectedItem()!=null){
				pstCertificadoMedico.setInt(6,comboBoxNivelGravedad.getSelectionModel().getSelectedItem().getId());
			}else{
				pstCertificadoMedico.setNull(6,java.sql.Types.INTEGER);
			}
			
			if(comboBoxMedicoCertificador.getSelectionModel().getSelectedItem()!=null){
				pstCertificadoMedico.setInt(7, comboBoxMedicoCertificador.getSelectionModel().getSelectedItem().getId());
			}else{
				pstCertificadoMedico.setNull(7, java.sql.Types.INTEGER);
			}
			
			if(comboBoxEstablecimientoSalud.getSelectionModel().getSelectedItem()!=null){
				pstCertificadoMedico.setInt(8,comboBoxEstablecimientoSalud.getSelectionModel().getSelectedItem().getId() );
			}else{
				pstCertificadoMedico.setNull(8, java.sql.Types.INTEGER);
			}
			if(comboBoxDirectorEstablecimiento.getSelectionModel().getSelectedItem()!=null){
				pstCertificadoMedico.setInt(9, comboBoxDirectorEstablecimiento.getSelectionModel().getSelectedItem().getId());
			}else{
				pstCertificadoMedico.setNull(9, java.sql.Types.INTEGER);
			}
			if(!textAreaObservacionesCertificacion.getText().trim().isEmpty()){
				pstCertificadoMedico.setString(10,textAreaObservacionesCertificacion.getText().trim());
			}else{
				pstCertificadoMedico.setNull(10, java.sql.Types.VARCHAR);
			} 
			if(!textFieldDiagnosticoMedico.getText().trim().isEmpty()){
				pstCertificadoMedico.setString(11, textFieldDiagnosticoMedico.getText().trim());
			}else{
				pstCertificadoMedico.setNull(11, java.sql.Types.VARCHAR);
			}
			if(comboBoxOrigenLimitacion.getSelectionModel().getSelectedItem()!=null){
				pstCertificadoMedico.setInt(12, comboBoxOrigenLimitacion.getSelectionModel().getSelectedItem().getId());
			}else{
				pstCertificadoMedico.setNull(12, java.sql.Types.INTEGER);
			}
			
			pstCertificadoMedico.setNull(13, java.sql.Types.VARCHAR);
			pstCertificadoMedico.setString(14, Sesion.DNI);
			pstCertificadoMedico.setString(15, Sesion.DNI);
			
			
			rsInsercion=pstCertificadoMedico.executeUpdate();
			if(rsInsercion==1){
				labelInformacion.setText("Datos de Certificado Medico.. Inserci�n Correcta...");	
			}
			
			
			
			
			pstLimitacion=conn.prepareStatement("INSERT INTO D_LIMITACION_PERMANENTE_PARA_CERTIFICADO_MEDICO(ID_CERTIFICADO_MEDICO,ID_LIMITACION_PERMANENTE_PARA) VALUES (?,?)");
			for(int i=0;i<chekcComboBoxLimitacionPermanente.getCheckModel().getItemCount();i++){
				if(chekcComboBoxLimitacionPermanente.getCheckModel().isChecked(chekcComboBoxLimitacionPermanente.getCheckModel().getItem(i))){
					pstLimitacion.setInt(1, idTemporalInscripcionConadis);
					pstLimitacion.setInt(2, chekcComboBoxLimitacionPermanente.getCheckModel().getItem(i).getId());
					pstLimitacion.addBatch();
	
				}
						
			}
			
			pstLimitacion.executeBatch();
			labelInformacion.setText("Datos de Limitacion.. Inserci�n Correcta...");	
			
			pstAyudaBiomecanica=conn.prepareStatement("INSERT INTO D_TIPO_AYUDA_BIOMECANICA_CERTIFICADO_MEDICO(ID_CERTIFICADO_MEDICO,ID_TIPO_AYUDA_BIOMECANICA ) VALUES (?,?);");

			for(int i=0;i<checkComboBoxTipoAyudaBiometrica.getCheckModel().getItemCount();i++){
				if(checkComboBoxTipoAyudaBiometrica.getCheckModel().isChecked(checkComboBoxTipoAyudaBiometrica.getCheckModel().getItem(i))){
					
					pstAyudaBiomecanica.setInt(1, idTemporalInscripcionConadis);
					pstAyudaBiomecanica.setInt(2, checkComboBoxTipoAyudaBiometrica.getCheckModel().getItem(i).getId());
					pstAyudaBiomecanica.addBatch();
					
				}
						
			}
			
			pstAyudaBiomecanica.executeBatch();
			
			labelInformacion.setText("Datos de Ayuda Biomecanica.. Inserci�n Correcta...");	
			
			pstTipoSEguro=conn.prepareStatement("INSERT INTO D_TTIPO_SEGURO_CERTIFICADO_MEDICO(ID_CERTIFICADO_MEDICO, ID_TIPO_SEGURO) VALUES(?, ?);");

			for(int i=0;i<checkComboBoxTipoSeguroMedico.getCheckModel().getItemCount();i++){
				if(checkComboBoxTipoSeguroMedico.getCheckModel().isChecked(checkComboBoxTipoSeguroMedico.getCheckModel().getItem(i))){
					pstTipoSEguro.setInt(1, idTemporalInscripcionConadis);
					pstTipoSEguro.setInt(2, checkComboBoxTipoSeguroMedico.getCheckModel().getItem(i).getId());
					pstTipoSEguro.addBatch();
				}
						
			}
			pstTipoSEguro.executeBatch();
			labelInformacion.setText("Datos de Ayuda Tipo Seguro.. Inserci�n Correcta...");	


			pstTipoDiscapacidad=conn.prepareStatement("INSERT INTO D_TIPO_DISCAPACIDAD_CERTIFICADO_MEDICO (ID_CERTIFICADO_MEDICO,ID_TIPO_DISCAPACIDAD) VALUES(?,?);");

			for(int i=0;i<checkComboBoxTipoDiscapacidad.getCheckModel().getItemCount();i++){
				if(checkComboBoxTipoDiscapacidad.getCheckModel().isChecked(checkComboBoxTipoDiscapacidad.getCheckModel().getItem(i))){
					pstTipoDiscapacidad.setInt(1, idTemporalInscripcionConadis);
					pstTipoDiscapacidad.setInt(2, checkComboBoxTipoDiscapacidad.getCheckModel().getItem(i).getId());
					pstTipoDiscapacidad.addBatch();
				}
						
			}
			pstTipoDiscapacidad.executeBatch();

			labelInformacion.setText("Datos de Ayuda Tipo Discapacidad.. Inserci�n Correcta...");	
			
			
			pstRequerimientoApoyo=conn.prepareStatement("INSERT INTO D_REQUERIMIENTO_APOYO_CERTIFICADO_MEDICO (ID_CERTIFICADO_MEDICO,ID_REQUERIMIENTO_APOYO) VALUES(?,?);");

			for(int i=0;i<chekcComboBoxRequerimientoApoyo.getCheckModel().getItemCount();i++){
				if(chekcComboBoxRequerimientoApoyo.getCheckModel().isChecked(chekcComboBoxRequerimientoApoyo.getCheckModel().getItem(i))){
					pstRequerimientoApoyo.setInt(1, idTemporalInscripcionConadis);
					pstRequerimientoApoyo.setInt(2, chekcComboBoxRequerimientoApoyo.getCheckModel().getItem(i).getId());
					pstRequerimientoApoyo.addBatch();
				}
						
			}
			pstRequerimientoApoyo.executeBatch();

			labelInformacion.setText("Datos de Requerimiento de Apoyo.. Inserci�n Correcta...");	
			
			if(!textFieldDiagnosticoMedicoCie.getText().trim().isEmpty()){
				
				pstDiagnosticoCIE=conn.prepareStatement("INSERT INTO D_DIAGNOSTICO_DANNO_CUATRO_CERTIFICADO_MEDICO (ID_DIAGNOSTICO_DANNO_CUATRO,ID_CERTIFICADO_MEDICO)VALUES(?,?);");
				String[] diagnosticoCIE=textFieldDiagnosticoMedicoCie.getText().trim().split(",");
				for(int i=0;i<diagnosticoCIE.length;i++){
					pstDiagnosticoCIE.setString(1, diagnosticoCIE[i]);
					pstDiagnosticoCIE.setInt(2, idTemporalInscripcionConadis);
					pstDiagnosticoCIE.addBatch();
				
						
				}
				pstDiagnosticoCIE.executeBatch();

				labelInformacion.setText("Datos de Diagnostico CIE.. Inserci�n Correcta...");	
			
			}
			
			pstResolucion=conn.prepareStatement("INSERT INTO RESOLUCION (ID,NRO_RESOLUCION,SE_RESUELVE,FECHA,FIRMA,DOCUMENTO_FISICO,CREADO_POR,MODIFICADO_POR)"+ 
			"VALUES(?,?,?,?,?,?,?,? );");
			pstResolucion.setInt(1, idTemporalInscripcionConadis);
			if(!textFieldNumeroResolucion.getText().trim().isEmpty()){
				pstResolucion.setString(2, textFieldNumeroResolucion.getText().trim());
			}else{
				pstResolucion.setNull(2, java.sql.Types.VARCHAR);
			}
			
			if(!textFieldSeResuelve.getText().trim().isEmpty()){
				pstResolucion.setString(3, textFieldSeResuelve.getText().trim());

			}else{
				pstResolucion.setNull(3, java.sql.Types.VARCHAR);
			}
			
			if(datePickerFechaEmisionResolcuion.getValue()!=null){
				java.util.Date dateEmisionResolucion =java.util.Date.from(datePickerFechaEmisionResolcuion.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				java.sql.Date sqlDateEmisionResolucion = new java.sql.Date(dateEmisionResolucion.getTime());
				pstResolucion.setDate(4,sqlDateEmisionResolucion);
			}else {
				pstResolucion.setNull(4, java.sql.Types.NULL);
			}
			
			pstResolucion.setNull(5, java.sql.Types.VARCHAR);
			pstResolucion.setNull(6, java.sql.Types.BLOB);
			pstResolucion.setString(7, Sesion.DNI);
			pstResolucion.setString(8, Sesion.DNI);
			
			rsInsercion=pstResolucion.executeUpdate();
			if(rsInsercion==1){
				labelInformacion.setText("Datos de Resoluci�n.. Inserci�n Correcta...");	
			}
			
			
			/*
			//datos discapacidad	
			
							
			pstDatosDiscapacidad=conn.prepareStatement("INSERT INTO CERTIFICADO_MEDICO(ID_NIVEL_GRAVEDAD_LIMITACION,ID_ORIGEN_LIMITACION,CREADO_POR,MODIFICADO_POR,DIAGNOSTICO_MEDICO ) "
					+ "												VALUES (?,?,?,?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstDatosDiscapacidad.setInt(1, comboBoxNivelGravedad.getSelectionModel().getSelectedItem().getId());
			pstDatosDiscapacidad.setInt(2, comboBoxOrigenLimitacion.getSelectionModel().getSelectedItem().getId());
			pstDatosDiscapacidad.setString(3, Sesion.DNI);
			pstDatosDiscapacidad.setString(4, Sesion.DNI);
			if(textFieldDiagnosticoMedico.getText().trim().isEmpty()){
				pstDatosDiscapacidad.setString(5,textFieldDiagnosticoMedico.getText().trim());
			}else{
				pstDatosDiscapacidad.setNull(5, java.sql.Types.NULL);
			}
			
			
			rsInsercion=pstDatosDiscapacidad.executeUpdate();
			if(rsInsercion==1){
				labelInformacion.setText("Datos de Discapacidad.. Inserci�n Correcta...");	
			}
			
		
			rsConsulta=pstDatosDiscapacidad.getGeneratedKeys();			
			while(rsConsulta.next()){
				idTemporalDatosDiscapacidad=rsConsulta.getInt(1);
			}
			rsConsulta.close();
			
			
			pstLimitacion=conn.prepareStatement("INSERT INTO D_LIMITACION_PERMANENTE_PARA_CERTIFICADO_MEDICO(ID_DATOS_DISCAPACIDAD,ID_LIMITACION_PERMANENTE_PARA) VALUES (?,?)");
			for(int i=0;i<chekcComboBoxLimitacionPermanente.getCheckModel().getItemCount();i++){
				if(chekcComboBoxLimitacionPermanente.getCheckModel().isChecked(chekcComboBoxLimitacionPermanente.getCheckModel().getItem(i))){
					pstLimitacion.setInt(1, idTemporalDatosDiscapacidad);
					pstLimitacion.setInt(2, chekcComboBoxLimitacionPermanente.getCheckModel().getItem(i).getId());
					pstLimitacion.addBatch();
	
				}
						
			}
			
			pstLimitacion.executeBatch();
			labelInformacion.setText("Datos de Limitacion.. Inserci�n Correcta...");	
			
			pstAyudaBiomecanica=conn.prepareStatement("INSERT INTO D_TIPO_AYUDA_BIOMECANICA_CERTIFICADO_MEDICO(ID_CERTIFICADO_MEDICO,ID_TIPO_AYUDA_BIOMECANICA ) VALUES (?,?);");

			for(int i=0;i<checkComboBoxTipoAyudaBiometrica.getCheckModel().getItemCount();i++){
				if(checkComboBoxTipoAyudaBiometrica.getCheckModel().isChecked(checkComboBoxTipoAyudaBiometrica.getCheckModel().getItem(i))){
					
					pstAyudaBiomecanica.setInt(1, idTemporalDatosDiscapacidad);
					pstAyudaBiomecanica.setInt(2, checkComboBoxTipoAyudaBiometrica.getCheckModel().getItem(i).getId());
					pstAyudaBiomecanica.addBatch();
					
				}
						
			}
			
			pstAyudaBiomecanica.executeBatch();
			
			labelInformacion.setText("Datos de Ayuda Biomecanica.. Inserci�n Correcta...");	
			
			pstTipoSEguro=conn.prepareStatement("INSERT INTO D_TTIPO_SEGURO_CERTIFICADO_MEDICO(ID_CERTIFICADO_MEDICO, ID_TIPO_SEGURO) VALUES(?, ?);");

			for(int i=0;i<checkComboBoxTipoSeguroMedico.getCheckModel().getItemCount();i++){
				if(checkComboBoxTipoSeguroMedico.getCheckModel().isChecked(checkComboBoxTipoSeguroMedico.getCheckModel().getItem(i))){
					pstTipoSEguro.setInt(1, idTemporalDatosDiscapacidad);
					pstTipoSEguro.setInt(2, checkComboBoxTipoSeguroMedico.getCheckModel().getItem(i).getId());
					pstTipoSEguro.addBatch();
				}
						
			}
			pstTipoSEguro.executeBatch();
			labelInformacion.setText("Datos de Ayuda Tipo Seguro.. Inserci�n Correcta...");	


			pstTipoDiscapacidad=conn.prepareStatement("INSERT INTO D_TIPO_DISCAPACIDAD_CERTIFICADO_MEDICO (ID_CERTIFICADO_MEDICO,ID_TIPO_DISCAPACIDAD) VALUES(?,?);");

			for(int i=0;i<checkComboBoxTipoDiscapacidad.getCheckModel().getItemCount();i++){
				if(checkComboBoxTipoDiscapacidad.getCheckModel().isChecked(checkComboBoxTipoDiscapacidad.getCheckModel().getItem(i))){
					pstTipoDiscapacidad.setInt(1, idTemporalDatosDiscapacidad);
					pstTipoDiscapacidad.setInt(2, checkComboBoxTipoDiscapacidad.getCheckModel().getItem(i).getId());
					pstTipoDiscapacidad.addBatch();
				}
						
			}
			pstTipoDiscapacidad.executeBatch();

			labelInformacion.setText("Datos de Ayuda Tipo Discapacidad.. Inserci�n Correcta...");	

			
			//Datos certificacion
			
			pstDatosCertificacion=conn.prepareStatement("INSERT INTO CERTIFICADO_MEDICO(NUMERO_CERTIFICADO,FECHA_CERTIFICADO, PORCENTAJE_RES_PARTI,PORCENTAJE_RES_PARTI_DIFERIDO, ID_NIVEL_GRAVEDAD_LIMITACION,ID_MEDICO,ID_ESTABLECIMIENTO_SALUD,ID_DIRECTOR,"+
					" OBSERVACIONES) VALUES ('67798', '12/12/12', '78','89',2,1,1,1,NULL);", PreparedStatement.RETURN_GENERATED_KEYS);
			
			pstDatosCertificacion.setString(1, textFieldNroCertificado.getText().trim());

			if(datePickerFechaEmisionCertificado.getValue()!=null){
				java.util.Date date1 =java.util.Date.from(datePickerFechaEmisionCertificado.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
				java.sql.Date  sqlDate1 = new java.sql.Date(date1.getTime());
				pstDatosCertificacion.setDate(2, sqlDate1);
			}else{
				pstDatosCertificacion.setNull(2, java.sql.Types.DATE);
				
			}*/
			
			conn.commit();
			labelInformacion.setText("El usuario "+textFieldApellNombres.getText()+", "+
			textFieldApellPaterno.getText()+" "+textFieldApellMaterno.getText()+" se ha registrado correctamente.");	
			   
			
			   if (! styleClass.contains("labelInformacioncorrecto")) {
	                styleClass.add("labelInformacioncorrecto");
	              
	            }
		}catch(Exception e){
			if (! styleClass.contains("labelInformacionincorrecto")) {
                styleClass.add("labelInformacionincorrecto");
              
            }
			labelInformacion.setText("Error :( ... "+e.getMessage());
			
		             

			
			e.printStackTrace();
		}finally{
	      	   try {
	      		   if(rsConsulta!=null){
	      			 rsConsulta.close();
	      		   }
	      		   	      		   
	      		   if(pstDomicilio!=null){
	      			 pstDomicilio.close();
					
	      	   		}
	      		   
	      		   if(pstPerDiscapacidad!=null){
	      			 pstPerDiscapacidad.close();
					
	      	   		}
	      		 if(pstDatosDiscapacidad!=null){
	      			pstDatosDiscapacidad.close();
					
	      	   		}
	      		if(pstLimitacion!=null){
	      			pstLimitacion.close();
					
	      	   		}
	      		if(pstAyudaBiomecanica!=null){
	      			pstAyudaBiomecanica.close();
					
	      	   		}
	      		if(pstTipoSEguro!=null){
	      			pstTipoSEguro.close();
					
	      	   		}
	      		if(pstTipoDiscapacidad!=null){
	      			pstTipoDiscapacidad.close();
					
	      	   		}
	      		
	      		if(pstProgramaSocial!=null){
	      			pstProgramaSocial.close();
					
	      	   		}
	      		
	      		if(pstDatosCertificacion!=null){
	      			pstDatosCertificacion.close();
					
	      	   		}
	      		if(pstInscricpcion_conadis!=null){
	      			pstInscricpcion_conadis.close();
					
	      	   		}
	      		if(pstCertificadoMedico!=null){
	      			pstCertificadoMedico.close();
					
	      	   		}
	    		if(pstRequerimientoApoyo!=null){
	    			pstRequerimientoApoyo.close();
					
	      	   		}
	      		
	    		if(pstResolucion!=null){
	    			pstResolucion.close();
					
	      	   		}
	      		
	    		if(pstDiagnosticoCIE!=null){
	    			pstDiagnosticoCIE.close();
					
	      	   		}
	    		
	    		if(pstNecesitaAprender!=null){
	    			pstNecesitaAprender.close();
					
	      	   		}
	    		if(pstTieneConocimientoEn!=null){
	    			pstTieneConocimientoEn.close();
					
	      	   		}
	      		
	      				
	      		
	    		
	      		   if(conn!=null){
	      	   			conn.close();
	      	   		}
	      	   
	      	   } catch (SQLException e) {
						
						e.printStackTrace();
					}
	      	   
	         }
		
	}
	
	public void mostrarComboBoxOrigenLimitacion(){
		comboBoxOrigenLimitacion.setItems(arrayOrigenLimitacion);
		comboBoxOrigenLimitacion.getItems().clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM ORIGEN_LIMITACION ORDER BY ID");
			
			
			rs=pst.executeQuery();
			while(rs.next()){
				comboBoxOrigenLimitacion.getItems().add(new OrigenLimitacion(rs.getInt("ID"), rs.getString("NOMBRE"), null));
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
	}

	public void mostrarDatosDiagnosticoEnfermedadesCIE() {
		mostrarDatosDiagnosticoEnfermedadesCIE(textFieldBuscarCie.getText().trim());
	}
	

	public void mostrarDatosDiagnosticoEnfermedadesCIE(String buscar) {
		// vamos limpiar el contenedor de los botones
		TableViewCie.getItems().clear();
		// hacer la conexcioon con la base de datos y obtener alas busquedas
		// encontradas
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement("select * from diagnostico_danno_cuatro where id like ? or nombre like ? ;");
			pst.setString(1, "%" + buscar + "%");
			pst.setString(2, "%" + buscar + "%");
			rs = pst.executeQuery();

			System.out.println("estoy aqui 1");
			int contador=0;
			while (rs.next()) {

				TableViewCie.getItems().add(new DiagnosticoEnfermedades_CIE(contador,rs.getString("ID"), rs.getString("NOMBRE")));
				contador++;
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
	
	
	//modifcaciones 2018
	public void mostrarCoboBoxClasificacionSisfoh(){
		comboBoxClasificacionSisfoh.setItems(arrayClasificacionSisfoh);
		comboBoxClasificacionSisfoh.getItems().clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM CLASIFICACION_SISFOH ORDER BY ID");
			
			
			rs=pst.executeQuery();
			while(rs.next()){
				comboBoxClasificacionSisfoh.getItems().add(new ClasificacionSisfoh(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	}
	
	
	
	public void mostrarCheckComboBoxTieneConocimientoEn(){
		checkComboBoxTieneConocimientoEn.getItems().clear();
		arrayTieneConocimiento.clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM TIENE_CONOCIMIENTO_EN  ORDER BY NOMBRE");
			
			rs=pst.executeQuery();
			while(rs.next()){
				
				arrayTieneConocimiento.add(new TieneConocimientoEn(rs.getInt("ID"), rs.getString("NOMBRE")));
				//checkComboBoxTipoDiscapacidad.getItems().add(new TipoDiscapacidad(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	
		checkComboBoxTieneConocimientoEn.getItems().addAll(arrayTieneConocimiento);
		
		
		

	}
	
	public void mostrarCheckComboBoxNecesitaAprender(){
		checkComboBoxNecesitaAprender.getItems().clear();
		arrayNecesitaAprender.clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM NECESITA_APRENDER  ORDER BY NOMBRE");
			
			rs=pst.executeQuery();
			while(rs.next()){
				
				arrayNecesitaAprender.add(new NecesitaAprender(rs.getInt("ID"), rs.getString("NOMBRE")));
				//checkComboBoxTipoDiscapacidad.getItems().add(new TipoDiscapacidad(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	
		checkComboBoxNecesitaAprender.getItems().addAll(arrayNecesitaAprender);
		
		
		

	}
	
	

}