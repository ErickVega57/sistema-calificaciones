package org.fmat.clases;
import java.util.regex.Pattern;

/*
* REFACTORIZACION IMPL-2
* EXTRACT CLASS:
*   - SeguridadPasswd
*   - Servicio Autenticacion
*   - UsuarioRepository
 */

public class GestorUsersContras {

    public boolean entradaPasswdEsValida(String passwd){
        // al menos 3 caracteres y 2 digitos
        String regex = "^(?=(?:.*\\d){2,}).{5,}$";
        return Pattern.matches(regex, passwd);
    }

    public boolean entradaUsuarioEsValida(String user) {
        if(user.isBlank()){
            return false;
        }
        return user.length() >= 4;
    }

}
