package registro.personaConDiscapacidad.nuevoRegistroPD.directorEstablecimiento;

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
import registro.personaConDiscapacidad.nuevoRegistroPD.DirectorEstablecimiento;
import registro.personaConDiscapacidad.nuevoRegistroPD.EstablecimientoSalud;
import registro.personaConDiscapacidad.nuevoRegistroPD.MedicoCertificador;
import registro.personaConDiscapacidad.nuevoRegistroPD.establecimientoSalud.EstablecimientoSaludControler;
import sesion.Sesion;
import venEmergentes.entradas.uno.EntradaUnoControler;



public class DirectorEstablecimientoControler extends Funciones implements Initializable {
	
	public final static int INSERTAR =1;
	public final static  int ACTUALIZAR=2;
	private int accion=INSERTAR;
	
	
	int idActualizar=-1;
	public void setIdActualizar(int idActualizar){
		this.idActualizar=idActualizar;
	}
	
	
	public void setAccion(int accion){
		if(accion==INSERTAR){
			this.accion =accion;
		}else if(accion==ACTUALIZAR){
			this.accion=accion;
		}
	}
	
	
	private int ultimoIDDirector=-1;

	public int getUltimoIDDirector() {
		return ultimoIDDirector;
	}


	public void setUltimoIDDirector(int ultimoIDDirector) {
		this.ultimoIDDirector = ultimoIDDirector;
	}

	@FXML Button buttonAnadirEstablecimientoSalud;
	
    @FXML
    private BorderPane borderPaneNuevoPersonal;

    @FXML 
    private Label labelEstableSalud;
    
    public Button getButtonAnadirEstablecimientoSalud() {
		return buttonAnadirEstablecimientoSalud;
	}


	public void setButtonAnadirEstablecimientoSalud(Button buttonAnadirEstablecimientoSalud) {
		this.buttonAnadirEstablecimientoSalud = buttonAnadirEstablecimientoSalud;
	}


	public Label getLabelEstableSalud() {
		return labelEstableSalud;
	}


	public void setLabelEstableSalud(Label labelEstableSalud) {
		this.labelEstableSalud = labelEstableSalud;
	}


	public ComboBox<EstablecimientoSalud> getComboBoxEstablecimientoSalud() {
		return comboBoxEstablecimientoSalud;
	}


	public void setComboBoxEstablecimientoSalud(ComboBox<EstablecimientoSalud> comboBoxEstablecimientoSalud) {
		this.comboBoxEstablecimientoSalud = comboBoxEstablecimientoSalud;
	}

	@FXML
    private Button buttonClose;

    @FXML
    private TextField textFieldDNI;

    @FXML
    private TextField textFieldNombres;

    @FXML
    private TextField textFieldApellidos;

    @FXML
    private TextField textFieldEspecialidad;

    @FXML
    private Button buttonGuardar;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Label labelInformacion;

    @FXML
    private TextField textFieldCMP;

    @FXML
    private TextField textFieldRNE;

    @FXML
    private ComboBox<EstablecimientoSalud> comboBoxEstablecimientoSalud;

 /*   @FXML
    private TableView<DirectorEstablecimientoTable> tableViewEstablecimientoSalud;

    @FXML
    private TableColumn<DirectorEstablecimientoTable, String> tableColumnDNIMedico;

    @FXML
    private TableColumn<DirectorEstablecimientoTable, String> tableColumnNombresApellidosMedico;

    @FXML
    private TableColumn<DirectorEstablecimientoTable, String> tableColumnEspecialidadMedico;

    @FXML
    private TableColumn<DirectorEstablecimientoTable, String> tableColumnCMPMedico;

    @FXML
    private TableColumn<DirectorEstablecimientoTable, String> tableColumnDNIRNE;

    @FXML
    private TableColumn<DirectorEstablecimientoTable, String> tableColumnEstablecimientoSalud;

    @FXML
    private Button buttonModificar;
*/
    
	private ObservableList<EstablecimientoSalud> arrayEstablecimientoSalud=FXCollections.observableArrayList();

//	private ObservableList<DirectorEstablecimientoTable> arrayTableViewDirectorEstablecimiento = FXCollections.observableArrayList();

    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//iniciarValoresTableViewDatosPersonaDiscapacidad();
		mostrarEstablecimientoSalud();
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
					if(accion==INSERTAR){
						insertarDatos();
					}else if(accion==ACTUALIZAR){
						actualizarDatos();
					}
					//mostrarDatosDirectorEstablecimiento();

				}
				}
			
		});
		
		buttonAnadirEstablecimientoSalud.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				EntradaUnoControler entradaUno=new EntradaUnoControler();
				entradaUno.cargarModal(EntradaUnoControler.ESTABLECIMIENTO_SALUD,"Establecimiento de Salud", "Nuevo Establecimiento de Salud");
				mostrarEstablecimientoSalud();			}
		});
	
		
		
	}
	
	
	public void restricciones(){
		textFieldNombres.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ObservableList<String> styleClass = textFieldNombres.getStyleClass();
		        if (textFieldNombres.getText().trim().length()==0) {
		            if (! styleClass.contains("error")) {
		                styleClass.add("error");
		            }
		        } else {
		            // remove all occurrences:
		            styleClass.removeAll(Collections.singleton("error"));                    
		        }
			}
		});
		
		textFieldApellidos.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				ObservableList<String> styleClass = textFieldApellidos.getStyleClass();
		        if (textFieldApellidos.getText().trim().length()==0) {
		            if (! styleClass.contains("error")) {
		                styleClass.add("error");
		            }
		        } else {
		            // remove all occurrences:
		            styleClass.removeAll(Collections.singleton("error"));                    
		        }
			}
		});
		
		 comboBoxEstablecimientoSalud.valueProperty().addListener(new ChangeListener<EstablecimientoSalud>() {

			@Override
			public void changed(ObservableValue<? extends EstablecimientoSalud> observable,
					EstablecimientoSalud oldValue, EstablecimientoSalud newValue) {
				ObservableList<String> styleClass = comboBoxEstablecimientoSalud.getStyleClass();
		        if (comboBoxEstablecimientoSalud.getSelectionModel().getSelectedItem()==null) {
		            if (! styleClass.contains("error")) {
		                styleClass.add("error");
		            }
		        } else {
		            // remove all occurrences:
		            styleClass.removeAll(Collections.singleton("error"));                    
		        }
			}
		});;
		
		textFieldNombres.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        char character=event.getCharacter().charAt(0);
		        System.out.println(character);
		        if(!(Character.isLetter(character) || character==' ')){
		        	event.consume();
		        }                    
		    }});
		
		textFieldApellidos.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        char character=event.getCharacter().charAt(0);
		        System.out.println(character);
		        if(!(Character.isLetter(character) || character==' ')){
		        	event.consume();
		        }                    
		    }});
		
		textFieldDNI.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        char numero=event.getCharacter().charAt(0);

		        if(!Character.isDigit(numero)){
		        	event.consume();
		        }                    
		    }});
		
		textFieldCMP.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        char numero=event.getCharacter().charAt(0);

		        if(!Character.isDigit(numero)){
		        	event.consume();
		        }                    
		    }});
		
		textFieldRNE.addEventFilter(KeyEvent.KEY_TYPED, new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {
		        char numero=event.getCharacter().charAt(0);

		        if(!Character.isDigit(numero)){
		        	event.consume();
		        }                    
		    }});
	}

	
	public boolean verificarCamposVacios(){
		
		
		ObservableList<String> styleClass = textFieldDNI.getStyleClass();

		if(!textFieldDNI.getText().trim().isEmpty() && textFieldDNI.getText().trim().length()!=8){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			textFieldDNI.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}
		
	styleClass = textFieldNombres.getStyleClass();

		if(textFieldNombres.getText().trim().isEmpty()){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			textFieldNombres.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}
		
		
		
		
		styleClass = textFieldApellidos.getStyleClass();

		if(textFieldApellidos.getText().trim().isEmpty()){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			textFieldApellidos.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}
		
		/*styleClass = comboBoxEstablecimientoSalud.getStyleClass();

		if(comboBoxEstablecimientoSalud.getSelectionModel().getSelectedItem()==null){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			comboBoxEstablecimientoSalud.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}*/
		
		return true;
	}

	/*public void iniciarValoresTableViewDatosPersonaDiscapacidad() {

		tableViewEstablecimientoSalud.setItems(arrayTableViewDirectorEstablecimiento);
		tableColumnDNIMedico.setCellValueFactory(new PropertyValueFactory<DirectorEstablecimientoTable,String>("dni"));
		tableColumnCMPMedico.setCellValueFactory(new PropertyValueFactory<DirectorEstablecimientoTable,String>("cmp"));
		tableColumnDNIRNE.setCellValueFactory(new PropertyValueFactory<DirectorEstablecimientoTable,String>("rne"));
		//tableColumnEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

		tableColumnNombresApellidosMedico.setCellValueFactory(new PropertyValueFactory<DirectorEstablecimientoTable,String>("nombres_apellidos"));
		tableColumnEstablecimientoSalud.setCellValueFactory(new PropertyValueFactory<DirectorEstablecimientoTable,String>("establecimientoSalud"));
		tableColumnEspecialidadMedico.setCellValueFactory(new PropertyValueFactory<DirectorEstablecimientoTable,String>("especialidad"));
		

	}
*/
	
	public void insertarDatos(){
		ObservableList<String> styleClass = labelInformacion.getStyleClass();
        styleClass.removeAll(Collections.singleton("labelInformacionincorrecto"));  
        styleClass.removeAll(Collections.singleton("labelInformacioncorrecto")); 
		  
		String mensaje=null;
		  Connection conn=null;
		  PreparedStatement pst=null;
		  PreparedStatement pstUpdateidDirector=null;
		  ResultSet rsset=null;
		  try{     
			
			  
              Conexion.conectarDB(); 
  		

			  conn=Conexion.getConexion();
				conn.setAutoCommit(false);
               pst = conn.prepareStatement(" INSERT INTO DIRECTOR(DNI,CMP,RNE,NOMBRES,APELLIDOS,ESPECIALIDAD,OBSERVACION)VALUES"
               		+ "(?,?,?,?,?,?,?);",PreparedStatement.RETURN_GENERATED_KEYS); 
               
               if(!textFieldDNI.getText().trim().isEmpty()){
            	   pst.setString(1, textFieldDNI.getText().trim());
               }else{
            	  pst.setNull(1,java.sql.Types.VARCHAR);
               }
               if(!textFieldCMP.getText().trim().isEmpty()){
            	   pst.setString(2, textFieldCMP.getText().trim());
               }else{
            	   pst.setNull(2, java.sql.Types.VARCHAR);
               }
               if(!textFieldRNE.getText().trim().isEmpty()){
            	   pst.setString(3, textFieldRNE.getText().trim());
               }else{
            	   pst.setNull(3, java.sql.Types.VARCHAR);
               }
               pst.setString(4, textFieldNombres.getText().trim());
            
               pst.setString(5, textFieldApellidos.getText().trim());
            
               
               if(!textFieldEspecialidad.getText().trim().isEmpty()){
            	  pst.setString(6, textFieldEspecialidad.getText().trim()); 
               }else{
            	   pst.setNull(6, java.sql.Types.VARCHAR);
               }
               
              
               
               pst.setNull(7, java.sql.Types.VARCHAR);
             
           
               int rs = pst.executeUpdate();
               if(rs==1){
            	   labelInformacion.setText("Inserción Correcta");;
            	   
            	  
               }else {
            	   labelInformacion.setText("Error :(...");;

            	   
            	   
               }
               
               rsset=pst.getGeneratedKeys();
               while(rsset.next()){
            	   ultimoIDDirector=rsset.getInt(1);
               }
               
               if(comboBoxEstablecimientoSalud.getSelectionModel().getSelectedItem()!=null){
            	   pstUpdateidDirector=conn.prepareStatement(" UPDATE  ESTABLECIMIENTO_SALUD SET ID_DIRECTOR=? WHERE ID=?;");
            	   pstUpdateidDirector.setInt(1, ultimoIDDirector);
            	   pstUpdateidDirector.setInt(2, comboBoxEstablecimientoSalud.getSelectionModel().getSelectedItem().getId());
            	   pstUpdateidDirector.executeUpdate();
               }
               
               conn.commit();
               
               
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
	

	public void mostrarEstablecimientoSalud(){
		comboBoxEstablecimientoSalud.setItems(arrayEstablecimientoSalud);
		comboBoxEstablecimientoSalud.getItems().clear();
		//mostrarDatosDirectorEstablecimiento();
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
	
	

/*	public void mostrarDatosDirectorEstablecimiento() {
		mostrarDatosMedicoCertificador("");
	}

	public void mostrarDatosMedicoCertificador(String buscar) {
		// vamos limpiar el contenedor de los botones
		tableViewEstablecimientoSalud.getItems().clear();
		// hacer la conexcioon con la base de datos y obtener alas busquedas
		// encontradas
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			Conexion.conectarDB();
			conn = Conexion.getConexion();
			pst = conn.prepareStatement(" SELECT ID, DNI,CMP,RNE,NOMBRES,APELLIDOS,ESPECIALIDAD,OBSERVACION FROM DIRECTOR WHERE NOMBRES LIKE ?;");
			pst.setString(1, "%" + buscar + "%");
			rs = pst.executeQuery();

			System.out.println("estoy aqui 1");
			while (rs.next()) {
				DirectorEstablecimientoTable auxiliar=new DirectorEstablecimientoTable(rs.getInt("ID"), rs.getString("DNI"), 
						rs.getString("CMP"),rs.getString("RNE"), rs.getString("NOMBRES"), rs.getString("APELLIDOS")
						, rs.getString("ESPECIALIDAD"), null, rs.getString("OBSERVACION"));
					
					
				tableViewEstablecimientoSalud.getItems().add(auxiliar);
						

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

	

	
*/
	
	public void actualizarDatos(){
		ObservableList<String> styleClass = labelInformacion.getStyleClass();
        styleClass.removeAll(Collections.singleton("labelInformacionincorrecto"));  
        styleClass.removeAll(Collections.singleton("labelInformacioncorrecto")); 
		  
		String mensaje=null;
		  Connection conn=null;
		  PreparedStatement pst=null;
		  try{     
			
			  
              Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
               pst = conn.prepareStatement(" UPDATE DIRECTOR SET DNI=?, CMP=?,RNE=?,NOMBRES=?,APELLIDOS=?,ESPECIALIDAD=?,OBSERVACION=? WHERE ID=?");
               		 
               if(!textFieldDNI.getText().trim().isEmpty()){
            	   pst.setString(1, textFieldDNI.getText().trim());
               }else{
            	  pst.setNull(1,java.sql.Types.VARCHAR);
               }
               if(!textFieldCMP.getText().trim().isEmpty()){
            	   pst.setString(2, textFieldCMP.getText().trim());
               }else{
            	   pst.setNull(2, java.sql.Types.VARCHAR);
               }
               if(!textFieldRNE.getText().trim().isEmpty()){
            	   pst.setString(3, textFieldRNE.getText().trim());
               }else{
            	   pst.setNull(3, java.sql.Types.VARCHAR);
               }
               pst.setString(4, textFieldNombres.getText().trim());
            
               pst.setString(5, textFieldApellidos.getText().trim());
            
               
               if(!textFieldEspecialidad.getText().trim().isEmpty()){
            	  pst.setString(6, textFieldEspecialidad.getText().trim()); 
               }else{
            	   pst.setNull(6, java.sql.Types.VARCHAR);
               }
               
              
               
               pst.setNull(7, java.sql.Types.VARCHAR);
               pst.setInt(8, idActualizar);
             
           
               int rs = pst.executeUpdate();
               
              
               if(rs==1){
            	   labelInformacion.setText("Actualización Correcta");;
            	   
            	  
               }else {
            	   labelInformacion.setText("Error :(...");;

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
        	
        	   
        	   } catch (SQLException e) {
					
					e.printStackTrace();
				}
        	   
           }
		 
		
		
	}
	

	
	public void mostrarDatosDirectorEstablecimiento(int idEstSalud){
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("	SELECT ID, DNI, CMP, RNE, NOMBRES, APELLIDOS, ESPECIALIDAD, OBSERVACION FROM DIRECTOR WHERE ID=?;");
			pst.setInt(1, idEstSalud);;
			
			rs=pst.executeQuery();
			while(rs.next()){
				
				textFieldDNI.setText((rs.getString("DNI")!=null)?rs.getString("DNI"):"");
				
				textFieldCMP.setText((rs.getString("CMP")!=null)?rs.getString("CMP"):"");
				
				textFieldRNE.setText((rs.getString("RNE")!=null)?rs.getString("RNE"):"");
				textFieldNombres.setText((rs.getAsciiStream("NOMBRES")!=null)?rs.getString("NOMBRES"):"");
				textFieldApellidos.setText((rs.getString("APELLIDOS")!=null)?rs.getString("APELLIDOS"):"");
				textFieldEspecialidad.setText((rs.getString("ESPECIALIDAD")!=null)?rs.getString("ESPECIALIDAD"):"");
			
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