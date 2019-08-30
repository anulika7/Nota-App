package fa.filter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author G4u521
 */
public class enkripnya {
    
    public static String sha512(String cadena){
        StringBuilder sib=new StringBuilder();
        
        try{
            MessageDigest md=MessageDigest.getInstance("SHA-512");
            md.update(cadena.getBytes());
            byte[] mb=md.digest();
            
            for (int i=0; i<mb.length; i++){
                sib.append(Integer.toString((mb[i] & 0xff)+ 0x100, 16).substring(1));
            }
        }catch(NoSuchAlgorithmException ex){
            /*...*/
        }
        
        return sib.toString();
    }
}
