package Servicios;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class envioSMS {
//	
//    public static final String ACCOUNT_SID = "AC4d537d4e004564cc9b83465b50c749e7";
//    public static final String AUTH_TOKEN = "0a540cd3eada1fd39b2b82f57c16a0f5";
    public static final String ACCOUNT_SID = "AC4d537d4e004564cc9b83465b50c749e7";
    public static final String AUTH_TOKEN = "77f7ece5744c20ee7f0203f2936cb8a0";
	
    
    public void enviarSMS(int numeroTelefono, String codigoVerificacion) {
    	Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+506"+numeroTelefono),
                new com.twilio.type.PhoneNumber("+19704659168"),
                "Este es su código de verifición: "+codigoVerificacion)
            .create();
        
        
	}
    
    public String generarCodigoVerificacion() {
    	//String randomString = "";
    	String alfabetoMayuscula = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
 	    String alfabetoMinuscula = "abcdefghijklmnopqrstuvwxyz"; 
 	    String numero = "012345678901234567890123456789"; 

 	    String unionString = alfabetoMayuscula + alfabetoMinuscula + numero; 

 	    StringBuffer randomString = new StringBuffer(); 

 	    for (int i = 0; i < 5 ; i++) { 

 	      int randomIndex = (int)(Math.random() * unionString.length()); 

 	      randomString.append(unionString.charAt(randomIndex)); 
 	    } 
 	    return randomString.toString(); 
    }
}
