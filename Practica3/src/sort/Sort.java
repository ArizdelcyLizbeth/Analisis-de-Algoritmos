package sort;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

public class Sort{

  int[] numeros;

  public Sort(String archivo, int framerate, String metodo){
    EventQueue.invokeLater(new Runnable(){
      @Override
      public void run(){
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        JFrame frame = new JFrame("Ordenamientos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(new Contenedor(archivo, framerate, metodo));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      }catch(Exception e){
        System.out.println("\t:(");
      }
      }
    });
  }

  public class Contenedor extends JPanel{

    private JLabel etiqueta;

    public Contenedor(String archivo, int framerate, String metodo){
      setLayout(new BorderLayout());
      etiqueta = new JLabel(new ImageIcon(createImage(archivo)));
      add(etiqueta);
      JButton botonOrdenar = new JButton("Ordenar");
      add(botonOrdenar, BorderLayout.SOUTH);
      botonOrdenar.addActionListener(new ActionListener(){
        @Override
        public void actionPerformed(ActionEvent e){
          BufferedImage imagen = (BufferedImage) ((ImageIcon) etiqueta.getIcon()).getImage();
          new UpdateWorker(imagen, etiqueta, archivo, framerate, metodo).execute();
        }
      });

    }

    public BufferedImage createImage(String archivo){
      BufferedImage imagen = null;
      try{
        imagen = ImageIO.read(new File("resource/"+archivo));
        ataqueHackerman(imagen);
        Graphics2D g = imagen.createGraphics();
        g.dispose();
      }catch(Exception e){
        System.err.println("(-)\tAsegurate de estar en el directorio 'src'");
        System.err.println("\ty de haber escrito bien el nombre de imagen (la cual debe estar en la carpeta resource)");
      }
      return imagen;
    }

    public void ataqueHackerman(BufferedImage imagen){
      int length = imagen.getHeight()*imagen.getWidth();
      numeros = new int[length];
      for(int i = 0; i < numeros.length; i++)
        numeros[i] = i;
      Random r = new Random();
      for(int i = 0; i < length; i++){
        int j = r.nextInt(length);
        swapImagen(imagen, i, j);
      }
    }

    public void swapImagen(BufferedImage imagen, int i, int j){
      int colI = i%imagen.getWidth();
      int renI = i/imagen.getWidth();
      int colJ = j%imagen.getWidth();
      int renJ = j/imagen.getWidth();
      int aux = imagen.getRGB(colI, renI);
      imagen.setRGB(colI, renI, imagen.getRGB(colJ, renJ));
      imagen.setRGB(colJ, renJ, aux);
      aux = numeros[i];
      numeros[i] = numeros[j];
      numeros[j] = aux;
    }

  }

  public class UpdateWorker extends SwingWorker<BufferedImage, BufferedImage>{

    private BufferedImage referencia;
    private BufferedImage copia;
    private JLabel target;
    int framerate;
    int n;
    String metodo;
    int iteracion;

    public UpdateWorker(BufferedImage master, JLabel target, String archivo, int speed, String algoritmo){
      this.target = target;
      try{
        referencia = ImageIO.read(new File("resource/"+archivo));
        copia = master;
        n = copia.getHeight()*copia.getWidth();
      }catch(Exception e){
        System.err.println(":c Esto no deberia ocurrir");
      }
      framerate = speed; // Indica cada cuantas iteraciones se actualizara la imagen
      metodo = algoritmo;
      iteracion = 0;
    }

    public BufferedImage updateImage(){
      Graphics2D g = copia.createGraphics();
      g.drawImage(copia, 0, 0, null);
      g.dispose();
      return copia;
    }

    @Override
    protected void process(List<BufferedImage> chunks){
      target.setIcon(new ImageIcon(chunks.get(chunks.size() - 1)));
    }

    public void update(){
      for(int i = 0; i < n; i++){
        int indiceDeOriginal = numeros[i];
        int colOriginal = indiceDeOriginal%copia.getWidth();
        int renOriginal = indiceDeOriginal/copia.getWidth();
        int colI = i%copia.getWidth();
        int renI = i/copia.getWidth();
        copia.setRGB(colI, renI, referencia.getRGB(colOriginal, renOriginal));
      }
      publish(updateImage());
    }

    @Override
    protected BufferedImage doInBackground() throws Exception{
      if(metodo.equals("bubble"))
        bubbleSort();
      if(metodo.equals("selection"))
        selectionSort();
      if(metodo.equals("insertion"))
        insertionSort();
      if(metodo.equals("merge"))
        mergeSort();
      if(metodo.equals("quick"))
        quickSort();
      if(metodo.equals("shell"))
        shellSort();
      update();
      return null;
    }

    /**
     * Ordena el arreglo de numeros utilizando el algoritmo Bubble Sort.
    */
    private void bubbleSort(){
      for(int i = 0; i < n-1; i++){
        for(int j = 0; j < n-i-1; j++){
          if(numeros[j] > numeros[j+1])
          swap(j, j+1);
        }
        if(iteracion%framerate == 0){
          update(); // Actualizamos la interfaz grafica solo si han pasado el numero de iteraciones deseadas
        }
        iteracion = (iteracion+1)%framerate; // Aumentamos el numero de iteraciones
      }
    }

    /**
     * Ordena el arreglo de numeros utilizando el algoritmo Selection Sort.
    */
    private void selectionSort(){
      for(int i = 0; i < n-1;i++){
        int min = i;
        for(int j = i+1; j< n; j++){
          if(numeros[j] < numeros[min]){
            min = j;
          }
        }
        swap(i, min);
        if(iteracion%framerate == 0){
          update(); // Actualizamos la interfaz grafica solo si han pasado el numero de iteraciones deseadas
        }
        iteracion = (iteracion+1)%framerate; // Aumentamos el numero de iteraciones
      }
    }

    /**
     * Ordena el arreglo de numeros utilizando el algoritmo Insertion Sort.
    */
    private void insertionSort(){
      for(int i = 1; i < n; i++){
        int temp = numeros[i];
        int j;
        for(j = i; j > 0 && numeros[j-1] > temp; j--){
            numeros[j] = numeros[j-1];
        }
        numeros[j] = temp;
        if(iteracion%framerate == 0){
          update(); // Actualizamos la interfaz grafica solo si han pasado el numero de iteraciones deseadas
        }
        iteracion = (iteracion+1)%framerate; // Aumentamos el numero de iteraciones
      }
    }

    /**
     * Ordena el arreglo de numeros utilizando el algoritmo Merge Sort.
     * Llama al metodo divideMezcla ya que este realiza todo el trabajo.
    */
    private void mergeSort(){
      this.divideMezcla(0,n-1);
    }

    /**
      * Divide y mezcla el arreglo de numeros para implementar el algoritmo Merge Sort.
      * @param izq indice izquierdo del subarreglo.
      * @param der indice derecho del subarreglo.
    */
    private void divideMezcla(int izq, int der){
      int mitad = 0;
      if(izq < der){
        mitad = (int) (Math.floor((izq+der)/2)); //calcula la mitad del arreglo
        this.divideMezcla(izq,mitad); //aplica divide y mezcla al subarreglo izquierdo
        this.divideMezcla(mitad+1,der); //aplica divide y mezcla al subarreglo derecho
        this.mezcla(izq,mitad,mitad+1,der); //mezcla los dos subarreglos 
        if(iteracion%framerate == 0){
          update(); // Actualizamos la interfaz grafica solo si han pasado el numero de iteraciones deseadas
        }
        iteracion = (iteracion+1)%framerate; // Aumentamos el numero de iteraciones
      }
    }

    /**
     * Combina y ordena dos subarreglos consecutivos.
     * 
     * @param izq1 Índice izquierdo del primer subarreglo.
     * @param izq2 Índice izquierdo del segundo subarreglo.
     * @param der1 Índice derecho del primer subarreglo.
     * @param der2 Índice derecho del segundo subarreglo.
    */
    private void mezcla(int izq1, int izq2, int der1, int der2){
      int[] aux = new int[der2-izq1+1]; //areglo donde se guardaran los valores ordenados 
      int i = izq1; //primer indice del sub-arreglo izquierdo
      int j = der1; //primer indice del sub-arreglo derecho
      int k = 0; //indice auxiliar para modificar el arreglo

      while(i <= izq2 && j <= der2){  
        if(numeros[i] <= numeros[j]){  
          aux[k] = numeros[i];        // mueve los k elementos menores de numeros[izq1,..., der2] al arreglo aux[0,...,k]
          i++;                           
        } else {                      
          aux[k] = numeros[j];
          j++;
        }
          k++;
      }

      while(i < izq2 + 1){
        aux[k] = numeros[i];      //mueve el resto de elementos, si hay de numeros[izq1,izq2] al arreglo aux
        i++;
        k++;
      }

      while(j < der2 + 1){
        aux[k] = numeros[j];    //mueve el resto de elementos, si hay de numeros[der1,der2] al arreglo aux
        j++;
        k++;
      }

      for(int h = 0; h < k; h++){
        numeros[h+izq1] = aux[h];     //mueve los datos del arrego aux a numeros
      }
    }

    /**
     * Ordena los arreglos de numeros utilizando el algoritmo Quick Sort.
     * Llama al metodo auxiliar quickSortAux para realizar la ordenacion.
    */
    private void quickSort(){
      quickSortAux(0,n-1);
    }

    /**
      * Metodo auxiliar para el algoritmo Quick Sort. Realiza la particion y llamadas recursivas para ordenar el arreglo.
      * 
      * @param a indice izquierdo del subarreglo.
      * @param b indice derecho del subarreglo.
    */
    private void quickSortAux(int a, int b){
      int j = 0;
      if(a <= b){
        j = partition(a,b);   //particona el arreglo
        quickSortAux(a,j-1);  //reorganiza el arreglo izquierdo
        quickSortAux(j+1,b);  //reorganiza el arreglo derecho
        if(iteracion%framerate == 0){
          update(); // Actualizamos la interfaz grafica solo si han pasado el numero de iteraciones deseadas
        }
        iteracion = (iteracion+1)%framerate; // Aumentamos el numero de iteraciones
      }
    }

    /**
     * Motodo auxiliar para el algoritmo Quick Sort. Realiza la particion y llamadas recursivas para ordenar la matriz.
     * 
     * @param a Indice izquierdo del subarreglo.
     * @param b Indice derecho del subarreglo.
    */
    private int partition(int a, int b){
      int i = a + 1;
      int j = b;

      while(i < b && numeros[i] < numeros[a]){
        i++;    //avance i mientras el elemento i sea menor al pivote
      }

      while(j > a && numeros[j] > numeros[a]){
        j--;    //avance j mientras el elemento j sea mayor al pivote
      }

      while(i < j){
        swap(i,j);     
        i++;
        j--;
        while(numeros[i] < numeros[a]){   //si numeros[i] < numeros[a] entonces aumentamos i
          i++;
        }
        while(numeros[j] > numeros[a]){
          j--;                            //si numeros[i] < numeros[a] entonces decrementamos j
        }
      }

      if(a < j){
        swap(a,j);          //intercambiamos numeros en a con numeros en j 
      }
      return j;
    }

    private void shellSort(){
      System.out.println("Falta implementar");
    }

    public void swap(int i, int j){
      int aux = numeros[i];
      numeros[i] = numeros[j];
      numeros[j] = aux;
    }

  }

}
