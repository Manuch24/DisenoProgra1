package Servicios;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BCCRCambioMoneda {
	private int indicador = 0; // 317: Compra, 318: Venta
	// private int indicadorCambio = 317;
	private long miliseconds = System.currentTimeMillis();
	private Date dateHoy = new Date(miliseconds);
	private String fecha = new SimpleDateFormat("dd/MM/yyyy").format(dateHoy);
	private final String nombreBCCR = "TEC";
	private final String correo = "manuelchavesmoya@gmail.com";
	private final String token = "0O5O3A0A2U";
	private final String subNivel = "N";
	private final String HOST = "https://gee.bccr.fi.cr/Indicadores/Suscripciones/WS/wsindicadoreseconomicos.asmx/ObtenerIndicadoresEconomicosXML?";
	private String url;
	private final String VALUE_TAG = "NUM_VALOR";


	// funcion que devuelve el html
	protected static String getHTML(String urlToRead) throws Exception {
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");

		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		rd.close();
		return result.toString();
	}

	// forma el url para el el tipo de cambio
	private String getValue() {
		try {
			setUrl(); // Set del Url con los Parámetros

			
			// Obtiene y Parsea el XML
			String data = getHTML(url);

			return parserXML(data);

		} catch (Exception e) {
			System.out.println(e);
			System.out.println("Error al obtener el valor del Banco Central de Costa Rica.");
			return "0";
		}
	}

	private void setUrl() {
		this.url = HOST + "Indicador=" + indicador + "&FechaInicio=" + fecha + "&FechaFinal=" + fecha
				+ "&Nombre="+nombreBCCR+"&SubNiveles="+subNivel+"&CorreoElectronico="+correo+"&Token="+token+"";

	}

	public float getCompra() {
		this.indicador = 317;
		float valor = Float.parseFloat(getValue());
		return valor;
	}

	public float getVenta() {
		this.indicador = 318;
		float valor = Float.parseFloat(getValue());
		return valor;
	}

	private String parserXML(String data) {

		String regex = "&lt;NUM_VALOR&gt;(.*?)&lt;/NUM_VALOR&gt;";

		String resultado = "";

		Pattern patron = Pattern.compile(regex);
		Matcher coincidencia = patron.matcher(data);

		while (coincidencia.find()) {
			resultado += coincidencia.group();
		}

		String limpiarTag1 = "&lt;NUM_VALOR&gt;";
		String limpiarTag2 = "&lt;/NUM_VALOR&gt;";

		return (resultado.replaceAll(limpiarTag1, "").replaceAll(limpiarTag2, ""));
	}
}
