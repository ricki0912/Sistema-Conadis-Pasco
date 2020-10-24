package gestMedicoCert;

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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import principal.PrincipalControler;





public class GestMedicoCertControler extends Funciones implements Initializable {

	 @FXML
	    private Label labelOpcionSeleccionada;

	    @FXML
	    private ToggleButton toggleButtonEstablecimientoSalud;

	    @FXML
	    private ToggleGroup hola;

	    @FXML
	    private ToggleButton toggleButtonDirectorEstablecimiento;

	    @FXML
	    private ToggleButton toggleButtonMedicoCertificador;

	    @FXML
	    private BorderPane borderPane_ventanaInterna;
	    
	    private BorderPane borderPaneEstablecimientoSalud;
	    private BorderPane borderPaneDirectorEstablecimiento;
	    private BorderPane borderPaneMedicoCertificador;
	
    public GestMedicoCertControler() {
		cargarVentanasInternas();

	}
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		labelOpcionSeleccionada.setText("Gestión de Medicos Certificadores");
	
		setInterfazInterna(borderPane_ventanaInterna, borderPaneEstablecimientoSalud);
		
		toggleButtonEstablecimientoSalud.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				labelOpcionSeleccionada.setText("Establecimiento de Salud");
				ToggleButton toggleButton=(ToggleButton)event.getSource();
				
				if(toggleButton.isSelected()){
				
					setInterfazInterna(borderPane_ventanaInterna, borderPaneEstablecimientoSalud);
				}else {
					toggleButton.setSelected(true);
					
				}
				
			}
		});
		
		toggleButtonDirectorEstablecimiento.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				labelOpcionSeleccionada.setText("Director de Establecimiento");
				ToggleButton toggleButton=(ToggleButton)arg0.getSource();
				
				if(toggleButton.isSelected()){
				
					setInterfazInterna(borderPane_ventanaInterna, borderPaneDirectorEstablecimiento);
				}else {
					toggleButton.setSelected(true);
					
				}
			}
		});
		
		toggleButtonMedicoCertificador.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				labelOpcionSeleccionada.setText("Medico Certificador");
				ToggleButton toggleButton=(ToggleButton)arg0.getSource();
				
				if(toggleButton.isSelected()){
				
					setInterfazInterna(borderPane_ventanaInterna, borderPaneMedicoCertificador);
				}else {
					toggleButton.setSelected(true);
					
				}
			}
		});

	
	}
	
	
	public void cargarVentanasInternas(){
		try {
			borderPaneEstablecimientoSalud=obtenerBorderPaneMostrar("/gestMedicoCert/establecimiento_salud/EstablecimientoSalud.fxml", "/gestMedicoCert/establecimiento_salud/establecimientoSalud.css");
			borderPaneDirectorEstablecimiento =obtenerBorderPaneMostrar("/gestMedicoCert/director_establecimiento/DirectorEstablecimiento.fxml", "/gestMedicoCert/director_establecimiento/directorEstablecimiento.css");
			borderPaneMedicoCertificador=obtenerBorderPaneMostrar("/gestMedicoCert/medico_certificador/MedicoCertificador.fxml", "/gestMedicoCert/medico_certificador/medicoCertificador.css");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	


  

}