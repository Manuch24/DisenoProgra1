package consultasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexion.conexion;
import logicadenegocios.Persona;

public class OperacionDAO extends conexion {

	public boolean registrar(Persona persona, String date ) {
		PreparedStatement ps = null;
		Connection con = getConexion();
		
		String sql = "INSERT INTO Persona (idPersona, identificacion, nombreCliente, primerApellido, segundoApellido, fechaNacimiento, numeroTelefono, email, cantPersonas)"
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			
			
			ps = con.prepareStatement(sql);
			
			java.sql.Date date1 = java.sql.Date.valueOf(date);
			
			ps.setString(1, persona.getIdPersona() + cantPersonas());
			ps.setInt(2, persona.getIdentificacion());		
			ps.setString(3, persona.getNombreCliente());		
			ps.setString(4, persona.getPrimerApellido());		
			ps.setString(5, persona.getSegundoApellido());		
			ps.setDate(6, date1);		
			ps.setInt(7, persona.getNumeroTelefono());		
			ps.setString(8, persona.getEmail());		
			ps.setInt(9, cantPersonas());	
			
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
}
