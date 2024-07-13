import java.util.ArrayList;
/**
 * La clase Grafica representa un grafo dirigido mediante listas de vértices y aristas.
 * Permite realizar operaciones como encontrar el conjunto independiente máximo.
 * @author Nepomuceno Escarcega Arizdelcy Lizbeth
 * @version 1.0
 */
public class Grafica {
    /**
     * Lista de vértices en la gráfica.
     */
    public ArrayList<Vertice> vertices;
    /**
     * Lista de aristas en la gráfica.
     */
    public ArrayList<Arista> aristas;
    /**
     * Constructor para crear una instancia de la clase Grafica a partir de una lista de vértices y una lista de aristas.
     * @param vertices La lista de vértices de la gráfica.
     * @param aristas  La lista de aristas de la gráfica.
     */
    public Grafica(ArrayList<Vertice> vertices, ArrayList<Arista> aristas){
		this.vertices = vertices;
		this.aristas = aristas;
		for(Vertice v : this.vertices){
			for(Arista a : this.aristas){
				if(v.equals(a.extremoSalida)){
					v.exVecinos.add(a.extremoEntrada);
				}else if(a.extremoEntrada.equals(v)){
					v.inVecinos.add(a.extremoSalida);
				}
			}
		}
	}
    /**
     * Encuentra el vértice con el mayor grado de salida en la gráfica.
     * @return El vértice con el mayor grado de salida.
     */
    public Vertice exGradoMax(){
        Vertice verExGraMax = this.vertices.get(0);
        for(Vertice ve : this.vertices){
            if(ve.exGrado() > verExGraMax.exGrado()){
                verExGraMax = ve;
            }
        } return verExGraMax;
    }
    /**
     * Verifica si un conjunto de vértices es independiente en la gráfica.
     * @param conjunto El conjunto de vértices a verificar.
     * @return true si el conjunto es independiente, false en caso contrario.
     */
    public boolean conjuntoIndependiente(ArrayList<Vertice> conjunto){
        for(Vertice v1 : conjunto){
            for(Vertice v2 : conjunto){
                /*if(v1.exVecinos.contains(v2)){
                   return false;
                } else if(v1.inVecinos.contains(v2)){
                   return false;
               }*/
               Arista a = new Arista(v1,v2);
               if(this.verificaArista(a)){
                   return false;
               }
            }
        }return true;
    }
    /**
     * Encuentra un vértice en el conjunto dado que no es independiente y lo devuelve.
     * @param conjunto El conjunto de vértices.
     * @param x El vértice que se debe buscar en las vecindades de los vértices del conjunto.
     * @return Un vértice no independiente o un vértice auxiliar en caso de no encontrar ninguno.
     */

    public Vertice verticeNoIndependiente(ArrayList<Vertice> conjunto, Vertice x){
        Vertice v = new Vertice("Auxiliar: ");
        for (Vertice vertice : conjunto) {
             for (Vertice ex : vertice.exVecinos){
                if(ex.equals(x)){
                    v = ex;
                }
             }
             for (Vertice in : vertice.inVecinos) {
                if(in.equals(x)){
                    v = in;
                }
            }
        } return v;
    }
    /**
     * Verifica si una arista está presente en la lista de aristas de la gráfica.
     * @param arista La arista a verificar.
     * @return true si la arista está presente, false en caso contrario.
     */
    public boolean verificaArista(Arista arista){
        for(Arista a : aristas){
            if(a.equals(arista)){
                return true;
            }
        } return false;
    }
    /**
     * Crea una gráfica inducida a partir de un conjunto de vértices.
     * @param conjunto El conjunto de vértices para crear la gráfica inducida.
     * @return La gráfica inducida.
     */
    public Grafica graficaInducida(ArrayList<Vertice> conjunto){
        ArrayList<Arista> aristasInducidas = new ArrayList<Arista>();
        for(Arista a : aristas){
            if(conjunto.contains(a.extremoEntrada) && conjunto.contains(a.extremoSalida)){
                aristasInducidas.add(a);
            }
        }
        ArrayList<Vertice> vertex = new ArrayList<Vertice>();
        for(Vertice aux : conjunto){
            Vertice v = new Vertice(aux.nombre);
            vertex.add(v);
        }
        Grafica grafInducida = new Grafica (vertex, aristasInducidas);
        return grafInducida;
    }
    /**
     * Devuelve una representación en cadena de la gráfica, incluyendo sus vértices y sus vecinos.
     * @return La representación en cadena de la gráfica.
     */
    public String toString(){
		String vert = "";
		for(Vertice v : this.vertices){
			vert = vert + v.toString() + " " + v.exVecinos.toString() + "\n";
		}
        for(Vertice v : this.vertices){
			vert = vert + v.toString() + " " + v.inVecinos.toString() + "\n";
		}
		return vert;
	}
    /**
     * Método auxiliar para el teorema de conjunto independiente máximo.
     * @param acumulador El conjunto acumulador de vértices independientes.
     * @param g La gráfica en la que se realiza el cálculo.
     * @return El conjunto independiente máximo.
     */
    public ArrayList<Vertice> teoremaAuxiliar(ArrayList<Vertice> acumulador, Grafica g){
        if(vertices.size()==1){
            acumulador.add(vertices.get(0));
            if(!g.conjuntoIndependiente(acumulador)){
                Vertice quitado = this.verticeNoIndependiente(acumulador, vertices.get(0));
                this.elimina(acumulador, quitado);
            }
            return acumulador;
        } else if(vertices.size()==0){
            return acumulador;
        }else{
            Vertice max = this.exGradoMax();
            ArrayList<Vertice> quitar = new ArrayList<Vertice>();
            ArrayList<Vertice> exVevinosAux = this.exVecinosVer(g,max);
            for (Vertice vertice : exVevinosAux) {
                quitar.add(vertice);
            } quitar.add(max);
            ArrayList<Vertice> verticeInducida = new ArrayList<Vertice>();
            for (Vertice vertice : this.vertices) {
                if(!this.contiene(quitar,vertice)){
                    verticeInducida.add(vertice);
                }
            }
            if(acumulador.size() == 0){
                acumulador.add(max);
            } else {
                acumulador.add(max);
                if(!g.conjuntoIndependiente(acumulador)){
                    Vertice quitado = this.verticeNoIndependiente(acumulador, max);
                    this.elimina(acumulador, quitado);
                }
            }
            Grafica h = this.graficaInducida(verticeInducida);
            return h.teoremaAuxiliar(acumulador, g);
        }
    }
    /**
      * Calcula el conjunto independiente máximo en la gráfica.
      * @return El conjunto independiente máximo.
      */
    public ArrayList<Vertice> teorema(){
        ArrayList<Vertice> acumulador = new ArrayList<Vertice>();
        return this.teoremaAuxiliar(acumulador, this);
    }
    /**
      * Elimina un vértice de un conjunto de vértices.
      * @param acumulador El conjunto de vértices.
      * @param v El vértice a eliminar.
      */
    public void elimina(ArrayList<Vertice> acumulador, Vertice v){
        int cont = 0;
        for(Vertice aux : acumulador){
            if(v.equals(aux)){
                break;
            }
            cont++;
        }
        acumulador.remove(cont);
    }
    /**
     * Verifica si un vértice está contenido en un conjunto de vértices.
     * @param ind El conjunto de vértices.
     * @param v1  El vértice a verificar.
     * @return true si el vértice está contenido, false en caso contrario.
     */
    public boolean contiene(ArrayList<Vertice> ind, Vertice v1){
        boolean esta = false;
        for(Vertice aux : ind){
            if(v1.equals(aux)){
                esta = true;
                break;
            }
        }
        return esta;
    }
    /**
     * Obtiene la lista de vecinos de salida de un vértice en la gráfica.
     * @param g La gráfica en la que se busca.
     * @param v El vértice del que se quieren obtener los vecinos de salida.
     * @return La lista de vecinos de salida del vértice.
     */
    public ArrayList<Vertice> exVecinosVer(Grafica g, Vertice v){
        ArrayList<Vertice> auxiliar = new ArrayList<Vertice>();
        for(Vertice x : g.vertices){
            if(x.equals(v)){
                auxiliar = x.exVecinos;
            }
        }
        return auxiliar;
    }
}