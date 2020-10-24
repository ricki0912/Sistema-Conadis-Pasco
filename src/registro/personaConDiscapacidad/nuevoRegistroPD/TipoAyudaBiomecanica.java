package registro.personaConDiscapacidad.nuevoRegistroPD;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;

public class TipoAyudaBiomecanica {

	 
private MenuButton menuButton=null;
	
	public int getId() {
		return id;
	}
	
	
	
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoAyudaBiomecanica other = (TipoAyudaBiomecanica) obj;
		if (id != other.id)
			return false;
		return true;
	}



	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public CheckBox getCheckBoxTipoAyudaBiometrica() {
		return checkBoxTipoAyudaBiometrica;
	}
	public void setCheckBoxLimitacionPermanente(CheckBox checkBoxTipoAyudaBiometrica) {
		this.checkBoxTipoAyudaBiometrica = checkBoxTipoAyudaBiometrica;
	}
	private int id=-1;
	private String nombre=null;
	private CheckBox checkBoxTipoAyudaBiometrica=null;
	public  TipoAyudaBiomecanica(int id, String nombre, MenuButton menuButton){
		
		this.id=id;
		this.nombre=nombre;
		this.checkBoxTipoAyudaBiometrica=new CheckBox(nombre);
		this.menuButton=menuButton;
		this.checkBoxTipoAyudaBiometrica.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				CheckBox cb=(CheckBox)event.getSource();
				menuButton.setText("es una prueba");
				event.consume();
			}
		});
		
		
	}
	
	
	
	@Override
	public String toString(){
		return this.nombre;
	}
	
	
	
	
	
	
	
}
