package consultasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.conexion;
import logicadenegocios.Cuenta;
import logicadenegocios.Persona;

public class CuentaDAO extends conexion {

	public  boolean registrar(Cuenta cuenta, int identificacion) {
		PreparedStatement ps = null;
		Connection con = getConexion();
		
		String sql = "{call ingresarCuenta (?,?,?,?,?,?) }";
		
		try {
			
			
			ps = con.prepareStatement(sql);
			
			java.sql.Date date1 = java.sql.Date.valueOf(cuenta.getFechaCreacion());
			
			ps.setInt(1, identificacion);
			ps.setInt(2, cantCuenta());		
			ps.setDate(3, date1);		
			ps.setInt(4, cuenta.getSaldo());		
			ps.setString(5, cuenta.getStatus());		
			ps.setString(6, cuenta.getStatus());
			
			ps.execute();
			return true;
					
		}catch (SQLException e) {
			System.err.println(e);
			return false;
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}
			
		}

	}

	public int cantCuenta () {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		int cantidad = 0;

		String sql = "SELECT MAX(numeroCuenta) as numeroCuenta FROM Cuenta";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				cantidad = rs.getInt("numeroCuenta"); 
			}
			return cantidad +1;
			
		}catch (SQLException e) {
			System.err.println(e);
			return cantidad;
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}

		}
	}
}
