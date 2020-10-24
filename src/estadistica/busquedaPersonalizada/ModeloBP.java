package estadistica.busquedaPersonalizada;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import funciones.Conexion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

public class ModeloBP {
	public static final String SEPARADOR_ID="<<ID>>";
	public static final String SEPARADOR_NOMBRE="<<NOMBRE>>";
	private String mensaje=null;
	private String consultaSql_Busqueda=null;
	private ObservableList<ObjetoBusquedaPersonalizada> arrayT=FXCollections.observableArrayList();
	private String consultaSQL_Array=null;
	public ModeloBP(ComboBox<ObjetoBusquedaPersonalizada> comboBox, String consultaSQL_Array, String consultaSQL_Busqueda){
		this.consultaSQL_Array=consultaSQL_Array;
		this.consultaSql_Busqueda=consultaSQL_Busqueda;
		consultar();
		cargarCombobox_ObjetoBusquedaPersonalizada(comboBox, arrayT);
	}
	public ModeloBP(ComboBox<ObjetoBusquedaPersonalizada> comboBox, ObservableList<ObjetoBusquedaPersonalizada> arrayT){
		cargarCombobox_ObjetoBusquedaPersonalizada(comboBox, arrayT);
	}
	
	public void setObservableListT(ObservableList<ObjetoBusquedaPersonalizada> arrayT){
		this.arrayT=FXCollections.observableArrayList();
		this.arrayT=arrayT;
	}
	
	public void cargarCombobox_ObjetoBusquedaPersonalizada(ComboBox<ObjetoBusquedaPersonalizada> comboBox,ObservableList<ObjetoBusquedaPersonalizada> arrayList){
		ObjetoBusquedaPersonalizada obj=new ObjetoBusquedaPersonalizada("0","No buscar","");
		arrayList.add(0, obj);
		comboBox.setItems(arrayList);
		if(comboBox.getSelectionModel().getSelectedItem()==null){
			comboBox.getSelectionModel().select(obj);
		}
	}
	
	public void consultar(){
		arrayT=FXCollections.observableArrayList();
		Connection conn=null;
        PreparedStatement pst=null;
        ResultSet rs=null;
    	try{           
             Class.forName("com.mysql.jdbc.Driver");  
             conn = DriverManager.getConnection(Conexion.SERVIDOR,Conexion.USER,Conexion.PASS);     
             pst = conn.prepareStatement(consultaSQL_Array);
             rs = pst.executeQuery();
             while(rs.next()){
            	 ObjetoBusquedaPersonalizada o=new ObjetoBusquedaPersonalizada(rs.getString(1), rs.getString(2),
            			 generarCodigoSQL(rs.getString(1), rs.getString(2),consultaSql_Busqueda));
            	 arrayT.add(o);
             }
             mensaje="Consulta exitosa.";
         }   
         catch(Exception e){
        	 mensaje=e.getMessage();
             e.printStackTrace();
         }finally{
        	
        		 try {
        			if(rs!=null){
        				 rs.close();
        			}
        			if(pst!=null){
        				pst.close();
        			}
        			if(conn!=null){
        				pst.close();
        			}
					
        		 } catch (SQLException e) {
					e.printStackTrace();
			
        	 }
        	 
        	 
         } 
	}
	
	public String generarCodigoSQL(String id, String nombre, String codigoSQL){
		
		String primerReemplazo=codigoSQL.replace(ModeloBP.SEPARADOR_ID, id);
		String SegundoReemplazo=primerReemplazo.replace(ModeloBP.SEPARADOR_NOMBRE, nombre);
		return SegundoReemplazo;
	}
}
