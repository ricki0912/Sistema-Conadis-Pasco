package funciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class VarAtomico {

	public static final int ENTERO =-1;
	public static final String STRING ="";
	
	public static final java.sql.Date DATE=   obtenerFecha("2200-01-01");
	public static java.sql.Date obtenerFecha(String fecha){
		 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		    java.util.Date parsedate = null;
			try {
				parsedate = format.parse(fecha);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		    java.sql.Date sql = new java.sql.Date(parsedate.getTime());
		    return sql;
	}
	
	public static final double DOUBLE=0.0;

	public static boolean empty( final String s ) {
		  return s == null || s.trim().isEmpty();
	}
	
}


