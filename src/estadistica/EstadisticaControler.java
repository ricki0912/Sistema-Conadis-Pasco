package estadistica;

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
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import principal.PrincipalControler;






 
public class EstadisticaControler extends Funciones implements Initializable {

    @FXML
    private Label labelOpcionSeleccionada;


    
    @FXML
    private BorderPane borderPane_ventanaInterna;
	
    
    @FXML MenuItem menuItemProvinciaDistrito;
    
    @FXML private MenuItem menuItemTipoDiscapacidad, menuItemTipoProgramaSocial_ClasificacionSisfoh, menuItemEdad_Genero,menuItemBusquedaAvanzada, menuItemBusquedaPersonalizada, menuItemNivelEducativo, menuItemTipoTrabajo;
    
    
    
    
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		labelOpcionSeleccionada.setText("Estadística");
		
		//carga estadistica de distritos
		String url="/estadistica/provincia_distrito/EstadisticaProvinciaDistrito.fxml";
		String css="/estadistica/provincia_distrito/estadisticaProvinciaDistrito.css";
		try {
			setInterfazInterna(borderPane_ventanaInterna, url,css);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		//incia en esto 
		
		menuItemTipoProgramaSocial_ClasificacionSisfoh.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				
				String url="/estadistica/programaSocial_ClasificacionSisfoh/EstadisticaProgramaSocial_ClasificacionSisfoh.fxml";
				String css="/estadistica/programaSocial_ClasificacionSisfoh/estadisticaProgramaSocial_ClasificacionSisfoh.css";
				
				try {
					;
					setInterfazInterna(borderPane_ventanaInterna, url,css);
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
		});
	
		menuItemTipoDiscapacidad.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				String url="/estadistica/tipoDiscapacidad_gravedad/EstadisticaTipoDiscapacidad.fxml";
				String css="/estadistica/tipoDiscapacidad_gravedad/estadisticaTipoDiscapacidad.css";
				try {
					setInterfazInterna(borderPane_ventanaInterna, url,css);
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
				
			}
		});
		menuItemEdad_Genero.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String url="/estadistica/edad_sexo/EstadisticaEdad_Sexo.fxml";
				String css="/estadistica/edad_sexo/estadisticaEdadSexo.css";
				try {
					setInterfazInterna(borderPane_ventanaInterna, url,css);
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}				
			}
		});
		menuItemProvinciaDistrito.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				
				
					String url="/estadistica/provincia_distrito/EstadisticaProvinciaDistrito.fxml";
					String css="/estadistica/provincia_distrito/estadisticaProvinciaDistrito.css";
					try {
						setInterfazInterna(borderPane_ventanaInterna, url,css);
						
					} catch (IOException e) {
						
						e.printStackTrace();
					}
							
			}
		});
		menuItemNivelEducativo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				String url="/estadistica/nivel_educativo/EstadisticaNivelEducativo.fxml";
				String css="/estadistica/nivel_educativo/estadisticaNivelEducativo.css";
				try {
					setInterfazInterna(borderPane_ventanaInterna, url,css);
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		});
		
		menuItemTipoTrabajo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				String url="/estadistica/tipo_trabajo/EstadisticaTipoTrabajo.fxml";
				String css="/estadistica/tipo_trabajo/estadisticaTipoTrabajo.css";
				try {
					setInterfazInterna(borderPane_ventanaInterna, url,css);
					
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}
		});
		
		menuItemBusquedaPersonalizada.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				
				
					String url="/estadistica/busquedaPersonalizada/EstadisticaBusquedaPersonalizada.fxml";
					String css="/estadistica/busquedaPersonalizada/estadisticaBusquedaPersonalizada.css";
					try {
						setInterfazInterna(borderPane_ventanaInterna, url,css);
						
					} catch (IOException e) {
						
						e.printStackTrace();
					}
							
			}
		});
		
		menuItemBusquedaAvanzada.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				
				
					String url="/estadistica/busquedaAvanzada/EstadisticaBusquedaAvanzada.fxml";
					String css="/estadistica/busquedaAvanzada/estadisticaBusquedaAvanzada.css";
					try {
						setInterfazInterna(borderPane_ventanaInterna, url,css);
						
					} catch (IOException e) {
						
						e.printStackTrace();
					}
							
			}
		});
		
		
		
	
	}
	
	

	

	


  

}