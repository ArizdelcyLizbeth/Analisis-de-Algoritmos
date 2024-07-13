import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
/**
 * La clase Lector proporciona métodos para leer contenido de un archivo de texto.
 * @author Nepomuceno Escarcega Arizdelcy Lizbeth
 * @version 1.0
 */
public class Lector {
    /**
     * Lee la primera línea de un archivo de texto y devuelve sus elementos como una lista de cadenas.
     * @param nombreArchivo El nombre del archivo a leer.
     * @return Una lista de cadenas que representa los elementos de la primera línea del archivo.
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
 * Lee el contenido del archivo, excluyendo la primera línea, y devuelve sus elementos como una lista de cadenas.
 * @param nombreArchivo El nombre del archivo a leer.
 * @return Una lista de cadenas que representa los elementos del resto de las líneas del archivo.
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
}