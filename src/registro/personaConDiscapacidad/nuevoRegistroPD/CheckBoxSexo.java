package registro.personaConDiscapacidad.nuevoRegistroPD;

import javafx.scene.control.CheckBox;

public class CheckBoxSexo extends CheckBox {
	private int id=-1;
	
	public int getId_() {
		return id;
	}
	public void setId_(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	private String nombre=null;
	public CheckBoxSexo(int id, String nombre){
		super(nombre);
		this.id=id;
		this.nombre=nombre;
		
	}
	
	
	@Override
	public String toString(){
		
		return this.nombre;
	}
	
}
