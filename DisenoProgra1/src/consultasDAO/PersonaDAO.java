package consultasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conexion.conexion;
import logicadenegocios.Persona;

public class PersonaDAO extends conexion  {
	
	public boolean registrar(Persona persona ) {
		PreparedStatement ps = null;
		Connection con = getConexion();
		
		String sql = "INSERT INTO Persona (idPersona, identificacion, nombreCliente, primerApellido, segundoApellido, fechaNacimiento, numeroTelefonico, email, cantPersonas)"
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		try {
			
			ps = con.prepareStatement(sql);
			ps.setString(1, persona.getIdPersona());
			ps.setInt(2, persona.getIdentificacion());		
			ps.setString(3, persona.getNombreCliente());		
			ps.setString(4, persona.getPrimerApellido());		
			ps.setString(5, persona.getSegundoApellido());		
			ps.setString(6, persona.getFechaNacimiento().toString());		
			ps.setInt(7, persona.getNumeroTelefono());		
			ps.setString(8, persona.getEmail());		
			ps.setInt(9, persona.getIdentificacion());	
			
			ps.execute();
			return true;
					
		}catch (SQLException e) {
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
