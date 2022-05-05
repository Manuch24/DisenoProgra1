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
			ps.setString(2, persona.getNombreCliente());		
			ps.setString(2, persona.getPrimerApellido());		
			ps.setString(2, persona.getSegundoApellido());		
			ps.setString(2, persona.getFechaNacimiento().toString());		
			ps.setInt(2, persona.getNumeroTelefono());		
			ps.setString(2, persona.getEmail());		
			ps.setInt(2, persona.getIdentificacion());		
					
		}catch (SQLException e) {
			
		}
		
	}
	
}
