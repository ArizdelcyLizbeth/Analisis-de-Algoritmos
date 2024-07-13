/**
 * La clase Arista representa una arista en una grafica, con dos vertices (extremo1 y extremo2)
 * y un peso asociado.
 * @author Nepomuceno Escarcega Arizdelcy Lizbeth
 * @version 1.0
 */
public class Arista implements Comparable<Arista>{

  Vertice extremo1;
  Vertice extremo2;
  int peso;
  boolean utilizada = false;

   /**
   * Constructor de la clase Arista.
   * @param extremo1 Primer vertice de la arista.
   * @param extremo2 Segundo vertice de la arista.
   * @param peso Peso asociado a la arista.
   */
  public Arista(Vertice extremo1, Vertice extremo2, int peso){
    this.extremo1 = extremo1;
    this.extremo2 = extremo2;
    this.peso = peso;
  }

  /**
   * Compara la arista actual con otra arista para determinar si son iguales.
   * @param a Arista a comparar con la actual.
   * @return true si las aristas son iguales, false en caso contrario.
   */
  public boolean equals(Arista a){
    boolean iguales = false;
    if(this.extremo1.equals(a.extremo1) && this.extremo2.equals(a.extremo2)){
      iguales = true;
    }else if(this.extremo1.equals(a.extremo2) && this.extremo2.equals(a.extremo1)){
      iguales = true;
    }
    return iguales;
  }

  /**
   * Devuelve una representacion en cadena de la arista.
   * @return Cadena que representa la arista en el formato "extremo1extremo2: peso".
   */
  public String toString(){
    String s = extremo1.toString() + extremo2.toString() + ": "+ peso;
    return s;
  }

  /**
   * Compara la arista actual con otra arista basandose en sus pesos.
   * @param a Arista con la que se va a comparar.
   * @return Valor negativo si el peso es menor, valor positivo si el peso es mayor,
   * y cero si los pesos son iguales.
   */
  @Override
  public int compareTo(Arista a) {
    if (peso < a.peso){
      return -1;
    }else if (peso > a.peso) {
      return 1;
    }else{
      return 0;
    }
  }
}
