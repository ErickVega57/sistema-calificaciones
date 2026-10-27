# Refactorizaciones

1. **[Extract Class](https://www.google.com/search?q=%23extract-class)**
2. **[Extract Interface](https://www.google.com/search?q=%23extract-interface)**
3. **[Pull Up Method](https://www.google.com/search?q=%23pull-up-method)**
4. **[Hide Delegate](https://www.google.com/search?q=%23hide-delegate)**
5. **[Introduce Parameter Object](https://www.google.com/search?q=%23introduce-parameter-object)**
6. **[Replace Magic Number with Symbolic Constant](https://www.google.com/search?q=%23replace-magic-number-with-symbolic-constant)**
7. **[Separate Query from Modifier](https://www.google.com/search?q=%23separate-query-from-modifier)**
8. **[Extract Method](https://www.google.com/search?q=%23extract-method)**
9. **[Substitute Algorithm](https://www.google.com/search?q=%23substitute-algorithm)**
10. **[Replace Nested Conditional with Guard Clauses](https://www.google.com/search?q=%23replace-nested-conditional-with-guard-clauses)**
11. **[Hide Method](https://www.google.com/search?q=%23hide-method)**
12. **[Encapsulate Field](https://www.google.com/search?q=%23encapsulate-field)**
13. **[Replace Array with Object](https://www.google.com/search?q=%23replace-array-with-object)**
14. **[Consolidate Conditional Expression](https://www.google.com/search?q=%23consolidate-conditional-expression)**

---

## Extract Class

En la clase `VisualizarDatosController`, los métodos `exportarCSV()` y `exportarPDF()` asumen responsabilidades de UI y además la lógica de exportar archivos PDF y CSV. Lo que mezcla las responsabilidades. Se pueden crear dos nuevas clases `ReporteExporter` o crear dos clases `CSVExporter` y `PDFExporter`.

También en las clases de `CSVManipulation` y `PDFManipulation` se pueden dividir para solo leer y devolver la información para la clase de `GestorUsersContras`.

## Extract Interface

Las clases `CSVManipulation` y `PDFManipulation` tienen comportamientos parecidos, se pueden relacionar por medio de una interfaz. Es decir, tienen métodos conceptualmente idénticos como los métodos `writeFile` y `writePDF` para exportar documentos.

## Pull Up Method

El método `volverMenu()` lo usan varias clases y el código se repite exactamente igual en las clases `ImportarArchivosController` y `VisualizarDatosController`:

```java
@FXML
public void volverMenu() {
    cerrarVolverAlMenu("Menú", MENU_VIEW_FXML, btnVolverMenu);
}

```

Al igual que se puede subir la lógica de los métodos `configureAlert` a la clase base `Controller`:

```java
private void configureAlert(Alert alert, String title, String header, String content) {
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    // ...
}

```

## Hide Delegate

El método de cerrar y abrir nueva ventana en la clase `Controller` revela la estructura interna de JavaFX a las clases clientes. Se puede ocultar este comportamiento para que las clases hijas no dependan de conocer el `Stage` o la `Scene`:

```java
// En Controller
public void cerrarYAbrirNuevaVentana(String titulo, String fxml, Button botonCerrar){
    // ...
    Stage stage = (Stage) botonCerrar.getScene().getWindow(); // Delegación expuesta
    // ...
}

```

## Introduce Parameter Object

Siempre se pasa la ruta y el título de la ventana en la clase `Controller` (métodos `abrirVentana`, `cambiarVista` y `cerrarVolverAlMenu`), por lo que podemos crear un objeto ENUM para meter ahí los títulos y hacer algo como `MENU_VISTA.getTitulo()`:

```java
public void cerrarYAbrirNuevaVentana(String titulo, String fxml, Button botonCerrar){...}

```

## Replace Magic Number with Symbolic Constant

En el método `encriptar()` de la clase `GestorUsersContras` contiene números mágicos para los parámetros de seguridad:

```java
int iterations = 3;
int memory = 65536;
int parallelism = 1;

```

Al igual que en `VisualizarDatosController` (método `exportarCSV` y `setupTableView`) se usan números mágicos para comprobar estados:

```java
if(a.getCalFinal() == -1){ ... }

```

## Separate Query from Modifier

La función `cerrarYAbrirNuevaVentana()` en la clase `Controller` mezcla la obtención del escenario actual (Query) con la acción de cambiar la escena y mostrarla (Modifier). Se puede dividir en un query `obtenerStage()`, un modifier `cerrarVentana()` y `abrirVentana()`, y un método orquestador tipo `navegarA(ventana)`:

```java
public void cerrarYAbrirNuevaVentana(String titulo, String fxml, Button botonCerrar){
    // ...
    Parent root = loader.load();
    Stage stage = (Stage) botonCerrar.getScene().getWindow(); // Query
    stage.setTitle(titulo); // Modifier
    stage.setScene(new Scene(root)); // Modifier
    // ...
}

```

## Extract Method

En la clase `GestorUsersContras`, los métodos `userExiste()` y `hashDeUsuario()` leen el archivo CSV y repiten la misma lógica de lectura e iteración para buscar al usuario por su nombre. Se debe extraer este bloque a un método auxiliar como `buscarLineaPorUsuario()`:

```java
ArrayList<String> usuarios = csv.readFile(rutaUsuarios);
for(String u : usuarios){
    String [] args = u.split(",",2);
    if (Objects.equals(user, args[0])){
        // ...
    }
}

```

## Substitute Algorithm

En la clase `GestorUsersContras`, método `guardarUsuario()`, hay un bloque condicional simulado:

```java
if(false){  // usuario existe
    return false;
}

```

En la clase `VisualizarDatosController`, hay un algoritmo anidado ineficiente en el método `exportarCSV()` que itera sobre la lista múltiples veces:

```java
for (Alumno a : Controller.alumnos){
    if(a.getCalFinal() == -1){
        lanzarErrorExportarCSV();
        return;
    }else{
        ListaAlumnos alumnoslista = new ListaAlumnos();
        for (Alumno a1 : Controller.alumnos){
            alumnoslista.agregar(a1);
        }
        // ...
    }
}

```

## Replace Nested Conditional with Guard Clauses

En el método `pantallaNombre()` de `VisualizarDatosController`, hay anidaciones de condicionales `if` que se pueden reemplazar por returns tempranos:

```java
if(nombre.isPresent()){
    String nombreArchivo = nombre.get().trim();
    if (!nombreArchivo.isEmpty()) {
        return nombreArchivo;
    }else {
        return null;
    }
}else
    return null;

```

## Hide Method

La clase `Controller` incluye métodos públicos (`abrirVentana`, `cambiarVista`, `cerrarVolverAlMenu`) que en realidad solo son usados por sus clases hija dentro de la jerarquía, por lo que se pueden cambiar a `protected`:

```java
public void cerrarYAbrirNuevaVentana(String titulo, String fxml, Button botonCerrar){ ... }

```

## Encapsulate Field

En la clase `Controller`, el atributo global de los alumnos está expuesto de forma pública:

```java
public static ObservableList<Alumno> alumnos;

```

Además, en `VisualizarDatosController` (método `setupTableView`), se intenta controlar el acceso y validación del atributo desde fuera de la clase `Alumno`:

```java
if (cal >= 0 && cal <= 100) {
    alumno.setCalFinal(cal);
} else {
    lanzarErrorCalificacionNoValida();
}

```

## Replace Array with Object

En la clase `GestorUsersContras` (métodos `userExiste` y `hashDeUsuario`), se divide el arreglo en posiciones genéricas donde `args[0]` es el nombre y `args[1]` es la contraseña. Se puede reemplazar añadiendo un objeto de dominio con dos atributos:

```java
String [] args = u.split(",",2);
if (Objects.equals(user, args[0])){
    return args[1].replace("\"","").strip(); 
}

```

## Consolidate Conditional Expression

En la clase `GestorUsersContras` en el método `userExiste()`, hay condicionales separados que conducen al mismo resultado lógico y repiten el mismo mensaje de error. Se pueden unir en un solo bloque:

```java
if (!Files.exists(rutaUsuarios)){
    System.out.println("No hay usuarios registrados");
    return false;
}
ArrayList<String> usuarios = csv.readFile(rutaUsuarios);
if (usuarios == null || usuarios.isEmpty()){
    System.out.println("No hay usuarios registrados");
    return false;
}

```
