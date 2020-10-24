package registro.personaConDiscapacidad.nuevoRegistroPD;

public class MedicoCertificador {
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
	public MedicoCertificador(int id, String nombre){
		this.id=id;
		this.nombre=nombre;
		
	}
	
	
	@Override
	public String toString(){
		
		return this.nombre;
	}
	
}
