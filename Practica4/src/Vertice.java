/**
 * La clase Vertice representa un vertice en una grafica.
 * @author Nepomuceno Escarcega Arizdelcy Lizbeth
 * @version 1.0
 */
public class Vertice{

  String identificador;
  boolean visitado = false;
  int componente = 0;
  int identificadorUnico = 0;

  /**
   * Constructor de la clase Vertice.
   * @param identificador Identificador del vertice.
   */
  public Vertice(String identificador){
    this.identificador = identificador;
  }

  /**
   * Compara el vertice actual con otro vertice para determinar si son iguales.
   * @param v Vertice a comparar con el actual.
   * @return true si los vertices son iguales (tienen el mismo identificador),
   * false en caso contrario.
   */
  public boolean equals(Vertice v){
    return this.identificador.equals(v.identificador);
  }

  /**
   * Devuelve una representacion en cadena del vertice.
   * @return Cadena que representa el vertice, utilizando su identificador.
   */
  public String toString(){
    return this.identificador;
  }
}
