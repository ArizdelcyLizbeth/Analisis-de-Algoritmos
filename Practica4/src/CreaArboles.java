/**
 * La clase CreaArboles proporciona un programa
 * principal para la creacion de arboles
 * @author Nepomuceno Escarcega Arizdelcy Lizbeth
 * @version 1.0
 */
import java.util.*;

public class CreaArboles{
  
    /**
     * El metodo principal que se ejecuta al iniciar el programa.
     * @param args Argumentos de linea de comandos.
     */
    public static void main(String args[]){

        String nombreArchivo = "Archivo.txt";

        List<String> elementosPrimeraLinea = Lector.obtenerElementosPrimeraLinea(nombreArchivo);
        List<String> elementosRestoLineas = Lector.obtenerElementosRestoLineas(nombreArchivo);
        ArrayList<Vertice> vertices = new ArrayList<Vertice>();
        ArrayList<Arista> aristas = new ArrayList<Arista>();
        for (String s : elementosPrimeraLinea) {
            vertices.add(new Vertice(s));
        }
        for (String ss : elementosRestoLineas) {
            String[] a = ss.split("[,:]");
            Vertice ver1 = new Vertice("Ari uwu");
            Vertice ver2 = new Vertice("Arii uwu");
            for (Vertice v1 : vertices) {
                if(a[0].equals(v1.identificador)){
                    ver1 = v1;
                }
            }
            for (Vertice v2 : vertices) {
                if(a[1].equals(v2.identificador)){
                    ver2 = v2;
                }
            }
            Arista aa = new Arista(ver1,ver2,Integer.parseInt(a[2]));
            aristas.add(aa);
        }

        String s = "//////////////////////////////////////////////(/////////////////////////////(((((((((((((((((((((((" + "\n";
        s = s + "//////////////////////////////////////////////////////((/////////////////////((((((((((((((((((((((/" + "\n";
        s = s + "////#//////////////////////////////////////(/////#/////#(//////(///////////////(((((((((((((((((((((" + "\n";
        s = s + "(/(//#///////////////////////////////////////((////#////#(////#/////(////////////(((((((((((((((((((" + "\n";
        s = s + "//////#///////////////////////////////////////((%((((%((/(#///%////#/////////////(((((((((((((((((((" + "\n";
        s = s + "*////(//(///////////////////////////////////(#(((((%(((#%##%%##%((%//////////////(((((((((((((((((((" + "\n";
        s = s + ",//(/(((((/(/////////////////////////////(((((((((##%&@&&%&%(//(#%%(%////////////////((/(((((((/(/(" + "\n";
        s = s + ",,,//(%###@#&%&@@&#((//////////////////////((((((((#@,%%#@@&%(//(%@@@@&#(///////////////////////////" + "\n";
        s = s + ",,,////(#,#/&@@@@%((##//////////////////////((((((&,*#(*@@@@&%%(&@@@@@@////////////////////////////" + "\n";
        s = s + "  .,,/////*/(@@@@@@%&&@@@(//////////////////////////,,#/,@@@#@@@&@@@@@@@#/***///////////////////////" + "\n";
        s = s + "   .,,//***(,&@&@@@%&@@@@@///******************/////*,*(*/@/@@@@@@@@@@@@//****//////////////////////" + "\n";
        s = s + "   ,,,*/****//&(&@@@@@@@@&//*********************//***,,#(/%&&&&&&&&&&//*******/////////////////////" + "\n";
        s = s + "   ,.,*******/(#&@@@@@@@%///******************************,###((///////********/////////////////////" + "\n";
        s = s + "   ,,,*********//(((///////////***************************//////////**********////////////////////((" + "\n";
        s = s + "   ,,,********//////////**//***********////**********************************//////////////////(/(((" + "\n";
        s = s + "   ,,*********************/************/////*********************************///////////////((((((((" + "\n";
        s = s + "   ..**********************//*********/////*********************************////////////////((((((((" + "\n";
        s = s + "   ,,*/*******************/////////////////********************************//////////////(((((((((((" + "\n";
        s = s + "   ,,*//*//**************////////////////*********************************//////////////((((((((((((" + "\n";
        s = s + "   ,,,/////***********/////////////////////////////////******************/////////////((((((((((((((" + "\n";
        s = s + "   ,.,,/////*********//(((((////////////////////////(((/*************//////////////((((((((((((((((#" + "\n";
        s = s + "    ,,,,///////**//////(((///////////////////////((((((//////////////////////////(((((((((((((((####" + "\n";
        s = s + "     ,,,,//////////////////////////(////////((((((((//////////////////////////(((((((((((((((#######" + "\n";
        s = s + "       ,,,,*/////////(((((/////////*////((((((////*******/////////////////((((((((((((((#(#########(" + "\n";
        s = s + "              ,///(((((((((((((/////////*/***********////////(((((/(//(((((((((((((((#######%%%%%%%#" + "\n";
        s = s + "               ,,,,*/(((((((##%%%%%%#(///////////////////(((((((((((((((((((((((####%%%%%%%%%#####(#" + "\n";
        s = s + "             ,,//////(((//(//////////((((#####((((##%%%%#######(((((((#((#############((((((((((((%%" + "\n";
        s = s + "          ,*/////////////////////////////////////////////////////////////((((((((((((((((((((((((%#(" + "\n";
        s = s + "        */////////////////////////////////////////////////////////////////////////////////(((((((#//" + "\n";
        s = s + "      .//////////////////////////////////////////////////////////////////////////////////(((((((#(//" + "\n";
        s = s + "     ////////////////////////////////////////////////////////////////////////////////////(((((((%///" + "\n";

        System.out.println(s);

        Grafica g = new Grafica(vertices,aristas);

        if(args.length != 0){
          if(args[0].equals("dfs")){
            String bosque = g.dfsArboles();
            Lector.escribeTxt(bosque, "Arboles.txt");
          }else if(args[0].equals("kruskal")){
            String bosque = g.kruskalArboles();
            Lector.escribeTxt(bosque, "ArbolesKruskal.txt");
          }else{
            System.out.println("No se ha pasado un argumento valido, vuelve a intentarlo");
          }
        }else{
          System.out.println("No se ha pasado un argumento valido, vuelve a intentarlo");
        }
  }
}