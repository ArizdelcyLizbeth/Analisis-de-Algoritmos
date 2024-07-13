import java.util.List;
import java.util.ArrayList;
/**
 * La clase Pruebados es una clase de prueba que utiliza las clases Vertice, Arista, Lector y Grafica
 * para crear una gráfica y aplicar el teorema.
 * @author Nepomuceno Escarcega Arizdelcy Lizbeth
 * @version 1.0
 */
public class Pruebados{
    /**
     * @param args Los argumentos de línea de comandos (no se utilizan en este caso).
     */
    public static void main(String[] args){

        String nombreArchivo = "Archivo.txt";
        // Obtener elementos de la primera línea del archivo
        List<String> elementosPrimeraLinea = Lector.obtenerElementosPrimeraLinea(nombreArchivo);
        // Obtener elementos del resto de las líneas del archivo
        List<String> elementosRestoLineas = Lector.obtenerElementosRestoLineas(nombreArchivo);
        // Crear una lista de vértices
        ArrayList<Vertice> vertices = new ArrayList<Vertice>();
        // Crear una lista de aristas
        ArrayList<Arista> aristas = new ArrayList<Arista>();
        // Crear vértices a partir de los elementos de la primera línea
        for (String s : elementosPrimeraLinea) {
            vertices.add(new Vertice(s));

        }
        // Crear aristas a partir de los elementos del resto de las líneas
        for (String ss : elementosRestoLineas) {
            String[] a = ss.split(",");
            Vertice ver1 = new Vertice("Ari uwu");
            Vertice ver2 = new Vertice("Arii uwu");
            // Buscar los vértices correspondientes en la lista de vértices
            for (Vertice v1 : vertices) {
                if(a[0].equals(v1.nombre)){
                    ver1 = v1;
                }
            }
            for (Vertice v2 : vertices) {
                if(a[1].equals(v2.nombre)){
                    ver2 = v2;
                }
            }
            // Crear una arista con los vértices encontrados
            Arista aa = new Arista(ver1,ver2);
            aristas.add(aa);
        }
        // Crear una gráfica a partir de los vértices y aristas
        Grafica g = new Grafica(vertices, aristas);
        System.out.println("El conjunto independiente es:");
        System.out.println(g.teorema());
    }
}