package registro.personaConDiscapacidad;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class OpcionesControler extends HBox{
	public OpcionesControler(){
		FXMLLoader loader=new FXMLLoader(getClass().getResource("Opciones.fxml"));
		//getStylesheets().add(getClass().getResource("Opciones.css").toExternalForm());
		loader.setRoot(this);
		loader.setController(this);
		try{
			loader.load();
		}catch(IOException e){
	
			
		}
		
	}
		public JFXButton getjFXButtonVer() {
		return jFXButtonVer;
	}

	public JFXButton getjFXButtonEditar() {
		return jFXButtonEditar;
	}

	public JFXButton getjFXButtonExportar() {
		return jFXButtonExportar;
	}
		@FXML
	    private JFXButton jFXButtonVer;

	    @FXML
	    private JFXButton jFXButtonEditar;

	    @FXML
	    private JFXButton jFXButtonExportar;

	    @FXML
	    private JFXButton jFXButtonEliminar;

		public JFXButton getjFXButtonEliminar() {
			return jFXButtonEliminar;
		}

}
