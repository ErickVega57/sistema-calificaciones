package org.fmat.controller;


import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import org.fmat.clases.GestorContras;


public class LoginController extends Controller implements Login {

    private static final GestorContras gc = new GestorContras();

    @FXML
    private TextField campoUsuario;
    @FXML
    private PasswordField campoPasswd;

    @Override
    @FXML
    public void login() {
        String user = campoUsuario.getText();
        char[] passwd = campoPasswd.getText().toCharArray();

        System.out.println("[!] Usuario: " + user );
        System.out.println("[!] Passwd: " + gc.encriptar(passwd) );
    }



    @Override
    public void agregarUser() {

    }
}
