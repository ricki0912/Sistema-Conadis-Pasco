package registro.personaConDiscapacidad.nuevoRegistroPD;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;

public class OrigenLimitacion {

	 

	
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
		OrigenLimitacion other = (OrigenLimitacion) obj;
		if (id != other.id)
			return false;
		return true;
	}



	public int getId() {
		return id;
	}
	
	
	
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public CheckBox getCheckBoxOrigenLimitacion() {
		return checkBoxOrigenLimitacion;
	}
	public void setCheckBoxLimitacionPermanente(CheckBox checkBoxOrigenLimitacion) {
		this.checkBoxOrigenLimitacion = checkBoxOrigenLimitacion;
	}
	private int id=-1;
	private String nombre=null;
	private CheckBox checkBoxOrigenLimitacion=null;
	public OrigenLimitacion(int id, String nombre, MenuButton menuButton){
		
		this.id=id;
		this.nombre=nombre;
		this.checkBoxOrigenLimitacion=new CheckBox(nombre);

		this.checkBoxOrigenLimitacion.setOnAction(new EventHandler<ActionEvent>() {
			
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
