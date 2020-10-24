package registro.personaConDiscapacidad.nuevoRegistroPD;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuButton;

public class LimitacionPermanente {

	 

	
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
		LimitacionPermanente other = (LimitacionPermanente) obj;
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
	public CheckBox getCheckBoxLimitacionPermanente() {
		return checkBoxLimitacionPermanente;
	}
	public void setCheckBoxLimitacionPermanente(CheckBox checkBoxLimitacionPermanente) {
		this.checkBoxLimitacionPermanente = checkBoxLimitacionPermanente;
	}
	private int id=-1;
	private String nombre=null;
	private CheckBox checkBoxLimitacionPermanente=null;
	public LimitacionPermanente(int id, String nombre, MenuButton menuButton){
		
		this.id=id;
		this.nombre=nombre;
		this.checkBoxLimitacionPermanente=new CheckBox(nombre);
	
		this.checkBoxLimitacionPermanente.setOnAction(new EventHandler<ActionEvent>() {
			
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
