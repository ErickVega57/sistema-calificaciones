package org.fmat.controller;


import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import org.fmat.clases.GestorUsersContras;


public class LoginController extends Controller implements Login {

    private static final GestorUsersContras gc = new GestorUsersContras();
    private final Alert alertUsuairioInvalido = new Alert(Alert.AlertType.WARNING);
    private final Alert alertPasswdIncorrecta = new Alert(Alert.AlertType.WARNING);
    private final Alert alertLoginIncorrecto = new Alert(Alert.AlertType.WARNING);
    private final Alert alertUsuarioGuardado = new Alert(Alert.AlertType.CONFIRMATION);
    private final ButtonType buttonAceptar = new ButtonType("VOLVER");

    @FXML
    private TextField campoUsuario;
    @FXML
    private PasswordField campoPasswd;
    @FXML
    private Button loginButton;


    @FXML
    private void initialize(){  //esta funcion se ejecta al abrir la ventana
        System.out.println("[+] Ventana de login abierta correctamente");
        configureAlerts();
    }


    @Override
    @FXML
    public void login() {
        String user = campoUsuario.getText();
        String passwd = campoPasswd.getText();
        if(entradasValidas(user, passwd)){
            if(gc.loginCorrecto(user, passwd)){
                System.out.println("[!] Sesion iniciada corrctamente");
                cerrarYAbrirNuevaVentana("Menú", MENU_VIEW_FXML, loginButton);  //aqui pon Los datos del menu
                //iniciar la ventana de menu
            }else
                lanzarErrorLoginIncorrecto();
        }
        System.out.println("[!] Usuario: " + user );
    }

    @Override
    @FXML
    public void agregarUser() {
        String user = campoUsuario.getText();
        String passwd = campoPasswd.getText();
        if(entradasValidas(user, passwd)){
            if(gc.guardarUsuario(user, passwd)){
                lanzarConfirmacionUsuarioAgregado();
            }else
                lanzarErrorUsuarioNoValido();
        }
    }

    private boolean entradasValidas(String user, String passwd){
        if(!gc.entradaUsuarioEsValida(user)){
            lanzarErrorUsuarioNoValido();
            return false;
        }
        if(!gc.entradaPasswdEsValida(passwd)){
            lanzarErrorContraIncorrecta();
            return false;
        }
        return true;
    }

    private void configureAlert(Alert alert, String title, String header, String content) {
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.getButtonTypes().setAll(buttonAceptar);
    }

    private void configureAlerts() {
        configureAlert(alertUsuairioInvalido, "Error Usuario", "El usuario no es válido", "Presione para volver al menu de inicio de sesión");
        configureAlert(alertPasswdIncorrecta, "Error Contraseña", "La contraseña es invalida", "Presione para volver al menu de inicio de sesión");
        configureAlert(alertLoginIncorrecto, "Error inicio de sesión", "Error al iniciar sesión ", "Presione para volver al menu de inicio de sesión");
        configureAlert(alertUsuarioGuardado, "Usuario guardado", "Usuario agregado","El usuasrio ha sido guardado dorrectamente en el archivo Usuarios");
    }


    private void lanzarErrorUsuarioNoValido() {
        alertUsuairioInvalido.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonAceptar) {
                campoUsuario.clear();
                campoPasswd.clear();
                alertUsuairioInvalido.close();
            }
        });
    }
    private void lanzarErrorLoginIncorrecto() {
        alertLoginIncorrecto.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonAceptar) {
                campoUsuario.clear();
                campoPasswd.clear();
                alertLoginIncorrecto.close();
            }
        });
    }
    private void lanzarConfirmacionUsuarioAgregado() {
        alertUsuarioGuardado.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonAceptar) {
                campoUsuario.clear();
                campoPasswd.clear();
                alertUsuarioGuardado.close();
            }
        });
    }
    private void lanzarErrorContraIncorrecta() {
        alertPasswdIncorrecta.showAndWait().ifPresent(buttonType -> {
            if (buttonType == buttonAceptar) {
                campoUsuario.clear();
                campoPasswd.clear();
                alertPasswdIncorrecta.close();
            }
        });
    }


}
