package consultasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import conexion.conexion;
import logicadenegocios.Cuenta;
import logicadenegocios.Persona;

public class PersonaDAO extends conexion {

	public boolean registrar(Persona persona, String date) {
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

		} catch (SQLException e) {
			System.err.println(e);
			return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}

		}

	}

	public int cantPersonas() {

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
			return cantidad + 1;

		} catch (SQLException e) {
			System.err.println(e);
			return cantidad;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}

		}
	}

	public void listarIdentificaciones(JComboBox cbx) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();

		String sql = "SELECT identificacion FROM Persona ";

		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				cbx.addItem(rs.getInt("identificacion"));
			}
			rs.close();
		} catch (SQLException e) {
			System.err.println(e);
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	public void listarClientes(JTable tabla) throws SQLException {
		DefaultTableModel modelo = new DefaultTableModel();
		tabla.setModel(modelo);
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "SELECT primerApellido,segundoApellido,nombreCliente,identificacion FROM Persona order by primerApellido asc";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		ResultSetMetaData rsMd = rs.getMetaData();
		int cantidadColumna = rsMd.getColumnCount();

		modelo.addColumn("Primer apellido");
		modelo.addColumn("Segundo apellido");
		modelo.addColumn("Nombre");
		modelo.addColumn("Identificacion:");

		while (rs.next()) {
			Object[] filas = new Object[cantidadColumna];

			for (int i = 0; i < cantidadColumna; i++) {
				filas[i] = rs.getObject(i + 1);
				System.out.println(rs.getObject(i + 1));
			}
			modelo.addRow(filas);
		}

	}

	public void detallesPersona(JTable tabla) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = getConexion();
		Persona persona = new Persona();
		String sql = "SELECT * FROM Persona WHERE identificacion = " + tabla.getValueAt(tabla.getSelectedRow(), 3);

		try {
			ps = con.prepareStatement(sql);
//			ps.setString(1, tabla.getValueAt(tabla.getSelectedRow(),tabla.getSelectedColumn()));
			rs = ps.executeQuery();

			if (rs.next()) {
				persona.setIdPersona(rs.getString("idPersona"));
				persona.setIdentificacion(rs.getInt("identificacion"));
				persona.setNombreCliente(rs.getString("nombreCliente"));
				persona.setPrimerApellido(rs.getString("primerApellido"));
				persona.setSegundoApellido(rs.getString("segundoApellido"));
				persona.setFechaNacimiento(rs.getDate("fechaNacimiento").toString());
				persona.setNumeroTelefono(rs.getInt("numeroTelefono"));
				persona.setEmail(rs.getString("email"));

				JOptionPane.showMessageDialog(null,
						"Detalles de la persona:\n" + "idPersona: " + persona.getIdPersona() + "\n" + "Identificacion: "
								+ persona.getIdentificacion() + "\n" + "Primer apellido: " + persona.getPrimerApellido()
								+ "\n" + "Segundo apellido: " + persona.getSegundoApellido() + "\n"
								+ "FechaNacimiento: " + persona.getFechaNacimiento() + "\n" + "Numero telefonico: "
								+ persona.getNumeroTelefono() + "\n" + "email: " + persona.getEmail() + "\n"
								+ "Cuentas asociadas: " + obtenerCuenta(tabla));
			}

		} catch (SQLException e) {
			System.err.println(e);
			// return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	// Se obtener las cuentas asociadas para luego pasarselas a obtener detalles del
	// cliente
	public String obtenerCuenta(JTable tabla) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = getConexion();
		String cuentaString = "";
		ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();

		String sql = "SELECT numeroCuenta FROM PersonaCuenta where identificacion = "
				+ tabla.getValueAt(tabla.getSelectedRow(), 3);

		try {
			ps = con.prepareStatement(sql);
//			ps.setString(1, tabla.getValueAt(tabla.getSelectedRow(),tabla.getSelectedColumn()));
			rs = ps.executeQuery();

			while (rs.next()) {
				int i = 0;
				Cuenta cuenta = new Cuenta();
				cuenta.setNumeroCuenta(rs.getInt("numerocuenta"));
				cuentas.add(cuenta);
				cuentaString += cuentas.get(i).getNumeroCuenta() + ", ";
				i++;
			}
//			for (int  i = 0; i<= cuentas.size(); i++) {
//				cuentaString += cuentas.get(i).getNumeroCuenta()+", ";
//			}
			if (cuentaString == "") {
				return "El cliente aun no posee cuentas asociadas";
			}
			return cuentaString;

		} catch (SQLException e) {
			System.err.println(e);
			// return false;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
		return cuentaString;
	}

	public int numeroTelefono(int numCuenta) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		int numeroTelefonico = 0;

		String sql = "SELECT Persona.numeroTelefono FROM Persona JOIN personaCuenta ON "
				+ "personaCuenta.identificacion = Persona.identificacion WHERE numeroCuenta =" + numCuenta;
		try {
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();
			if (rs.next()) {
				numeroTelefonico = rs.getInt("numeroTelefono");
			}
			return numeroTelefonico;

		} catch (SQLException e) {
			System.err.println(e);
			return numeroTelefonico;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

	public String email(int numCuenta) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		String numeroTelefonico = "";

		String sql = "SELECT Persona.numeroTelefono FROM Persona JOIN personaCuenta ON "
				+ "personaCuenta.identificacion = Persona.identificacion WHERE numeroCuenta =" + numCuenta;
		try {
			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();
			if (rs.next()) {
				numeroTelefonico = rs.getString("email");
			}
			return numeroTelefonico;

		} catch (SQLException e) {
			System.err.println(e);
			return numeroTelefonico;
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println(e);
			}
		}
	}

}
