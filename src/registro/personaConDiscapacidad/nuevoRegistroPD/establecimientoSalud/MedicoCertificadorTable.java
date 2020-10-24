package registro.personaConDiscapacidad.nuevoRegistroPD.establecimientoSalud;

public class MedicoCertificadorTable {
	
	private int id;
	
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
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getEspecialidad() {
		return especialidad;
	}
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}
	public String getEstablecimientoSalud() {
		return establecimientoSalud;
	}
	public void setEstablecimientoSalud(String establecimientoSalud) {
		this.establecimientoSalud = establecimientoSalud;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	private String dni;
	private String cmp;
	private String rne;
	private String nombres_apellidos;
	public String getNombres_apellidos() {
		return nombres_apellidos;
	}
	public void setNombres_apellidos(String nombres_apellidos) {
		this.nombres_apellidos = nombres_apellidos;
	}
	private String nombres;
	private String apellidos;
	private String especialidad;
	private String establecimientoSalud;
	private String observacion;
	public MedicoCertificadorTable(int id,String dni,String cmp,	String rne,	 String nombres, String apellidos,
	 String especialidad, String establecimientoSalud,	 String observacion){
		
		this.id=id;
		this.dni=dni;
		this.cmp=cmp;
		this.rne=rne;
		this.nombres=nombres;
		this.apellidos=apellidos;
		this.especialidad=especialidad;
		this.establecimientoSalud=establecimientoSalud;
		this.observacion=observacion;
		this.nombres_apellidos=nombres+", "+apellidos;
	}	
	
	
	@Override
	public String toString(){
		return nombres+", "+apellidos;
	}
	
	
}
