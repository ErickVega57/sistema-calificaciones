package org.fmat.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;
import org.fmat.clases.GestorUsersContras;
import org.fmat.clases.SeguridadPassword;
import org.fmat.clases.ServicioAutenticacion;
import org.fmat.clases.UsuarioRepository;
import org.fmat.vistas.Vistas;

import java.io.IOException;


public class LoginController extends Controller implements Login {

    private static final GestorUsersContras gestorContras = new GestorUsersContras();
    private static final ServicioAutenticacion autenticacion = new ServicioAutenticacion();


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


    /*
    * REFACTORIZACION-IMPL#9
    * ConsolidateConditional
     */

    @Override
    @FXML
    public void login() throws IOException {
        String user = campoUsuario.getText();
        String passwd = campoPasswd.getText();
        //if(entradasValidas(user, passwd)){
            //if(gc.loginCorrecto(user, passwd)){
                //System.out.println("[!] Sesion iniciada corrctamente");
                //cerrarYAbrirNuevaVentana("Menú", MENU_VIEW_FXML, loginButton);  //aqui pon Los datos del menu
                //iniciar la ventana de menu
            //}else
                //lanzarErrorLoginIncorrecto();
        //}

        if(!(usuarioEsValido(user) && usuarioEsValido(passwd)) || !autenticacion.loginCorrecto(user,passwd)){
            lanzarErrorLoginIncorrecto();
            return;
        }

        cambiarVista(Vistas.MENU_VIEW, loginButton);

        System.out.println("[!] Usuario: " + user );
    }



    /*
     * REFACTORIZACION-IMPL#1
     * REPLACED NESTED CONDITIONAL WITH GUARD CLAUSES
     *
     */
    @Override
    @FXML
    public void agregarUser() throws IOException {

        String user = campoUsuario.getText();
        String passwd = campoPasswd.getText();

        if(!usuarioEsValido(user)){
            lanzarErrorUsuarioNoValido();
            return;
        }

        if(!contraEsValida(passwd)){
            lanzarErrorContraIncorrecta();
            return;
        }

        autenticacion.registrarUsuario(user, passwd);

        lanzarConfirmacionUsuarioAgregado();
    }

    /*
     * REFACTORIZACION-IMPL#1
     * DECOMPOSE CONDITIONAL
     *
     */

    private  boolean usuarioEsValido(String user){
        return gestorContras.entradaUsuarioEsValida(user);
    }
    private boolean contraEsValida(String passwd){
        return gestorContras.entradaPasswdEsValida(passwd);
    }
    //
    //private boolean entradasValidas(String user, String passwd) {
        // ANTERIOR
        //if(!gc.entradaUsuarioEsValida(user)){
        //    lanzarErrorUsuarioNoValido();
        //    return false;
        //}
        //if(!gc.entradaPasswdEsValida(passwd)){
        //    lanzarErrorContraIncorrecta();
        //    return false;
        //}
        //return true;
    //}
    // TERMINO DECOMPOSE


    /*
    *REFACTORIZACION-IMPL#3
    * PULL UP METHOD
    *
    *
     */

    protected void configureAlert(Alert alert, String title, String header, String content) {
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
