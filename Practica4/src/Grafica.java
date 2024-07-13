/**
 * La clase Grafica representa una grafica no dirigida mediante una lista de vertices y aristas.
 * Proporciona metodos para realizar operaciones como el recorrido en profundidad (DFS).
 * @author Nepomuceno Escarcega Arizdelcy Lizbeth
 * @version 1.0
 */
import java.util.*;

public class Grafica{

  ArrayList<Vertice> vertices; //Lista de vertices en una grafica
  ArrayList<Arista> aristas; //Lista de aristas de la grafica
  int componentesConexas = 0;

  /**
   * Constructor de la clase Grafica.
   * @param vertices Lista de vertices para la grafica.
   * @param aristas Lista de aristas para la grafica.
   */
  public Grafica(ArrayList<Vertice> vertices, ArrayList<Arista> aristas){
    this.vertices = vertices;
    this.aristas = aristas;
    int identificadorUn = 1;
    for(Vertice v : this.vertices){
      v.identificadorUnico = identificadorUn;
      identificadorUn++;
    }
  }

  /**
   * Busca y devuelve una arista auxiliar para el algoritmo de DFS.
   * @param lV Lista de vertices.
   * @param lA Lista de aristas.
   * @return Arista auxiliar para el DFS.
   */
  public Arista buscaAristaAuxiliar(ArrayList<Vertice> lV, ArrayList<Arista> lA){
    Arista a = null;
    for(Arista uv : lA){
      if(lV.contains(uv.extremo1) && !lV.contains(uv.extremo2)){
        a = uv;
      }else if(!lV.contains(uv.extremo1) && lV.contains(uv.extremo2)){
        a = uv;
      }
    }

    for(Arista uv2 : lA){
      if(lV.contains(uv2.extremo1) && !lV.contains(uv2.extremo2)){
        if(uv2.peso < a.peso && !uv2.utilizada){
          uv2.utilizada = true;
          a = uv2;
        }
      }else if(!lV.contains(uv2.extremo1) && lV.contains(uv2.extremo2)){
        if(uv2.peso < a.peso && !uv2.utilizada){
          uv2.utilizada = true;
          a = uv2;
        }
      }
    }

    return a;
  }

  /**
   * Devuelve una lista de vertices vecinos del vertice dado.
   * @param v Vertice para el cual se obtienen los vecinos.
   * @return Lista de vertices vecinos.
   */
  public LinkedList<Vertice> regresaVecinos(Vertice v){
    LinkedList<Vertice> vecinos = new LinkedList<Vertice>();
    for(Arista uv : this.aristas){
      if(uv.extremo1.equals(v)){
        vecinos.add(uv.extremo2);
      }else if(uv.extremo2.equals(v)){
        vecinos.add(uv.extremo1);
      }
    }
    return vecinos;
  }

  /**
   * Verifica si existe algun vertice no visitado en la grafica.
   * @return true si existe al menos un vertice no visitado, false en caso contrario.
   */
  public boolean existeVerticeNoVisitado(){
    boolean existe = false;
    for(Vertice x : this.vertices){
      if(!x.visitado){
        existe = true;
        break;
      }
    }
    return existe;
  }

  /**
   * Devuelve el primer vertice no visitado en la grafica.
   * @return Vertice no visitado.
   */
  public Vertice regresaNoVisitado(){
    Vertice v = null;
    for(Vertice x : this.vertices){
      if(x.visitado == false){
        v = x;
        break;
      }
    }
    return v;
  }

  /**
   * Realiza un recorrido en profundidad (DFS) en la grafica.
   * @param raiz Vertice de inicio para el recorrido.
   * @param verticesArbol Lista de vertices del arbol resultante.
   * @param aristasArbol Lista de aristas del arbol resultante.
   * @param componenteAux Numero de componente conexa.
   */
  public void dfsBosqueAux(Vertice raiz, ArrayList<Vertice> verticesArbol, ArrayList<Arista> aristasArbol, int componenteAux){
    LinkedList<Vertice> cola = new LinkedList<Vertice>();
    verticesArbol.add(raiz);
    raiz.visitado = true;
    raiz.componente = componenteAux;

    for(Vertice x : this.regresaVecinos(raiz)){
      x.visitado = true;
      x.componente = componenteAux;
      cola.add(x);
    }

    while(cola.size() != 0){
      Arista a = this.buscaAristaAuxiliar(verticesArbol, this.aristas);
      aristasArbol.add(a);

        if(verticesArbol.contains(a.extremo1)){
          verticesArbol.add(a.extremo2);
          a.extremo2.componente = componenteAux;
          boolean link = cola.remove(a.extremo2);

          for(Vertice aux : this.regresaVecinos(a.extremo2)){
            if(aux.visitado == false){
              aux.visitado = true;
              aux.componente = componenteAux;
              cola.add(aux);
            }
          }

        }else if(verticesArbol.contains(a.extremo2)){

          verticesArbol.add(a.extremo1);
          a.extremo1.componente = componenteAux;
          boolean brenda = cola.remove(a.extremo1);

          for(Vertice aux : this.regresaVecinos(a.extremo1)){
            if(aux.visitado == false){
              aux.componente = componenteAux;
              aux.visitado = true;
              cola.add(aux);
            }
          }

        }
    }
  }

  /**
   * @return Grafica que representa el bosque resultante.
   */
  public Grafica dfsBosque(){
    Vertice raiz = this.vertices.get(0);
    ArrayList<Vertice> verticesArbol = new ArrayList<Vertice>();
    ArrayList<Arista> aristasArbol = new ArrayList<Arista>();
    this.dfsBosqueAux(raiz, verticesArbol, aristasArbol, 1);
    boolean sigue = this.existeVerticeNoVisitado();
    int componente = 2;
    while(sigue){
      Vertice raiz2 = regresaNoVisitado();
      this.dfsBosqueAux(raiz2, verticesArbol, aristasArbol, componente);
      sigue = this.existeVerticeNoVisitado();
      componente++;
    }
    this.componentesConexas = componente;
    return new Grafica(verticesArbol, aristasArbol);
  }

  /**
   * Genera una representacion en cadena de los arboles obtenidos mediante DFS.
   * @return Cadena que representa los arboles.
   */
  public String dfsArboles(){
    Grafica bosque = this.dfsBosque();
    Grafica[] arbolesBosque = new Grafica[this.componentesConexas-1];
    for(int i = 0; i < arbolesBosque.length; i++){
      arbolesBosque[i] = new Grafica(new ArrayList<Vertice>(), new ArrayList<Arista>());
    }

    for(Vertice v : bosque.vertices){
      arbolesBosque[v.componente-1].vertices.add(v);
    }

    for(Arista a : bosque.aristas){
      arbolesBosque[a.extremo1.componente-1].aristas.add(a);
    }

    int cont = 1;
    String s = "";
    for(Grafica g : arbolesBosque){
      s = s + "Arbol componente " + cont + "\n";
      s = s + g.toString() + "\n";
      cont++;
    }
    return s;
  }

  //Metodo para solucion adicional

  /**
   * Realiza el algoritmo de Kruskal en la grafica para formar un bosque.
   * @return Grafica que representa el bosque generador de peso minimo resultante.
   */
  public Grafica kruskalBosque(){
    ArrayList<Arista> aristasArbol = new ArrayList<Arista>();
    Collections.sort(this.aristas);
    ArrayList<Integer> identificadoresUn = new ArrayList<Integer>();
    for(Arista a : this.aristas){
      if(a.extremo1.identificadorUnico != a.extremo2.identificadorUnico){
        a.extremo1.identificadorUnico = Math.min(a.extremo2.identificadorUnico, a.extremo1.identificadorUnico);
        a.extremo2.identificadorUnico = Math.min(a.extremo2.identificadorUnico, a.extremo1.identificadorUnico);
        aristasArbol.add(a);
        if(!identificadoresUn.contains(a.extremo1.identificadorUnico)){
          identificadoresUn.add(a.extremo1.identificadorUnico);
        }
      }
    }
    Grafica g = new Grafica(new ArrayList<Vertice>(), aristasArbol);
    g.vertices = this.vertices;
    g.componentesConexas = identificadoresUn.size();
    for(Vertice v : g.vertices){
      int compo = identificadoresUn.indexOf(v.identificadorUnico);
      v.componente = compo+1;
    }
    return g;
  }

  /**
   * Genera una representacion en cadena de los arboles obtenidos mediante Kruskal.
   * @return Cadena que representa los arboles conexos.
   */
  public String kruskalArboles(){
    Grafica g = this.kruskalBosque();
    Grafica[] arbolesBosque = new Grafica[g.componentesConexas];
    for(int i = 0; i < arbolesBosque.length; i++){
      arbolesBosque[i] = new Grafica(new ArrayList<Vertice>(), new ArrayList<Arista>());
    }

    for(Vertice v : g.vertices){
      arbolesBosque[v.componente-1].vertices.add(v);
    }

    for(Arista a : g.aristas){
      arbolesBosque[a.extremo1.componente-1].aristas.add(a);
    }

    int cont = 1;
    String s = "";
    for(Grafica h : arbolesBosque){
      s = s + "Arbol componente " + cont + "\n";
      s = s + h.toString() + "\n";
      cont++;
    }
    return s;
  }

  /**
   * Devuelve una representacion en cadena de la grafica.
   * @return Cadena que representa la grafica en formato de vertices y aristas.
   */
  public String toString(){
    String ver = "";
    int cont = 0;
    for(Vertice x : this.vertices){
      if(cont < this.vertices.size()-1){
        ver = ver + x.toString() + ",";
      }else{
        ver = ver + x.toString();
      }
      cont++;
    }

    int cont2 = 0;
    String ar = "";
    for(Arista y : this.aristas){
      ar = ar + y.extremo1.toString() + "," + y.extremo2.toString() + ": " + y.peso + "\n";
    }

    return ver + "\n" + ar;
  }

}
