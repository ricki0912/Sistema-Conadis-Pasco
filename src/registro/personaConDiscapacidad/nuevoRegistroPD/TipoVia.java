package registro.personaConDiscapacidad.nuevoRegistroPD;

import registro.personaConDiscapacidad.OpcionesControler;

public class TipoVia {
	
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

	
	 public TipoVia(int id, int nro, String nombre, int codEstadistico, String descripcion) {
		 this.id=id;
		 this.nro=nro;
		 this.nombre=nombre;
		 this.codEstadistico=codEstadistico;
		 this.descripcion=descripcion;
		 this.opciones=new OpcionesControler();
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
	public TipoVia(int id, String nombre){
		this.id=id;
		this.nombre=nombre;
		
	}
	
	
	@Override
	public String toString(){
		
		return this.nombre;
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
		TipoVia other = (TipoVia) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
}
