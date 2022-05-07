package consultasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;

import conexion.conexion;
import logicadenegocios.Persona;

public class PersonaDAO extends conexion  {
	
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
	
	public int cantPersonas () {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		int cantidad = 0;
		
		String sql = "SELECT MAX(cantPersonas) as cantidadPersonas FROM Persona";
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
	
	public void listarIdentificaciones(JComboBox cbx) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();

		String sql = "SELECT identificacion FROM Persona ";

		try{
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()){
				cbx.addItem(rs.getInt("identificacion"));
			}
			rs.close();
		}catch(SQLException e){
			System.err.println(e);
		}finally{
			try{
				con.close();
			}catch (SQLException e){
				System.err.println(e);
			}
		} 
	}
	
	
}