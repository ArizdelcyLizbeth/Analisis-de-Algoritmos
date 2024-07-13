/**
 * La clase Arista representa una arista en un grafo dirigido.
 * Cada arista tiene un extremo de salida y un extremo de entrada,
 * que son vértices en el grafo.
 * @author Nepomuceno Escarcega Arizdelcy Lizbeth
 * @version 1.0
 */
public class Arista {
    /**
     * El vértice de salida de la arista.
     */
    public Vertice extremoSalida;
    /**
     * El vértice de entrada de la arista.
     */
    public Vertice extremoEntrada;
    /**
     * Constructor para crear una nueva instancia de la clase Arista.
     * @param extremoSalida   El vértice de salida de la arista.
     * @param extremoEntrada  El vértice de entrada de la arista.
     */
    public Arista (Vertice extremoSalida, Vertice extremoEntrada){
        this.extremoSalida = extremoSalida;
        this.extremoEntrada = extremoEntrada;
    }
    /**
     * Compara esta arista con otra arista para verificar si son iguales.
     * @param arista La arista con la que se va a comparar.
     * @return true si las aristas son iguales, false en caso contrario.
     */
    public boolean equals(Arista arizta){
        return this.extremoSalida.equals(arizta.extremoSalida) &&
               this.extremoEntrada.equals(arizta.extremoEntrada);
    }
    /**
     * Devuelve una representación en cadena de la arista.
     * @return La representación en cadena de la arista.
     */
    public String toString(){
        return "("+extremoSalida.nombre+","+extremoEntrada.nombre+")";
    }
}