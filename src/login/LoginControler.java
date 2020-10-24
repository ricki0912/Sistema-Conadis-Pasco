package login;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import cargando.Cargando;
import funciones.Conexion;
import funciones.Funciones;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import registro.personaConDiscapacidad.nuevoRegistroPD.Rol;
import sesion.Sesion;

public class LoginControler extends Funciones implements Initializable {

	
	
	
	@FXML private Hyperlink hiperlinkConfigurarServidor;
	@FXML
	
	
	private Button buttonIngresar;

		@FXML private JFXComboBox<Rol> jfxComboBoxRol;
	    @FXML
	    private JFXPasswordField jFXPasswordFieldContrasena;
	    
	    @FXML
	    private JFXButton jFXButtonClose;

	    private String stringFicheroUser="login/user";
	    private String stringFicheroPass="login/pass";
	    private String stringFicheroIP="servidor/ip";

	    @FXML
	    private JFXTextField jFXTextFieldUsuario;
	    @FXML JFXCheckBox jFXCheckBoxRecordarContrasena;
	    
	    @FXML
	    private Label labelAdvertencia;
	    
	    public LoginControler(){
	    	
	    }

	    
		private ObservableList<Rol> arrayComboBoxRol = FXCollections.observableArrayList();

		@Override
		public void initialize(URL location, ResourceBundle resources) {
			mostrarRol();
			
			//Inicializamos la conexion a la base de datos y vereficamos si esta vacio 
			Conexion.conectarDB();
			if(!(Conexion.getConexion()!=null)){
				labelAdvertencia.setText("Por favor.... Encienda el servidor.");
			}
			
			//cargamos los campos con los datos guardados
			jFXTextFieldUsuario.setText(leerFichero(stringFicheroUser));
			jFXPasswordFieldContrasena.setText(leerFichero(stringFicheroPass));
			
			jFXButtonClose.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					JFXButton jFXButton=(JFXButton)event.getSource();
					Stage stage=(Stage)jFXButton.getScene().getWindow();
					stage.close();
					
				}
			});
			
			buttonIngresar.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					
					
										
                	Conexion.conectarDB();
					
					Connection conn=Conexion.getConexion();
					PreparedStatement pst=null; 
					ResultSet rs=null;
					try{     
						
			               	pst= conn.prepareStatement("SELECT DNI, NOMBRES, APELL_PATERNO, APELL_MATERNO, FOTO, CARGO, ROL, CONTRASENNA, ESTADO, CREADO_POR FROM USUARIOS WHERE DNI=? AND CONTRASENNA=? AND ESTADO=1 AND ROL=?"); 
			                pst.setString(1, jFXTextFieldUsuario.getText().trim());
			                pst.setString(2, encriptar(jFXPasswordFieldContrasena.getText().trim()));
			                pst.setInt(3,  (jfxComboBoxRol.getSelectionModel().getSelectedItem()!=null)?jfxComboBoxRol.getSelectionModel().getSelectedItem().getId():-1);
			                rs = pst.executeQuery();
			                if(rs.next()){
			                	Sesion.DNI=rs.getObject("DNI").toString();
			                	Sesion.NOMBRES=rs.getString("NOMBRES").toString();
			                	Sesion.APELL_PATERNO=rs.getString("APELL_PATERNO").toString();
			                	Sesion.APELL_MATERNO=rs.getString("APELL_MATERNO").toString();
			                	Sesion.CARGO=rs.getString("CARGO");
			                	Sesion.ROL=rs.getInt("ROL");
			                	Sesion.ESTADO=rs.getInt("ESTADO");
			                	//obtener foto y convertir xD
			                	Blob imageBlob=rs.getBlob("FOTO");
			                	if(imageBlob!=null){
			                		ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(imageBlob.getBytes(1, (int)imageBlob.length()));
			                		Sesion.FOTO=new Image(byteArrayInputStream);
			                   	}else {
			                   		Sesion.FOTO=new Image("/personal/verPersonal/anadirPersonal/foto0.jpg");
			                   		//Sesion.FOTO=new Image("/src/personal/verPersonal/anadirPersonal/foto0.jpg");
			                	}
			                	Sesion.CONTRASENA=rs.getString("CONTRASENNA");
			                	Sesion.CREADO_POR=rs.getString("CREADO_POR");
			                	
			                	//guardamos el usuario y contrase�a para un posterior recuerdo
			                	if(jFXCheckBoxRecordarContrasena.isSelected())
			                	{
			                		escribirFichero(stringFicheroUser, jFXTextFieldUsuario.getText().trim() );
				                	escribirFichero(stringFicheroPass, jFXPasswordFieldContrasena.getText().trim());
			                	}else{
			                		escribirFichero(stringFicheroUser, jFXTextFieldUsuario.getText().trim() );
			                		escribirFichero(stringFicheroPass, "");
			                	}
			                	
			                	//ocultar el scene del login
			                	((Node)(arg0.getSource())).getScene().getWindow().hide();
					    		//mostrar cargando
			                	Stage cargando=Cargando.cargando();
			    				cargando.show();
								//cargar el interfaz principal
			    				Task<Void> task=new Task<Void>(){

									@Override
									protected Void call() throws Exception {
										Parent parent;
										try {
											parent = FXMLLoader.load(getClass().getResource("/principal/PrincipalInterfaz.fxml"));
											Scene scene=new Scene(parent);

											Platform.runLater(new Runnable() {
												
												@Override
												public void run() {
													Stage stage = new Stage();
													stage.setScene(scene);
													stage.setMaximized(true);
													stage.setTitle("Sistema CONADIS");
													stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
														
														@Override
														public void handle(WindowEvent arg0) {
															if(!cerrarVentana()){
																arg0.consume();
																
															}
															
														}
													});
													stage.show();	
													cargando.close();

													
													
												}
												
												
											});
										} catch (IOException e) {
											
											e.printStackTrace();
										}										return null;
									}
			    					
			    				};
			    				Thread hilo=new Thread(task);
			    				hilo.setDaemon(true);
			    				hilo.start();
			                	
			       
			                	System.out.println( rs.getObject(1).toString());
			                }else { 
			                	labelAdvertencia.setText("Usuario y/o Contrase�a Incorrecto(s)");
			                }
			                
			        			               
			            }   
			            catch(Exception e){
			                labelAdvertencia.setText("No se pudo conectar con la base de datos :(");
			                e.printStackTrace();
			           
			            }finally{
			            	 try {
			            		 pst.close();
			            		 rs.close();
			            		 conn.close();
								
							} catch (SQLException e) {
								e.printStackTrace();
							}
				                
				                
				                
			            }
					 
					 
				}
			});
			
			
			hiperlinkConfigurarServidor.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent arg0) {
					
					TextInputDialog texInputDialogIp=new TextInputDialog(leerFichero(stringFicheroIP));
					texInputDialogIp.setTitle("Congigurar IP Servidor");
					texInputDialogIp.setHeaderText("");
					texInputDialogIp.setContentText("Ingrese IP-SERVIDOR:");

					// Traditional way to get the response value.
					Optional<String> result = texInputDialogIp.showAndWait();
					if (result.isPresent()){
					   escribirFichero(stringFicheroIP, result.get());
					   Conexion.HOST_NAME=Conexion.getIpServidor();
					   
					 Hyperlink hyperlink=(Hyperlink)arg0.getSource();
					Stage stage=(Stage)hyperlink.getScene().getWindow();
					stage.close();
					
					Parent parent;
					try {
						parent = FXMLLoader.load(getClass().getResource("/login/Login.fxml"));
						Stage stageLogin = new Stage();
						Scene scene=new Scene(parent);
						stageLogin.setScene(scene);
						stageLogin.initStyle(StageStyle.UNDECORATED);
						stageLogin.show();
					} catch (IOException e) {
						
						e.printStackTrace();
					}
				
				}
					
				}
			});
		}
		
		
		
		public boolean cerrarVentana(){
			
			Alert alert = new Alert(AlertType.CONFIRMATION, "�Est� seguro que desea salir :( ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
			alert.showAndWait();

			if (alert.getResult() == ButtonType.YES) {
			    return true;
			}else {
				return false;
			}
		}

		private String leerFichero(String url){
			
			 File archivo = null;
		      FileReader fr = null;
		      BufferedReader br = null;
		         String texto="";


		      try {
		         // Apertura del fichero y creacion de BufferedReader para poder
		         // hacer una lectura comoda (disponer del metodo readLine()).
		         archivo = new File (url); //"C:\\archivo.txt"
		         fr = new FileReader (archivo);
		         br = new BufferedReader(fr);

		         // Lectura del fichero
		         //texto
		         String linea;
		         while((linea=br.readLine())!=null){
		        	 texto=texto+linea;
		         }
		         
		      }
		      catch(Exception e){
		         e.printStackTrace();
		      }finally{
		         // En el finally cerramos el fichero, para asegurarnos
		         // que se cierra tanto si todo va bien como si salta 
		         // una excepcion.
		         try{                    
		            if( null != fr ){   
		               fr.close();     
		            }                  
		         }catch (Exception e2){ 
		            e2.printStackTrace();
		         }
		      }
			
		      return texto;
			
		}
  
		
		
		private void escribirFichero(String url, String texto){
			
			  

			FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(url);//url
            pw = new PrintWriter(fichero);

            //for (int i = 0; i < 10; i++)
                pw.println(texto);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
		        
		        
		 
			
			
			
			
		}
		
		public void  mostrarRol(){
			arrayComboBoxRol.add(new Rol(1, "Administrador"));
			arrayComboBoxRol.add(new Rol(0, "Usuario"));
			jfxComboBoxRol.getItems().addAll(arrayComboBoxRol);
			
		}
		

}