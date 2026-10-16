# Refactorizaciones

1. [**Extract Class**](#extract-class)
2. [**Extract Interface**](#extract-interface)
3. [**Pull Up Method**](#pull-up-method)
4. [**Hide Delegate**](#hide-delegate)
5. [**Introduce Parameter Object**](#introduce-parameter-object)



## Extract Class
En la clase de visualizar datos controler se exporta un archivo CSV y PDF
lo que mezcla las responsabilidades podemos hacer una clase para exportarPDF y otra para exportarCSV

Tambien en las clases de CSV manipulation y PDF se pueden dividir para solo leer

## Extract interface
Las clases CSVManipulation y PDFManipulation tienen comportamientos parecidos, se pueden
relacionar por medio de una interfaz

## Pull up method
El metodo de volver al menu lo usan varias clases y el codigo se repite mucho

## HIDE DELEGATE
por ejemplo el metodo cerrar y abrir nueva ventana
se puede ocultar

## Introduce Parameter Object
Siempre se pasa la ruta y el titulo de la ventana por lo que podemos crear un objeto ENUM para meter ahi los
titulos y hacer algo como MENU_VISTA.getTitulo()

## Replace Magic Number with Symbolic Constant
En el metodo de GestorUserContras