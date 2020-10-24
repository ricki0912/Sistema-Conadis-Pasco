package registro.personaConDiscapacidad.nuevoRegistroPD.establecimientoSalud;

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
import registro.personaConDiscapacidad.nuevoRegistroPD.DirectorEstablecimiento;
import registro.personaConDiscapacidad.nuevoRegistroPD.EstablecimientoSalud;
import registro.personaConDiscapacidad.nuevoRegistroPD.MedicoCertificador;
import registro.personaConDiscapacidad.nuevoRegistroPD.directorEstablecimiento.DirectorEstablecimientoControler;
import sesion.Sesion;



public class EstablecimientoSaludControler extends Funciones implements Initializable {
	
	public final static int INSERTAR =1;
	public final static  int ACTUALIZAR=2;
	private int accion=INSERTAR;
	
	int idActualizar=-1;
	public void setIdActualizar(int idActualizar){
		this.idActualizar=idActualizar;
	}
	
	
	private int idUltimoEstablecimientoSalud =-1;
    public int getIdUltimoEstablecimientoSalud() {
		return idUltimoEstablecimientoSalud;
	}


	public void setIdUltimoEstablecimientoSalud(int idUltimoEstablecimientoSalud) {
		this.idUltimoEstablecimientoSalud = idUltimoEstablecimientoSalud;
	}

	public void setAccion(int accion){
		if(accion==INSERTAR){
			this.accion =accion;
		}else if(accion==ACTUALIZAR){
			this.accion=accion;
		}
	}
	

	@FXML
    private BorderPane borderPaneNuevoPersonal;

    @FXML
    private Button buttonClose;

    @FXML
    private TextField textFieldNombre;

    @FXML
    private Label labelInformacion;

    @FXML
    private ComboBox<DirectorEstablecimiento> comboBoxDirector;

    @FXML
    private Button buttonGuardar;

    @FXML
    private Button buttonCancelar;

   // @FXML
    //private TableView<EstablecimientoSalud> tableViewEstablecimientoSalud;

    //@FXML
//    private TableColumn<EstablecimientoSalud, String> tableColumnNombre;

   // @FXML
    //private TableColumn<EstablecimientoSalud, String> tableColumnDirector;

   // @FXML
    //private TableColumn<EstablecimientoSalud, String> tableColumnDescripcion;

    @FXML
    private Button buttonModificar;

    @FXML
    private TextArea textAreaDescripcion;

    @FXML
    private Button buttonAnadirDirector;
    
	private ObservableList<DirectorEstablecimiento> arrayDirector=FXCollections.observableArrayList();

	//private ObservableList<EstablecimientoSalud> arrayTableViewEstablecimientoSalud = FXCollections.observableArrayList();

    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//iniciarValoresTableViewDatosPersonaDiscapacidad();
		mostrarDirectorEstablecimiento();
		restricciones();
		//mostrarDatosEstablecimientoSalud();
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
					//mostrarDatosEstablecimientoSalud();
				}
				}
		});
		
		buttonAnadirDirector.setOnAction(new EventHandler<ActionEvent>() {

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
					estab.getButtonAnadirEstablecimientoSalud().setVisible(false);
					estab.getLabelEstableSalud().setVisible(false);
					estab.getComboBoxEstablecimientoSalud().setVisible(false);
					
					Parent parent= fXMLLoader.getRoot();
					Scene scene=new Scene(parent);
					scene.setFill(new Color(0,0,0,0));
					scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
					Stage stage1=new Stage();
					stage1.setScene(scene);
					stage1.initModality(Modality.APPLICATION_MODAL);
					stage1.initStyle(StageStyle.TRANSPARENT);
					stage1.showAndWait();;
					mostrarDirectorEstablecimiento();
					
					comboBoxDirector.getSelectionModel().select(new DirectorEstablecimiento(estab.getUltimoIDDirector(), null));
					//comboBoxDirectorEstablecimiento.getSelectionModel().select(new DirectorEstablecimiento(estab.getUltimoIDDirector(), null));
					estab.getUltimoIDDirector();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
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
		
	
		
		 comboBoxDirector.valueProperty().addListener(new ChangeListener<DirectorEstablecimiento>() {

			@Override
			public void changed(ObservableValue<? extends DirectorEstablecimiento> observable,
					DirectorEstablecimiento oldValue, DirectorEstablecimiento newValue) {
				ObservableList<String> styleClass = comboBoxDirector.getStyleClass();
		        if (comboBoxDirector.getSelectionModel().getSelectedItem()==null) {
		            if (! styleClass.contains("error")) {
		                styleClass.add("error");
		            }
		        } else {
		            // remove all occurrences:
		            styleClass.removeAll(Collections.singleton("error"));                    
		        }
				
			}
		});
		
		
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
		
	
		
		styleClass = comboBoxDirector.getStyleClass();

		if(comboBoxDirector.getSelectionModel().getSelectedItem()==null){
			if (! styleClass.contains("error")) {
                styleClass.add("error");
              
            }
			comboBoxDirector.requestFocus();
			return false;
		}else{
		    styleClass.removeAll(Collections.singleton("error"));

		}
		
		return true;
	}

	/*public void iniciarValoresTableViewDatosPersonaDiscapacidad() {
		
	
		
		
		
		tableViewEstablecimientoSalud.setItems(arrayTableViewEstablecimientoSalud);
		tableColumnNombre.setCellValueFactory(new PropertyValueFactory<EstablecimientoSalud,String>("nombre"));
		tableColumnDirector.setCellValueFactory(new PropertyValueFactory<EstablecimientoSalud,String>("director"));
		tableColumnDescripcion.setCellValueFactory(new PropertyValueFactory<EstablecimientoSalud,String>("descripcion"));
		//tableColumnEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

		

	}*/

	
	public void insertarDatos(){
		ObservableList<String> styleClass = labelInformacion.getStyleClass();
        styleClass.removeAll(Collections.singleton("labelInformacionincorrecto"));  
        styleClass.removeAll(Collections.singleton("labelInformacioncorrecto")); 
		  
		String mensaje=null;
		  Connection conn=null;
		  PreparedStatement pst=null;
		  ResultSet rset=null;
		  try{     
			
			  
              Conexion.conectarDB(); 
			  conn=Conexion.getConexion();
               pst = conn.prepareStatement("  INSERT INTO ESTABLECIMIENTO_SALUD (NOMBRE,ID_DIRECTOR, OBSERVACION)VALUES"
               		+ "(?,?,?);",PreparedStatement.RETURN_GENERATED_KEYS); 
               
               pst.setString(1, textFieldNombre.getText().trim());
               pst.setInt(2, comboBoxDirector.getSelectionModel().getSelectedItem().getId());
               if(!textAreaDescripcion.getText().trim().isEmpty()){
            	   pst.setString(3, textAreaDescripcion.getText().trim());
               }else{
            	  pst.setNull(3,java.sql.Types.VARCHAR);
               }             
           
               int rs = pst.executeUpdate();
               if(rs==1){
            	   labelInformacion.setText("Inserción Correcta");;
            	   
            	  
               }else {
            	   labelInformacion.setText("Error :(...");;

               }
               rset=pst.getGeneratedKeys();
               while(rset.next()){
            	   idUltimoEstablecimientoSalud=rset.getInt(1);
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
        	   		if(rset!=null){
        	   			rset.close();
        	   		}
        	   
        	   } catch (SQLException e) {
					
					e.printStackTrace();
				}
        	   
           }
		 
		
		
	}
	
	

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
               pst = conn.prepareStatement(" UPDATE  ESTABLECIMIENTO_SALUD SET NOMBRE=?,ID_DIRECTOR=?, OBSERVACION=? WHERE ID=?;"); 
               
               pst.setString(1, textFieldNombre.getText().trim());
               pst.setInt(2, comboBoxDirector.getSelectionModel().getSelectedItem().getId());
               if(!textAreaDescripcion.getText().trim().isEmpty()){
            	   pst.setString(3, textAreaDescripcion.getText().trim());
               }else{
            	  pst.setNull(3,java.sql.Types.VARCHAR);
               }             
               pst.setInt(4, idActualizar);
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
	

	
	public void mostrarDatosEstablecimientoSalud(int idEstSalud){
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, NOMBRE, ID_DIRECTOR, OBSERVACION FROM ESTABLECIMIENTO_SALUD WHERE ID=?;");
			pst.setInt(1, idEstSalud);;
			
			rs=pst.executeQuery();
			while(rs.next()){
				textFieldNombre.setText(rs.getString("NOMBRE"));
				comboBoxDirector.getSelectionModel().select(new DirectorEstablecimiento(rs.getInt("ID_DIRECTOR"), null));
				if(rs.getString("OBSERVACION")!=null){
					textAreaDescripcion.setText(rs.getString("OBSERVACION"));
				}
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
		comboBoxDirector.setItems(arrayDirector);
		comboBoxDirector.getItems().clear();
		
		Connection conn=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			Conexion.conectarDB();
			conn=Conexion.getConexion();
			pst=conn.prepareStatement("SELECT ID, CONCAT(NOMBRES, ', ',APELLIDOS) AS NOMBRE FROM DIRECTOR ORDER BY NOMBRE");
			
			
			rs=pst.executeQuery();
			while(rs.next()){
				comboBoxDirector.getItems().add(new DirectorEstablecimiento(rs.getInt("ID"), rs.getString("NOMBRE")));
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
	
	
/*
	public void mostrarDatosEstablecimientoSalud() {
		mostrarDatosEstablecimientoSalud("");
	}

	public void mostrarDatosEstablecimientoSalud(String buscar) {
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
			pst = conn.prepareStatement("  SELECT ID, NOMBRE, (SELECT CONCAT(NOMBRES,', ',APELLIDOS) FROM DIRECTOR WHERE ID= ESTABLECIMIENTO_SALUD.ID_DIRECTOR) AS DIRECTOR, OBSERVACION FROM ESTABLECIMIENTO_SALUD WHERE NOMBRE LIKE ? ;");
			pst.setString(1, "%" + buscar + "%");
			rs = pst.executeQuery();

			System.out.println("estoy aqui 1");
			while (rs.next()) {
				EstablecimientoSalud auxiliar=new EstablecimientoSalud(rs.getInt("ID"), rs.getString("NOMBRE"), rs.getString("DIRECTOR"), rs.getString("OBSERVACION"));
					
					
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


  

}