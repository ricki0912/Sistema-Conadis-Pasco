package principal;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import cargando.Cargando;
import cargando.CargandoControlerAnchorPane;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import funciones.Funciones;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
import sesion.Sesion;



public class PrincipalControler extends Funciones implements Initializable {
	@FXML private StackPane stackPnaeLateralOpciones;
	@FXML private StackPane stackPaneFodoSemiTransparente;
	@FXML private StackPane stackPaneCargando;
	@FXML private ToggleButton toggleButtonRegistro;
	@FXML private BorderPane borderPane_ventanaInterna;
	
	
	@FXML private FontAwesomeIconView imageViewMenu;
	@FXML private BorderPane borderPaneLateralOpciones;
	@FXML private ScrollPane scrollPaneLateralOpciones;
    @FXML private ToggleButton toggleButtonPerfil;
    @FXML private ToggleButton toggleButtonPersonal, toggleButtonAcercaDe, toggleButtonInicio, toggleButtonGestMedicoCert;
    @FXML private Circle CircleFotoPerfil;
    @FXML private Circle circleFotoPerfilGrande;
    @FXML private Label labelNombreUsuario;
    @FXML private Label labelCargoUsuario;
    
    @FXML private VBox vBoxMenu;
    
    @FXML private ToggleButton toggleButtonEstadistica;
    @FXML private Button buttonSalir;
    
    private boolean booleanMenuMostrar=false;
    
    private AnchorPane anchorPaneIncio;
    private BorderPane borderPaneRegistro;
    
    private BorderPane borderPaneGestMedicoCert;
    private BorderPane borderPaneEstadistica;
    private BorderPane borderPanePersonal;
    private BorderPane borderPanePerfil;
    private AnchorPane anchorPaneAcercaDe;
       
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ocultarMenu();
		cargarVentanasInternas();
		labelNombreUsuario.setText(Sesion.NOMBRES);
		labelCargoUsuario.setText(Sesion.CARGO);
		CircleFotoPerfil.setFill(new ImagePattern(Sesion.FOTO));	
		circleFotoPerfilGrande.setFill(new ImagePattern(Sesion.FOTO));

		if(Sesion.ROL==0){
			vBoxMenu.getChildren().remove(toggleButtonPersonal);
			vBoxMenu.getChildren().remove(toggleButtonGestMedicoCert);

		}
		
		//muestra la la interfaz de inicio 
		borderPane_ventanaInterna.getChildren().clear();
	 	AnchorPane.setTopAnchor(anchorPaneIncio, 0.0);
    	AnchorPane.setRightAnchor(anchorPaneIncio, 0.0);
    	AnchorPane.setLeftAnchor(anchorPaneIncio, 0.0);
    	AnchorPane.setBottomAnchor(anchorPaneIncio, 0.0);
		 borderPane_ventanaInterna.setCenter(anchorPaneIncio);
				
		
		toggleButtonRegistro.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				ocultarMenu();
				ToggleButton toggleButton=(ToggleButton)event.getSource();
						if(toggleButton.isSelected()){
							
							setInterfazInterna(borderPane_ventanaInterna, borderPaneRegistro);
							
						}else {
							toggleButton.setSelected(true);				
						}

			}
		});
		
		stackPnaeLateralOpciones.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				
			}
			
			
		});  
		
		toggleButtonInicio.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				ocultarMenu();				
				ToggleButton toggleButton=(ToggleButton)arg0.getSource();
				
				if(toggleButton.isSelected()){
					borderPane_ventanaInterna.getChildren().clear();
				 	AnchorPane.setTopAnchor(anchorPaneIncio, 0.0);
			    	AnchorPane.setRightAnchor(anchorPaneIncio, 0.0);
			    	AnchorPane.setLeftAnchor(anchorPaneIncio, 0.0);
			    	AnchorPane.setBottomAnchor(anchorPaneIncio, 0.0);
					 borderPane_ventanaInterna.setCenter(anchorPaneIncio);
				}else {
					toggleButton.setSelected(true);				
				}
				
			}
		});
		
		toggleButtonAcercaDe.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				ocultarMenu();				
				ToggleButton toggleButton=(ToggleButton)arg0.getSource();
				if(toggleButton.isSelected()){
										
					borderPane_ventanaInterna.getChildren().clear();
				 	AnchorPane.setTopAnchor(anchorPaneAcercaDe, 0.0);
			    	AnchorPane.setRightAnchor(anchorPaneAcercaDe, 0.0);
			    	AnchorPane.setLeftAnchor(anchorPaneAcercaDe, 0.0);
			    	AnchorPane.setBottomAnchor(anchorPaneAcercaDe, 0.0);
					 borderPane_ventanaInterna.setCenter(anchorPaneAcercaDe);
				}else {
					toggleButton.setSelected(true);				
				}
			}
		});
		
		toggleButtonEstadistica.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				ocultarMenu();
				ToggleButton toggleButton=(ToggleButton)event.getSource();
				if(toggleButton.isSelected()){
								
					setInterfazInterna(borderPane_ventanaInterna, borderPaneEstadistica);
				}else {
					toggleButton.setSelected(true);				
				}
				
			}
		});
		
		
		buttonSalir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(cerrarVentana()){
					Stage stage=(Stage)stackPaneFodoSemiTransparente.getScene().getWindow();
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

		
		
		
		imageViewMenu.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				mostrarMenu();
			}
		});
		
		toggleButtonPerfil.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				ocultarMenu();		
				ToggleButton toggleButton=(ToggleButton)event.getSource();
				if(toggleButton.isSelected()){
					setInterfazInterna(borderPane_ventanaInterna, borderPanePerfil);
				}else{
					toggleButton.setSelected(true);
					
				}
			}
		});
		
		stackPaneFodoSemiTransparente.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				ocultarMenu();
				
			}
		});
		
		toggleButtonPersonal.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				ocultarMenu();
				ToggleButton toggleButton=(ToggleButton)event.getSource();
				if(toggleButton.isSelected()){
					
					setInterfazInterna(borderPane_ventanaInterna, borderPanePersonal);
					
				}else{
					toggleButton.setSelected(true);
				}
			}
			
		});
		
		toggleButtonGestMedicoCert.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event){
				ocultarMenu();
				ToggleButton toggleButton=(ToggleButton)event.getSource();
				if(toggleButton.isSelected()){
					
					setInterfazInterna(borderPane_ventanaInterna, borderPaneGestMedicoCert);
					
				}else{
					toggleButton.setSelected(true);
				}
			}
			
		});
	}

	public void cargarVentanasInternas(){
		try {
			anchorPaneIncio=obtenerAnchorPaneMostrar("/inicio/PrincipalInicioInterfaz.fxml", "");
			borderPaneRegistro=obtenerBorderPaneMostrar("/registro/RegistroInterfaz.fxml","/registro/registroInterfaz.css");
			borderPaneGestMedicoCert=obtenerBorderPaneMostrar("/gestMedicoCert/GestMedicoCertInterfaz.fxml", "/gestMedicoCert/gestMedicoCertInterfaz.css");
			borderPaneEstadistica=obtenerBorderPaneMostrar("/estadistica/EstadisticaInterfaz.fxml","/estadistica/estadisticaInterfaz.css");
			borderPanePersonal=obtenerBorderPaneMostrar("/personal/PersonalInterfaz.fxml","/personal/personalInterfaz.css");
			borderPanePerfil=obtenerBorderPaneMostrar("/perfil/PerfilInterfaz.fxml","/perfil/perfilInterfaz.css");
			anchorPaneAcercaDe=obtenerAnchorPaneMostrar("/proyeccion/PrincipalInterfazUsuariosInterfaz.fxml", "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	public StackPane getStackPaneFodoSemiTransparente() {
		return stackPaneFodoSemiTransparente;
	}
	
	public boolean cerrarVentana(){
		
		Alert alert = new Alert(AlertType.CONFIRMATION, "¿Está seguro que desea salir :( ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
		    return true;
		}else {
			return false;
		}
	}
	
	public void mostrarMenu(){
		stackPnaeLateralOpciones.setTranslateX(-211);
		TranslateTransition menuLateral=new TranslateTransition(Duration.millis(200.0), stackPnaeLateralOpciones);
		menuLateral.setByX(211);
		menuLateral.play();
		booleanMenuMostrar=false;
		stackPaneFodoSemiTransparente.setVisible(true);
	}
	
	public void ocultarMenu(){
		stackPnaeLateralOpciones.setTranslateX(0);
		TranslateTransition menuLateral= new TranslateTransition(Duration.millis(200.0), stackPnaeLateralOpciones );
		menuLateral.setByX(-211);
		menuLateral.play();
		booleanMenuMostrar=true;
		stackPaneFodoSemiTransparente.setVisible(false);
	}
	
	
	public void disponibleToggleMenu(){
		toggleButtonInicio.setDisable(false);
		toggleButtonRegistro.setDisable(false);
		toggleButtonEstadistica.setDisable(false);
		toggleButtonPersonal.setDisable(false);
		toggleButtonPerfil.setDisable(false);
		toggleButtonAcercaDe.setDisable(false);
	}

	 private void setNode(Node node) {
	        //holderPane.getChildren().clear();
	        //holderPane.getChildren().add((Node) node);

	        FadeTransition ft = new FadeTransition(Duration.millis(2000));
	        ft.setNode(node);
	        ft.setFromValue(0.1);
	        ft.setToValue(1);
	        ft.setCycleCount(1);
	        ft.setAutoReverse(false);
	        ft.play();
	    }
	    

}