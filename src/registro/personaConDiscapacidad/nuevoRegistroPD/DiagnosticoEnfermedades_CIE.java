package registro.personaConDiscapacidad.nuevoRegistroPD;

public class DiagnosticoEnfermedades_CIE {
	
	private int nro;
	private String codCie;
	private String nombre;
	public DiagnosticoEnfermedades_CIE(int nro,String codCie,String nombre){
		this.nro=nro;
		this.codCie=codCie;
		this.nombre=nombre;
	}
	public int getNro() {
		return nro;
	}
	public void setNro(int nro) {
		this.nro = nro;
	}

	public String getCodCie() {
		return codCie;
	}
	public void setCodCie(String codCie) {
		this.codCie = codCie;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
