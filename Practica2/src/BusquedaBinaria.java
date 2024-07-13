/**
 * La clase BusquedaBinaria proporciona métodos para realizar una búsqueda binaria en una secuencia 
 * ordenada de números enteros.
 * @author  Nepomuceno Escarcega Arizdelcy Lizbeth
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BusquedaBinaria{
    /**
     * Punto de entrada principal del programa.
     * @param args Los argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main (String [] args){
        // Lee la secuencia de números desde el archivo
        int[] secuencia = leerNumerosDesdeTxt("Archivo.txt");
        // Lee el elemento a buscar desde el archivo
        int elemento = leerEnteroDesdeTxt("Archivo.txt");
        boolean secuenciaValida = true;
        // Verifica si la secuencia está ordenada
        for(int i = 0; i < secuencia.length -1; i++){
            if(secuencia[i] >= secuencia[i+1]){
                secuenciaValida = false;
            }
        } 
        // Si la secuencia está ordenada, realiza la búsqueda binaria
        if(secuenciaValida){
            bBPrincipal(secuencia, elemento);
        } else {
            System.out.println("Error, sencuencia desordenada...");
        }
    }
    /**
     * Método auxiliar para la búsqueda binaria recursiva.
     * @param secuencia La secuencia ordenada en la que se realizará la búsqueda.
     * @param inicio    El índice de inicio del espacio de búsqueda actual.
     * @param fin       El índice de finalización del espacio de búsqueda actual.
     * @param elemento  El elemento que se está buscando.
     * @param iteracion El número de iteración actual.
     */
    public static void bBAuxiliar(int[] secuencia, int inicio, int fin, int elemento, int iteracion){
        System.out.print("En la iteración: " + iteracion + " nuestro espacio de búsqueda es: ");
        System.out.print(imprimirEspacioBusqueda(secuencia, inicio, fin));
        System.out.println(" El elemento a buscar es: " + elemento);
        int mid;
        if (inicio > fin){ 
            System.out.println("El elemento no está en la secuencia, y el algoritmo tomó: " + iteracion + " iteraciones.");
        } else {
            mid = (int)(Math.floor((inicio + fin)/2));
            if (secuencia[mid] == elemento){
                System.out.println("El elemento se encuentra en la posición: "+ mid + " de la secuencia, y el algoritmo tomó: " + iteracion + " iteraciones.");
            } else if (secuencia[mid] > elemento){
               bBAuxiliar(secuencia, inicio, mid -1, elemento, iteracion + 1);
            } else {
               bBAuxiliar(secuencia, mid +1, fin, elemento, iteracion + 1);
            }
        } 
    }
    /**
     * Realiza la búsqueda binaria en la secuencia ordenada.
     * @param secuencia La secuencia ordenada en la que se realizará la búsqueda.
     * @param elemento  El elemento que se está buscando.
     */
    public static void bBPrincipal(int[] secuencia, int elemento){
         bBAuxiliar(secuencia, 0, secuencia.length -1, elemento, 1);
    }
    /**
     * @param secuencia La secuencia ordenada en la que se está buscando.
     * @param inicio    El índice de inicio del espacio de búsqueda actual.
     * @param fin       El índice de finalización del espacio de búsqueda actual.
     * @return Una cadena que representa el espacio de búsqueda actual.
     */
    public static String imprimirEspacioBusqueda(int[] secuencia, int inicio, int fin){
        String espacioDeBusqueda = "[";
        for(int i = 0; i < secuencia.length; i++){
            if(i <= fin && i >= inicio){
                if(i == fin) {
                    espacioDeBusqueda += secuencia[i]; 
                } else {
                    espacioDeBusqueda += secuencia[i] + ","; 
                }
            }
        } 
        espacioDeBusqueda += "]"; 
        return espacioDeBusqueda; 
    } 
    /**
     * Lee una secuencia de números desde un archivo de texto.
     * @param nombreArchivo El nombre del archivo de texto que contiene la secuencia de números.
     * @return Un array de enteros que representa la secuencia de números leída desde el archivo.
     */
    public static int[] leerNumerosDesdeTxt(String nombreArchivo) {
        int[] numeros = new int[0];
        
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea = br.readLine();
            if (linea != null) {
                String[] numerosComoTexto = linea.split(",");
                numeros = new int[numerosComoTexto.length];
                for (int i = 0; i < numerosComoTexto.length; i++) {
                    numeros[i] = Integer.parseInt(numerosComoTexto[i].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir texto a número: " + e.getMessage());
        }
        
        return numeros;
    }
    /**
     * Lee un número entero desde un archivo de texto.
     * @param nombreArchivo El nombre del archivo de texto que contiene el número entero.
     * @return El número entero leído desde el archivo.
     */
    public static int leerEnteroDesdeTxt(String nombreArchivo) {
        int valor = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea = br.readLine();
            if (linea != null) {
                linea = br.readLine();
                if (linea != null) {
                    valor = Integer.parseInt(linea.trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Error al convertir texto a número: " + e.getMessage());
        }
        
        return valor;
    }
}