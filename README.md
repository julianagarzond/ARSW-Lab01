# ARSW-Lab01

## Integrantes 
- Juliana Garzon Duque
- Juan David Navarro
- Eduardo Ocampo Arellano
- Sarah Vieda Castro

# BBP Formula

## Parte I - Introducción de Threads en JAVA

1. ¿Cual es la diferencia entre start() y run()?
- Al ejecutar el metodo start() , los threads se corren al mismo tiempo y su salida es en un orden aleatorio.
- Al ejecutar el metodo run() ,los threads se corren en un orden consecutivo y su salida es en el orden de ejecución de los threads.

## Part III - Evaluación del desempeño
Pruebas utilizando 1 Thread
![A](https://user-images.githubusercontent.com/53972469/73075095-27f48780-3e89-11ea-8264-af0b5f26bb7d.png) 

Pruebas utilizando 200 threads 
![B](https://user-images.githubusercontent.com/53972469/73075097-29be4b00-3e89-11ea-86c7-3c08250ed516.png)

Pruebas utilizando 500 threads
![C](https://user-images.githubusercontent.com/53972469/73075099-2aef7800-3e89-11ea-8ca0-8c74052ca57a.png)

1. Realizando un análisis de los resultados anteriores, podemos observar que utilizar 200 hilos es mucho más eficiente que usar 500 hilos, esto se debe a que se debe guardar el estado de cada uno de los hilos y luego restaurar su valor al momento de ejecutar.
    
2.   Es mucho más efectivo que utilizar la misma cantidad de hilos y de procesadores ya que se realiza un uso mas óptimo de los procesadores debido a que cada uno se hace cargo de algo de más que no le provoca una carga.
    
3.  De acuerdo a esta ley, el programa será menos eficiente si se agregan mas CPUs por lo que si tenemos 500 CPUs y cada una de estas para un hilo no se estaría usando la máxima capacidad del procesador. 
La solución mas adecuada seria que al tener c hilos y ls distribuimos sobre 500 CPUs entonces se le estarían dando un mejor uso al tener que manejar esos hilos, por esta razón aplicariamos de una forma más adecuada la ley de Amdahls.

# Dogs Race case

## Parte l

Ejecución usando 1 hilo.
![1h](https://user-images.githubusercontent.com/44879884/73420779-b20d7780-42f1-11ea-99f9-f1a0eba4344e.png)

Ejecución usando 2 hilos.
![2h](https://user-images.githubusercontent.com/44879884/73420833-df5a2580-42f1-11ea-9986-515fe847e10e.png)

Ejecución usando 3 hilos.
![3h](https://user-images.githubusercontent.com/44879884/73420837-e2edac80-42f1-11ea-8540-9a83e0b1ba10.png)

## Parte lI 

Al corregir el programa encontramos que había más inconsistencias ya que al finalizar la carrera había galgos que aparecían en la misma posición de llegada que otros.

## Parte lIl

1.  Para esta primera parte agregamos el uso de join() para que se muestre los resultados cuando finalicen todos los hilos.
  ``` java
  public void run() {
        for (int i = 0; i < can.getNumCarriles(); i++) {
            //crea los hilos 'galgos'
            galgos[i] = new Galgo(can.getCarril(i), "" + i, reg);
            //inicia los hilos
            galgos[i].start();
        }
        for (Galgo gl : galgos) {
            try {
                gl.join();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        can.winnerDialog(reg.getGanador(), reg.getUltimaPosicionAlcanzada() - 1);
        System.out.println("El ganador fue:" + reg.getGanador());
    }
```
        
    
2. Identificamos que la región critica que provoca las inconsistencias en los resultados esta en los get de la clase RegistroLlegada.

``` java
  public class RegistroLlegada {

    private int ultimaPosicionAlcanzada = 1;

    private String ganador = null;

    public String getGanador() {
        return ganador;
    }

    public void setGanador(String ganador) {
        this.ganador = ganador;
    }

    public int getUltimaPosicionAlcanzada() {
        return ultimaPosicionAlcanzada;
    }

    public void setUltimaPosicionAlcanzada(int ultimaPosicionAlcanzada) {
        this.ultimaPosicionAlcanzada = ultimaPosicionAlcanzada;
    }

}
```
    
3. Utilizamos synchronized en los get ya que es este método en el cual los hilos acceden al tiempo y provocan resultados erróneos.
``` java
  public synchronized int getUltimaPosicionAlcanzada() {
        return ultimaPosicionAlcanzada;
    }

    public synchronized int getUltimaPosicionAlcanzadaGalgo() {
        this.setUltimaPosicionAlcanzada(ultimaPosicionAlcanzada + 1);
        return ultimaPosicionAlcanzada - 1;
    }
```
    
4. En la clase MainCanodromo agregamos todos los oyentes que permiten la funcionalidad de detener y continuar.

``` java
  public class MainCanodromo {

    private static Galgo[] galgos;

    private static Canodromo can;

    private static RegistroLlegada reg = new RegistroLlegada();

    public static void main(String[] args) {
        //synchronized()
        can = new Canodromo(17, 100);
        galgos = new Galgo[can.getNumCarriles()];
        can.setVisible(true);

        //Acción del botón start
        can.setStartAction(
                new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        //como acción, se crea un nuevo hilo que cree los hilos
                        //'galgos', los pone a correr, y luego muestra los resultados.
                        //La acción del botón se realiza en un hilo aparte para evitar
                        //bloquear la interfaz gráfica.
                        ((JButton) e.getSource()).setEnabled(false);
                        new Thread() {
                            public void run() {
                                for (int i = 0; i < can.getNumCarriles(); i++) {
                                    //crea los hilos 'galgos'
                                    galgos[i] = new Galgo(can.getCarril(i), "" + i, reg);
                                    //inicia los hilos
                                    galgos[i].start();
                                }
                                for (Galgo gl : galgos) {
                                    try {
                                        gl.join();
                                    } catch (InterruptedException ex) {
                                        ex.printStackTrace();
                                    }
                                }

                                can.winnerDialog(reg.getGanador(), reg.getUltimaPosicionAlcanzada() - 1);
                                System.out.println("El ganador fue:" + reg.getGanador());
                            }
                        }.start();

                    }
                }
        );

        can.setStopAction(
                new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        ((JButton) e.getSource()).setEnabled(false);
                        new Thread() {
                            public void run() {
                                for (Galgo g : galgos) {
                                    g.changeState();
                                }
                                System.out.println("Carrera parada!");
                            }
                        }.start();

                    }
                }
        );

        can.setContinueAction(
                new ActionListener() {

                    @Override
                    public void actionPerformed(final ActionEvent e) {
                        ((JButton) e.getSource()).setEnabled(false);
                        new Thread() {
                            public void run() {
                                galgos[0].restart();
                                for (Galgo g : galgos) {
                                    g.changeState();
                                    g.restart();
                                }
                                System.out.println("Carrera reanudada!");
                            }
                        }.start();

                    }
                }
        );

    }

}
```
