package registro.personaConDiscapacidad.nuevoRegistroPD.programaSocial;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import com.jfoenix.controls.JFXButton;

import funciones.Conexion;
import funciones.Funciones;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import registro.personaConDiscapacidad.PersonaDiscapacidad;
import registro.personaConDiscapacidad.modificarRegistroPD.ModificarRegistroPDControler_v1;
import registro.personaConDiscapacidad.nuevoRegistroPD.EstablecimientoSalud;
import registro.personaConDiscapacidad.nuevoRegistroPD.MedicoCertificador;
import registro.personaConDiscapacidad.nuevoRegistroPD.ProgramaSocial;
import registro.personaConDiscapacidad.nuevoRegistroPD.establecimientoSalud.EstablecimientoSaludControler;
import sesion.Sesion;



public class ProgramaSocialControler extends Funciones implements Initializable {
	
	private int ultimoIDProgramaSocial=-1;
	
	public int getUltimoIDProgramaSocial() {
		return ultimoIDProgramaSocial;
	}


	public void setUltimoIDProgramaSocial(int ultimoIDProgramaSocial) {
		this.ultimoIDProgramaSocial = ultimoIDProgramaSocial;
	}

	@FXML private TextArea textAreaDescripcion;
	
	@FXML
    private BorderPane borderPaneNuevoPersonal;

    @FXML
    private Button buttonClose;

    @FXML
    private TextField textFieldNombre;

    @FXML
    private TextField textFieldCodigo;

    @FXML
    private Label labelInformacion;

    @FXML
    private Button buttonGuardar;

    @FXML
    private Button buttonCancelar;

    @FXML
    private TableView<ProgramaSocial> tableViewProgramaSocial;

    @FXML
    private TableColumn<ProgramaSocial, String> tableColumnNombre;

    @FXML
    private TableColumn<ProgramaSocial, String> tableColumnCodigo;

    @FXML
    private Button buttonModificar;

    @FXML
    private Button buttonEditar;

    @FXML
    private Button buttonEliminar;

    

	private ObservableList<ProgramaSocial> arrayTableViewProgramaSocial = FXCollections.observableArrayList();

    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		iniciarValoresTableViewDatosPersonaDiscapacidad();
		mostrarDatosProgramaSocial();
		restricciones();
		buttonClose.setOnAction(new EventHandler<ActionEvent>() {
			
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
		buttonGuardar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(verificarCamposVacios()){
					insertarDatos();
					mostrarDatosProgramaSocial();

				}
				}
		});
		
		
	
		
		
	}
	
	
	public void restricciones(){
		textFieldNombre.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ObservableList<String> styleClass = textFieldNombre.getStyleClass();
		        if (textFieldNombre.getText().trim().length()==0) {
		            if (! styleClass.contains("error")) {
		                styleClass.add("error");
		            }
		        } else {
		            // remove all occurrences:
		            styleClass.removeAll(Collections.singleton("error"));                    
		        }
			}
		});
		
		textFieldCodigo.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ObservableList<String> styleClass = textFieldCodigo.getStyleClass();
		        if (textFieldCodigo.getText().trim().length()==0) {
		            if (! styleClass.contains("error")) {
		                styleClass.add("error");
		            }
		        } else {
		            // remove all occurrences:
		            styleClass.removeAll(Collections.singleton("error"));                    
		        }
			}
		});
		
		
		
		textFieldNombre.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        char character=event.getCharacter().charAt(0);
		        System.out.println(character);
		        if(!(Character.isLetter(character) || character==' ')){
		        	event.consume();
		        }                    
		    }});
		
	
		textFieldCodigo.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        char numero=event.getCharacter().charAt(0);

		        if(!Character.isDigit(numero)){
		        	event.consume();
		        }                    
		    }});
		
		
	}

	
	public boolean verificarCamposVacios(){
		
		
		ObservableList<String> styleClass = textFieldNombre.getStyleClass();

		
	
		if(textFieldNombre.getText().trim().isEmpty()){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			textFieldNombre.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}
		
		
		
		
		styleClass = textFieldCodigo.getStyleClass();

		if(textFieldCodigo.getText().trim().isEmpty()){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			textFieldCodigo.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}
		
		
		return true;
	}

	public void iniciarValoresTableViewDatosPersonaDiscapacidad() {
		
	
		
		
		
		tableViewProgramaSocial.setItems(arrayTableViewProgramaSocial);
		tableColumnNombre.setCellValueFactory(new PropertyValueFactory<ProgramaSocial,String>("nombre"));
		tableColumnCodigo.setCellValueFactory(new PropertyValueFactory<ProgramaSocial,String>("codigo_estadistico"));
		

	}

	
	public void insertarDatos(){
		ObservableList<String> styleClass = labelInformacion.getStyleClass();
        styleClass.removeAll(Collections.singleton("labelInformacionincorrecto"));  
        styleClass.removeAll(Collections.singleton("labelInformacioncorrecto")); 
		  
		String mensaje=null;
		  Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rsset=null;
		  try{     
			
			  
              Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
               pst = conn.prepareStatement("INSERT INTO PROGRAMA_SOCIAL(NOMBRE, CODIGO_ESTADISTICO,OBSERVACION)"
               		+ "VALUES(?,?,?); ",PreparedStatement.RETURN_GENERATED_KEYS); 
               
               pst.setString(1, textFieldNombre.getText().trim());
               pst.setInt(2, Integer.valueOf(textFieldCodigo.getText().trim()));
               if(!textAreaDescripcion.getText().trim().isEmpty()){
                   pst.setString(3, textAreaDescripcion.getText().trim());           

               }else{
            	   pst.setNull(3, java.sql.Types.VARCHAR);
               }
           
               int rs = pst.executeUpdate();
               if(rs==1){
            	   labelInformacion.setText("Inserción Correcta");;
            	   
            	  
               }else {
            	   labelInformacion.setText("Error :(...");;

            	   
            	   
               }
               rsset=pst.getGeneratedKeys();
               while(rsset.next()){
            	   ultimoIDProgramaSocial=rsset.getInt(1);
               }
               
               conn.close();
               pst.close();
               if (! styleClass.contains("labelInformacioncorrecto")) {
	                styleClass.add("labelInformacioncorrecto");
	              
	            }
               
               
               
           }   
           catch(SQLException e){
        	   
        	   
        	   if (! styleClass.contains("labelInformacionincorrecto")) {
                   styleClass.add("labelInformacionincorrecto");
                 
               }
        	   labelInformacion.setText("Error :(..."+e.getMessage());;
               e.printStackTrace();
          
          
		  }finally{
        	   try {
        		   if(pst!=null){
        			   pst.close();
				
        	   		}if(conn!=null){
        	   			conn.close();
        	   		}
        	   		if(rsset!=null){
        	   			rsset.close();
        	   		}
        	   
        	   } catch (SQLException e) {
					
					e.printStackTrace();
				}
        	   
           }
		 
		
		
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
			pst = conn.prepareStatement("  SELECT ID, NOMBRE, CODIGO_ESTADISTICO, OBSERVACION FROM PROGRAMA_SOCIAL WHERE NOMBRE LIKE ?;");
			pst.setString(1, "%" + buscar + "%");
			rs = pst.executeQuery();

			System.out.println("estoy aqui 1");
			while (rs.next()) {
				ProgramaSocial auxiliar=new ProgramaSocial(rs.getInt("ID"), rs.getString("NOMBRE"), rs.getInt("CODIGO_ESTADISTICO"));
					
				tableViewProgramaSocial.getItems().add(auxiliar);
						

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