package estadistica.busquedaPersonalizada;

public class ObjetoBusquedaPersonalizada {
	private String id=null;
	private String nombre=null;
	private String sentenciaSql=null;
	
	
	
	public ObjetoBusquedaPersonalizada(String id, String nombre, String sentenciaSql) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.sentenciaSql = sentenciaSql;
	}
	
	@Override
	public String toString() {
		return nombre ;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getSentenciaSql() {
		return sentenciaSql;
	}
	public void setSentenciaSql(String sentenciaSql) {
		this.sentenciaSql = sentenciaSql;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ObjetoBusquedaPersonalizada other = (ObjetoBusquedaPersonalizada) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	
}
