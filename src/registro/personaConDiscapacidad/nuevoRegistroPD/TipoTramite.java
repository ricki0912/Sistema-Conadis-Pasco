package registro.personaConDiscapacidad.nuevoRegistroPD;

public class TipoTramite {
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
		TipoTramite other = (TipoTramite) obj;
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
	public TipoTramite(int id, String nombre){
		this.id=id;
		this.nombre=nombre;
		
	}
	
	
	@Override
	public String toString(){
		
		return this.nombre;
	}
	
}
