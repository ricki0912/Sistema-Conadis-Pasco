package registro.personaConDiscapacidad.nuevoRegistroPD;

public class EstablecimientoSalud {
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
		EstablecimientoSalud other = (EstablecimientoSalud) obj;
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
	public EstablecimientoSalud(int id, String nombre){
		this.id=id;
		this.nombre=nombre;
		
	}
	
	public EstablecimientoSalud(int id, String nombre, String director, String descripcion){
		this.id=id;
		this.nombre=nombre;
		this.director=director;
		this.descripcion=descripcion;
	}
	
	private String descripcion="";
	
	private String director="";
	
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString(){
		
		return this.nombre;
	}
	
}
