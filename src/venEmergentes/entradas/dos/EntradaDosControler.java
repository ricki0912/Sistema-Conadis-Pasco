package venEmergentes.entradas.dos;


import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;

import funciones.Conexion;
import funciones.Funciones;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import sesion.Sesion;





public class EntradaDosControler extends Funciones  implements Initializable {
	
	public static EntradaDosControler controler=null;
	public  EntradaDosControler cargarModal(int opcion, String  titulo, String subtitulo){
		FXMLLoader fXMLLoader=new FXMLLoader();
		fXMLLoader.setLocation(getClass().getResource("/venEmergentes/entradas/uno/EntradaUno.fxml"));
		try {
			fXMLLoader.load();
			EntradaDosControler.controler=fXMLLoader.getController();
			EntradaDosControler.controler.setOpcionSeleccionada(opcion);
			EntradaDosControler.controler.labelTitulo.setText(titulo);
			EntradaDosControler.controler.labelEntrada1.setText(subtitulo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Parent parent= fXMLLoader.getRoot();
		Scene scene=new Scene(parent);
		scene.setFill(new Color(0,0,0,0));
		scene.getStylesheets().add(getClass().getResource("/venEmergentes/entradas/uno/entradaUno.css").toExternalForm());
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.showAndWait();;
		return controler;
	}
	private int opcionSeleccionada=-1;
	
	
	
	
	public int getOpcionSeleccionada() {
		return opcionSeleccionada;
	}




	public void setOpcionSeleccionada(int opcionSeleccionada) {
		this.opcionSeleccionada = opcionSeleccionada;
	}




	public Label getLabelEntrada1() {
		return labelEntrada1;
	}




	public void setLabelEntrada1(Label labelEntrada1) {
		this.labelEntrada1 = labelEntrada1;
	}



	
	public static  final int PROGRAMA_SOCIAL=1;
	public static final int PROCEDENCIA=2;
	public static final int TIPO_VIA=3;
	public static final int TIPO_DISCAPACIDAD=4;
	public static final int LIMITACION_PERMANENTE=5;
	public static final int REQUERIMIENTO_APOYO=6;
	public static final int TIPO_AYUDA_BIOMETRICA=7;
	public static final int TIPO_SEGURO_MEDICO=8;
	public static final int ESTABLECIMIENTO_SALUD=9;
	
	
	
	@FXML
    private Label labelEntrada1;

    @FXML
    private Button buttonGuardar;

    @FXML
    private Button buttonCancelar;

    @FXML
    private Label labelInformacion;

    @FXML
    private BorderPane borderPaneNuevoPersonal;

    @FXML
    private Label labelTitulo;

    @FXML
    private Button buttonClose;

    @FXML
    private TextField textFieldEntrada1;

    
    
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		buttonGuardar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(verificarRestricciones()){
					insertarDatos();
				}
			}
			
		}	
		);
		
		buttonCancelar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				Button button=(Button)event.getSource();
				Stage stage=(Stage)button.getScene().getWindow();
				stage.close();
				
			}
		});
		
	buttonClose.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Button button=(Button)event.getSource();
				Stage stage=(Stage)button.getScene().getWindow();
				stage.close();
				
			}
		});
		
		
	
	}
	
	public void cerrar(){
		
		Stage stage=(Stage)buttonClose.getScene().getWindow();
		stage.close();
		
	}
public boolean verificarRestricciones(){
	if(textFieldEntrada1.getText().trim().isEmpty()){
		labelInformacion.setText("Campo vacio");
		textFieldEntrada1.requestFocus();
		return false;
	}
	return true;
}
	
	
public String insertarDatos(){
		
		String mensaje=null;
	  Connection conn=null;
	  PreparedStatement pst=null;
	  try{     
		
		  
          Conexion.conectarDB(); 
		  conn=Conexion.getConexion();
		  if(opcionSeleccionada==EntradaDosControler.PROGRAMA_SOCIAL){
           pst = conn.prepareStatement("INSERT INTO PROGRAMA_SOCIAL(NOMBRE) VALUES (?);"); 
		  }else if(opcionSeleccionada==EntradaDosControler.PROCEDENCIA){
	           pst = conn.prepareStatement("INSERT INTO TIPO_PROCEDENCIA(NOMBRE) VALUES (?);"); 
		  }else if(opcionSeleccionada==EntradaDosControler.TIPO_VIA){
			  pst = conn.prepareStatement("INSERT INTO TIPO_VIA(NOMBRE) VALUES (?);");
		  }else if(opcionSeleccionada==EntradaDosControler.TIPO_DISCAPACIDAD){
			  pst = conn.prepareStatement("INSERT INTO TIPO_DISCAPACIDAD(NOMBRE) VALUES (?);");
		  }else if(opcionSeleccionada==EntradaDosControler.LIMITACION_PERMANENTE){
			  pst = conn.prepareStatement("INSERT INTO LIMITACION_PERMANENTE_PARA(NOMBRE) VALUES (?);");
		  }else if(opcionSeleccionada==EntradaDosControler.REQUERIMIENTO_APOYO){
			  pst = conn.prepareStatement("INSERT INTO REQUERIMIENTO_APOYO(NOMBRE)VALUES(?);");
		  }else if(opcionSeleccionada==EntradaDosControler.TIPO_AYUDA_BIOMETRICA){
			  pst = conn.prepareStatement("INSERT INTO TIPO_AYUDA_BIOMECANICA(NOMBRE) VALUES (?);");
		  }else if(opcionSeleccionada==EntradaDosControler.TIPO_SEGURO_MEDICO){
			  pst = conn.prepareStatement("INSERT INTO TIPO_SEGURO(NOMBRE) VALUES (?);");
		  }else if(opcionSeleccionada==EntradaDosControler.ESTABLECIMIENTO_SALUD){
			  pst = conn.prepareStatement("INSERT INTO establecimiento_salud( NOMBRE) VALUES (?)");
		  }
		  pst.setString(1, textFieldEntrada1.getText().trim());
           
           int rs = pst.executeUpdate();
           if(rs==1){
        	   mensaje ="Se ejecuto correctamente la inserción y/o actualización de datos..";
        	  
           }else {
        	   mensaje= "Fallo la inserción y/o actualización de datos... :(";
        	   
           }
           
           
           conn.close();
           pst.close();
           
           
           
           cerrar();
           
       }   
       catch(SQLException e){
    	   mensaje=e.getMessage();
    	   labelInformacion.setText(e.getMessage());
    	   System.out.println(e.getMessage());
           e.printStackTrace();
      
       }  finally{
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
	
	return mensaje;
}

	


  

}