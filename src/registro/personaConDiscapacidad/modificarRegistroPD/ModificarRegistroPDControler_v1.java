package registro.personaConDiscapacidad.modificarRegistroPD;


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
import javafx.fxml.Initializable;

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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import registro.personaConDiscapacidad.PersonaDiscapacidad;
import registro.personaConDiscapacidad.nuevoRegistroPD.CheckBoxSexo;
import registro.personaConDiscapacidad.nuevoRegistroPD.CheckBoxTieneCertificadoDiscapacidad;
import registro.personaConDiscapacidad.nuevoRegistroPD.CheckBoxTieneInscripcionConadis;
import registro.personaConDiscapacidad.nuevoRegistroPD.Departamento;
import registro.personaConDiscapacidad.nuevoRegistroPD.DiagnosticoEnfermedades_CIE;
import registro.personaConDiscapacidad.nuevoRegistroPD.DirectorEstablecimiento;
import registro.personaConDiscapacidad.nuevoRegistroPD.Distrito;
import registro.personaConDiscapacidad.nuevoRegistroPD.EstablecimientoSalud;
import registro.personaConDiscapacidad.nuevoRegistroPD.EstadoCivil;
import registro.personaConDiscapacidad.nuevoRegistroPD.LimitacionPermanente;
import registro.personaConDiscapacidad.nuevoRegistroPD.MedicoCertificador;
import registro.personaConDiscapacidad.nuevoRegistroPD.NivelEducativo;
import registro.personaConDiscapacidad.nuevoRegistroPD.NivelGravedad;
import registro.personaConDiscapacidad.nuevoRegistroPD.OrigenLimitacion;
import registro.personaConDiscapacidad.nuevoRegistroPD.Procedencia;
import registro.personaConDiscapacidad.nuevoRegistroPD.ProgramaSocial;
import registro.personaConDiscapacidad.nuevoRegistroPD.Provincia;
import registro.personaConDiscapacidad.nuevoRegistroPD.RequerimientoApoyo;
import registro.personaConDiscapacidad.nuevoRegistroPD.TipoAyudaBiomecanica;
import registro.personaConDiscapacidad.nuevoRegistroPD.TipoDiscapacidad;
import registro.personaConDiscapacidad.nuevoRegistroPD.TipoSeguroMedico;
import registro.personaConDiscapacidad.nuevoRegistroPD.TipoTrabajo;
import registro.personaConDiscapacidad.nuevoRegistroPD.TipoTramite;
import registro.personaConDiscapacidad.nuevoRegistroPD.TipoVia;
import sesion.Sesion;

public class ModificarRegistroPDControler_v1 extends Funciones implements Initializable {
	
	
	
	
	private int idDomicilioBaseDatos=-1;
	private int idPerDiscapacidad=-1;
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

    @FXML private ToggleButton toggleButtonInscripcionConadis;
    @FXML private ScrollPane scrollPaneDatosPersonales;
    @FXML private ToggleButton toggleButtonDatosPersonal;
    @FXML private ScrollPane scrollPaneDatosDiscapacidadCertificadoMedico;
    @FXML private ScrollPane scrollPaneDatosDomicilio;
    @FXML private ScrollPane  scrollPaneResolucionDiscapacidad;
    @FXML private ToggleButton toggleButtonDatosDomicilio;
    @FXML private ToggleButton toggleButtonDatosDiscapacidadCertificadoMedico;
    @FXML private ToggleButton toggleButtonResolucionDiscapacidad;

    
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
		
		
		mostrarCheckBoxTieneInscripcionConadis();
		
		agregarRestriccionCamposNoVacios();
		
		TableViewCie.setOnMouseReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				
				textFieldDiagnosticoMedicoCie.setText(textFieldDiagnosticoMedicoCie.getText().trim()+", "+TableViewCie.getSelectionModel().getSelectedItem().getCodCie());
			
/*				String [] vectoPalabras=textFieldDiagnosticoMedicoCie.getText().split(",");
				textFieldDiagnosticoMedicoCie.setText(vectoPalabras[0]);
			
				for(int i=1;i<=vectoPalabras.length;i++){
					textFieldDiagnosticoMedicoCie.setText(textFieldDiagnosticoMedicoCie.getText().trim()+", "+vectoPalabras[1]);

				}
				*/
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
				//if(verifcarCamposBlancos()){
					//insertarDatosPersonaDiscapacidad();
				//}
				insertarDatosPersonaDiscapacidad_v1();
				
				
			}
		});
		
		
		
		
		
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
		
		textFieldDocId.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> arg0, String arg1, String arg2) {
				ObservableList<String> styleClass = textFieldDocId.getStyleClass();
		        if (textFieldDocId.getText().trim().length()==0) {
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
		});
		
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
		textFieldApellPaterno.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        char character=event.getCharacter().charAt(0);
		        System.out.println(character);
		        if(!(Character.isLetter(character) || character==' ')){
		        	event.consume();
		        }                    
		    }});
		textFieldApellMaterno.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        char character=event.getCharacter().charAt(0);

		        if(!(Character.isLetter(character) || character==' ')){
		        	event.consume();
		        }                    
		    }});
		textFieldApellNombres.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        char character=event.getCharacter().charAt(0);

		        if(!(Character.isLetter(character) || character==' ')){
		        	event.consume();
		        }                    
		    }});
		textFieldDocId.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        char numero=event.getCharacter().charAt(0);

		        if(!Character.isDigit(numero)){
		        	event.consume();
		        }                    
		    }});
		
		toggleButtonDatosPersonal.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ToggleButton toggleButton=(ToggleButton)event.getSource();
				if(toggleButton.isSelected()){
					scrollPaneDatosDiscapacidadCertificadoMedico.setVisible(false);
					scrollPaneDatosDomicilio.setVisible(false);
					scrollPaneResolucionDiscapacidad.setVisible(false);
					scrollPaneDatosPersonales.setVisible(true);
				}else {
					toggleButton.setSelected(true);				
				}
				
			}
		});
	
		toggleButtonDatosDomicilio.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ToggleButton toggleButton=(ToggleButton)event.getSource();
				if(toggleButton.isSelected()){
					scrollPaneDatosDiscapacidadCertificadoMedico.setVisible(false);
					scrollPaneDatosDomicilio.setVisible(true);
					scrollPaneResolucionDiscapacidad.setVisible(false);
					scrollPaneDatosPersonales.setVisible(false);
				}else {
					toggleButton.setSelected(true);				
				}
				
			}
		});
		
		toggleButtonDatosDiscapacidadCertificadoMedico.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ToggleButton toggleButton=(ToggleButton)event.getSource();
				if(toggleButton.isSelected()){
					scrollPaneDatosDiscapacidadCertificadoMedico.setVisible(true);
					scrollPaneDatosDomicilio.setVisible(false);
					scrollPaneResolucionDiscapacidad.setVisible(false);
					scrollPaneDatosPersonales.setVisible(false);
				}else {
					toggleButton.setSelected(true);				
				}
				
			}
		});
		
		
			
		
		toggleButtonResolucionDiscapacidad.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ToggleButton toggleButton=(ToggleButton)event.getSource();
				if(toggleButton.isSelected()){
					scrollPaneDatosDiscapacidadCertificadoMedico.setVisible(true);
					scrollPaneDatosDomicilio.setVisible(false);
					scrollPaneResolucionDiscapacidad.setVisible(true);
					scrollPaneDatosPersonales.setVisible(false);
				}else {
					toggleButton.setSelected(true);				
				}
				
			}
		});
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
		
		//campo de apellido paterno 
		ObservableList<String> styleClass = textFieldApellPaterno.getStyleClass();
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
		
		
		//dni seleccionado 
		if(!(RadioButtonCUI.isSelected() || RadioButtonDNI.isSelected())){
			RadioButtonDNI.requestFocus();
			scrollPaneContenedor.setVvalue(0);
			return false; 
		}
		
		//campo de dni vacio 
		styleClass=textFieldDocId.getStyleClass();
		if(textFieldDocId.getText().trim().isEmpty()){
			
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
		   scrollPaneContenedor.setVvalue(0);
		   textFieldDocId.requestFocus();
			
			
			return false;
		}else{
			styleClass.removeAll(Collections.singleton("error"));

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
		styleClass=comboBoxNivelEducativo.getStyleClass();
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
		
		//campo 
		
		if(comboBoxTipoTrabajo.getSelectionModel().getSelectedItem()==null){
			comboBoxTipoTrabajo.requestFocus();
			scrollPaneContenedor.setVvalue(0);
			return false;
		
		}
		
		if(comboBoxRegion.getSelectionModel().getSelectedItem()==null){
			comboBoxRegion.requestFocus();
			return false;
		
		}
		
		if(comboBoxProvincia.getSelectionModel().getSelectedItem()==null){
			comboBoxProvincia.requestFocus();
			return false;
		
		}
		
		if(comboBoxDistrito.getSelectionModel().getSelectedItem()==null){
			comboBoxDistrito.requestFocus();
			return false;
		
		}
		if(comboBoxTipoVia.getSelectionModel().getSelectedItem()==null){
			comboBoxTipoVia.requestFocus();
			return false;
		
		}

		if(textFieldNombreVia.getText().trim().isEmpty()){
			textFieldNombreVia.requestFocus();
			return false;
		}
		
		
		styleClass=checkComboBoxTipoDiscapacidad.getStyleClass();
		if(checkComboBoxTipoDiscapacidad.getCheckModel().getCheckedIndices().isEmpty()){
			checkComboBoxTipoDiscapacidad.requestFocus();
			if(!styleClass.contains("error_check")){
				styleClass.add("error_check");
			}
			return false;
		}else{
			styleClass.removeAll(Collections.singleton("error_check"));
		}
		
		
		styleClass=textFieldDiagnosticoMedico.getStyleClass();

		if(textFieldDiagnosticoMedico.getText().trim().isEmpty()){
			textFieldDiagnosticoMedico.requestFocus();
			if(!styleClass.contains("error")){
				styleClass.add("error");
			}
			return false;
		}else{
			styleClass.removeAll(Collections.singleton("error"));
		}
		
		
		if(checkComboBoxTipoAyudaBiometrica.getCheckModel().getCheckedIndices().isEmpty()){
			if(!styleClass.contains("error_check")){
				styleClass.add("error_check");
			}
			checkComboBoxTipoAyudaBiometrica.requestFocus();
			return false;
		}
		
		
		if(checkComboBoxTipoSeguroMedico.getCheckModel().getCheckedIndices().isEmpty()){
			checkComboBoxTipoSeguroMedico.requestFocus();
			return false;
		}
		
		if(chekcComboBoxLimitacionPermanente.getCheckModel().getCheckedIndices().isEmpty()){
			chekcComboBoxLimitacionPermanente.requestFocus();
			return false;
		}
		
		if(comboBoxNivelGravedad.getSelectionModel().getSelectedItem()==null){
			comboBoxNivelGravedad.requestFocus();
			return false;
		}
		
		if(comboBoxOrigenLimitacion.getSelectionModel().getSelectedItem()==null){
			comboBoxOrigenLimitacion.requestFocus();
			return false;
		}
		
		
		//tiene certificado medico
		boolean tieneCertificadoDiscapacidad=false;
		for(int i=0;i<hBoxTieneCertificadoDiscapacidad.getChildren().size();i++){
			CheckBoxTieneCertificadoDiscapacidad checBoxTieneCertD=(CheckBoxTieneCertificadoDiscapacidad)hBoxTieneCertificadoDiscapacidad.getChildren().get(i);
			if(checBoxTieneCertD.isSelected()){
				tieneCertificadoDiscapacidad=true;
				break;
			}
		}
		
		
		
		if(!tieneCertificadoDiscapacidad){
			for(int i=0;i<hBoxTieneCertificadoDiscapacidad.getChildren().size();i++){
				CheckBoxTieneCertificadoDiscapacidad checBoxTieneCertD=(CheckBoxTieneCertificadoDiscapacidad)hBoxTieneCertificadoDiscapacidad.getChildren().get(i);
				checBoxTieneCertD.requestFocus();		
				break;
			}
			return false;
		}
		
		
		//tieneincripcoion
		boolean tieneInscripcionCondis=false;
		for(int i=0;i<hBoxTieneInscripcionConadis.getChildren().size();i++){
			CheckBoxTieneInscripcionConadis checBoxTieneCertD=(CheckBoxTieneInscripcionConadis)hBoxTieneInscripcionConadis.getChildren().get(i);
			if(checBoxTieneCertD.isSelected()){
				tieneInscripcionCondis=true;
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
		
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM REQUERIMIENTO_APOYO  ORDER BY ID");
			System.out.println("Estuve aqui xD");
			
			rs=pst.executeQuery();
			while(rs.next()){
				System.out.println("aqui tambien");
				System.out.println("Holaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
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
		
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM TIPO_AYUDA_BIOMECANICA  ORDER BY ID");
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
		
		arrayTipoSeguroMedico.clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM TIPO_SEGURO  ORDER BY ID");
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
		
		arrayLimitacionPermanente.clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE FROM LIMITACION_PERMANENTE_PARA  ORDER BY ID");
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
				labelInformacion.setText("Datos de Domicilio.. Inserción Correcta...");	
			}
			
			
			rsConsulta=pstDomicilio.getGeneratedKeys();
			
			while(rsConsulta.next()){
				idTemporal=rsConsulta.getInt(1);
				System.out.println(idTemporal);
			}
			
			rsConsulta.close();
			
			//datos de personales
			
			pstPerDiscapacidad=conn.prepareStatement("INSERT INTO PER_DISCAPACIDAD(NOMBRES, APELL_PATERNO,APELL_MATERNO,DNI,CUI,FECHA_NACIMIENTO,SEXO,ID_NIVEL_EDUCATIVO,ID_TIPO_TRABAJO,ID_DOMICILIO,CREADO_POR,MODIFICADO_POR, "
					+ "ID_ESTADO_CIVIL,EMAIL, NUM_CONTACTO,OBSERVACION, UTLIMO_AÑO_NIVEL_EDUCATIVO,OCUPACION_DESEMPENNA) "
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
				labelInformacion.setText("Datos de Persona.. Inserción Correcta...");	
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

			labelInformacion.setText("Datos de Ayuda Tipo Discapacidad.. Inserción Correcta...");	

			
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
				labelInformacion.setText("Datos de Discapacidad.. Inserción Correcta...");	
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
			labelInformacion.setText("Datos de Limitacion.. Inserción Correcta...");	
			
			pstAyudaBiomecanica=conn.prepareStatement("INSERT INTO D_TIPO_AYUDA_BIOMECANICA_DATOS_DISCAPACIDAD(ID_DATOS_DISCAPACIDAD,ID_TIPO_AYUDA_BIOMECANICA ) VALUES (?,?);");

			for(int i=0;i<checkComboBoxTipoAyudaBiometrica.getCheckModel().getItemCount();i++){
				if(checkComboBoxTipoAyudaBiometrica.getCheckModel().isChecked(checkComboBoxTipoAyudaBiometrica.getCheckModel().getItem(i))){
					
					pstAyudaBiomecanica.setInt(1, idTemporal);
					pstAyudaBiomecanica.setInt(2, checkComboBoxTipoAyudaBiometrica.getCheckModel().getItem(i).getId());
					pstAyudaBiomecanica.addBatch();
					
				}
						
			}
			
			pstAyudaBiomecanica.executeBatch();
			
			labelInformacion.setText("Datos de Ayuda Biomecanica.. Inserción Correcta...");	
			
			pstTipoSEguro=conn.prepareStatement("INSERT INTO D_TTIPO_SEGURO_DATOS_DISCAPACIDAD(ID_DATOS_DISCAPACIDAD, ID_TIPO_SEGURO) VALUES(?, ?);");

			for(int i=0;i<checkComboBoxTipoSeguroMedico.getCheckModel().getItemCount();i++){
				if(checkComboBoxTipoSeguroMedico.getCheckModel().isChecked(checkComboBoxTipoSeguroMedico.getCheckModel().getItem(i))){
					pstTipoSEguro.setInt(1, idTemporal);
					pstTipoSEguro.setInt(2, checkComboBoxTipoSeguroMedico.getCheckModel().getItem(i).getId());
					pstTipoSEguro.addBatch();
				}
						
			}
			pstTipoSEguro.executeBatch();
			labelInformacion.setText("Datos de Ayuda Tipo Seguro.. Inserción Correcta...");	


			pstTipoDiscapacidad=conn.prepareStatement("INSERT INTO D_TIPO_DISCAPACIDAD_DATOS_DISCAPACIDAD (ID_DATOS_DISCAPACIDAD,ID_TIPO_DISCAPACIDAD) VALUES(?,?);");

			for(int i=0;i<checkComboBoxTipoDiscapacidad.getCheckModel().getItemCount();i++){
				if(checkComboBoxTipoDiscapacidad.getCheckModel().isChecked(checkComboBoxTipoDiscapacidad.getCheckModel().getItem(i))){
					pstTipoDiscapacidad.setInt(1, idTemporal);
					pstTipoDiscapacidad.setInt(2, checkComboBoxTipoDiscapacidad.getCheckModel().getItem(i).getId());
					pstTipoDiscapacidad.addBatch();
				}
						
			}
			pstTipoDiscapacidad.executeBatch();

			labelInformacion.setText("Datos de Ayuda Tipo Discapacidad.. Inserción Correcta...");	

			
		

			
			conn.commit();
			labelInformacion.setText("Inserción Correcta...");	
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
		
		
		ResultSet rsConsulta=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			conn.setAutoCommit(false);
			pstDomicilio=conn.prepareStatement("INSERT INTO DOMICILIO(ID_DISTRITO, ID_TIPO_VIA, NOMBRE_VIA,NRO,EDIFICIO,DPTO_INT,MZ,LOTE,ETAPA,URB_AAHH_CP_PJ_OTRO, ID_TIPO_PROCEDENCIA) VALUES "
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
				labelInformacion.setText("Datos de Domicilio.. Inserción Correcta...");	
			}
			
			
			rsConsulta=pstDomicilio.getGeneratedKeys();
			
			while(rsConsulta.next()){
				idTemporalDatosDomicilio=rsConsulta.getInt(1);
				System.out.println(idTemporalDatosDomicilio);
			}
			
			rsConsulta.close();
			
			//datos de personales
			
			pstPerDiscapacidad=conn.prepareStatement("INSERT INTO PER_DISCAPACIDAD(NOMBRES, APELL_PATERNO,APELL_MATERNO,DNI,CUI,FECHA_NACIMIENTO,SEXO,ID_NIVEL_EDUCATIVO,ID_TIPO_TRABAJO,ID_DOMICILIO,CREADO_POR,MODIFICADO_POR, "
					+ "ID_ESTADO_CIVIL,EMAIL, NUM_CONTACTO,OBSERVACION, UTLIMO_AÑO_NIVEL_EDUCATIVO,OCUPACION_DESEMPENNA) "
					+ "		   VALUES(?, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);", PreparedStatement.RETURN_GENERATED_KEYS);

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
			pstPerDiscapacidad.setInt(8, comboBoxNivelEducativo.getSelectionModel().getSelectedItem().getId());
			pstPerDiscapacidad.setInt(9, comboBoxTipoTrabajo.getSelectionModel().getSelectedItem().getId());
			pstPerDiscapacidad.setInt(10, idTemporalDatosDomicilio );
			pstPerDiscapacidad.setString(11, Sesion.DNI);
			pstPerDiscapacidad.setString(12, Sesion.DNI);
			
			//nuevas modificaciones 
			pstPerDiscapacidad.setInt(13, comboBoxEstadoCivil.getSelectionModel().getSelectedItem().getId());
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
			if(textAreaObervacionDatosPersonales.getText().trim().isEmpty()){
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
			//
			rsInsercion=pstPerDiscapacidad.executeUpdate();
			if(rsInsercion==1){
				labelInformacion.setText("Datos de Persona.. Inserción Correcta...");	
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

			labelInformacion.setText("Datos de Programa Social.. Inserción Correcta...");	
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
			pstInscricpcion_conadis.setNull(8, java.sql.Types.NULL);
			
			pstInscricpcion_conadis.setString(9,Sesion.DNI);
			pstInscricpcion_conadis.setString(10, Sesion.DNI);
			
				
			rsInsercion=pstInscricpcion_conadis.executeUpdate();
			if(rsInsercion==1){
				labelInformacion.setText("Datos de Incripcion.. Inserción Correcta...");	
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
				java.util.Date dateEmisionCertificado =java.util.Date.from(datePickerRegistroConadis.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
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
				labelInformacion.setText("Datos de Certificado Medico.. Inserción Correcta...");	
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
			labelInformacion.setText("Datos de Limitacion.. Inserción Correcta...");	
			
			pstAyudaBiomecanica=conn.prepareStatement("INSERT INTO D_TIPO_AYUDA_BIOMECANICA_CERTIFICADO_MEDICO(ID_CERTIFICADO_MEDICO,ID_TIPO_AYUDA_BIOMECANICA ) VALUES (?,?);");

			for(int i=0;i<checkComboBoxTipoAyudaBiometrica.getCheckModel().getItemCount();i++){
				if(checkComboBoxTipoAyudaBiometrica.getCheckModel().isChecked(checkComboBoxTipoAyudaBiometrica.getCheckModel().getItem(i))){
					
					pstAyudaBiomecanica.setInt(1, idTemporalInscripcionConadis);
					pstAyudaBiomecanica.setInt(2, checkComboBoxTipoAyudaBiometrica.getCheckModel().getItem(i).getId());
					pstAyudaBiomecanica.addBatch();
					
				}
						
			}
			
			pstAyudaBiomecanica.executeBatch();
			
			labelInformacion.setText("Datos de Ayuda Biomecanica.. Inserción Correcta...");	
			
			pstTipoSEguro=conn.prepareStatement("INSERT INTO D_TTIPO_SEGURO_CERTIFICADO_MEDICO(ID_CERTIFICADO_MEDICO, ID_TIPO_SEGURO) VALUES(?, ?);");

			for(int i=0;i<checkComboBoxTipoSeguroMedico.getCheckModel().getItemCount();i++){
				if(checkComboBoxTipoSeguroMedico.getCheckModel().isChecked(checkComboBoxTipoSeguroMedico.getCheckModel().getItem(i))){
					pstTipoSEguro.setInt(1, idTemporalInscripcionConadis);
					pstTipoSEguro.setInt(2, checkComboBoxTipoSeguroMedico.getCheckModel().getItem(i).getId());
					pstTipoSEguro.addBatch();
				}
						
			}
			pstTipoSEguro.executeBatch();
			labelInformacion.setText("Datos de Ayuda Tipo Seguro.. Inserción Correcta...");	


			pstTipoDiscapacidad=conn.prepareStatement("INSERT INTO D_TIPO_DISCAPACIDAD_CERTIFICADO_MEDICO (ID_CERTIFICADO_MEDICO,ID_TIPO_DISCAPACIDAD) VALUES(?,?);");

			for(int i=0;i<checkComboBoxTipoDiscapacidad.getCheckModel().getItemCount();i++){
				if(checkComboBoxTipoDiscapacidad.getCheckModel().isChecked(checkComboBoxTipoDiscapacidad.getCheckModel().getItem(i))){
					pstTipoDiscapacidad.setInt(1, idTemporalInscripcionConadis);
					pstTipoDiscapacidad.setInt(2, checkComboBoxTipoDiscapacidad.getCheckModel().getItem(i).getId());
					pstTipoDiscapacidad.addBatch();
				}
						
			}
			pstTipoDiscapacidad.executeBatch();

			labelInformacion.setText("Datos de Ayuda Tipo Discapacidad.. Inserción Correcta...");	
			
			
			pstRequerimientoApoyo=conn.prepareStatement("INSERT INTO D_REQUERIMIENTO_APOYO_CERTIFICADO_MEDICO (ID_CERTIFICADO_MEDICO,ID_REQUERIMIENTO_APOYO) VALUES(?,?);");

			for(int i=0;i<chekcComboBoxRequerimientoApoyo.getCheckModel().getItemCount();i++){
				if(chekcComboBoxRequerimientoApoyo.getCheckModel().isChecked(chekcComboBoxRequerimientoApoyo.getCheckModel().getItem(i))){
					pstRequerimientoApoyo.setInt(1, idTemporalInscripcionConadis);
					pstRequerimientoApoyo.setInt(2, chekcComboBoxRequerimientoApoyo.getCheckModel().getItem(i).getId());
					pstRequerimientoApoyo.addBatch();
				}
						
			}
			pstRequerimientoApoyo.executeBatch();

			labelInformacion.setText("Datos de Requerimiento de Apoyo.. Inserción Correcta...");	
			
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
				java.util.Date dateEmisionResolucion =java.util.Date.from(datePickerRegistroConadis.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
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
				labelInformacion.setText("Datos de Resolución.. Inserción Correcta...");	
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
				labelInformacion.setText("Datos de Discapacidad.. Inserción Correcta...");	
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
			labelInformacion.setText("Datos de Limitacion.. Inserción Correcta...");	
			
			pstAyudaBiomecanica=conn.prepareStatement("INSERT INTO D_TIPO_AYUDA_BIOMECANICA_CERTIFICADO_MEDICO(ID_CERTIFICADO_MEDICO,ID_TIPO_AYUDA_BIOMECANICA ) VALUES (?,?);");

			for(int i=0;i<checkComboBoxTipoAyudaBiometrica.getCheckModel().getItemCount();i++){
				if(checkComboBoxTipoAyudaBiometrica.getCheckModel().isChecked(checkComboBoxTipoAyudaBiometrica.getCheckModel().getItem(i))){
					
					pstAyudaBiomecanica.setInt(1, idTemporalDatosDiscapacidad);
					pstAyudaBiomecanica.setInt(2, checkComboBoxTipoAyudaBiometrica.getCheckModel().getItem(i).getId());
					pstAyudaBiomecanica.addBatch();
					
				}
						
			}
			
			pstAyudaBiomecanica.executeBatch();
			
			labelInformacion.setText("Datos de Ayuda Biomecanica.. Inserción Correcta...");	
			
			pstTipoSEguro=conn.prepareStatement("INSERT INTO D_TTIPO_SEGURO_CERTIFICADO_MEDICO(ID_CERTIFICADO_MEDICO, ID_TIPO_SEGURO) VALUES(?, ?);");

			for(int i=0;i<checkComboBoxTipoSeguroMedico.getCheckModel().getItemCount();i++){
				if(checkComboBoxTipoSeguroMedico.getCheckModel().isChecked(checkComboBoxTipoSeguroMedico.getCheckModel().getItem(i))){
					pstTipoSEguro.setInt(1, idTemporalDatosDiscapacidad);
					pstTipoSEguro.setInt(2, checkComboBoxTipoSeguroMedico.getCheckModel().getItem(i).getId());
					pstTipoSEguro.addBatch();
				}
						
			}
			pstTipoSEguro.executeBatch();
			labelInformacion.setText("Datos de Ayuda Tipo Seguro.. Inserción Correcta...");	


			pstTipoDiscapacidad=conn.prepareStatement("INSERT INTO D_TIPO_DISCAPACIDAD_CERTIFICADO_MEDICO (ID_CERTIFICADO_MEDICO,ID_TIPO_DISCAPACIDAD) VALUES(?,?);");

			for(int i=0;i<checkComboBoxTipoDiscapacidad.getCheckModel().getItemCount();i++){
				if(checkComboBoxTipoDiscapacidad.getCheckModel().isChecked(checkComboBoxTipoDiscapacidad.getCheckModel().getItem(i))){
					pstTipoDiscapacidad.setInt(1, idTemporalDatosDiscapacidad);
					pstTipoDiscapacidad.setInt(2, checkComboBoxTipoDiscapacidad.getCheckModel().getItem(i).getId());
					pstTipoDiscapacidad.addBatch();
				}
						
			}
			pstTipoDiscapacidad.executeBatch();

			labelInformacion.setText("Datos de Ayuda Tipo Discapacidad.. Inserción Correcta...");	

			
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
			labelInformacion.setText("Inserción Correcta...");	
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
	      		
	      		if(pstProgramaSocial!=null){
	      			pstProgramaSocial.close();
					
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

	
	public void cargarDatos(int idInscripcion_conadis){
		seleccionaryCargarInscripcionConadis(idInscripcion_conadis);
		seleccionaryCargarDatosPersonales(idPerDiscapacidad);
		seleccionaryCargarDatosProgramaSocial(idPerDiscapacidad);
		seleccionaryCargarDatosDomicilio(idDomicilioBaseDatos);
		seleccionaryCargarCertificadoMedico(idInscripcion_conadis);
		seleccionaryCargarResolucion(idInscripcion_conadis);
		seleccionaryCargarTiposDiscapacidad(idInscripcion_conadis);
		seleccionaryCargarRequerimientoApoyo(idInscripcion_conadis);
		seleccionaryCargarLimitacionPermanente(idInscripcion_conadis);
		seleccionaryCargarTipoAyudaBiomecanica(idInscripcion_conadis);
		seleccionaryCargarTipoSeguro(idInscripcion_conadis);
	}

	
	public void seleccionaryCargarInscripcionConadis(int id){
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	try {
		Conexion.conectarDB();
		conn = Conexion.getConexion();
		pst = conn.prepareStatement("SELECT  ID, ID_TIPO_DE_TRAMITE, MOTIVO, FECHA_REGISTRO_CONADIS,ID_PER_DISCAPACIDAD,ID_TIENE_CERTIFICADO_DISCAPACIDAD,ID_TIENE_INSCRIPCION_CONADIS,NRO_CARNE,"+
		"ANNO_INCRIPCCION, FECHA_CREACION, FECHA_MODIFICACION,CREADO_POR,MODIFICADO_POR FROM INSCRIPCION_CONADIS_PASCO WHERE ID=?;");
		pst.setInt(1, id);
		rs = pst.executeQuery();

		while (rs.next()) {
			comboBoxTipoTramite.getSelectionModel().select(new TipoTramite(rs.getInt("ID_TIPO_DE_TRAMITE"), null));
			textAreaMotivoInscripcionConadis.setText(rs.getString("MOTIVO"));
			java.sql.Date mysqlFecha = rs.getDate("FECHA_REGISTRO_CONADIS");
			datePickerRegistroConadis.setValue(mysqlFecha.toLocalDate());
			idPerDiscapacidad=rs.getInt("ID_PER_DISCAPACIDAD");
			
			
			for(int i=0;i<hBoxTieneCertificadoDiscapacidad.getChildren().size();i++){
				CheckBoxTieneCertificadoDiscapacidad checBoxCertificadoDiscapacidad=(CheckBoxTieneCertificadoDiscapacidad)hBoxTieneCertificadoDiscapacidad.getChildren().get(i);
				if(checBoxCertificadoDiscapacidad.getId_()==rs.getInt("ID_TIENE_CERTIFICADO_DISCAPACIDAD")){
					System.out.println(checBoxCertificadoDiscapacidad.getNombre());
					checBoxCertificadoDiscapacidad.setSelected(true);;
					break;
				}
			}
			
			for(int i=0;i<hBoxTieneInscripcionConadis.getChildren().size();i++){
				CheckBoxTieneInscripcionConadis checBoxIncripcionConadis=(CheckBoxTieneInscripcionConadis)hBoxTieneInscripcionConadis.getChildren().get(i);
				if(checBoxIncripcionConadis.getId_()==rs.getInt("ID_TIENE_INSCRIPCION_CONADIS")){
					System.out.println(checBoxIncripcionConadis.getNombre());
					checBoxIncripcionConadis.setSelected(true);;
					break;
				}
			}
			
			textFieldNumeroCarne.setText(rs.getString("NRO_CARNE"));
			
			
			
			
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

	
	public void seleccionaryCargarDatosPersonales(int id){
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement("SELECT NOMBRES,APELL_PATERNO,APELL_MATERNO,DNI,CUI,FECHA_NACIMIENTO,SEXO,ID_NIVEL_EDUCATIVO,UTLIMO_AÑO_NIVEL_EDUCATIVO,ID_TIPO_TRABAJO,"+
			"OCUPACION_DESEMPENNA,ID_DOMICILIO,ID_ESTADO_CIVIL,EMAIL,NUM_CONTACTO,OBSERVACION,ESTADO,FECHA_CREACION,FECHA_MODIFICACION,"+
			"CREADO_POR,MODIFICADO_POR FROM PER_DISCAPACIDAD WHERE ID=?;");
			pst.setInt(1, id);
			rs = pst.executeQuery();

			System.out.println("estoy aqui 1");
			while (rs.next()) {
				
				this.idDomicilioBaseDatos=rs.getInt("ID_DOMICILIO");
				
				textFieldApellNombres.setText(rs.getString("NOMBRES"));
			
				textFieldApellPaterno.setText(rs.getString("APELL_PATERNO"));
				textFieldApellMaterno.setText(rs.getString("APELL_MATERNO"));
				String dni=rs.getString("DNI");
				String cui=rs.getString("CUI");
				System.out.println(dni);
				if(dni!=null){
					textFieldDocId.setText(rs.getString("DNI"));
					RadioButtonDNI.setSelected(true);
				}else if(cui!=null){
					textFieldDocId.setText(rs.getString("CUI"));
					RadioButtonCUI.setSelected(true);
				}else{
					RadioButtonS_D.setSelected(true);
				}
				
				
				
				java.sql.Date mysqlFecha = rs.getDate("FECHA_NACIMIENTO");
				datePickerFechaNacimiento.setValue(mysqlFecha.toLocalDate());
				

				for(int i=0;i<hBoxSexo.getChildren().size();i++){
					CheckBoxSexo checBoxSexo=(CheckBoxSexo)hBoxSexo.getChildren().get(i);
					if(checBoxSexo.getId_()==rs.getInt("SEXO")){
						System.out.println(checBoxSexo.getNombre());
						
						checBoxSexo.setSelected(true);
						break;
					}
				}
				
				
				comboBoxNivelEducativo.getSelectionModel().select(new NivelEducativo(rs.getInt("ID_NIVEL_EDUCATIVO"), null));;
				textFiedUltimoAnio.setText(String.valueOf(rs.getInt("UTLIMO_AÑO_NIVEL_EDUCATIVO")));
				
				comboBoxTipoTrabajo.getSelectionModel().select(new TipoTrabajo(rs.getInt("ID_TIPO_TRABAJO"), null));
				
				textFieldOcupacionDatosPersonales.setText(rs.getString("OCUPACION_DESEMPENNA"));
				comboBoxEstadoCivil.getSelectionModel().select(new EstadoCivil(rs.getInt("ID_ESTADO_CIVIL"), null));
				
				textFieldEmail.setText(rs.getString("EMAIL"));
				textFieldApellNumContacto.setText(rs.getString("NUM_CONTACTO"));
				textAreaObervacionDatosPersonales.setText(rs.getString("OBSERVACION"));
				
				
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
	
	

	
	
	public void seleccionaryCargarDatosProgramaSocial(int id){
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement("SELECT ID_PER_DISCAPACIDAD, ID_PROGRAMA_SOCIAL FROM D_PROGRAMA_SOCIAL_PER_DISCAPACIDAD WHERE ID_PER_DISCAPACIDAD=?;");
			pst.setInt(1, id);
			rs = pst.executeQuery();

			System.out.println("estoy aqui 1");
			while (rs.next()) {
				checkComboBoxProgramaSocial.getCheckModel().check(new ProgramaSocial(rs.getInt("ID_PROGRAMA_SOCIAL"), null));;


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
	
public void seleccionaryCargarDatosDomicilio(int id){
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement("SELECT ID,(SELECT DISTRITO.ID_PROVINCIA FROM DISTRITO WHERE ID=DOMICILIO.ID_DISTRITO) AS ID_PROVINCIA, (SELECT ID_DEPARTAMENTO  FROM PROVINCIA WHERE ID =(SELECT DISTRITO.ID_PROVINCIA FROM DISTRITO WHERE ID=DOMICILIO.ID_DISTRITO)) AS ID_DEPARTAMENTO,"+
			" ID_DISTRITO, ID_TIPO_VIA, ID_TIPO_PROCEDENCIA, URB_AAhh_CP_PJ_OTRO, NOMBRE_VIA,NRO,EDIFICIO,DPTO_INT,MZ,LOTE,ETAPA  FROM DOMICILIO WHERE ID=?;");
			pst.setInt(1, id);
			rs = pst.executeQuery();

			System.out.println("estoy aqui 1");
			while (rs.next()) {
				
				comboBoxRegion.getSelectionModel().select(new Departamento(rs.getInt("ID_DEPARTAMENTO"), null));
				mostrarProvincias(comboBoxRegion.getSelectionModel().getSelectedItem().getId());
				comboBoxProvincia.getSelectionModel().select(new Provincia(rs.getInt("ID_PROVINCIA"),null));
				mostrarDistritos(comboBoxProvincia.getSelectionModel().getSelectedItem().getId());
				comboBoxDistrito.getSelectionModel().select(new Distrito(rs.getInt("ID_DISTRITO"),null));
				comboBoxProcedencia.getSelectionModel().select(new Procedencia(rs.getInt("ID_TIPO_PROCEDENCIA"), null));
				textFieldUrb_AAHH_CP_PJ_OtroDatosDomicilio.setText(rs.getString("URB_AAhh_CP_PJ_OTRO"));
				comboBoxTipoVia.getSelectionModel().select(new TipoVia(rs.getInt("ID_TIPO_VIA"), null));
				textFieldNombreVia.setText(rs.getString("NOMBRE_VIA"));
				textFieldNroDatosDomicilio.setText(rs.getString("NRO"));
				textFieldEdificioDatosDomicilio.setText(rs.getString("EDIFICIO"));
				textFieldDpto_IntDatosDomicilio.setText(rs.getString("DPTO_INT"));
				textFieldMzDatosDomicilio.setText(rs.getString("MZ"));
				textFieldLoteDatosDomicilio.setText(rs.getString("LOTE"));
				textFieldEtapaDatosDomicilio.setText(rs.getString("ETAPA"));
				
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
	

public void seleccionaryCargarCertificadoMedico(int id){
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	try {
		Conexion.conectarDB();
		conn = Conexion.getConexion();
		pst = conn.prepareStatement("SELECT ID,NUMERO_CERTIFICADO, FECHA_CERTIFICADO,PORCENTAJE_RES_PARTI,PORCENTAJE_RES_PARTI_DIFERIDO,ID_NIVEL_GRAVEDAD_LIMITACION,ID_MEDICO,ID_ESTABLECIMIENTO_SALUD,"+
		"ID_DIRECTOR,OBSERVACIONES_CERTIFICADO_MEDICO, DIAGNOSTICO_MEDICO,ID_ORIGEN_LIMITACION,OBSERVACIONES_OTROS,FECHA_CREACION,FECHA_MODIFICACION,CREADO_POR,MODIFICADO_POR FROM CERTIFICADO_MEDICO WHERE ID=?;");
		pst.setInt(1, id);
		rs = pst.executeQuery();

		while (rs.next()) {
			textFieldNroCertificado.setText(rs.getString("NUMERO_CERTIFICADO"));
			java.sql.Date fechaCertificado=rs.getDate("FECHA_CERTIFICADO");
			datePickerFechaEmisionCertificado.setValue(fechaCertificado.toLocalDate());
			
			textFieldRestrincionParticipacion.setText(String.valueOf(rs.getDouble("PORCENTAJE_RES_PARTI")));
			textFieldRestriccionDiferido.setText(rs.getString("PORCENTAJE_RES_PARTI_DIFERIDO"));
			comboBoxNivelGravedad.getSelectionModel().select(new NivelGravedad(rs.getInt("ID_NIVEL_GRAVEDAD_LIMITACION"), null));
			
			comboBoxMedicoCertificador.getSelectionModel().select(new MedicoCertificador(rs.getInt("ID_MEDICO"), null));
			comboBoxEstablecimientoSalud.getSelectionModel().select(new EstablecimientoSalud(rs.getInt("ID_ESTABLECIMIENTO_SALUD"), null));
			comboBoxDirectorEstablecimiento.getSelectionModel().select(new DirectorEstablecimiento(rs.getInt("ID_DIRECTOR"), null));
			
			textAreaObservacionesCertificacion.setText(rs.getString("OBSERVACIONES_CERTIFICADO_MEDICO"));
			textFieldDiagnosticoMedico.setText(rs.getString("DIAGNOSTICO_MEDICO"));
			
			comboBoxOrigenLimitacion.getSelectionModel().select(new OrigenLimitacion(rs.getInt("ID_ORIGEN_LIMITACION"), null, null));
			
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


public void seleccionaryCargarResolucion(int id){
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	try {
		Conexion.conectarDB();
		conn = Conexion.getConexion();
		pst = conn.prepareStatement("SELECT ID, NRO_RESOLUCION, SE_RESUELVE,FECHA,FIRMA,DOCUMENTO_FISICO,"
				+ "FECHA_CREACION,FECHA_MODIFICACION,CREADO_POR,MODIFICADO_POR FROM RESOLUCION  WHERE ID=?;");
		pst.setInt(1, id);
		rs = pst.executeQuery();

		while (rs.next()) {
			textFieldNumeroResolucion.setText(rs.getString("NRO_RESOLUCION"));
			java.sql.Date dateEmisionResolucion=rs.getDate("FECHA");
			datePickerFechaEmisionResolcuion.setValue(dateEmisionResolucion.toLocalDate());
			textFieldSeResuelve.setText(rs.getString("SE_RESUELVE"));
			
			
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



public void seleccionaryCargarTiposDiscapacidad(int id){
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	try {
		Conexion.conectarDB();
		conn = Conexion.getConexion();
		pst = conn.prepareStatement("SELECT ID_CERTIFICADO_MEDICO, ID_TIPO_DISCAPACIDAD FROM D_TIPO_DISCAPACIDAD_CERTIFICADO_MEDICO WHERE ID_CERTIFICADO_MEDICO=?;");
		pst.setInt(1, id);
		rs = pst.executeQuery();

		System.out.println("estoy aqui 1");
		while (rs.next()) {
			checkComboBoxTipoDiscapacidad.getCheckModel().check(new TipoDiscapacidad(rs.getInt("ID_TIPO_DISCAPACIDAD"), null,null));;


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


public void seleccionaryCargarRequerimientoApoyo(int id){
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	try {
		Conexion.conectarDB();
		conn = Conexion.getConexion();
		pst = conn.prepareStatement("SELECT ID_CERTIFICADO_MEDICO, ID_REQUERIMIENTO_APOYO FROM D_REQUERIMIENTO_APOYO_CERTIFICADO_MEDICO WHERE ID_CERTIFICADO_MEDICO=?;");
		pst.setInt(1, id);
		rs = pst.executeQuery();

		System.out.println("estoy aqui 1");
		while (rs.next()) {
			chekcComboBoxRequerimientoApoyo.getCheckModel().check(new RequerimientoApoyo(rs.getInt("ID_REQUERIMIENTO_APOYO"), null));
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



public void seleccionaryCargarLimitacionPermanente(int id){
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	try {
		Conexion.conectarDB();
		conn = Conexion.getConexion();
		pst = conn.prepareStatement("SELECT ID_CERTIFICADO_MEDICO, ID_LIMITACION_PERMANENTE_PARA FROM D_LIMITACION_PERMANENTE_PARA_CERTIFICADO_MEDICO WHERE ID_CERTIFICADO_MEDICO=?;");
		pst.setInt(1, id);
		rs = pst.executeQuery();

		System.out.println("estoy aqui 1");
		while (rs.next()) {
			chekcComboBoxLimitacionPermanente.getCheckModel().check(new LimitacionPermanente(rs.getInt("ID_LIMITACION_PERMANENTE_PARA"), null,null));
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




public void seleccionaryCargarTipoAyudaBiomecanica(int id){
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	try {
		Conexion.conectarDB();
		conn = Conexion.getConexion();
		pst = conn.prepareStatement("SELECT ID_CERTIFICADO_MEDICO, ID_TIPO_AYUDA_BIOMECANICA FROM D_TIPO_AYUDA_BIOMECANICA_CERTIFICADO_MEDICO WHERE ID_CERTIFICADO_MEDICO=?;");
		pst.setInt(1, id);
		rs = pst.executeQuery();

		System.out.println("estoy aqui 1");
		while (rs.next()) {
			checkComboBoxTipoAyudaBiometrica.getCheckModel().check(new TipoAyudaBiomecanica(rs.getInt("ID_TIPO_AYUDA_BIOMECANICA"), null,null));
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



public void seleccionaryCargarTipoSeguro(int id){
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	try {
		Conexion.conectarDB();
		conn = Conexion.getConexion();
		pst = conn.prepareStatement("SELECT ID_CERTIFICADO_MEDICO,ID_TIPO_SEGURO FROM D_TTIPO_SEGURO_CERTIFICADO_MEDICO WHERE ID_CERTIFICADO_MEDICO=?;");
		pst.setInt(1, id);
		rs = pst.executeQuery();

		System.out.println("estoy aqui 1");
		while (rs.next()) {
			checkComboBoxTipoSeguroMedico.getCheckModel().check(new TipoSeguroMedico(rs.getInt("ID_TIPO_SEGURO"), null,null));
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