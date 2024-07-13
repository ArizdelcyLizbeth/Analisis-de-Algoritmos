/**
 * La clase Lector para leer y procesar archivos.
 * @author Nepomuceno Escarcega Arizdelcy Lizbeth
 * @version 1.0
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;

public class Lector {

    /**
     * Lee el archivo especificado y devuelve una lista de elementos
     * de la primera linea, separados por comas.
     * @param nombreArchivo Nombre del archivo a leer.
     * @return Lista de elementos de la primera linea del archivo.
     */
    public static List<String> obtenerElementosPrimeraLinea(String nombreArchivo) {
        List<String> elementos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String primeraLinea = br.readLine();
            if (primeraLinea != null) {
                String[] elementosArray = primeraLinea.split(",");
                for (String elemento : elementosArray) {
                    elementos.add(elemento);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return elementos;
    }

    /**
     * Lee el archivo especificado y devuelve una lista de elementos de las
     * lineas restantes, omitiendo la primera linea.
     * @param nombreArchivo Nombre del archivo a leer.
     * @return Lista de elementos de las lineas restantes del archivo.
     */
    public static List<String> obtenerElementosRestoLineas(String nombreArchivo) {
        List<String> elementos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            boolean primeraLineaLeida = false;
            while ((linea = br.readLine()) != null) {
                if (primeraLineaLeida) {
                    elementos.add(linea.trim());
                } else {
                    primeraLineaLeida = true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return elementos;
    }

    /**
     * @param bosque Contenido a escribir en el archivo.
     * @param nombreArchivo Nombre del archivo de salida.
     */
    public static void escribeTxt(String bosque, String nombreArchivo){

        try {
            File archivo = new File(nombreArchivo);
            FileWriter escritor = new FileWriter(archivo);
            BufferedWriter bufferEscritura = new BufferedWriter(escritor);
            bufferEscritura.write(bosque);
            bufferEscritura.close();
            System.out.println("Los arboles de cada componente conexa se encuentran en el archivo: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Ha habido un error, vuelve a intentarlo: " + e.getMessage());
        }
    }
}
