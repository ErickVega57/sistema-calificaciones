package org.fmat.clases;

import java.io.IOException;

public class ServicioAutenticacion {
    private final SeguridadPassword seguridad = new SeguridadPassword();
    private final UsuarioRepository repositorio = new UsuarioRepository();
    // El validador se puede usar aquí o desde el controlador/UI antes de llamar a este servicio.

    public boolean registrarUsuario(String user, String passwd) throws IOException {
        if(repositorio.userExiste(user)){
            return false;
        }
        char[] passwordArray = passwd.toCharArray();
        String hash = seguridad.encriptar(passwordArray);
        return repositorio.guardarUsuario(user, hash);
    }

    public boolean loginCorrecto(String user, String passwd) throws IOException {
        if (!repositorio.userExiste(user)){
            return false;
        }

        String userHash = repositorio.obtenerHashDeUsuario(user);
        char[] passwordArray = passwd.toCharArray();

        return seguridad.contraEsValida(userHash, passwordArray);
    }
}