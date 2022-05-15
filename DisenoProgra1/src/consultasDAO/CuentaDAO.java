package consultasDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import conexion.conexion;
import logicadenegocios.Cuenta;
import logicadenegocios.Persona;
import logicadenegocios.Operacion;

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
			ps.setString(6, cuenta.getPin());
			
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
	
	public boolean verificarExistenciaCuenta(int numCuenta) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		boolean opcion=false;
		
		String sql="select Cuenta.numeroCuenta from Cuenta where Cuenta.numeroCuenta="+numCuenta;
		//System.out.println(sql);
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				//rs.getInt(0);
				opcion=true;
			}
		}catch(SQLException e) {
			System.err.println(e);
			return opcion;
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}

		}
		
		 
		return opcion;
	}
	
	public String buscarPin(int numCuenta) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		String cantidad = "";

		String sql = "select Cuenta.pin from Cuenta where Cuenta.numeroCuenta="+numCuenta;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				cantidad = rs.getString("pin"); 
				return cantidad;
			}
			
			
		}catch (SQLException e) {
			System.err.println(e);
			
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}

		}
		return "";
	}
	
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
	
	public void consultarCuentasInfo(JTable tabla) throws SQLException {
		DefaultTableModel modelo = new DefaultTableModel();
		tabla.setModel(modelo);
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = getConexion();

		String sql = "SELECT  Cuenta.numeroCuenta, Cuenta.estatus, Cuenta.saldo, Persona.primerApellido, "
				+ "Persona.segundoApellido, Persona.nombreCliente  FROM Persona INNER JOIN personaCuenta ON "
				+ "Persona.identificacion = personaCuenta.identificacion INNER JOIN Cuenta ON "
				+ "Cuenta.numeroCuenta = personaCuenta.numeroCuenta order by Cuenta.saldo desc\r\n"
				+ "";
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();

		ResultSetMetaData rsMd = rs.getMetaData();
		int cantidadColumna = rsMd.getColumnCount();

		modelo.addColumn("Numero Cuenta");
		modelo.addColumn("Estatus");
		modelo.addColumn("Saldo");
		modelo.addColumn("Primer Apellido");
		modelo.addColumn("Segundo apellido");
		modelo.addColumn("Nombre");


		while(rs.next()) {
			Object[] filas = new Object[cantidadColumna];

			for(int i = 0; i<cantidadColumna; i++) {
				filas[i] = rs.getObject(i+1);
				System.out.println(rs.getObject(i+1));
			}
			modelo.addRow(filas);
		}
	}
	
	public void detallesCuenta(JTable tabla) {
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = getConexion();
		Cuenta cuenta = new Cuenta();
		
		String sql = "SELECT * FROM Cuenta WHERE numeroCuenta ="+tabla.getValueAt(tabla.getSelectedRow(), 0);
		
		try {
			ps = con.prepareStatement(sql);
//			ps.setString(1, tabla.getValueAt(tabla.getSelectedRow(),tabla.getSelectedColumn()));
			rs = ps.executeQuery();
			
			if(rs.next()) {
				cuenta.setNumeroCuenta(rs.getInt("numeroCuenta"));
				cuenta.setFechaCreacion(rs.getDate("fechaCreacion").toString());
				cuenta.setSaldo(rs.getInt("saldo"));
				cuenta.setStatus(rs.getString("estatus"));
				cuenta.setPin(rs.getString("pin"));
				JOptionPane.showMessageDialog(null, "Detalles de la cuenta:\n"
						+ "Numero de cuenta: " + cuenta.getNumeroCuenta()+"\n"+
				"Fecha de creacion: "+cuenta.getFechaCreacion()+"\n"+
						"Saldo: " +cuenta.consultarSaldo()+"\n"
						+"estatus: " + cuenta.getStatus()+"\n"
						+ "Pin: " +cuenta.getPin() +"");				
			} 
			
			
		}catch (SQLException e) {
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
	
	
	public void listarCuentas(JComboBox cbx) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();

		String sql = "SELECT numeroCuenta FROM Cuenta ";

		try{
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()){
				cbx.addItem(rs.getInt("numeroCuenta"));
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
	
	public void listarCuentasDestino(JComboBox cbx) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();

		String sql = "SELECT Cuenta.numeroCuenta FROM Cuenta WHERE numeroCuenta  != " + cbx.getSelectedItem().toString();

		try{
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()){
				cbx.addItem(rs.getInt("numeroCuenta"));
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


	
public String esActiva(int numCuenta) {
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection con = getConexion();
		String estatus = "";

		String sql = "select Cuenta.estatus from Cuenta where Cuenta.numeroCuenta="+numCuenta;
		
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				estatus = rs.getString("estatus"); 

				return estatus;
			}
			
			
		}catch (SQLException e) {
			System.err.println(e);
			
		}finally {
			try {
				con.close();
			}catch(SQLException e) {
				System.err.println(e);
			}

		}
		return "";
	}

public void bloquearCuenta(int numCuenta) throws SQLException {
	PreparedStatement ps = null;
	Connection con = getConexion();
	//String cantidad = "";

	String sql = "update Cuenta Set estatus= 'Inactivo' where Cuenta.numeroCuenta="+numCuenta;
	try {
        
        ps = con.prepareStatement(sql);

       
        
        ps = con.prepareStatement(sql);

        
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
public int obtenerSaldo(int numCuenta) {
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = getConexion();
	int cantidad = 0;

	String sql = "select Cuenta.saldo from Cuenta where Cuenta.numeroCuenta="+numCuenta;
	
	try {
		ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		if (rs.next()) {
			cantidad = rs.getInt("saldo"); 
			return cantidad;
		}
		
		
	}catch (SQLException e) {
		System.err.println(e);
		
	}finally {
		try {
			con.close();
		}catch(SQLException e) {
			System.err.println(e);
		}

	}
	return cantidad;
}
	
		
}
