import java.util.ArrayList;
/**
 * La clase Vertice representa un vértice en un grafo dirigido.
 * @author Nepomuceno Escarcega Arizdelcy Lizbeth
 * @version 1.0
 */
public class Vertice {
    String nombre;
    /**
     * Lista de vértices vecinos de salida.
     */
    public ArrayList<Vertice> exVecinos = new ArrayList<Vertice>();
    /**
     * Lista de vértices vecinos de entrada.
     */
    public ArrayList<Vertice> inVecinos = new ArrayList<Vertice>();
    /**
     * Constructor para crear una nueva instancia de la clase Vertice.
     * @param nombre El nombre del vértice.
     */
   public Vertice(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Devuelve el grado de salida del vértice, que es el número de vecinos de salida.
     * @return El grado de salida del vértice.
     */
    public int exGrado(){
        return exVecinos.size();
    }
    /**
     * Devuelve el grado de entrada del vértice, que es el número de vecinos de entrada.
     * @return El grado de entrada del vértice.
     */
    public int inGrado(){
        return inVecinos.size();
    }
    /**
     * Compara este vértice con otro vértice para verificar si son iguales.
     * @param elOtroVertice El otro vértice con el que se va a comparar.
     * @return true si los vértices son iguales, false en caso contrario.
     */
    public boolean equals(Vertice elOtroVertice){
        return this.nombre.equals(elOtroVertice.nombre);
    }
    /**
     * @return La representación en cadena del vértice.
     */
    public String toString(){
        return nombre;
    }
}