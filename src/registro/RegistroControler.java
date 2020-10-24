package registro;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import funciones.Funciones;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import principal.PrincipalControler;





public class RegistroControler extends Funciones implements Initializable {

    @FXML
    private Label labelOpcionSeleccionada;

  

    @FXML
    private ToggleButton toggleButtonPersonaConDiscapacidad;
    
    @FXML private ToggleButton toggleButtonDatosGenerales;
    
    private BorderPane borderPanePersonaConDiscapacidad;
    private BorderPane borderPaneDatosGenerales;

    
    
    
    @FXML
    private BorderPane borderPane_ventanaInterna;
	
    public RegistroControler() {
		cargarVentanasInternas();

	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		labelOpcionSeleccionada.setText("Registro");
	
		setInterfazInterna(borderPane_ventanaInterna, borderPanePersonaConDiscapacidad);
		
		toggleButtonPersonaConDiscapacidad.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				labelOpcionSeleccionada.setText("Persona con Discapacidad");
				ToggleButton toggleButton=(ToggleButton)event.getSource();
				
				if(toggleButton.isSelected()){
				
					setInterfazInterna(borderPane_ventanaInterna, borderPanePersonaConDiscapacidad);
				}else {
					toggleButton.setSelected(true);
					
				}
				
			}
		});
		
		toggleButtonDatosGenerales.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				labelOpcionSeleccionada.setText("Datos Generales");
				ToggleButton toggleButton=(ToggleButton)arg0.getSource();
				
				if(toggleButton.isSelected()){
				
					setInterfazInterna(borderPane_ventanaInterna, borderPaneDatosGenerales);
				}else {
					toggleButton.setSelected(true);
					
				}
			}
		});
		

	
	}
	
	
	public void cargarVentanasInternas(){
		try {
			borderPanePersonaConDiscapacidad =obtenerBorderPaneMostrar("/registro/personaConDiscapacidad/PersonaConDiscapacidadInterfaz2.fxml", "/registro/personaConDiscapacidad/personaConDiscapacidadInterfaz.css");
			borderPaneDatosGenerales=obtenerBorderPaneMostrar("/registro/datosGenerales/DatosGeneralesInterfaz.fxml", "/registro/datosGenerales/datosGeneralesInterfaz.css");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	


  

}