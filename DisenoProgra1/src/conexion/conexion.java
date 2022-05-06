package conexion;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.Statement;

public class conexion {

	
	/*
	 * private final String base = "DisennoPP1"; private final String user = "sa";
	 * private final String password = "12345"; private final string url = "jdbc:"
	 */
	 /**
		 * Class para generar la la conexion con sql server 2017
		 */

	/**
	 * atributos requeridos para la clase conexion
	 */
	public static Connection contacto = null;



	/**
	 * Método devuelve el status de la conexión con el servidor la variable contacto
	 * se hace cargo de hacer la conexión la variable status almacena false en caso
	 * de que la conexión no sirva
	 * 
	 * @return contacto que seria el resultado de la conexión
	 */
	public static Connection getConexion() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=DisennoPP1";
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "NO SE PUDO ESTABLECER CONEXION (REVISAR DRIVER)" + e.getMessage(),
					"Error de conexion", JOptionPane.ERROR_MESSAGE);
		}
		try {
			contacto = DriverManager.getConnection(url, "sa", "12345");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage(), "Error de conexion",
					JOptionPane.ERROR_MESSAGE);
		}
		return contacto;
	}

	/**
	 * Método de validar la cuenta creada con authorization en sql
	 * 
	 * @param usuario    se encargar de comparar el usuario creado para modificar el
	 *                   proyecto de sql
	 * @param contrssena se encargar de comparar la contrseña del usurio creado para
	 *                   modificar el proyecto de sql
	 */
	/*
	 * public static void setcuenta(String usuario, String contrasena) {
	 * conexion.usuario = usuario; conexion.contrasena = contrasena; }
	 * 
	 * // método para la variable status public static boolean getStatus() { return
	 * status; }
	 */

	/**
	 * método de realizar consulta
	 * 
	 * @param consulta se encarga se almacernar la consulta que se quiera realizar
	 *                 en sql
	 * @return los datos de la consulta en caso que sea exitoso de lo contrario
	 *         retorna un null
	 */
	public static ResultSet Consulta(String consulta) {
		Connection con = getConexion();
		Statement declara;
		try {
			declara = con.createStatement();
			ResultSet respuesta = declara.executeQuery(consulta);
			return respuesta;
		} catch (SQLException e) { 
			JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage(), "Error de conexion",
					JOptionPane.ERROR_MESSAGE);
		}
		return null;
	}
}
