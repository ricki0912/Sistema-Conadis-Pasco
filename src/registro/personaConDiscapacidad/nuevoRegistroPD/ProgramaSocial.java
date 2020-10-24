package registro.personaConDiscapacidad.nuevoRegistroPD;

import registro.personaConDiscapacidad.OpcionesControler;

public class ProgramaSocial {
	private int nro;
	private int codEstadistico;
	private String descripcion;

	public int getNro() {
		return nro;
	}
	public void setNro(int nro) {
		this.nro = nro;
	}
	public int getCodEstadistico() {
		return codEstadistico;
	}
	public void setCodEstadistico(int codEstadistico) {
		this.codEstadistico = codEstadistico;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public OpcionesControler getOpciones() {
		return opciones;
	}
	public void setOpciones(OpcionesControler opciones) {
		this.opciones = opciones;
	}
	private OpcionesControler opciones;

	
	 public ProgramaSocial(int id, int nro, String nombre, int codEstadistico, String descripcion) {
		 this.id=id;
		 this.nro=nro;
		 this.nombre=nombre;
		 this.codEstadistico=codEstadistico;
		 this.descripcion=descripcion;
		 this.opciones=new OpcionesControler();
	}
	
	
	
	
	
	
	
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
		ProgramaSocial other = (ProgramaSocial) obj;
		if (id != other.id)
			return false;
		return true;
	}
	private int id=-1;
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
	private String nombre=null;
	
	private int codigo_estadistico=-1;
	public ProgramaSocial(int id, String nombre){
		this.id=id;
		this.nombre=nombre;
		
	}
	
	public ProgramaSocial(int id, String nombre, int codigo_estadistico){
		this.id=id;
		this.nombre=nombre;
		this.codigo_estadistico=codigo_estadistico;
	}
	public int getCodigo_estadistico() {
		return codigo_estadistico;
	}
	public void setCodigo_estadistico(int codigo_estadistico) {
		this.codigo_estadistico = codigo_estadistico;
	}
	@Override
	public String toString(){
		
		return this.nombre;
	}
	
}
