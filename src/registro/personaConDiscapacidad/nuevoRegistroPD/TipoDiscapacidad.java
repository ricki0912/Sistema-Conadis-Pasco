package registro.personaConDiscapacidad.nuevoRegistroPD;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;

public class TipoDiscapacidad {

	 

	
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
		TipoDiscapacidad other = (TipoDiscapacidad) obj;
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

	
	public CheckBox getCheckBoxTipoDiscapacidad() {
		return checkBoxTipoDiscapacidad;
	}



	public void setCheckBoxTipoDiscapacidad(CheckBox checkBoxTipoDiscapacidad) {
		this.checkBoxTipoDiscapacidad = checkBoxTipoDiscapacidad;
	}


	private int id=-1;
	private String nombre=null;
	private CheckBox checkBoxTipoDiscapacidad=null;
	public TipoDiscapacidad(int id, String nombre, MenuButton menuButton){
		
		this.id=id;
		this.nombre=nombre;
		this.checkBoxTipoDiscapacidad=new CheckBox(nombre);

		this.checkBoxTipoDiscapacidad.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				
				
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
