package gestMedicoCert.medico_certificador;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import registro.personaConDiscapacidad.OpcionesControler;

public class MedicoCertificador {
	

	private int nro=0;
	private int id=-1;
	private String dni=null;
	private String cmp=null;
	private String rne=null;
	private String nombres_apellido=null;
	private String especialidad=null;
	private String est_salud=null;

	
	
	
	
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
		MedicoCertificador other = (MedicoCertificador) obj;
		if (id != other.id)
			return false;
		return true;
	}


	public MedicoCertificador(int nro, int id, String dni, String cmp, String rne, String nombres_apellido,
			String especialidad, String est_salud) {
		super();
		this.nro = nro;
		this.id = id;
		this.dni = dni;
		this.cmp = cmp;
		this.rne = rne;
		this.nombres_apellido = nombres_apellido;
		this.especialidad = especialidad;
		this.est_salud = est_salud;
	}


	private OpcionesControler opciones=new OpcionesControler();


	public int getNro() {
		return nro;
	}


	public void setNro(int nro) {
		this.nro = nro;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getCmp() {
		return cmp;
	}


	public void setCmp(String cmp) {
		this.cmp = cmp;
	}


	public String getRne() {
		return rne;
	}


	public void setRne(String rne) {
		this.rne = rne;
	}


	public String getNombres_apellido() {
		return nombres_apellido;
	}


	public void setNombres_apellido(String nombres_apellido) {
		this.nombres_apellido = nombres_apellido;
	}


	public String getEspecialidad() {
		return especialidad;
	}


	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}





	public String getEst_salud() {
		return est_salud;
	}


	public void setEst_salud(String est_salud) {
		this.est_salud = est_salud;
	}


	public OpcionesControler getOpciones() {
		return opciones;
	}


	public void setOpciones(OpcionesControler opciones) {
		this.opciones = opciones;
	}

	
	
	
	

}
