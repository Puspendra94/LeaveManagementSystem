// this class will validate a given password

package bca.leave.util;

import java.util.regex.*;

public class PasswordValidator {
  
    public static String validatePassword( String pass ) {
        
        String message = "";
        String alphabate = "[a-zA-z]+";
        String digit = "\\d+";
        String special = "[^a-zA-Z0-9]+";
      
        Pattern digitPattern = Pattern.compile(digit);
        Pattern specPattern = Pattern.compile(special);
        Pattern alphaPattern = Pattern.compile(alphabate); 
        
        if (pass.length() < 8) {
            message = "Password should contain atleast 8 character";
            return message;
        }
        
        // check for digits
        if (digitPattern.matcher(pass).find()== false) {
            message = "Password should contain atleast 1 digit";
            return message;
        }
        
        // check for special character
        if (specPattern.matcher(pass).find()== false) {
            message = "Password should contain atleast 1 special character";
            return message;
        }
        
        // check for alphabates
        if (alphaPattern.matcher(pass).find()== false) {
            message = "Password should contain atleast 1 alphabate";
            return message;
        }
        
        return message;
    }
}
