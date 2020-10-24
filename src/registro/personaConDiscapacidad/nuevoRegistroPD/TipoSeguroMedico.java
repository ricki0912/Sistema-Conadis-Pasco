package registro.personaConDiscapacidad.nuevoRegistroPD;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;

public class TipoSeguroMedico {

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
		TipoSeguroMedico other = (TipoSeguroMedico) obj;
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
	public CheckBox getCheckBoxTipoSeguroMedico() {
		return checkBoxTipoSeguroMedico;
	}
	public void setCheckBoxTipoSeguroMedico(CheckBox checkBoxTipoSeguroMedico) {
		this.checkBoxTipoSeguroMedico = checkBoxTipoSeguroMedico;
	}
	private int id=-1;
	private String nombre=null;
	private CheckBox checkBoxTipoSeguroMedico=null;
	public TipoSeguroMedico(int id, String nombre, MenuButton menuButton){
		
		this.id=id;
		this.nombre=nombre;
		this.checkBoxTipoSeguroMedico=new CheckBox(nombre);

		this.checkBoxTipoSeguroMedico.setOnAction(new EventHandler<ActionEvent>() {
			
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
