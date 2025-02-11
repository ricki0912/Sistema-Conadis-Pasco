package funciones;

import java.sql.Connection;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Map;

public abstract class AbstractJasperReports {
	private static JasperReport report;
	private static JasperPrint reportFilled;
	private static JasperViewer viewer;
	
	public static void createReport(Connection conn, String path){
		try{
			report=(JasperReport)JRLoader.loadObjectFromFile(path);
			reportFilled=JasperFillManager.fillReport(report, null, conn);
		}catch(JRException ex){
			
		}
		
	}
	public static void createReport(Connection conn, String path, Map parametros){
		try{
			report=(JasperReport)JRLoader.loadObjectFromFile(path);
			reportFilled=JasperFillManager.fillReport(report, parametros, conn);
		}catch(JRException ex){
			
		}
		
	}
	
	public static void showViewer(){
		viewer=new JasperViewer(reportFilled,false);
		viewer.setVisible(true);
		//viewer.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
	}
	
	public static void exportToPDF(String destination){
		
		try{
			JasperExportManager.exportReportToPdfFile(reportFilled, destination);
		}catch(JRException ex){
			ex.printStackTrace();
			
		}
	}
	
}
