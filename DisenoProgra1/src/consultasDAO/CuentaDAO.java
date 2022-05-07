package consultasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conexion.conexion;
import logicadenegocios.Cuenta;
import logicadenegocios.Persona;

public class CuentaDAO extends conexion {

	public boolean registrar(Cuenta cuenta, String date ) {
		PreparedStatement ps = null;
		Connection con = getConexion();
		
		String sql = "INSERT INTO Cuenta (numeroCuenta, fechaCreacion, saldo, estatus, pin)"
				+ "VALUES (?,?,?,?,?)";
		try {
			
			
			ps = con.prepareStatement(sql);
			
			java.sql.Date date1 = java.sql.Date.valueOf(date);
			
			ps.setInt(1, cuenta.getNumeroCuenta() + cantCuenta());
			ps.setDate(2, date1);		
			ps.setInt(3, cuenta.getSaldo());		
			ps.setString(4, cuenta.getStatus());		
			ps.setString(5, cuenta.getPin());		
			
			
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
				cantidad = rs.getInt("cantidadPersonas"); 
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
