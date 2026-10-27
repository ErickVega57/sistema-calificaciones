package org.fmat.clases;

public class CredencialDeUsuario {

    private final String usuario;
    private final String hash;


    public CredencialDeUsuario(String usuario, String hash) {
        this.usuario = usuario;
        this.hash = hash;
    }

    public static CredencialDeUsuario fromCsvLine(String linea){
        String[] args = linea.split(",", 2);
        String hash = args[1].replace("\"", "").strip();
        return new CredencialDeUsuario(args[0], hash);
    }

    public String getUsuario() {
        return usuario;
    }

    public String getHash() {
        return hash;
    }
}
