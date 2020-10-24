package gestMedicoCert.establecimiento_salud;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import registro.personaConDiscapacidad.OpcionesControler;

public class EstablecimientoSalud {
	private int idEstSalud=-1;
	private int nro=0;
	private String nombreEstSalud=null;
	private int idDirector=-1;
	private String nombreDirector=null;
	private OpcionesControler opciones=new OpcionesControler();

	
	
	public EstablecimientoSalud(int idEstSalud, int nro, String nombreEstSalud, int idDirector, String nombreDirector			) {
		super();
		this.idEstSalud = idEstSalud;
		this.nro = nro;
		this.nombreEstSalud = nombreEstSalud;
		this.idDirector = idDirector;
		this.nombreDirector = nombreDirector;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idEstSalud;
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
		EstablecimientoSalud other = (EstablecimientoSalud) obj;
		if (idEstSalud != other.idEstSalud)
			return false;
		return true;
	}
	public int getIdEstSalud() {
		return idEstSalud;
	}
	public void setIdEstSalud(int idEstSalud) {
		this.idEstSalud = idEstSalud;
	}
	public int getNro() {
		return nro;
	}
	public void setNro(int nro) {
		this.nro = nro;
	}
	public String getNombreEstSalud() {
		return nombreEstSalud;
	}
	public void setNombreEstSalud(String nombreEstSalud) {
		this.nombreEstSalud = nombreEstSalud;
	}
	public int getIdDirector() {
		return idDirector;
	}
	public void setIdDirector(int idDirector) {
		this.idDirector = idDirector;
	}
	public String getNombreDirector() {
		return nombreDirector;
	}
	public void setNombreDirector(String nombreDirector) {
		this.nombreDirector = nombreDirector;
	}
	public OpcionesControler getOpciones() {
		return opciones;
	}
	public void setOpciones(OpcionesControler opciones) {
		this.opciones = opciones;
	}
	
	
	
	

}
