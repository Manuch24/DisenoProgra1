package consultasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Servicios.BCCRCambioMoneda;
import conexion.conexion;
import logicadenegocios.Persona;
import logicadenegocios.Cuenta;
import logicadenegocios.Operacion;



public class OperacionDAO extends conexion {
	
	
	
//	public boolean registrar(Persona persona, String date ) {
//		PreparedStatement ps = null;
//		Connection con = getConexion();
//		
//		String sql = "INSERT INTO Persona (idPersona, identificacion, nombreCliente, primerApellido, segundoApellido, fechaNacimiento, numeroTelefono, email, cantPersonas)"
//				+ "VALUES (?,?,?,?,?,?,?,?,?)";
//		try {
//			
//			
//			ps = con.prepareStatement(sql);
//			
//			java.sql.Date date1 = java.sql.Date.valueOf(date);
//			
//			ps.setString(1, persona.getIdPersona() + cantPersonas());
//			ps.setInt(2, persona.getIdentificacion());		
//			ps.setString(3, persona.getNombreCliente());		
//			ps.setString(4, persona.getPrimerApellido());		
//			ps.setString(5, persona.getSegundoApellido());		
//			ps.setDate(6, date1);		
//			ps.setInt(7, persona.getNumeroTelefono());		
//			ps.setString(8, persona.getEmail());		
//			ps.setInt(9, cantPersonas());	
//			
//			ps.execute();
//			return true;
//					
//		}catch (SQLException e) {
//			System.err.println(e);
//			return false;
//		}finally {
//			try {
//				con.close();
//			}catch(SQLException e) {
//				System.err.println(e);
//			}
//			
//		}
//		
//	}
	
	
	public void actualizarPin(String nuevoPin, int numCuenta) throws SQLException {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		//String cantidad = "";

		String sql = "update Cuenta Set pin=(?) where Cuenta.numeroCuenta="+numCuenta;
		try {
            
            ps = con.prepareStatement(sql);

           
            
            ps = con.prepareStatement(sql);
            ps.setString(1,nuevoPin);
            
            ps.execute();
            //return true;

        } catch (SQLException e) {
            System.err.println(e);
            //return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
		
	}
	
	public boolean realizarDeposito(Operacion operacion,int numCuenta) {
		PreparedStatement ps = null;
		Connection con = getConexion();
		
		String sql = "{call realizarDeposito (?,?,?,?,?,?) }";
		
		try {
			
			
			ps = con.prepareStatement(sql);
			
			long timeInMilliSeconds = operacion.getFechaOperacion().getTime();
			
			java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);
			
			//String com = new
			
			ps.setInt(1, numCuenta);
			ps.setString(2, operacion.getTipo());		
			ps.setDate(3, date1);		
			ps.setString(4, String.valueOf(operacion.isHayComision()));		
			ps.setFloat(5, operacion.getMontoOperacion());		
			ps.setFloat(6, operacion.getMontoComision());
			
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
	
	public int verificarCantTransaccionesGratis(int numCuenta) {
		int cantidad = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		
		

		String sql = "select count(numeroCuenta) as Cant from cuentaOperacion \r\n"
				+ "inner join Operacion on Operacion.idOperacion=cuentaOperacion.idOperacion where cuentaOperacion.numeroCuenta="+numCuenta+" and (Operacion.tipo='retiro' or Operacion.tipo='deposito')";
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				cantidad = rs.getInt("Cant"); 
				//System.out.println(cantidad);
				return cantidad;
			}
			
			
		}catch (SQLException e) {
			System.err.println(e);
			return cantidad;
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}

		}return cantidad;
	}
	
	
	public boolean realizarRetiro(Operacion operacion,int numCuenta) {
		PreparedStatement ps = null;
		Connection con = getConexion();
		
		String sql = "{call realizarRetiro (?,?,?,?,?,?) }";
		
		try {
			
			
			ps = con.prepareStatement(sql);
			
			long timeInMilliSeconds = operacion.getFechaOperacion().getTime();
			
			java.sql.Date date1 = new java.sql.Date(timeInMilliSeconds);
			
			//String com = new
			
			ps.setInt(1, numCuenta);
			ps.setString(2, operacion.getTipo());		
			ps.setDate(3, date1);		
			ps.setString(4, String.valueOf(operacion.isHayComision()));		
			ps.setFloat(5, operacion.getMontoOperacion());		
			ps.setFloat(6, operacion.getMontoComision());
			
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
	public float confirmarSaldo(int numCuenta) {
		float cantidad = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		
		

		String sql = "select Cuenta.saldo from Cuenta where Cuenta.numeroCuenta="+numCuenta;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				cantidad = rs.getFloat("saldo"); 
				//System.out.println(cantidad);
				return cantidad;
			}
			
			
		}catch (SQLException e) {
			System.err.println(e);
			return cantidad;
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}

		}return cantidad;
	}
	
	public int extaerTelefono(int numCuenta) {
		int cantidad = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		
		

		String sql = "select Persona.numeroTelefono from Persona \r\n"
				+ "inner join personaCuenta on personaCuenta.identificacion=Persona.identificacion\r\n"
				+ "inner join Cuenta on Cuenta.numeroCuenta=personaCuenta.numeroCuenta where Cuenta.numeroCuenta="+numCuenta;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				cantidad = rs.getInt("numeroTelefono"); 
				//System.out.println(cantidad);
				return cantidad;
			}
			
			
		}catch (SQLException e) {
			System.err.println(e);
			return cantidad;
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}

		}return cantidad;
	}
	
	public void InactivarCuenta(int numCuenta) {
		int cantidad = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		
		//String seInactiva = "Inactivo";

		String sql = "update Cuenta set estatus = (?) where numeroCuenta="+numCuenta;
		
		try {
			 ps = con.prepareStatement(sql);

	           
	            
	            ps = con.prepareStatement(sql);
	            ps.setString(1,"Inactivo");
	            
	            ps.execute();
			
			
		}catch (SQLException e) {
			System.err.println(e);
			
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}

		}
	}
	
	public boolean estatusInactivo(int numCuenta) {
		String cantidad = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		
		

		String sql = "select Cuenta.estatus from Cuenta where Cuenta.numeroCuenta="+numCuenta+" and Cuenta.estatus='Inactivo'";
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				cantidad = rs.getString("estatus"); 
				//System.out.println(cantidad);
				return true;
			}
			
			
		}catch (SQLException e) {
			System.err.println(e);
			return false;
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}

		}return false;
	}

	public String consultarEstatusCuenta(int numCuenta) {
		String cantidad = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		
		

		String sql = "select Cuenta.estatus from Cuenta where Cuenta.numeroCuenta="+numCuenta;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				cantidad = rs.getString("estatus"); 
				//System.out.println(cantidad);
				return cantidad;
			}
			
			
		}catch (SQLException e) {
			System.err.println(e);
			return cantidad;
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}

		}return cantidad;
	}
	
	public Vector generarEstadoCuenta(int numCuenta) {
		Vector cantidad = new Vector();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		
		DateFormat df= new SimpleDateFormat("yyyy-MM-dd");
		
		//Date fecha;
		String sql = "select Operacion.fechaOperacion,Operacion.tipo, Operacion.montoOperacion,Operacion.montoComision from Cuenta\r\n"
				+ "inner join personaCuenta on personaCuenta.numeroCuenta=Cuenta.numeroCuenta \r\n"
				+ "inner join Persona on Persona.identificacion=personaCuenta.identificacion\r\n"
				+ "inner join cuentaOperacion on cuentaOperacion.numeroCuenta=Cuenta.numeroCuenta\r\n"
				+ "inner join Operacion on Operacion.idOperacion=cuentaOperacion.idOperacion where Cuenta.numeroCuenta="+numCuenta;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Vector fila = new Vector();
				String fecha=df.format(rs.getDate(1));
				fila.add(fecha); 
				fila.add(rs.getString(2));
				fila.add(rs.getInt(3));
				fila.add(rs.getInt(4));
				cantidad.add(fila);
			}
			
			
		}catch (SQLException e) {
			System.err.println(e);
			return cantidad;
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}

		}return cantidad;
	}
	
	public float consultaComisionesTotales() {
		float cantidad = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		
		

		String sql = "select sum(Operacion.montoComision) as TotalComisiones from Operacion";
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				cantidad = rs.getFloat("TotalComisiones"); 
				//System.out.println(cantidad);
				return cantidad;
			}
			
			
		}catch (SQLException e) {
			System.err.println(e);
			return cantidad;
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}

		}return cantidad;
	}
	
	public float consultaComisionesPorCuenta(int numCuenta) {
		float cantidad = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		
		

		String sql = "select sum(Operacion.montoComision) as TotalComisiones from Operacion\r\n"
				+ "inner join cuentaOperacion on cuentaOperacion.idOperacion=Operacion.idOperacion\r\n"
				+ "inner join Cuenta on Cuenta.numeroCuenta=cuentaOperacion.numeroCuenta where Cuenta.numeroCuenta="+numCuenta;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				cantidad = rs.getFloat("TotalComisiones"); 
				//System.out.println(cantidad);
				return cantidad;
			}
			
			
		}catch (SQLException e) {
			System.err.println(e);
			return cantidad;
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}

		}return cantidad;
	}
	
	public void consultarEstadoCuenta(JTable tabla, int numCuenta) throws SQLException {
		DefaultTableModel modelo = new DefaultTableModel();
		tabla.setModel(modelo);
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "SELECT Operacion.tipo, Operacion.montoOperacion, Operacion.montoComision, "
				+ "Operacion.fechaOperacion FROM Operacion JOIN cuentaOperacion "
				+ "ON cuentaOperacion.idOperacion = "
				+ "Operacion.idOperacion WHERE cuentaOperacion.numeroCuenta ="+numCuenta;
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		ResultSetMetaData rsMd = rs.getMetaData();
		int cantidadColumna = rsMd.getColumnCount();

		modelo.addColumn("Tipo Operacion");
		modelo.addColumn("Monto Operacion");
		modelo.addColumn("Comision");
		modelo.addColumn("Fecha");

		while(rs.next()) {
			Object[] filas = new Object[cantidadColumna];

			for(int i = 0; i<cantidadColumna; i++) {
				filas[i] = rs.getObject(i+1);
//				System.out.println(rs.getObject(i+1));
			}
			modelo.addRow(filas);
		}
	}
	
	public void consultarEstadoCuentaDolares(JTable tabla, int numCuenta) throws SQLException {
		DefaultTableModel modelo = new DefaultTableModel();
		tabla.setModel(modelo);
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = getConexion();
		BCCRCambioMoneda cambioMoneda = new BCCRCambioMoneda();
		
		String sql = "SELECT Operacion.tipo, Operacion.montoOperacion / "+ cambioMoneda.getCompra()+", "
				+ "Operacion.montoComision / "+ cambioMoneda.getCompra()+ ", "
				+ "Operacion.fechaOperacion FROM Operacion JOIN cuentaOperacion "
				+ "ON cuentaOperacion.idOperacion = "
				+ "Operacion.idOperacion WHERE cuentaOperacion.numeroCuenta ="+numCuenta;
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		ResultSetMetaData rsMd = rs.getMetaData();
		int cantidadColumna = rsMd.getColumnCount();

		modelo.addColumn("Tipo Operacion");
		modelo.addColumn("Monto Operacion");
		modelo.addColumn("Comision");
		modelo.addColumn("Fecha");

		while(rs.next()) {
			Object[] filas = new Object[cantidadColumna];

			for(int i = 0; i<cantidadColumna; i++) {
				filas[i] = rs.getObject(i+1);
//				System.out.println(rs.getObject(i+1));
			}
			modelo.addRow(filas);
		}
	}
	
	public float totalRetiros() {
		float cantidad = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		
		

		String sql = "SELECT SUM(Operacion.montoComision) as total FROM Operacion WHERE Operacion.tipo = 'retiro'";
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				cantidad = rs.getFloat("total"); 
				//System.out.println(cantidad);
				return cantidad;
			}
			
			
		}catch (SQLException e) {
			System.err.println(e);
			return cantidad;
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}

		}return cantidad;
	}
	
	public float totalDepositos() {
		float cantidad = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		
		

		String sql = "SELECT SUM(Operacion.montoComision) as total FROM Operacion WHERE Operacion.tipo = 'deposito'";
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				cantidad = rs.getFloat("total"); 
				//System.out.println(cantidad);
				return cantidad;
			}
			
			
		}catch (SQLException e) {
			System.err.println(e);
			return cantidad;
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}

		}return cantidad;
	}
	
	public float totalDepositosRetiros() {
		float cantidad = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		
		

		String sql = "SELECT SUM(Operacion.montoComision) as total FROM Operacion WHERE Operacion.tipo = 'Retiro' or Operacion.tipo = 'deposito'";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				cantidad = rs.getFloat("total"); 
				//System.out.println(cantidad);
				return cantidad;
			}
			
			
		}catch (SQLException e) {
			System.err.println(e);
			return cantidad;
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}

		}return cantidad;
	}
	
	public float totalRetiroCuenta(int numCuenta) {
		float cantidad = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		
		

		String sql = "SELECT SUM(Operacion.montoComision) as total FROM Operacion JOIN cuentaOperacion "
				+ "ON cuentaOperacion.idOperacion = Operacion.idOperacion WHERE Operacion.tipo = 'retiro' "
				+ "and cuentaOperacion.numeroCuenta ="+ numCuenta;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				cantidad = rs.getFloat("total"); 
				//System.out.println(cantidad);
				return cantidad;
			}
			
			
		}catch (SQLException e) {
			System.err.println(e);
			return cantidad;
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}

		}return cantidad;
	}

	public float totalDepositosCuenta(int numCuenta) {
		float cantidad = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		
		

		String sql = "SELECT SUM(Operacion.montoComision) as total FROM Operacion JOIN cuentaOperacion "
				+ "ON cuentaOperacion.idOperacion = Operacion.idOperacion WHERE Operacion.tipo = 'deposito' "
				+ "and cuentaOperacion.numeroCuenta ="+ numCuenta;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				cantidad = rs.getFloat("total"); 
				//System.out.println(cantidad);
				return cantidad;
			}
			
			
		}catch (SQLException e) {
			System.err.println(e);
			return cantidad;
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}

		}return cantidad;
	}
	
	public float totalDepositosRetirosCuenta(int numCuenta) {
		float cantidad = 0;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		
		

		String sql = "SELECT SUM(Operacion.montoComision) as total FROM Operacion JOIN "
				+ "cuentaOperacion ON cuentaOperacion.idOperacion = Operacion.idOperacion WHERE"
				+ " cuentaOperacion.numeroCuenta =" +numCuenta+ " and Operacion.tipo = 'deposito' "
						+ "or Operacion.tipo = 'retiro'";
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				cantidad = rs.getFloat("total"); 
				//System.out.println(cantidad);
				return cantidad;
			}
			
			
		}catch (SQLException e) {
			System.err.println(e);
			return cantidad;
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}

		}return cantidad;
	}
	
}
