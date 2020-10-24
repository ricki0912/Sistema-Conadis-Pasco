package registro.personaConDiscapacidad.nuevoRegistroPD;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;



import com.jfoenix.controls.JFXButton;
import funciones.Conexion;
import funciones.Funciones;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;




public class NuevoRegistroPDControler2 extends Funciones implements Initializable {
	
	private ObservableList<CheckBoxTieneCertificadoDiscapacidad> arrayCheckBoxCertificadoDiscapacidad=FXCollections.observableArrayList();
	private ObservableList<CheckBoxTieneInscripcionConadis> arrayCheckBoxTieneIncripcionConadis=FXCollections.observableArrayList();
	private ObservableList<CheckBoxSexo> arrayCheckBoxSexo=FXCollections.observableArrayList();
	@FXML private Label labelInformacion;
	@FXML private HBox hBoxTieneInscripcionConadis;
	@FXML private HBox hBoxTieneCertificadoDiscapacidad;
	@FXML private HBox hBoxSexo;
	
	
	@FXML
    private DatePicker datePickerFechaNacimiento;

    @FXML
    private ComboBox<NivelEducativo> comboBoxNivelEducativo;

    @FXML
    private ComboBox<Distrito> comboBoxDistrito;

    @FXML
    private TextField textFieldApellPaterno;

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

    @FXML private MenuButton menuButtonLimitacionPermanente;
    
    @FXML
    private TextField textFieldUrb_AAHH_CP_PJ_OtroDatosDomicilio;
    
    @FXML private TableView<TipoDiscapacidad> tableViewTipoDiscapacidad;
    
    @FXML private TableColumn<TipoDiscapacidad, Boolean> tableColumnTipoDiscapacidad;
    
    @FXML private TableView<OrigenLimitacion> tableViewOrigenLimitaicion;
    @FXML private TableColumn<OrigenLimitacion, Boolean> tableColumnOrigenLimitacion;
    
    @FXML private TableColumn<TipoAyudaBiomecanica, Boolean> tableColumnTipoAyudaBiometrica;
    @FXML private TableView<TipoAyudaBiomecanica> tableViewTipoAyudaBiometrica;
    
    @FXML private ComboBox<NivelGravedad> comboBoxNivelGravedad;
    @FXML private TextField textFieldDiagnosticoMedico;
    @FXML private MenuButton menuButtonTipoDiscapacidad;
    @FXML private MenuButton menuButoonOrigenLimitacion;
    @FXML private MenuButton menutButtonSeguroMedico;
    @FXML private MenuButton menuButtonTipoAyudaBiometrica;
    
    
    @FXML private TableColumn<LimitacionPermanente,Boolean> tableColumnLimitacionPermanente;
    
    @FXML private TableView<LimitacionPermanente> tableViewLImitacionPermanente;
    @FXML private TableView<TipoSeguroMedico> tableViewTipoSeguroMedico;
    @FXML private TableColumn<TipoSeguroMedico, Boolean> tableColumnTipoSeguroMedico;
    
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
		mostrarDepartamentos();
		mostrarNivelEducativo();
		mostrarTipoTrabajo();
		mostrarTipoVia();
		mostrarTableLimitacionPermanente();
		mostrarTableTipoDiscapacidad();
		mostrarTableTipoAyudaBiometrica();
		mostrarTableTipoSeguroMedico();
		mostrarTableOrigenLimitacion();
		mostrarNivelGravedad();
		mostrarCheckBoxTieneCertificadoDiscapacidad();
		mostrarCheckBoxSexo();
		restricciones();

		mostrarCheckBoxTieneInscripcionConadis();
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
		
	
		buttonValidar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				verifcarCamposBlancos();
			}
		});
		
	}
	
	

	
	public void restricciones(){
		textFieldApellPaterno.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        char character=event.getCharacter().charAt(0);

		        if(!Character.isLetter(character)){
		        	event.consume();
		        }                    
		    }});
		textFieldApellMaterno.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        char character=event.getCharacter().charAt(0);

		        if(!Character.isLetter(character)){
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
	public void verifcarCamposBlancos(){
		if(textFieldApellPaterno.getText().trim().isEmpty()){
			textFieldApellPaterno.requestFocus();
			return;
		}
		menuButoonOrigenLimitacion.requestFocus();
	}
	
	public void mostrarTableTipoDiscapacidad(){
		System.out.println("EStoy aquiiiiiiiii xD");
		tableViewTipoDiscapacidad.setItems(arrayTipoDiscapacidad);
		tableColumnTipoDiscapacidad.setCellValueFactory(new PropertyValueFactory<TipoDiscapacidad, Boolean>("checkBoxTipoDiscapacidad"));
	

		
		//tableColumnLimitacionPermanente.setCellValueFactory(cellData -> cellData.getValue().activeProperty());
		//vamos limpiar el contenedor de los botones
		tableViewTipoDiscapacidad.getItems().clear(); 
		  //hacer la conexcioon con la base de datos y obtener alas busquedas encontradas
      Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{    			  
        Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
			  pst=conn.prepareStatement("SELECT ID, NOMBRE FROM TIPO_DISCAPACIDAD");
          rs = pst.executeQuery();
        
         while(rs.next()){
        	 tableViewTipoDiscapacidad.getItems().add(new TipoDiscapacidad(rs.getInt("ID"), rs.getString("NOMBRE"),menuButtonTipoDiscapacidad));

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
	
	
	public void mostrarTableOrigenLimitacion(){
		tableViewOrigenLimitaicion.setItems(arrayOrigenLimitacion);
		tableColumnOrigenLimitacion.setCellValueFactory(new PropertyValueFactory<OrigenLimitacion, Boolean>("checkBoxOrigenLimitacion"));
	

		
		//tableColumnLimitacionPermanente.setCellValueFactory(cellData -> cellData.getValue().activeProperty());
		//vamos limpiar el contenedor de los botones
		tableViewOrigenLimitaicion.getItems().clear(); 
		  //hacer la conexcioon con la base de datos y obtener alas busquedas encontradas
      Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{    			  
        Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
			  pst=conn.prepareStatement("SELECT ID, NOMBRE FROM ORIGEN_LIMITACION");
          rs = pst.executeQuery();
        
         while(rs.next()){
        	 tableViewOrigenLimitaicion.getItems().add(new OrigenLimitacion(rs.getInt("ID"), rs.getString("NOMBRE"),menuButtonTipoDiscapacidad));

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
	
	public void mostrarTableTipoSeguroMedico(){
		tableViewTipoSeguroMedico.setItems(arrayTipoSeguroMedico);
		tableColumnTipoSeguroMedico.setCellValueFactory(new PropertyValueFactory<TipoSeguroMedico, Boolean>("checkBoxTipoSeguroMedico"));
	

		
		//tableColumnLimitacionPermanente.setCellValueFactory(cellData -> cellData.getValue().activeProperty());
		//vamos limpiar el contenedor de los botones
		tableViewTipoSeguroMedico.getItems().clear(); 
		  //hacer la conexcioon con la base de datos y obtener alas busquedas encontradas
      Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{    			  
        Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
			  pst=conn.prepareStatement("SELECT ID, NOMBRE FROM TIPO_SEGURO");
          rs = pst.executeQuery();
        
         while(rs.next()){
        	 tableViewTipoSeguroMedico.getItems().add(new TipoSeguroMedico(rs.getInt("ID"), rs.getString("NOMBRE"),menutButtonSeguroMedico));

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
	
	public void mostrarTableTipoAyudaBiometrica(){
		tableViewTipoAyudaBiometrica.setItems(arrayTipoAyudaBiometrica);
		tableColumnTipoAyudaBiometrica.setCellValueFactory(new PropertyValueFactory<TipoAyudaBiomecanica, Boolean>("checkBoxTipoAyudaBiometrica"));
	

		
		//tableColumnLimitacionPermanente.setCellValueFactory(cellData -> cellData.getValue().activeProperty());
		//vamos limpiar el contenedor de los botones
		tableViewTipoAyudaBiometrica.getItems().clear(); 
		  //hacer la conexcioon con la base de datos y obtener alas busquedas encontradas
      Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{    			  
        Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
			  pst=conn.prepareStatement("SELECT ID, NOMBRE FROM TIPO_AYUDA_BIOMECANICA");
          rs = pst.executeQuery();
        
         while(rs.next()){
        	 tableViewTipoAyudaBiometrica.getItems().add(new TipoAyudaBiomecanica(rs.getInt("ID"), rs.getString("NOMBRE"),menuButtonTipoDiscapacidad));

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
	
	public void mostrarTableLimitacionPermanente(){
		tableViewLImitacionPermanente.setItems(arrayLimitacionPermanente);
		tableColumnLimitacionPermanente.setCellValueFactory(new PropertyValueFactory<LimitacionPermanente, Boolean>("checkBoxLimitacionPermanente"));
	

		
		//tableColumnLimitacionPermanente.setCellValueFactory(cellData -> cellData.getValue().activeProperty());
		//vamos limpiar el contenedor de los botones
		tableViewLImitacionPermanente.getItems().clear(); 
		  //hacer la conexcioon con la base de datos y obtener alas busquedas encontradas
      Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{    			  
        Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
			  pst=conn.prepareStatement("SELECT ID, NOMBRE FROM LIMITACION_PERMANENTE_PARA");
          rs = pst.executeQuery();
        
         while(rs.next()){
        	 tableViewLImitacionPermanente.getItems().add(new LimitacionPermanente(rs.getInt("ID"), rs.getString("NOMBRE"),menuButtonLimitacionPermanente));

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

	

  

}