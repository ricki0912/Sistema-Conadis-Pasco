package registro.datosGenerales;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import registro.personaConDiscapacidad.OpcionesControler;

public class PersonaDiscapacidad {
	private int id_inscripcion_conadis;
	private int id_per_discapacidad;
	private String dni_Cui;
	private String ApellidosYNombres;
	private String edad;
	private String sexo;
	private String provincia;
	private String distrito;
	private String nombreVia;
	private String tipoDiscapacidad;
	private String nivelGravedad;
	private String tieneCertificado;
	private String obervacion;
	private OpcionesControler opciones;
	public PersonaDiscapacidad(int id_inscripcion_conadis,int id_per_discapacidad, String dni_cui, String ApellidosYNombres, String edad, String sexo,
			String provincia, String distrito, String nombreVia, String tipoDiscapacidad, String nivelGravedad,
			String tieneCertificado, String observacion) {
		this.id_per_discapacidad=id_per_discapacidad;
		this.id_inscripcion_conadis = id_inscripcion_conadis;
		this.dni_Cui = dni_cui;
		this.ApellidosYNombres = ApellidosYNombres;
		this.edad = edad;
		this.sexo = sexo;
		this.provincia = provincia;
		this.distrito = distrito;
		this.nombreVia = nombreVia;

		this.tipoDiscapacidad = tipoDiscapacidad;
		this.nivelGravedad = nivelGravedad;
		this.tieneCertificado = tieneCertificado;
		this.obervacion = observacion;
		opciones=new OpcionesControler();
	
	}

	public OpcionesControler getOpciones(){
		return this.opciones;
	}


	public int getId_inscripcion_conadis() {
		return id_inscripcion_conadis;
	}

	public int getId_per_discapacidad() {
		return id_per_discapacidad;
	}

	public String getDni_Cui() {
		return dni_Cui;
	}

	public String getNombresYApellidos() {
		return ApellidosYNombres;
	}

	public String getEdad() {
		return edad;
	}

	public String getSexo() {
		return sexo;
	}

	public String getProvincia() {
		return provincia;
	}

	public String getDistrito() {
		return distrito;
	}

	public String getNombreVia() {
		return nombreVia;
	}

	public String getTipoDiscapacidad() {
		return tipoDiscapacidad;
	}

	public String getNivelGravedad() {
		return nivelGravedad;
	}

	public String getTieneCertificado() {
		return tieneCertificado;
	}

	public String getObervacion() {
		return obervacion;
	}

}
