# Refactorizaciones

1. [**Extract Class**](#extract-class)
2. [**Extract Interface**](#extract-interface)
3. [**Pull Up Method**](#pull-up-method)
4. [**Hide Delegate**](#hide-delegate)
5. [**Introduce Parameter Object**](#introduce-parameter-object)


## Extract Class
En la clase de VisualizarDatosController esta asumiendo responsabilidades de UI y además la lógica de
exportar archivos pdf y csv.
lo que mezcla las responsabilidades podemos hacer una clase para exportarPDF y otra para exportarCSV
Se pueden crear dos nuevas clases `ReporteExporter` o crear dos clases `CSVExporter` y `PDFExporter`

Tambien en las clases de CSV manipulation y PDF se pueden dividir para solo leer y devolver la informacion
Para la clase de `GestorUsersContras`

## Extract interface
Las clases CSVManipulation y PDFManipulation tienen comportamientos parecidos, se pueden
relacionar por medio de una interfaz
Es decir tienen métodos conceptualmente idénticos como los métodos `writeFile` y `writePDF`

## Pull up method
El metodo de `volverAlMenu()` lo usan varias clases y el codigo se repite en las clases `ImportarArchivosController`
y `VisualizarDatosController`

Al igual que se puede subir el método `mostrarAlerta` a  `Controller`

## HIDE DELEGATE
por ejemplo el metodo cerrar y abrir nueva ventana
se puede ocultar

## Introduce Parameter Object
Siempre se pasa la ruta y el titulo de la ventana por lo que podemos crear un objeto ENUM para meter ahi los
titulos y hacer algo como `MENU_VISTA.getTitulo()`

## Replace Magic Number with Symbolic Constant
En el metodo de GestorUserContras contiene muchos números mágicos al igual que mas clases como la alumno

## Separate Query from Modifier
La funcion de `cerrarYAbrirNuevaVentana`, se puede dividir en un *query* `obtenerStage()` y un *modifier* `cerrarVentana()`
y `abrirVentana()` y un metodod orquestador de igual manera tipo `navegarA(ventana)`

## Extract method
En la clase de `GestorUserContras`
Métodos `userExiste` y `hashDeUsuario` Ambos leen el archivo CSV y repiten la logica de lectura e iteracion 
para buscar al usuario por su nombre 
Extraer el método auxiliar para buscarLineaPorUsuario.

## Substitude Algorithm
En la validación de guardar usuario hay un bloque if(false) 
En la clase `VisualizarDatosController` hay un algoritmo raro en el método `exportarCSV()`

## Replace Nested Conditional with Guard Clauses
En el método `pantallaNombre()`
hay una anidaciones de if que se pueden reemplazar por returns tempranos

## Hide Method
La clase controller incluye metodos publicos que en realidad solo son usados
por sus clases hija por lo que se puede cambiar a protected

## Encapsulate fields
Controlar el acceso del metodo dentro del setter no desde `VisualizarDatosController`
```java
if (cal >= 0 && cal <= 100) {
    alumno.setCalFinal(cal);
} else {
        //...
}
```
## Replace array with object
En la clase de `GestorUserContras` se divide el arreglo en secciones donde args[0] es el nombre y args[1].
Se puede reemplazar añadiendo un objeto usuario con dos atributos nombre y hash de usuario

## Consolidate Conditional Expresion
En la clase de `GestorUserContras`  en el método de `userExiste()` tambien en la clase
`ImportarArchivosController` en `validarArchivo()` hay condicionales que condicen al mismo resultado de mostrar alertas
Se pueden unir en un solo bloque condicional para evitar la repeticion de codigo

